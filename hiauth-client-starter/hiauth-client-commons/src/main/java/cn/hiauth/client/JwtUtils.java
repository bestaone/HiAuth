package cn.hiauth.client;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    public static final String SUB_KEY = "sub";
    /**
     * 10小时
     */
//    public static final Integer EXPIRE = 60 * 60 * 10;
    private static final String KEY = "jwtsecret";

//    public static String generateToken(String sub) {
//        return generateToken(sub, EXPIRE);
//    }

    public static String generateToken(String sub, Integer expire) {

        Map<String, Object> payload = new HashMap<>(2);
        payload.put(SUB_KEY, sub);

        //过期时间
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireTime = now.plusSeconds(expire);
        payload.put(JWTPayload.EXPIRES_AT, expireTime);

        //签发时间
        payload.put(JWTPayload.ISSUED_AT, now);

        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);

        return generateToken(payload);
    }

    private static String generateToken(Map<String, Object> payload) {
        return JWTUtil.createToken(payload, KEY.getBytes());
    }

    public static JWT parseToken(String token) {
        return JWT.of(token);
    }

}
