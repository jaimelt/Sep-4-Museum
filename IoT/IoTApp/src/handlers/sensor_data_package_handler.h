/*
 * sensor_data_package_handler.h
 *
 * Created: 16/05/2020 12.03.15
 *  Author: Marina Ionel
 */
/************************************************************************/
/*                          Sensor data	                                 /         */
/************************************************************************/
#pragma once
#include <stdint.h>
#include <lora_driver.h>

//functions
void SensorDataPackageHandler_setCo2ppm(uint16_t ppm);

void SensorDataPackageHandler_setHumidity(float humidity);

void SensorDataPackageHandler_setTemperature(float temperature);

void SensorDataPackageHandler_setLight(float lux);

/**
 * \brief Set all the measurements in the payload 
 * 
 * \param portNo	Port number
 *
 * The data from Humidity, Temperature, CO2 and Light sensors will be set in an array _uplink_payload
 * Each value form the sensor has 2 bytes
 * \return _uplink_payload
 */
lora_payload_t SensorDataPackageHandler_getLoraPayload(uint8_t portNo);