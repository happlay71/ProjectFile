#include "header/AMGraph.h"
#include "header/LinkQueue.h"

status createUDM(AMGraph &G) {
    VerTexType v1, v2;

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
        
        G.arcs[i][j] = 1;
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

int main() {
    AMGraph G;
    createUDM(G);
    printf("深度优先：");
    DFS_All(G);
    printf("广度优先：");
    breadthFirst(G, 0);

}