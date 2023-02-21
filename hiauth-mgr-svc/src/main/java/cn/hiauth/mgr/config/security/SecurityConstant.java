package cn.hiauth.mgr.config.security;

import cn.hiauth.mgr.utils.JwtTokenUtil;

public class SecurityConstant {

    public final static String API_RESULT = "{ \"code\": %d, \"message\": \"%s\" }";
    public static final Integer ACCESS_TOKEN_EXPIRE = JwtTokenUtil.EXPIRE;
    public static final Integer REFRESH_TOKEN_EXPIRE = 60 * 60 * 24 * 3;

    public final static String ACCESS_TOKEN_KEY = "security:accessToken:";
    public final static String REFRESH_TOKEN_KEY = "security:refreshToken:";

}
