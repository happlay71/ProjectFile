#include "header/SeqStack.h"



void initSeqBiTree(SeqBiTree &T) {
    T.size = 0;
}

void createSeqBiTree(SeqBiTree &T, TElemType *e, int n, int index) {
    if (index > n) {
        return;
    }

    T.nodes[index].data = e[index-1];
    T.size++;

    createSeqBiTree(T, e, n, 2*index);    
    createSeqBiTree(T, e, n, 2*index+1);  
}

void preOrder(SeqBiTree T, int index) {
    if (index <= T.size) {
        printf("%c ", T.nodes[index].data);
        preOrder(T, 2*index);
        preOrder(T, 2*index+1);
    }
}

void midOrder(SeqBiTree T, int index) {
    if (index <= T.size) {
        midOrder(T, 2*index);
        printf("%c ", T.nodes[index].data);
        midOrder(T, 2*index+1);
    }
}

void postOrder(SeqBiTree T, int index) {
    if (index <= T.size) {
        postOrder(T, 2*index);
        postOrder(T, 2*index+1);
        printf("%c ", T.nodes[index].data);
    }
}

void noRecursivePre(SeqBiTree T) {

    if (T.size <= 0) return;

    printf("非递归先序：");

    SqStack nodeStack;
    initStack(nodeStack, T.size);
    int index = 1;
    bool flag = false;
    SElemType e;

    while (index <= T.size || !isEmpty(nodeStack)) {
        if (!flag) {
            while (index <= T.size) {
                printf("%c ", T.nodes[index].data);
                if (index != 1)  // 不把A压栈
                push(nodeStack, T.nodes[index].data);
                if (index != T.size) {
                    index = 2 * index;
                } else {
                    pop(nodeStack, e);
                    flag = true;
                    break;
                }
            }
        }
        

        if (!isEmpty(nodeStack)) {
            pop(nodeStack, e);
            int j = 2;
            while (true) {
                if (e == T.nodes[j].data) {
                    printf("%c ", T.nodes[j + 1].data);
                    break;
                }
                j++;
            }
            index = 2 * (j + 1);  // 修正计算方式，避免重复访问右子树
        } else {
            printf("%c ", T.nodes[index].data);
            push(nodeStack, T.nodes[index].data);
        }
    }
    printf("\n");
}

void noRecursivePMid(SeqBiTree T) {
    if (T.size <= 0) return;

    printf("非递归中序：");

    SqStack nodeStack;
    initStack(nodeStack, T.size);
    int index = 1;
    bool flag = false;
    SElemType e;

    while (index <= T.size || !isEmpty(nodeStack)) {
        while (index <= T.size) {
            push(nodeStack, T.nodes[index].data);  // 将节点的索引入栈
            index = 2 * index;   // 访问左子树
        }

        if (!isEmpty(nodeStack)) {
            pop(nodeStack, e);  // 出栈节点的索引
            printf("%c ", e);  // 访问当前节点
            index = 2 * index + 1;  // 访问右子树
        }
    }

    printf("\n");
}

int treeDepth(SeqBiTree T, int index) {
    if (index >= T.size) {
        return 0;  // 超出树的范围，返回深度0
    }

    int leftDepth = treeDepth(T, 2 * index);  // 计算左子树的深度
    int rightDepth = treeDepth(T, 2 * index + 1);  // 计算右子树的深度

    // 返回左右子树中深度较大的值加1
    return (leftDepth > rightDepth) ? (leftDepth + 1) : (rightDepth + 1);
}

int countLeaves(SeqBiTree T, int index) {
    if (index >= T.size) {
        return 0;  // 超出树的范围，返回0个叶子节点
    }

    // 如果是叶子节点，返回1
    if (2 * index + 1 >= T.size && 2 * index + 2 >= T.size) {
        return 1;
    }

    // 递归计算左右子树中叶子节点的个数
    int leftLeaves = countLeaves(T, 2 * index + 1);
    int rightLeaves = countLeaves(T, 2 * index + 2);

    // 返回左右子树中叶子节点的个数之和
    return leftLeaves + rightLeaves;
}

int main() {
    SeqBiTree T;
    initSeqBiTree(T);
    TElemType e[] = {'A', 'B', 'C', 'D', 'E', 'F','G','H'};
    createSeqBiTree(T, e, 8, 1);

    printf("先序为：");
    preOrder(T, 1);
    printf("中序为：");
    midOrder(T, 1);
    printf("后序为：");
    postOrder(T, 1);

    noRecursivePre(T);
    noRecursivePMid(T);

    printf("树深：%d ", treeDepth(T, 1));
    printf("叶子结点的个数：%d", countLeaves(T, 1));
}