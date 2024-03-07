# def multiple_knapsack(weights, values, counts, capacity):
#     n = len(weights)
#     dp = [0] * (capacity + 1)

#     for i in range(n):
#         count = min(counts[i], capacity // weights[i])
#         k = 1
#         while count > 0:
#             # 二进制拆分
#             amount = min(k, count)
#             weight = amount * weights[i]
#             value = amount * values[i]

#             for j in range(capacity, weight - 1, -1):
#                 dp[j] = max(dp[j], dp[j - weight] + value)

#             count -= amount
#             k <<= 1

#     return dp[capacity]

# # 示例
# weights = [2, 3, 4]
# values = [3, 4, 5]
# counts = [1, 2, 2]
# capacity = 5
# result = multiple_knapsack(weights, values, counts, capacity)
# print(result)

s = "g"
t = "d"
print(abs(ord(s) - ord(t)))