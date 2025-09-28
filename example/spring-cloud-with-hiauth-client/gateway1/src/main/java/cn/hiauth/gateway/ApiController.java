package cn.hiauth.gateway;

import cn.hiauth.client.Authentication;
import cn.hiauth.client.SessionContextHolder;
import cn.hiauth.client.UserinfoVo;
import cn.webestar.scms.commons.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/common")
public class ApiController {

    @ResponseBody
    @GetMapping(value = "/api/userinfo")
    public R<UserinfoVo> userinfo() {
        Authentication auth = SessionContextHolder.getContext().getAuth();
        return R.success(UserinfoVo.toVo(auth));
    }

}