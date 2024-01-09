#include "../header/hfmTree.h"


FreqList F[27] = {
            {' ', 186}, {'A', 64}, {'B', 13}, {'C', 22},
            {'D', 32}, {'E', 103}, {'F', 21}, {'G', 15},
            {'H', 47}, {'I', 57}, {'J', 1}, {'K', 5},
            {'L', 32}, {'M', 20}, {'N', 57}, {'O', 63},
            {'P', 15}, {'Q', 1}, {'R', 48}, {'S', 51},
            {'T', 80}, {'U', 23}, {'V', 8}, {'W', 18},
            {'X', 1}, {'Y', 16}, {'Z', 1}
};

void Select(HuffmanTree HT, int n, int &s1, int &s2) {
    int min1 = INT_MAX;
    int min2 = INT_MAX;
    s1 = 0;
    s2 = 0;
    for(int j = 1; j <= n; j++){
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

void huffmanTreeCode(HuffmanTree HT, int n) {
    if (!HT) return;

    FILE *fp = NULL;

    fp = fopen("E:\\E\\ProjectFile\\DS_HFM_en_de\\hfmTree.txt", "w+");
      
    if (!fp) {
        printf("Error opening file!\n");
        return;
    }

    char code[n];  // 存储编码结果
    int start, c, f;
    code[n - 1] = '\0'; // 结束标志

    // 从哈夫曼树存储的起始位开始，因为都是叶子结点
    for (int i = 1; i <= n; ++i) {
        start = n - 1;
        c = i;
        f = HT[i].parent;

        // 不是左子树就是右子树，类似后序编码
        while (f != 0) {
            if (HT[f].lchild == c) {
                code[--start] = '0';
            } else {
                code[--start] = '1';
            }
            c = f;
            f = HT[f].parent;
        }

        fprintf(fp, "%c\t%s\t%d\t%d\t%d\t%d\n", HT[i].data, &code[start], HT[i].weight, HT[i].parent, HT[i].lchild, HT[i].rchild);
    }

    fclose(fp);
    printf("已保存在hfmTree.txt中！\n");
}

void initHuffmanTree(HuffmanTree &HT) {
    HT = NULL;  // 建立空树
    printf("空树创建成功！\n");
}

void buildHuffmanTree(HuffmanTree &HT, FreqList *F, int n) {
    int m;
    int s1, s2;

    if (n <= 1) return;
    m = n * 2 - 1;
    HT = (HuffmanTree)malloc((m + 1) * sizeof(HTNode));

    for (int i = 1; i <= m; ++i) {
        HT[i].parent = 0;
        HT[i].lchild = 0;
        HT[i].rchild = 0;
    }

    // 以字符及频率为基础创建哈夫曼树
    for (int i = 1; i <= n; ++i) {
        HT[i].data = F[i - 1].data;
        HT[i].weight = F[i - 1].weight;
    }

    for (int i = n + 1; i <= m; ++i) {
        Select(HT, i - 1, s1, s2);
        HT[s1].parent = i;
        HT[s2].parent = i;
        HT[i].data = '*';
        HT[i].lchild = s1;
        HT[i].rchild = s2;
        HT[i].weight = HT[s1].weight + HT[s2].weight;
    }
    printf("哈夫曼树创建成功！\n");

    huffmanTreeCode(HT, m);

}

void encode(HuffmanTree HT, const string &input, int n) {
    int m;
    char buff[255];
    string code;
    FILE *fp = NULL;
    FILE *fP_new = NULL;

    m = n * 2 - 1;

    fp = fopen("E:\\E\\ProjectFile\\DS_HFM_en_de\\hfmTree.txt", "r");
    fP_new = fopen("E:\\E\\ProjectFile\\DS_HFM_en_de\\CodeFile.txt", "w+");

    if (!fp || !fP_new) {
        // 处理文件打开失败的情况
        printf("Failed to open file!");
        return;
    }

    // 通过input.length()来获取字符串的长度
    for (int i = 0; i < input.length(); ++i) {
        rewind(fp);  // 让文件从头读取
        while (fgets(buff, 255, fp)) {
            char data;
            
            sscanf(buff, "%c\t%s", &data, &code[0]);

            if (input[i] == data) {
                fprintf(fP_new, " %s", code.c_str());  // 需要转换成C风格的字符串, %s前加空格防止译码时多输出一次结尾字符
                break;
            }
        }
    }

    fclose(fp);
    fclose(fP_new);
    printf("编码成功，已保存在CodeFile.txt文件中！\n");
}

void decode(HuffmanTree HT, const string &input, int n) {
    // printf("%s", input.c_str());
    int m;
    char buff[255];
    FILE *fp = NULL;
    FILE *fP_new = NULL;

    m = n * 2 - 1;

    fp = fopen("E:\\E\\ProjectFile\\DS_HFM_en_de\\hfmTree.txt", "r");
    fP_new = fopen("E:\\E\\ProjectFile\\DS_HFM_en_de\\TextFile.txt", "a+");

    if (!fp || !fP_new) {
        // 处理文件打开失败的情况
        printf("Failed to open file!\n");
        return;
    }

    while (fgets(buff, 255, fp)) {
        char data;
        string code;
        
        sscanf(buff, "%c\t%s", &data, &code[0]);

        // 比较二者是否相等
        if (strcmp(input.c_str(), code.c_str()) == 0) {
            fprintf(fP_new, "%c", data);  // 需要转换成C风格的字符串
            break;
        }
    }

    fclose(fp);
    fclose(fP_new);
}

void printFilet() {
    int count = 0;
    int ch;
    FILE *fp = NULL;


    fp = fopen("E:\\E\\ProjectFile\\DS_HFM_en_de\\CodeFile.txt", "r");
    
    if (!fp) {
        // 处理文件打开失败的情况
        printf("Failed to open file!\n");
        return;
    }
    
    while ((ch = fgetc(fp)) != EOF)
    {
        if (ch != ' ') {
            printf("%c", ch);
            count++;
        }
        
        if (count == 50) {
            printf("\n");
            count = 0;
        }
    }
    fclose(fp);
    printf("\n");
    printf("打印完成！\n");
}

void displayHuffmanTree() {
    char buff[255];
    FILE *fp = NULL;
    FILE *fP_new = NULL;


    fp = fopen("E:\\E\\ProjectFile\\DS_HFM_en_de\\hfmTree.txt", "r");
    fP_new = fopen("E:\\E\\ProjectFile\\DS_HFM_en_de\\TreePrint.txt", "w");
    fclose(fP_new);
    fP_new = fopen("E:\\E\\ProjectFile\\DS_HFM_en_de\\TreePrint.txt", "a+");
    
    if (!fp) {
        // 处理文件打开失败的情况
        printf("Failed to open file!\n");
        return;
    }

    while (fgets(buff, 255, fp)) {
        char data;
        string code;
        
        sscanf(buff, "%c\t%s", &data, &code[0]);

        // 多打印1000*的原因是因为编码为空，自动获取编码后面的字符即权值1000
        for (int i = 0; i < strlen(code.c_str()); ++i) {
             if (strlen(code.c_str()) == 4 && data == '*') {
                break; // 如果当前处理的是最后一个字符，且是 '*'，则退出内部循环
            }
            printf("- ");
            fprintf(fP_new, "- ");
        }
        // printf("%s", code.c_str());
        printf("%c\n", data);
        fprintf(fP_new, "%c\n", data);
    }

    fclose(fp);
    fclose(fP_new);
    printf("打印完成，已保存在TreePrint.txt文件中！\n");
}
