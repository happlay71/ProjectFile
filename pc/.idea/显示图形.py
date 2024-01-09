import pygame
#导入数学模块
from math import pi
pygame.init()
window = pygame.display.set_mode((800,800))
pygame.display.set_caption('游戏')
window.fill((255,255,255))

for w in range(0, 700, 50):
    for h in range(0, 700, 50):
        t = pygame.draw.rect(window, (0, 0, 0), (w, h, 20, 10))
        pygame.display.update()

#直线
#line(画在那，线的颜色，起点，终点，线宽=1）
pygame.draw.line(window,(255,0,150),(0,0),(200,200),5)
#画折线
#lines(画在那，颜色，是否闭合，多个点，线宽=1）
point = [(0,0),(10,100),(200,500)]
pygame.draw.lines(window,(255,0,150),False,point,5)
#画圆
#circle(画在那，颜色，圆心坐标，半径，线宽=0）
pygame.draw.circle(window,(255,0,150),(300,300),200,10)
#画矩形
#rect(画在那，颜色，矩形范围(起点坐标，宽，高），线宽=0）
#pygame.draw.rect(window,(255,0,150),(600,0,200,100),1)
#画椭圆
#ellipse(画在那，颜色，矩形范围，线宽=0）矩形的内切圆
pygame.draw.ellipse(window,(255,0,150),(600,0,200,100),1)
#画弧线
#arc(画在那，颜色，矩形范围，起始弧度，结束弧度，线宽=1）
pygame.draw.arc(window,(0,0,255),(600,0,200,100),0,-pi*3/2,4)


pygame.display.flip()
while True:
    #制造矩阵

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            exit()