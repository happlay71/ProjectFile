# 字典是无序可变序列  键值对，排列顺序为不可变
scores = {'张三': 100, '李四': 98, '王五': 45}
# print(scores)
# student = dict(name = 'jack', age = 18)
# print(student)
# d = {}# 空字典

# 字典中元素的获取
# print(scores['张三'])
# #print(scores['陈六'])# 不存在的键会报错
# print(scores.get('张三'))
# print(scores.get('陈六', 71))# 不存在的键会显示None,71为不存在的键提供的默认值

# key值的判断
# print('张三' in scores)
# print('张三' not in scores)

# 字典元素的删除
# del scores['张三']  # 删除指定
# print(scores)
# scores.clear()  # 清空字典
# print(scores)
# # 增加+修改
# scores['陈六'] = 98
# print(scores)
# scores['陈六'] = 100
# print(scores)

# 获取所有key
keys = scores.keys()
print(keys)
print(type(keys))
print(list(keys))  # 将所有的key组成的视图转成列表

# 获取所有的值
# values = scores.values()
# print(values)
# print(type(values))
# print(list(values))

# key-values
# items = scores.items()
# print(items)
# print(list(items))

# 遍历
# for item in scores:
#     print(item, scores[item], scores.get(item))

# 字典生成式
items = ['Fruits', 'Books', 'Others']
prices = [96, 78, 85]
d = {items.upper(): prices for items,prices in zip(items, prices)}
print(d)