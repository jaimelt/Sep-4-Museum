/**
* \file
* \brief RC-servo task implementation
* \note Depends on rcServo.h
*
* \author Marina Ionel
* \version 1.0.0
*
* \date 01/06/2020 11.03.23
*
*/
#pragma once
#include <ATMEGA_FreeRTOS.h>
#include <queue.h>
#include <task.h>

typedef enum Action
{
	UP,
	DOWN
} rcServo_Action_t;

/**
* \brief Creates the rcServo driver.
* \param[in] queue The receiving queue for getting what action should be performed by rc-servo motor
*/
void rcServoTask_create(QueueHandle_t queue);