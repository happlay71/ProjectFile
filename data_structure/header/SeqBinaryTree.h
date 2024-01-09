#include <stdio.h>
#include <stdlib.h>
#include <conio.h>  // 控制台输出
#include <string.h>

#define OK 1
#define ERROR 0

#define MAXSIZE 100
typedef char TElemType;


typedef struct BiTreeNode {
    TElemType data;
} BiTreeNode;

typedef struct SeqBiTree {
    BiTreeNode nodes[MAXSIZE];
    int size;
} SeqBiTree;

void initSeqBiTree(SeqBiTree &T);

void createBiTree(SeqBiTree &T, TElemType *e, int n, int index);

void preOrder(SeqBiTree T, int index);

void midOrder(SeqBiTree T, int index);

void postOrder(SeqBiTree T, int index);

// 非递归-先序
void noRecursivePre(SeqBiTree T);

// 非递归-中序
void noRecursivePMid(SeqBiTree T);

int treeDepth(SeqBiTree T, int index);  // 层深

int countLeaves(SeqBiTree T, int index); // 叶子结点