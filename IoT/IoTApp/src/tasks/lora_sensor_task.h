/*
* lora_sensor_task.h
*
* Created: 16/05/2020 11.34.06
*  Author: Marina Ionel
*/
/************************************************************************/
/*                           LoRa                                        /         */
/************************************************************************/
#pragma once

//FreeRTOS
#include <ATMEGA_FreeRTOS.h>
#include <queue.h>
#include <semphr.h>
#include <task.h>

//functions

/**
 * \brief Initializes the Lora drivers and creates the Lora task
 * 
 * \param pQueue				To detach sensor_control task from Lora task and to pass the data
 * \param pPrintfSemaphore		Semaphore to protect printf
 * 
 * \initialize the LoRa driver by _setup_lora_driver. If everything is OK,
 * a new task will run to send the data
 */
void loraSensor_create(QueueHandle_t pQueue, SemaphoreHandle_t pPrintfSemaphore);