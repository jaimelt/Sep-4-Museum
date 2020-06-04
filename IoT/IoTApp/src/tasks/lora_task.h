/**
 * \file
 * \brief Lorawan task implementation
 *
 * \note depends on lora_driver.h and iled.h
 *
 * \author Marina Ionel
 * \version 1.0.0
 * \created 16/05/2020 11.34.06
 */
#pragma once

//FreeRTOS
#include <ATMEGA_FreeRTOS.h>
#include <queue.h>
#include <semphr.h>
#include <task.h>

//drivers
#include <lora_driver.h>
#include <iled.h>

/**
 * \brief Creates the lora task
 * Creates and sets up the lora driver
 * 
 * \param pQueue				The queue for receiving lorapayload_t that needs to be sent via lorawan network
 * \param pPrintfSemaphore		Mutex for protecting printf function call
 * 
 */
void lorawan_create(QueueHandle_t pQueue, SemaphoreHandle_t pPrintfSemaphore);