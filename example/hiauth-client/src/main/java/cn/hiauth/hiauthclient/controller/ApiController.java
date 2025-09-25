package cn.hiauth.hiauthclient.controller;

import cn.hiauth.client.Authentication;
import cn.hiauth.client.SessionContextHolder;
import cn.webestar.scms.commons.R;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/getAuth")
    public R<Authentication> getAuth(HttpServletRequest request) {
        Authentication auth = SessionContextHolder.getAuthentication();
        return R.success(auth);
    }

}
