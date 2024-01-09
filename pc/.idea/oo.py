import pygame

pygame.init()
windows = pygame.display.set_mode((1010,800))
pygame.display.set_caption('起飞')

redColour = pygame.Color(255,0,0)
blackColour = pygame.Color(0,0,0)
whiteColour = pygame.Color(255,255,255)
greyColour = pygame.Color(150,150,150)
LightGrey = pygame.Color(220,220,220)
windows.fill(LightGrey)

img1=pygame.image.load('img/大学牲.jpg')
#windows.blit(img1,(4,0))
w,h =img1.get_size()
print(w,h)
new_img1=pygame.transform.scale(img1,(100,100))
#windows.blit(new_img1,(0,0))
#旋转
new_img2=pygame.transform.rotozoom(new_img1,45,1)
windows.blit(new_img2,(0,0))
pygame.display.flip()

snakePosition =[100,100]#蛇头位置
snakeSegments =[[100,100],[80,100],[60,100]]#初始长度为3个单位
raspberrySpawned = 1#食物个数
direction ='right'#初始方向
changeDirection = direction
score =0#初始分数


'''for event in pygame.event.get():
    if event.type==QUIT:
        pygame.quit()
        sys.exit()
    elif event.type==KEYDOWN:
        if event.key==K_RIGHT or event.key == ord('d'):
            changeDirection = 'right'
        if event.key==K_LEFT or event.key == ord('a'):

            changeDirection = 'left'
        if event.key==K_UP or event.key == ord('w'):
            changeDirection = 'up'
        if event.key==K_DOWN or event.key == ord('s'):
            changeDirection = 'down'
        if event.key==K_ESCAPE:
            pygame.event.post(pygame.event.Event(QUIT))

#判断是否输入了反方向
if changeDirection == 'right' and not direction == 'left':
    direction = changeDirection
if changeDirection == 'left' and not  direction == 'right':
    direction = changeDirection
if changeDirection == 'up' and not direction =='down':
    direction = changeDirection
if changeDirection == 'down' and not direction == 'up':
    direction =changeDirection

#根据方向移动蛇头坐标
if direction == 'right':
    snakePosition[0] += 20
if direction == 'left':
    snakePosition[0] -= 20
if direction == 'up':
    snakePosition[1] -=20
if direction == 'down':
    snakePosition[1] +=20'''

while True:
    for event in pygame.event.get():#检测事件
        if event.type==pygame.QUIT:
            exit()#程序结束运行
    pygame.display.update()