/**
 * \file
 * \brief RC-servo implementation
 * \note Depends on rcServo.h
 * 
 * \author Marina Ionel
 * \version 1.0.0
 * 
 * \created 01/06/2020 11.03.23
 * 
 */
#pragma once

/**
 * \brief Creates the rcServo driver.
 */
void rc_servo_create();

/**
 * \brief Move up (position 100) if the rc-servo motor is down (position -100).
 * This action should lift up the window shutters
 * 
 */
void rcServo_Up();

/**
 * \brief Move down (position -100) if the rc-servo motor is up (position 100).
 * This action should descent the window shutters
 *
 */
void rcServo_Down();