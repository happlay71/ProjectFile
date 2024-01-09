#ifndef LBT
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>  // 控制台输出
#include <string.h>

#define OK 1
#define ERROR 0 

typedef char TElemType;

typedef struct BiTNode {
    TElemType data;  // 结点数据域
    struct BiTNode *lchild, *rchild;  // 左孩子，右孩子
}BiTNode, *BiTree;

void initBiTree(BiTree &T);

void createBiTree(BiTree &T);

// 先序
void preOrder(BiTree &T);

// 中序
void midOrder(BiTree &T);

// 后序
void postOrder(BiTree &T);

// 非递归-先序
void noRecursivePre(BiTree &T);

// 非递归-中序
void noRecursivePMid(BiTree &T);

// 求深度
int deep(BiTree T);

// 求叶子节点数
int countLeaves(BiTree T);

#endif
