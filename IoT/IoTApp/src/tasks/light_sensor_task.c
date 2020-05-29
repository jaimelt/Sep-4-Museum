/*
* light_sensor_task.c
*
* Created: 13/05/2020 16.19.34
*  Author: Marina Ionel
*/
#include "light_sensor_task.h"

#include <stdlib.h>
#include <stdio.h>

#include "../constants/global_constants.h"

#define LIGHT_TASK_PRIORITY (configMAX_PRIORITIES - 3)
#define LIGHT_SENSOR_TASK_NAME "Light"
#define LIGHT_SENSOR_TAG "LIGHT SENSOR TASK"

static SemaphoreHandle_t _xPrintfSemaphore;
static EventGroupHandle_t _eventGroupHandleMeasure;
static EventGroupHandle_t _eventGroupHandleNewData;
static TaskHandle_t _lightSensorTaskHandle;
static float _lastMeasurementLux;

void LightSensor_callback(tsl2591ReturnCode_t rc)
{
	if (rc != TSL2591_DATA_READY)
	{
		xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
		printf("%s :: ", LIGHT_SENSOR_TAG);
		printf("Light sensor not ready :: return code %d\n", rc);
		xSemaphoreGive(_xPrintfSemaphore);
		return;
	}
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
		printf("%s :: ", LIGHT_SENSOR_TAG);
		printf("Get light measurement failed :: return code %d\n", result);
		xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
	}
}

static void _setup_light_driver()
{
	//create
	int result = tsl2591Create(LightSensor_callback);
	if (result != TSL2591_OK)
	{
		printf("%s :: ", LIGHT_SENSOR_TAG);
		printf("FAILED DRIVER INITIALIZATION :: result code %d\n", result);
		exit(EXIT_FAILURE);
	}
	else
	{
		printf("%s :: ", LIGHT_SENSOR_TAG);
		printf("SUCCESSFUL DRIVER INITIALIZATION\n");
	}

	//enable
	result = tsl2591Enable();
	if (result != TSL2591_OK)
	{
		printf("%s :: ", LIGHT_SENSOR_TAG);
		printf("FAILED DRIVER ENABLING :: Light :: result code %d\n", result);
		exit(EXIT_FAILURE);
	}
	else
	{
		printf("%s :: ", LIGHT_SENSOR_TAG);
		printf("SUCCESSFUL DRIVER ENABLING\n");
	}
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
			printf("%s :: ", LIGHT_SENSOR_TAG);
			printf("ERROR :: ");
			printf("Fetch light data failed %d\n", result);
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