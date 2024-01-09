#include "header/ALPGraph.h"
#include "header/LinkQueue.h"


status createUDG(ALPGraph &G) {
    VerTexType v1, v2;
    int w;

    printf("请输入顶点数和边数（空格分开）：");
    scanf("%d %d", &G.vexnum, &G.arcnum);
    getchar();

    printf("请输入各顶点的值（空格分开）：");
    for (int i = 0; i < G.vexnum; i++) {
        scanf("%c", &G.vertices[i].data);
        G.vertices[i].firstarc = NULL;
        getchar();
    }

    // 创建邻接表
    for (int k = 0; k < G.arcnum; k++) {
        printf("请输入两个顶点（空格分开）：");
        scanf(" %c %c", &v1, &v2);
        int i = locateVex(G, v1);
        int j = locateVex(G, v2);

        printf("请输入权值：");
        scanf("%d", &w);

        ArcNode *p1 = (ArcNode*)malloc(sizeof(ArcNode));
        ArcNode *p2 = (ArcNode*)malloc(sizeof(ArcNode));
        
        // 先指向的结点在表尾部
        p1->adjvex = j;
        p1->wight = w;
        p1->nextarc = G.vertices[i].firstarc;
        G.vertices[i].firstarc = p1;

        p2->adjvex = i;
        p2->wight = w;
        p2->nextarc = G.vertices[j].firstarc;
        G.vertices[j].firstarc = p2;
    }
    return OK;
}

status locateVex(ALPGraph &G, VerTexType vex) {
    if (G.vexnum == NULL) return ERROR;

    for (int i = 0; i < G.vexnum; i++) {
        if (vex == G.vertices[i].data) {
            return i;
        }
    }
}

status traverse(ALPGraph &G) {
    if (G.vexnum == NULL) return ERROR;

    for (int i = 0; i < G.vexnum; i++) {
        printf("%c-> ", G.vertices[i].data);  // 打印当前顶点

        ArcNode *p = G.vertices[i].firstarc;

        while (p) {
            printf("%c(%d) ", G.vertices[p->adjvex].data, p->wight);  // 打印邻接顶点及权值
            p = p->nextarc;
        }

        printf("\n");
    }

    return OK;
}

status depthFirst(ALPGraph G, int i) {
    if (G.vexnum == 0 || i < 0 || i >= G.vexnum) return ERROR;
    visited[i] = true;
    printf("%c ", G.vertices[i].data);

    ArcNode *p = G.vertices[i].firstarc;
    while (p) {
        if (visited[p->adjvex] == false) depthFirst(G, p->adjvex);
        p = p->nextarc;
    }
    return OK;
}


void DFS_All(ALPGraph G)
{
	for (int i=0;i<G.vexnum;i++)
	{
		if (visited[i]==false)
		{
			depthFirst(G, i);
		}
	}
}

status breadthFirst(ALPGraph G, int i) {
    if (G.vexnum == 0 || i < 0 || i >= G.vexnum) return ERROR;

    LinkQueue Q;
    QElemType e;
    int j;

    initQueue(Q);
    for (int k = 0; k < G.vexnum; ++k) {
        visited[k] = false; // 初始化访问标记数组
    }

    visited[i] = true;
    printf("%c ", G.vertices[i].data); // 访问初始节点
    enQueue(Q, G.vertices[i].data); // 将初始节点入队

    while (!isEmpty(Q)) {
        deQueue(Q, e); // 出队一个节点
        int l = locateVex(G, e);
        ArcNode *p = G.vertices[l].firstarc;

        while (p != NULL) {
            int x = p->adjvex;
            
            if (!visited[x]) {
                visited[x] = true;
                printf("%c ", G.vertices[x].data);
                enQueue(Q, G.vertices[x].data);
            }

            p = p->nextarc;
        }
    }

    return OK;
}

int main() {
    ALPGraph G;

    createUDG(G);
    traverse(G);
    printf("深度优先：");
    DFS_All(G);
    printf("广度优先：");
    breadthFirst(G, 0);

}