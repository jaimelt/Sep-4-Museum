/*
* sensor_data_package_handler.c
*
* Created: 16/05/2020 12.03.06
*  Author: Marina Ionel
*/

#define RATIO 100

static uint16_t _co2_ppm = 0;
static float _light = 0;
static float _humidity = 0;
static float _temperature = 0;

void SensorDataPackageHandler_setCo2ppm(uint16_t ppm)
{
	_co2_ppm = ppm;
}

void SensorDataPackageHandler_setHumidity(float humidity)
{
	_humidity = humidity;
}

void SensorDataPackageHandler_setTemperature(float temperature)
{
	_temperature = temperature;
}

void SensorDataPackageHandler_setLight(float lux)
{
	_light = lux;
}

lora_payload_t SensorDataPackageHandler_getLoraPayload(uint8_t portNo)
{
	lora_payload_t _uplink_payload = {.len = 8, .bytes = {0}, .port_no = portNo};

	uint16_t _light_as_int = _light * RATIO;
	uint16_t _humidity_as_int = _humidity * RATIO;
	uint16_t _temperature_as_int = _temperature * RATIO;

	_uplink_payload.bytes[0] = _humidity_as_int >> 8;
	_uplink_payload.bytes[1] = _humidity_as_int & 0xFF;
	_uplink_payload.bytes[2] = _temperature_as_int >> 8;
	_uplink_payload.bytes[3] = _temperature_as_int & 0xFF;
	_uplink_payload.bytes[4] = _co2_ppm >> 8;
	_uplink_payload.bytes[5] = _co2_ppm & 0xFF;
	_uplink_payload.bytes[6] = _light_as_int >> 8;
	_uplink_payload.bytes[7] = _light_as_int & 0xFF;

	return _uplink_payload;
}