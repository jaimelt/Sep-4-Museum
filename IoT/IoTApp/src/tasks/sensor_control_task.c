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

#include "light_sensor_task.h"
#include "../constants/global_constants.h"
#include "../handler/sensor_data_package_handler.h"
#include "lora_sensor_task.h"

//constants
#define SENSOR_CONTROL_TAG "SENSOR CONTROL"
//5 minutes in ms
#define TIME_DELAY_BETWEEN_MEASUREMENTS 300000
#define SENSOR_CONTROL_TASK_PRIORITY (configMAX_PRIORITIES - 2)
#define SENSOR_CONTROL_TASK_NAME "Sensor Control Task"

//private fields
static TaskHandle_t _sensor_control_task_handle;
static SemaphoreHandle_t _xPrintfSemaphore;
static EventGroupHandle_t _event_group_measure;
static EventGroupHandle_t _event_group_new_data;
static QueueHandle_t _sendingQueue;

void vASensorControlTask(void *pvParameters)
{
	for (;;)
	{
		//set bits to measure
		xEventGroupSetBits(_event_group_measure, CO2_MEASURE_BIT | TEMPERATURE_HUMIDITY_MEASURE_BIT | LIGHT_MEASURE_BIT);

		/*CO2_READY_BIT | TEMPERATURE_HUMIDITY_READY_BIT | */

		//wait for new data bits
		xEventGroupWaitBits(
			_event_group_new_data,
			LIGHT_READY_BIT,
			pdTRUE,
			pdTRUE,
			portMAX_DELAY);

		//get co2
		//get temperature
		//get humidity

		//get light
		float _lightInLux = LightSensor_getLightMeasurement();

		SensorDataPackageHandler_setLight(_lightInLux);
		lora_payload_t _lora_payload = SensorDataPackageHandler_getLoraPayload(LORA_PAYLOAD_PORT_NO);

		xQueueSend(_sendingQueue, //queue handler
				   (void *)&_lora_payload,
				   portMAX_DELAY);

		vTaskDelay(TIME_DELAY_BETWEEN_MEASUREMENTS);
	}
	vTaskDelete(_sensor_control_task_handle);
}

void sensorControl_create(SemaphoreHandle_t pPrintfSemaphore)
{
	_xPrintfSemaphore = pPrintfSemaphore;
	_event_group_measure = xEventGroupCreate();

	if (_event_group_measure != NULL)
	{
		if (_xPrintfSemaphore != NULL)
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("%s :: SUCCESS :: created event group measure\n", SENSOR_CONTROL_TAG);
			xSemaphoreGive(_xPrintfSemaphore);
		}
	}
	else
	{
		if (_xPrintfSemaphore != NULL)
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("%s :: ERROR :: creation of event group measure failed\n", SENSOR_CONTROL_TAG);
			xSemaphoreGive(_xPrintfSemaphore);
		}
	}

	_event_group_new_data = xEventGroupCreate();
	if (_event_group_new_data != NULL)
	{
		if (_xPrintfSemaphore != NULL)
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("%s :: SUCCESS :: created event group new data\n", SENSOR_CONTROL_TAG);
			xSemaphoreGive(_xPrintfSemaphore);
		}
	}
	else
	{
		if (_xPrintfSemaphore != NULL)
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("%s :: ERROR :: creation of event group new data failed\n", SENSOR_CONTROL_TAG);
			xSemaphoreGive(_xPrintfSemaphore);
		}
	}

	_sendingQueue = xQueueCreate(1, sizeof(lora_payload_t));
	if (_sendingQueue != NULL)
	{
		if (_xPrintfSemaphore != NULL)
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("%s :: SUCCESS :: created queue\n", SENSOR_CONTROL_TAG);
			xSemaphoreGive(_xPrintfSemaphore);
		}
	}
	else
	{
		if (_xPrintfSemaphore != NULL)
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("%s :: ERROR :: creation of queue\n", SENSOR_CONTROL_TAG);
			xSemaphoreGive(_xPrintfSemaphore);
		}
	}

	//create lora driver
	loraSensor_create(_sendingQueue, _xPrintfSemaphore);

	//create co2
	//TODO

	//create humidity/temperature
	//TODO

	//create light
	LightSensor_create(_event_group_measure, _event_group_new_data, _xPrintfSemaphore);

	_sensor_control_task_handle = NULL;
	//create the task
	xTaskCreate(
		vASensorControlTask,						/* Task function. */
		(const portCHAR *)SENSOR_CONTROL_TASK_NAME, /* String with name of task. */
		configMINIMAL_STACK_SIZE,					/* Stack size in words. */
		NULL,										/* Parameter passed as input of the task */
		SENSOR_CONTROL_TASK_PRIORITY,				/* Priority of the task. */
		&_sensor_control_task_handle);				/* Task handle. */
}