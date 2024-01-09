
# a =float(input('请输入一个数'))
# b =float(input('请输入一个数'))
# print('使用条件表达式进入比较')
# print( str(a)+'大于等于'+str(b) if a>=b else str(a)+'小于'+str(b))

# c=range(4,71,8)
# print(list(c)


# a=0
# sum=-1
# while a<8:
#   sum+=a
#   a+=2
#print(sum)
#print(a)

#凑270000摩拉
n1 = 270000//1260+1
n2 = 270000//2525+1
for a in range(n1):
    for b in range(n2):
        for c in range(13):
            if a*1260+b*2525+c*10000==270000:
                print('分解三星圣遗物', a, '个', '打本', b, '次', '换摩拉', c, '次')