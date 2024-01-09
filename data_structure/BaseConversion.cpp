#include "header/BaseConversion.h"

int main() {
    int number;
    int num;
    SElemType c[100];
    while(true)
    { 
        printf("-----------------各进制之间的转换---------------\n");
        printf("1.二进制转八进制\t|\t2.八进制转二进制\n");
        printf("3.二进制转十进制\t|\t4.十进制转二进制\n");
        printf("5.二进制转十六进制\t|\t6.十六进制转二进制\n");
        printf("7.八进制转十进制\t|\t8.十进制转八进制\n");
        printf("9.八进制转十六进制\t|\t10.十六进制转八进制\n");
        printf("11.十进制转十六进制\t|\t12.十六进制转十进制\n");
        printf("请输入你要选择的功能（退出请按0）:");
        scanf("%d", &number);
        getchar();

        if (number == 6 || number == 10 || number == 12) {
            printf("请输入要转换的16进制数（回车结束）：");
            for (int i = 0;; i++) {
                scanf("%c", &c[i]);
                if (c[i] == '\n') {
                    c[i] = NULL;
                    break;}
            }
        } else if (number == 0) {
            break;
        } else if (number == 1 || number == 3 || number == 5) {
            printf("请输入要转换的2进制数：");
            scanf("%d", &num);
        } else if (number == 2 || number == 7 || number == 9) {
            printf("请输入要转换的8进制数：");
            scanf("%d", &num);
        } else if (number == 4 || number == 8 || number == 11) {
            printf("请输入要转换的10进制数：");
            scanf("%d", &num);
        } else {
            printf("输入错误,请重新输入！\n");
        }

        switch(number)
        {
            case 1:
                //基本操作:二进制转换为八进制
                printf("Octonary：%d\n", binaryToOctonary(num));
                break;
            case 2:
                //八进制转换为二进制
                octonaryToBinary(num);
                break;
            case 3:
                //基本操作:二进制转换为十进制
                printf("Decimal：%d\n", binaryToDecimal(num));
                break;
            case 4:
                //基本操作:十进制转换为二进制
                decimalToBinary(num);
                break;
            case 5:
                //二进制转换为十六进制
                printf("Hexadecimal：");
                binaryToHexadecimal(num);
                break;
            case 6:
                //基本操作:十六进制转换为二进制
                hexadecimalToBinary(c);
                break;
            case 7:
                //基本操作:八进制转换为十进制
                printf("Decimal：%d\n", octonaryToDecimal(num));
                break;
            case 8:
                //基本操作:十进制转换为八进制
                printf("Octonary：%d\n", decimalToOctonary(num));
                break;
            case 9:
                //八进制转换为十六进制
                printf("Hexadecimal：");
                octonaryToHexadecimal(num);
                break;
            case 10:
                //十六进制转换为八进制
                printf("Octonary：%d\n", hexadecimalToOctonary(c));
                break;
            case 11:
                //基本操作:十进制转换为十六进制
                printf("Hexadecimal：");
                decimalToHexadecimal(num);
                break;
            case 12:
                //基本操作:十六进制转换为十进制
                printf("Decimal：%d\n", hexadecimalToDecimal(c));
                break;
        }
    }
    
    return 0;

}