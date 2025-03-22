package cn.hiauth.server.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RsaUtils {

    /**
     * 类型
     */
    public static final String ENCRYPT_TYPE = "RSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 公钥加密
     *
     * @param content:
     * @param publicKey:
     */
    public static String encrypt(String content, PublicKey publicKey) {
        try {
            RSA rsa = new RSA(null, publicKey);
            return rsa.encryptBase64(content, KeyType.PublicKey);
        } catch (Exception e) {
            log.error("公钥加密异常 msg:{}", e.getMessage());
        }
        return null;
    }

    /**
     * 公钥加密
     *
     * @param content:
     * @param publicKey:
     */
    public static String encrypt(String content, String publicKey) {
        try {
            RSA rsa = new RSA(null, publicKey);
            return rsa.encryptBase64(content, KeyType.PublicKey);
        } catch (Exception e) {
            log.error("公钥加密异常 msg:{}", e.getMessage());
        }
        return null;
    }


    /**
     * 私钥解密
     *
     * @param content:
     * @param privateKey:
     */
    public static String decrypt(String content, PrivateKey privateKey) {
        try {
            RSA rsa = new RSA(privateKey, null);
            return rsa.decryptStr(content, KeyType.PrivateKey);
        } catch (Exception e) {
            log.error("私钥解密异常 msg:{}", e.getMessage());
        }
        return null;
    }

    /**
     * 私钥解密
     *
     * @param content:
     * @param privateKey:
     */
    public static String decrypt(String content, String privateKey) {
        try {
            RSA rsa = new RSA(privateKey, null);
            return rsa.decryptStr(content, KeyType.PrivateKey);
        } catch (Exception e) {
            log.error("私钥解密异常 msg:{}", e.getMessage());
        }
        return null;
    }

    /**
     * 获取公私钥-请获取一次后保存公私钥使用
     */
    public static Map<String, String> generateKeyPair() {
        try {
            KeyPair pair = SecureUtil.generateKeyPair(ENCRYPT_TYPE);
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();
            // 获取 公钥和私钥 的 编码格式（通过该 编码格式 可以反过来 生成公钥和私钥对象）
            byte[] pubEncBytes = publicKey.getEncoded();
            byte[] priEncBytes = privateKey.getEncoded();

            // 把 公钥和私钥 的 编码格式 转换为 Base64文本 方便保存
            String pubEncBase64 = Base64.encode(pubEncBytes);
            String priEncBase64 = Base64.encode(priEncBytes);

            Map<String, String> map = new HashMap<String, String>(2);
            map.put(PUBLIC_KEY, pubEncBase64);
            map.put(PRIVATE_KEY, priEncBase64);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        Map<String, String> key = generateKeyPair();
        System.out.println(key);
        String privateKey = key.get(PRIVATE_KEY);
        String publicKey = key.get(PUBLIC_KEY);
        String text = "wasu:234@125.2321321he12321321";
        String content = encrypt(text, publicKey);
        content = decrypt(content, privateKey);
        System.out.println(content);
    }
}
