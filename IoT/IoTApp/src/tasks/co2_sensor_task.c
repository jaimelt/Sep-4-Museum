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

void vACo2Task(void *pvParameters)
{
	for(;;)
	{
		xEventGroupWaitBits(_eventGroupHandleMeasure,
		CO2_MEASURE_BIT,
		pdTRUE,
		pdTRUE,
		portMAX_DELAY);
				
		co2sensor_measure();
	}
}

static void _setup_co2_driver()
{
	mh_z19_create(ser_USART3, co2Sensor_callback);
	xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
	//printf("%s :: SUCCESSFULL DRIVER INITIALIZATION\n", CO2_SENSOR_TAG);
	xSemaphoreGive(_xPrintfSemaphore);
}

void co2Sensor_create(EventGroupHandle_t pvEventHandleMeasure, EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pPrintfSemaphore)
{
	_xPrintfSemaphore = pPrintfSemaphore;
	_eventGroupHandleMeasure = pvEventHandleMeasure;
	_eventGroupHandleNewData = pvEventHandleNewData;
	
	_co2SensorTaskHandle = NULL;
	
	_setup_co2_driver();
	
	xTaskCreate(
	vACo2Task,
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
		//printf("%s :: CO2 sensor not ready :: return code %d\n", CO2_SENSOR_TAG, rc);
		printf("pls");
		xSemaphoreGive(_xPrintfSemaphore);
	}
	
	uint16_t ppm = 0;
	rc = mh_z19_get_co2_ppm(&ppm);
	if(rc != MHZ19_OK)
	{
		xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
		printf("work");
		//printf("%s :: CO2 sensor not ready :: return code %d\n", CO2_SENSOR_TAG, rc);
		xSemaphoreGive(_xPrintfSemaphore);
	}
	else
	{
		xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
		printf("works");
		//printf("%s :: CO2 sensor data retrived\n", CO2_SENSOR_TAG);
		xSemaphoreGive(_xPrintfSemaphore);
		_lastCo2Measurement = ppm;
		xEventGroupSetBits(_eventGroupHandleNewData, CO2_READY_BIT);
	}
}

