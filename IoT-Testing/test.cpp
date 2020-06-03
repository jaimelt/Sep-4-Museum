#include "gtest/gtest.h"
#include "fff.h"
DEFINE_FFF_GLOBALS

extern "C" {
#include "../IoT/IoTApp/src/handlers/rc_servo_handler.h"
#include <rcServo.h>
#include <ATMEGA_FreeRTOS.h>
#include <queue.h>
#include <event_groups.h>
}

FAKE_VALUE_FUNC1(void*, pvPortMalloc, size_t)
FAKE_VALUE_FUNC6(BaseType_t, xTaskCreate, TaskFunction_t, const char*, configSTACK_DEPTH_TYPE, void*, UBaseType_t, TaskHandle_t*)
FAKE_VALUE_FUNC2(BaseType_t, xQueueSemaphoreTake, QueueHandle_t, TickType_t)
FAKE_VALUE_FUNC4(BaseType_t, xQueueGenericSend, QueueHandle_t, const void*, TickType_t, BaseType_t)
FAKE_VALUE_FUNC5(EventBits_t, xEventGroupWaitBits, EventGroupHandle_t, EventBits_t, BaseType_t, BaseType_t, TickType_t)
FAKE_VALUE_FUNC2(EventBits_t, xEventGroupSetBits, EventGroupHandle_t, EventBits_t)
FAKE_VOID_FUNC1(vTaskDelete, TaskHandle_t)
FAKE_VOID_FUNC1(vPortFree, void*)

FAKE_VOID_FUNC(rcServoCreate)

TEST(TestCaseName, TestName) {
	rc_servo_create();
	EXPECT_EQ(1u, rcServoCreate_fake.call_count);
	EXPECT_TRUE(true);
}