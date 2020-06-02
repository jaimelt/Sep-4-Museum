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
 * \brief 
 * 
 * \param pQueue align all the data into a queue 
 * \param pPrintfSemaphore 
 * 
 * \iniatilaze the LoRa driver by _setup_lora_driver if everything is OK,
 * a new task will run to sent the data
 */
void loraSensor_create(QueueHandle_t pQueue, SemaphoreHandle_t pPrintfSemaphore);