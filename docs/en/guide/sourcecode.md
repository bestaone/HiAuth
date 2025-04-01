# Source Code Edition {#sourcecode}
The integration process for the source code edition is similar to the Docker edition, except that the Docker version of HiAuth is replaced with a source code compilation and startup.

## Environment Requirements {#env-demand}
- Git
- JDK 17+
- Maven 3.8+
- Node.js v20.15+
- pnpm 9.14+
- PostgreSQL 16+
- Redis

## Installation and Integration Steps
- Check the server environment
- Download the source code
- Initialize the HiAuth database by executing SQL scripts
- Configure the HiAuth startup configuration file
- Compile the source code and start the service
- Verify using the HiAuth source code demo

### Check the Server Environment {#check-env}
```shell
# Check the git version
$ git --version
git version 1.8.3.1

# Check the JDK version
$ java -version
openjdk version "17.0.5" 2022-10-18

# Check the Maven version
$ mvn -v
Apache Maven 3.8.6 (36645f6c9b5079805ea5009217e36f2cffd34256)

# Check the Node.js version
$ node -v
v20.15.0

# Check the pnpm version
$ pnpm -v
9.14.2
```

### Download the Source Code {#download-source}
```shell
$ git clone https://github.com/bestaone/HiAuth.git
```

### Initialize the HiAuth Database by Executing SQL Scripts {#init-db}
- Install `PostgreSQL 16+`;
- Create a database named `hiauth`;
- Execute the initialization script `HiAuth/other/hiauth.sql`;

### Configure the HiAuth Startup Configuration File {#config-file}
- Modify the configuration file `HiAuth/cicd/hiauth.properties` to your own settings;
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
- Apply the configuration file by modifying `HiAuth/hiauth-server/src/main/resources/application.yml`;
```yaml
...
# Replace `/hiauth/conf/hiauth.properties` with the file path you configured above
spring.config.import: ${CONFIG_FILE:optional:/hiauth/conf/hiauth.properties}
...
```

### Compile the Source Code and Start the Service {#compile-start}
```shell
# Compile and start the backend service
$ cd HiAuth/hiauth-server
$ mvn clean install
$ mvn spring-boot:run

# Compile and start the frontend service
$ cd HiAuth/hiauth-front
$ pnpm install
$ pnpm dev:auth

# Access the service
$ curl http://127.0.0.1:8080
{ "code": 50000, "message": "Invalid or expired token" }
```
- Check the backend service: http://127.0.0.1:8080
- Check the frontend service: http://127.0.0.1:5666/admin (The port may change, please check the console)

### Verify Using the HiAuth Source Code Demo {#hiauth-himall}
- Modify the configuration `HiAuth/example/himall/src/main/resources/application.yml`
```yaml
...
spring.security.oauth2.client:
  provider:
    hiauth-server:
      # Change the value of issuer-uri from http://auth.hiauth.cn to http://127.0.0.1:8080
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
<iframe src="//player.bilibili.com/player.html?bvid=BV1KhZEYmERF&page=1" allowfullscreen></iframe>