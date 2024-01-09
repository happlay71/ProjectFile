'''a=0
while a<3:
    b=input('请输入密码')
    if b==5471:
        print('密码正确')
        break
    else:
        print('密码错误')
    a+=1'''


for a in range(3):
    a=input('请输入密码')
    if a==5471:
        print('密码正确')
        break
    else:
        print('密码错误')
else:
    print('对不起，三次密码均输入错误')