/*
 * sensor_data_package_handler.h
 *
 * Created: 16/05/2020 12.03.15
 *  Author: Marina Ionel
 */ 
void SensorDataPackageHandler_setCo2ppm( uint16_t ppm);
void SensorDataPackageHandler_setHumidity(float humidity);
void SensorDataPackageHandler_setTemperature(float temperature);
void SensorDataPackageHandler_setLight(float lux);
lora_payload_t SensorDataPackageHandler_getLoraPayload(uint8_t portNo);