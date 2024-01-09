#include "header/BruteForce.h"

int main() {
    SString s, t;

    // 初始化串
    initSString(&s, "anbdsadsa");
    initSString(&t, "dsa");

    int pos = index_BF(s, t, 0);

    if (pos > 0) {
        printf("位置是: %d\n", pos + 1);
    } else {
        printf("不存在\n");
    }

    return 0;
}