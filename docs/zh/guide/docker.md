# Docker版 {#docker}

Docker版的安装和集成，需要用户提供本地数据库`PostgreSQL`、`Redis`，如果需要运行HiAuth自带的Demo的话，还需要 `JDK17` 和 `Maven 3.8+`。

安装及集成步骤：
- 检查服务器环境
- 初始化HiAuth数据库
- 准备启动配置
- 下载下载镜像、启动服务
- 使用HiAuth源码Demo验证

## 检查服务器环境 {#check-env}
```shell
# 检查git版本
$git --version
git version 1.8.3.1

# 检查daocker版本
$docker -v
Docker version 26.1.4, build 5650f9b

# 检查JDK版本
$java -version
openjdk version "17.0.5" 2022-10-18
OpenJDK Runtime Environment GraalVM CE 22.3.0 (build 17.0.5+8-jvmci-22.3-b08)
OpenJDK 64-Bit Server VM GraalVM CE 22.3.0 (build 17.0.5+8-jvmci-22.3-b08, mixed mode, sharing)

# 检查maven版本
$mvn -v
Apache Maven 3.8.6 (36645f6c9b5079805ea5009217e36f2cffd34256)
Maven home: /opt/install/apache-maven-3.8.6
Java version: 21.0.3, vendor: Oracle Corporation, runtime: /usr/local/jdk-17
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "3.10.0-1160.119.1.el7.x86_64", arch: "amd64", family: "unix"
```

## 初始HiAuth化数据库 {#init-db}
- 安装`PostgreSQL16+`；
- 创建数据库`hiauth`；
- 执行初始化脚本 `http://localhost:5173/docs/guide/docker` ；

## 准备HiAuth的启动配置 {#config-file}
- 下载配置文件 `http://localhost:5173/docs/guide/docker` ;
- 创建配置文件 `/opt/install/hiauth/conf/hiauth.properties`;
```properties [hiauth.properties]
# only supported postgresql
datasource.type=com.alibaba.druid.pool.DruidDataSource
datasource.driverClassName=org.postgresql.Driver
datasource.url=jdbc:postgresql://db_host:5432/hiauth
datasource.username=test
datasource.password=123456

redis.host=redis_host
redis.port=6379
redis.database=0
redis.username=test
redis.password=123456
```

## 下载镜像和启动服务 {#download-image}
```shell
# 需要能够访问dockerhub中央仓库，可能需要梯子
$docker run -d \
  --restart=always \
  -p 9080:80 -p 8080:8080 \
  -v /opt/install/hiauth/conf:/hiauth/conf \
  -v /opt/install/hiauth/logs:/hiauth/logs \
  --name hiauth bestaone/hiauth:3.0.0
  
# 如果无法访问dockerhub中央仓库，可以从阿里云仓库下载镜像
$docker run -d \
  --restart=always \
  -p 9080:80 -p 8080:8080 \
  -v /opt/install/hiauth/conf:/hiauth/conf \
  -v /opt/install/hiauth/logs:/hiauth/logs \
  --name hiauth registry-vpc.cn-zhangjiakou.aliyuncs.com/bestaone/hiauth:3.0.0
  
# 查看镜像  
$docker images
REPOSITORY                                                 TAG           IMAGE ID       CREATED         SIZE
bestaone/hiauth                                            3.0.0         c5e4140bd5aa   3 hours ago     810MB
registry-vpc.cn-zhangjiakou.aliyuncs.com/bestaone/hiauth   3.0.0         c5e4140bd5aa   3 hours ago     810MB

# 查看服务
$docker ps
CONTAINER ID   IMAGE                   COMMAND                  CREATED        STATUS       PORTS                                                  NAMES
3ea0fdb8a165   bestaone/hiauth:3.0.0   "/hiauth/run.sh"         3 hours ago    Up 3 hours   8080/tcp, 0.0.0.0:9080->80/tcp, :::9080->80/tcp        hiauth

# 查看日志
$docker logs 3ea0fdb8a165
...
INFO 7 [main] org.springframework.boot.web.embedded.tomcat.TomcatWebServer Tomcat started on port 8080 (http) with context path '/'
INFO 7 [main] cn.hiauth.server.ServerStarter Started ServerStarter in 10.094 seconds (process running for 11.107)
...

# 访问服务
$curl http://127.0.0.1:9080
{ "code": 50000, "message": "令牌无效或已过期" }
```

## 使用HiAuth源码Demo验证 {#hiauth-himall}
- 下载源码并编译
```shell
$git clone https://github.com/bestaone/hiauth.git
$cd hiauth/himall
# 编译前修改配置文件 HiAuth/example/himall/src/main/resources/application.yml，
# 将 issuer-uri 的值从http://auth.hiauth.cn 改为 http://127.0.0.1:8080
$mvn clean install
$mvn spring-boot:run
```

- 浏览器访问: http://127.0.0.1:9000