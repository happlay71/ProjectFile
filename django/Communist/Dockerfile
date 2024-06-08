# 基础镜像
FROM python:3.10
 
# 在容器中创建项目目录
RUN mkdir /code
 
# 设置工作目录
WORKDIR /code
 
# 将当前目录下的所有文件复制到容器中的 /code 目录
COPY . /code/
 
# 安装项目依赖
RUN pip install -r requirements.txt