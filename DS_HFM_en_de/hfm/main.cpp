#include "header/hfmTree.h"



int main() {
    int n;
    int num;
    char buff[255];
    char duff[255];
    HuffmanTree HT;
    FILE *fp = NULL;

    n = sizeof(F) / sizeof(F[0]);

    do {
        printf("---------------------菜单栏-------------\n");
        printf("\t\t1.创建空哈夫曼树\n");
        printf("\t\t2.哈夫曼树初始化\n");
        printf("\t\t3.编码并保存\n");
        printf("\t\t4.译码并保存\n");
        printf("\t\t5.打印编码后的文件\n");
        printf("\t\t6.以凹入表显示哈夫曼树\n");
        printf("\t\t7.退出\n");
        printf("请输入功能选项：");
        scanf("%d", &num);

        switch (num) {
            case 1: 
                initHuffmanTree(HT); 
                break;
            case 2:
                buildHuffmanTree(HT, F, n);
                break;
            case 3:
                fp = fopen("E:\\E\\ProjectFile\\DS_HFM_en_de\\ToBeTran.txt", "r");
                fgets(buff, 255, fp);  // 读取后存储的位置，读取最多 255 个字符，文件
                encode(HT, buff, n);
                fclose(fp);
                break;
            case 4:
                // 实现下一次保存前清空里面的内容
                fp = fopen("E:\\E\\ProjectFile\\DS_HFM_en_de\\TextFile.txt", "w");
                fclose(fp);
                fp = fopen("E:\\E\\ProjectFile\\DS_HFM_en_de\\CodeFile.txt", "r");
                // 使用feof函数来检查文件是否到达了文件末尾
                while (!feof(fp))
                {
                    fscanf(fp, "%s", duff);
                    decode(HT, duff, n);
                }
                fclose(fp);
                printf("译码成功，已保存在TextFile.txt文件中！\n");
                break;
            case 5:
                printFilet();
                break;
            case 6:
                displayHuffmanTree();
                break;
            default : 
                printf("感谢使用，再见！\n");
                break;
        }
    } while (num != 7);
    


    return 0;
}