
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

//local variables
static SemaphoreHandle_t _xPrintfSemaphore;
static EventGroupHandle_t _eventGroupHandleMeasure;
static EventGroupHandle_t _eventGroupHandleNewData;
static TaskHandle_t _temperatureHumiditySensorTaskHandle;
static float _lastMeasurementTemperature;
static float _lastMeasurementHumidity;

static void _setup_temperature_humidity_driver()
{
	//set up driver
	int result = hih8120Create();
	if(HIH8120_OK != result)
	{
		//printf("%s", TEMPERATURE_HUMIDITY_SENSOR_TAG);
		printf(" :: FAILED DRIVER INITIALIZATION\n");
		exit(EXIT_FAILURE);
	}
	else
	{
		printf("Init Temp + Hum driver\n");
	}
}

static hih8120DriverReturnCode_t result;

void _TemperatureHumiditySensorTask(void *pvParameters)
{
	for (;;)
	{
		//wait for the start measuring bit in the event group
		xEventGroupWaitBits(_eventGroupHandleMeasure, 
		TEMPERATURE_HUMIDITY_MEASURE_BIT, 
		pdTRUE, 
		pdTRUE, 
		portMAX_DELAY);
	
		//wake up the sensor from power down
		result = hih8120Wakeup();
		if ( HIH8120_OK != result )
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			//printf("%s", TEMPERATURE_HUMIDITY_SENSOR_TAG);
			printf(" :: DRIVER CANNOT WAKE UP\n");
			xSemaphoreGive(_xPrintfSemaphore);
		}	
		
		//delay 50ms to wait for the sensor to be ready
		vTaskDelay(pdMS_TO_TICKS(80UL));
		//pool sensor for result
		result = hih8120Meassure();
		//check the result
		if ( HIH8120_OK !=  result)
		{
			int count = 10;
			while((HIH8120_TWI_BUSY == result) && count > 0)
			{
				result = hih8120Meassure();
				vTaskDelay(pdMS_TO_TICKS(20UL));
				count--;
				xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
				printf("\n%s", TEMPERATURE_HUMIDITY_SENSOR_TAG);
				printf(" :: FETCH TEMP/HUM FAILED\n");
				xSemaphoreGive(_xPrintfSemaphore);
			}
		}
		else
		{	
			//get measurements
			_lastMeasurementTemperature = hih8120GetTemperature();
			_lastMeasurementHumidity = hih8120GetHumidity();
			
			//set the bit to true to signalize that the measurement was completed
			xEventGroupSetBits(_eventGroupHandleNewData, TEMPERATURE_HUMIDITY_READY_BIT);
		}
	}
	vTaskDelete(_temperatureHumiditySensorTaskHandle);
}

void temperatureHumiditySensor_create(EventGroupHandle_t pvEventHandleMeasure, 
EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pvPrintfSemaphore)
{
	//setting variables
	_xPrintfSemaphore = pvPrintfSemaphore;
	_eventGroupHandleMeasure = pvEventHandleMeasure;
	_eventGroupHandleNewData = pvEventHandleNewData;
	_lastMeasurementTemperature = 0.0;
	_lastMeasurementHumidity = 0.0;
	
	//starting the drivers
	_setup_temperature_humidity_driver();
	
	//task handler
	_temperatureHumiditySensorTaskHandle = NULL;
	
	//task creation
	xTaskCreate(_TemperatureHumiditySensorTask, 
	(const portCHAR *)TEMPERATURE_HUMIDITY_SENSOR_TASK_NAME, 
	configMINIMAL_STACK_SIZE + 400, 
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