package com.xncoding.pos.crypto.symmetric;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 对称加密算法：DES
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/21
 */
public class DESUtil {
    private static final String DATA = "这个是内容";
    /* 算法名称 */
    private static final String KEY_ALGORITHM = "DES";
    /* 算法名称/加密模式/填充方式 */
    private static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    private static final Charset UTF8 = Charset.forName("UTF-8");

    public static byte[] encrypt(String data, String key) throws Exception {
        return encrypt(data.getBytes(UTF8), key.getBytes(UTF8));
    }
    public static byte[] encrypt(String data, byte[] key) throws Exception {
        return encrypt(data.getBytes(UTF8), key);
    }
    public static byte[] encrypt(byte[] data, String key) throws Exception {
        return encrypt(data, key.getBytes(UTF8));
    }
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = generateSecretKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(String data, String key) throws Exception {
        return decrypt(data.getBytes(UTF8), key.getBytes(UTF8));
    }
    public static byte[] decrypt(String data, byte[] key) throws Exception {
        return decrypt(data.getBytes(UTF8), key);
    }
    public static byte[] decrypt(byte[] data, String key) throws Exception {
        return decrypt(data, key.getBytes(UTF8));
    }
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = generateSecretKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    private static SecretKey generateSecretKey(byte[] key) throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(key);
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        kg.init(secureRandom);
        return kg.generateKey();
    }

    public static void main(String[] args) throws Exception {
        String key = "1233344534534423423";
        byte[] desResult = encrypt(DATA, key);
        System.out.println(DATA + ">>>DES 加密结果>>>" + Hex.encodeHexString(desResult));

        byte[] desPlain = decrypt(desResult, key);
        System.out.println(DATA + ">>>DES 解密结果>>>" + new String(desPlain, Charset.forName("UTF-8")));
    }
}
