/*
* co2_sensor_task.c
*
* Created: 16/05/2020 11.14.42
*  Author:
*/

#include <stdint.h>

#define CO2_SENSOR_TAG "CO2 SENSOR TASK"

static uint16_t _lastCo2Measurement = 0;

void co2Sensor_callback(uint16_t ppm)
{
	_lastCo2Measurement = ppm;
}

uint16_t co2sensor_getCo2(){}