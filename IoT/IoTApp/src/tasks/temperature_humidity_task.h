/**
 * \file
 * \brief Temperature/humidity sensor implementation using tasks
 *
 * \note Depends on hih8120.h
 *
 * \author Fabian Bernhardt
 * \version 1.0.0
 * \created 26/05/2020 12.39.34
 */
#pragma once

 //FreeRTOS
#include <ATMEGA_FreeRTOS.h>
#include <semphr.h>
#include <event_groups.h>
#include <task.h>

#include <hih8120.h>

/**
 * \brief Creates and initializes the temperature and humidity sensor
 * (Creates the hih8120 driver; creates the temperature/humidity sensor task)
 *
 * \param[in] pvEventHandleMeasure		Event group used for triggering the start of a measurement; passed by the controller task
 * \param[in] pvEventHandleNewData		Event group used for signalizing that a measurement is completed and the data can be retrieved
 * \param[in] pPrintfSemaphore			Mutex for protecting printf function call
 *
 */
void temperatureHumiditySensor_create(EventGroupHandle_t pvEventHandleMeasure,
	EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pPrintfSemaphore);

//void temperatureHumiditySensor_inLoop(); //in header for testing

/**
 * \brief Gets the last measurement of humidity
 *
 * \returns last measured humidity value from temperature/humidity sensor
 */
float temperatureHumiditySensor_getHumidity();

/**
 * \brief Gets the last measurement of temperature
 *
 * \returns last measured temperature value from temperature/humidity sensor
 */
float temperatureHumiditySensor_getTemperature();