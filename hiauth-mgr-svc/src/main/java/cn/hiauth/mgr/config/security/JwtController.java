package cn.hiauth.mgr.config.security;

import cn.hiauth.mgr.common.ApiResponse;
import cn.hiauth.mgr.common.Assert;
import cn.hiauth.mgr.service.UserService;
import cn.hiauth.mgr.utils.CacheUtil;
import cn.hiauth.mgr.utils.JwtTokenUtil;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@Api(value = "JWT", tags = {"JWT"})
@RestController
public class JwtController {

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private UserService userService;

//    @ApiOperation(value = "刷新token")
    @GetMapping(value = {"/refreshToken"})
    public ApiResponse refreshToken(@RequestHeader("Authorization") String authHeader, @RequestParam("token") String refreshToken) {

        String oldToken = authHeader.replace("Bearer ", "");
        String cacheToken = (String) cacheUtil.get(SecurityConstant.REFRESH_TOKEN_KEY + refreshToken);
        Assert.isTrue(oldToken.equals(cacheToken), 50000, "invalid refreshToken.");

        String newToken = JwtTokenUtil.refreshToken(oldToken);
        cacheUtil.rename(SecurityConstant.ACCESS_TOKEN_KEY + oldToken, SecurityConstant.ACCESS_TOKEN_KEY + newToken);
        cacheUtil.expire(SecurityConstant.ACCESS_TOKEN_KEY + newToken, SecurityConstant.ACCESS_TOKEN_EXPIRE);
        cacheUtil.set(SecurityConstant.REFRESH_TOKEN_KEY + refreshToken, newToken);

        Map map = new HashMap();
        map.put("accessToken", newToken);
        map.put("expire", SecurityConstant.ACCESS_TOKEN_EXPIRE);

        return ApiResponse.success(map);
    }

//    @ApiOperation(value = "注册")
    @PostMapping(value = {"/regist"})
    public ApiResponse regist(@RequestBody Map registRequest) {
        return ApiResponse.success();
    }

}
