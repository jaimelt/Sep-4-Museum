/*
* co2_sensor_task.c
*
* Created: 16/05/2020 11.14.42
*  Author:
*/

#include <stdlib.h>
#include <stdio.h>

#include <stdint.h>
#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <semphr.h>
#include <event_groups.h>
#include <mh_z19.h>

#include "../constants/global_constants.h"
#include "../setup/setup_drivers.h"

#define CO2_TASK_PRIORITY (configMAX_PRIORITIES - 3)
#define CO2_SENSOR_TAG "CO2 SENSOR TASK"
#define CO2_SENSOR_TASK_NAME "CO2"

static SemaphoreHandle_t _xPrintfSemaphore;
static EventGroupHandle_t _eventGroupHandleMeasure;
static EventGroupHandle_t _eventGroupHandleNewData;
static TaskHandle_t _co2SensorTaskHandle;

static uint16_t _lastCo2Measurement = 0;

void co2Sensor_callback(uint16_t ppm)
{
	_lastCo2Measurement = ppm;
}

uint16_t co2sensor_getCo2()
{
	return _lastCo2Measurement;
}

void vACo2SensorTask()
{
	for(;;)
	{
		xEventGroupWaitBits(_eventGroupHandleMeasure,
		CO2_MEASURE_BIT,
		pdTRUE,
		pdTRUE,
		portMAX_DELAY);
		
		uint16_t co2 = NULL;
		co2 = co2sensor_getCo2();

		if(co2 == NULL)
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("%s :: Fetch co2 data failed\n", CO2_SENSOR_TAG);
			xSemaphoreGive(_xPrintfSemaphore);
		}
		else
		{
			xEventGroupSetBits(_eventGroupHandleNewData, CO2_MEASURE_BIT);
		}
	}
}

void CO2_create(EventGroupHandle_t pvEventHandleMeasure, EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pvPrintfSemaphore)
{
	_xPrintfSemaphore = pvPrintfSemaphore;
	_eventGroupHandleMeasure = pvEventHandleMeasure;
	_eventGroupHandleNewData = pvEventHandleNewData;
	
	_co2SensorTaskHandle = NULL;
	
	_setup_co2_driver();
	
	xTaskCreate(
	vACo2SensorTask,
	(const portCHAR *)CO2_SENSOR_TASK_NAME,
	configMINIMAL_STACK_SIZE + 200,
	NULL,
	CO2_TASK_PRIORITY,
	&_co2SensorTaskHandle);
	
}

void co2sensor_measure()
{
	mh_z19_return_code_t rc;
	rc = mh_z19_take_meassuring();
	if(rc != MHZ19_OK)
	{
		xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
		printf("%s :: CO2 sensor not ready :: return code %d\n", CO2_SENSOR_TAG, rc);
		xSemaphoreGive(_xPrintfSemaphore);
	}
	
	uint16_t ppm = 0;
	rc = mh_z19_get_co2_ppm(&ppm);
	if(rc != MHZ19_OK)
	{
		xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
		printf("%s :: CO2 sensor not ready :: return code %d\n", CO2_SENSOR_TAG, rc);
		xSemaphoreGive(_xPrintfSemaphore);
	}
	else
	{
		_lastCo2Measurement = ppm;
	}
}

