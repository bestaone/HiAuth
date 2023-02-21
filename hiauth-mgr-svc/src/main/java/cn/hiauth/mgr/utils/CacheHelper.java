package cn.hiauth.mgr.utils;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheHelper implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(CacheHelper.class);

    private static String prefix;

    private static Integer expire = 300;

    private static RedisTemplate<String, Object> redisTemplate;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final String id;

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    public static void setRedisTemplate(RedisTemplate redisTemplate) {
        CacheHelper.redisTemplate = redisTemplate;
    }

    public static void setPrefix(String prefix) {
        CacheHelper.prefix = prefix;
    }

    public CacheHelper(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.debug("CacheHelper:id=" + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        try {
            if (null != value) {
                String k = key.toString().replaceAll("[\\n]", "").replaceAll("[\\s]+", " ");
                k = prefix + ":" + k;
                logger.debug("Mybatis Cache Put:{} -> {}", k, value);
                redisTemplate.opsForValue().set(k, value, expire, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("redis保存数据异常！");
        }
    }

    @Override
    public Object getObject(Object key) {
        try {
            if (null != key) {
                String k = key.toString().replaceAll("[\\n]", "").replaceAll("[\\s]+", " ");
                k = prefix + ":" + k;
                logger.debug("Mybatis Cache Get:{}", k);
                return redisTemplate.opsForValue().get(k);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("redis获取数据异常！");
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        try {
            if (null != key) {
                String k = key.toString().replaceAll("[\\n]", "").replaceAll("[\\s]+", " ");
                k = prefix + ":" + k;
                logger.debug("Mybatis Cache Remove:{}", k);
                return redisTemplate.expire(k, 0, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("redis获取数据异常！");
        }
        return null;
    }

    @Override
    public void clear() {
        Integer size = redisTemplate.execute((RedisConnection connection) -> {
            Set<String> keys = redisTemplate.keys("*:" + this.id + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                redisTemplate.delete(keys);
            }
            return keys.size();
        });
        logger.debug("Mybatis Cache Clean:清除了{}个对象", size);
    }

    @Override
    public int getSize() {
        Long size = redisTemplate.execute((RedisConnection connection) -> connection.dbSize());
        return size.intValue();
    }

    public static void setExpire(Integer expire) {
        CacheHelper.expire = expire;
    }

}
