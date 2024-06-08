def queen(r):
    if r == n:
        print(['.' * c + 'Q' + '.' * (n - 1 - c) for c in col])
        ans.append(['.' * c + 'Q' + '.' * (n - 1 - c) for c in col])
        return
    for c, on in enumerate(on_path):
        if not on and not diag1[r + c] and not diag2[r - c]:
            col[r] = c
            on_path[c] = diag1[r + c] = diag2[r - c] = True
            queen(r + 1)
            on_path[c] = diag1[r + c] = diag2[r - c] = False  # 恢复现场
n = 8
m = n * 2 - 1
ans = []
col = [0] * n
on_path, diag1, diag2 = [False] * n, [False] * m, [False] * m
queen(0)
