#include "header/LinkedList.h"

int main() {
    int number;
    LkList FL;
    LkList NL;
    LkList L;
    ElemType e;
    int i;
    ElemType k;
    int n;
    ElemType k1;
    int m;
    int j;
    LkList p = L;

    while (true) {
        printf("------------------链表-----------------\n");
        printf("\t\t1.创建表\n");
        printf("\t\t2.存入数据\n");
        printf("\t\t3.取值\n");
        printf("\t\t4.取位置\n");
        printf("\t\t5.插入数据\n");
        printf("\t\t6.删除数据\n");
        printf("\t\t7.清空表\n");
        printf("\t\t8.销毁表\n");
        printf("\t\t9.遍历表\n");
        printf("\t\t10.选择表\n");
        printf("\t\t11.求交集(请返回1表操作)\n");
        printf("\t\t12.求并集(请返回1表操作)\n");
        printf("\t\t退出请按0\n");
        printf("请选择：");
        scanf("%d", &number);

        switch (number) {
            case 1:
                if (initList(L)) printf("创建成功！\n");
                break;
            case 2:
                setList(L); break;
            case 3:
                printf("\n请输入要查询的位置：");
                scanf("%d", &n);

                if (getList(L, n, k)) {
                    printf("数据为：%d", k);
                } else {
                    printf("位置错误！\n");
                }
                break;
            case 4:
                printf("\n请输入要查询的数据：");
                scanf("%d", &k1);
                
                if (getLocate(L, m, k1)) {
                    printf("该数据在第%d个结点", m);
                } else {
                    printf("数据不存在！\n");
                }
                break;
            case 5:
                printf("\n请输入要插入的位置：");
                scanf("%d", &i);
                printf("请输入要插入的整数：");
                scanf("%d", &e);
                insertList(L, i, e);
                break;
            case 6:
                printf("\n请输入要删除整数的位置：");
                scanf("%d", &j);
                deleteList(L, j);
                break;
            case 7:
                clearList(L);
                if (clearList(L)) printf("清除成功！\n");
                else printf("清除失败！\n");
                break;
            case 8:
                destroyList(L);
                if (destroyList(L)) printf("销毁成功！\n");
                else printf("销毁失败！\n");
                break;
            case 9:
                if (traverseList(L) == 0) printf("该表为空！\n");
                break;
            case 10:
                if (L) {
                    printf("请选择FL表(1)或NL表(2)：");
                    int s;
                    scanf("%d", &s);
                    if (s == 1) {
                        copyList(NL, L);
                        L = FL;
                    }
                    else if (s == 2) {
                        copyList(FL, L);
                        L = NL;
                    }
                }
                break;
            case 11:
                intersection(FL, NL);
                break;
            case 12:
                mergeLists(FL, NL);
        }

        if (number == 0) break;
    }
    return 0;
}
