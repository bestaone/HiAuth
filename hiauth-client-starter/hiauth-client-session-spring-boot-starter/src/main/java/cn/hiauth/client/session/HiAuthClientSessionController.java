package cn.hiauth.client.session;

import cn.hiauth.client.Authentication;
import cn.hiauth.client.SessionContextHolder;
import cn.hiauth.client.UserinfoVo;
import cn.webestar.scms.commons.R;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/")
public class HiAuthClientSessionController {

    @ResponseBody
    @GetMapping(value = "/api/common/user/info")
    public R<UserinfoVo> userInfo() {
        Authentication auth = SessionContextHolder.getContext().getAuth();
        return R.success(UserinfoVo.toVo(auth));
    }

}
