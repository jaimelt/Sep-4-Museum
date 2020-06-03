
/*
* temperature_humidity_task.h
*
* Created: 26/05/2020 12.39.34
* Author: Fabian Bernhardt
*/
/************************************************************************/
/*                      Temperature & Humidity				             /         */
/************************************************************************/
/*
 * The current header file represents all the tasks for 
 * Temperature/Humidity sensor create, callback and get 
*/
#pragma once

//FreeRTOS
#include <ATMEGA_FreeRTOS.h>
#include <semphr.h>
#include <event_groups.h>
#include <task.h>

//drivers
//The user manual for HIH8120 can be found here <a href="https://sensing.honeywell.com/i2c-comms-humidicon-tn-009061-2-en-final-07jun12.pdf">I2C Communication with the Honeywell HumidIcon Digital Humidity/Temperature Sensors (Version: 1.0)</a>
#include <hih8120.h>

//functions
/**
 * \brief Setup the Temperature and Humidity driver 
 *	and creates a task to get new measurement from temperature and humidity sensor
 * 
 * \param pvEventHandleMeasure		Event group for measuring
 * \param pvEventHandleNewData		Event group for the retrieved new data
 * \param pvPrintfSemaphore			Semaphore to protect printf
 * 
 * \setting up the temperature/Humidity driver
 * \Create a task to get temperature and humidity  
 * \
 */
void temperatureHumiditySensor_create(EventGroupHandle_t pvEventHandleMeasure,
                                      EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pvPrintfSemaphore);

/*
 * \brief in header for testing
 * 
 */
void temperatureHumiditySensor_inLoop();

/**
 * \brief the last data that was taken from the sensor
 * 
 * 
 * \return the last measurement(Humidity) value from Temperature/Humidity sensor
 */
float temperatureHumiditySensor_getHumidity();

/**
 * \brief the last data that was taken from the sensor
 * 
 * 
 * \return the last measurement(Temperature) value from Temperature/Humidity sensor
 */
float temperatureHumiditySensor_getTemperature();