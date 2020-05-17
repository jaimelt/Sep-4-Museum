/*
* setup_drivers.c
*
* Created: 13/05/2020 18.58.16
*  Author: Marina Ionel
*/

#include <stdlib.h>
#include <stdio.h>
#include <avr/io.h>
#include <avr/sfr_defs.h>

#include <hal_defs.h>
#include <ihal.h>

#include <ATMEGA_FreeRTOS.h>

#include <stdio_driver.h>
#include <hih8120.h>
#include <tsl2591.h>
#include <lora_driver.h>
#include <serial.h>
#include <stdio_driver.h>
#include <iled.h>
#include <mh_z19.h>

#include "../constants/global_constants.h"

#include "../tasks/light_sensor_task.h"
#include "../tasks/co2_sensor_task.h"

#define SETUP_DRIVERS_TAG "SETUP DRIVERS"

static SemaphoreHandle_t _xPrintfSemaphore;

static void _setup_light_driver()
{
	//create
	int result = tsl2591Create(LightSensor_callback);
	if (result != TSL2591_OK)
	{
		if (_xPrintfSemaphore != NULL)
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("%s :: FAILED DRIVER INITIALIZATION :: Light :: result code %d\n", SETUP_DRIVERS_TAG, result);
			xSemaphoreGive(_xPrintfSemaphore);
		}
		exit(EXIT_FAILURE);
	}
	else
	{
		if (_xPrintfSemaphore != NULL)
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("%s :: SUCCESSFULL DRIVER INITIALIZATION :: Light\n", SETUP_DRIVERS_TAG);
			xSemaphoreGive(_xPrintfSemaphore);
		}
	}

	//enable
	result = tsl2591Enable();
	if (result != TSL2591_OK)
	{
		if (_xPrintfSemaphore != NULL)
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("%s :: FAILED DRIVER ENABLING :: Light :: result code %d\n", SETUP_DRIVERS_TAG, result);
			xSemaphoreGive(_xPrintfSemaphore);
		}
		exit(EXIT_FAILURE);
	}
	else
	{
		if (_xPrintfSemaphore != NULL)
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("%s :: SUCCESSFULL DRIVER ENABLING :: Light\n", SETUP_DRIVERS_TAG);
			xSemaphoreGive(_xPrintfSemaphore);
		}
	}
}

static void _setup_temperature_humidity_driver()
{
	if (HIH8120_OK != hih8120Create())
	{
		if (_xPrintfSemaphore != NULL)
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("%s :: FAILED DRIVER INITIALIZATION :: Temperature/humidity\n", SETUP_DRIVERS_TAG);
			xSemaphoreGive(_xPrintfSemaphore);
		}
		exit(EXIT_FAILURE);
	}
	else
	{
		if (_xPrintfSemaphore != NULL)
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("%s :: SUCCESSFULL DRIVER INITIALIZATION :: Temperature/humidity\n", SETUP_DRIVERS_TAG);
			xSemaphoreGive(_xPrintfSemaphore);
		}
	}
}

static void _setup_co2_driver()
{
	mh_z19_create(ser_USART3, co2Sensor_callback);
	if (_xPrintfSemaphore != NULL)
	{
		xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
		printf("%s :: SUCCESSFULL DRIVER INITIALIZATION :: co2\n", SETUP_DRIVERS_TAG);
		xSemaphoreGive(_xPrintfSemaphore);
	}
}

void setup_drivers(SemaphoreHandle_t pPrintfSemaphore)
{
	_xPrintfSemaphore = pPrintfSemaphore;

	_setup_co2_driver();
	_setup_light_driver();
	_setup_temperature_humidity_driver();
}