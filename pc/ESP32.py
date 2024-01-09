# # 点亮LED灯
# import machine
# import time
#
# pin2 = machine.Pin(2, machine.Pin.OUT)
# while True:
#     pin2.value(1)  # 1控制灯亮
#     time.sleep(1)  # 表示延迟1秒
#     pin2.value(0)  # 0控制灯灭
#     time.sleep(1)


# # PWM呼吸灯
# from machine import Pin, PWM
# import time
#
# led2 = PWM(Pin(2))  # 开发板上的引脚
# led2.freq(1000)  # 设置频率
# while True:
#     # 从不亮到亮
#     for i in range(0, 1024):
#         led2.duty(i)  # 设置占空比（高电平所占比例，越大越亮）
#         time.sleep_ms(2)  # 毫秒延迟
#     # 从亮到不亮
#     for i in range(1023, -1, -1):
#         led2.duty(i)
#         time.sleep_ms(2)


# # 链接wifi热点
# import network
# wlan = network.WLAN(network.STA_IF)  # ESP32链接wifi/路由器
# wlan.active(True)  # 激活设备,结果为True则激活成功
# wlan.scan()  # 扫描周围设备
# wlan.isconnected()  # 检测是否链接设备（若链接过一次则以后都显示True)
# wlan.connect('设备名', '密码')  # 链接设备
# wlan.ifconfig()  # 显示('ip地址','端网页码','网端','域名系统')
#
# # 测试
# from socket import *
# udp_socket = socket(AF_INET, SOCK_DGRAM)  # 创建udp套接字
# dest_addr = ('本地主机地址', 端口)  # 准备接收方的地址
# send_data = "hello world"  # 传输的数据
# udp_socket.sendto(send_data.encode('utf-8'), dest_addr)  # 发送数据到指定电脑上
# recv_data = udp_socket.recvfrom(1024)  # 接收数据
# udp_socket.close()  # 关闭套接字


# # PC远程控制LED灯
# import time
# import network
# import machine
#
#
# def do_connect():
#     # 作用：链接wifi网络
#     wlan = network.WLAN(network.STA_IF)
#     wlan.active(True)
#     if not wlan.isconnected():
#         print('connecting to network...')
#         wlan.connect('71', '61527483')
#         i = 1
#         while not wlan.isconnected():
#             print("正在连接网络...{}".format(i))
#             time.sleep(1)
#     print('network config:', wlan.ifconfig())
#
#
# def create_udp_socket():
#     import socket
#     # 1.创建udp套接字
#     udp_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
#     # 2.绑定一个固定的端口
#     udp_socket.bind(("0.0.0.0", 5471))
#     return udp_socket
#
#
# def main():
#     # 1.让esp32链接wifi，让其拥有ip地址
#     do_connect()
#     # 2.创建UDP socket
#     udp_socket = create_udp_socket()
#     # 创建GPIO引脚对象
#     led = machine.Pin(2, machine.Pin.OUT)
#     # 3.接收数据
#     while True:
#         recv_data, sender_info = udp_socket.recvfrom(1024)
#         print("{}发送的数据，{}".format(sender_info, recv_data))
#         recv_data_str = recv_data.decode("utf-8")  # decode是解码，incode是编码
#         print("解码后的数据:{}".format(recv_data_str))
#         # 4.根据收到的UDP数据控制LED灯的亮灭
#         if recv_data_str == "light on":
#             led.value(1)
#         elif recv_data_str == "light off":
#             led.value(0)
#
#
# if __name__ == "__main__":
#     main()


