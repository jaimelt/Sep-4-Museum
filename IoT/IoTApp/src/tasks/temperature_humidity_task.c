
/*
* temperature_humidity_task.c
*
* Created: 26/05/2020 12.39.14
* Author: Fabian Bernhardt
*/

//header
#include "temperature_humidity_task.h"

//required libraries
#include <stdlib.h>
#include <stdio.h>
#include <stdio_driver.h>
#include <avr/io.h>

//constants
#include "../constants/global_constants.h"

//task details
#define Temperature_Humidity_TASK_PRIORITY (configMAX_PRIORITIES - 3)
#define TEMPERATURE_HUMIDITY_SENSOR_TASK_NAME "TempHum"
#define TEMPERATURE_HUMIDITY_SENSOR_TAG "TEMP/HUM SENSOR TASK"
//task handler
static TaskHandle_t _temperatureHumiditySensorTaskHandle;

//private fields
static SemaphoreHandle_t _xPrintfSemaphore;
static EventGroupHandle_t _eventGroupHandleMeasure;
static EventGroupHandle_t _eventGroupHandleNewData;
static float _lastMeasurementTemperature;
static float _lastMeasurementHumidity;

static void _setup_temperature_humidity_driver()
{
	//create driver
	int result = hih8120Create();
	if (HIH8120_OK != result)
	{
		//printf("%s", TEMPERATURE_HUMIDITY_SENSOR_TAG);
		//printf(" :: FAILED DRIVER INITIALIZATION\n");
		exit(EXIT_FAILURE);
	}
	else
	{
		//printf("Init Temp + Hum driver\n");
	}
}

void temperatureHumiditySensor_inLoop()
{
	//wait for the start measuring bit to be true in the event group
	xEventGroupWaitBits(_eventGroupHandleMeasure,
						TEMPERATURE_HUMIDITY_MEASURE_BIT,
						pdTRUE,
						pdTRUE,
						portMAX_DELAY);

	hih8120DriverReturnCode_t result;

	//wake up the sensor from power down
	result = hih8120Wakeup();

	//after the wakeup, the sensor needs minimum 50ms to start measuring
	vTaskDelay(100);

	//if (HIH8120_OK != result)
	//{
	//xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
	//printf("%s", TEMPERATURE_HUMIDITY_SENSOR_TAG);
	//printf(" :: DRIVER CANNOT WAKE UP\n");
	//xSemaphoreGive(_xPrintfSemaphore);
	//}

	//pool sensor for result
	result = hih8120Meassure();

	//delay to fetch the results from the sensor
	vTaskDelay(100);

	//check the result
	if (HIH8120_OK != result)
	{
		//10 trials to get the measurement from the sensor
		int count = 10;
		while ((HIH8120_TWI_BUSY == result) && count > 0)
		{
			result = hih8120Meassure();
			//delay to fetch the results from the sensor
			vTaskDelay(20);
			count--;
		}
	}

	//get measurements
	if (result == HIH8120_OK)
	{
		_lastMeasurementTemperature = hih8120GetTemperature();
		_lastMeasurementHumidity = hih8120GetHumidity();

		//set the bit to true to signalize that the measurement was completed
		xEventGroupSetBits(_eventGroupHandleNewData, TEMPERATURE_HUMIDITY_READY_BIT);
	}
}

void vATemperatureHumiditySensorTask(void *pvParameters)
{
	for (;;)
	{
		temperatureHumiditySensor_inLoop();
	}
	vTaskDelete(_temperatureHumiditySensorTaskHandle);
}

void temperatureHumiditySensor_create(EventGroupHandle_t pvEventHandleMeasure,
									  EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pPrintfSemaphore)
{
	//setting variables
	_xPrintfSemaphore = pPrintfSemaphore;
	_eventGroupHandleMeasure = pvEventHandleMeasure;
	_eventGroupHandleNewData = pvEventHandleNewData;
	_lastMeasurementTemperature = 0;
	_lastMeasurementHumidity = 0;

	//starting the drivers
	_setup_temperature_humidity_driver();

	//task handler
	_temperatureHumiditySensorTaskHandle = NULL;

	//task creation
	xTaskCreate(vATemperatureHumiditySensorTask,
				(const portCHAR *)TEMPERATURE_HUMIDITY_SENSOR_TASK_NAME,
				configMINIMAL_STACK_SIZE,
				NULL,
				Temperature_Humidity_TASK_PRIORITY,
				&_temperatureHumiditySensorTaskHandle);
}

float temperatureHumiditySensor_getHumidity()
{
	return _lastMeasurementHumidity;
}

float temperatureHumiditySensor_getTemperature()
{
	return _lastMeasurementTemperature;
}