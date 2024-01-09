# 可变类型的序列，是没有value的字典

# # 创建集合
# # 1.{}
# s = {2, 3, 4, 5, 5, 6}  # 集合中的元素不能重复
# # 2.set{}
# s1 = set(range(6))
# print(s1, type(s1))
# s2 = set([1, 2, 4, 5, 5, 6, 6])
# print(s2)
# s3 = set((1, 2, 4, 4, 5, 65))  # 集合的元素是无序的
# print(s3)
# s4 = set('python')
# print(s4, type(s4))
# # 空集合
# s5 = set()
# print(s5, type(s5))


# s = {10, 20, 30, 405, 60}
# # 集合元素的判断
# print(10 in s)
# print(100 in s)
# print(100 not in s)
# # 集合元素的新增
# s.add(80)
# print(s)
# s.update({200, 400, 300})  # 无序
# print(s)
# s.update([100, 99, 8])
# s.update((78, 64, 56))
# print(s)
# # 集合元素的删除
# s.remove(100)  # 若没有，会报错
# print(s)
# s.discard(500)  # 若没有，不会报错
# print(s)
# s.pop()  # 随机删除一个
# print(s)
# s.clear()  # 清空
# print(s)


# # 一个集合是另一个集合的子集
# s1 = {10, 20, 30, 40, 50, 60}
# s2 = {10, 20, 30, 40}
# s3 = {10, 20, 90}
# print(s2.issubset(s1))  # s2是s1的子集
# print(s3.issubset(s1))
# # 超集
# print(s1.issuperset(s2))
# # 无交集
# print(s2.isdisjoint(s3))
# # 查看交集内容
# print(s1.intersection(s3))
# print(s1 & s3)
# # 并集
# print(s1.union(s3))
# print(s1 | s3)
# # 差集--前面的比后面的多了什么
# print(s1.difference(s3))
# print(s1-s3)
# # 对称差集--除交集以外的元素
# print(s1.symmetric_difference(s3))
# print(s1 ^ s3)


# 集合生成式
s = {i * i for i in range(10)}
print(s)




