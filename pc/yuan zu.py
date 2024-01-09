# # 元组的创建方式
t = ('Python', 'word', 98)
# # 或省略（）
# # 若元组只包含一个元素，则应在元素后加','
# print(t)
# print(type(t))
#
# # 内置函数tuple
# t1 = tuple(('Python', 'word', 98))
# print(t1)
# print(type(t1))
#
# # 空元组
# t2 = ()
# t3 = tuple()


# t = (10, [20, 30], 9)
# print(t)
# print(type(t))
# print(t[0], type(t[0]), id(t[0]))  # t[0]表示获取索引0位置的值
# print(t[1], type(t[1]), id(t[1]))
# print(t[2], type(t[2]), id(t[2]))
# # 不可将t[1]修改为100，但可以向t[1]中增加100
# t[1].append(100)
# print(t, id(t[1]))


#  遍历
#  第一种获取元组的方式，索引
#  第二种 遍历元组
for item in t:
    print(item)

# 为不可变序列，没有生成式



