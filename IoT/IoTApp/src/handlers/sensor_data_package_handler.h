/**
 * \file
 * \brief Sensor data package handler implementation
 *
 * \author Marina Ionel
 * \version 1.0.0
 * \date 16/05/2020 12.03.15
 */

#pragma once

#include <stdint.h>
#include <lora_driver.h>

 /**
  * \brief Set the co2 (in ppm) value
  * \param[in] ppm the ppm value to set
  */
void SensorDataPackageHandler_setCo2ppm(uint16_t ppm);
/**
 * \brief Set the humidity value
 * \param[in] humidity Humidity value to set
 */
void SensorDataPackageHandler_setHumidity(float humidity);
/**
 * \brief Set the temperature value
 * \param[in] temperature The temperature value to set
 */
void SensorDataPackageHandler_setTemperature(float temperature);
/**
 * \brief Set the light (in lux) value
 * \param[in] lux The lux value to set
 */
void SensorDataPackageHandler_setLight(float lux);

/**
 * \brief Get the uplink lora payload including all sensor values converted to bytes
 *
 * \param portNo Port number of the uplink lora payload
 * \return lora_payload_t the uplink lora payload for sending to lorawan network
 */
lora_payload_t SensorDataPackageHandler_getLoraPayload(uint8_t portNo);