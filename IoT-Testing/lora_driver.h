#define LORA_MAX_PAYLOAD_LENGTH	20
typedef struct lora_payload {
	uint8_t port_no; /**< Port_no the data is received on, or to transmit to */
	uint8_t len; /**< Length of the payload (no of bytes) */
	uint8_t bytes[LORA_MAX_PAYLOAD_LENGTH]; /**< Array to hold the payload to be sent, or that has been received */
} lora_payload_t;