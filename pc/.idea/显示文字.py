import pygame

pygame.init()
window = pygame.display.set_mode((800,800))
pygame.display.set_caption('游戏')
window.fill((0,0,0))
#创建字体对象
#font(字体文件路径，字号）
font = pygame.font.Font('img/艺术字.ttf',70)
#创建文字对象
#render(文字内容，True，文字颜色，背景颜色）
text = font.render('想得美！还不快去学习！',True,(0,225,0))
window.blit(text,(50,347))
w,h = text.get_size()
print(w,h)


pygame.display.flip()
while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            exit()