import subprocess  # 执行命令并处理其输出
import os
import tkinter as tk  # 创建GUI窗口
from tkinter import simpledialog
from threading import Thread
from datetime import datetime

# 保存用户输入的路径和文件信息的文件路径
input_file_path = "input.txt"

def get_user_input():
    root = tk.Tk()
    root.withdraw()  # 隐藏主窗口

    if os.path.exists(input_file_path):
        # 如果文件存在，读取文件中保存的路径和文件信息
        with open(input_file_path, "r") as file:
            input_text = file.read().strip()
    else:
        # 如果文件不存在，提示用户输入路径和文件信息
        input_text = simpledialog.askstring("输入执行路径和文件", "请输入执行路径和文件（用空格分隔）:")
        if input_text is not None:
            # 将用户输入的路径和文件信息保存到文件中
            with open(input_file_path, "w") as file:
                file.write(input_text)

    # 将输入的文本按空格分割成路径和文件两部分
    directory, files = input_text.split(' ', 1)
    print("用户输入的路径是:", directory)
    print("用户输入的文件是:", files)

    return str(directory), str(files)

def command_in_directory(directory):
    try:
        # 切换到指定目录
        os.chdir(directory)
        # 打印当前工作目录
        print("当前工作目录:", os.getcwd())
    except FileNotFoundError:
        print("指定文件夹不存在！")

def execute_command(command, directory):
    # 执行命令并捕获其输出
    # process = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True)

    # for line in process.stdout:
    #     print(line.strip())
    
    # # 等待命令执行完毕
    # process.wait()
    
    # # 检查命令执行结果
    # if process.returncode == 0:
    #     print("命令执行成功")
    # else:
    #     print("命令执行失败")

    try:
        # 执行命令并捕获其输出
        result = subprocess.run(command, shell=True, cwd=directory, capture_output=True, text=True)
        # 打印命令执行结果
        print(result.stdout)
        # 检查命令执行状态
        if result.returncode == 0:
            print("命令执行成功")
        else:
            print("命令执行失败")
    except Exception as e:
        print("命令执行出错:", e)

def main():
    try:
        directory, files = get_user_input()
        # directory = "E:\\E\\Typora\\笔记"

        # 命令
        # command_add = subprocess.run(['git', 'add', '题'], check=True, cwd=directory)
        command_add = subprocess.run(['git', 'add', files], check=True, cwd=directory)
        import datetime
        time = datetime.datetime.now().strftime("%Y.%m.%d")
        command_commit = subprocess.run(['git', 'commit', '-m', time], check=True, cwd=directory)
        command_push = subprocess.run(['git', 'push', 'origin', 'master'], check=True, cwd=directory)
        

        #启动三个线程执行命令并捕获输出
        # thread_add = Thread(target=execute_command, args=(command_add, directory))
        # thread_commit = Thread(target=execute_command, args=(command_commit, directory))
        # thread_push = Thread(target=execute_command, args=(command_push, directory))

        # thread_add.start()
        # thread_commit.start()
        # thread_push.start()

        # 等待执行完毕
        # thread_add.join()
        # thread_commit.join()
        # thread_push.join()
    except subprocess.CalledProcessError as e:
        # 打印异常信息
        print("Command execution failed with return code:", e.returncode)
        print("Command output:", e.output)

if __name__ == "__main__":
    main()
