## Spring MVC

### Restful
- url描述资源，用http方法来描述行为
- rest 成熟度模型：level0:http协议，level1:使用url描述资源，level2:http verbs（使用方法描述行为），level3:Hypermedia Controls

### 单元测试
参考:com.com.bestaone.aiwan.account.web.controller.UserControllerTest

```
增加
post http://127.0.0.1:8080/user
{
	"username":"user",
	"password":"123456"
}
```

```
删除
delete http://127.0.0.1:8080/user/1
```


```
修改
put http://127.0.0.1:8080/user/1
{
	"username":"user",
	"password":"123456"
}
```

```
查询
get http://127.0.0.1:8080/user/1
```

### @Valid & @JsonView & 自定义Constraint
- @Valid 添加到controller中表示对dto进行校验
- @JsonView 表示使用对应的View进行转换，需要在在controller中置顶view
- 自定义Constraint,当已有的校验器无法满足使用时，可以自定义校验器

### 异常处理
参考：ControllerExceptionHandler

### Filter and Interceptor
Filter 属于svelet内容，所以获取不到spring上下文数据，如果需要spring上下文数据，需要使用Interceptor
Filter的添加有两种方法
1.@Component
2.使用
```
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private TimeInterceptor timeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}
	
	@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);
		return registrationBean;
	}

}

```
添加Interceptor与Filter不同，不仅需要@Component还需要 registry.addInterceptor(timeInterceptor);

### 文件处理
参考：com.com.bestaone.aiwan.account.web.controller.UserControllerTest:whenUploadSuccess

### 异步服务
- Callable<?> 提高容器的被压能力
测试：post http://127.0.0.1:8080/async/order

- DeferredResult<?> 适用于后段服务的异步运行

### Swagger

### WireMock
官网地址：http://wiremock.org
使用方法：
- 运行服务 java -jar xxx.jar 
- 使用sdk，连接服务，写入规则
