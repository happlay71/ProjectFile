#include <stdio.h>
#include <stdlib.h>
#include <conio.h>  // 控制台输出
#include <string.h>

#define MAXLEN 10

typedef struct {
    char ch[MAXLEN + 1];  // 字符数组，+1 用于存储字符串结束符 '\0'
    int length;          // 串的长度
} SString;

// 初始化串
void initSString(SString *s, char *str) {
    if (s != NULL && str != NULL) {
        strncpy(s->ch, str, MAXLEN);
        s->ch[MAXLEN] = '\0'; // 确保字符串结尾
        s->length = strlen(s->ch);
    }
}


// Brute-Force 模式匹配算法
int index_BF(SString S, SString T, int pos) {
    int i = pos, j = 0;

    while (i < S.length && j < T.length) {
        // printf("%c", S.ch[i]);
        // printf("%c", T.ch[j]);
        if (S.ch[i] == T.ch[j]) {
            ++i;
            ++j;
        } else {
            i = i - j + 1;
            j = 0;
        }
    }

    return i - T.length;
    
}