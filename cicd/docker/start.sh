#!/bin/sh

echo '192.168.0.158 mysql-server' >> /etc/hosts
echo '192.168.0.158 redis-server' >> /etc/hosts

nohup /usr/local/nginx/sbin/nginx &
nohup java -jar -Xms128M -Xmx256M /hiauth-resource.jar &
nohup java -jar -Xms128M -Xmx256M /hiauth-server.jar &
nohup java -jar -Xms128M -Xmx256M /hiauth-himall.jar &
nohup java -jar -Xms128M -Xmx256M /hiauth-mgr-svc.jar &

while [[ true ]]; do
sleep 1
done