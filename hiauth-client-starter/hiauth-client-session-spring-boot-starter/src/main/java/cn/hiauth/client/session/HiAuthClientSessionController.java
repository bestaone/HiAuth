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
    @PostMapping(value = "/api/common/myOrgs")
    public R<List<SecurityOrg>> myOrgs() {
        Authentication auth = SessionContextHolder.getContext().getAuth();
        List<SecurityOrg> orgs = securityService.loadUserOrgs(auth.getUserId());
        return R.success(orgs);
    }

    @ResponseBody
    @PostMapping(value = "/api/common/intoOrgSpace")
    public R<Boolean> intoOrgSpace(@RequestParam(name = "orgId", required = false) Long orgId) {
        return R.success(securityService.intoOrgSpace(orgId));
    }

}
