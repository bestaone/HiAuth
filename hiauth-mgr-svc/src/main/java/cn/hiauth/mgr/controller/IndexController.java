package cn.hiauth.mgr.controller;

import cn.hiauth.mgr.common.BaseController;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController extends BaseController {

    @Override
    public IService getService() {
        return null;
    }

    @ResponseBody
    @GetMapping({"/test"})
    public String test() {
        return "test";
    }

}
