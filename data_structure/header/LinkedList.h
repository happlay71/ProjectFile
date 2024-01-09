#include <stdio.h>
#include <stdlib.h>
#include <conio.h>  // 控制台输出
#include <string.h>

#define OK 1;
#define ERROR 0;

typedef int ElemType;
typedef int status;

// 链表
typedef struct LNode{
    ElemType data;
    struct LNode *next;
}LNode, *LkList;

// 初始化链表
status initList(LkList &L) {
    // 构造一个空的单链表
    L = (LNode*)malloc(sizeof(LNode));  // 头节点
    if (!L) return ERROR;
    L->next = NULL;  // 头指针
    return OK;
}

// 销毁表
status destroyList(LkList &L){
    LkList p = L;
    for(;;) {
        if (p == NULL) break;
        LkList temp = p;
        p = p->next;
        free(temp);
    }
    L = NULL;  // 防止野指针
    return OK;
}

// 清空表
status clearList(LkList &L) {
    LkList p = L->next;
    if (L) {
        for (;;) {
            if (p->next == NULL) return OK;
            LkList temp = p;
            p = p->next;
            temp->data = -1;
        }
    }
    L->next = NULL;
    return OK;
}

// 存数据(尾插法)
status setList(LkList &L) {
    if (!L) return ERROR;
    ElemType num;
    LkList tail = L;
    char i;
    bool flag = false;
    for (;;) {
        if (flag) {
            printf("结束请按Y(回车继续):");
            i = getchar();
            if (i == 'Y' || i == 'y') break;
        }

        LkList dat = (LNode*)malloc(sizeof(LNode));
        printf("请存入整数：");
        scanf("%d", &num);
        dat->data = num;
        dat->next = NULL;  // 将新数据指向的数据域设为空
        tail->next = dat;  // 将结尾指向的指针域设为新数据
        tail = dat;  // 将新数据设为结尾
        getchar();  // 为了防止存入整数后的回车被i存入
        flag = true;
    }
    return OK;
}

// 取值
status getList(LkList &L, int i, ElemType &e) {
    if (!L || i < 1) return ERROR;

    LkList p = L;
    int j = 0;
    while (p && j < i) {
        p = p->next;
        ++j;
    }
    if (!p) return ERROR;
    e = p->data;
    return OK;
}

// 取位置
status getLocate(LkList &L, int &i, ElemType e) {
    if (!L) return ERROR;

    LkList p = L->next;
    int j = 1;
    bool flag = false;
    while (p) {
        if (e == p->data) {
            i = j;
            flag = true;
        }
        p = p->next;
        j++;
    }
    if (!flag) return ERROR;  // 判断没有匹配的数据
    return OK;
}

// 插入数据
status insertList(LkList &L, int i, ElemType e) {
    if (!L) return ERROR;
    
    LkList p = L;
    int j = 0;
    while (p && j < i - 1) {
        p = p->next;
        j++;
    }
    if (!p || j > i - 1) return ERROR;
    LkList dat = (LNode*)malloc(sizeof(LNode));
    dat->data = e;
    dat->next = p->next;
    p->next = dat;
    return OK;
}

// 删除数据
status deleteList(LkList &L, int i) {
    if (!L || i < 1) {
        printf("删除失败！");
        return ERROR;
    }

    LkList p = L;
    LkList q;
    int j = 0;
    while (p->next && j < i - 1) {
        p = p->next;
        j++;
    }
    if (!(p->next) || j > i - 1) {
        printf("删除失败！");
        return ERROR;
    }
    q = p->next;
    p->next = q->next;
    free(q);
    printf("删除成功！");
    return OK;
}

// 遍历链表
status traverseList(LkList L) {
    if (!L->next) return ERROR;
    LkList p = L->next;
    for (;;) {
        if (p == NULL) break;
        printf("%d ", p->data);
        p = p->next;
    }
    printf("\n");
    return OK;
}

// 求交集
status intersection(LkList FL, LkList NL) {
    if (!FL || !NL) {
        printf("链表为空，无法找到交集。\n");
        return ERROR;
    }

    LkList p1 = FL->next;
    printf("交集为：");
    while (p1) {
        LkList p2 = NL->next;
        while (p2) {
            if (p1->data == p2->data) {
                printf("%d ", p1->data);
                break; // 找到一个匹配后，不再查找第二个链表
            }
            p2 = p2->next;
        }
        p1 = p1->next;
    }
    printf("\n");
    return OK;
}

// 求并集
status mergeLists(LkList &FL, LkList &NL) {
    if (!FL || !NL) {
        printf("链表为空，无法找到并集。\n");
        return ERROR;
    }

    LkList p1 = FL->next;
    LkList p2 = NL->next;

    printf("并集为：");
    while (p1 != NULL && p2 != NULL) {
        if (p1->data < p2->data) {
            printf("%d ", p1->data);
            p1 = p1->next;
        } else if (p1->data > p2->data) {
            printf("%d ", p2->data);
            p2 = p2->next;
        } else {
            printf("%d ", p1->data);
            p1 = p1->next;
            p2 = p2->next;
        }
    }

    while (p1 != NULL) {
        printf("%d ", p1->data);
        p1 = p1->next;
    }

    while (p2 != NULL) {
        printf("%d ", p2->data);
        p2 = p2->next;
    }
    printf("\n");
    return OK;
}


// 复制表
status copyList(LkList &destination, LkList source) {
    if (!source) {
        printf("源链表为空，无法复制。\n");
        return ERROR;
    }

    initList(destination);
    LkList sourcePtr = source->next;
    LkList destPtr = destination;

    while (sourcePtr) {
        LkList newNode = (LNode*)malloc(sizeof(LNode));
        newNode->data = sourcePtr->data;
        newNode->next = NULL;

        destPtr->next = newNode;
        destPtr = newNode;

        sourcePtr = sourcePtr->next;
    }

    return OK;
}


