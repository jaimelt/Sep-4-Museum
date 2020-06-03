#ifndef _FORCED_INCLUDE_H
#define _FORCED_INCLUDE_H
#include <stdint.h>
#define __AVR_LIBC_DEPRECATED_ENABLE__
#define __AVR_ATmega2560__
#define _AVR_SFR_DEFS_H_ 1

typedef unsigned char UBaseType_t;

// 0x136 is highest address of registers in ATMEGA2560
#define _HIGHEST_REGISTER_ADD	0x136

#define strncasecmp(a, b, c) _strnicmp(a, b, c)

// These global variables (fake hardware registers) needs to be accessible from both C and C++
// Therefore they must be declared in C scope if it is the C++ compiler that access
#ifdef __cplusplus
extern "C" {
#endif
	extern uint8_t __avr_reg[_HIGHEST_REGISTER_ADD];
#ifdef __cplusplus
} // extern "C"
#endif

// Macros to access the fake hardware registers
#define _SFR_MEM8(mem_addr) (*(uint8_t *)(&__avr_reg[mem_addr]))
#define _SFR_IO8(io_addr) (*(uint8_t *)(&__avr_reg[io_addr]))
// Byte value from bit_no
#define _BV(bit) (1 << (bit))

#endif /* _FORCED_INCLUDE_H */