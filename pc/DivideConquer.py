import random
# 加法
# def binary(X, Y, start, end, carry):
#     s = ''
#     for i in range(end - 1, start - 1, -1):
#         num = X[i] + Y[i] + carry
#         if num > 1: 
#             carry = 1
#             if num == 2:
#                 s = '0' + s
#             else: s = '1' + s
#         else:
#             carry = 0
#             if num == 0:
#                 s = '0' + s
#             else: s = '1' + s
#     return s, carry
def karatsuba(X, Y):
    n = max(len(X), len(Y))
    if n == 1:
        return int(X) * int(Y)

    n_2 = n // 2

    a = X[:-n_2]
    b = X[-n_2:]
    c = Y[:-n_2]
    d = Y[-n_2:]

    ac = karatsuba(a, c)
    bd = karatsuba(b, d)
    ab_cd = karatsuba(str(int(a) + int(b)), str(int(c) + int(d))) - ac - bd

    return ac * 10**(2*n_2) + ab_cd * 10**n_2 + bd

n = int(input())
s = ''
X = ''.join(str(random.randint(0, 1)) for _ in range(n))
Y = ''.join(str(random.randint(0, 1)) for _ in range(n))
print(f"X:{X}, Y:{Y}")
result = karatsuba(X, Y)
print(result)



