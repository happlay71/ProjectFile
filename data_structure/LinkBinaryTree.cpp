// #include "header/LinkBinaryTree.h"
#include "header/LinkStack.h"


void initBiTree(BiTree &T) {
    T = NULL;  // 根节点为空
}

void createBiTree(BiTree &T) {
    TElemType i;

    printf("请输入结点字符（输入#结束）：");
    scanf("%c", &i);
    getchar();
    if (i == '#') {
        T = NULL;
    } else {
        T = (BiTree)malloc(sizeof(BiTNode));
        T->data = i;
        createBiTree(T->lchild);
        createBiTree(T->rchild);
        printf("创建完毕\n");
    }
}

void preOrder(BiTree &T) {
    if (T) {
        printf("%c ", T->data);
        preOrder(T->lchild);
        preOrder(T->rchild);
    }
}

void midOrder(BiTree &T) {
    if (T) {
        midOrder(T->lchild);
        printf("%c ", T->data);
        midOrder(T->rchild);
    }
}

void postOrder(BiTree &T) {
    if (T) {
        postOrder(T->lchild);
        postOrder(T->rchild);
        printf("%c ", T->data);
    }
}

void noRecursivePre(BiTree &T) {
    LinkStack S;
    BiTree q = (BiTree)malloc(sizeof(BiTNode));
    BiTree p = T;
    initStack(S);
    while (p || !isEmpty(S)) {
        if (p) {
            push(S, p);
            printf("%c ", p->data);
            p = p->lchild;
        } else {
            pop(S, q);
            p = q->rchild;
        }
    }
}

void noRecursiveMid(BiTree &T) {
    LinkStack S;
    SElemType e;
    BiTree q = (BiTree)malloc(sizeof(BiTNode));
    BiTree p = T;
    initStack(S);
    while (p || !isEmpty(S)) {
        if (p) {
            push(S, p);
            p = p->lchild;
        } else {
            pop(S, q);
            printf("%c ", q->data);
            p = q->rchild;
        }
    }
}

int deep(BiTree T) {
    if (!T) {
        return ERROR;
    } else {
        int leftDepth = deep(T->lchild);
        int rightDepth = deep(T->rchild);

        // 返回左右子树深度的较大值加1
        return (leftDepth > rightDepth) ? (leftDepth + 1) : (rightDepth + 1);
    }
}

int countLeaves(BiTree T) {
    if (T == NULL) {
        return 0;
    } else if (T->lchild == NULL && T->rchild == NULL) {
        // 叶子节点
        return 1;
    } else {
        // 非叶子节点，递归计算左右子树的叶子节点数量
        return countLeaves(T->lchild) + countLeaves(T->rchild);
    }
}

int main() {
    BiTree T;
    initBiTree(T);
    createBiTree(T);
    printf("先序为：");
    preOrder(T);
    printf("中序为：");
    midOrder(T);
    printf("后序为：");
    postOrder(T);
    printf("非递归先序：");
    noRecursivePre(T);
    printf("非递归中序：");
    noRecursiveMid(T);
    printf("层深：%d ", deep(T));
    printf("叶子结点的个数：%d", countLeaves(T));
}