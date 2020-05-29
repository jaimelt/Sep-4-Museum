/*
 * global_constants.h
 *
 * Created: 16/05/2020 10.46.56
 *  Author: Marina Ionel
 */

#pragma once

#define LORA_appEUI "e8ba58083fa48824"
#define LORA_appKEY "33a1fd9d394852209520d4bf0c15bfda"

#define LORA_PAYLOAD_PORT_NO 1

#define TEMPERATURE_HUMIDITY_MEASURE_BIT (1 << 0)
#define CO2_MEASURE_BIT (1 << 1)
#define LIGHT_MEASURE_BIT (1 << 2)

#define TEMPERATURE_HUMIDITY_READY_BIT (1 << 0)
#define CO2_READY_BIT (1 << 1)
#define LIGHT_READY_BIT (1 << 2)