/*
* main.c
* Author : IHA
*
* Example main file including LoRaWAN setup
* Just for inspiration :)
*/

#include <stdio.h>
#include <avr/io.h>
#include <avr/sfr_defs.h>

#include <hal_defs.h>
#include <ihal.h>

#include <ATMEGA_FreeRTOS.h>
#include <semphr.h>

#include <FreeRTOSTraceDriver.h>
#include <stdio_driver.h>
#include <serial.h>

#include "tasks/sensor_control_task.h"

// Needed for LoRaWAN
#include <lora_driver.h>

SemaphoreHandle_t _xPrintfSemaphore;

void initialiseSystem()
{
	// Set output ports for leds used in the example
	DDRA |= _BV(DDA0) | _BV(DDA7);
	// Initialise the trace-driver to be used together with the R2R-Network
	trace_init();
	// Make it possible to use stdio on COM port 0 (USB) on Arduino board - Setting 57600,8,N,1
	stdioCreate(ser_USART0);
}

/*-----------------------------------------------------------*/
int main(void)
{
	_xPrintfSemaphore=xSemaphoreCreateMutex();
	if (_xPrintfSemaphore!=NULL){
		xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
		printf("Program Started!!\n");
		xSemaphoreGive(_xPrintfSemaphore);
	}
	
	initialiseSystem(); // Must be done as the very first thing!!
	
	sensorControl_create(_xPrintfSemaphore);
	
	vTaskStartScheduler(); // Initialise and run the freeRTOS scheduler. Execution should never return from here.

	/* Replace with your application code */
	while (1)
	{
	}
}

