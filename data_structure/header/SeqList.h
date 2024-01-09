#include <stdio.h>
#include <stdlib.h>
#include <conio.h>  // 控制台输出
#include <string.h>

#define OK 1;
#define ERROR 0;

typedef int ElemType;
typedef int status;

// 顺序表
typedef struct {
    int length;
    int size;
    ElemType *elem;
}SqList;

// 初始化表
status initList(SqList &L, ElemType i){
    L.elem = (ElemType*)malloc(i*sizeof(ElemType));
    L.length = 0;
    L.size = i; // 要的空间的大小
    return OK;
}

// 销毁是指销毁表的全部结构，置空只是把length设为0，将数据清空
// 销毁表
status destroyList(SqList &L){
    free(L.elem);  // 将调用的空间释放出来
    L.elem = NULL;  // 防止野指针
    L.length = 0;
    L.size = 0;
    return OK;
}

// 置空
status clearList(SqList &L){
    L.length = 0;
    return OK;
}

// 检查是否为空
status emptyList(SqList &L){
    if (L.elem && L.length == 0) return 1;
    else return 0;
}

// 查看元素个数
status lengthList(SqList &L){
    return L.length;
}

// 存储数据
status setList(SqList &L){
    int i;
    int num;
    for (i = 0; i < L.size; i++) {
        printf("请存入第%d个整数：", i + 1);
        scanf("%d", &num);
        L.elem[i] = num;
        L.length++;
    }
    return OK;
}

// 获取数据
status getList(SqList &L, int i, ElemType &e){
    if (i <= 0 || i > L.length) return ERROR;
    e = L.elem[i - 1];
    return e;
}

// 取位置
status locateElem(SqList &L, ElemType e){
    if (L.elem) {
        for (int i = 0; i < L.length; i++) {
            if (L.elem[i] == e) return i + 1;
        }
    }
    return OK;
}

// 取前驱
status priorElem(SqList &L, ElemType cur_e, ElemType &pre_e) {
    if (L.elem) {
        for (int i = 0; i < L.length; i++) {
            if (L.elem[i] == cur_e && i != 0) return pre_e = L.elem[i - 1];
        }
    }
    return ERROR;
}

// 取后继
status nextElem(SqList &L, ElemType cur_e, ElemType &next_e) {
    if (L.elem) {
        for (int i = 0; i < L.length; i++) {
            if (L.elem[i] == cur_e && i != L.length - 1) return next_e = L.elem[i + 1];
        }
    }
    return ERROR;
}

// 插入数据
status insertList(SqList &L, ElemType i, ElemType e) {
    ElemType *newbase;
    if (!L.elem || (i < 1 || i > L.length)) return ERROR;
    L.length++;
    if (L.length > L.size) {
        newbase = (ElemType*)realloc(L.elem, (L.size + 10) * sizeof(ElemType));  // 创建新数据
        if(!newbase) return ERROR;
        L.elem = newbase;
        L.size += 10;
    }
    for (int j = L.length - 1; j > 0; j--) {
        if (i - 1 == j) {
            L.elem[j] = e;
            break;
            }
        L.elem[j] = L.elem[j - 1];
    }
    return OK;
}

// 删除数据
status deleteList(SqList &L, ElemType i) {
    if (!L.elem || (i < 1 || i > L.length)) return ERROR;
    for (int j = 0; j < L.length - 1; j++) {
        if ( j >= i - 1) {
            L.elem[j] = L.elem[j + 1];
        }
    }
    L.length--;
    return OK;
}

// 遍历表
status traverseList(SqList &L) {
    if (L.length == 0) return ERROR;
    for (int i = 0; i < L.length; i++) {
        printf("%d\n", L.elem[i]);
    }
    return OK;
}

// 求交集
status intersection(SqList &FL, SqList &NL) {
    if (!FL.elem || !NL.elem) {
        printf("传入表出错！\n");
        return ERROR;
    }
    printf("交集为：");
    for (int i = 0; i < FL.length; i++) {
        if (FL.elem[i] == NL.elem[i]) {
            printf("%d ", FL.elem[i]);
        }
    }
    printf("\n");
    return OK;
}

// 求并集
status mergeLists(SqList &FL, SqList &NL) {
    if (!FL.elem || !NL.elem) {
        printf("传入表出错！\n");
        return ERROR;
    }

    SqList result;
    result.length = FL.length;
    result.size = FL.size + NL.size;
    result.elem = (ElemType*)malloc((FL.size + NL.size) * sizeof(ElemType)); // 初始化结果表

    for (int i = 0; i < FL.length; i++) {
        result.elem[i] = FL.elem[i];
    }

    for (int j = 0; j < NL.length; j++) {
        // 检查是否已存在于结果表中，避免重复添加
        int exists = 0;
        for (int k = 0; k < result.length; k++) {
            if (NL.elem[j] == result.elem[k]) {
                exists = 1;
                break;
            }
        }

        if (!exists) {
            insertList(result, FL.length, NL.elem[j]);
        }
    }

    printf("并集为：");
    for (int i = 0; i < result.length; i++) {
        printf("%d ", result.elem[i]);
    }
    printf("\n");

}

// 复制表
status copy(SqList &L, SqList &FL) {
    // 复制 FL 表到 L 表
    L.length = FL.length;
    L.size = FL.size;
    L.elem = (ElemType*)malloc(L.size * sizeof(ElemType));
    for (int i = 0; i < L.length; i++) {
        L.elem[i] = FL.elem[i];
    }
}