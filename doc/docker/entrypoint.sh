

java -jar ./hi-auth-web.jar -Xmx512m -Xss256k >/dev/null &
java -jar ./hi-mall-web.jar -Xmx512m -Xss256k >/dev/null &
java -jar ./hi-mall-microsvr-goods.jar -Xmx512m -Xss256k >/dev/null &
java -jar ./hi-mall-microsvr-order.jar -Xmx512m -Xss256k >/dev/null &