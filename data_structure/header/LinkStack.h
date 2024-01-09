#include "LinkBinaryTree.h"
#ifndef LS
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>

#define OK 1
#define ERROR 0

typedef BiTree SElemType;  // 字符
typedef int status;
bool flag = false;

typedef struct StackNode{
    SElemType data;
    struct StackNode *next;
}StackNode, *LinkStack;

// 初始化链栈
status initStack(LinkStack &S) {
    S = NULL;
    // printf("链栈初始化成功！\n");
    return OK;
}

// 销毁栈
status destroyStack(LinkStack &S) {
    if (!S) {
        printf("栈传入错误！\n");
        return ERROR;
    }

    LinkStack p = S;
    while (p) {
        LinkStack temp = p;
        p = p->next;
        free(temp);
    }

    S = NULL;
    return OK;
}

// 判空
status isEmpty(LinkStack S) {
    return S == NULL;  // 如果栈顶指针为空，表示栈为空
}

// 入栈
status push(LinkStack &S, SElemType e) {

    LinkStack p;
    p = (StackNode*)malloc(sizeof(StackNode));
    p->data = e;
    p->next = S;
    S = p;
    flag = true;
    // printf("入栈成功！\n");
    return OK;
}

// 出栈
status pop(LinkStack &S, SElemType &e) {
    if (!flag || !S) {
        printf("栈为空或不存在！\n");
        return ERROR;
    }

    LinkStack p = S;
    e = S->data;
    S = S->next;
    free(p);
    return OK;
}

// 取栈顶元素
SElemType getTop(LinkStack &S) {
    if (!flag || !S) {
        printf("栈为空或不存在！\n");
        return ERROR;
    }

    return S->data;
}

// 遍历栈
status traverseStack(LinkStack S) {
    if (!flag || !S) {
        printf("栈为空或不存在！\n");
        return ERROR;
    }

    LinkStack p = S;
    while (p) {
        printf("%d ", p->data);
        p = p->next;
    }
    printf("\n");
    return OK;
}

#endif