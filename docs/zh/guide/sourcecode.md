# 源码版 {#sourcecode}
源码版的集成过程和Docker版类似，只不过将Docker版本的HiAuth改为源码编译启动。

## 环境要求 {#env-demand}
- Git
- JDK 17+
- Maven 3.8+
- Nodejs v20.15+
- pnpm 10.10+
- PostgreSQL16+
- Redis

## 安装及集成步骤
- 检查服务器环境
- 下载源码
- 初始化HiAuth数据库，执行SQL脚本
- 配置HiAuth的启动配置文件
- 编译源码、启动服务
- 使用HiAuth源码Demo验证

### 检查服务器环境 {#check-env}
```shell
# 检查git版本
$ git --version
git version 1.8.3.1

# 检查JDK版本
$ java -version
openjdk version "17.0.5" 2022-10-18

# 检查maven版本
$ mvn -v
Apache Maven 3.8.6 (36645f6c9b5079805ea5009217e36f2cffd34256)

# 检查node版本
$ node -v
v20.15.0

# 检查pnpm版本
$ pnpm -v
9.14.2
```

### 下载源码 {#download-source}
```shell
$ git clone https://github.com/bestaone/HiAuth.git
```

### 初始化HiAuth数据库，执行SQL脚本 {#init-db}
- 安装`PostgreSQL16+`；
- 创建数据库`hiauth`；
- 执行初始化脚本 `HiAuth/other/hiauth.sql` ；

### 配置HiAuth的启动配置文件 {#config-file}
- 修改配置文件 `HiAuth/cicd/hiauth.properties`，改成你自己的配置;
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
- 应用配置文件，修改`HiAuth/hiauth-server/src/main/resources/application.yml`;
```yaml
...
# 将 /hiauth/conf/hiauth.properties 替换为你上面配置的文件路径
spring.config.import: ${CONFIG_FILE:optional:/hiauth/conf/hiauth.properties}
...
```

### 编译源码、启动服务 {#compile-start}
```shell
# 编译、启动服务端
$ cd HiAuth/hiauth-server
$ mvn clean install
$ mvn spring-boot:run

# 编译、启动前端
$ cd HiAuth/hiauth-front
$ pnpm install
$ pnpm dev:auth

# 访问服务
$ curl http://127.0.0.1:8080
{ "code": 50000, "message": "令牌无效或已过期" }
```
- 检查后端服务：http://127.0.0.1:8080
- 检查前端服务：http://127.0.0.1:5666/admin (端口可能会变化，请自行查看控制台)

### 使用HiAuth源码Demo验证 {#hiauth-himall}
- 修改配置`HiAuth/example/himall/src/main/resources/application.yml`
```yaml
...
spring.security.oauth2.client:
  provider:
    hiauth-server:
      # 将 issuer-uri 的值从 http://auth.hiauth.cn 改为 http://127.0.0.1:8080
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

## 视频教程
<iframe src="//player.bilibili.com/player.html?bvid=BV1KhZEYmERF&page=1" allowfullscreen></iframe>