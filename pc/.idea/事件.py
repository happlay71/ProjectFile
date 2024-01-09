import sys

import pygame
from random import randint

pygame.init()

W = 800
H = 800
window = pygame.display.set_mode((W,H))
pygame.display.set_caption('检测事件')
window.fill((255,255,255))
count = 0
x = W/2
y = 20
r = 20
pygame.draw.circle(window, (70, 70, 70), (x, y), r)


pygame.display.flip()
while True:

    for event in pygame.event.get():
        # count += 1
        # print(count)
        #鼠标

        if event.type == pygame.MOUSEBUTTONDOWN:
            print('鼠标按下',event.pos)   #event.pos 鼠标点击的坐标
            print(event)

        if event.type == pygame.MOUSEBUTTONUP:
            #print(pygame.draw.circle(window,(255,0,150),(300,300),20))
            #pygame.display.update()

            pass
        if event.type == pygame.MOUSEMOTION:
            #鼠标移动
            # mx,my = event.pos
            # R = randint(0, 255)
            # B = randint(0, 255)
            # G = randint(0, 255)
            #
            # print(pygame.draw.circle(window,(R,G,B),(mx,my),20))
            # pygame.display.update()
            pass
        #键盘
        if event.type == pygame.KEYDOWN:

            print('键盘按下',event.key,chr(event.key))
            if chr(event.key) == 'a':
                x -= 10
                window.fill((255, 255, 255))
                pygame.draw.circle(window, (0, 0, 0), (x, y), r)
                pygame.display.update()

            if chr(event.key) == 'd':
                x += 10
                window.fill((255, 255, 255))
                pygame.draw.circle(window, (0, 0, 255), (x, y), r)
                pygame.display.update()

            if chr(event.key) == 'w':
                y -= 10
                window.fill((255, 255, 255))
                pygame.draw.circle(window, (0, 0, 255), (x, y), r)
                pygame.display.update()

            if chr(event.key) == 's':
                y += 10
                window.fill((255, 255, 255))
                pygame.draw.circle(window, (0, 0, 255), (x, y), r)
                pygame.display.update()
        if event.type == pygame.QUIT:
            if pygame.QUIT == pygame.K_ESCAPE:
                sys.exit()
            exit()