/*
* co2_sensor_task.c
*
* Created: 16/05/2020 11.14.42
* Author: Justinas Jancys, Fabian Bernhardt
*/

//header
#include "co2_sensor_task.h"

//required libraries
#include <stdlib.h>
#include <stdio.h>
#include <stdio_driver.h>
#include <avr/io.h>

//constants
#include "../constants/global_constants.h"

//task details
#define CO2_TASK_PRIORITY (configMAX_PRIORITIES - 3)
#define CO2_SENSOR_TASK_NAME "CO2"
#define CO2_SENSOR_TAG "CO2 SENSOR TASK"
//task handler
static TaskHandle_t _CO2SensorTaskHandle;

//private fields
static SemaphoreHandle_t _xPrintfSemaphore;
static EventGroupHandle_t _eventGroupHandleMeasure;
static EventGroupHandle_t _eventGroupHandleNewData;
static uint16_t _lastCo2Measurement;
static mh_z19_return_code_t _rc;

void co2Sensor_callback(uint16_t ppm)
{
	_lastCo2Measurement = ppm;
	//set the bit to true to signalize that the measurement was completed
	xEventGroupSetBits(_eventGroupHandleNewData, CO2_READY_BIT);
}

static void inline _setup_co2_driver()
{
	//create driver
	mh_z19_create(ser_USART3, co2Sensor_callback);
}

static void inline co2Sensor_inLoop()
{
	//wait for the start measuring bit to be true in the event group
	xEventGroupWaitBits(_eventGroupHandleMeasure,
						CO2_MEASURE_BIT,
						pdTRUE,
						pdTRUE,
						portMAX_DELAY);

	//perform measuring
	_rc = mh_z19_take_meassuring();

	// if (_rc != MHZ19_OK)
	// {
	// 		printf(":: Fetch co2 data failed \n");
	//		return;
	// }
}

void vAco2SensorTask(void *pvParameters)
{
	for (;;)
	{
		co2Sensor_inLoop();
	}
	vTaskDelete(_CO2SensorTaskHandle);
}

void co2Sensor_create(EventGroupHandle_t pvEventHandleMeasure, EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pPrintfSemaphore)
{
	//setting variables
	_xPrintfSemaphore = pPrintfSemaphore;
	_eventGroupHandleMeasure = pvEventHandleMeasure;
	_eventGroupHandleNewData = pvEventHandleNewData;
	_lastCo2Measurement = 0;

	//starting the drivers
	_setup_co2_driver();

	//task handler
	_CO2SensorTaskHandle = NULL;

	//task creation
	xTaskCreate(
		vAco2SensorTask,
		(const portCHAR *)CO2_SENSOR_TASK_NAME,
		configMINIMAL_STACK_SIZE,
		NULL,
		CO2_TASK_PRIORITY,
		&_CO2SensorTaskHandle);
}

uint16_t co2sensor_getCo2()
{
	return _lastCo2Measurement;
}