'''def max(a,b):
    a>=b
    return








a=int(input('请输入整数'))#,int(input('请输入整数')),int(input('请输入整数')),int(input('请输入整数')),int(input('请输入整数'))
b=int(input('请输入整数'))#,int(input('请输入整数')),int(input('请输入整数')),int(input('请输入整数')),int(input('请输入整数'))
#if a>=b:
    #print(a)
c=max(a,b)
print(c)'''
#a=int(input('请输入一个三位整数'))


'''for a in range(100,1000):
    b=a%10
    c=a//10%10
    d=a//100
    if b**3+c**3+d**3==a:

       print(a)'''
'''i=0
while i<9:
    i+=1
    j=0
    while i>j:
        j+=1
        print('%d*%d=%d'%(j,i,i*j),end='\t')

    print()'''

for r in range(1,10):
     for c in range(1,r+1):
         print(c,'*',r,'=',r*c,end='\t')
     print()