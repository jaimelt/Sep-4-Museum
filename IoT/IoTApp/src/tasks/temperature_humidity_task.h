
/*
* temperature_humidity_task.h
*
* Created: 26/05/2020 12.39.34
*  Author:
*/
#pragma once
#include <ATMEGA_FreeRTOS.h>

void temperatureHumiditySensor_create(EventGroupHandle_t pvEventHandleMeasure, EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pPrintfSemaphore);
float temperatureHumiditySensor_getHumidity();
float temperatureHumiditySensor_getTemperature();