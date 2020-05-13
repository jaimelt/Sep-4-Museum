/*
* light_sensor_task.c
*
* Created: 13/05/2020 16.19.34
*  Author: Marina Ionel
*/

#include <ATMEGA_FreeRTOS.h>
#include <stdio.h>
#include <stdio_driver.h>
#include <task.h>
#include <semphr.h>
#include <event_groups.h>
#include <tsl2591.h>

#define LIGHT_TASK_PRIORITY (configMAX_PRIORITIES-4)
#define LIGHT_BIT_3 (1<<3)

static SemaphoreHandle_t _printfSemaphore;
static EventGroupHandle_t _eventGroupHandleMeasure;
static EventGroupHandle_t _eventGroupHandleNewData;
static TaskHandle_t _lightSensorTaskHandle;
static float _lastMeasurementLux;

void vALightSensorTask(void* pvParameters){
	for (;;){
		xEventGroupWaitBits
		(_eventGroupHandleMeasure,		/* The event group being tested. */
		LIGHT_BIT_3,					/* The bits within the event group to wait for. */
		pdTRUE,							/* BIT_0 & BIT_4 should be cleared before returning. */
		pdFALSE,						/* Don't wait for both bits, either bit will do. */
		portMAX_DELAY );
		
		
		int result = tsl2591FetchData();
		
		if(result != TSL2591_OK) {
			xSemaphoreTake(_printfSemaphore, portMAX_DELAY);
			printf("Fetch light data failed %d\n", result);
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
	(const portCHAR*)"Light Sensor Task",
	configMINIMAL_STACK_SIZE+200,
	NULL,
	LIGHT_TASK_PRIORITY,
	&_lightSensorTaskHandle
	);
}

void LightSensor_callback(tsl2591ReturnCode_t pvTsl2591ReturnCode){
	if (pvTsl2591ReturnCode!=TSL2591_OK){
		xSemaphoreTake(_printfSemaphore, portMAX_DELAY);
		printf("Light measurement failed :: return code %d\n", pvTsl2591ReturnCode);
		xSemaphoreGive(_printfSemaphore);
	}
	
	float lux;
	tsl2591ReturnCode_t result=tsl2591GetLux(&lux);
	if (TSL2591_OK==result){
		xEventGroupSetBits(_eventGroupHandleNewData, LIGHT_BIT_3);
		//set data
		_lastMeasurementLux=lux;
	}
	else{
		xSemaphoreTake(_printfSemaphore, portMAX_DELAY);
		printf("Get light measurement failed :: return code %d\n", result);
		xSemaphoreTake(_printfSemaphore, portMAX_DELAY);
	}
}

float LightSensor_getLightMeasurement(){
	return _lastMeasurementLux;
}