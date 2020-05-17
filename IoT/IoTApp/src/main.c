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
#include <FreeRTOSConfig.h>
#include <avr/interrupt.h>

#include "tasks/sensor_control_task.h"
#include "setup/setup_drivers.h"

// Needed for LoRaWAN
#include <lora_driver.h>

SemaphoreHandle_t _xPrintfSemaphore;

void initialiseSystem()
{
	trace_init();
	stdioCreate(ser_USART0);
	//enable interrupt
	sei();
}

/*-----------------------------------------------------------*/
int main(void)
{
	initialiseSystem();
	setup_drivers();
	
	_xPrintfSemaphore=xSemaphoreCreateMutex();
	if (_xPrintfSemaphore!=NULL){
		xSemaphoreGive(_xPrintfSemaphore);
	}
	xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
	printf("Program Started!!\n");
	xSemaphoreGive(_xPrintfSemaphore);
	
	sensorControl_create(_xPrintfSemaphore);
	
	vTaskStartScheduler(); // Initialise and run the freeRTOS scheduler. Execution should never return from here.

	/* Replace with your application code */
	while (1)
	{
	}
}