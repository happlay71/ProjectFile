#include "header/HuffmanTree.h"

void CreateHuffmanTree(HuffmanTree &HT, int n) {
    int m;
    int s1, s2;

    if (n <= 1) return;
    m = n * 2 - 1;
    HT = (HuffmanTree)malloc((m + 1) * sizeof(HTNode));
    // 初始化huffman树
    for (int i = 1; i <= m; ++i) {
        HT[i].data = NULL;
        HT[i].parent = 0;
        HT[i].lchild = 0;
        HT[i].rchild = 0;
    }
    // 输入权值
    printf("请输入字符和权值（空格隔开）：\n");
    for (int i = 1; i <= n; ++i) {
        getchar();
        scanf("%c %d", &HT[i].data, &HT[i].weight);
    }
    
    // 创建huffman树
    for (int i = n + 1; i <= m; ++i) {
        Select(HT, i - 1, s1, s2);
        set(HT, i, s1, s2);
        printf("%d %d\n", HT[s1].weight, HT[s2].weight);
        // HT[s1].weight = HT[s2].weight = INT_MAX;
    }

    for (int i = 1; i < m; ++i) {
        printf("%c\n", HT[i].data);
    }
    
}

void set(HuffmanTree &HT, int i, int s1, int s2) {
    HT[s1].parent = i;
    HT[s2].parent = i;
    HT[i].data = '*';
    HT[i].lchild = s1;
    HT[i].rchild = s2;
    HT[i].weight = HT[s1].weight + HT[s2].weight;
}

void Select(HuffmanTree HT, int n, int &s1, int &s2) {
    int min1 = 10000;
    int min2 = 10000;
    s1 = 0;
    s2 = 0;
    for(int j = 1;j <= n;j++){
        if(HT[j].weight < min1 && HT[j].parent == 0){
            s2 = s1;
            min2 = min1;
            s1 = j;
            min1 = HT[j].weight;
        }else if(HT[j].weight < min2 && HT[j].parent == 0){
            s2 = j;
            min2 = HT[j].weight;
        }
    }
}

int main() {
    HuffmanTree HT;
    int n;

    while (true) {
        printf("请输入权值个数：");
        scanf("%d", &n);
        if (n > 1) break;
    }
    
    CreateHuffmanTree(HT, n);

}