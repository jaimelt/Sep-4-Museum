/*
* light_sensor_task.c
*
* Created: 13/05/2020 16.19.34
*  Author: Marina Ionel
*/

#include <stdio.h>

#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <semphr.h>
#include <event_groups.h>

#include <tsl2591.h>

#include "../constants/global_constants.h"

#define LIGHT_TASK_PRIORITY (configMAX_PRIORITIES-4)
#define LIGHT_SENSOR_TASK_NAME "Light Sensor Task"
#define LIGHT_SENSOR_TAG "LIGHT SENSOR TASK"

static SemaphoreHandle_t _printfSemaphore;
static EventGroupHandle_t _eventGroupHandleMeasure;
static EventGroupHandle_t _eventGroupHandleNewData;
static TaskHandle_t _lightSensorTaskHandle;
static float _lastMeasurementLux;

void vALightSensorTask(void* pvParameters){
	for (;;){
		xEventGroupWaitBits
		(_eventGroupHandleMeasure,
		LIGHT_MEASURE_BIT,
		pdTRUE,
		pdTRUE,
		portMAX_DELAY );
		
		
		int result = tsl2591FetchData();
		
		if(result != TSL2591_OK) {
			xSemaphoreTake(_printfSemaphore, portMAX_DELAY);
			printf("%s :: Fetch light data failed %d\n", LIGHT_SENSOR_TAG, result);
			xSemaphoreGive(_printfSemaphore);
		}
	}
	vTaskDelete(_lightSensorTaskHandle);
}

void LightSensor_create(EventGroupHandle_t pvEventHandleMeasure,EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pvPrintfSemaphore){
	_printfSemaphore=pvPrintfSemaphore;
	_eventGroupHandleMeasure=pvEventHandleMeasure;
	_eventGroupHandleNewData=pvEventHandleNewData;
	_lastMeasurementLux=0;
	
	_lightSensorTaskHandle=NULL;
	
	xTaskCreate(
	vALightSensorTask,
	(const portCHAR*) LIGHT_SENSOR_TASK_NAME,
	configMINIMAL_STACK_SIZE+200,
	NULL,
	LIGHT_TASK_PRIORITY,
	&_lightSensorTaskHandle
	);
}

void LightSensor_callback(tsl2591ReturnCode_t pvTsl2591ReturnCode){
	if (pvTsl2591ReturnCode!=TSL2591_OK){
		xSemaphoreTake(_printfSemaphore, portMAX_DELAY);
		printf("%s :: Light measurement failed :: return code %d\n", LIGHT_SENSOR_TAG, pvTsl2591ReturnCode);
		xSemaphoreGive(_printfSemaphore);
	}
	
	float _lux;
	tsl2591ReturnCode_t result=tsl2591GetLux(&_lux);
	if (TSL2591_OK==result){
		xEventGroupSetBits(_eventGroupHandleNewData, LIGHT_READY_BIT);
		//set data
		_lastMeasurementLux=_lux;
	}
	else{
		xSemaphoreTake(_printfSemaphore, portMAX_DELAY);
		printf("%s :: Get light measurement failed :: return code %d\n",LIGHT_SENSOR_TAG, result);
		xSemaphoreTake(_printfSemaphore, portMAX_DELAY);
	}
}

float LightSensor_getLightMeasurement(){
	return _lastMeasurementLux;
}