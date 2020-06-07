/*
* sensor_control_task.c
*
* Created: 16/05/2020 10.19.48
*  Author: Marina Ionel
*/

//headers
#include "sensor_control_task.h"
#include "../handlers/sensor_data_package_handler.h"
#include "lora_task.h"
#include "light_sensor_task.h"
#include "temperature_humidity_task.h"
#include "co2_sensor_task.h"
#include "rc_servo_task.h"

//required libraries
#include <stdio.h>
#include <avr/io.h>

//FreeRTOS
#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <semphr.h>
#include <queue.h>

//drivers
//#include <lora_driver.h>
#include <stdio_driver.h>

//constants
#include "../constants/global_constants.h"

//task details
#define SENSOR_CONTROL_TAG "SENSOR CONTROL"
#define SENSOR_CONTROL_TASK_PRIORITY (configMAX_PRIORITIES - 3)
#define SENSOR_CONTROL_TASK_NAME "SensorC"

//task handler
static TaskHandle_t _sensor_control_task_handle = NULL;

//private fields
#define TIME_DELAY_BETWEEN_MEASUREMENTS ((5 * 60000) / portTICK_PERIOD_MS) //1 minutes in ms = 60000
static SemaphoreHandle_t _xPrintfSemaphore = NULL;
static EventGroupHandle_t _event_group_measure = NULL;
static EventGroupHandle_t _event_group_new_data = NULL;
static QueueHandle_t _sendingQueue = NULL;
static QueueHandle_t _rc_servo_queue = NULL;

void vASensorControlTask(void *pvParameters)
{
	for (;;)
	{
		//set bits to true in order to start measurements
		xEventGroupSetBits(_event_group_measure,
						   ALL_MEASURE_BITS);

		//wait for new data bits set by the sensors when the measurements are done
		EventBits_t uxBits = xEventGroupWaitBits(
			_event_group_new_data,
			ALL_READY_BITS,
			pdTRUE,
			pdTRUE,
			TIME_DELAY_BETWEEN_MEASUREMENTS);

		if ((uxBits & ALL_READY_BITS) != ALL_READY_BITS)
		{
			continue;
		}

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

		rcServo_Action_t _tmp;
		//light values checking that trigger rcServo behavior
		if (_lightInLux > CRITICAL_LIGHT_MAX)
		{
			_tmp = DOWN;
			xQueueSend(_rc_servo_queue, &_tmp, portMAX_DELAY);
		}
		if (_lightInLux < CRITICAL_LIGHT_MIN)
		{
			_tmp = UP;
			xQueueSend(_rc_servo_queue, &_tmp, portMAX_DELAY);
		}

		//set the measurement values inside the sensor data package
		SensorDataPackageHandler_setCo2ppm(_co2);
		SensorDataPackageHandler_setTemperature(_temperature);
		SensorDataPackageHandler_setHumidity(_humidity);
		SensorDataPackageHandler_setLight(_lightInLux);

		//get the payload
		lora_payload_t _lora_payload = SensorDataPackageHandler_getLoraPayload(LORA_PAYLOAD_PORT_NO);

		//send the payload to the queue
		xQueueSend(_sendingQueue, //queue handler
				   (void *)&_lora_payload,
				   portMAX_DELAY);

		vTaskDelay(TIME_DELAY_BETWEEN_MEASUREMENTS);
	}
	vTaskDelete(_sensor_control_task_handle);
}

void sensorControl_create()
{
	//create print mutex
	if (_xPrintfSemaphore == NULL)
	{
		_xPrintfSemaphore = xSemaphoreCreateMutex();
		if (_xPrintfSemaphore != NULL)
			xSemaphoreGive(_xPrintfSemaphore);
	}

	//create event groups
	if (_event_group_measure == NULL)
		_event_group_measure = xEventGroupCreate();

	if (_event_group_new_data == NULL)
		_event_group_new_data = xEventGroupCreate();

	//create lorawan queue
	if (_sendingQueue == NULL)
		_sendingQueue = xQueueCreate(1, sizeof(lora_payload_t));

	//create rc servo queue
	if (_rc_servo_queue == NULL)
		_rc_servo_queue = xQueueCreate(1, sizeof(rcServo_Action_t));

	//create lora task
	lorawan_create(_sendingQueue, _xPrintfSemaphore);

	//create co2 task
	co2Sensor_create(_event_group_measure, _event_group_new_data, _xPrintfSemaphore);

	//create humidity/temperature task
	temperatureHumiditySensor_create(_event_group_measure, _event_group_new_data, _xPrintfSemaphore);

	//create light task
	LightSensor_create(_event_group_measure, _event_group_new_data, _xPrintfSemaphore);

	//create rc servo
	rcServoTask_create(_rc_servo_queue);

	//create the task
	xTaskCreate(
		vASensorControlTask,						/* Task function. */
		(const portCHAR *)SENSOR_CONTROL_TASK_NAME, /* String with name of task. */
		configMINIMAL_STACK_SIZE + 100,				/* Stack size in words. */
		NULL,										/* Parameter passed as input of the task */
		SENSOR_CONTROL_TASK_PRIORITY,				/* Priority of the task. */
		&_sensor_control_task_handle);				/* Task handle. */
}