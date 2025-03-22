FROM ubuntu:jdk21-ng-24

# 设置语言
ENV LANG en_US.UTF-8
ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8

# 设置地理位置
ENV TZ=Asia/Shanghai

# 设置时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 安装工具
RUN apt-get update && apt-get install -y curl && apt-get clean

# 挂在目录
VOLUME /data

# 安装jar包
ADD ./hiauth-server/target/hiauth-server.jar /hiauth/app.jar
COPY ./cicd/hiauth.properties /hiauth/conf/hiauth.properties

# 安装前端
ADD ./hiauth-front/apps/web-auth/dist.zip dist.zip
RUN mkdir -p /html && chmod a+rwx -R /html
RUN unzip dist.zip -d /html/admin

# 安装文档
COPY ./docs/.vitepress/dist /html/docs

# 配置nginx
COPY ./cicd/nginx.conf /etc/nginx/nginx.conf

# 配置启动脚本
RUN echo "#!/bin/bash" > /hiauth/run.sh
RUN echo "java -jar /hiauth/app.jar & nginx -g 'daemon off;'" >> /hiauth/run.sh

# 设置权限
RUN chmod +x /hiauth/run.sh

# 暴露端口
EXPOSE 80 8080

# 设置容器启动时执行的命令
ENTRYPOINT ["/hiauth/run.sh"]