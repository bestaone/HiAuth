package cn.hiauth.mgr.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class CacheHelperTransfer {

    @Value("${app.cache.prefix}")
    private String prefix;

    @Value("${mybatis-plus.configuration.cache-expire:300}")
    private Integer expire;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        CacheHelper.setRedisTemplate(redisTemplate);
        CacheHelper.setPrefix(prefix + ":mapper");
        CacheHelper.setExpire(expire);
    }

}
