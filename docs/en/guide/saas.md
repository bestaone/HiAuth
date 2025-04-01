# SaaS Edition {#saas}

To integrate the SaaS edition, users need to sign up for an account on HiAuth, add applications, and perform relevant settings. Then, they can use the obtained `client-id` and `client-secret` for integration. For a quick validation, we will use the demo account provided in the system for integration. After completing the validation, users can replace it with their own account.

- The HiAuth authorization address is: http://auth.hiauth.cn
- The HiAuth management address is: http://auth.hiauth.cn/admin

## Example of Integrating with a SpringBoot Project
- Create a SpringBoot project
- Add dependencies
- Add configurations
- Add a home page

Integration with the SaaS edition can be completed with just these simple steps.

## Running the HiAuth Source Code Demo {#hiauth-demo}
### Environment Requirements
- Git
- JDK 17+
- Maven 3.8+

### Running Script
```shell
# Download HiAuth source code
$ git clone https://github.com/bestaone/HiAuth.git
# Enter the demo directory
$ cd HiAuth/example/himall
# Compile and install
$ mvn clean install
# Run
$ mvn spring-boot:run
```
### Verification
- Open a browser and visit: http://127.0.0.1:9000
- Click the `Login` button, and you will be redirected to the unified authentication system. Enter the account: `corpadmin`, password: `123456`
- After successful login, you will see the home page and the logged-in user information!

## Step-by-Step Integration {#hand-by-hand}
### Environment Requirements
- JDK 17+
- Maven 3.8+

### Create an Empty SpringBoot Project

Use [Spring Initializr](https://start.spring.io/) to create an empty SpringBoot project. The `pom.xml` file is as follows:

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
		<version>3.4.3</version>
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

### Add Dependencies
```xml [pom.xml]
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-client</artifactId>
</dependency>
```

### Add Configurations
```yml [application.yml]
server.port: 9000
spring.security.oauth2.client:
  provider:
    # Authentication server information
    hiauth-server:
      # If you have deployed HiAuth privately, replace this address with the private deployment authentication server address
      issuer-uri: http://auth.hiauth.cn
      authorizationUri: http://auth.hiauth.cn/oauth2/authorize
      # Token acquisition address
      tokenUri: http://auth.hiauth.cn/oauth2/token
      userInfoUri: http://auth.hiauth.cn/userinfo
      jwkSetUri: http://auth.hiauth.cn/oauth2/jwks
      #userNameAttribute: name
  registration:
    hiauth-code:
      # Authentication provider, indicating which authentication server to use for authentication, associated with the above hiauth-server
      provider: hiauth-server
      # Client name
      client-name: himall
      # Client ID, obtained from the authentication platform
      client-id: himall
      # Client secret
      client-secret: secret
      # Client authentication method client_secret_basic\client_secret_post
      client-authentication-method: client_secret_basic
      # Use authorization code mode to obtain token
      authorization-grant-type: authorization_code
      # Callback address after authentication, this address needs to be registered in the oauth2_registered_client table,
      # otherwise the callback will be rejected
      redirect-uri: http://127.0.0.1:9000/login/oauth2/code/hiauth-code
      scope:
        - profile
        - openid
```
> Note: After adding the `application.yml` file, delete the `application.properties` file.

### Add a Controller
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
        // After authentication, the accessToken will be obtained
        String accessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // Set the request header and include the accessToken in it
        headers.add("Authorization", "Bearer " + accessToken);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        // Request the authentication server to obtain user information
        return restTemplate.postForObject(this.userInfoUri, entity, Map.class);
    }

}
```

### Verification

- Start the project and visit: http://127.0.0.1:9000. The system will detect that you are not logged in and redirect you to the unified authentication system;
- Enter the account: `corpadmin`, password: `123456`. After successful login, you will be redirected back to the application;
- In this demo, the home page directly outputs the logged-in user information in `json` format;
```json
{
  "sub": "corpadmin",
  "empId": 1,
  "avatarUrl": "/unpapi/image/2c924149ddfe4bd181959ee9bede10c0.jpeg",
  "appId": 91,
  "name": "Corporate Administrator",
  "phoneNum": "13400000001",
  "userId": 11,
  "authorities": [],
  "cid": 1,
  "username": "corpadmin"
}
```

## Video Tutorial
<iframe src="//player.bilibili.com/player.html?bvid=BV1KhZEYmEf1&page=1" scrolling="no" allowfullscreen></iframe>