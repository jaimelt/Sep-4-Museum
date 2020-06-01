/*
* light_sensor_task.h
*
* Created: 13/05/2020 18.44.41
*  Author: Marina Ionel
*/
#pragma once

//freertos
#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <semphr.h>
#include <event_groups.h>
//driver
#include <tsl2591.h>

void LightSensor_create(EventGroupHandle_t pvEventHandleMeasure,
						EventGroupHandle_t pvEventHandleNewData,
						SemaphoreHandle_t pvPrintfSemaphore);

void LightSensor_callback(tsl2591ReturnCode_t pvTsl2591ReturnCode); //in header for testing

float LightSensor_getLightMeasurement();

void LightSensor_inLoop(); //in header for testing