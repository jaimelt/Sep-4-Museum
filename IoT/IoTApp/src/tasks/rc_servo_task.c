
/*
* rc_servo_task.c
*
* Created: 01/06/2020 11.03.08
*  Author: Marina Ionel
*/

//header
#include "rc_servo_task.h"

//required libraries
#include <stdbool.h>
#include <rcServo.h>

//constants
#define RC_SERVO_PORT_NO 0
#define RC_SERVO_UP_POSITION 100
#define RC_SERVO_DOWN_POSITION -100

#define RC_SERVO_TASK_NAME "rcservo"
#define RC_SERVO_TASK_PRIORITY (configMAX_PRIORITIES - 3)

static TaskHandle_t _rc_servo_task_handle;
static QueueHandle_t _rc_servo_queue;

//private variables
static bool _IsUp;

void rcServoTask(void *pvParameters)
{
	for (;;)
	{
		static rcServo_Action_t _tmp;
		if (_rc_servo_queue != NULL)
		{
			if (xQueueReceive(_rc_servo_queue,
							  &_tmp,
							  portMAX_DELAY) == pdPASS)
			{
				switch (_tmp)
				{
				case UP:
					if (_IsUp == false)
					{
						rcServoSet(RC_SERVO_PORT_NO, RC_SERVO_UP_POSITION);
						_IsUp = true;
					}
					break;
				case DOWN:
					if (_IsUp == true)
					{
						rcServoSet(RC_SERVO_PORT_NO, RC_SERVO_DOWN_POSITION);
						_IsUp = false;
					}
					break;
				default:
					break;
				}
			}
		}
	}
	vTaskDelete(_rc_servo_task_handle);
}

static void inline _setup_rc_servo()
{
	// Create and initialize the rc-servo driver
	rcServoCreate();

	//reset rc-servo position to 0
	rcServoSet(RC_SERVO_PORT_NO, 0);
}

void rcServoTask_create(QueueHandle_t queue)
{
	_IsUp = false;
	_rc_servo_queue = queue;
	_rc_servo_task_handle = NULL;

	_setup_rc_servo();

	xTaskCreate(rcServoTask, RC_SERVO_TASK_NAME, configMINIMAL_STACK_SIZE, NULL, RC_SERVO_TASK_PRIORITY, &_rc_servo_task_handle);
}