#include <stdio.h>
#include <stdlib.h>
#include <conio.h>  // 控制台输出
#include <string.h>
#include <math.h>
#include "SeqStack.h"

#define OK 1
#define ERROR 0

typedef int status;
SqStack hexadecimal;

// 转换16
SElemType convertDigitToHex(int digit) {
    if (digit >= 0 && digit <= 9) {
        return '0' + digit;
    } else if (digit >= 10 && digit <= 15) {
        return 'A' + (digit - 10);
    } else {
        return '0'; // 返回默认值
    }
}

// 16转
int hexCharToDecimal(char c) {
    if (c >= '0' && c <= '9') {
        return c - '0';
    } else if (c >= 'A' && c <= 'F') {
        return 10 + (c - 'A');
    } else if (c >= 'a' && c <= 'f') {
        return 10 + (c - 'a');
    }
    return -1; // 非法字符
}

// 2转10
status binaryToDecimal(int e) {
    int decimal = 0, i = 0;

    while (e != 0) {
        decimal += (e % 10) * pow(2, i);
        ++i;
        e /= 10;
    }

    return decimal;
}

// 10转2
void decimalToBinary(int decimal) {
    int i = 0;
    initStack(hexadecimal, 100);

    while (decimal > 0) {
        char c = convertDigitToHex(decimal % 2);
        push(hexadecimal, c);  // 获取余数（0或1）
        decimal = decimal / 2;  // 获取商
        i++;
    }

    if (i == 0) {
        printf("Binary: 0\n");
    } else {
        printf("Binary: ");
        traverseStack(hexadecimal);
    }
}

// 10转8
int decimalToOctonary(int decimal) {
    int octal = 0, i = 1;
    while (decimal != 0) {
        octal += (decimal % 8) * i;
        decimal /= 8;
        i *= 10;
    }
    return octal;
}

// 10转16
void decimalToHexadecimal(int decimal) {
    int i = 0;
    initStack(hexadecimal, 100);

    while (decimal != 0) {
        int digit = decimal % 16;
        SElemType c = convertDigitToHex(digit);
        push(hexadecimal, c);
        decimal /= 16;
        ++i;
    }

    traverseStack(hexadecimal);
}

// 2转8
int binaryToOctonary(int e) {
    int octal = 0, decimal, i = 1;
    
    decimal = binaryToDecimal(e);
    
    octal = decimalToOctonary(decimal);

    return octal;
}

// 2转16
void binaryToHexadecimal(int e) {
    int octal = 0, i = 0;
    int decimal;

    decimal = binaryToDecimal(e);

    decimalToHexadecimal(decimal);

}

// 8转10
int octonaryToDecimal(int octal) {
    int decimal = 0;
    int base = 1; // 8^0

    while (octal > 0) {
        int lastDigit = octal % 10; // 获取最右边的数字
        decimal += lastDigit * base;
        octal = octal / 10; // 去掉最右边的数字
        base *= 8; // 递增幂
    }

    return decimal;
}

// 16转10
int hexadecimalToDecimal(char* hex) {
    int decimal = 0;
    int length = strlen(hex);

    for (int i = 0; i < length; i++) {
        int digitValue = hexCharToDecimal(hex[i]);
        if (digitValue == -1) {
            printf("非法字符: %c\n", hex[i]);
            return -1; // 非法字符
        }
        int power = length - i - 1;
        decimal += digitValue * pow(16, power);
    }

    return decimal;
}

// 8转2
void octonaryToBinary(int octonary) {
    int decimal;

    decimal = octonaryToDecimal(octonary);

    decimalToBinary(decimal);
}

// 8转16
void octonaryToHexadecimal(int octonary) {
    int decimal;

    decimal = octonaryToDecimal(octonary);

    decimalToHexadecimal(decimal);
}

// 16转8
int hexadecimalToOctonary(char* hex) {
    int decimal;

    decimal = hexadecimalToDecimal(hex);

    return decimalToOctonary(decimal);
}

// 16转2
void hexadecimalToBinary(char* hex) {
    int decimal;

    decimal = hexadecimalToDecimal(hex);

    decimalToBinary(decimal);
}

