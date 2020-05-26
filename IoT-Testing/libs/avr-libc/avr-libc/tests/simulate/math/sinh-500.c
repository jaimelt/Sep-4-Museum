/* Copyright (c) 2007  Dmitry Xmelkov
   All rights reserved.

   Redistribution and use in source and binary forms, with or without
   modification, are permitted provided that the following conditions are met:

   * Redistributions of source code must retain the above copyright
     notice, this list of conditions and the following disclaimer.
   * Redistributions in binary form must reproduce the above copyright
     notice, this list of conditions and the following disclaimer in
     the documentation and/or other materials provided with the
     distribution.
   * Neither the name of the copyright holders nor the names of
     contributors may be used to endorse or promote products derived
     from this software without specific prior written permission.

   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
   AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
   IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
   ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
   LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
   CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
   SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
   INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
   CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
   ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
   POSSIBILITY OF SUCH DAMAGE.
 */

/* Test of sinh() function. 500 random cases.
   $Id$
 */
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include "progmem.h"

union lofl_u {
    long lo;
    float fl;
};

volatile union lofl_u v = { .lo = 1 };

PROGMEM const struct {		/* Table of test cases.	*/
    long x;		/* argument	*/
    long z;		/* sinh(x)	*/
} t[] = {

    /* x, sinh(x)	*/
    { 0xbee0f447,0xbee8430e }, /*   1: -4.3936369e-01 -4.5363659e-01 */
    { 0xbee7345a,0xbeef248b }, /*   2: -4.5157129e-01 -4.6707568e-01 */
    { 0x3e867ddc,0x3e880b2c }, /*   3:  2.6267898e-01  2.6571023e-01 */
    { 0x3f60ef91,0x3f7f0453 }, /*   4:  8.7865549e-01  9.9615973e-01 */
    { 0xbd9fcbcf,0xbd9ff554 }, /*   5: -7.8025453e-02 -7.8104645e-02 */
    { 0x3ebda5a1,0x3ec2036f }, /*   6:  3.7040427e-01  3.7893245e-01 */
    { 0xc09b7df6,0xc280e75b }, /*   7: -4.8591261e+00 -6.4451866e+01 */
    { 0x3f3291ea,0x3f416840 }, /*   8:  6.9753897e-01  7.5549698e-01 */
    { 0xbf646f18,0xbf81fd3f }, /*   9: -8.9232016e-01 -1.0155410e+00 */
    { 0x4061a9ae,0x4187d4ad }, /*  10:  3.5259814e+00  1.6978846e+01 */
    { 0xbf303b04,0xbf3e7bb4 }, /*  11: -6.8840051e-01 -7.4407506e-01 */
    { 0x3954cdaf,0x3954cdaf }, /*  12:  2.0294519e-04  2.0294519e-04 */
    { 0xbf129bc3,0xbf1ac13c }, /*  13: -5.7268924e-01 -6.0451102e-01 */
    { 0xbed9eba2,0xbee08f59 }, /*  14: -4.2562586e-01 -4.3859366e-01 */
    { 0x3fa0f620,0x3fceddf5 }, /*  15:  1.2575111e+00  1.6161486e+00 */
    { 0x3fc847d8,0x40124e40 }, /*  16:  1.5646925e+00  2.2860260e+00 */
    { 0xbfdfca58,0xc03246b0 }, /*  17: -1.7483625e+00 -2.7855644e+00 */
    { 0x3f634a85,0x3f812d15 }, /*  18:  8.8785583e-01  1.0091883e+00 */
    { 0xbf95e815,0xbfba9a61 }, /*  19: -1.1711451e+00 -1.4578363e+00 */
    { 0xbf1dc1dd,0xbf27eeef }, /*  20: -6.1623937e-01 -6.5598959e-01 */
    { 0x3feb92fc,0x40447e97 }, /*  21:  1.8404231e+00  3.0702264e+00 */
    { 0x3f739164,0x3f8d01ef }, /*  22:  9.5143723e-01  1.1016215e+00 */
    { 0xbff90af2,0xc05b5e4a }, /*  23: -1.9456465e+00 -3.4276299e+00 */
    { 0x3ffd442a,0x40630779 }, /*  24:  1.9786427e+00  3.5473311e+00 */
    { 0xbf67093f,0xbf83d9b1 }, /*  25: -9.0248483e-01 -1.0300809e+00 */
    { 0x401805c0,0x40aa96c1 }, /*  26:  2.3753510e+00  5.3309026e+00 */
    { 0xbf99fd13,0xbfc1ea5f }, /*  27: -1.2030357e+00 -1.5149649e+00 */
    { 0xc00be162,0xc08c8aad }, /*  28: -2.1856313e+00 -4.3919282e+00 */
    { 0x3edb9e9e,0x3ee26aa5 }, /*  29:  4.2894453e-01  4.4221988e-01 */
    { 0xbed491fa,0xbedabadb }, /*  30: -4.1517621e-01 -4.2720684e-01 */
    { 0xbf5f5725,0xbf7cc51a }, /*  31: -8.7242347e-01 -9.8738253e-01 */
    { 0x3f22f57d,0x3f2e307d }, /*  32:  6.3655835e-01  6.8042737e-01 */
    { 0x40361f57,0x41093d2c }, /*  33:  2.8456628e+00  8.5774345e+00 */
    { 0x3f9b9508,0x3fc4d2c8 }, /*  34:  1.2154856e+00  1.5376825e+00 */
    { 0xbefe91a1,0xbf049812 }, /*  35: -4.9720481e-01 -5.1794541e-01 */
    { 0xbf89f6d8,0xbfa645de }, /*  36: -1.0778456e+00 -1.2990072e+00 */
    { 0x3ff083a9,0x404c9e5e }, /*  37:  1.8790179e+00  3.1971660e+00 */
    { 0x3f65bf11,0x3f82ed1c }, /*  38:  8.9744669e-01  1.0228610e+00 */
    { 0xc029b3cd,0xc0e1b2ce }, /*  39: -2.6515992e+00 -7.0530767e+00 */
    { 0x403b348f,0x4114a82a }, /*  40:  2.9250829e+00  9.2910557e+00 */
    { 0x3f6a8f86,0x3f866495 }, /*  41:  9.1625249e-01  1.0499445e+00 */
    { 0x3f8b6590,0x3fa8a1b8 }, /*  42:  1.0890369e+00  1.3174353e+00 */
    { 0x3f6bbe00,0x3f87403d }, /*  43:  9.2086792e-01  1.0566479e+00 */
    { 0xbf7556ee,0xbf8e5431 }, /*  44: -9.5835769e-01 -1.1119443e+00 */
    { 0xbff417c3,0xc052b178 }, /*  45: -1.9069752e+00 -3.2920818e+00 */
    { 0x3f1e02e0,0x3f283cb5 }, /*  46:  6.1723137e-01  6.5717632e-01 */
    { 0x3f2a7332,0x3f375374 }, /*  47:  6.6582024e-01  7.1611714e-01 */
    { 0x3f3109c5,0x3f3f7da8 }, /*  48:  6.9155532e-01  7.4801111e-01 */
    { 0x3f9c33be,0x3fc5f67d }, /*  49:  1.2203290e+00  1.5465847e+00 */
    { 0xbf9939d2,0xbfc088d1 }, /*  50: -1.1970770e+00 -1.5041753e+00 */
    { 0x3f026132,0x3f0816ef }, /*  51:  5.0929558e-01  5.3159994e-01 */
    { 0x3f44e1d4,0x3f58df61 }, /*  52:  7.6907086e-01  8.4715849e-01 */
    { 0x3fb843d1,0x3ffed6a1 }, /*  53:  1.4395696e+00  1.9909250e+00 */
    { 0xc02e75e0,0xc0f34919 }, /*  54: -2.7259445e+00 -7.6026731e+00 */
    { 0x3e958cd2,0x3e97af89 }, /*  55:  2.9209000e-01  2.9626110e-01 */
    { 0x3f996269,0x3fc0d22c }, /*  56:  1.1983157e+00  1.5064139e+00 */
    { 0xbf6c9f13,0xbf87e42a }, /*  57: -9.2430228e-01 -1.0616505e+00 */
    { 0xbed4352e,0xbeda55f6 }, /*  58: -4.1446823e-01 -4.2643708e-01 */
    { 0xbf636a5b,0xbf8143b4 }, /*  59: -8.8834161e-01 -1.0098786e+00 */
    { 0xc0f60a6b,0xc4887b0b }, /*  60: -7.6887717e+00 -1.0918451e+03 */
    { 0x3e9436d7,0x3e964aff }, /*  61:  2.8948089e-01  2.9354092e-01 */
    { 0xbf4c9911,0xbf6315be }, /*  62: -7.9921061e-01 -8.8705051e-01 */
    { 0x3fd0c674,0x401d3bc5 }, /*  63:  1.6310563e+00  2.4567730e+00 */
    { 0xbe4df209,0xbe4f562d }, /*  64: -2.0111860e-01 -2.0247717e-01 */
    { 0x3f1a5021,0x3f23d446 }, /*  65:  6.0278517e-01  6.3995779e-01 */
    { 0x3ef3561d,0x3efc99e3 }, /*  66:  4.7526637e-01  4.9336156e-01 */
    { 0x3f149a5f,0x3f1d171a }, /*  67:  5.8048052e-01  6.1363375e-01 */
    { 0xc1a6faeb,0xce0a65e1 }, /*  68: -2.0872519e+01 -5.8048314e+08 */
    { 0xbf01ed25,0xbf07938e }, /*  69: -5.0752479e-01 -5.2959526e-01 */
    { 0x3eb6e560,0x3ebacf86 }, /*  70:  3.5721874e-01  3.6486453e-01 */
    { 0x4060ddc9,0x418625df }, /*  71:  3.5135367e+00  1.6768492e+01 */
    { 0xbf143567,0xbf1ca0af }, /*  72: -5.7893986e-01 -6.1182684e-01 */
    { 0xbf5ed092,0xbf7c081e }, /*  73: -8.7037003e-01 -9.8449886e-01 */
    { 0x40778928,0x41bf4115 }, /*  74:  3.8677464e+00  2.3906778e+01 */
    { 0xc0671132,0xc193d19f }, /*  75: -3.6104245e+00 -1.8477354e+01 */
    { 0xbf9a5693,0xbfc28d05 }, /*  76: -1.2057670e+00 -1.5199286e+00 */
    { 0x25b28812,0x25b28812 }, /*  77:  3.0970283e-16  3.0970283e-16 */
    { 0xbf968aa2,0xbfbbba55 }, /*  78: -1.1761057e+00 -1.4666239e+00 */
    { 0xbf702ac4,0xbf8a7d70 }, /*  79: -9.3815255e-01 -1.0819530e+00 */
    { 0xbf95feb0,0xbfbac25a }, /*  80: -1.1718349e+00 -1.4590561e+00 */
    { 0xbfd6bada,0xc0254dcb }, /*  81: -1.6775773e+00 -2.5828731e+00 */
    { 0xbf800000,0xbf966cfe }, /*  82: -1.0000000e+00 -1.1752012e+00 */
    { 0xbef2b83a,0xbefbe9e1 }, /*  83: -4.7406179e-01 -4.9201873e-01 */
    { 0xc0c1f421,0xc3566969 }, /*  84: -6.0610509e+00 -2.1441176e+02 */
    { 0xbf37f41e,0xbf4832a3 }, /*  85: -7.1856868e-01 -7.8202266e-01 */
    { 0xbf8cdaf0,0xbfab0e17 }, /*  86: -1.1004314e+00 -1.3363675e+00 */
    { 0x3f65e59e,0x3f8308b0 }, /*  87:  8.9803493e-01  1.0237026e+00 */
    { 0xbf3453fb,0xbf439d7e }, /*  88: -7.0440644e-01 -7.6412189e-01 */
    { 0x3f29cea3,0x3f368933 }, /*  89:  6.6330928e-01  7.1303099e-01 */
    { 0x3f9905c1,0x3fc02ad5 }, /*  90:  1.1954881e+00  1.5013071e+00 */
    { 0x4012b947,0x409cc9a2 }, /*  91:  2.2925584e+00  4.8996134e+00 */
    { 0xbf9e86c6,0xbfca46c9 }, /*  92: -1.2384880e+00 -1.5802852e+00 */
    { 0x3f253984,0x3f30efcf }, /*  93:  6.4540887e-01  6.9115919e-01 */
    { 0x3f39e2e5,0x3f4aa836 }, /*  94:  7.2611839e-01  7.9162920e-01 */
    { 0x3f2eefe1,0x3f3cdf94 }, /*  95:  6.8334776e-01  7.3778653e-01 */
    { 0xbf1f8914,0xbf2a1065 }, /*  96: -6.2318540e-01 -6.6431266e-01 */
    { 0xbf16eec3,0xbf1fd480 }, /*  97: -5.8958071e-01 -6.2433624e-01 */
    { 0xbf343dfe,0xbf4381d3 }, /*  98: -7.0407093e-01 -7.6369971e-01 */
    { 0x3fe5e70b,0x403b8711 }, /*  99:  1.7961134e+00  2.9301188e+00 */
    { 0x3f991418,0x3fc044b5 }, /* 100:  1.1959257e+00  1.5020968e+00 */
    { 0xbf3a8ea6,0xbf4b8372 }, /* 101: -7.2873914e-01 -7.9497445e-01 */
    { 0x3fd8bfb4,0x40281ed9 }, /* 102:  1.6933503e+00  2.6268828e+00 */
    { 0x3e9758b2,0x3e998f68 }, /* 103:  2.9559857e-01  2.9992223e-01 */
    { 0x3fa09a03,0x3fce2f1b }, /* 104:  1.2547001e+00  1.6108125e+00 */
    { 0xaced00fa,0xaced00fa }, /* 105: -6.7360536e-12 -6.7360536e-12 */
    { 0x3f5fc227,0x3f7d5b91 }, /* 106:  8.7405628e-01  9.8967844e-01 */
    { 0x389e6725,0x389e6725 }, /* 107:  7.5532393e-05  7.5532393e-05 */
    { 0xbe95f3c0,0xbe981ae6 }, /* 108: -2.9287529e-01 -2.9708022e-01 */
    { 0x3f8bbc1b,0x3fa93103 }, /* 109:  1.0916780e+00  1.3218082e+00 */
    { 0xbf679151,0xbf843b70 }, /* 110: -9.0456110e-01 -1.0330639e+00 */
    { 0xbde4b232,0xbde52bf2 }, /* 111: -1.1166801e-01 -1.1190023e-01 */
    { 0xbfa01ce6,0xbfcd4247 }, /* 112: -1.2508819e+00 -1.6035851e+00 */
    { 0x40d1c173,0x43afa9c2 }, /* 113:  6.5548644e+00  3.5132623e+02 */
    { 0x3dde869c,0x3ddef6c4 }, /* 114:  1.0865518e-01  1.0886911e-01 */
    { 0xbfed8fea,0xc047ba5a }, /* 115: -1.8559544e+00 -3.1207490e+00 */
    { 0x404731ab,0x413371d0 }, /* 116:  3.1124065e+00  1.1215286e+01 */
    { 0xbeda408c,0xbee0ec15 }, /* 117: -4.2627370e-01 -4.3930116e-01 */
    { 0xbf3a9010,0xbf4b8540 }, /* 118: -7.2876072e-01 -7.9500198e-01 */
    { 0xbea7538d,0xbeaa5223 }, /* 119: -3.2680932e-01 -3.3265790e-01 */
    { 0x42b18b58,0x7f067885 }, /* 120:  8.8772156e+01  1.7874232e+38 */
    { 0x40498f5d,0x413a3a98 }, /* 121:  3.1493752e+00  1.1639305e+01 */
    { 0xbf9a9a11,0xbfc307ec }, /* 122: -1.2078267e+00 -1.5236793e+00 */
    { 0x3f3219be,0x3f40d1b9 }, /* 123:  6.9570529e-01  7.5320011e-01 */
    { 0x3fcc67e3,0x40178771 }, /* 124:  1.5969204e+00  2.3676417e+00 */
    { 0xbffd9915,0xc063a428 }, /* 125: -1.9812342e+00 -3.5568943e+00 */
    { 0x3ff40f98,0x4052a36b }, /* 126:  1.9067259e+00  3.2912242e+00 */
    { 0xbffae80e,0xc05eb80a }, /* 127: -1.9602067e+00 -3.4799829e+00 */
    { 0x3f977db2,0x3fbd6b1e }, /* 128:  1.1835234e+00  1.4798315e+00 */
    { 0x3f66a915,0x3f8394b3 }, /* 129:  9.0101749e-01  1.0279754e+00 */
    { 0xbe0a4328,0xbe0aaecd }, /* 130: -1.3502181e-01 -1.3543244e-01 */
    { 0xbf65525e,0xbf829f69 }, /* 131: -8.9578807e-01 -1.0204898e+00 */
    { 0xbf36c3f9,0xbf46b116 }, /* 132: -7.1392781e-01 -7.7613962e-01 */
    { 0x3eeae2f1,0x3ef33679 }, /* 133:  4.5876268e-01  4.7502497e-01 */
    { 0x3f74e0cc,0x3f8dfbeb }, /* 134:  9.5655513e-01  1.1092504e+00 */
    { 0xbed7a79f,0xbede168f }, /* 135: -4.2120072e-01 -4.3376586e-01 */
    { 0x3f95566e,0x3fb9995b }, /* 136:  1.1667001e+00  1.4499925e+00 */
    { 0xc03af9ef,0xc1141f73 }, /* 137: -2.9215047e+00 -9.2576780e+00 */
    { 0xbd4f881c,0xbd4f9ed8 }, /* 138: -5.0666913e-02 -5.0688595e-02 */
    { 0xbf21622e,0xbf2c4984 }, /* 139: -6.3040435e-01 -6.7299676e-01 */
    { 0x3ccfd9f2,0x3ccfdfa8 }, /* 140:  2.5372479e-02  2.5375202e-02 */
    { 0xbeffbc40,0xbf05404f }, /* 141: -4.9948311e-01 -5.2051252e-01 */
    { 0x3ee764e2,0x3eef5a1c }, /* 142:  4.5194155e-01  4.6748435e-01 */
    { 0xc03a37f6,0xc1125e8d }, /* 143: -2.9096656e+00 -9.1480837e+00 */
    { 0x3fcd64a5,0x4018cd69 }, /* 144:  1.6046339e+00  2.3875372e+00 */
    { 0xbf9ac626,0xbfc3584f }, /* 145: -1.2091720e+00 -1.5261325e+00 */
    { 0xbf3b5cb7,0xbf4c8af3 }, /* 146: -7.3188347e-01 -7.9899520e-01 */
    { 0xc02b8807,0xc0e84e5c }, /* 147: -2.6801775e+00 -7.2595654e+00 */
    { 0xbff8ef6a,0xc05b2d29 }, /* 148: -1.9448063e+00 -3.4246314e+00 */
    { 0xbf65ffb5,0xbf831b5b }, /* 149: -8.9843303e-01 -1.0242723e+00 */
    { 0x3f96cb93,0x3fbc2db4 }, /* 150:  1.1780876e+00  1.4701447e+00 */
    { 0xbf68a279,0xbf85001a }, /* 151: -9.0872914e-01 -1.0390656e+00 */
    { 0xbf1dc5c8,0xbf27f39f }, /* 152: -6.1629915e-01 -6.5606111e-01 */
    { 0xbf389540,0xbf48ff58 }, /* 153: -7.2102737e-01 -7.8514624e-01 */
    { 0x3f0073c8,0x3f05e91d }, /* 154:  5.0176668e-01  5.2308828e-01 */
    { 0x3f5f63ed,0x3f7cd710 }, /* 155:  8.7261850e-01  9.8765659e-01 */
    { 0xbd0c024b,0xbd0c0946 }, /* 156: -3.4181874e-02 -3.4188531e-02 */
    { 0x3f675822,0x3f841257 }, /* 157:  9.0368855e-01  1.0318097e+00 */
    { 0xbf4ca865,0xbf632a3c }, /* 158: -7.9944450e-01 -8.8736320e-01 */
    { 0xbe8b1314,0xbe8cca83 }, /* 159: -2.7162993e-01 -2.7498254e-01 */
    { 0xbf64fb86,0xbf826166 }, /* 160: -8.9446294e-01 -1.0185974e+00 */
    { 0xbf5c531c,0xbf788c9c }, /* 161: -8.6064315e-01 -9.7089553e-01 */
    { 0xbf3b0b30,0xbf4c22a3 }, /* 162: -7.3063946e-01 -7.9740351e-01 */
    { 0x3f1cc3cd,0x3f26bf68 }, /* 163:  6.1236268e-01  6.5135813e-01 */
    { 0xbff5539c,0xc054d356 }, /* 164: -1.9166141e+00 -3.3253989e+00 */
    { 0xbf8a9d8d,0xbfa757b5 }, /* 165: -1.0829331e+00 -1.3073641e+00 */
    { 0x3fcdd3b3,0x40195d5e }, /* 166:  1.6080230e+00  2.3963237e+00 */
    { 0x3fa151d4,0x3fcf8c73 }, /* 167:  1.2603097e+00  1.6214737e+00 */
    { 0xbd109acd,0xbd10a27e }, /* 168: -3.5303880e-02 -3.5311215e-02 */
    { 0xbf154efe,0xbf1deb2b }, /* 169: -5.8323658e-01 -6.1686963e-01 */
    { 0x3e67f3e1,0x3e69f0fa }, /* 170:  2.2651626e-01  2.2845832e-01 */
    { 0xc1cba61f,0xd153a19c }, /* 171: -2.5456114e+01 -5.6809341e+10 */
    { 0xbf00e672,0xbf066a92 }, /* 172: -5.0351632e-01 -5.2506363e-01 */
    { 0x3f29333e,0x3f35ca7a }, /* 173:  6.6093814e-01  7.1012080e-01 */
    { 0x3f93886e,0x3fb6704b }, /* 174:  1.1526010e+00  1.4253019e+00 */
    { 0xbc3dd6a4,0xbc3dd7ba }, /* 175: -1.1586819e-02 -1.1587078e-02 */
    { 0xbf2a36bc,0xbf37091b }, /* 176: -6.6489768e-01 -7.1498269e-01 */
    { 0x3f8c5215,0x3faa2a0c }, /* 177:  1.0962549e+00  1.3294082e+00 */
    { 0xbefc64d3,0xbf035ed9 }, /* 178: -4.9295673e-01 -5.1316601e-01 */
    { 0xbfb9337b,0xc000772b }, /* 179: -1.4468836e+00 -2.0072734e+00 */
    { 0xbfd065f8,0xc01cbbfb }, /* 180: -1.6281118e+00 -2.4489734e+00 */
    { 0xbf6627ce,0xbf833810 }, /* 181: -8.9904487e-01 -1.0251484e+00 */
    { 0x3f8fb00d,0x3fafd331 }, /* 182:  1.1225601e+00  1.3736326e+00 */
    { 0xbfbd0888,0xc004d21d }, /* 183: -1.4768229e+00 -2.0753243e+00 */
    { 0x3a7d36b4,0x3a7d36b7 }, /* 184:  9.6593355e-04  9.6593372e-04 */
    { 0xbcc18363,0xbcc187fe }, /* 185: -2.3622220e-02 -2.3624416e-02 */
    { 0x3f423b0d,0x3f5568bc }, /* 186:  7.5871354e-01  8.3362937e-01 */
    { 0xc0120485,0xc09b081e }, /* 187: -2.2815259e+00 -4.8447409e+00 */
    { 0x3eefbd26,0x3ef89887 }, /* 188:  4.6823996e-01  4.8553869e-01 */
    { 0x4023ada3,0x40cd35f0 }, /* 189:  2.5574729e+00  6.4128342e+00 */
    { 0x4038797c,0x410e6927 }, /* 190:  2.8824148e+00  8.9006720e+00 */
    { 0xbfedc152,0xc0480b5c }, /* 191: -1.8574622e+00 -3.1256933e+00 */
    { 0xbf59e683,0xbf752e78 }, /* 192: -8.5117358e-01 -9.5774031e-01 */
    { 0x3f169998,0x3f1f7021 }, /* 193:  5.8828115e-01  6.2280470e-01 */
    { 0xbeff5459,0xbf0505c0 }, /* 194: -4.9869040e-01 -5.1961899e-01 */
    { 0xbf8fa728,0xbfafc415 }, /* 195: -1.1222887e+00 -1.3731714e+00 */
    { 0x038fa702,0x038fa702 }, /* 196:  8.4431277e-37  8.4431277e-37 */
    { 0x3f5e229c,0x3f7b143a }, /* 197:  8.6771560e-01  9.8077738e-01 */
    { 0xc10d4776,0xc5559f5e }, /* 198: -8.8299465e+00 -3.4179604e+03 */
    { 0xbf63f41d,0xbf81a5ab }, /* 199: -8.9044362e-01 -1.0128683e+00 */
    { 0xbf645434,0xbf81ea16 }, /* 200: -8.9190984e-01 -1.0149562e+00 */
    { 0x3f670883,0x3f83d92a }, /* 201:  9.0247363e-01  1.0300648e+00 */
    { 0x3f370363,0x3f470162 }, /* 202:  7.1489543e-01  7.7736485e-01 */
    { 0x3f724d7e,0x3f8c116d }, /* 203:  9.4649494e-01  1.0942818e+00 */
    { 0xbf70392f,0xbf8a8810 }, /* 204: -9.3837255e-01 -1.0822773e+00 */
    { 0x3f64dad0,0x3f824a0e }, /* 205:  8.9396381e-01  1.0178850e+00 */
    { 0xbf6772a2,0xbf842562 }, /* 206: -9.0409291e-01 -1.0323908e+00 */
    { 0x3f2a199e,0x3f36e551 }, /* 207:  6.6445339e-01  7.1443659e-01 */
    { 0xbe56e5d9,0xbe587a8e }, /* 208: -2.0986117e-01 -2.1140501e-01 */
    { 0x3ecfad56,0x3ed56b3b }, /* 209:  4.0561932e-01  4.1683373e-01 */
    { 0x3f334c75,0x3f42523f }, /* 210:  7.0038539e-01  7.5906748e-01 */
    { 0xbfc9c93d,0xc01431ab }, /* 211: -1.5764538e+00 -2.3155315e+00 */
    { 0x3f0d3065,0x3f1474d4 }, /* 212:  5.5151969e-01  5.7990766e-01 */
    { 0x3f9799d5,0x3fbd9d64 }, /* 213:  1.1843821e+00  1.4813657e+00 */
    { 0x3fcdd47f,0x40195e67 }, /* 214:  1.6080474e+00  2.3963869e+00 */
    { 0x41f4c127,0x550cdfc9 }, /* 215:  3.0594313e+01  9.6807986e+12 */
    { 0x3f98375d,0x3fbeb782 }, /* 216:  1.1891896e+00  1.4899752e+00 */
    { 0xbf682aea,0xbf84a9f3 }, /* 217: -9.0690482e-01 -1.0364364e+00 */
    { 0x415a8904,0x48d0a7d4 }, /* 218:  1.3658451e+01  4.2732662e+05 */
    { 0xbde54065,0xbde5bb09 }, /* 219: -1.1193923e-01 -1.1217315e-01 */
    { 0xbf676686,0xbf841cae }, /* 220: -9.0390813e-01 -1.0321252e+00 */
    { 0x42448009,0x626a5660 }, /* 221:  4.9125034e+01  1.0806905e+21 */
    { 0x3ef7abc9,0x3f00b8cc }, /* 222:  4.8373249e-01  5.0281978e-01 */
    { 0x3f18c33e,0x3f21fdd7 }, /* 223:  5.9672916e-01  6.3277954e-01 */
    { 0x3fc385a2,0x400c77f6 }, /* 224:  1.5275156e+00  2.1948218e+00 */
    { 0x419036d5,0x4c00a27f }, /* 225:  1.8026773e+01  3.3720828e+07 */
    { 0xbf2b1613,0xbf381bef }, /* 226: -6.6830558e-01 -7.1917623e-01 */
    { 0xbfe9e31d,0xc041c9b0 }, /* 227: -1.8272434e+00 -3.0279350e+00 */
    { 0xbefde801,0xbf043896 }, /* 228: -4.9591067e-01 -5.1648843e-01 */
    { 0xbf03b8c7,0xbf099c86 }, /* 229: -5.1453823e-01 -5.3754461e-01 */
    { 0x3f446e33,0x3f5847ec }, /* 230:  7.6730651e-01  8.4484744e-01 */
    { 0xbe6fbfd5,0xbe71f21c }, /* 231: -2.3413022e-01 -2.3627514e-01 */
    { 0x3f9162a2,0x3fb2b990 }, /* 232:  1.1358225e+00  1.3962879e+00 */
    { 0xc176ebf4,0xca19c2c3 }, /* 233: -1.5432606e+01 -2.5192168e+06 */
    { 0x41935d4a,0x4c3eb5ce }, /* 234:  1.8420551e+01  4.9993528e+07 */
    { 0x40f5214f,0x4484a6d7 }, /* 235:  7.6603160e+00  1.0612137e+03 */
    { 0x401b04b4,0x40b2e74d }, /* 236:  2.4221621e+00  5.5907350e+00 */
    { 0xbbe3324e,0xbbe332c5 }, /* 237: -6.9334870e-03 -6.9335424e-03 */
    { 0x3e7fa73b,0x3e8128a0 }, /* 238:  2.4966137e-01  2.5226307e-01 */
    { 0x3f00fdf6,0x3f068522 }, /* 239:  5.0387514e-01  5.2546895e-01 */
    { 0x401e185a,0x40bbd9cf }, /* 240:  2.4702363e+00  5.8703380e+00 */
    { 0x3f104be1,0x3f180f37 }, /* 241:  5.6365782e-01  5.9398216e-01 */
    { 0x3ed855cc,0x3eded477 }, /* 242:  4.2252958e-01  4.3521473e-01 */
    { 0x3f8d3494,0x3faba3df }, /* 243:  1.1031671e+00  1.3409384e+00 */
    { 0xbf6c8206,0xbf87cefc }, /* 244: -9.2385900e-01 -1.0610042e+00 */
    { 0xbfd0e775,0xc01d6790 }, /* 245: -1.6320635e+00 -2.4594460e+00 */
    { 0x3fa196e3,0x3fd01020 }, /* 246:  1.2624172e+00  1.6254921e+00 */
    { 0x3fdbef84,0x402ca6d8 }, /* 247:  1.7182469e+00  2.6976833e+00 */
    { 0x3f96dd30,0x3fbc4d07 }, /* 248:  1.1786251e+00  1.4711007e+00 */
    { 0xbf8c5434,0xbfaa2d93 }, /* 249: -1.0963197e+00 -1.3295158e+00 */
    { 0x3f921827,0x3fb3f204 }, /* 250:  1.1413621e+00  1.4058232e+00 */
    { 0x3fbeb293,0x4006bfbd }, /* 251:  1.4898247e+00  2.1054528e+00 */
    { 0x3f8a09d0,0x3fa664f8 }, /* 252:  1.0784245e+00  1.2999563e+00 */
    { 0xbfdc5f5c,0xc02d47fe }, /* 253: -1.7216601e+00 -2.7075191e+00 */
    { 0xbf9a9701,0xbfc30257 }, /* 254: -1.2077333e+00 -1.5235089e+00 */
    { 0x3dffa6bd,0x3e00286c }, /* 255:  1.2482975e-01  1.2515420e-01 */
    { 0xbf677f85,0xbf842ea5 }, /* 256: -9.0428954e-01 -1.0326735e+00 */
    { 0xbf17e136,0xbf20f29a }, /* 257: -5.9328020e-01 -6.2870181e-01 */
    { 0x3e410fb8,0x3e42350b }, /* 258:  1.8853652e-01  1.8965547e-01 */
    { 0x4030f06b,0x40fcf94f }, /* 259:  2.7646739e+00  7.9054332e+00 */
    { 0x3f9b22f2,0x3fc401d3 }, /* 260:  1.2120039e+00  1.5313057e+00 */
    { 0xbf0a34a2,0xbf11047f }, /* 261: -5.3986561e-01 -5.6647485e-01 */
    { 0xc01ae62c,0xc0b290ae }, /* 262: -2.4202986e+00 -5.5801611e+00 */
    { 0xb4feb15e,0xb4feb15e }, /* 263: -4.7440238e-07 -4.7440238e-07 */
    { 0xbf8bf7b3,0xbfa993db }, /* 264: -1.0934967e+00 -1.3248247e+00 */
    { 0x3f981405,0x3fbe781d }, /* 265:  1.1881109e+00  1.4880406e+00 */
    { 0x3f9adf8b,0x3fc386a8 }, /* 266:  1.2099470e+00  1.5275469e+00 */
    { 0x40d0d833,0x43aabb64 }, /* 267:  6.5263915e+00  3.4146399e+02 */
    { 0x417899fa,0x4a2ac808 }, /* 268:  1.5537592e+01  2.7980820e+06 */
    { 0xbb65bed2,0xbb65bef1 }, /* 269: -3.5056365e-03 -3.5056437e-03 */
    { 0x3f90df22,0x3fb1d816 }, /* 270:  1.1318095e+00  1.3894069e+00 */
    { 0x3f18319a,0x3f215197 }, /* 271:  5.9450686e-01  6.3015121e-01 */
    { 0x3f98cd37,0x3fbfc4eb }, /* 272:  1.1937627e+00  1.4981970e+00 */
    { 0xc02a697c,0xc0e43d7e }, /* 273: -2.6626883e+00 -7.1325064e+00 */
    { 0x3ee52319,0x3eecdce7 }, /* 274:  4.4753340e-01  4.6262285e-01 */
    { 0x4075531a,0x41b8c0ec }, /* 275:  3.8331971e+00  2.3094200e+01 */
    { 0x3f19af5c,0x3f231587 }, /* 276:  6.0033202e-01  6.3704723e-01 */
    { 0x3e7f612c,0x3e810480 }, /* 277:  2.4939412e-01  2.5198746e-01 */
    { 0xbfd97272,0xc0291ab2 }, /* 278: -1.6988051e+00 -2.6422544e+00 */
    { 0x3e291e49,0x3e29e35f }, /* 279:  1.6515459e-01  1.6590641e-01 */
    { 0xbefb7ad0,0xbf02db63 }, /* 280: -4.9117136e-01 -5.1116008e-01 */
    { 0x3fced327,0x401aaa38 }, /* 281:  1.6158189e+00  2.4166393e+00 */
    { 0x3f5c74da,0x3f78bba5 }, /* 282:  8.6115801e-01  9.7161323e-01 */
    { 0x3f027f40,0x3f0838f9 }, /* 283:  5.0975418e-01  5.3211933e-01 */
    { 0x3f9d7923,0x3fc85048 }, /* 284:  1.2302593e+00  1.5649500e+00 */
    { 0x402a2c8e,0x40e3627b }, /* 285:  2.6589694e+00  7.1057715e+00 */
    { 0xc0831425,0xc1f061a0 }, /* 286: -4.0962090e+00 -3.0047668e+01 */
    { 0x3f31fafd,0x3f40ab3a }, /* 287:  6.9523603e-01  7.5261271e-01 */
    { 0x3f1c9622,0x3f2688ea }, /* 288:  6.1166584e-01  6.5052664e-01 */
    { 0xbfdf4e0a,0xc0318f11 }, /* 289: -1.7445691e+00 -2.7743571e+00 */
    { 0x3f1e6485,0x3f28b199 }, /* 290:  6.1872131e-01  6.5895993e-01 */
    { 0xbf4064d4,0xbf5305f4 }, /* 291: -7.5153852e-01 -8.2430959e-01 */
    { 0x3f1ef332,0x3f295c91 }, /* 292:  6.2089837e-01  6.6156870e-01 */
    { 0xbf91dd6c,0xbfb38cc4 }, /* 293: -1.1395698e+00 -1.4027333e+00 */
    { 0x3f6768c4,0x3f841e4b }, /* 294:  9.0394235e-01  1.0321745e+00 */
    { 0xbef9f43d,0xbf020037 }, /* 295: -4.8819152e-01 -5.0781578e-01 */
    { 0xbfd2c2dc,0xc01fe2ea }, /* 296: -1.6465716e+00 -2.4982247e+00 */
    { 0x3ffaf71e,0x405ed350 }, /* 297:  1.9606664e+00  3.4816475e+00 */
    { 0x3f0b443c,0x3f123cf8 }, /* 298:  5.4400992e-01  5.7124281e-01 */
    { 0x3f8f497d,0x3faf2527 }, /* 299:  1.1194302e+00  1.3683213e+00 */
    { 0xc04400a2,0xc12aae14 }, /* 300: -3.0625386e+00 -1.0667500e+01 */
    { 0xbf96b917,0xbfbc0cd8 }, /* 301: -1.1775235e+00 -1.4691420e+00 */
    { 0xc05a6ee3,0xc172971a }, /* 302: -3.4130180e+00 -1.5161890e+01 */
    { 0x3e24d26c,0x3e2588da }, /* 303:  1.6095895e-01  1.6165486e-01 */
    { 0x3f6b25ab,0x3f86d186 }, /* 304:  9.1854352e-01  1.0532691e+00 */
    { 0x3fa340ab,0x3fd3413b }, /* 305:  1.2754110e+00  1.6504282e+00 */
    { 0x3fcd2dca,0x40188678 }, /* 306:  1.6029599e+00  2.3832073e+00 */
    { 0x4056bb7c,0x4164ee0c }, /* 307:  3.3551931e+00  1.4308117e+01 */
    { 0xbf9938a8,0xbfc086b7 }, /* 308: -1.1970415e+00 -1.5041112e+00 */
    { 0x3ff42732,0x4052cc06 }, /* 309:  1.9074461e+00  3.2937026e+00 */
    { 0x3fe01325,0x4032b288 }, /* 310:  1.7505842e+00  2.7921467e+00 */
    { 0xbfc491f8,0xc00dbcc5 }, /* 311: -1.5357046e+00 -2.2146466e+00 */
    { 0xc0885c24,0xc20dc4c0 }, /* 312: -4.2612476e+00 -3.5442139e+01 */
    { 0xc0764809,0xc1bb89f7 }, /* 313: -3.8481467e+00 -2.3442366e+01 */
    { 0x3f0a1919,0x3f10e4da }, /* 314:  5.3944546e-01  5.6599200e-01 */
    { 0x3f61b29d,0x3f800bf6 }, /* 315:  8.8163167e-01  1.0003650e+00 */
    { 0xbf64fb44,0xbf826137 }, /* 316: -8.9445901e-01 -1.0185918e+00 */
    { 0xbf6ef2c0,0xbf8997fe }, /* 317: -9.3339157e-01 -1.0749509e+00 */
    { 0x4068ef2e,0x4198337c }, /* 318:  3.6395984e+00  1.9025139e+01 */
    { 0xbf240077,0xbf2f73c7 }, /* 319: -6.4063209e-01 -6.8536037e-01 */
    { 0xbed85092,0xbedecec4 }, /* 320: -4.2248970e-01 -4.3517125e-01 */
    { 0x3f9eeba6,0x3fcb03ad }, /* 321:  1.2415664e+00  1.5860497e+00 */
    { 0xbe048556,0xbe04e41d }, /* 322: -1.2941489e-01 -1.2977643e-01 */
    { 0xbeda2741,0xbee0d075 }, /* 323: -4.2608073e-01 -4.3909040e-01 */
    { 0x405963de,0x416ea8fa }, /* 324:  3.3967204e+00  1.4916254e+01 */
    { 0xbf04bf09,0xbf0ac68e }, /* 325: -5.1853997e-01 -5.4209220e-01 */
    { 0x3ff19567,0x404e6ab5 }, /* 326:  1.8873719e+00  3.2252629e+00 */
    { 0x3f0b961a,0x3f129b48 }, /* 327:  5.4525912e-01  5.7268190e-01 */
    { 0xbf7a075b,0xbf91dbf3 }, /* 328: -9.7667474e-01 -1.1395248e+00 */
    { 0x3fc719d6,0x4010d70e }, /* 329:  1.5554760e+00  2.2631259e+00 */
    { 0xbd6cb977,0xbd6cdb35 }, /* 330: -5.7794061e-02 -5.7826240e-02 */
    { 0xbf9eba5a,0xbfcaa74e }, /* 331: -1.2400620e+00 -1.5832307e+00 */
    { 0x3f97a16d,0x3fbdaaf6 }, /* 332:  1.1846138e+00  1.4817798e+00 */
    { 0xbf735a80,0xbf8cd91d }, /* 333: -9.5059967e-01 -1.1003758e+00 */
    { 0xbdc24057,0xbdc28af0 }, /* 334: -9.4849281e-02 -9.4991565e-02 */
    { 0x3fd4b784,0x40228956 }, /* 335:  1.6618505e+00  2.5396323e+00 */
    { 0x3fe8d8c2,0x404022a5 }, /* 336:  1.8191149e+00  3.0021145e+00 */
    { 0x3fedd2ff,0x4048285f }, /* 337:  1.8580016e+00  3.1274641e+00 */
    { 0x3ebc6936,0x3ec0b134 }, /* 338:  3.6799020e-01  3.7635195e-01 */
    { 0x405ba25d,0x4177321f }, /* 339:  3.4317849e+00  1.5449737e+01 */
    { 0xbf6a4f5a,0xbf863614 }, /* 340: -9.1527331e-01 -1.0485253e+00 */
    { 0xbefadbff,0xbf02823b }, /* 341: -4.8995969e-01 -5.0979966e-01 */
    { 0x3f6cfa17,0x3f882692 }, /* 342:  9.2569107e-01  1.0636771e+00 */
    { 0x3efcb8b7,0x3f038e00 }, /* 343:  4.9359676e-01  5.1388550e-01 */
    { 0x3f694ae1,0x3f8579a5 }, /* 344:  9.1129881e-01  1.0427748e+00 */
    { 0xbf640036,0xbf81ae47 }, /* 345: -8.9062822e-01 -1.0131310e+00 */
    { 0xc094fb66,0xc252581b }, /* 346: -4.6556883e+00 -5.2586040e+01 */
    { 0x3f029a0c,0x3f085755 }, /* 347:  5.1016307e-01  5.3258258e-01 */
    { 0x3fccad9e,0x4017e123 }, /* 348:  1.5990484e+00  2.3731163e+00 */
    { 0x3ed71374,0x3edd7517 }, /* 349:  4.2007029e-01  4.3253395e-01 */
    { 0xbf9da837,0xbfc8a7c4 }, /* 350: -1.2316960e+00 -1.5676198e+00 */
    { 0x3f948cfc,0x3fb8376e }, /* 351:  1.1605525e+00  1.4391916e+00 */
    { 0xbfc54da7,0xc00ea165 }, /* 352: -1.5414323e+00 -2.2286007e+00 */
    { 0xbf705db6,0xbf8aa2fb }, /* 353: -9.3892992e-01 -1.0830988e+00 */
    { 0x3f927a9d,0x3fb49c17 }, /* 354:  1.1443669e+00  1.4110135e+00 */
    { 0x3fe7f157,0x403eb5bb }, /* 355:  1.8120526e+00  2.9798419e+00 */
    { 0xbbc22e15,0xbbc22e5f }, /* 356: -5.9259036e-03 -5.9259380e-03 */
    { 0x402ca1b4,0x40ec5f47 }, /* 357:  2.6973696e+00  7.3866305e+00 */
    { 0xbd630088,0xbd631e49 }, /* 358: -5.5420429e-02 -5.5448804e-02 */
    { 0xbff39f16,0xc051e23c }, /* 359: -1.9032924e+00 -3.2794333e+00 */
    { 0xbed54a97,0xbedb83aa }, /* 360: -4.1658470e-01 -4.2873889e-01 */
    { 0x3dee707d,0x3deefa7b }, /* 361:  1.1642549e-01  1.1668869e-01 */
    { 0xbf10015f,0xbf17b895 }, /* 362: -5.6252092e-01 -5.9266025e-01 */
    { 0x40248d51,0x40d010bc }, /* 363:  2.5711253e+00  6.5020428e+00 */
    { 0xc005b7cd,0xc07e963c }, /* 364: -2.0893433e+00 -3.9779196e+00 */
    { 0xbf64b638,0xbf822ff4 }, /* 365: -8.9340544e-01 -1.0170884e+00 */
    { 0x3f68f3d3,0x3f853ac9 }, /* 366:  9.0997046e-01  1.0408565e+00 */
    { 0xc1a4b19b,0xcdcffd17 }, /* 367: -2.0586721e+01 -4.3618378e+08 */
    { 0x3ebd23d8,0x3ec178aa }, /* 368:  3.6941409e-01  3.7787372e-01 */
    { 0x3f339cd1,0x3f42b72c }, /* 369:  7.0161158e-01  7.6060748e-01 */
    { 0xbf9e2217,0xbfc98abd }, /* 370: -1.2354153e+00 -1.5745465e+00 */
    { 0x3fa38038,0x3fd3bbf8 }, /* 371:  1.2773504e+00  1.6541739e+00 */
    { 0xc00fa02a,0xc09538dd }, /* 372: -2.2441506e+00 -4.6631913e+00 */
    { 0xbf62d349,0xbf80d870 }, /* 373: -8.8603646e-01 -1.0066051e+00 */
    { 0x3ea33be2,0x3ea60367 }, /* 374:  3.1881624e-01  3.2424471e-01 */
    { 0x3fe2cef0,0x4036cac6 }, /* 375:  1.7719402e+00  2.8561263e+00 */
    { 0x3c9e274e,0x3c9e29d2 }, /* 376:  1.9305851e-02  1.9307051e-02 */
    { 0xc0388149,0xc10e7aa0 }, /* 377: -2.8828909e+00 -8.9049377e+00 */
    { 0x42004fea,0x561b4a82 }, /* 378:  3.2078041e+01  4.2686078e+13 */
    { 0xbfea77dd,0xc042b75d }, /* 379: -1.8317829e+00 -3.0424416e+00 */
    { 0x3f72fc67,0x3f8c9332 }, /* 380:  9.4916385e-01  1.0982420e+00 */
    { 0x4101ae14,0x44ceefad }, /* 381:  8.1049995e+00  1.6554899e+03 */
    { 0x3f260f60,0x3f31f405 }, /* 382:  6.4867210e-01  6.9512969e-01 */
    { 0x3f989ed8,0x3fbf7171 }, /* 383:  1.1923475e+00  1.4956495e+00 */
    { 0x3f65e12e,0x3f830583 }, /* 384:  8.9796722e-01  1.0236057e+00 */
    { 0x3fa1daef,0x3fd0921b }, /* 385:  1.2644938e+00  1.6294588e+00 */
    { 0xbfeced40,0xc046b073 }, /* 386: -1.8509903e+00 -3.1045196e+00 */
    { 0x3fdb34f9,0x402b9b36 }, /* 387:  1.7125541e+00  2.6813483e+00 */
    { 0x40057c3f,0x407da264 }, /* 388:  2.0857084e+00  3.9630365e+00 */
    { 0xc2713fec,0xea811ba6 }, /* 389: -6.0312424e+01 -7.8040998e+25 */
    { 0x3f2ef241,0x3f3ce288 }, /* 390:  6.8338400e-01  7.3783159e-01 */
    { 0xbfd65d51,0xc024cc6f }, /* 391: -1.6747228e+00 -2.5749776e+00 */
    { 0x3f93df3a,0x3fb70795 }, /* 392:  1.1552498e+00  1.4299189e+00 */
    { 0xbf9320d1,0xbfb5bc20 }, /* 393: -1.1494390e+00 -1.4198036e+00 */
    { 0x3f9b2333,0x3fc4024a }, /* 394:  1.2120117e+00  1.5313199e+00 */
    { 0x3f69bf0e,0x3f85cd9f }, /* 395:  9.1307151e-01  1.0453376e+00 */
    { 0xb8db1d6b,0xb8db1d6b }, /* 396: -1.0448213e-04 -1.0448213e-04 */
    { 0x3fa55e7d,0x3fd75e3f }, /* 397:  1.2919461e+00  1.6825637e+00 */
    { 0xbf1583c8,0xbf1e2935 }, /* 398: -5.8404207e-01 -6.1781627e-01 */
    { 0xbf2f15f5,0xbf3d0ee8 }, /* 399: -6.8392879e-01 -7.3850870e-01 */
    { 0x3f38c1d8,0x3f49380e }, /* 400:  7.2170782e-01  7.8601158e-01 */
    { 0xc248ba39,0xe3288f57 }, /* 401: -5.0181858e+01 -3.1093817e+21 */
    { 0xbed95c60,0xbedff2f3 }, /* 402: -4.2453289e-01 -4.3740043e-01 */
    { 0x3ec9536c,0x3ece8de1 }, /* 403:  3.9321458e-01  4.0342620e-01 */
    { 0xbe45b4e5,0xbe46efef }, /* 404: -1.9307287e-01 -1.9427465e-01 */
    { 0x3fd711f3,0x4025c68f }, /* 405:  1.6802353e+00  2.5902441e+00 */
    { 0x3f9a5fa7,0x3fc29d8a }, /* 406:  1.2060441e+00  1.5204327e+00 */
    { 0xbf62f4bd,0xbf80f02d }, /* 407: -8.8654691e-01 -1.0073296e+00 */
    { 0xbf9bfde5,0xbfc59362 }, /* 408: -1.2186857e+00 -1.5435603e+00 */
    { 0xbf61628c,0xbf7fa6b8 }, /* 409: -8.8040996e-01 -9.9863768e-01 */
    { 0xbef4f9c0,0xbefe6e26 }, /* 410: -4.7846794e-01 -4.9693412e-01 */
    { 0x40658d52,0x41905af8 }, /* 411:  3.5867505e+00  1.8044418e+01 */
    { 0x4061a23d,0x4187c4dc }, /* 412:  3.5255272e+00  1.6971123e+01 */
    { 0xc022a771,0xc0c9e9c3 }, /* 413: -2.5414698e+00 -6.3097854e+00 */
    { 0x3f913012,0x3fb262c7 }, /* 414:  1.1342795e+00  1.3936394e+00 */
    { 0x3df2e780,0x3df37965 }, /* 415:  1.1860561e-01  1.1888389e-01 */
    { 0x40520e30,0x4154c192 }, /* 416:  3.2821159e+00  1.3297258e+01 */
    { 0xbfd3e290,0xc0216798 }, /* 417: -1.6553516e+00 -2.5219479e+00 */
    { 0xbf993e1e,0xbfc09094 }, /* 418: -1.1972082e+00 -1.5044122e+00 */
    { 0x3f61019f,0x3f7f1dcf }, /* 419:  8.7893099e-01  9.9654859e-01 */
    { 0x37f74015,0x37f74015 }, /* 420:  2.9474535e-05  2.9474535e-05 */
    { 0x3fc95ef2,0x4013abd1 }, /* 421:  1.5732100e+00  2.3073618e+00 */
    { 0xbf8b9f0a,0xbfa900da }, /* 422: -1.0907910e+00 -1.3203385e+00 */
    { 0x3ff84cb0,0x405a0b93 }, /* 423:  1.9398403e+00  3.4069564e+00 */
    { 0x3f9b7081,0x3fc48fd0 }, /* 424:  1.2143708e+00  1.5356388e+00 */
    { 0xbf6624c0,0xbf8335e0 }, /* 425: -8.9899826e-01 -1.0250816e+00 */
    { 0x3ec819a8,0x3ecd3bb1 }, /* 426:  3.9082074e-01  4.0084603e-01 */
    { 0x3ff91cd7,0x405b7e3f }, /* 427:  1.9461926e+00  3.4295805e+00 */
    { 0x3e75e04b,0x3e783ee0 }, /* 428:  2.4011342e-01  2.4242735e-01 */
    { 0xbf896c52,0xbfa56329 }, /* 429: -1.0736182e+00 -1.2920886e+00 */
    { 0xbf94cd3f,0xbfb8a824 }, /* 430: -1.1625136e+00 -1.4426312e+00 */
    { 0xbf353e93,0xbf44c50f }, /* 431: -7.0798606e-01 -7.6863188e-01 */
    { 0xbf111907,0xbf18fe04 }, /* 432: -5.6678814e-01 -5.9762597e-01 */
    { 0x3f6f8378,0x3f8a0251 }, /* 433:  9.3559980e-01  1.0781957e+00 */
    { 0x3fa17499,0x3fcfceb7 }, /* 434:  1.2613708e+00  1.6234959e+00 */
    { 0xbf9979f6,0xbfc0fcc4 }, /* 435: -1.1990345e+00 -1.5077138e+00 */
    { 0x3efe4714,0x3f046e19 }, /* 436:  4.9663603e-01  5.1730496e-01 */
    { 0x3f89244d,0x3fa4ed98 }, /* 437:  1.0714203e+00  1.2885008e+00 */
    { 0xc061002d,0xc1866e29 }, /* 438: -3.5156357e+00 -1.6803789e+01 */
    { 0x3ef5b0c9,0x3eff3a9a }, /* 439:  4.7986439e-01  4.9849397e-01 */
    { 0xc1061f7c,0xc50895e2 }, /* 440: -8.3826866e+00 -2.1853677e+03 */
    { 0x3e3c04dc,0x3e3d13c5 }, /* 441:  1.8361229e-01  1.8464573e-01 */
    { 0x3f94ba85,0x3fb88747 }, /* 442:  1.1619421e+00  1.4416283e+00 */
    { 0xbff90f2a,0xc05b65d3 }, /* 443: -1.9457753e+00 -3.4280899e+00 */
    { 0x3fed81d7,0x4047a34b }, /* 444:  1.8555249e+00  3.1193416e+00 */
    { 0xbb144b36,0xbb144b3e }, /* 445: -2.2627837e-03 -2.2627856e-03 */
    { 0xbf8fdb6b,0xbfb01ceb }, /* 446: -1.1238836e+00 -1.3758825e+00 */
    { 0xbee59f21,0xbeed6597 }, /* 447: -4.4847968e-01 -4.6366569e-01 */
    { 0x3efb7f90,0x3f02de0e }, /* 448:  4.9120760e-01  5.1120079e-01 */
    { 0xbefe97c5,0xbf049b87 }, /* 449: -4.9725166e-01 -5.1799816e-01 */
    { 0x401af260,0x40b2b349 }, /* 450:  2.4210434e+00  5.5843854e+00 */
    { 0x3fe1eedd,0x403578d3 }, /* 451:  1.7651020e+00  2.8354995e+00 */
    { 0x3edceee2,0x3ee3da84 }, /* 452:  4.3151003e-01  4.4502652e-01 */
    { 0x3f9a1ee7,0x3fc227ce }, /* 453:  1.2040681e+00  1.5168397e+00 */
    { 0xbdf80d72,0xbdf8a8d2 }, /* 454: -1.2111939e-01 -1.2141575e-01 */
    { 0x3eec7c63,0x3ef4fc12 }, /* 455:  4.6188650e-01  4.7848564e-01 */
    { 0x3f914554,0x3fb28740 }, /* 456:  1.1349282e+00  1.3947525e+00 */
    { 0x3b895702,0x3b89571c }, /* 457:  4.1912803e-03  4.1912924e-03 */
    { 0x3fb1d692,0x3ff0d5cd }, /* 458:  1.3893607e+00  1.8815247e+00 */
    { 0x3f18786b,0x3f21a552 }, /* 459:  5.9558743e-01  6.3142884e-01 */
    { 0xbf96b8d9,0xbfbc0c6a }, /* 460: -1.1775161e+00 -1.4691288e+00 */
    { 0x3f16680a,0x3f1f35c3 }, /* 461:  5.8752501e-01  6.2191409e-01 */
    { 0x3f8db7bf,0x3fac7fa3 }, /* 462:  1.1071700e+00  1.3476452e+00 */
    { 0x3fff385d,0x4066a80c }, /* 463:  1.9939076e+00  3.6040068e+00 */
    { 0x3ee68d30,0x3eee6c18 }, /* 464:  4.5029593e-01  4.6566844e-01 */
    { 0x3f96a11e,0x3fbbe241 }, /* 465:  1.1767919e+00  1.4678422e+00 */
    { 0xbf0bd953,0xbf12e8c4 }, /* 466: -5.4628485e-01 -5.7386422e-01 */
    { 0xb9433ae1,0xb9433ae1 }, /* 467: -1.8618583e-04 -1.8618583e-04 */
    { 0xbf9077d9,0xbfb12781 }, /* 468: -1.1286575e+00 -1.3840181e+00 */
    { 0xbef2bdef,0xbefbf03d }, /* 469: -4.7410533e-01 -4.9206725e-01 */
    { 0xbfa1fd36,0xbfd0d3ab }, /* 470: -1.2655399e+00 -1.6314596e+00 */
    { 0xbebc5ca4,0xbec0a3c6 }, /* 471: -3.6789429e-01 -3.7624949e-01 */
    { 0xbf5ca6a3,0xbf790114 }, /* 472: -8.6191767e-01 -9.7267270e-01 */
    { 0x3f9330ff,0x3fb5d83a }, /* 473:  1.1499327e+00  1.4206612e+00 */
    { 0x410a70a1,0x4532e365 }, /* 474:  8.6524973e+00  2.8622122e+03 */
    { 0x3fa046cb,0x3fcd917f }, /* 475:  1.2521604e+00  1.6060027e+00 */
    { 0x3f97a762,0x3fbdb59d }, /* 476:  1.1847956e+00  1.4821049e+00 */
    { 0x3f660cf1,0x3f8324d5 }, /* 477:  8.9863497e-01  1.0245615e+00 */
    { 0x3c30b0d3,0x3c30b1b3 }, /* 478:  1.0784346e-02  1.0784554e-02 */
    { 0xbf9fee02,0xbfcce9b7 }, /* 479: -1.2494509e+00 -1.6008824e+00 */
    { 0xbf1ff200,0xbf2a8e6a }, /* 480: -6.2478638e-01 -6.6623557e-01 */
    { 0xbf285c8b,0xbf34c367 }, /* 481: -6.5766209e-01 -7.0610660e-01 */
    { 0x4005a68d,0x407e4f84 }, /* 482:  2.0882905e+00  3.9736032e+00 */
    { 0x3ff57bae,0x405518f3 }, /* 483:  1.9178369e+00  3.3296478e+00 */
    { 0xbf32692b,0xbf413531 }, /* 484: -6.9691724e-01 -7.5471789e-01 */
    { 0xbf9be9a1,0xbfc56e1f }, /* 485: -1.2180673e+00 -1.5424231e+00 */
    { 0xc0092599,0xc08682fc }, /* 486: -2.1429198e+00 -4.2034893e+00 */
    { 0x400b468b,0x408b2f8d }, /* 487:  2.1761806e+00  4.3495545e+00 */
    { 0xbfcb5a0a,0xc0162dfa }, /* 488: -1.5886853e+00 -2.3465562e+00 */
    { 0x3ede3ea5,0x3ee54a38 }, /* 489:  4.3407169e-01  4.4783187e-01 */
    { 0x3ec1441a,0x3ec5e371 }, /* 490:  3.7747270e-01  3.8650087e-01 */
    { 0xbfe9bb4b,0xc0418a3c }, /* 491: -1.8260282e+00 -3.0240622e+00 */
    { 0x3f05cab8,0x3f0bf756 }, /* 492:  5.2262449e-01  5.4674280e-01 */
    { 0x3f07c4d6,0x3f0e393c }, /* 493:  5.3034723e-01  5.5556083e-01 */
    { 0x40e343c6,0x4417cd76 }, /* 494:  7.1020231e+00  6.0721033e+02 */
    { 0x4267a38d,0x68bae465 }, /* 495:  5.7909718e+01  7.0605864e+24 */
    { 0xc095719b,0xc25566e3 }, /* 496: -4.6701179e+00 -5.3350475e+01 */
    { 0x3f9a077b,0x3fc1fd44 }, /* 497:  1.2033533e+00  1.5155416e+00 */
    { 0x3e8a3341,0x3e8be26f }, /* 498:  2.6992229e-01  2.7321193e-01 */
    { 0x3f5cda0a,0x3f7948ce }, /* 499:  8.6270201e-01  9.7376716e-01 */
    { 0x403478b0,0x4105b84e }, /* 500:  2.8198662e+00  8.3574963e+00 */
};

void x_exit (int index)
{
#ifndef	__AVR__
    fprintf (stderr, "t[%d]:  %#lx\n", index - 1, v.lo);
#endif
    exit (index ? index : -1);
}

int main ()
{
    union lofl_u x, z;
    unsigned long v1, z1, r;
    int i;
    
    for (i = 0; i < (int) (sizeof(t) / sizeof(t[0])); i++) {
	x.lo = pgm_read_dword (& t[i].x);
	z.lo = pgm_read_dword (& t[i].z);
	v.fl = sinh (x.fl);
	
	v1 = (v.lo < 0) ? (unsigned long)~(v.lo) : v.lo + 0x80000000;
	z1 = (z.lo < 0) ? (unsigned long)~(z.lo) : z.lo + 0x80000000;
	r = (v1 >= z1) ? v1 - z1 : z1 - v1;
	
	if (r > 78) x_exit (i+1);
    }
    return 0;
}
