import pygame
from collections import deque

W = 800
H = 800
window = pygame.display.set_mode((W, H))
pygame.display.set_caption('贪吃蛇')
window.fill((255,255,255))
# x_r = 0
# y_r = 0
# c = 0
# r = 0
# pygame.draw.rect(window,(0,255,0),(x_r,y_r,20,20))
# pygame.draw.rect(window, (0, 0, 0), (c, r, 20, 20))
def init_snake():
    snake = deque()
    snake.append((2, 0))
    snake.append((1, 0))
    snake.append((0, 0))
    return snake
snake = init_snake()

pygame.display.flip()
n = 0
x_sp = 0.1
y_sp = 0
motion = False
pos = (1, 0)
snake.pop()
while True:
    window.fill((255, 255, 255))
    n += 10
    # if motion == True:
    #     pygame.draw.rect(window, (0, 255, 0), (x_r - c, y_r - r, 20, 20))
    #     pygame.Rect.union(window, (0, 255, 0), (x_r - c, y_r - r, 20, 20),)
    #     pygame.display.update()
    #
    # if n % 100 == 0:
    #
    #     x_r += x_sp
    #     y_r += y_sp
    #     pygame.draw.rect(window, (0, 255, 0), (x_r, y_r, 20, 20))
    #
    #
    #     if x_r == c:
    #         if y_r == r:
    #             motion = True
    #     pygame.display.update()

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            exit()
        if event.type == pygame.KEYDOWN:
            char = chr(event.key)
            # if char == 'w':
            #     x_sp = 0
            #     y_sp = - 0.1
            # if char == 's':
            #     x_sp = 0
            #     y_sp = 0.1
            # if char == 'a':
            #     x_sp = - 0.1
            #     y_sp = 0
            # if char == 'd':
            #     x_sp = 0.1
            #     y_sp = 0
    for s in snake:
        pygame.draw.rect(window, (0, 255, 0), (s[0] * 20, s[1] * 20, 20, 20))
    pygame.display.update()