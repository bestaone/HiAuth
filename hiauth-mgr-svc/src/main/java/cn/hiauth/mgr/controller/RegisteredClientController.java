package cn.hiauth.mgr.controller;

import cn.hiauth.mgr.common.BaseController;
import cn.hiauth.mgr.domain.RegisteredClient;
import cn.hiauth.mgr.service.RegisteredClientService;
import com.baomidou.mybatisplus.extension.service.IService;
//import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Api(tags = {"用户"})
@RequestMapping("/api/registeredClient")
@RestController
public class RegisteredClientController extends BaseController<RegisteredClient> {

    @Autowired
    private RegisteredClientService service;

    @Override
    public IService getService() {
        return service;
    }

    @PostMapping("/add")
    public RegisteredClient add(@RequestBody RegisteredClient body) {
        String clientSecret = service.create(body);
        body.setClientSecret(clientSecret);
        return body;
    }

}
