FROM bestaone/jdk11:20201204
# 设置语音
ENV LANG en_US.UTF-8
# 设置地理位置
ENV TZ=Asia/Shanghai
# 设置时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 挂在目录
VOLUME /data
# 暴露远程deug端口
EXPOSE 5005
ADD hi-mall-microsvr-goods.jar hi-mall-microsvr-goods.jar
RUN sh -c 'touch /hi-mall-microsvr-goods.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "/hi-mall-microsvr-goods.jar"]
