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

# 运行HiAuth 运行HiMall
ADD hi-auth-web.jar hi-auth-web.jar
ADD hi-mall-web.jar hi-mall-web.jar
ADD hi-mall-microsvr-goods.jar hi-mall-microsvr-goods.jar
ADD hi-mall-microsvr-order.jar hi-mall-microsvr-order.jar
RUN sh -c 'touch /hi-auth-web.jar'
RUN sh -c 'touch /hi-mall-web.jar'
RUN sh -c 'touch /hi-mall-microsvr-goods.jar'
RUN sh -c 'touch /hi-mall-microsvr-order.jar'


CMD nohup sh -c 'java -jar /hi-auth-web.jar && java -jar /hi-mall-web.jar && java -jar /hi-mall-microsvr-goods.jar && java -jar /hi-mall-microsvr-order.jar'

# ADD entrypoint.sh entrypoint.sh
# RUN chmod +x entrypoint.sh

# CMD nohup sh -c "/entrypoint.sh && java -jar /hi-auth-web.jar"
