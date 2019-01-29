# micro service order

## 说明
这个项目主要是单个微服务的集成实践，在你构架微服务项目时，提供参考

## 主要技术
- spring security
- mybatis
- oauth

## 安装、编译及启动
参考 [Aiwan](https://github.com/bestaone/Aiwan/blob/master/README.md)

## 技术介绍

### Restful
- url描述资源，用http方法来描述行为
- rest 成熟度模型：level0:http协议，level1:使用url描述资源，level2:http verbs（使用方法描述行为），level3:Hypermedia Controls
- 除了数据主键，其他入参使用content json，出参使用body json
```java

@RestController
@RequestMapping("/user")
public class UserController{

    //创建使用 POST /user  入参、出参使用JSON
	@PostMapping
	public ApiResponse<String> create(@Valid @RequestBody UserDto userDto){
        return ApiResponse.sucess();
	}

    //修改使用 PUT /user/{id}  入参、出参使用JSON
	@PutMapping("/{id:\\d+}")
	public ApiResponse update(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        return ApiResponse.sucess();
	}

    //删除使用 DELETE /user/{id}  
	@DeleteMapping("/{id:\\d+}")
	public ApiResponse delete(@PathVariable Long id) {
        return ApiResponse.sucess();
	}

    //查询使用 GET /user  入参、出参使用JSON
	@GetMapping
	public ApiResponse<PageVo<UserVo>> query(Integer pageNum, Integer pageSize, String name) {
        return ApiResponse.sucess();
	}

    //查询详细使用 GET /user/{id}  出参使用JSON
	@GetMapping("/{id:\\d+}")
	public ApiResponse<UserVo> getInfo(@PathVariable Long id){
		return ApiResponse.sucess();
	}

}

```

### 单元测试

- service做最基础的增删改查单元测试
```
//添加此注解可以使单元测试完后的数据库数据全部回滚，避免造成脏数据
@Transactional  
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    @Autowired
    public UserService service;

    @Test
    public void CRUDTest() {

        //CREATE
        User o = new User();
        o.setUsername("test");
        o.setPassword("test");
        o.setCreateTime(new Date());
        o.setName("CRUDTest");
        service.save(o);
        Assert.assertNotNull(o.getId());

        //READ
        o = service.findById(o.getId());
        Assert.assertNotNull(o.getId());

        //UPDATE
        o.setName("CRUDTest1");
        service.save(o);
        o = service.findById(o.getId());
        Assert.assertTrue(o.getName().equals("CRUDTest1"));

        //PAGE
        PageHelper.startPage(1,1);
        List<User> list = service.findAll();
        PageInfo<User> page = new PageInfo<>(list);
        Assert.assertTrue(page.getTotal()>0);

        //DELETE
        service.delete(o.getId());
        o = service.findById(o.getId());
        Assert.assertNull(o);

    }

}

```

- controller的单元测试

### 分页查询
- 引入插件
```
<dependency>
	<groupId>com.github.pagehelper</groupId>
	<artifactId>pagehelper-spring-boot-starter</artifactId>
	<version>1.2.10</version>
</dependency>
```

- 配置插件
```
pagehelper.helperDialect = mysql
pagehelper.supportMethodsArguments = true
pagehelper.autoRuntimeDialect = true
pagehelper.offsetAsPageNum = true
pagehelper.rowBoundsWithCount = true
pagehelper.reasonable = false
pagehelper.returnPageInfo = true
pagehelper.params = count=countSql
```

- 分页代码
```
@GetMapping
public ApiResponse<PageVo<User>> query(Integer pageNum, Integer pageSize, String name) {
	Page pageinfo = PageHelper.startPage(pageNum, pageSize);
	List<User> users = userService.findByName(name);
	return ApiResponse.sucess(new PageVo<>(pageinfo.getPageNum(), pageinfo.getPageSize(),pageinfo.getTotal(),pageinfo.getPages(),users));
}
```

### JsonView
- 使用JsonView标记Vo，使同一个返回数据，显示不同结果
- 在实际使用中，如果使用ApiResponse、PageVo等对Vo进行包装后，则无法生效
```
public class UserVo {
	
	public interface UserSimpleView {};
	public interface UserDetailView extends UserSimpleView {};

	@JsonView(UserSimpleView.class)
	private Long id;

	@JsonView(UserSimpleView.class)
	private String name;

	@JsonView(UserSimpleView.class)
	private String gender;

	@JsonView(UserSimpleView.class)
	private String username;

	@JsonView(UserDetailView.class)
	private String password;

	@JsonView(UserDetailView.class)
	private Date createTime;

}
```

### 时间传递
出、入参使用Long类型时间戳避免不同的格式化问题和时区问题
```
#剔除返回结果的null值
spring.jackson.default-property-inclusion = non_null
#返回时间类型时，使用Long时间戳
spring.jackson.serialization.write-dates-as-timestamps = true
```

### @Valid 和 BindingResult
- @Valid 申明需要进行参数校验
- @NotBlank @Past 参数校验的规则设置
- BindingResult 获取到参数校验的所有异常结果
```
//如果UserDto中有未通过校验的参数，则异常信息可以从BindingResult中获取
public ApiResponse update(@PathVariable Long id, @Valid @RequestBody UserDto userDto, BindingResult errors)
```

### 自定义参数校验器

- 定义注解
```
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {

	String message();
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
	
}

```

- 添加注解，申明需要校验
```
@MyConstraint(message = "这是一个测试")
private String username;
```

- 校验逻辑
```
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("my validator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		System.out.println(value);
		return true;
	}

}
```

### 异常处理
- 系统接口抛出的异常都要包含 code码和msg
```
public class CommonException extends Exception {

    private Integer code;
    private String msg;

    public CommonException(Integer code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public CommonException(Integer code, String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
        this.code = code;
    }

}

```

- Api接口需要统一处理异常
```
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse exceptionHandler(Exception e){
        CommonException ce = null;
        if(e instanceof CommonException){
            ce = (CommonException) e;
        } else {
            ce = new CommonException(50000, e.getMessage());
        }
        return ApiResponse.fail(ce);
    }

}
```

- 断言工具类，负责异常处理
```
public class Assert {

    public static void notNull(Object obj, Integer errorCode, String errorMsg) throws CommonException {
        if(obj==null){
            throwCommonException(errorCode, errorMsg);
        }
    }

    public static void isTrue(boolean expression, Integer errorCode, String errorMsg) throws CommonException {
        if(expression){
            throwCommonException(errorCode, errorMsg);
        }
    }

    private static void throwCommonException(Integer errorCode, String errorMsg) throws CommonException{
        throw new CommonException(errorCode, errorMsg);
    }

}

```

- 断言的使用
```
@PostMapping
public ApiResponse<String> create(@Valid @RequestBody UserDto userDto) throws CommonException {
	Assert.notNull(userDto.getUsername(),50000, "用户名不存在");
	Assert.notNull(userDto.getPassword(),50000, "密码不存在");
    ...
}
```

### swwarger
参考 aiwan-starter-swagger2 项目


### spring security 过滤链原理
未完成

### 单元测试
参考:com.bestaone.aiwan.order.web.controller.OrderControllerTest

```
增加
post http://127.0.0.1:8081/user
{
	"username":"user",
	"password":"123456"
}
```

```
删除
delete http://127.0.0.1:8081/user/1
```


```
修改
put http://127.0.0.1:8081/user/1
{
	"username":"user",
	"password":"123456"
}
```

```
查询
get http://127.0.0.1:8081/user/1
```

### @Valid & @JsonView & 自定义Constraint
- @Valid 添加到controller中表示对dto进行校验
- @JsonView 表示使用对应的View进行转换，需要在在controller中置顶view
- 自定义Constraint,当已有的校验器无法满足使用时，可以自定义校验器

### 异常处理
参考：com.bestaone.aiwan.core.advice.CommonExceptionHandler

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

### 异步服务
- Callable<?> 提高容器的背压能力
测试：post http://localhost:9081/api/async/order

- DeferredResult<?> 适用于后段服务的异步运行

### WireMock
官网地址：http://wiremock.org
使用方法：
- 运行服务 java -jar xxx.jar 
- 使用sdk，连接服务，写入规则
