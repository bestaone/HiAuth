package com.bestaone.aiwan.core.utils;

import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.Random;

/**
 * Long型的ID生成器
 * @author zhangguosheng
 */
@Component("idGenerator")
public class LongIdGenerator implements IdGenerator<Long> {

    private static int counter = 0;
    private static final int IP_RANDOM_INT;
    private static final int JVM_RANDOM_INT;
    private static final int LAST_RANDOM_INT;
    private static final int FOR_SIZE = 4;

    static {
        int ip;
        long jvm = System.currentTimeMillis();
        try {
            ip = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ip = 0;
        }
        Random ipRandom = new Random(ip);
        Random jvmRandom = new Random(jvm);
        Random lastRandom = new Random();
        IP_RANDOM_INT = ipRandom.nextInt(800) + 100;
        JVM_RANDOM_INT = jvmRandom.nextInt(90) + 10;
        LAST_RANDOM_INT = lastRandom.nextInt(10000);
    }

    protected int getCount() {
        synchronized (LongIdGenerator.class) {
            if (counter < 0) {
                counter = 0;
            }
            return counter++;
        }
    }

    private static int toInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < FOR_SIZE; i++) {
            result = (result << 8) - Byte.MIN_VALUE + bytes[i];
        }
        return result;
    }

    @Override
    public Long generate() {
        long millisecond = System.currentTimeMillis();
        int count = LAST_RANDOM_INT + getCount();
        long id = IP_RANDOM_INT * 10000000000000000L + millisecond * 100000 + JVM_RANDOM_INT * 10000 + count;
        return id;
    }

}