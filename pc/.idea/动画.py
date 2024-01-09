import  pygame

pygame.init()

W = 800
H = 800
window = pygame.display.set_mode((W,H))
pygame.display.set_caption('动画')
window.fill((255,255,255))

x = W/2
y = 20
r = 20
pygame.draw.circle(window,(0,0,255),(x,y),r)
n = 0
x_sp = 1
y_sp = 1

pygame.display.flip()
while True:
    #移动动画
    n += 1

    if n % 5000 == 0:
        # 1 pygame.draw.circle(window, (255, 255, 255), (x, y), r)
        window.fill((255,255,255))# 2
        #检测边界
        y += y_sp
        if y >= H - r:
            y_sp = -1
        if y <= r:
            y_sp = 1
        #y += y_sp
        pygame.draw.circle(window, (0, 0, 255), (x, y), r)
        pygame.display.update()
    #放大或缩小动画
    # if n % 100000 == 0:
    #     pygame.draw.circle(window, (255, 255, 255), (x, y), r)
    #     r -= 1
    #     pygame.draw.circle(window, (0, 0, 255), (x, y), r)
    #     pygame.display.update()
    for evevt in pygame.event.get():

        if evevt.type == pygame.QUIT:
            exit()