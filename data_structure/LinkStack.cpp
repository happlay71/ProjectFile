#include "header/LinkStack.h"

int main() {
    int number;
    LinkStack S;
    SElemType s;
    SElemType e;

    while (true) {
        printf("------------------链栈-----------------\n");
        printf("\t\t1.初始化栈\n");
        printf("\t\t2.入栈\n");
        printf("\t\t3.出栈\n");
        printf("\t\t4.取栈顶元素\n");
        printf("\t\t5.遍历栈\n");
        printf("\t\t退出请按0\n");
        printf("请选择：");
        scanf("%d", &number);

        switch(number) {
            case 1: 
                initStack(S);
                break;
            case 2:
                printf("请输入入栈的数据：");
                scanf("%d", &s);
                push(S, s);
                break;
            case 3:
                if (pop(S, e))
                printf("出栈元素为：%d\n", e);
                break;
            case 4:
                if (getTop)
                printf("栈顶元素为：%d\n", getTop(S));
                break;
            case 5:
                traverseStack(S);
                break;
           
        }

        if (number == 0) break;
    }
}