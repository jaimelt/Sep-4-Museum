/*
* light_sensor_task.h
*
* Created: 13/05/2020 18.44.41
*  Author: Marina Ionel
*/
/************************************************************************/
/*                           Light                                       /         */
/************************************************************************/
//The current header file represents all the functionality for Light sensor

#pragma once

//FreeRTOS
#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <semphr.h>
#include <event_groups.h>

//driver
//The Data-sheet for TSL2591 can be found here <a href="https://ams.com/documents/20143/36005/TSL2591_DS000338_6-00.pdf/090eb50d-bb18-5b45-4938-9b3672f86b80">TSL2591 Datasheet - June 2018</a>
#include <tsl2591.h>

//functions
/**
 * \brief Setup the light driver and creates the light task
 * 
 * \param pvEventHandleMeasure		Event group for measuring
 * \param pvEventHandleNewData		Event group for the retrieved new data
 * \param pvPrintfSemaphore			Semaphore to protect printf
 * 
 * \the light drivers are initialized
 * \light task is created
 */
void LightSensor_create(EventGroupHandle_t pvEventHandleMeasure,
						EventGroupHandle_t pvEventHandleNewData,
						SemaphoreHandle_t pvPrintfSemaphore);

/**
 * \brief if the data from the sensor is ready then the light bit in the event group is set
 * 
 * \param pvTsl2591ReturnCode	Return code to check the result
 * 
 */
void LightSensor_callback(tsl2591ReturnCode_t pvTsl2591ReturnCode); //in header for testing

/**
 * \brief the last data that was taken from the sensor
 * 
 * 
 * \return the last measurement value from light sensor
 */
float LightSensor_getLightMeasurement();

//in header for testing
void LightSensor_inLoop(); 