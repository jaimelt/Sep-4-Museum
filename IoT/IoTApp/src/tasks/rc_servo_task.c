
/*
* rc_servo_task.c
*
* Created: 01/06/2020 11.03.08
*  Author: Marina Ionel
*/
#include "rc_servo_task.h"
#include <ATMEGA_FreeRTOS.h>
#include <task.h>
#include <rcServo.h>

static TaskHandle_t _rcServoTaskHandle;

void rc_servo_Task(void *pvParameters)
{
	for (;;)
	{
		rcServoSet(0, 100);
		vTaskDelay(5000 / portTICK_PERIOD_MS);
		rcServoSet(0, -100);
		vTaskDelay(5000 / portTICK_PERIOD_MS);
	}
	vTaskDelete(_rcServoTaskHandle);
}

void rc_servo_create()
{
	rcServoCreate();

	_rcServoTaskHandle = NULL;

	xTaskCreate(
		rc_servo_Task,				 /* Task function. */
		(const portCHAR *)"rcservo", /* String with name of task. */
		configMINIMAL_STACK_SIZE,	 /* Stack size in words. */
		NULL,						 /* Parameter passed as input of the task */
		(configMAX_PRIORITIES - 3),	 /* Priority of the task. */
		&_rcServoTaskHandle);
}