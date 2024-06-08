#include <stdio.h>

int main() {
    
    for (int i = 1; i <= 9; i++) 
        {
            for (int j = 1; j <= i; j++) 
                {
                printf("%d*%d=%d ", j, i, j * i);
                }
        printf("\n");
        }
    
    // int i = 1, count = 10, m = 0;
    // float s;
    // int n;

    // while (i <= count)
    // {
    //     scanf("%d", &n);
    //     if (n % 2 != 0) {
    //         s += n;
    //     } else if (m != 0) {
    //         m *= n;
    //     } else {
    //         m = n;
    //     }
    //     i++;
    // }
    // printf("奇数平均值为%f,偶数乘积为%d", s/count, m);
}