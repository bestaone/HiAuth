# Docker版 {#docker}
Docker版的集成过程和SaaS版类似，只不过在集成前，我们需要部署一个Docker版本的HiAuth。Docker版的本地安装部署，依赖`PostgreSQL16+`、`Redis`，如果需要运行HiAuth自带的Demo的话，还需要 `JDK17` 和 `Maven 3.8+`环境。

## 安装及集成步骤：
- 检查服务器环境
- 初始化HiAuth数据库，执行SQL脚本
- 配置HiAuth的启动配置文件
- 下载镜像、启动服务
- 使用HiAuth源码Demo验证

### 检查服务器环境 {#check-env}
```shell
# 检查git版本
$ git --version
git version 1.8.3.1

# 检查daocker版本
$ docker -v
Docker version 26.1.4, build 5650f9b

# 检查JDK版本
$ java -version
openjdk version "17.0.5" 2022-10-18

# 检查maven版本
$ mvn -v
Apache Maven 3.8.6 (36645f6c9b5079805ea5009217e36f2cffd34256)
```

### 初始化HiAuth数据库，执行SQL脚本 {#init-db}
- 安装`PostgreSQL16+`；
- 创建数据库`hiauth`；
- 执行初始化脚本 `HiAuth/other/hiauth.sql` ；

### 配置HiAuth的启动配置文件 {#config-file}
- 创建配置文件 `/opt/install/hiauth/conf/hiauth.properties`，并配置正确的参数;
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
> 配置参考：[hiauth.properties](https://github.com/bestaone/HiAuth/blob/master/other/hiauth.properties)

### 下载镜像、启动服务 {#download-image}
```shell
# 需要能够访问dockerhub中央仓库，可能需要梯子
$ docker run -d \
  --restart=always \
  -p 9080:80 -p 8080:8080 \
  -v /opt/install/hiauth/conf:/hiauth/conf \
  -v /opt/install/hiauth/logs:/hiauth/logs \
  --name hiauth bestaone/hiauth:3.0.0
  
# 如果无法访问dockerhub中央仓库，可以从阿里云仓库下载镜像
$ docker run -d \
  --restart=always \
  -p 9080:80 -p 8080:8080 \
  -v /opt/install/hiauth/conf:/hiauth/conf \
  -v /opt/install/hiauth/logs:/hiauth/logs \
  --name hiauth registry.cn-zhangjiakou.aliyuncs.com/bestaone/hiauth:3.0.0
  
# 查看镜像  
$ docker images
REPOSITORY                                                 TAG           IMAGE ID       CREATED         SIZE
bestaone/hiauth                                            3.0.0         c5e4140bd5aa   3 hours ago     810MB
registry-vpc.cn-zhangjiakou.aliyuncs.com/bestaone/hiauth   3.0.0         c5e4140bd5aa   3 hours ago     810MB

# 查看服务
$ docker ps
CONTAINER ID   IMAGE                   COMMAND                  CREATED        STATUS       PORTS                                                  NAMES
3ea0fdb8a165   bestaone/hiauth:3.0.0   "/hiauth/run.sh"         3 hours ago    Up 3 hours   8080/tcp, 0.0.0.0:9080->80/tcp, :::9080->80/tcp        hiauth

# 查看日志
$ docker logs 3ea0fdb8a165
...
INFO 7 [main] org.springframework.boot.web.embedded.tomcat.TomcatWebServer Tomcat started on port 8080 (http) with context path '/'
INFO 7 [main] cn.hiauth.server.ServerStarter Started ServerStarter in 10.094 seconds (process running for 11.107)
...

# 访问服务
$ curl http://127.0.0.1:9080
{ "code": 50000, "message": "令牌无效或已过期" }
```
- Docker 版 HiAuth 授权地址为：http://127.0.0.1:9080
- Docker 版 HiAuth 管理地址为：http://127.0.0.1:9080/admin

### 使用HiAuth源码Demo验证 {#hiauth-himall}
- 下载源码
```shell
$ git clone https://github.com/bestaone/HiAuth.git
```
- 修改配置`HiAuth/example/himall/src/main/resources/application.yml`
```yaml
...
spring.security.oauth2.client:
  provider:
    hiauth-server:
      # 将 issuer-uri 的值从 http://auth.hiauth.cn 改为 http://127.0.0.1:9080
      issuer-uri: http://auth.hiauth.cn
...
```
- 编译运行
```shell
$ cd HiAuth/example/himall
$ mvn clean install
$ mvn spring-boot:run
```

### 验证
- 浏览器访问: http://127.0.0.1:9000
- 点击`Login`按钮，会被重定向到统一认证系统，输入账号：`corpadmin`，密码：`123456`
- 登录成功后，会看到首页及登录用户信息!
