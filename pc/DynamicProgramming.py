def subsequence(s1, s2):
    m = len(s1)
    n = len(s2)
    
    # 创建一个二维数组来存储子问题的解
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    
    # 使用动态规划填充数组
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if s1[i - 1] == s2[j - 1]:
                dp[i][j] = dp[i - 1][j - 1] + 1
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
            print(dp)
    
    # 从dp数组中提取最长公共子序列
    lcs = ""
    i, j = m, n
    while i > 0 and j > 0:
        if s1[i - 1] == s2[j - 1]:
            lcs = s1[i - 1] + lcs
            i -= 1
            j -= 1
        elif dp[i - 1][j] > dp[i][j - 1]:
            i -= 1
        else:
            j -= 1
    
    return lcs

s1 = "ABCBDAB"
s2 = "BDCAB"
print("最长公共子序列为:", subsequence(s1, s2))
