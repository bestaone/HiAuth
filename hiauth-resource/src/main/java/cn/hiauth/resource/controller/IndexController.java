package cn.hiauth.resource.controller;

import cn.hiauth.resource.domain.Goods;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Tag(name = "Index", description = "index")
@EnableMethodSecurity
@Controller
public class IndexController {

    @ResponseBody
    @GetMapping("/api/messages")
    public String[] getMessages() {
        return new String[] {"Message 1", "Message 2", "Message 3"};
    }

    @ResponseBody
//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/api/user/info")
    public Map<String, Object> getUser(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Resource");
        return map;
    }

    @ResponseBody
    @RequestMapping(path = "/api/profile")
    public Map profile() {
        Map<String, Object> map = new HashMap();
        map.put("currnetUser", getUser());
        map.put("order", order());
        map.put("goods", goods());
        return map;
    }

    private Map getUser() {
        Map<String, Object> map = new HashMap();
        map.put("name", "张三");
        map.put("username", "zhangsan");
        map.put("tel", "13412345678");
        map.put("lastLoginTime", new Date());
        return map;
    }

    private Map order() {
        Map<String, Object> map = new HashMap();
        map.put("orderNo", "SN2019010112301");
        map.put("orderTitle", "Iphone X (深空灰色，256G) 1台");
        map.put("orderCreateTime", new Date());
        map.put("orderTotalAmount", 10000);
        return map;
    }

    private List<Goods> goods() {
        List<Goods> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            Goods g = new Goods();
            g.setId(i + 1L);
            g.setTitle("Iphone X 256G NO" + i);
            g.setPrice(10000f + i);
            g.setAmount(i);
            g.setCreateTime(new Date());
            list.add(g);
        }
        return list;
    }

}
