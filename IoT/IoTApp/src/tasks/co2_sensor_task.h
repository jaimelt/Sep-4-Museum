/*
 * co2_sensor_task.h
 *
 * Created: 16/05/2020 11.15.11
 *  Author: Justinas Jancys, Fabian Bernhardt
 */

/************************************************************************/
/*                           CO2		                                 /         */
/************************************************************************/
/*
The current header file represents all the tasks for CO2 sensor
*/
#pragma once


//FreeRTOS
#include <stdint.h>
#include <ATMEGA_FreeRTOS.h>
#include <semphr.h>
#include <event_groups.h>
#include <task.h>


//drivers
//The simple user manual for MH-Z19 can be found here <a href="https://www.winsen-sensor.com/d/files/PDF/Infrared%20Gas%20Sensor/NDIR%20CO2%20SENSOR/MH-Z19%20CO2%20Ver1.0.pdf">Intelligent Infrared CO2 Module
//(Model: MH-Z19) User's Manual (Version: 1.0)</a>
#include <mh_z19.h>



//Functions
/**
 * \brief Creates and setup the driver by calling mh_z19_create() method from co2 driver
 * \ 
 * \param pvEventHandleMeasure 
 * \param pvEventHandleNewData 
 * \param pvPrintfSemaphore
 * \
 * \ the setup driver for co2 must be implemented
 * \in xTaskCreate must be implemented the co2 task
 * \
 * \co2sensor_inLoop starts measuring 
 * \
 */
void co2Sensor_create(EventGroupHandle_t pvEventHandleMeasure,
                      EventGroupHandle_t pvEventHandleNewData, SemaphoreHandle_t pvPrintfSemaphore);

/**
 * \brief set the bit to true to signalize that the measurement was completed
 * 
 * \param ppm will represent an 16 bits witch will represent the CO2 measure 
 * 
 * \ void
 */
void co2Sensor_callback(uint16_t ppm); //in header for testing

void co2Sensor_inLoop(); //in header for testing


/**
 * \brief Gets the last data that was taken from the sensor
 * 
 * \return the last measurement value from CO2 sensor
 */
uint16_t co2sensor_getCo2();