
/*
* rc_servo_task.c
*
* Created: 01/06/2020 11.03.08
*  Author: Marina Ionel
*/

//header
#include "rc_servo_handler.h"

//freertos
#include "ATMEGA_FreeRTOS.h"

//required libraries
#include <stdbool.h>
#include <rcServo.h>

//constants
#define RC_SERVO_PORT_NO 0
#define RC_SERVO_UP_POSITION 100
#define RC_SERVO_DOWN_POSITION -100

//private variables
static bool _IsUp;

void rcServo_Up()
{
	if (_IsUp == false)
	{
		rcServoSet(RC_SERVO_PORT_NO, RC_SERVO_UP_POSITION);
		_IsUp = true;
	}
}

void rcServo_Down()
{
	if (_IsUp == true)
	{
		rcServoSet(RC_SERVO_PORT_NO, RC_SERVO_DOWN_POSITION);
		_IsUp = false;
	}
}

void rc_servo_create()
{
	_IsUp = false;
	
	// Create and initialize the rc-servo driver
	rcServoCreate();
	
	//reset rc-servo position to 0
	rcServoSet(RC_SERVO_PORT_NO, 0);
}