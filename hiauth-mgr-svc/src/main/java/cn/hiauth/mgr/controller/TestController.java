package cn.hiauth.mgr.controller;

import cn.hiauth.mgr.common.ApiResponse;
import cn.hiauth.mgr.domain.User;
import cn.hiauth.mgr.advice.IgnoreResponseBody;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//@Api(tags = {"测试接口"})
@RestController
public class TestController {

    @GetMapping({"/test/11"})
    public String test11() {
        return "test11";
    }

    @GetMapping({"/test/12"})
    public int test12() {
        return 123;
    }

    @GetMapping({"/test/13"})
    public Integer test13() {
        return null;
    }

    @GetMapping({"/test/14"})
    public void test14() {

    }

//    @ApiOperation(value = "测试返回值统一处理")
    @GetMapping({"/test/21"})
    public User test21() {
        User uer = new User();
        uer.setId(100000000000000002L);
        uer.setUsername("test2");
        return uer;
    }

    @IgnoreResponseBody
    @GetMapping({"/test/22"})
    public User test22() {
        User uer = new User();
        uer.setId(100000000000000003L);
        uer.setUsername("test3");
        return uer;
    }

    @GetMapping({"/test/23"})
    public ApiResponse test23() {
        User uer = new User();
        uer.setId(100000000000000005L);
        uer.setUsername("test5");
        return ApiResponse.success(uer);
    }

    @GetMapping({"/test/31"})
    public List<User> test31() {
        List<User> list = new ArrayList<>();
        User uer1 = new User();
        uer1.setId(100000000000000001L);
        uer1.setUsername("test1");
        User uer2 = new User();
        uer2.setId(100000000000000002L);
        uer2.setUsername("test2");
        list.add(uer1);
        list.add(uer2);
        return list;
    }
    @GetMapping({"/test/41"})
    public String test41() throws Exception {
        throw new Exception("系统异常");
    }

    @GetMapping({"/test/42"})
    public Integer test42() {
        return 1/0;
    }

}
