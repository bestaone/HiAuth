package cn.hiauth.mgr.controller;

import cn.hiauth.mgr.common.BaseController;
import cn.hiauth.mgr.domain.Manager;
import cn.hiauth.mgr.service.ManagerService;
import com.baomidou.mybatisplus.extension.service.IService;
//import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Api(tags = {"用户"})
@RequestMapping("/api/manager")
@RestController
public class ManagerController extends BaseController<Manager> {

    @Autowired
    private ManagerService service;

    @Override
    public IService getService() {
        return service;
    }

}
