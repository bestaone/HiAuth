FROM java:8
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
VOLUME /tmp
VOLUME /data
ADD hi-mall-web.jar hi-mall-web.jar
RUN sh -c 'touch /hi-mall-web.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /hi-mall-web.jar" ]
