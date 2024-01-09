#include "header/LinkQueue.h"

int main() {
    int number;
    int num;
    LinkQueue Q;
    int size;
    QElemType s;
    QElemType e;

    while (true) {
        printf("------------------链队-----------------\n");
        printf("\t\t1.初始化队\n");
        printf("\t\t2.入队\n");
        printf("\t\t3.出队\n");
        printf("\t\t4.取队头元素\n");
        printf("\t\t5.遍历队\n");
        printf("\t\t6.进制转换\n");
        printf("\t\t退出请按0\n");
        printf("请选择：");
        scanf("%d", &number);

        switch(number) {
            case 1: 
                initQueue(Q);
                break;
            case 2:
                printf("请输入入队的数据：");
                scanf("%d", &s);
                enQueue(Q, s);
                break;
            case 3:
                if (deQueue(Q, e))
                printf("出队的元素是：%d\n", e);
                break;
            case 4:
                if (getFront(Q, s))
                printf("队头元素为：%d\n", s);
                break;
            case 5:
                printQueue(Q);
                break;
        }

        if (number == 0) break;
    }

}