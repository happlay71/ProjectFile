import pygame
import random
from collections import deque

pygame.init()
W = 800
H = 800
window = pygame.display.set_mode((W, H))
pygame.display.set_caption('贪吃蛇')
window.fill((255,255,255))
font = pygame.font.Font('艺术字.ttf',80)
font1 = pygame.font.Font('艺术字.ttf', 30)
text = font.render('游戏结束', True, (255, 0, 0))
text2 = font.render('分数：', True, (255, 0, 0))
text5 = font.render('请退出重来', True, (255, 0, 0))
#

def init_snake():
    snake = deque()
    snake.append((2, 0))
    snake.append((1, 0))
    snake.append((0, 0))
    return snake
snake = init_snake()


def Creat_Food(snake):
    food_x = random.randrange(0, 781, 20)
    food_y = random.randrange(0, 781, 20)
    while (food_x, food_y) in snake:
        food_x = random.randrange(0, 781, 20)
        food_y = random.randrange(0, 781, 20)
    return food_x, food_y
food_x = 60
food_y = 0
pygame.draw.rect(window, (255, 0, 0), (food_x, food_y, 20, 20))


def Creat_trap_x():
    trap_x = random.randrange(0, 781, 20)
    while trap_x in snake:
        trap_x = random.randrange(0, 781, 20)
trap_x = Creat_trap_x()
def Creat_trap_y():
    trap_y = random.randrange(0, 781, 20)
    while trap_y in snake:
        trap_y = random.randrange(0, 781, 20)
trap_y = Creat_trap_y()

def Creat_trap(food):
    trap_x = random.randrange(0, 781, 20)
    trap_y = random.randrange(0, 781, 20)
    while (trap_x, trap_y) in (food_x, food_y):
        trap_x = random.randrange(0, 781, 20)
        trap_y = random.randrange(0, 781, 20)
    return trap_x, trap_y
trap_x = random.randrange(0, 781, 20)
trap_y = random.randrange(0, 781, 20)

def show_score(score):
    text2 = font.render("{0}".format(score), True, (255, 0, 0))
    window.blit(text2, (450, 350))
score = -1

i = 0
pos = (1, 0)
m = 0
q = 0
l = 0
t = 0
p = 0
count = 0
modul = 0
modul_1 = 0
modul_2 = 0
trap = 0
while True :
    m += 1
    window.fill((255, 255, 255))
    text3 = font.render('开始游戏', True, (0, 255, 0), (150, 150, 150))
    window.blit(text3, (250, 150))
    text_5 = font.render('模式选择', True, (0, 255, 0), (150, 150, 150))
    window.blit(text_5, (250, 300))

    if modul == 1:
        pygame.draw.rect(window, (255, 255, 255), (250, 150, 320, 105))
        pygame.draw.rect(window, (255, 255, 255), (250, 300, 320, 105))
        text6_1 = font.render('普通模式', True, (0, 255, 0), (150, 150, 150))
        window.blit(text6_1, (60, 410))
        text6_2 = font.render('成长+陷阱', True, (0, 255, 0), (150, 150, 150))
        window.blit(text6_2, (400, 410))
    if modul_1 == 1:
        modul = 0
        text6_1 = font.render('普通模式', True, (0, 255, 0), (0, 0, 0))
        window.blit(text6_1, (60, 410))
    if modul_2 == 1:
        modul = 0
        text6_2 = font.render('成长+陷阱', True, (0, 255, 0), (0, 0, 0))
        window.blit(text6_2, (400, 410))

    if p == 1:
        text4 = font.render('继续游戏', True, (0, 255, 0), (150, 150, 150))
        window.blit(text4, (250, 150))


    if l == 1:
        i = 1
        modul = 0
        score_window = font1.render("分数 : %s" % score, True, (0, 0, 0))
        window.blit(score_window, (0, 0))
        pygame.draw.rect(window, (255, 255, 255), (250, 150, 320, 105))
        pygame.draw.rect(window, (255, 255, 255), (250, 300, 320, 105))


    if snake[0] == (food_x, food_y) or snake[0] == ((food_x - (pos[0] * 20)) / 20, (food_y - (pos[1] * 20)) /20):
        score += 1
        food_x = random.randrange(0, 781, 20)
        food_y = random.randrange(0, 781, 20)

        R = random.randint(100, 255)
        G = random.randint(100, 200)
        B = random.randint(100, 255)
        snake.appendleft((snake[0][0] + pos[0], snake[0][1] + pos[1]))
    pygame.draw.rect(window, (R, G, B), (food_x, food_y, 20, 20))#随机食物

    if i == 1 and m  % 250 == 0:
        snake.appendleft((snake[0][0] + pos[0], snake[0][1] + pos[1]))
        snake.pop()
        pygame.display.update()

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            exit()
        if event.type == pygame.MOUSEBUTTONDOWN:
            mx, my = event.pos
            print(mx, my)
            if 250 <= mx <= 570 and 150 <= my <= 255:
                l = 1
            if 250 <= mx <= 570 and 300 <= my <= 405:
                modul = 1
            if 60 <= mx <= 380 and 410 <= my <= 515:
                modul_1 = 1
                trap = 0
            if 400 <= mx <= 755 and 410 <= my <= 515:
                modul_2 = 1
                trap = 1
        if event.type == pygame.MOUSEBUTTONUP:
            if 60 <= mx <= 380 and 410 <= my <= 515:
                modul_1 = 0
            if 400 <= mx <= 755 and 410 <= my <= 515:
                modul_2 = 0
        if event.type == pygame.KEYDOWN:
            if  event.key == pygame.K_UP:
                pos = (0, -1)
            if  event.key == pygame.K_DOWN:
                pos = (0, 1)
            if  event.key == pygame.K_LEFT:
                pos = (-1, 0)
            if  event.key == pygame.K_RIGHT:
                pos = (1, 0)
            if event.key == pygame.K_SPACE:
                i = 0
                q = 0
                l = 0
                p = 1
                modul = 0
                count += 1
                if count % 2 == 0:
                    l = 1
            if event.key == pygame.K_ESCAPE:
                exit()


    for s in snake:
        pygame.draw.rect(window, (0, 255, 0), (s[0] * 20, s[1] * 20, 20, 20))

    if snake[0] == (trap_x, trap_y) or snake[0] == ((trap_x - (pos[0] * 20)) / 20, (trap_y - (pos[1] * 20)) / 20):
        t = 1
    if trap == 1:
        if i == 1 and m % 5000 == 0:
            snake.appendleft((snake[0][0] + pos[0], snake[0][1] + pos[1]))
        if snake[0] == (food_x, food_y) or snake[0] == ((food_x - (pos[0] * 20)) / 20, (food_y - (pos[1] * 20)) / 20):
            trap_x = random.randrange(0, 781, 20)
            trap_y = random.randrange(0, 781, 20)
        pygame.draw.rect(window, (0, 0, 0), (trap_x, trap_y, 20, 20))  # 随即陷阱

    if t == 1:
        i = 0
        q = 0
        l = 0
        window.fill((0, 0, 0))
        window.blit(text, (250, 250))
        window.blit(text2, (250, 350))
        window.blit(text5, (250, 450))
        show_score(score)

    if snake[0][0] >= 40 or snake[0][1] >= 40 or snake[0][0] < 0 or snake[0][1] < 0 or (snake[0][0] + pos[0], snake[0][1] + pos[1]) in snake :
        t = 1
    pygame.display.update()







