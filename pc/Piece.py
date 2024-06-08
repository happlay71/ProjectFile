def collect_coins(board):
    if not board:
        return 0, []

    m, n = len(board), len(board[0])
    dp = [[0] * n for _ in range(m)]
    path = [[""] * n for _ in range(m)]

    # 动态规划求解
    for i in range(1, m):
        for j in range(1, n):
            dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]) + board[i][j]
            if dp[i - 1][j] > dp[i][j - 1]:
                path[i][j] = path[i - 1][j] + "->" + str(board[i][j])
            else:
                path[i][j] = path[i][j - 1] + "->" + str(board[i][j])

    return dp[-1][-1], path[-1][-1].split("->")


# 测试
board = [
    [0, 3, 1, 1],
    [2, 0, 0, 4],
    [1, 5, 3, 1]
]
max_coins, max_path = collect_coins(board)
print("最大硬币数:", max_coins)
print("路径:", max_path)
