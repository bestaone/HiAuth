
<p align="center">
  <a href="http://hiwork.hiauth.cn">
    <img width="200" src="https://gw.alipayobjects.com/zos/rmsportal/KDpgvguMpGfqaHPjicRK.svg">
  </a>
</p>

<h1 align="center">Hi Auth</h1>

<div align="center">

HiAuth是一个开源的基于Oauth2协议的认证、授权系统，除了标准的Oauth2授权流程功能外，还提供了应用管理、用户管理、权限管理等相关功能。

[![Star](https://img.shields.io/github/stars/bestaone/HiAuth?color=42b883&logo=github&style=flat-square)](https://github.com/bestaone/HiAuth/stargazers)
[![Fork](https://img.shields.io/github/forks/bestaone/HiAuth?color=42b883&logo=github&style=flat-square)](https://github.com/bestaone/HiAuth/network/members)
[![Star](https://gitee.com/bestaone/HiAuth/badge/star.svg?theme=gray)](https://gitee.com/bestaone/HiAuth/stargazers)
[![Fork](https://gitee.com/bestaone/HiAuth/badge/fork.svg?theme=gray)](https://gitee.com/bestaone/HiAuth/members)
[![Language](https://img.shields.io/badge/%E8%AF%AD%E8%A8%80-Java%20%7C%20Springboot%20%7C%20React-red?style=flat-square&color=42b883)](https://github.com/zuihou/lamp-cloud)
[![License](https://img.shields.io/github/license/bestaone/HiAuth?color=42b883&style=flat-square)](https://github.com/bestaone/HiAuth/blob/master/LICENSE)
[![Author](https://img.shields.io/badge/作者-码道功臣-orange.svg)](https://github.com/bestaone)

</div>

## 介绍
除了认证相关功能外，还提供了hiauth-mall、hiauth-mgr-svc项目，供用户参考如何集成。

- 参考`hiauth-mall`，你可以了解如何在第三方应用中集成hiauth授权服务；
- 参考`hiauth-mgr-svc`项目，你可以快速的启动一个微服务项目的框架搭建，亦可以在这里找到一些技术的最佳实践，为你的项目开发提供参考；

### HiAuth 2.0 升级到 3.0 的升级内容
- SpringBoot 3.0.2
- spring-security-oauth2-authorization-server 1.0.0
- mybatis-plus 3.5.3.1

### 调整比较大的地方
- `SpringSecurity`升级到`6.0`版本后，用法有不少改动
- `SpringSecurity5`以后已不再支持`Authorization Server`，取而代之的事`spring-security-oauth2-authorization-server` 项目
- 前端从`vue-element-admin`换成了`AntDesignPro`

**如果你觉得此项目有价值，请给我点个star，谢谢！**

> 项目地址：https://github.com/bestaone/HiAuth

### 目录结构
```
├─doc                  文档目录，架构设计、数据库设计...
├─cicd                 持续集成相关脚本
├─hiauth-parent        统一管理依赖（必选）
├─hiauth-server        hiauth认证服务（必选）
├─hiauth-resource      hiauth资源管理服务，参考如何集成资源服务（参考）
├─hiauth-himall        himall是一个demo，参考此项目了解如何集成hiauth（参考）
├─hiauth-mgr-svc       hiauth管理端后台服务，基于SpringBoot（可选）
├─hiauth-mgr-fornt     hiauth管理端前端代码，基于AntDesignPro（可选）
```

### 功能
- 这个项目可以帮你实现基于`Oauth2`协议的统一认证、授权系统；
- 这个项目可以帮助你快速的启动一个基于`SpringBoot`技术栈的微服务框架搭建；
- 如果你仅仅使用到了Oauth2协议的统一认证、授权系统功能，那么你用技术可以是除java外的技术栈；
- himall演示了`authorization_code`模式，HiAuth支持了用户名密码、手机号短信两种认证方式；
- 发送短信、登录接口实现了图形验证码防刷功能；
- HiAuth是一个前后端分离项目，前端使用了`React`、`AntDesign`技术，参考这个项目，你可以自定义开发管理后端；
- 基于`SpringBoot`项目更容易集成到多个平台（SpringCloud、K8S、Istio）；
- 演示了如何统一控制接口规范；
- 演示了如何规范异常处理；
- 演示了如何规范使用`MyBaits-Plus`、分页；
- 演示了单元测试、mock测试、测试数据回滚，包括对controller、service的测试；

### 截图
- HiMall
<p align="center">
  <img width="900" src="https://hiauth.oss-cn-zhangjiakou.aliyuncs.com/himall.gif">
</p>

- UMC
>前端从`vue-element-admin`换成了`AntDesignPro`截图还没来得及做新的
<p align="center">
  <img width="900" src="https://hiauth.oss-cn-zhangjiakou.aliyuncs.com/umc.gif">
</p>


### LIVE DEMO
- Hiauth HiMall：http://himall.hiauth.cn
- Hiauth Manger：http://mgr.hiauth.cn
- Hiauth Server：http://auth.hiauth.cn

## 快速启动
### 环境需求
- JDK17+
- Maven 3.8.6
- MySQL 8
- Redis
- NodeJS v16+(<v18)
- Yarn 1.22.4

### 下载源码
```
>git clone https://github.com/bestaone/HiAuth.git
```

### 创建数据库
在你的`mysql`数据库中创建库hiauth，并执行下面脚本:
```
> HiAuth\doc\hiauth.sql

# 也可以从JAR自己提取相关表结构
# org.springframework.security.oauth2.server.authorization.client/oauth2-registered-client-schema.sql
# org.springframework.security.oauth2.server.authorization/oauth2-authorization-consent-schema.sql
# org.springframework.security.oauth2.server.authorization/oauth2-authorization-schema.sql
```

### 调整配置
需要调整的配置有数据库、redis，默认会使用`native.properties`配置，如果和你的环境不一致。

请修改:
```
# 需要将如下两个文件中的mysql、redis的配置改成自己的
# HiAuth\hiauth-server\src\main\properties\native.properties
# HiAuth\hiauth-mgr-svc\src\main\properties\native.properties

app.host=http://127.0.0.1:8080

database.url=jdbc:mysql://mysql-server:3306/hiauth3?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
database.username=dev
database.password=123456

redis.host=redis-server
redis.port=6379
redis.database=8
redis.password=
```

### 添加host
```bash
# IP 换成你自己的
127.0.0.1 redis-server
127.0.0.1 mysql-server
```

### 构建、启动
```
# 编译后台，会执行单元测试，需要正确配置数据库和redis
>cd HiAuth
>mvn clean install

# 构建前端并启动
>cd HiAuth\hiauth-mgr-front
>yarn install
>yarn start

# 启动hiauth授权服务端
>cd HiAuth\hiauth-server
>mvn spring-boot:run

# 启动hiauth资源服务端
>cd HiAuth\hiauth-resource
>mvn spring-boot:run

# 启动himall
>cd HiAuth\hiauth-himall
>mvn spring-boot:run

# 启动hiauth管理后端
>cd HiAuth\hiauth-mgr-svc
>mvn spring-boot:run
```

### 验证
#### 验证登录
- 访问HiMall：http://127.0.0.1:8081
- 访问Server：http://127.0.0.1:8080
- 访问Manager：http://127.0.0.1:8080 (具体端口看前端项目启动后的控制台输出)

#### 验证Swagger
- 访问HiAuth的`Swagger`地址：http://127.0.0.1:8080/swagger-ui.html
- 直接测试接口，显示未认证
```
{
  "error": "unauthorized",
  "error_description": "Full authentication is required to access this resource"
}
```
- 点击认证按钮，会被重定向到登录，输入账号登录进行认证
- 认证成功后被重定向回swagger页面
- 再次测试接口，获取正确数据

#### 验证Oauth2流程

#### authorization_code 认证流程
- 在浏览器中输入如下地址，会调到登录页，登录完成后会跳转到百度，在浏览器url中把`code`码复制出来
```
http://127.0.0.1:8080/oauth2/authorize?client_id=demo-client-id&response_type=code&scope=user_info&redirect_uri=http://www.baidu.com
```
- 使用上面获取的`code`换取`accessToken`
```bash
curl --location --request POST 'http://127.0.0.1:8080/oauth2/token?grant_type=authorization_code&code=code&redirect_uri=http://www.baidu.com' \
  --header 'Authorization: Basic ZGVtby1jbGllbnQtaWQ6ZGVtby1jbGllbnQtc2VjcmV0'
```
```
{
    "access_token": "xxxxxx",
    "refresh_token": "yyyyy",
    "scope": "user_info",
    "token_type": "Bearer",
    "expires_in": 7199
}
```
> `Authorization = Basic base64.encode(client_id:client_secret)`可以在网上找个在线工具生成，或者直接用postman测试，`Authorization`选择`Basic Auth`，填入对应值即可

- 访问受控接口，不带`accessToken`，返回401，未授权
```
>curl --location --request POST 'http://127.0.0.1:8082/user/info'
```
```
{
    "error": "unauthorized",
    "error_description": "Full authentication is required to access this resource"
}
```

- 访问受控接口，携带`accessToken`，返回数据
```
curl --location --request POST 'http://127.0.0.1:8082/user/info' --header 'Authorization: Bearer token'
```
```
{
    "name":"Resource"
}
```

#### refresh_token
```bash
curl --location --request POST 'http://127.0.0.1:8080/oauth2/token?grant_type=refresh_token&refresh_token=refresh_token' \
  --header 'Authorization: Basic ZGVtby1jbGllbnQtaWQ6ZGVtby1jbGllbnQtc2VjcmV0'
```
```
{
    "access_token": "xxxxxx",
    "refresh_token": "yyyyy",
    "scope": "user_info",
    "token_type": "Bearer",
    "expires_in": 6152
}
```

##### client_credentials 认证流程
- 使用POST访问获取`access_token`接口，设置`grant_type=client_credentials`
```
curl --location --request POST 'http://127.0.0.1:8080/oauth2/token?grant_type=client_credentials&scope=user_info' \
    --header 'Authorization: Basic ZGVtby1jbGllbnQtaWQ6ZGVtby1jbGllbnQtc2VjcmV0'
```
```
{
    "access_token": "xxxxxx",
    "scope": "user_info",
    "token_type": "Bearer",
    "expires_in": 7199
}
```

##### scop权限范围验证
- 使用POST访问获取`access_token`接口,设置`grant_type=client_credentials`，`scope=message.read`
```bash
> curl --location --request POST 'http://127.0.0.1:8080/oauth2/token?grant_type=client_credentials&scope=message.read' --header 'Authorization: Basic ZGVtby1jbGllbnQtaWQ6ZGVtby1jbGllbnQtc2VjcmV0'
```
```
# 返回的 token 的权限范围是 message.read
{
    "access_token": "xxxxxx",
    "scope": "message.read",
    "token_type": "Bearer",
    "expires_in": 7199
}
```

- 使用拥有`message.read`权限的toke访问用户接口，被拒绝，提示无权限
```
curl --location --request POST 'http://127.0.0.1:8082/user/info' --header 'Authorization: Bearer token'
```
```
{
    "error": "insufficient_scope",
    "error_description": "Insufficient scope for this resource",
    "scope": "user_info"
}
```

> 所有的127.0.0.1不能使用localhost代替，因为auth会检查域名的合法性，数据库中登记的是127.0.0.1

## 集成认证、授权服务
这里为了演示如何集成HiAuth、提供了一个Demo项目`HiMall`。

### HiMall
HiMall是基于`SpringBoot`技术的微服务项目，其集成了`HiAuth`的认证、授权。

### 环境需求
- JDK17+
- HiAuth3

### 安装、启动
```
#编译、构建项目
>cd HiAuth\hiaut-himall
>mvn clean install
>mvn spring-boot:run
```
### 验证`authorization_code`模式认证
- 访问地址：http://127.0.0.1:8081
- 点击Login，会被重定向到HiAuth系统进行认证
- 认证通过后会被重定向回HiMall，此时HiMall也将持有登录状态


## 授权协议
本项目执行 [MIT](https://github.com/bestaone/HiAuth/blob/master/LICENSE) 协议


## 社区与作者
<p align="center">
  <img width="900" src="https://hiauth.oss-cn-zhangjiakou.aliyuncs.com/community_wechat.jpg">
</p>


>如果群二维码失效了，请先添加我的微信，然我我拉你入群。