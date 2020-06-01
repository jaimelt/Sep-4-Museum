
/*
* temperature_humidity_task.h
*
* Created: 26/05/2020 12.39.34
* Author: Fabian Bernhardt
*/
#pragma once

//FreeRTOS
#include <ATMEGA_FreeRTOS.h>
#include <semphr.h>
#include <event_groups.h>
#include <task.h>

//drivers
#include <hih8120.h>

//functions
void temperatureHumiditySensor_create(EventGroupHandle_t pvEventHandleMeasure,
                                      EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pvPrintfSemaphore);

float temperatureHumiditySensor_getHumidity();

float temperatureHumiditySensor_getTemperature();