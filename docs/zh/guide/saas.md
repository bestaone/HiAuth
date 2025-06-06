# SaaS版 {#saas}

SaaS版的集成，需要用户在HiAuth上开通账号、添加应，并进行相关的设置后，使用你获取的 `client-id` 和 `client-secret` 进行集成。这里为了快速验证，我们使用系统中提供的demo账号来进行集成。等完成验证后，用户可以替换成自己的账号即可。

- HiAuth 授权地址为：http://auth.hiauth.cn
- HiAuth 管理地址为：http://auth.hiauth.cn/admin

## 以SpringBoot项目为例，我们的集成需要以下几步：
- 创建一个SpringBoot项目
- 添加依赖
- 添加配置
- 添加一个首页

只需以上简单几步就可以完成集成SaaS版。

## 运行HiAuth源码自带demo {#hiauth-demo}
### 环境要求
- Git
- JDK 17+
- Maven 3.8+

### 运行脚本
```shell
# 下载HiAuth源码
$ git clone https://github.com/bestaone/HiAuth.git
# 进入demo
$ cd HiAuth/example/himall
# 编译和安装
$ mvn clean install
# 运行
$ mvn spring-boot:run
```
### 验证
- 浏览器访问: http://127.0.0.1:9000
- 点击`Login`按钮，会被重定向到统一认证系统，输入账号：`corpadmin`，密码：`123456`
- 登录成功后，会看到首页及登录用户信息!

## 手把手集成 {#hand-by-hand}
### 环境要求
- JDK 17+
- Maven 3.8+

### 创建一个空的SpringBoot项目

使用 [Spring Initializr](https://start.spring.io/) 创建一个空的SpringBoot项目。pom.xml 文件如下：

```xml [pom.xml]
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
	https://maven.apache.org/xsd/maven-4.0.0.xsd">
    
	<modelVersion>4.0.0</modelVersion>
    
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.4.5</version>
		<relativePath/>
	</parent>
    
	<groupId>cn.hiauth</groupId>
	<artifactId>demo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>demo</name>
	<description>Demo project for Spring Boot</description>
    
	<properties>
		<java.version>17</java.version>
	</properties>
    
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

### 添加依赖
```xml [pom.xml]
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-client</artifactId>
</dependency>
```

### 添加配置
```yml [application.yml]
server.port: 9000
spring.security.oauth2.client:
  provider:
    #认证服务器信息
    hiauth-server:
      # 如果你私有化部署了 HiAuth服务，请将此地址替换为私有部署的认证服务器地址
      issuer-uri: http://auth.hiauth.cn
      authorizationUri: http://auth.hiauth.cn/oauth2/authorize
      #令牌获取地址
      tokenUri: http://auth.hiauth.cn/oauth2/token
      userInfoUri: http://auth.hiauth.cn/userinfo
      jwkSetUri: http://auth.hiauth.cn/oauth2/jwks
      #userNameAttribute: name
  registration:
    hiauth-code:
      #认证提供者，标识由哪个认证服务器进行认证，和上面的hiauth-server进行关联
      provider: hiauth-server
      #客户端名称
      client-name: himall
      #客户端id，从认证平台申请的客户端id
      client-id: himall
      #客户端秘钥
      client-secret: secret
      #客户端认证方式 client_secret_basic\client_secret_post
      client-authentication-method: client_secret_basic
      #使用授权码模式获取令牌（token）
      authorization-grant-type: authorization_code
      # 认证完成后回调的地址，需要在数据库表oauth2_registered_client中登记这个地址，
      # 否则会拒绝回调
      redirect-uri: http://127.0.0.1:9000/login/oauth2/code/hiauth-code
      scope:
        - profile
        - openid
```
> 注意：添加完成 `application.yml` 文件后，将 `application.properties` 文件删除掉

### 添加一个Controller
```java [IndexController.java]
package cn.hiauth.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class IndexController {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${spring.security.oauth2.client.provider.hiauth-server.userInfoUri}")
    private String userInfoUri;

    @GetMapping("/")
    public Map<?, ?> index(Model model, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient oAuth2AuthorizedClient) {
        // 认证完后会获取到 accessToken
        String accessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 设置请求头，将 accessToken 放入请求头中
        headers.add("Authorization", "Bearer " + accessToken);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        // 请求认证服务器获取用户信息
        return restTemplate.postForObject(this.userInfoUri, entity, Map.class);
    }

}
```

### 验证

- 启动项目，访问: http://127.0.0.1:9000，系统检测到未登录，会被重定向到统一认证系统；
- 输入账号：`corpadmin`，密码：`123456`，登录成功后，会跳转回应用；
- 这个demo中。首页直接输出了登录用户信息，以`json`格式；
```json
{
  "sub": "corpadmin",
  "empId": 1,
  "avatarUrl": "/unpapi/image/2c924149ddfe4bd181959ee9bede10c0.jpeg",
  "appId": 91,
  "name": "企业管理员",
  "phoneNum": "13400000001",
  "userId": 11,
  "authorities": [],
  "cid": 1,
  "username": "corpadmin"
}
```

## 视频教程
<iframe src="//player.bilibili.com/player.html?bvid=BV1KhZEYmEf1&page=1" scrolling="no" allowfullscreen></iframe>

