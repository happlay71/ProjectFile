#ifndef AMG
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>


#define OK 1
#define ERROR 0 
#define MaxInt 32767
#define MVNum 100

bool visited[MVNum];
int Vexset[MVNum];  // 辅助数组
typedef char VerTexType;  // 顶点数据类型
typedef int ArcType;  // 权值类型
typedef int status;

// 边集数组
typedef struct {
    VerTexType Head;  // 边的始点
    VerTexType Tail;  // 边的终点
    ArcType lowcost;  // 边上的权值
}Edge;

typedef struct {
    VerTexType vexs[MVNum];  // 顶点表
    ArcType arcs[MVNum][MVNum];
    int vexnum, arcnum;
}AMGraph;

Edge edge[MaxInt];

status createUDM(AMGraph &G);  // 创建图

status locateVex(AMGraph &G, VerTexType vex);  // 获取下标

status depthFirst(AMGraph G, int i);  // 深度优先

void DFS_All(AMGraph G);  // 非连通图深度优先

status breadthFirst(AMGraph G);  // 广度优先

void primMinTree(AMGraph G);  // 最小生成树

void Sort(Edge edge);  // 比较大小

void kruskalTree(AMGraph G);  // 最小生成树

#endif