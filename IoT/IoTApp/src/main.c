#include <stdio.h>
#include <avr/io.h>

#include <ATMEGA_FreeRTOS.h>

#include <FreeRTOSTraceDriver.h>
#include <stdio_driver.h>
#include <serial.h>
#include "tasks/sensor_control_task.h"

int main(void)
{
	trace_init();
	stdioCreate(ser_USART0);

	printf("Program Started!\n");

	sensorControl_create();

	vTaskStartScheduler();

	while (1){}
}