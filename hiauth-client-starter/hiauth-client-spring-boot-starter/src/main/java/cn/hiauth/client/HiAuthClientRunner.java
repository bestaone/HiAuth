package cn.hiauth.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zgs
 */
@Slf4j
@Component
public class HiAuthClientRunner implements ApplicationRunner {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void run(ApplicationArguments args) {
        SessionContextHolder.setRedisTemplate(redisTemplate);
    }

}
