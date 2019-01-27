package com.bestaone.aiwan.auth.web.config.smscode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

//模拟缓存
public class Cache {

    private static Logger LOGGER = LoggerFactory.getLogger(Cache.class);

    private static String code = "0000";

    public static void send(String telNo, String code){
        LOGGER.info("发送短信验证码{}到{}", code, telNo);
        Cache.code = code;
    }

    public static String get(String telNo){
        return Cache.code;
    }

}
