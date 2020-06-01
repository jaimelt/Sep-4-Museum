/*
 * co2_sensor_task.h
 *
 * Created: 16/05/2020 11.15.11
 *  Author: Justinas Jancys, Fabian Bernhardt
 */
#pragma once
//FreeRTOS
#include <stdint.h>
#include <ATMEGA_FreeRTOS.h>
#include <semphr.h>
#include <event_groups.h>
#include <task.h>
//drivers
#include <mh_z19.h>

//functions

void co2Sensor_create(EventGroupHandle_t pvEventHandleMeasure,
                      EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pvPrintfSemaphore);
void co2Sensor_callback(uint16_t ppm); //in header for testing
void co2Sensor_inLoop(); //in header for testing
uint16_t co2sensor_getCo2();