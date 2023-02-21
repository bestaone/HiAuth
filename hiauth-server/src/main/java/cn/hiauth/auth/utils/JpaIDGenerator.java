package cn.hiauth.auth.utils;

import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Random;

public class JpaIDGenerator extends IdentityGenerator {

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
        synchronized (JpaIDGenerator.class) {
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

    public Long generate() {
        long millisecond = System.currentTimeMillis();
        int count = LAST_RANDOM_INT + getCount();
        long id = IP_RANDOM_INT * 10000000000000000L + millisecond * 100000 + JVM_RANDOM_INT * 10000 + count;
        return id;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws MappingException {
        Long id =  generate();
        if (id != null) {
            return id;
        }
        return (Serializable) super.generate(session, object);
    }

}
