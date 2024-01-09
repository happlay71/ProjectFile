#include "header/SeqStack.h"

int main() {
    int number;
    int num;
    SqStack S;
    int size;
    SElemType s;
    SElemType e;

    while (true) {
        printf("------------------顺序栈-----------------\n");
        printf("\t\t1.初始化栈\n");
        printf("\t\t2.入栈\n");
        printf("\t\t3.出栈\n");
        printf("\t\t4.取栈顶元素\n");
        printf("\t\t5.遍历栈\n");
        printf("\t\t6.进制转换\n");
        printf("\t\t退出请按0\n");
        printf("请选择：");
        scanf("%d", &number);
        getchar();

        switch(number) {
            case 1: 
                printf("请输入你想创建的顺序栈的大小：");
                scanf("%d", &size);
                initStack(S, size);
                break;
            case 2:
                printf("请输入入栈的数据(结束请按回车)：");
                for (int i = 0;; i++) {
                    scanf("%c", &s);
                    if (s == '\n') break;
                    push(S, s);
                }
                break;
            case 3:
                if (pop(S, e))
                printf("出栈的元素是：%c\n", e);
                break;
            case 4:
                if (getTop(S))
                printf("栈顶元素为：%c\n", getTop(S));
                break;
            case 5:
                traverseStack(S);
                break;

        }

        if (number == 0) break;
    }

}