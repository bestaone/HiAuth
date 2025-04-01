# Docker Edition {#docker}

The integration process for the Docker edition is similar to the SaaS edition, except that before integration, we need to deploy a Docker version of HiAuth. The local installation and deployment of the Docker edition rely on `PostgreSQL 16+` and `Redis`. If you need to run the HiAuth demo, you will also need a `JDK 17` and `Maven 3.8+` environment.

## Installation and Integration Steps:
- Check the server environment
- Initialize the HiAuth database by executing SQL scripts
- Configure the HiAuth startup configuration file
- Download the image and start the service
- Verify using the HiAuth source code demo

### Check the Server Environment {#check-env}
```shell
# Check the git version
$ git --version
git version 1.8.3.1

# Check the Docker version
$ docker -v
Docker version 26.1.4, build 5650f9b

# Check the JDK version
$ java -version
openjdk version "17.0.5" 2022-10-18

# Check the Maven version
$ mvn -v
Apache Maven 3.8.6 (36645f6c9b5079805ea5009217e36f2cffd34256)
```

### Initialize the HiAuth Database by Executing SQL Scripts {#init-db}
- Install `PostgreSQL 16+`;
- Create a database named `hiauth`;
- Execute the initialization script `HiAuth/other/hiauth.sql`;

### Configure the HiAuth Startup Configuration File {#config-file}
- Create the configuration file `/opt/install/hiauth/conf/hiauth.properties` and configure the correct parameters;
```properties [hiauth.properties]
# Only PostgreSQL is supported
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
> Configuration reference: [hiauth.properties](https://github.com/bestaone/HiAuth/blob/master/other/hiauth.properties)

### Download the Image and Start the Service {#download-image}
```shell
# You need to be able to access the Docker Hub central repository, which may require a proxy.
$ docker run -d \
  --restart=always \
  -p 9080:80 -p 8080:8080 \
  -v /opt/install/hiauth/conf:/hiauth/conf \
  -v /opt/install/hiauth/logs:/hiauth/logs \
  --name hiauth bestaone/hiauth:3.0.0
  
# If you cannot access the Docker Hub central repository, you can download the image from the Alibaba Cloud repository.
$ docker run -d \
  --restart=always \
  -p 9080:80 -p 8080:8080 \
  -v /opt/install/hiauth/conf:/hiauth/conf \
  -v /opt/install/hiauth/logs:/hiauth/logs \
  --name hiauth registry.cn-zhangjiakou.aliyuncs.com/bestaone/hiauth:3.0.0
  
# Check the images  
$ docker images
REPOSITORY                                                 TAG           IMAGE ID       CREATED         SIZE
bestaone/hiauth                                            3.0.0         c5e4140bd5aa   3 hours ago     810MB
registry-vpc.cn-zhangjiakou.aliyuncs.com/bestaone/hiauth   3.0.0         c5e4140bd5aa   3 hours ago     810MB

# Check the services
$ docker ps
CONTAINER ID   IMAGE                   COMMAND                  CREATED        STATUS       PORTS                                                  NAMES
3ea0fdb8a165   bestaone/hiauth:3.0.0   "/hiauth/run.sh"         3 hours ago    Up 3 hours   8080/tcp, 0.0.0.0:9080->80/tcp, :::9080->80/tcp        hiauth

# Check the logs
$ docker logs 3ea0fdb8a165
...
INFO 7 [main] org.springframework.boot.web.embedded.tomcat.TomcatWebServer Tomcat started on port 8080 (http) with context path '/'
INFO 7 [main] cn.hiauth.server.ServerStarter Started ServerStarter in 10.094 seconds (process running for 11.107)
...

# Access the service
$ curl http://127.0.0.1:9080
{ "code": 50000, "message": "Invalid or expired token" }
```
- The authorization address for the Docker edition of HiAuth is: http://127.0.0.1:9080
- The management address for the Docker edition of HiAuth is: http://127.0.0.1:9080/admin

### Verify Using the HiAuth Source Code Demo {#hiauth-himall}
- Download the source code
```shell
$ git clone https://github.com/bestaone/HiAuth.git
```
- Modify the configuration `HiAuth/example/himall/src/main/resources/application.yml`
```yaml
...
spring.security.oauth2.client:
  provider:
    hiauth-server:
      # Change the value of issuer-uri from http://auth.hiauth.cn to http://127.0.0.1:9080
      issuer-uri: http://auth.hiauth.cn
...
```
- Compile and run
```shell
$ cd HiAuth/example/himall
$ mvn clean install
$ mvn spring-boot:run
```

### Verification
- Open a browser and visit: http://127.0.0.1:9000
- Click the `Login` button, and you will be redirected to the unified authentication system. Enter the account: `corpadmin`, password: `123456`
- After successful login, you will see the home page and the logged-in user information!

## Video Tutorial
<iframe src="//player.bilibili.com/player.html?bvid=BV14hZEYmEEq&page=1" allowfullscreen></iframe>