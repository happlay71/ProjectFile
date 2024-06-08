import heapq

def dijkstra(graph, start):
    # 初始化距离字典
    distances = {node: float('inf') for node in graph}
    distances[start] = 0
    
    # 使用优先队列来存储待访问的节点
    priority_queue = [(0, start)]
    
    while priority_queue:
        # 弹出距离最小的节点
        current_distance, current_node = heapq.heappop(priority_queue)
        
        # 如果当前距离大于已经记录的距离，则忽略
        if current_distance > distances[current_node]:
            continue
        
        # 遍历当前节点的邻居节点
        for neighbor, weight in graph[current_node].items():
            distance = current_distance + weight
            # 更新距离字典和优先队列
            if distance < distances[neighbor]:
                distances[neighbor] = distance
                heapq.heappush(priority_queue, (distance, neighbor))
    
    return distances

# 示例图的邻接表表示
graph = {
    'A': {'B': 6, 'C': 1},
    'B': {'A': 6, 'C': 2, 'D': 2},
    'C': {'A': 1, 'B': 2, 'D': 1},
    'D': {'B': 2, 'C': 1}
}

start_node = 'A'
shortest_distances = dijkstra(graph, start_node)
print("从节点 {} 到其他节点的最短距离:".format(start_node))
for node, distance in shortest_distances.items():
    print("到节点 {} 的最短距离为: {}".format(node, distance))
