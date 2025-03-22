FROM harbor.vking.fun/vking/nginx:1.25.5

VOLUME /tmp

# 设置语言
ENV LANG en_US.UTF-8

# 设置地理位置
ENV TZ=Asia/Shanghai

RUN mkdir -p /html && chmod a+rwx -R /html

ADD ./apps/web-auth/dist.zip front-auth.zip
RUN unzip front-auth.zip -d /html/front-auth

EXPOSE 80
EXPOSE 443
