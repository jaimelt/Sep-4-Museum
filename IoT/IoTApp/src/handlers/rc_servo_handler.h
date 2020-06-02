
/*
* rc_servo_task.h
*
* Created: 01/06/2020 11.03.23
*  Author: Marina Ionel
*/

/************************************************************************/
/*                          RC Servo	                                 /         */
/************************************************************************/
/*
The current header file represents all the tasks for RC Servo
*/
#pragma once

//functions
/**
 * \brief Creates and initialize the driver.
 * 
 * 
 * \return void
 */
void rc_servo_create();

/**
 * \brief Make a rotation if the servo position is up
 * 
 * 
 * \
 */
void rcServo_Up();

/**
 * \brief  Make a rotation if the servo position is down
 *
 * 
 * \
 */
void rcServo_Down();