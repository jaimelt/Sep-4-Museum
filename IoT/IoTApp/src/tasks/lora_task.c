/*
* lora_task.c
*
* Created: 16/05/2020 11.33.49
*  Author: Marina Ionel
*/

//header
#include "lora_task.h"

//required libraries
#include <stdio.h>
#include <hal_defs.h>

//constants
#include "../constants/global_constants.h"

//task details
#define LORA_TAG "LORA TASK"
#define LORA_TASK_NAME "Lorawan"
#define LORA_TASK_PRIORITY (configMAX_PRIORITIES - 1)
//task handler
static TaskHandle_t _lora_task_handle;

//private fields
static QueueHandle_t _receivingQueue;
static SemaphoreHandle_t _xPrintfSemaphore;

static char _out_buf[100];

#define LED_TASK_PRIORITY 7
#define LORA_CONNECTION_DELAY 5000UL
#define LORA_RESET_DELAY 2
#define LORA_SETUP_DELAY 150

static void _setup_lora_driver()
{
	e_LoRa_return_code_t rc;
	led_slow_blink(led_ST2); // OPTIONAL: Led the green led blink slowly while we are setting up LoRa

	if (_xPrintfSemaphore != NULL)
	{
		xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
		// Factory reset the transceiver
		printf("FactoryReset >%s<\n", lora_driver_map_return_code_to_text(lora_driver_rn2483_factory_reset()));

		// Configure to EU868 LoRaWAN standards
		printf("Configure to EU868 >%s<\n", lora_driver_map_return_code_to_text(lora_driver_configure_to_eu868()));

		// Get the transceivers HW EUI
		rc = lora_driver_get_rn2483_hweui(_out_buf);
		printf("Get HWEUI >%s<: %s\n", lora_driver_map_return_code_to_text(rc), _out_buf);

		// Set the HWEUI as DevEUI in the LoRaWAN software stack in the transceiver
		printf("Set DevEUI: %s >%s<\n", _out_buf, lora_driver_map_return_code_to_text(lora_driver_set_device_identifier(_out_buf)));

		// Set Over The Air Activation parameters to be ready to join the LoRaWAN
		printf("Set OTAA Identity appEUI:%s appKEY:%s devEUI:%s >%s<\n", LORA_appEUI, LORA_appKEY, _out_buf, lora_driver_map_return_code_to_text(lora_driver_set_otaa_identity(LORA_appEUI, LORA_appKEY, _out_buf)));

		// Save all the MAC settings in the transceiver
		printf("Save mac >%s<\n", lora_driver_map_return_code_to_text(lora_driver_save_mac()));

		// Enable Adaptive Data Rate
		printf("Set Adaptive Data Rate: ON >%s<\n", lora_driver_map_return_code_to_text(lora_driver_set_adaptive_data_rate(LoRa_ON)));

		// Set receiver window1 delay to 500 ms - this is needed if down-link messages will be used
		//printf("Set Receiver Delay: %d ms >%s<\n", 500, lora_driver_map_return_code_to_text(lora_driver_set_receive_delay(500)));
		xSemaphoreGive(_xPrintfSemaphore);
	}

	// Join the LoRaWAN
	uint8_t maxJoinTriesLeft = 10;

	do
	{
		rc = lora_driver_join(LoRa_OTAA);

		if (_xPrintfSemaphore != NULL)
		{
			xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
			printf("Join Network TriesLeft:%d >%s<\n", maxJoinTriesLeft, lora_driver_map_return_code_to_text(rc));
			xSemaphoreGive(_xPrintfSemaphore);
		}

		if (rc != LoRa_ACCEPTED)
		{
			// Make the red led pulse to tell something went wrong
			led_long_puls(led_ST1); // OPTIONAL
			// Wait 5 sec and lets try again
			vTaskDelay(pdMS_TO_TICKS(LORA_CONNECTION_DELAY));
		}
		else
		{
			break;
		}
	} while (--maxJoinTriesLeft);

	if (rc == LoRa_ACCEPTED)
	{
		// Connected to LoRaWAN :-)
		// Make the green led steady
		led_led_on(led_ST2); // OPTIONAL
	}
	else
	{
		// Something went wrong
		// Turn off the green led
		led_led_off(led_ST2); // OPTIONAL
		// Make the red led blink fast to tell something went wrong
		led_fast_blink(led_ST1); // OPTIONAL

		// Lets stay here
		while (1)
		{
			taskYIELD();
		}
	}
}

void loraDriver_sent_upload_message(lora_payload_t uplink_lora_payoad)
{
	led_short_puls(led_ST4);

	if (_xPrintfSemaphore != NULL)
	{
		xSemaphoreTake(_xPrintfSemaphore, portMAX_DELAY);
		printf("Upload Message >%s<\n", lora_driver_map_return_code_to_text(
											lora_driver_sent_upload_message(false, &uplink_lora_payoad)));
		xSemaphoreGive(_xPrintfSemaphore);
	}
}

void vALoraTask(void *pvParameters)
{
	lora_driver_reset_rn2483(1);
	vTaskDelay(LORA_RESET_DELAY);
	lora_driver_reset_rn2483(0);
	vTaskDelay(LORA_SETUP_DELAY);

	lora_driver_flush_buffers();

	_setup_lora_driver();

	vTaskDelay(LORA_SETUP_DELAY);

	static lora_payload_t _lorapayload;

	for (;;)
	{
		if (_receivingQueue != NULL)
		{
			if (xQueueReceive(_receivingQueue,
							  &_lorapayload,
							  portMAX_DELAY) == pdPASS)
			{
				loraDriver_sent_upload_message(_lorapayload);
			}
		}
	}
	vTaskDelete(_lora_task_handle);
}

static void _init_hal()
{
	hal_create(LED_TASK_PRIORITY);
	lora_driver_create(LORA_USART, NULL);
}

void lorawan_create(QueueHandle_t pQueue, SemaphoreHandle_t pPrintfSemaphore)
{
	_receivingQueue = pQueue;
	_xPrintfSemaphore = pPrintfSemaphore;
	_lora_task_handle = NULL;

	_init_hal();

	xTaskCreate(
		vALoraTask,						  /* Task function. */
		(const portCHAR *)LORA_TASK_NAME, /* String with name of task. */
		configMINIMAL_STACK_SIZE + 200,	  /* Stack size in words. */
		NULL,							  /* Parameter passed as input of the task */
		LORA_TASK_PRIORITY,				  /* Priority of the task. */
		&_lora_task_handle);			  /* Task handle. */
}