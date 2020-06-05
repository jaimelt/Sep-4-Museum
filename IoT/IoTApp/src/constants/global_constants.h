/**
 * \file
 * \brief Constants that can be accessed globally representing configuration data
 *
 * \author Marina Ionel
 * \version 1.0.0
 *
 * \date 16/05/2020 10.46.56
 */
#pragma once

 //Parameters for OTAA join
#define LORA_appEUI "e8ba58083fa48824"

#define LORA_appKEY "33a1fd9d394852209520d4bf0c15bfda"

#define LORA_PAYLOAD_PORT_NO 1

#define CRITICAL_LIGHT_MAX 15000
#define CRITICAL_LIGHT_MIN 1000

#define TEMPERATURE_HUMIDITY_MEASURE_BIT (1 << 0)
#define CO2_MEASURE_BIT (1 << 1)
#define LIGHT_MEASURE_BIT (1 << 2)
#define ALL_MEASURE_BITS (TEMPERATURE_HUMIDITY_MEASURE_BIT | CO2_MEASURE_BIT | LIGHT_MEASURE_BIT)

#define TEMPERATURE_HUMIDITY_READY_BIT TEMPERATURE_HUMIDITY_MEASURE_BIT
#define CO2_READY_BIT CO2_MEASURE_BIT
#define LIGHT_READY_BIT LIGHT_MEASURE_BIT
#define ALL_READY_BITS ALL_MEASURE_BITS