# # 数码管
# import machine
# import time
#
#
# a = machine.Pin(13, machine.Pin.OUT)
# b = machine.Pin(12, machine.Pin.OUT)
# c = machine.Pin(14, machine.Pin.OUT)
# d = machine.Pin(27, machine.Pin.OUT)
# e = machine.Pin(26, machine.Pin.OUT)
# f = machine.Pin(25, machine.Pin.OUT)
# g = machine.Pin(33, machine.Pin.OUT)
# dot = machine.Pin(32, machine.Pin.OUT)
#
# number_led = [a, b, c, d, e, f, g, dot]
#
# number_dict = {
#     0: "11111100",
#     1: "01100000",
#     2: "11011010",
#     3: "11110010",
#     4: "01100110",
#     5: "10110110",
#     6: "10111110",
#     7: "11100000",
#     8: "11111110",
#     9: "11110110",
#     "open": "11111111",
#     "close": "00000000"
# }
#
# def show_number(number):
#     if number_dict.get(number):
#         i = 0
#         for bit in number_dict.get(number):
#             if bit == "1":
#                 number_led[i].value(1)
#             else:
#                 number_led[i].value(0)
#             i += 1
#
# def main():
#     # show_number("open")  # 全亮
#     # show_number("close")  # 全灭
#
#     for i in range(10):
#         show_number(i)
#         time.sleep(0.3)
#
# if __name__ == "__main__":
#     main()


# 4位数码管

# import machine
# import time
#
# # led1-4都是负极，相当于GND
# led1 = machine.Pin(5, machine.Pin.OUT)
# led2 = machine.Pin(18, machine.Pin.OUT)
# led3 = machine.Pin(19, machine.Pin.OUT)
# led4 = machine.Pin(21, machine.Pin.OUT)
#
# led_light_list = [led1, led2, led3, led4]
#
# a = machine.Pin(13, machine.Pin.OUT)
# b = machine.Pin(12, machine.Pin.OUT)
# c = machine.Pin(14, machine.Pin.OUT)
# d = machine.Pin(27, machine.Pin.OUT)
# e = machine.Pin(26, machine.Pin.OUT)
# f = machine.Pin(25, machine.Pin.OUT)
# g = machine.Pin(33, machine.Pin.OUT)
# dot = machine.Pin(32, machine.Pin.OUT)
#
# number_led = [a, b, c, d, e, f, g, dot]
#
# number_dict = {
#     0: "11111100",
#     1: "01100000",
#     2: "11011010",
#     3: "11110010",
#     4: "01100110",
#     5: "10110110",
#     6: "10111110",
#     7: "11100000",
#     8: "11111110",
#     9: "11110110",
#     "open": "11111111",
#     "close": "00000000"
# }
#
# def show_number(number):
#     if number_dict.get(number):
#         i = 0
#         for bit in number_dict.get(number):
#             if bit == "1":
#                 number_led[i].value(0)  # les1-4为共阳极，esp32相当于阴极。0时为阴极，1时为阳极
#             else:
#                 number_led[i].value(1)
#             i += 1
#
#
# def light_on(i):
#     for led in led_light_list:  # 将所有引脚设为低电压
#         led.value(0)
#     led_light_list[i].value(1)
#
# def show_4_number(number):
#     if 0 <= number <= 9999:
#         i = 0
#         for num in "%04d" % number:  # 将1234转换为字符串“1234”
#             show_number(int(num))  # 控制显示什么数字，转换为int类型
#             light_on(i)
#             i += 1
#
# for i in range(1000, 10000):
#     for j in range(5):
#         show_4_number(i)

# 转圈

import machine
import time

# led1-4都是负极，相当于GND
led1 = machine.Pin(5, machine.Pin.OUT)
led2 = machine.Pin(18, machine.Pin.OUT)
led3 = machine.Pin(19, machine.Pin.OUT)
led4 = machine.Pin(21, machine.Pin.OUT)

led_light_list = [led1, led2, led3, led4]

a = machine.Pin(13, machine.Pin.OUT)
b = machine.Pin(12, machine.Pin.OUT)
c = machine.Pin(14, machine.Pin.OUT)
d = machine.Pin(27, machine.Pin.OUT)
e = machine.Pin(26, machine.Pin.OUT)
f = machine.Pin(25, machine.Pin.OUT)
g = machine.Pin(33, machine.Pin.OUT)
dot = machine.Pin(32, machine.Pin.OUT)

number_led = [a, b, c, d, e, f, g, dot]

number_dict = {
    0: "11111100",
    1: "01100000",
    2: "11011010",
    3: "11110010",
    4: "01100110",
    5: "10110110",
    6: "10111110",
    7: "11100000",
    8: "11111110",
    9: "11110110",
    "open": "11111111",
    "close": "00000000"
}

