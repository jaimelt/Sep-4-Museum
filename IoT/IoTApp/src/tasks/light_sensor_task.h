/**
 * \file
 * \brief Light sensor implementation using tasks
 * \note Depends on tsl2591.h
 *
 * \author Marina Ionel
 * \version 1.0.0
 *
 * \date 13/05/2020 18.44.41
 */

#pragma once

 //FreeRTOS
#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <semphr.h>
#include <event_groups.h>

#include <tsl2591.h>

/**
 * \brief Creates and initializes the light sensor (Creates and enables the tsl2591 driver; creates the light sensor task)
 *
 * \param[in] pvEventHandleMeasure		Event group used for triggering the start of a measurement; passed by the controller task
 * \param[in] pvEventHandleNewData		Event group used for signalizing that a measurement is completed and the data can be retrieved
 * \param[in] pPrintfSemaphore			Mutex for protecting printf function call
 *
 */
void LightSensor_create(EventGroupHandle_t pvEventHandleMeasure,
	EventGroupHandle_t pvEventHandleNewData,
	SemaphoreHandle_t pvPrintfSemaphore);

 /**
  * \brief The callback function for the tsl2591 driver measuring.
  * It retrieves the data (light value in lux) and signalizes the finished measurement.
  *
  * \param[in] pvTsl2591ReturnCode return code indicating the status of the finished measuring process
  */
void LightSensor_callback(tsl2591ReturnCode_t pvTsl2591ReturnCode); //in header for testing


/**
 * \brief Gets the last measurement in lux
 *
 * \returns last measured value from light sensor
 */
float LightSensor_getLightMeasurement();

//void LightSensor_inLoop(); //in header for testing