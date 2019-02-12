package com.bestaone.aiwan.auth.web.config.smscode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

//模拟缓存
public class Cache {

    private static Logger LOGGER = LoggerFactory.getLogger(Cache.class);

    private static Map<String,String> data = new HashMap<>();

    public static void put(String key, String code){
        LOGGER.info("设置验证码{}到{}", code, key);
        data.put(key,code);
    }

    public static String get(String key){
        return data.get(key);
    }

}
