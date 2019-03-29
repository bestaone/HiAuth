FROM java:8
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
VOLUME /tmp
VOLUME /data
ADD hi-mall-microsvr-order.jar hi-mall-microsvr-order.jar
RUN sh -c 'touch /hi-mall-microsvr-order.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /hi-mall-microsvr-order.jar" ]
