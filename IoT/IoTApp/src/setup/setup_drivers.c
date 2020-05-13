/*
* setup_drivers.c
*
* Created: 13/05/2020 18.58.16
*  Author: Marina Ionel
*/

#include <stdio.h>
#include <stdlib.h>

#include <ATMEGA_FreeRTOS.h>
#include <stdio_driver.h>
#include <hih8120.h>
#include <tsl2591.h>

#include "..\tasks\light_sensor_task.h"

void setup_driver(){
	//temperature/humidity sensor
	if(HIH8120_OK != hih8120Create()) {
		printf("FAILED INITIALIZATION::Temperature sensor\n");
		exit(1);
	}
	
	//co2 sensor
	//TODO
	
	//light sensor
	//create
	int result = tsl2591Create(LightSensor_callback);
	if(result != TSL2591_OK) {
		printf("FAILED INITIALIZATION::Light sensor: %d\n", result);
		exit(1);
	}
	
	//enable
	result = tsl2591Enable();
	if(result != TSL2591_OK) {
		printf("FAILED ENABLING::Light sensor %d\n", result);
		exit(1);
	}
}