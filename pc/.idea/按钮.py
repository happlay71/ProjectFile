import pygame

pygame.init()
W = 800
H = 800
window = pygame.display.set_mode((W,H))
pygame.display.set_caption('检测事件')
window.fill((255,255,255))
#确定按钮
pygame.draw.rect(window,(255,0,170),(335,90,150,50))
font = pygame.font.Font('艺术字.ttf',30)
text = font.render('开始游戏',True,(0,255,0))
window.blit(text,((W-100)/2,100))


pygame.display.flip()
while True:
    for event in pygame.event.get():

        if event.type ==pygame.MOUSEBUTTONDOWN:
            mx,my = event.pos
            if 335 <= mx <= 485 and 90 <= my <= 140:
                pygame.draw.rect(window, (255, 255, 255), (335, 90, 150, 50))
                # font = pygame.font.Font('艺术字.ttf', 30)
                # text = font.render('开始游戏', True, (0, 255, 0))
                # window.blit(text, ((W - 100) / 2, 100))

                pygame.display.update()
        if event.type == pygame.MOUSEBUTTONUP:

            # pygame.draw.rect(window, (255, 0, 170), (335, 90, 150, 50))
            # font = pygame.font.Font('img/艺术字.ttf', 30)
            # text = font.render('开始游戏', True, (0, 255, 0))
            # window.blit(text, ((W - 100) / 2, 100))
            x = 0
            y = 0
            window.fill((255,255,255))
            image1 = pygame.image.load('img/大学牲.jpg')
            new1 = pygame.transform.scale(image1, (200, 200))
            window.blit(new1, (x, y))
            pygame.display.update()
        if event.type == pygame.KEYDOWN:

            print('键盘按下',event.key,chr(event.key))
            if chr(event.key) == 'a':
                x -= 10
                window.fill((255, 255, 255))
                image1 = pygame.image.load('img/大学牲.jpg')
                new1 = pygame.transform.scale(image1, (200, 200))
                window.blit(new1, (x, y))
                pygame.display.update()

            if chr(event.key) == 'd':
                x += 10
                window.fill((255, 255, 255))
                image1 = pygame.image.load('img/大学牲.jpg')
                new1 = pygame.transform.scale(image1, (200, 200))
                window.blit(new1, (x, y))
                pygame.display.update()

            if chr(event.key) == 'w':
                y -= 10
                window.fill((255, 255, 255))
                image1 = pygame.image.load('img/大学牲.jpg')
                new1 = pygame.transform.scale(image1, (200, 200))
                window.blit(new1, (x, y))
                pygame.display.update()

            if chr(event.key) == 's':
                y += 10
                window.fill((255, 255, 255))
                # image1 = pygame.image.load('img/大学牲.jpg')
                # new1 = pygame.transform.scale(image1, (200, 200))
                window.blit(new1, (x, y))
                pygame.display.update()

        if event.type == pygame.QUIT:
            exit()