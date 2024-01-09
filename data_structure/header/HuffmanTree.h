#ifndef HT
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>  // 控制台输出
#include <string.h>

#define OK 1
#define ERROR 0 

typedef struct {
    char data;
    int weight;
    int parent, lchild, rchild;
}HTNode, *HuffmanTree;

void CreateHuffmanTree(HuffmanTree &HT, int n);

// 选出两个最小值
void Select(HuffmanTree HT, int n, int &s1, int &s2);

// 在森林里添加新节点
void set(HuffmanTree &HT, int i, int s1, int s2);

#endif