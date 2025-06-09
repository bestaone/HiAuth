package cn.hiauth.client.session;

import cn.hiauth.client.*;
import cn.webestar.scms.commons.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class HiAuthClientSessionController {

    @Autowired(required = false)
    private SecurityService securityService;

    @ResponseBody
    @GetMapping(value = "/api/common/user/info")
    public R<UserinfoVo> userInfo() {
        Authentication auth = SessionContextHolder.getContext().getAuth();
        return R.success(UserinfoVo.toVo(auth));
    }

    @ResponseBody
    @PostMapping(value = "/api/common/myCorps")
    public R<List<SecurityCorp>> myCorps() {
        Authentication auth = SessionContextHolder.getContext().getAuth();
        List<SecurityCorp> corps = securityService.loadUserCorps(auth.getUserId());
        return R.success(corps);
    }

    @ResponseBody
    @PostMapping(value = "/api/common/switchCorp")
    public R<Boolean> switchCorp(@RequestParam("id") Long id) {
        return R.success(securityService.switchCorp(id));
    }

}
