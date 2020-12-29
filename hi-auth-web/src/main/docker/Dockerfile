FROM bestaone/jdk11:20201204
# 设置语音
ENV LANG en_US.UTF-8
# 设置地理位置
ENV TZ=Asia/Shanghai
# 设置时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 安装字体 支持easyexcel
RUN yum install dejavu-sans-fonts fontconfig -y
# 挂在目录
VOLUME /data
# 暴露远程deug端口
EXPOSE 5005
ADD hi-auth-web.jar hi-auth-web.jar
RUN sh -c 'touch /hi-auth-web.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "/hi-auth-web.jar"]
