#include "header/SeqList.h"

int main() 
{
    int number;
    SqList L;
    SqList FL;
    SqList NL;
    ElemType size;
    ElemType i;
    ElemType e;
    ElemType pre;
    ElemType next;
    ElemType j;
    ElemType q;
    ElemType p;
    ElemType del;
    bool flag = true;

    while (true) {
        printf("------------------顺序栈-----------------\n");
        printf("\t\t1.创建表\n");
        printf("\t\t2.存入数据\n");
        printf("\t\t3.取值\n");
        printf("\t\t4.取位置\n");
        printf("\t\t5.取前驱\n");
        printf("\t\t6.取后继\n");
        printf("\t\t7.插入数据\n");
        printf("\t\t8.删除数据\n");
        printf("\t\t9.清空表\n");
        printf("\t\t10.销毁表\n");
        printf("\t\t11.遍历表\n");
        printf("\t\t12.选择表\n");
        printf("\t\t13.求交集(请返回1表操作)\n");
        printf("\t\t14.求并集(请返回1表操作)\n");
        printf("\t\t退出请按0\n");
        printf("请选择：");
        scanf("%d", &number);
        
        switch (number) {
            case 1: 
                printf("请输入你想创建的顺序表的大小：");
                scanf("%d", &size);
                initList(L, size);
                break;
            case 2:
                setList(L); break;
            case 3:
                printf("请输入想要获取的位置，将返回对应的值：");
                scanf("%d", &i);
                printf("获取值%d\n", getList(L, i, e));
                break;
            case 4:
                printf("请输入值，将返回对应的位置：");
                scanf("%d", &i);
                printf("获取位置%d\n", locateElem(L, i));
                break;
            case 5:
                printf("请输入想要获取哪个数据的前驱：");
                scanf("%d", &pre);
                printf("前驱为%d\n", priorElem(L, pre, j));
                break;
            case 6:
                printf("请输入想要获取哪个数据的后继：");
                scanf("%d", &pre);
                printf("后继为%d\n", nextElem(L, next, j));
                break;
            case 7:
                printf("请输入要插入的位置");
                scanf("%d", &q);
                printf("请输入要插入的值");
                scanf("%d", &p);
                insertList(L, q, p);
                break;
            case 8:
                printf("请输入想要删除的数据的位置：");
                scanf("%d", &del);
                deleteList(L, del);
                break;
            case 9:
                clearList(L);
                if (clearList(L)) printf("清除成功！\n");
                else printf("清除失败！\n");
                break;
            case 10:
                destroyList(L);
                if (destroyList(L)) printf("销毁成功！\n");
                else printf("销毁失败！\n");
                break;
            case 11:
                if (traverseList(L) == 0) printf("该表为空！\n");
                break;
            case 12:
                if (L.elem) {
                    printf("请选择FL表(1)或NL表(2)：");
                    int s;
                    scanf("%d", &s);
                    if (s == 1) {
                        copy(NL, L);
                        L = FL;
                    }
                    else if (s == 2) {
                        copy(FL, L);
                        L = NL;
                    }
                }
                
                break;
            case 13:
                intersection(FL, NL);
                break;
            case 14:
                mergeLists(FL, NL);
        }
        
        if (number == 0) break;
    }
    
    return 0;
}