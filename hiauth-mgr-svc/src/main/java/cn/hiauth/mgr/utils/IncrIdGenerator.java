package cn.hiauth.mgr.utils;

import cn.hiauth.mgr.common.IdGenerator;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自增主键生成器
 * 利用redis缓存生成自增主键
 * 第一次启动时会检测redis服务器时间，根据（当前时间毫秒值*100）生成一个长度为15位的初始值。
 * （当前时间毫秒值*100）决定了，平均1秒钟最多允许生成99999个主键。
 * 问题：
 * 1.当主键前13位时间段大于当前服务器时间，此时重启redis服务器，会导致初始化时生成一个重叠的主键区间，有可能会导致主键冲突。
 * 解决方法：
 * 1.延迟启动服务器当前，直到时间窗口划过数据库中最大的主键
 * 2.重启redis后，手工在redis中初始化一个大于数据库中主键的值，key参考 ID_GENERATOR_KEY
 * 后续优化：
 * 1.定期将时间窗口值存储起来，以便宕机启动后能够自动设置合适的id初始值
 *
 * @author: Guosheng.Zhang
 */
@Component("idGenerator")
public class IncrIdGenerator implements IdGenerator {

    private final static String ID_GENERATOR_KEY = "common:id_generator";

    /**
     * 15位
     * 记录本实例的id增长偏移量,保证相对的安全（不能保证全局安全）
     */
    private volatile static long ID_OFFSET = 100000000000000L;

    /**
     * 16位
     * 最大值小于16位
     */
    private final static long ID_MAX = 1000000000000000L;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 初始化检查主键基值
     * 如果没有找到，则按REDIS服务器当前时间设置一个基值
     */
    @PostConstruct
    void init() {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(ID_GENERATOR_KEY, redisTemplate.getConnectionFactory());
        Long id = entityIdCounter.getAndIncrement();
        if (id == null || id < ID_OFFSET) {
            ID_OFFSET = reset();
        } else if (id > ID_MAX) {
            throw new RuntimeException("当前主键偏移量错误");
        }
    }

    private Long reset() {
        // 13位
        Long now = redisTemplate.getRequiredConnectionFactory().getConnection().time();
        // 扩展到15位
        Long id = now * 100;
        setIncr(ID_GENERATOR_KEY, id);
        return id;
    }

    @Override
    public Long generate() {
        return getIncr(ID_GENERATOR_KEY);
    }

    @Override
    public List<Long> generateBatch(int count) {
        throw new RuntimeException("不支持");
    }

    /**
     * 遇到主键偏移量错误时的错误时，可将缓存中记录主键偏移量的key删除掉，让系统自动生成
     *
     * @param key key
     * @return
     * @Description: 获取自增长值
     */
    private Long getIncr(String key) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        if (increment < ID_OFFSET) {
            increment = reset();
        } else if (increment > ID_MAX) {
            throw new RuntimeException("当前主键偏移量错误");
        }
        ID_OFFSET = increment;
        return increment;
    }

    /**
     * @param key key
     * @param value 当前值
     * @Description: 初始化自增长值
     */
    private void setIncr(String key, Long value) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        counter.set(value);
    }

}