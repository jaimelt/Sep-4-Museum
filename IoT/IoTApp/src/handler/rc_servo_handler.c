
/*
* rc_servo_task.c
*
* Created: 01/06/2020 11.03.08
*  Author: Marina Ionel
*/
#include <stdbool.h>
#include <rcServo.h>

#include "rc_servo_handler.h"
static bool IsUp;

void rcServo_Up()
{
	if (IsUp == false)
	{
		rcServoSet(0, 100);
		IsUp = true;
	}
}

void rcServo_Down()
{
	if (IsUp == true)
	{
		rcServoSet(0, -100);
		IsUp = false;
	}
}

void rc_servo_create()
{
	IsUp = false;
	// Create and initialize the rc-servo driver
	rcServoCreate();
}