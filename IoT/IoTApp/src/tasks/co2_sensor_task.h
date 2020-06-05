/**
 * \file
 * \brief Co2 sensor implementation using tasks
 * \note Depends on mh_z19.h
 *
 * \authors Justinas Jancys, Fabian Bernhardt
 * \version 1.0.0
 *
 * \date 16/05/2020 11.15.11
 *
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

/**
 * \brief Creates and initializes the co2 sensor (Creates the MH-Z19 driver; creates the co2 sensor task)
 *
 * \param[in] pvEventHandleMeasure		Event group used for triggering the start of a measurement; passed by the controller task
 * \param[in] pvEventHandleNewData		Event group used for signalizing that a measurement is completed and the data can be retrieved
 * \param[in] pPrintfSemaphore			Mutex for protecting printf function call
 *
 */
void co2Sensor_create(EventGroupHandle_t pvEventHandleMeasure,
	EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pPrintfSemaphore);

/**
 * \brief The callback function for the MH-Z19 driver measuring.
 * It retrieves the data (co2 value in ppm) and signalizes the finished measurement. 
 *
 * \param[in] ppm value set by the MH-Z19 driver representing the measurement
 */
void co2Sensor_callback(uint16_t ppm); //in header for testing

//void co2Sensor_inLoop(); //in header for testing

/**
 * \brief Gets the last measurement in ppm
 *
 * \returns last measured value from CO2 sensor
 */
uint16_t co2sensor_getCo2();