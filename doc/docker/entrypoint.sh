#!/bin/sh
nohup java -jar /hi-auth-web.jar -Xmx512m -Xss256k >/dev/null &
nohup java -jar /hi-mall-web.jar -Xmx512m -Xss256k >/dev/null &
nohup java -jar /hi-mall-microsvr-goods.jar -Xmx512m -Xss256k >/dev/null &
nohup java -jar /hi-mall-microsvr-order.jar -Xmx512m -Xss256k >/dev/null &
# 死循环，保持docker前台运行
while [[ true ]]; do
    sleep 1
done