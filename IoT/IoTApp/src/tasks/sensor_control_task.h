/**
 * \file
 * \brief Sensor control task implementation
 *
 * \author Marina Ionel
 * \version 1.0.0
 * \created 16/05/2020 10.20.54
 */

#pragma once

 //functions
 /**
  * \brief Creates the sensor control task, the rcServo handler and all the dependent tasks: LoRa, Co2, Temperature/Humidity, Light
  *
  */
void sensorControl_create();