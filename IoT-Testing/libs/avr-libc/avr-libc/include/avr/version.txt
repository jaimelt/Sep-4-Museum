/* Copyright (c) 2005, Joerg Wunsch                               -*- c -*-
    2    All rights reserved.
    3 
    4    Redistribution and use in source and binary forms, with or without
    5    modification, are permitted provided that the following conditions are met:
    6 
    7    * Redistributions of source code must retain the above copyright
    8      notice, this list of conditions and the following disclaimer.
    9 
   10    * Redistributions in binary form must reproduce the above copyright
   11      notice, this list of conditions and the following disclaimer in
   12      the documentation and/or other materials provided with the
   13      distribution.
   14 
   15    * Neither the name of the copyright holders nor the names of
   16      contributors may be used to endorse or promote products derived
   17      from this software without specific prior written permission.
   18 
   19   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
   20   AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
   21   IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
   22   ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
   23   LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
   24   CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
   25   SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
   26   INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
   27   CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
   28   ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
   29   POSSIBILITY OF SUCH DAMAGE. */
   30 
   31 /* $Id: version_8h_source.html,v 1.1.1.6 2016/02/09 07:13:40 joerg_wunsch Exp $ */
   32 
   33 /** \defgroup avr_version <avr/version.h>: avr-libc version macros
   34     \code #include <avr/version.h> \endcode
   35 
   36     This header file defines macros that contain version numbers and
   37     strings describing the current version of avr-libc.
   38 
   39     The version number itself basically consists of three pieces that
   40     are separated by a dot: the major number, the minor number, and
   41     the revision number.  For development versions (which use an odd
   42     minor number), the string representation additionally gets the
   43     date code (YYYYMMDD) appended.
   44 
   45     This file will also be included by \c <avr/io.h>.  That way,
   46     portable tests can be implemented using \c <avr/io.h> that can be
   47     used in code that wants to remain backwards-compatible to library
   48     versions prior to the date when the library version API had been
   49     added, as referenced but undefined C preprocessor macros
   50     automatically evaluate to 0.
   51 */
   52 
   53 #ifndef _AVR_VERSION_H_
   54 #define _AVR_VERSION_H_
   55 
   56 /** \ingroup avr_version
   57     String literal representation of the current library version. */
   58 #define __AVR_LIBC_VERSION_STRING__ "2.0.0"
   59 
   60 /** \ingroup avr_version
   61     Numerical representation of the current library version.
   62 
   63     In the numerical representation, the major number is multiplied by
   64     10000, the minor number by 100, and all three parts are then
   65     added.  It is intented to provide a monotonically increasing
   66     numerical value that can easily be used in numerical checks.
   67  */
   68 #define __AVR_LIBC_VERSION__        20000UL
   69 
   70 /** \ingroup avr_version
   71     String literal representation of the release date. */
   72 #define __AVR_LIBC_DATE_STRING__    "20150208"
   73 
   74 /** \ingroup avr_version
   75     Numerical representation of the release date. */
   76 #define __AVR_LIBC_DATE_            20150208UL
   77 
   78 /** \ingroup avr_version
   79     Library major version number. */
   80 #define __AVR_LIBC_MAJOR__          2
   81 
   82 /** \ingroup avr_version
   83     Library minor version number. */
   84 #define __AVR_LIBC_MINOR__          0
   85 
   86 /** \ingroup avr_version
   87     Library revision number. */
   88 #define __AVR_LIBC_REVISION__       0
   89 
   90 #endif /* _AVR_VERSION_H_ */
