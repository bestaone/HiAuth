
# AIWAN

## 功能介绍

- 这个脚手架可以帮助你快速启动一个基于spring技术栈的微服务项目开发（不包含服务治理等，便于自己集成spring cloud、k8s等）
- 包含一个基于spring-cloud-starter-oauth2的oauth2认证服务
- 认证服务器支持了图形验证码、短信验证码功能
- 包含了两个微服务的演示模块misc、order，需要通过oauth2认证后才能访问
- 包含一个web demo web-crm，演示如何通过oauth2授权后访问各个微服务
- 演示web服务通过oauth2认证后，调用微服务接口获取数据的完整流程
- 演示了如何通过starter扩展功能(oauth2、swagger2、monitor)
- 演示了如何统一控制接口规范
- 演示了如何规范异常处理
- 演示了如何规范使用mybaits、分页
- 演示了单元测试、mock测试、测试数据回滚，包括对controller、service的测试
- 演示了swagger2通过oauth2认证后查看功能

## 目录介绍

- aiwan-common : 公共模块，提供比较通用的功能，比如：最基本的异常基类、接口基类、工具类等
- aiwan-core ： 微服务模块的核心包，提供一些核心的、通用的系统控制。例如：Service、Mapper的统一控制、通用拦截等
- aiwan-microsvr-api ： 微服务业务接口定义，将所有接口打成jar，便于在client中使用
- aiwan-microsvr-misc ： misc微服务，包含各种不易归类的业务功能接口，这里面包好了日志接口
- aiwan-microsvr-order ： order微服务，包含订单业务接口 [README.MD](https://github.com/bestaone/MicroServices1/blob/master/aiwan-microsvr-order/README.md)
- aiwan-starter-monitor ： 监控功能的starter项目
- aiwan-starter-oauth2 ： oauth2功能的starter项目
- aiwan-starter-swagger2 ： swagger2功能的starter项目
- aiwan-web-auth ： 授权服务 [README.MD](https://github.com/bestaone/MicroServices1/blob/master/aiwan-web-auth/README.md)
- aiwan-web-crm ： crm演示项目 [README.MD](https://github.com/bestaone/MicroServices1/blob/master/aiwan-web-crm/README.md)


## 快速启动

### 创建数据库

- 数据使用的是mysql5.6
- 创建数据库user，运行脚本 aiwan-web-auth/doc/user.sql
- 创建数据库misc，运行脚本 aiwan-microsvr-misc/doc/misc.sql
- 创建数据库order，运行脚本 aiwan-microsvr-order/doc/order.sql
- 默认数据库配置为，如果不正确请修改 resources\application.properties 文件
```
spring.datasource.url = jdbc:mysql://127.0.0.1:3306/user
spring.datasource.username = root
spring.datasource.password = 123456
```

### 构建、测试

- 执行如下命名会编译、测试
```
cd aiwan
mvn clean install
```

### 启动

- 这个项目包含4个可运行项目，分别是auth（端口：8080）、crm（端口：8081）、misc（端口：9080）、order（端口：9081）
- 启动这4个项目
```
cd aiwan-web-auth
mvn spring-boot:run

cd aiwan-web-crm
mvn spring-boot:run

cd aiwan-microsvr-misc
mvn spring-boot:run

cd aiwan-microsvr-order
mvn spring-boot:run
```

### 验证

#### CRM验证
- 访问crm主页 http://localhost:8081
- 被重定向到了auth服务进行认证，输入账号登录
- 被重定向回crm系统，进入crm首页，登录成功

#### auth验证
- 访问auth主页 http://localhost:8080
- 输入账号登录
- 进入首页

#### swagger2验证
- 访问misc项目swagger2 ui地址 http://127.0.0.1:9080/swagger-ui.html
- 直接测试接口，显示未认证
- 点击认证按钮，会被重定向到auth进行认证，输入账号登录
- 被重定向到misc的swagger2页面
- 再次测试接口，获取正确数据

> 注意：所有的localhost不能使用127.0.0.1代替，因为auth会检查域名的合法性，数据库中登记的是localhost