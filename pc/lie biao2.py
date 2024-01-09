from random import randint
# lst=[10,20,'python','hello']
# print(10 in lst)# True为存在
# for item in lst:
#    print(item)
# 列表元素的添加操作
''' lst=[10,20,30]
print('添加之前=',lst,id(lst))
lst.append(100)
print('增加之后=',lst,id(lst))
lst2=['hello','world']
lst.extend(lst2)
print(lst)
lst.insert(3,90)
print(lst)
lst3=[True,False,'hello']
lst[1::]=lst3
print(lst) '''



# 列表元素的删除操作
# lst=[10,20,30,40,50,60,30]
# print(id(lst))
# lst.remove(30)
# print(lst,id(lst))
# lst.pop(1)# 若（）内为空，则删除列表中最后一个元素
# print(lst,id(lst))
# n_lst = lst[1:3]#  切出1，2，不包括3，产生新的列表对象
# print(n_lst)
# lst[1:3] = []# 将1，2的元素用空列表替换，不产生新列表对象
# lst.clear()
# print(lst)
# del lst[2]
# print(lst,id(lst))




'''#列表元素的改
lst=[10,20,30,40]
lst[2]=100
print(lst)
lst[1:3]=[5471]
print(lst)'''


'''lst=[20,40,10,90,54]
print('排序前的列表',lst,id(lst))
lst.sort()
print('排列后的顺序',lst,id(lst))
lst.sort(reverse=True)# 降序
print(lst,id(lst))
lst.sort(reverse=False)# 升序
print(lst,id(lst))'''



'''#内置函数sorted()对列表排序
lst=[20,40,10,98,54]
print('原列表',lst)
new_list=sorted(lst)
print(lst)
print(new_list)'''

#列表生成式
lst = [i * i for i in range(1, 10)]
print(lst)
lst2 = [i * 2 for i in range(1, 6)]
print(lst2)
