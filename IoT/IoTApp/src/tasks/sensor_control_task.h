/*
 * sensor_control_task.h
 *
 * Created: 16/05/2020 10.20.54
 *  Author: Marina Ionel
 */

#pragma once

//functions
/**
 * \First step when the program runs
 * \brief Creates all the tasks(sensors): LoRa, Co2, Temperature/Humidity, Light and RC Servo sensors 
 * \
 */
void sensorControl_create();