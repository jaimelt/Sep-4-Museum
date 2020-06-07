#include "gtest/gtest.h"
#include "fff.h"
DEFINE_FFF_GLOBALS

/*#include "../IoT/IoTApp/src/tasks/rc_servo_task.h"
#include <rcServo.h>
#include <ATMEGA_FreeRTOS.h>
#include <queue.h>
#include <event_groups.h>*/

extern "C" {
#include "../IoT/IoTApp/src/handlers/sensor_data_package_handler.h"
}

/*FAKE_VALUE_FUNC1(void*, pvPortMalloc, size_t)
FAKE_VALUE_FUNC6(BaseType_t, xTaskCreate, TaskFunction_t, const char*, configSTACK_DEPTH_TYPE, void*, UBaseType_t, TaskHandle_t*)
FAKE_VALUE_FUNC2(BaseType_t, xQueueSemaphoreTake, QueueHandle_t, TickType_t)
FAKE_VALUE_FUNC4(BaseType_t, xQueueGenericSend, QueueHandle_t, const void*, TickType_t, BaseType_t)
FAKE_VALUE_FUNC5(EventBits_t, xEventGroupWaitBits, EventGroupHandle_t, EventBits_t, BaseType_t, BaseType_t, TickType_t)
FAKE_VALUE_FUNC2(EventBits_t, xEventGroupSetBits, EventGroupHandle_t, EventBits_t)
FAKE_VOID_FUNC1(vTaskDelete, TaskHandle_t)
FAKE_VOID_FUNC1(vPortFree, void*)

FAKE_VOID_FUNC(rcServoCreate)*/

TEST(SensorDataPackageHandler, CanCallWithoutCrashing) {
	//act
	//assert
	EXPECT_NO_THROW(SensorDataPackageHandler_getLoraPayload(1));
}

TEST(SensorDataPackageHandler, ReturnsExpectedPayload0)
{
	//arrange
	int8_t arr[LORA_MAX_PAYLOAD_LENGTH] = { 0 };
	//act
	auto _lp = SensorDataPackageHandler_getLoraPayload(1);
	//assert
	EXPECT_EQ(1u, _lp.port_no);
	EXPECT_EQ(8u, _lp.len);
	EXPECT_TRUE(0 == std::memcmp(_lp.bytes, arr, sizeof arr));
}

TEST(SensorDataPackageHandler, ReturnsExpectedPayload1)
{
	//arrange
	int8_t arr1[LORA_MAX_PAYLOAD_LENGTH] = { 0 };

	const int co2 = 734;
	const int ratio = 100;

	const float t1 = 764.3;
	const float h1 = 76.6;
	const float l1 = 87.7;

	const int t = ratio * t1;
	const int h = ratio * h1;
	const int l = ratio * l1;

	SensorDataPackageHandler_setTemperature(t1);
	SensorDataPackageHandler_setCo2ppm(co2);
	SensorDataPackageHandler_setHumidity(h1);
	SensorDataPackageHandler_setLight(l1);

	arr1[0] = h >> 8;
	arr1[1] = h & 0xff;
	arr1[2] = t >> 8;
	arr1[3] = t & 0xff;
	arr1[4] = co2 >> 8;
	arr1[5] = co2 & 0xff;
	arr1[6] = l >> 8;
	arr1[7] = l & 0xff;

	//act
	auto _lp = SensorDataPackageHandler_getLoraPayload(1);

	//assert
	EXPECT_EQ(1u, _lp.port_no);
	EXPECT_EQ(8u, _lp.len);
	EXPECT_TRUE(0 == std::memcmp(_lp.bytes, arr1, sizeof _lp.bytes));
}