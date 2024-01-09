import pygame
from random import randint

pygame.init()
W = 800
H = 800
window = pygame.display.set_mode((W,H))
pygame.display.set_caption('消除方块')
window.fill((255,255,255))
font = pygame.font.Font('img/艺术字.ttf',50)
text = font.render('游戏结束',True,(255,0,0))


#pygame.display.flip()

x_r = 360
y_r = 600
w = 70
h = 5
#pygame.draw.rect(window,(150,150,150),(x_r, y_r, w, h))

x_c = 400
y_c = 595
r = 5
pygame.draw.circle(window,(255,0,150),(x_c, y_c),r)


n_c = 0
y_sp = 1
x_sp = 1
pygame.display.flip()
motion = False
start = False
i = list(range(50, 251, 30))#(0,6)
y = randint(0, 5)
q = list(range(35, 701, 30))#(0,26)
x = randint(0, 2)
i = list(range(50, 251, 30))  # (0,7)
                y = randint(0, 5)
                q = list(range(35, 751, 30))  # (0,24)
                x = randint(0, 22)
                q.pop(x)
                i.pop(y)
while True:

    window.fill((255, 255, 255))
    lsti = i[y]
    lstq = q[x]
    for lsti in i:
        for lstq in q:
            pygame.draw.rect(window, (0, 0, 0), (lstq, lsti, 20, 10))

            if x_c + r == lstq or x_c - r == lstq + 20 or y_c + r == lsti or y_c - r == lsti + 10:

                pygame.draw.rect(window, (0, 0, 0), (lstq, lsti, 20, 10))

    if y_c >= y_r + 20:
        window.fill((0, 0, 0))

        window.blit(text,(330,350))
        pygame.display.update()
    n_c += 1
    if n_c % 30 == 0:
        if motion == True:

            pygame.draw.rect(window, (150, 150, 150), (mx - w / 2 , y_r, w, h))
            #window.fill((255, 255, 255))
            y_c -= y_sp
            x_c += x_sp
            if mx - w / 2 <= x_c - r and x_c + r <= mx + w / 2 and y_r <= y_c + r :
                #if  x_c + r <= mx + w / 2:
                #if y_r <= y_c + r :
                y_sp = 1
            if y_c <= r:
                y_sp = -1
            if x_c <= r:
                x_sp = 1
            if x_c >= W + r:
                x_sp = -1
            if 250 < y_c - r and y_c + r < 250 + 10:
                if x_c + r == 750:
                    x_sp = -1
            #pygame.draw.rect(window, (150, 150, 150), (x_r, y_r, w, h))
            pygame.draw.circle(window, (255, 0, 150), (x_c, y_c), r)
            pygame.display.update()


    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            exit()
        if event.type == pygame.MOUSEMOTION:
            mx,my = event.pos
            print(mx,my)
            motion = True
