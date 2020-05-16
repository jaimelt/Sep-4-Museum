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

#include "../constants/global_constants.h"

#include "../tasks/light_sensor_task.h"
#include "../tasks/co2_sensor_task.h"

#define SETUP_DRIVERS_TAG "SETUP DRIVERS"

void setup_light_driver(){
	//create
	int result = tsl2591Create(LightSensor_callback);
	if(result != TSL2591_OK) {
		printf("%s :: FAILED DRIVER INITIALIZATION :: Light :: result code %d\n", SETUP_DRIVERS_TAG, result);
		exit(EXIT_FAILURE);
	}
	
	//enable
	result = tsl2591Enable();
	if(result != TSL2591_OK) {
		printf("%s :: FAILED DRIVER ENABLING :: Light :: result code %d\n",SETUP_DRIVERS_TAG, result);
		exit(EXIT_FAILURE);
	}
}

void setup_temperature_humidity_driver(){
	if(HIH8120_OK != hih8120Create()) {
		printf("%s :: FAILED INITIALIZATION :: Temperature/humidity sensor\n", SETUP_DRIVERS_TAG);
		exit(EXIT_FAILURE);
	}
}

void setup_co2_driver(){
	//mh_z19_create(ser_USART3, co2Sensor_callback)
}