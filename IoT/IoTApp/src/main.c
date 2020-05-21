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
#include <avr/interrupt.h>
#include <lora_driver.h>

#include "tasks/sensor_control_task.h"
#include "setup/setup_drivers.h"

SemaphoreHandle_t _xPrintfSemaphore =NULL;

int main(void)
{
	trace_init();
	stdioCreate(ser_USART0);	

	if (_xPrintfSemaphore==NULL){
		_xPrintfSemaphore = xSemaphoreCreateMutex();
		if (_xPrintfSemaphore!=NULL) xSemaphoreGive(_xPrintfSemaphore);
	}
	
	printf("Program Started!!\n");
	
	sensorControl_create(_xPrintfSemaphore);

	vTaskStartScheduler();

	while (1)
	{
	}
}