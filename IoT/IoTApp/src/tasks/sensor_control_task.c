/*
* sensor_control_task.c
*
* Created: 16/05/2020 10.19.48
*  Author: Marina Ionel
*/

#include <stdio.h>
#include <avr/io.h>
//freertos
#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <semphr.h>
#include <queue.h>

//drivers
#include <lora_driver.h>
#include <stdio_driver.h>

#include "sensor_control_task.h"
#include "../constants/global_constants.h"
#include "../handlers/sensor_data_package_handler.h"
#include "lora_sensor_task.h"
#include "light_sensor_task.h"
#include "temperature_humidity_task.h"
#include "co2_sensor_task.h"
#include "../handlers/rc_servo_handler.h"

//constants
#define SENSOR_CONTROL_TAG "SENSOR CONTROL"
//1 minutes in ms = 60000
#define TIME_DELAY_BETWEEN_MEASUREMENTS ((5 * 60000) / portTICK_PERIOD_MS)
#define SENSOR_CONTROL_TASK_PRIORITY (configMAX_PRIORITIES - 3)
#define SENSOR_CONTROL_TASK_NAME "SensorC"

//private fields
static TaskHandle_t _sensor_control_task_handle = NULL;
static SemaphoreHandle_t _xPrintfSemaphore = NULL;
static EventGroupHandle_t _event_group_measure = NULL;
static EventGroupHandle_t _event_group_new_data = NULL;
static QueueHandle_t _sendingQueue = NULL;

void vASensorControlTask(void *pvParameters)
{
	for (;;)
	{
		//set bits to measure
		xEventGroupSetBits(_event_group_measure,
						   ALL_MEASURE_BITS);

		/*CO2_READY_BIT | TEMPERATURE_HUMIDITY_READY_BIT |    */

		//wait for new data bits
		xEventGroupWaitBits(
			_event_group_new_data,
			ALL_READY_BITS,
			pdTRUE,
			pdTRUE,
			portMAX_DELAY);

		//get co2
		uint16_t _co2 = co2sensor_getCo2();

		xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
		printf("Co2 %d \n", _co2);
		xSemaphoreGive(_xPrintfSemaphore);

		//get temperature
		float _temperature = temperatureHumiditySensor_getTemperature();

		xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
		printf("Temperature %.2f \n", _temperature);
		xSemaphoreGive(_xPrintfSemaphore);

		//get humidity
		float _humidity = temperatureHumiditySensor_getHumidity();

		xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
		printf("Humidity %.2f \n", _humidity);
		xSemaphoreGive(_xPrintfSemaphore);

		//get light
		float _lightInLux = LightSensor_getLightMeasurement();

		xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
		printf("Light %.2f \n", _lightInLux);
		xSemaphoreGive(_xPrintfSemaphore);

		if (_lightInLux > CRITICAL_LIGHT_MAX)
		{
			rcServo_Down();
		}
		if (_lightInLux < CRITICAL_LIGHT_MIN)
		{
			rcServo_Up();
		}

		SensorDataPackageHandler_setCo2ppm(_co2);
		SensorDataPackageHandler_setTemperature(_temperature);
		SensorDataPackageHandler_setHumidity(_humidity);
		SensorDataPackageHandler_setLight(_lightInLux);

		lora_payload_t _lora_payload = SensorDataPackageHandler_getLoraPayload(LORA_PAYLOAD_PORT_NO);

		xQueueSend(_sendingQueue, //queue handler
				   (void *)&_lora_payload,
				   portMAX_DELAY);

		vTaskDelay(TIME_DELAY_BETWEEN_MEASUREMENTS);
	}
	vTaskDelete(_sensor_control_task_handle);
}

void sensorControl_create()
{
	if (_xPrintfSemaphore == NULL)
	{
		_xPrintfSemaphore = xSemaphoreCreateMutex();
		if (_xPrintfSemaphore != NULL)
			xSemaphoreGive(_xPrintfSemaphore);
	}

	if (_event_group_measure == NULL)
		_event_group_measure = xEventGroupCreate();

	if (_event_group_new_data == NULL)
		_event_group_new_data = xEventGroupCreate();

	if (_sendingQueue == NULL)
		_sendingQueue = xQueueCreate(1, sizeof(lora_payload_t));

	//create lora driver
	loraSensor_create(_sendingQueue, _xPrintfSemaphore);

	//create co2
	co2Sensor_create(_event_group_measure, _event_group_new_data, _xPrintfSemaphore);

	//create humidity/temperature
	temperatureHumiditySensor_create(_event_group_measure, _event_group_new_data, _xPrintfSemaphore);

	//create light
	LightSensor_create(_event_group_measure, _event_group_new_data, _xPrintfSemaphore);

	//create rc servo
	rc_servo_create();

	_sensor_control_task_handle = NULL;

	//create the task
	xTaskCreate(
		vASensorControlTask,						/* Task function. */
		(const portCHAR *)SENSOR_CONTROL_TASK_NAME, /* String with name of task. */
		configMINIMAL_STACK_SIZE + 100,				/* Stack size in words. */
		NULL,										/* Parameter passed as input of the task */
		SENSOR_CONTROL_TASK_PRIORITY,				/* Priority of the task. */
		&_sensor_control_task_handle);				/* Task handle. */
}