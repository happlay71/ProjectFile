'''import pygame
import random

W = 800
H = 800
FPS = 30#帧率

#创建窗口
# initialize pygame and create wndow
pygame.init()#启动pygame并初始化
pygame.mixer.init()#声音初始化
screen = pygame.display.set_mode((W,H))
pygame.display.set_caption("she")#窗口标题
clock = pygame.time.Clock()#时钟对象

#Game Loop
running = True
while running:
    BLACK = (0, 0, 0)
    WHITE = (255, 255, 255)
    RED = (255, 0, 0)
    GREEN = (0, 255, 0)
    BLUE = (0, 0, 255)
    screen.fill(BLACK)'''
'''# Pygame template - skeleton for a new pygame project
import time
import pygame
import random

WIDTH = 360
HEIGHT = 480
FPS = 30

# define colors
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)

# initialize pygame and create window
pygame.init()
pygame.mixer.init()
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("My Game")
clock = pygame.time.Clock()

myfont = pygame.font.Font(None,60)
textImage = myfont.render("pygame", True, WHITE)
# Game loop
running = True
count = 0
start = time.time()

clock = pygame.time.Clock()
all_sprites = pygame.sprite.Group()

all_sprites.update()
screen.fill(BLACK)
all_sprites.draw(screen)
class Player(pygame.sprite.Sprite):
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.Surface((50,50))
        self.image.fill(GREEN)
        self.rect = self.image.get_rect()
        self.rect.center = (WIDTH/2,HEIGHT/2)
all_sprites = pygame.sprite.Group()
player = Player()
all_sprites.add(player)

while running:
    # keep loop running at the right speed
    clock.tick(FPS)
    # Process input (events)
    for event in pygame.event.get():
        # check for closing window
        if event.type == pygame.QUIT:
            running = False

    # Update
    count+=1
    now = time.time()
    fps = count/(now-start)
    fpsImage = myfont.render(str(fps), True, WHITE)
    # Draw / render
    screen.fill(BLACK)
    screen.blit(fpsImage, (10, 100))
    # *after* drawing everything, flip the display
    pygame.display.flip()

pygame.quit()'''

'''# Pygame sprite Example
import pygame
import random
import os

WIDTH = 800
HEIGHT = 600
FPS = 30

# define colors
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)

class Player(pygame.sprite.Sprite):
    # sprite for the Player
    def __init__(self):
        # this line is required to properly create the sprite
        pygame.sprite.Sprite.__init__(self)
        # create a plain rectangle for the sprite image
        self.image = player_img
        # find the rectangle that encloses the image
        self.rect = self.image.get_rect()
        # center the sprite on the screen
        self.rect.center = (WIDTH / 2, HEIGHT / 2)

    def update(self):
        # any code here will happen every time the game loop updates
        self.rect.x += 100
        if self.rect.left > WIDTH:
            self.rect.right = 0

# initialize pygame and create window
pygame.init()
pygame.mixer.init()
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Sprite Example")
clock = pygame.time.Clock()

game_folder = os.path.dirname(__file__)
img_folder = os.path.join(game_folder, 'img')



all_sprites = pygame.sprite.Group()
player = Player()
all_sprites.add(player)
# Game loop
running = True
while running:
    # keep loop running at the right speed
    clock.tick(FPS)
    # Process input (events)
    for event in pygame.event.get():
        # check for closing window
        if event.type == pygame.QUIT:
            running = False

    # Update
    all_sprites.update()

    # Draw / render
    screen.fill(BLACK)
    all_sprites.draw(screen)
    # *after* drawing everything, flip the display
    pygame.display.flip()

pygame.quit()'''
'''
# Pygame sprite Example
import pygame
import random
import os

WIDTH = 800
HEIGHT = 600
FPS = 30

# define colors
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)

# set up assets folders
# Windows: "C:\chris\Documents\img"
# Mac: "/Users/chris/Documents/img"
game_folder = os.path.dirname(__file__)
img_folder = os.path.join(game_folder, "img")

class Player(pygame.sprite.Sprite):
    # sprite for the Player
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load(os.path.join(img_folder, "p1_jump.png")).convert()
        self.image.set_colorkey(BLACK)
        self.rect = self.image.get_rect()
        self.rect.center = (WIDTH / 2, HEIGHT / 2)
        self.y_speed = 5

    def update(self):
        self.rect.x += 5
        self.rect.y += self.y_speed
        if self.rect.bottom >  HEIGHT-200:
            self.y_speed = -5
        if self.rect.top < 200:
            self.y_speed = 5
        if self.rect.left > WIDTH:
            self.rect.right = 0

# initialize pygame and create window
pygame.init()
pygame.mixer.init()
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("My Game")
clock = pygame.time.Clock()


all_sprites = pygame.sprite.Group()
player = Player()
all_sprites.add(player)
# Game loop
running = True
while running:
    # keep loop running at the right speed
    clock.tick(FPS)
    # Process input (events)
    for event in pygame.event.get():
        # check for closing window
        if event.type == pygame.QUIT:
            running = False
        

    # Update
    all_sprites.update()

    # Draw / render
    screen.fill(BLACK)
    all_sprites.draw(screen)
    # *after* drawing everything, flip the display
    pygame.display.flip()

pygame.quit() '''

'''import sys
import pygame

pygame.init()
size = width, height = 600, 400
screen = pygame.display.set_mode(size)
screen.fill('black')
pygame.display.set_caption('外星人键盘移动事件')
img = pygame.image.load('E:\E\PyCharm Community Edition 2022.2.3\pc\.idea\img\p1_jump.png')
position = img.get_rect()


def update(self):
    self.rect.x += 5
    self.rect.y += self.y_speed
    if self.rect.bottom >  - 200:
        self.y_speed = -5
    if self.rect.top < 200:
        self.y_speed = 5
    if self.rect.left > WIDTH:
        self.rect.right = 0

while True:


    site = [0, 0]
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_UP:
                site[1] -= 10
            if event.key == pygame.K_DOWN:
                site[1] += 10
            if event.key == pygame.K_LEFT:
                site[0] -= 10
            if event.key == pygame.K_RIGHT:
                site[0] += 10
        if event.type == pygame.MOUSEBUTTONDOWN:
            xx, yy = event.pos
            site = [xx, yy]

    position = position.move(site)
    screen.fill('black')
    screen.blit(img, position)
    pygame.display.flip()'''

