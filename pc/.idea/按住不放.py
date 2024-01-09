import  pygame

pygame.init()

W = 800
H = 800
window = pygame.display.set_mode((W,H))
pygame.display.set_caption('动画')
window.fill((255,255,255))
image = pygame.image.load('img/大学牲.jpg')

new_w = pygame.transform.scale(image,(100,100))
new_a = pygame.transform.rotozoom(new_w,90,1)
new_s = pygame.transform.rotozoom(new_w,180,1)
new_d = pygame.transform.rotozoom(new_s,90,1)
new = new_w
x , y = 350 , 350
#print(x,y)
window.blit(new_w,(x,y))


pygame.display.flip()

move = False
x_s = 0
y_s = 0
while True:
    if move == True:
        window.fill((255, 255, 255))
        x += x_s
        y += y_s
        window.blit(new, (x, y))

        pygame.display.update()
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            exit()

        if event.type == pygame.KEYDOWN:
            char = chr(event.key)
            if char == 'w':
                move = True
                x_s = 0
                y_s = -0.1
                new = new_w
                #y -= 0.1
            if char == 's':
                move = True
                x_s = 0
                y_s = 0.1
                new = new_s
                #y += 0.1
            if char == 'a':
                move =True
                x_s = -0.1
                y_s = 0
                new = new_a
                #x -= 0.1
            if char == 'd':
                move = True
                x_s = 0.1
                y_s = 0
                new = new_d
                #x += 0.1

        if event.type == pygame.KEYUP:
            if char == 'w':
                move = False
            if char == 's':
                move = False
            if char == 'a':
                move = False
            if char == 'd':
                move = False