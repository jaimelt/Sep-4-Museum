/*
* lora_sensor_task.h
*
* Created: 16/05/2020 11.34.06
*  Author: Marina Ionel
*/
#pragma once

#include <ATMEGA_FreeRTOS.h>
#include <queue.h>
#include <semphr.h>
#include <task.h>

void loraSensor_create(QueueHandle_t pQueue, SemaphoreHandle_t pPrintfSemaphore);