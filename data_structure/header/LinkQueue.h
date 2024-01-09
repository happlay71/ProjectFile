#include <stdio.h>
#include <stdlib.h>
#include <conio.h>  // 控制台输出
#include <string.h>

#define OK 1;
#define ERROR 0;

typedef char QElemType;
typedef int status;

typedef struct QNode{
    QElemType data;
    struct QNode *next;
}QNode, *QueuePtr;

typedef struct{
    QueuePtr front;  // 对头指针
    QueuePtr rear;  // 对尾指针
}LinkQueue;

// 初始化
status initQueue(LinkQueue &Q) {
    Q.front = Q.rear = (QNode*)malloc(sizeof(QNode));
    if (!Q.front) {
        // 头结点分配内存失败
        return ERROR;
    }
    Q.front->next = NULL;
    return OK;
}

bool isEmpty(LinkQueue Q) {
    return Q.front == Q.rear; // 如果队列的头尾指针相等，则队列为空
}

// 入队
status enQueue(LinkQueue &Q, QElemType e) {
    QueuePtr p = (QNode*)malloc(sizeof(QNode));
    if (!p) {
        // 内存分配失败
        return ERROR;
    }

    p->data = e;
    p->next = NULL;

    Q.rear->next = p;  // 插入
    Q.rear = p;  // 更新队尾
    
    return OK;
}

// 出队
status deQueue(LinkQueue &Q, QElemType &e) {
    if (Q.front == Q.rear) {
        // 队列为空
        return ERROR;
    }

    QueuePtr p = Q.front->next; // 获取队头节点
    e = p->data; // 获取队头元素的数据
    Q.front->next = p->next; // 更新队头指针
    free(p); // 释放队头节点

    if (Q.rear == p) {
        // 出队后队列为空，需要更新rear指针
        Q.rear = Q.front;
    }

    return OK;
}

// 获取队头元素值
status getFront(LinkQueue Q, QElemType &e) {
    if (Q.front == Q.rear) {
        // 队列为空
        return ERROR;
    }

    QueuePtr p = Q.front->next; // 获取队头节点
    e = p->data; // 获取队头元素的数据

    return OK;
}

// 遍历并打印链式队列
void printQueue(LinkQueue Q) {
    QueuePtr p = Q.front->next; // 从队头节点的下一个节点开始
    while (p) {
        printf("%c ", p->data); 
        p = p->next; // 移动到下一个节点
    }
    printf("\n"); // 打印完所有元素后换行
}


