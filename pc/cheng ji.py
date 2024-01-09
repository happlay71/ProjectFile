
s=float(input("请输入一个成绩"))
if 90 <= s <= 100:
    print('A级')
elif 80 <= s < 90:
    print('B级')
elif 70 <= s < 80:
    print('C级')
elif 60 <= s < 70:
    print('D级')
elif 0 <= s < 60:
    print('不及格')
elif s<0 or s>100:
    print('判题错误')



