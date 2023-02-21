### 参考demo

- demo参考的文章
https://blog.csdn.net/friendlytkyj/article/details/128889875

- 解析的很透彻的文章
https://blog.csdn.net/sjdl9396/article/details/122564492


https://blog.csdn.net/baidu_28068985/article/details/128431612

### 测试
启动 auh、client、resource服务后，访问：http://127.0.0.1:8081/hello 账号：test\test

- authorization_code
首先在代码中将对应的client的redirectUri加上：http://www.baidu.com,然后浏览器中访问
```
http://127.0.0.1:8080/oauth2/authorize?client_id=demo-client-id&response_type=code&scope=user_info&redirect_uri=http://www.baidu.com
```
在跳转的url中能够去到code，接下来可以使用这个code换取accessToken
```bash
>curl --location --request POST 'http://127.0.0.1:8080/oauth2/token?grant_type=authorization_code&code=code&redirect_uri=http://www.baidu.com' --header 'Authorization: Basic ZGVtby1jbGllbnQtaWQ6ZGVtby1jbGllbnQtc2VjcmV0'
```

- refresh_token
```bash
>curl --location --request POST 'http://127.0.0.1:8080/oauth2/token?grant_type=refresh_token&refresh_token=refresh_token' --header 'Authorization: Basic ZGVtby1jbGllbnQtaWQ6ZGVtby1jbGllbnQtc2VjcmV0' 
```

- client_credentials
```bash
>curl --location --request POST 'http://127.0.0.1:8080/oauth2/token?grant_type=client_credentials&scope=user_info' --header 'Authorization: Basic ZGVtby1jbGllbnQtaWQ6ZGVtby1jbGllbnQtc2VjcmV0'
```

- 访问resource服务接口
```bash
>curl --location --request POST 'http://127.0.0.1:8082/user/info' --header 'Authorization: Bearer token' 
```


curl --location --request GET 'http://127.0.0.1:8080/oauth2/authorize?client_id=demo-client-id&client_secret=demo-client-secret&response_type=code&redirect_uri=http://127.0.0.1:8081/login/oauth2/code/client-id-1'