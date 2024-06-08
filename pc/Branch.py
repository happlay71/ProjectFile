class Item:
    def __init__(self, weight, value):
        self.weight = weight
        self.value = value

class Node:
    def __init__(self, level, profit, weight):
        self.level = level
        self.profit = profit
        self.weight = weight

def knapsack_branch(items, capacity):
    n = len(items)
    items.sort(key=lambda x: x.value / x.weight, reverse=True)  # 按单位价值排序

    max_profit = 0
    queue = []

    root = Node(-1, 0, 0)
    queue.append(root)

    while queue:
        current_node = queue.pop(0)

        if current_node.level == n - 1:
            continue

        next_level = current_node.level + 1
        next_weight = current_node.weight + items[next_level].weight

        # 如果添加下一个物品不超过容量，计算添加和不添加的最大价值
        if next_weight <= capacity:
            # 添加下一个物品
            next_profit = current_node.profit + items[next_level].value
            max_profit = max(max_profit, next_profit)
            queue.append(Node(next_level, next_profit, next_weight))

            # 不添加下一个物品
            bound = calculate_bound(next_level, n, items, capacity, next_weight, next_profit)
            if bound > max_profit:
                queue.append(Node(next_level, current_node.profit, current_node.weight))

    return max_profit

def calculate_bound(current_level, n, items, capacity, weight, profit):
    if weight >= capacity:
        return 0

    bound = profit
    total_weight = weight
    j = current_level + 1

    while j < n and total_weight + items[j].weight <= capacity:
        total_weight += items[j].weight
        bound += items[j].value
        j += 1

    if j < n:
        bound += (capacity - total_weight) * (items[j].value / items[j].weight)

    return bound

# 示例
items = [Item(2, 3), Item(3, 4), Item(4, 8), Item(5, 6)]
capacity = 5
max_profit = knapsack_branch(items, capacity)
print("最大价值:", max_profit)
