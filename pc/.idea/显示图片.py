import pygame

pygame.init()
window = pygame.display.set_mode((800,800))
pygame.display.set_caption('游戏')
#背景颜色
window.fill((225,225,225))
#加载图片
image1 = pygame.image.load('img/大学牲.jpg')
#渲染图片
#window.blit(image1,(0,0))
#获取图片大小
w,h = image1.get_size()
print(w,h)
#缩放图片
#scale (缩放对象，缩放大小）
new1 = pygame.transform.scale(image1,(200,200))
window.blit(new1,(0,0))
#缩放+旋转
#rotozoom (对象，旋转角度，缩放比例）
new2 = pygame.transform.rotozoom(new1,180,2)
window.blit(new2,(0,200))



pygame.display.flip()
while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            exit()