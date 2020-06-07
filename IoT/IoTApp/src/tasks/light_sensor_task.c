/*
* light_sensor_task.c
*
* Created: 13/05/2020 16.19.34
*  Author: Marina Ionel
*/

//header
#include "light_sensor_task.h"

//required libraries
#include <stdlib.h>
#include <stdio.h>

//constants
#include "../constants/global_constants.h"

//task details
#define LIGHT_TASK_PRIORITY (configMAX_PRIORITIES - 3)
#define LIGHT_SENSOR_TASK_NAME "Light"
#define LIGHT_SENSOR_TAG "LIGHT SENSOR TASK"
//task handler
static TaskHandle_t _lightSensorTaskHandle;

//private fields
static SemaphoreHandle_t _xPrintfSemaphore;
static EventGroupHandle_t _eventGroupHandleMeasure;
static EventGroupHandle_t _eventGroupHandleNewData;
static float _lastMeasurementLux;

void LightSensor_callback(tsl2591ReturnCode_t rc)
{
	//verify if the value was measured
	if (rc != TSL2591_DATA_READY)
	{
		return;
	}
	float _lux = 0;
	tsl2591ReturnCode_t result = tsl2591GetLux(&_lux);
	if (TSL2591_OK == result)
	{
		//set data
		_lastMeasurementLux = _lux;
		//set the bit to true to signalize that the measurement was completed
		xEventGroupSetBits(_eventGroupHandleNewData, LIGHT_READY_BIT);
	}
}

static void inline _setup_light_driver()
{
	//create driver
	int result = tsl2591Create(LightSensor_callback);

	//check if creation was completed
	if (result != TSL2591_OK)
	{
		exit(EXIT_FAILURE);
	}
	else
	{
		//printf("Initialized Light driver\n");
	}

	//enable
	result = tsl2591Enable();
	if (result != TSL2591_OK)
	{
		exit(EXIT_FAILURE);
	}
	else
	{
		//printf("Enabled light driver\n");
	}
}

static void inline LightSensor_inLoop()
{
	//wait for the start measuring bit to be true in the event group
	xEventGroupWaitBits(_eventGroupHandleMeasure,
						LIGHT_MEASURE_BIT,
						pdTRUE,
						pdTRUE,
						portMAX_DELAY);

	//perform measuring
	tsl2591FetchData();

	// int result = tsl2591FetchData();

	// if (result != TSL2591_OK)
	// {
	// 	//printf("%s :: Fetch light data failed %d\n", LIGHT_SENSOR_TAG, result);
	// }
}

void vALightSensorTask(void *pvParameters)
{
	for (;;)
	{
		LightSensor_inLoop();
	}
	vTaskDelete(_lightSensorTaskHandle);
}

void LightSensor_create(EventGroupHandle_t pvEventHandleMeasure, EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pvPrintfSemaphore)
{
	//setting variables
	_xPrintfSemaphore = pvPrintfSemaphore;
	_eventGroupHandleMeasure = pvEventHandleMeasure;
	_eventGroupHandleNewData = pvEventHandleNewData;
	_lastMeasurementLux = 0;

	//starting the drivers
	_setup_light_driver();

	//task handler
	_lightSensorTaskHandle = NULL;

	//task creation
	xTaskCreate(
		vALightSensorTask,
		(const portCHAR *)LIGHT_SENSOR_TASK_NAME,
		configMINIMAL_STACK_SIZE,
		NULL,
		LIGHT_TASK_PRIORITY,
		&_lightSensorTaskHandle);
}

float LightSensor_getLightMeasurement()
{
	return _lastMeasurementLux;
}