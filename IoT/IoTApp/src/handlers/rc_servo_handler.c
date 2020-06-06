
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

//private variables
static bool _IsUp;

void rcServo_Up()
{
	if (_IsUp == false)
	{
		rcServoSet(0, 100);
		_IsUp = true;
	}
}

void rcServo_Down()
{
	if (_IsUp == true)
	{
		rcServoSet(0, -100);
		_IsUp = false;
	}
}

void rc_servo_create()
{
	_IsUp = false;
	// Create and initialize the rc-servo driver
	rcServoCreate();
	rcServoSet(0, 0);
}