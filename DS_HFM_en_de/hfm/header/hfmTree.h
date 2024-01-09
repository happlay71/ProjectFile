#ifndef HT
#include "../header/freqList.h"
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>
#include <iomanip>
#include <iostream>
#include <fstream>
#include <climits>  // 导入有最大值的库

using namespace std;


#define OK 1
#define ERROR 0

typedef struct {
    char data;
    int weight;
    int parent, lchild, rchild;
}HTNode, *HuffmanTree;

void Select(HuffmanTree HT, int n, int &s1, int &s2);  // 选出两个最小值

void huffmanTreeCode(HuffmanTree HT, int n);  // 构建哈夫曼编码

void initHuffmanTree(HuffmanTree &HT);  // 构建空的哈夫曼树

void buildHuffmanTree(HuffmanTree &HT, FreqList *F, int n);  // 根据字符频率初始化哈夫曼树

void encode(HuffmanTree HT, const string &input, int n);  // 在读取文件内容获得字符串后进行编码，const可防止函数修改传入的参数

void decode(HuffmanTree HT, const string &input, int n);  // 在读取文件内容获得字符串后进行译码

void printFilet();  // 将文件内容直接输出在终端

void displayHuffmanTree();  // 以凹入表的形式展示哈夫曼树


/*-------------考虑中-----------------
getFrequency(Message)：
          初始条件：Message 是待编码的字符串。
          操作结果：计算字符串中各字符出现的频率。
compressFile(InputFile, OutputFile)：
          初始条件：InputFile 是待压缩的文件。
          操作结果：使用哈夫曼编码对文件进行压缩。
decompressFile(InputFile, OutputFile)：
          初始条件：InputFile 是待解压的文件。
          操作结果：使用哈夫曼编码对文件进行解压。
*/
#endif