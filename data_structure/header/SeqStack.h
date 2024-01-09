#include "SeqBinaryTree.h"
#ifndef SS
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>

#define OK 1
#define ERROR 0

typedef char SElemType;
typedef int status;
bool flag = false;

typedef struct {
    SElemType *base;  // 栈底指针
    SElemType *top;  // 栈顶指针
    int stacksize;  // 栈可用的最大容量
}SqStack;

// 初始化
status initStack(SqStack &S, int i) {
    S.base = (SElemType*)malloc(i * sizeof(SElemType));

    if (!S.base) return ERROR;  // 储存失败
    S.top = S.base;  // 初始化top，空栈
    S.stacksize = i;
    flag = true;
    // printf("初始化成功！\n");
    return OK;
}

bool isEmpty(const SqStack &stack) {
    return stack.top == stack.base; // 如果栈顶等于栈底，表示栈为空
}

// 销毁栈
status destroyStack(SqStack &S) {
    if (!flag) {
        printf("栈传入错误！\n");
        return ERROR;
    }

    free(S.base);
    S.stacksize = 0;
    S.base = NULL;
    S.top = NULL;
    printf("销毁成功！\n");
    return OK;
}

// 存数据(入栈)
status push(SqStack &S, SElemType &e) {
    if (!S.base) {
        printf("栈传入错误！\n");
        return ERROR;
    }

    if (S.top - S.base == S.stacksize) {
        printf("栈满！\n");
        return ERROR;
    }
    *S.top = e;
    S.top++;

    // printf("入栈成功！\n");
    return OK;
}

// 删除(出栈)
status pop(SqStack &S, SElemType &e) {
    if (S.base == S.top || !flag) {
        printf("栈为空或不存在！\n");
        return ERROR;
    }

    S.top--;
    e = *S.top;
    return OK;
}

//取栈顶元素
SElemType getTop(SqStack S) {
    if (S.top == S.base || !flag) {
        printf("栈为空或不存在！\n");
        return ERROR;
    }
    
    return *(S.top - 1);
}

// 遍历栈
status traverseStack(SqStack S) {
    if (S.top == S.base || !flag) {
        printf("栈为空或不存在！\n");
        return ERROR;
    }
    
    // 从栈顶逆序遍历
    SElemType *p = S.top - 1;  // 遍历时不应该移动top指针，应用新指针代替
    while (p >= S.base) {
        printf("%c", *p);
        p--;
    }
    printf("\n");

    return OK;
}

#endif