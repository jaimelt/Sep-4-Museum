/*
* light_sensor_task.c
*
* Created: 13/05/2020 16.19.34
*  Author: Marina Ionel
*/
#include <stdlib.h>
#include <stdio.h>

#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <semphr.h>
#include <event_groups.h>
#include <tsl2591.h>

#include "../constants/global_constants.h"

#define LIGHT_TASK_PRIORITY (configMAX_PRIORITIES - 3)
#define LIGHT_SENSOR_TASK_NAME "Light"
#define LIGHT_SENSOR_TAG "LIGHT SENSOR TASK"

static SemaphoreHandle_t _xPrintfSemaphore;
static EventGroupHandle_t _eventGroupHandleMeasure;
static EventGroupHandle_t _eventGroupHandleNewData;
static TaskHandle_t _lightSensorTaskHandle;
static float _lastMeasurementLux;

void LightSensor_callback(tsl2591ReturnCode_t pvTsl2591ReturnCode)
{
	if (pvTsl2591ReturnCode != TSL2591_OK && pvTsl2591ReturnCode !=TSL2591_DATA_READY)
	{
		xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
		printf("%s :: Light measurement failed :: return code %d\n", LIGHT_SENSOR_TAG, pvTsl2591ReturnCode);
		xSemaphoreGive(_xPrintfSemaphore);
	}
	else
	{
		float _lux = 0;
		tsl2591ReturnCode_t result = tsl2591GetLux(&_lux);
		if (TSL2591_OK == result)
		{
			//set data
			_lastMeasurementLux = _lux;
			xEventGroupSetBits(_eventGroupHandleNewData, LIGHT_READY_BIT);
		}
		else
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("%s :: Get light measurement failed :: return code %d\n", LIGHT_SENSOR_TAG, result);
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
		}
	}
}

static void _setup_light_driver()
{
	//create
	int result = tsl2591Create(LightSensor_callback);
	if (result != TSL2591_OK)
	{
		printf("%s :: FAILED DRIVER INITIALIZATION :: result code %d\n", LIGHT_SENSOR_TAG, result);
		exit(EXIT_FAILURE);
	}
	else
		printf("%s :: SUCCESSFULL DRIVER INITIALIZATION :: Light\n", LIGHT_SENSOR_TAG);

	//enable
	result = tsl2591Enable();
	if (result != TSL2591_OK)
	{
		printf("%s :: FAILED DRIVER ENABLING :: Light :: result code %d\n", LIGHT_SENSOR_TAG, result);
		exit(EXIT_FAILURE);
	}
	else
		printf("%s :: SUCCESSFULL DRIVER ENABLING :: Light\n", LIGHT_SENSOR_TAG);
}

void vALightSensorTask(void *pvParameters)
{	
	for (;;)
	{
		xEventGroupWaitBits(_eventGroupHandleMeasure,
		LIGHT_MEASURE_BIT,
		pdTRUE,
		pdTRUE,
		portMAX_DELAY);

		int result = tsl2591FetchData();

		if (result != TSL2591_OK)
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("%s :: Fetch light data failed %d\n", LIGHT_SENSOR_TAG, result);
			xSemaphoreGive(_xPrintfSemaphore);
		}
	}
	vTaskDelete(_lightSensorTaskHandle);
}

void LightSensor_create(EventGroupHandle_t pvEventHandleMeasure, EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pvPrintfSemaphore)
{
	_xPrintfSemaphore = pvPrintfSemaphore;
	_eventGroupHandleMeasure = pvEventHandleMeasure;
	_eventGroupHandleNewData = pvEventHandleNewData;
	_lastMeasurementLux = 0;

	_setup_light_driver();

	_lightSensorTaskHandle = NULL;

	xTaskCreate(
	vALightSensorTask,
	(const portCHAR *)LIGHT_SENSOR_TASK_NAME,
	configMINIMAL_STACK_SIZE + 200,
	NULL,
	LIGHT_TASK_PRIORITY,
	&_lightSensorTaskHandle);
}

float LightSensor_getLightMeasurement()
{
	return _lastMeasurementLux;
}