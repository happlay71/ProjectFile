 #ifndef ALG
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>

#define OK 1
#define ERROR 0 
#define MVNum 100

bool visited[MVNum];
typedef char VerTexType;
typedef int status;

// 边结点
typedef struct ArcNode {
    int adjvex;  // 该顶点对应的下标
    struct ArcNode *nextarc;
    int wight;
}ArcNode;

// 顶点
typedef struct VNode {
    VerTexType data;
    ArcNode *firstarc;
}VNode, AdjList[MVNum];  // 顶点用顺序结构存储

// 邻接表
typedef struct {
    AdjList vertices;
    int vexnum, arcnum;
}ALPGraph;

status createUDG(ALPGraph &G);  // 创建图

status locateVex(ALPGraph &G, VerTexType vex);  // 获取下标

status depthFirst(ALPGraph G, int i);  // 深度优先

void DFS_All(ALPGraph G);  // 非连通图深度优先

status breadthFirst(ALPGraph G, int i);  // 广度优先


#endif