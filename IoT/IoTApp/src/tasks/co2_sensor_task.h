/*
 * co2_sensor_task.h
 *
 * Created: 16/05/2020 11.15.11
 *  Author: Marina Ionel
 */

#include <stdint.h>
#include <ATMEGA_FreeRTOS.h>
#include <semphr.h>
#include <event_groups.h>

void vACo2Task(void *pvParameters);
void co2Sensor_create(EventGroupHandle_t pvEventHandleMeasure, EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pPrintfSemaphore);
void co2Sensor_callback(uint16_t ppm);
uint16_t co2sensor_getCo2();