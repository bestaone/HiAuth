
# AIWAN | 爱玩商城

## 功能介绍
- 这个脚手架可以帮助你快速启动一个基于spring技术栈的微服务项目开发
- 包含一个基于spring-cloud-starter-oauth2的oauth2认证服务
- 认证服务器支持了图形验证码、短信验证码功能
- 包含了两个微服务的演示模块goods、order，需要通过oauth2认证后才能访问
- 包含一个web demo web-mall，演示如何通过oauth2认证后访问各个微服务
- 演示web服务通过oauth2认证后，调用微服务接口获取数据的完整流程
- 演示了如何通过starter扩展功能(oauth2、swagger2、monitor)
- 演示了如何统一控制接口规范
- 演示了如何规范异常处理
- 演示了如何规范使用mybaits、分页
- 演示了单元测试、mock测试、测试数据回滚，包括对controller、service的测试
- 演示了swagger2通过oauth2认证后查看功能
- 不打算集成spring cloud，个人比较喜欢service mesh，后面会集成Kubernetes、Istio

## 目录介绍
- aiwan-common : 公共模块，提供比较通用的功能，比如：最基本的异常基类、接口基类、工具类等
- aiwan-core ： 微服务模块的核心包，提供一些核心的、通用的系统控制。例如：Service、Mapper的统一控制、通用拦截等
- aiwan-microsvr-api ： 微服务业务接口定义，将所有接口打成jar，便于在client中使用
- aiwan-microsvr-goods ： goods微服务，包含商品业务接口
- aiwan-microsvr-order ： order微服务，包含订单业务接口 [ [文档](https://github.com/bestaone/Aiwan/blob/master/aiwan-microsvr-order/README.md)：介绍如何开发服务接口 ]
- aiwan-starter-monitor ： 监控功能的starter项目
- aiwan-starter-oauth2 ： oauth2功能的starter项目
- aiwan-starter-swagger2 ： swagger2功能的starter项目
- aiwan-web-auth ： 授权服务 [ [文档](https://github.com/bestaone/Aiwan/blob/master/aiwan-web-auth/README.md)：介绍如何开发认证服务 ]
- aiwan-web-mall ： mall演示项目 [ [文档](https://github.com/bestaone/Aiwan/blob/master/aiwan-web-mall/README.md)：介绍如何集成认证服务 ]


## 快速启动

### 创建数据库
- 数据使用的是mysql5.6
- 创建数据库user，运行脚本 aiwan-web-auth/doc/user.sql
- 创建数据库goods，运行脚本 aiwan-microsvr-goods/doc/goods.sql
- 创建数据库order，运行脚本 aiwan-microsvr-order/doc/order.sql
- 默认数据库配置为，如果不正确请修改 resources/application.properties 文件
```
spring.datasource.url = jdbc:mysql://127.0.0.1:3306/[user|goods|order]
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
- 这个项目包含4个可运行项目及端口，分别是auth:8080、mall:8081、goods:9080、order:9081
- 启动这4个项目，启动不分先后顺序
```
cd aiwan-web-auth
mvn spring-boot:run

cd aiwan-web-mall
mvn spring-boot:run

cd aiwan-microsvr-goods
mvn spring-boot:run

cd aiwan-microsvr-order
mvn spring-boot:run
```

### 验证

#### mall验证
- 访问mall主页 http://localhost:8081, 点击signin登录
- 被重定向到了auth服务进行认证，输入账号登录
- 被重定向回mall系统，进入mall首页，登录成功

#### auth验证
- 访问auth主页 http://localhost:8080
- 输入账号登录
- 进入首页

#### swagger2验证
- 访问goods项目swagger2 ui地址 http://localhost:9080/swagger-ui.html
- 直接测试接口，显示未认证
- 点击认证按钮，会被重定向到auth进行认证，输入账号登录
- 被重定向到goods的swagger2页面
- 再次测试接口，获取正确数据

#### oath2授权验证

##### authorization_code 认证流程
参看 [mall验证]
> 注意：如果授权环节不出现，可以清除下数据库的数据

##### password 认证流程
- post 访问 [http://localhost:8080/oauth/token?username=user&password=123456&grant_type=password&client_id=client&client_secret=123456]
- 正常访问后返回 json token
- get 访问 [http://localhost:9081/api/order/1]，返回401，未授权
- get 访问 [http://localhost:9081/api/order/1]，在请求头中添加凭证 Authorization Bearer -access_token-,能获取到数据
- 无权限拦截的测试 [http://localhost:8081/test/a] （未实现）

##### client_credentials 认证流程
- post 访问 [http://localhost:8080/oauth/token?grant_type=client_credentials&client_id=client&client_secret=123456&scope=read]
- 正常访问后返回 json token
- get 访问 [http://localhost:9081/api/order/1]，返回401，未授权
- get 访问 [http://localhost:9081/api/order/1]，在请求头中添加凭证 Authorization Bearer -access_token-,能获取到数据
- 无权限拦截的测试 [http://localhost:8081/test/a] （未实现）

##### scop权限范围验证
- post 访问 [http://localhost:8080/oauth/token?username=user&password=123456&grant_type=password&client_id=client&client_secret=123456&scope=write]
- 返回的 json token 的权限范围是 write
- get 访问 [http://localhost:9081/api/order/1]，在请求头中添加凭证 Authorization Bearer -access_token-，被拒绝（这个接口设置了需要read权限）

> 注意：所有的localhost不能使用127.0.0.1代替，因为auth会检查域名的合法性，数据库中登记的是localhost

## 贴图

### mall演示认证流程
![image](https://raw.githubusercontent.com/bestaone/Aiwan/master/doc/mall.gif)

### oauth2认证流程
![image](https://raw.githubusercontent.com/bestaone/Aiwan/master/doc/oauth2.gif)


## 未完事项
- 将认证服务和资源服务合并在一起的时候，验证authorization_code功能时，登录时报错“不支持 POST”,初步定为是 auth server 的SecurityConfig的HttpSecurity http未设置好
- auth 认证服务 user/profile 接口
- 整合docker
- 整合Kubernetes
- 整合Istio
