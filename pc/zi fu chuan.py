
s = 'hello,hello'
# # 字符串的查询操作
# print(s.index('lo'))  # 从前往后查，没有时会报错
# print(s.find('lo'))  # 从前往后查，没有时或显示-1
# print(s.rindex('lo'))  # 后往前，没有时会报错
# print(s.rfind('lo'))  # 后往前，没有时或显示-1

# # 大小写转换
# a = s.upper()  # 全转换成大写，id改变
# print(a)
# b = s.lower()  # 全转换成小写，id改变
# print(b)
# c = s.swapcase()  # 大变小，小变大，id改变
# print(c)
# d = s.title()  # 每个英文单词开头大写，id改变
# print(d)
# f = s.capitalize()  # 第一个字符大写，其余小写
# print(f)

# # 字符串对齐操作
# print(s.center(20, '*'))  # 居中对齐，第一个参数指定宽度，第二个参数指定填充符
# print(s.ljust(20, '*'))  # 左对齐.......
# print(s.rjust(20, '*'))  # 右对齐.......
# print(s.zfill(20))  # 右对齐，只能用0填充
# print('-8910'.zfill(8))  # 在引号左边填充0

# # 字符串劈分操作
# a = 'hellow world Python'
# lst = a.split()  # 左侧开始分割，默认分割字符为空格，返回值为一个列表
# print(lst)
# a1 = 'hellow|world|Python'
# print(a1.split(sep='|'))  # 指定以谁为分割对象
# print(a1.split(sep='|', maxsplit=1))  # 指定最大分割次数
# print(a1.split('|', 1))  # 等同于上面
#
# print(a.rsplit())  # 右侧开始分割，其余同上
# print(a1.rsplit(sep='|'))
# print(a1.rsplit(sep='|', maxsplit=1))
# print(a1.rsplit('|', 1))

# 判断字符串
print('1.', s.isidentifier())  # 判断字符串是否合法【字母（可中文），数字，下划线（第一个字符不能是数字）】
print('2.', '\t'.isspace())  # 判断字符串是否由空白字符组成（回车，换行，水平制表符）
print('3.', '张三'.isalpha())  # 判断字符串是否由字母组成（中文也算）
print('4.', '12四'.isdecimal())  # 判断字符串是否由十进制数字组成（如罗马数，中文数字就为False)
print('5.', '1二Ⅲ'.isnumeric())  # 判断字符串是否由数字（所有）组成
print('6.', '张1Ⅱ'.isalnum())  # 判断字符串是否全由字母和数字组成

# 字符串的替换，合并
a = 'hello, Python'
print(a.replace('Python', 'Java'))  # 被替换量，替换量，替换次数
a1 = 'hello, Python, Python, Python'
print(a1.replace('Python', 'Java', 2))
# 只能合并列表或元组
lst = ['hello', 'java', 'Python']
print('|'.join(lst))
t = ('hello', 'Java', 'Python')
print(''.join(t))
print('*'.join('Python'))  # 将python认为是字符串


# # 字符串的比较
# print('apple' > 'app')
# print('a' > 'b')
# print(ord('a'), ord('b'))  # 解释a, b在Unicode里的编码
# print(chr(97), chr(98))  # 查询97，98在Unicode里代表的字符
# print(ord('屈'))
# # ==是比较值
# # is是比较地址


# # 字符串的切片--[start:end:step]
# a = 'hello,Python'
# a1 = a[:5]  # 没有指定起始位置，所以从0开始
# a2 = a[6:]  # 没有指定结束位置，所以切到最后一个元素
# a3 = '!'
# newstr = a1 + a3 + a2  # 新地址
# print(newstr)
# print(a[1:5:1])
# print(a[::-1])  # 默认从字符串的最后一个元素开始，因为步长为负


# 格式化字符串
# 1.%占位符
name = '张三'
age = 20
print('我叫%s,今年%d岁' % (name, age))
# 2.{}-format
print('我叫{0},今年{1}岁'.format(name, age))
# 3.f-string
print(f'我叫{name},今年{age}岁')
# 4.精确小数点和宽度
print('{0:.3}'.format((3.1415926)))  # 显示三个数
print('{0:10.3f}'.format((3.1415926)))  # 显示三位小数，宽度为10