# word_dict = {
#     "H": "10010001",
#     "E": "01100001",
#     "L": "11100011",
#     "O": "00000011",
#     "W": "10101011",
#     "R": "11110101",
#     "d": "10000101",
#     " ": "11111111",
# }


def show_number(number):
    if number_dict.get(number):
        i = 0
        for bit in number_dict.get(number):
            if bit == "1":
                number_led[i].value(0)  # les1-4为共阳极，esp32相当于阴极。0时为阴极，1时为阳极
            else:
                number_led[i].value(1)
            i += 1


def light_on(i):
    for led in led_light_list:  # 将所有引脚设为高电压
        led.value(1)
    led_light_list[i].value(0)


# 转圈
def show_turn():
    while True:
        for i in range(0, 4):
            a.value(1)
            light_on(i)
        time.sleep(1)

        for i in range(1, 4):
            a.value(1)
            light_on(i)
            if i == 3:
                b.value(1)
        time.sleep(1)

        for i in range(2, 4):
            a.value(1)
            light_on(i)
            if i == 3:
                b.value(1)
                c.value(1)
        time.sleep(1)

        a.value(1)
        b.value(1)
        c.value(1)
        d.value(1)
        light_on(3)
        time.sleep(1)

        for i in range(3, 1, -1):
            if i == 3:
                b.value(1)
                c.value(1)
                light_on(i)
            d.value(1)
            light_on(i)
        time.sleep(1)

        for i in range(3, 0, -1):
            if i == 3:
                c.value(1)
                light_on(i)
            d.value(1)
            light_on(i)
        time.sleep(1)

        for i in range(3, -1, -1):
            d.value(1)
            light_on(i)
        time.sleep(1)

        for i in range(2, -1, -1):
            d.value(1)
            light_on(i)
            if i == 0:
                e.value(1)
                light_on(i)
        time.sleep(1)

        for i in range(1, -1, -1):
            d.value(1)
            light_on(i)
            if i == 0:
                e.value(1)
                f.value(1)
                light_on(i)
        time.sleep(1)

        d.value(1)
        e.value(1)
        f.value(1)
        a.value(1)
        light_on(0)
        time.sleep(1)

        for i in range(0, 2, 1):
            if i == 0:
                e.value(1)
                f.value(1)
                light_on(i)
            a.value(1)
            light_on(i)
        time.sleep(1)

        for i in range(0, 3, 1):
            if i == 0:
                f.value(1)
                light_on(i)
            a.value(1)
            light_on(i)
        time.sleep(1)


def show_4_number(number):
    if 0 <= number <= 9999:
        i = 0
        for num in "%04d" % number:  # 将1234转换为字符串“1234”
            show_number(int(num))  # 控制显示什么数字，转换为int类型
            light_on(i)
            i += 1


W = 'HELLO WORLd'


i = 0
j = 3
for word in W[i::1]:
    show_word(word)
    light_on(j)
    for word in W[i + 1::1]:
        light_on(j - 1)
        for word in W[i + 2::1]:
            light_on(j - 2)
            for word in W[i + 3::1]:
                light_on(j - 3)

#
# for i in range(1000, 10000):
#     for j in range(5):
#         show_4_number(i)




import machine
import dht
import time
from machine import SoftI2C, Pin
from esp32_i2c_1602lcd import I2cLcd


DEFAULT_I2C_ADDR = 0x27
i2c = SoftI2C(sda=Pin(15),scl=Pin(2),freq=100000)
lcd = I2cLcd(i2c, DEFAULT_I2C_ADDR, 2, 16)

while True:
    th = dht.DHT11(machine.Pin(14))
    th.measure()
    t = th.temperature()
    h = th.humidity()
    lcd.clear()
    lcd.putstr('temperature: ' +str(t) + "\n")
    lcd.putstr('humidity: ' + str(h))


# SDA GPIO15
# SCL GPIO2
# Vcc 5V （3V显示不清楚）
# GND GND


