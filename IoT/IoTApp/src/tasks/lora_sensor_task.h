/*
* lora_sensor_task.h
*
* Created: 16/05/2020 11.34.06
*  Author:
*/
#pragma once
void loraSensor_create(QueueHandle_t pQueue, SemaphoreHandle_t pPrintfSemaphore);
void vALoraTask(void *pvParameters);