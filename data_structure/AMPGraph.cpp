#include "header/AMGraph.h"
#include "header/LinkQueue.h"
#include <algorithm>


status createUDM(AMGraph &G) {
    VerTexType v1, v2;
    ArcType w;

    printf("请输入顶点数和边数（空格分开）：");
    scanf("%d %d", &G.vexnum, &G.arcnum);
    getchar();

    printf("请输入各顶点的值（空格分开）：");
    for (int i = 0; i < G.vexnum; i++) {
        scanf("%c", &G.vexs[i]);
        getchar();
    }

    // 初始化邻接矩阵
    for (int i = 0; i < G.vexnum; i++) {
        for (int j = 0; j < G.vexnum; j++) {
            G.arcs[i][j] = 0;
        }
    }

    // 创建邻接矩阵
    for (int k = 0; k < G.arcnum; k++) {
        printf("请输入两个顶点（空格分开）：");
        scanf(" %c %c", &v1, &v2);
        int i = locateVex(G, v1);
        int j = locateVex(G, v2);
        
        printf("请输入权值：");
        scanf("%d", &w);
        G.arcs[i][j] = w;
        G.arcs[j][i] = G.arcs[i][j];

        edge[k] = {v1, v2, w};
    }
    return OK;
}

status locateVex(AMGraph &G, VerTexType vex) {
    if (G.vexnum == NULL) return ERROR;

    for (int i = 0; i < G.vexnum; i++) {
        if (vex == G.vexs[i]) {
            return i;
        }
    }
}

status depthFirst(AMGraph G, int i) {
    if (G.vexnum == NULL) return ERROR;

    int j;
    visited[i] = true;
    printf("%c ", G.vexs[i]);

    for (j = 0; j < G.vexnum; j++) {
        if (G.arcs[i][j] == 1 && !visited[j]) {
            depthFirst(G, j);
        }
    }
}

void DFS_All(AMGraph G)
{
	for (int i=0;i<G.vexnum;i++)
	{
		if (visited[i]==false)
		{
			depthFirst(G, i);
		}
	}
}

status breadthFirst(AMGraph G, int i) {
    if (G.vexnum == 0 || i < 0 || i >= G.vexnum) return ERROR;

    LinkQueue Q;
    QElemType e;
    int j;

    initQueue(Q);
    for (int k = 0; k < G.vexnum; ++k) {
        visited[k] = false; // 初始化访问标记数组
    }

    visited[i] = true;
    printf("%c ", G.vexs[i]); // 访问初始节点
    enQueue(Q, G.vexs[i]); // 将初始节点入队

    while (!isEmpty(Q)) {
        deQueue(Q, e); // 出队一个节点
        for (j = 0; j < G.vexnum; ++j) {
            if (G.arcs[e][j] != 0 && !visited[j]) { // 遍历与当前节点 e 相连的节点
                visited[j] = true;
                printf("%c ", G.vexs[j]); // 访问节点
                enQueue(Q, G.vexs[j]); // 将节点入队
            }
        }
    }

    return OK;
}

void primMinTree(AMGraph G) {
    int min, i, j, k;
    int adjvex[MaxInt];
    int lowcost[MaxInt];
    lowcost[0] = 0;
    adjvex[0] = 0;
    // 初始化
    for (i = 1; i < G.vexnum; i++) {
        lowcost[i] = G.arcs[0][i];
        adjvex[i] = 0;
    }

    for (i = 1; i < G.vexnum; i++) {
        min = MaxInt;
        j = 1;
        k = 0;
        while (j < G.vexnum) {
            if (lowcost[j] != 0 && lowcost[j] < min) {
                min = lowcost[j];
                k = j;  // 记录最小值下标
            }
            j++;
        }

        printf("(%d->%d) ", adjvex[k], k);
        adjvex[k] = 0;

        for (j = 1; j < G.vexnum; ++j) {
            if (lowcost[j] != 0 && G.arcs[k][j] < lowcost[j]) {
                lowcost[j] = G.arcs[k][j];
                adjvex[j] = k;
            }
        }
        
    }
}

void Sort(Edge *edge, AMGraph G) {
    
    int i, j;
    Edge v;
    //排序主体
    for(i = 0; i < G.arcnum - 1; i++)
        for(j = i+1; j < G.arcnum; j ++)
        {
            if(edge[i].lowcost > edge[j].lowcost)//如前面的比后面的大，则交换。
            {
                v = edge[i];
                edge[i] = edge[j];
                edge[j] = v;
            }
        }

}

void kruskalTree(AMGraph G) {
    Sort(edge, G);
    
    int i, v1, v2;
    int vs1, vs2;

    for (i = 0; i < G.vexnum; ++i) {
        Vexset[i] = i;
    }
    for (i = 0; i < G.arcnum; ++i) {
        v1 = locateVex(G, edge[i].Head);
        v2 = locateVex(G, edge[i].Tail);
        vs1 = Vexset[v1];
        vs2 = Vexset[v2];

        if (vs2 != vs1) {
            printf("%c->%c ", edge[i].Head, edge[i].Tail);
            for (int j = 0; j < G.vexnum; ++j) {
                if (Vexset[j] == vs2) Vexset[j] = vs1;
            }
        }
    }
}

int main() {
    AMGraph G;
    createUDM(G);
    printf("深度优先：");
    DFS_All(G);
    printf("广度优先：");
    breadthFirst(G, 0);
    printf(" Prim最小生成树：");
    primMinTree(G);
    printf(" Kruskal最小生成树：");
    kruskalTree(G);    
    
}