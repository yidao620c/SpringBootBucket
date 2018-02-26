package com.enzhico.pos.crypto.symmetric;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;

/**
 * 对称加密/解密算法（推荐算法）：AES
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/21
 */
public class AESUtil {
    private static final String DATA = "这个是内容";
    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
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
        Key k = genSecretKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
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
        Key k = genSecretKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * AES only supports key sizes of 16, 24 or 32 bytes
     */
    public static Key genSecretKey(byte[] key) throws Exception {
        if (key.length == 16 || key.length == 24 || key.length == 32) {
            return new SecretKeySpec(key, KEY_ALGORITHM);
        }
        throw new IllegalArgumentException("AES only supports key sizes of 16, 24 or 32 bytes");
    }

    public static void main(String[] args) throws Exception {
        String key = "1234567890123456";
        byte[] desResult = encrypt(DATA, key);
        System.out.println(DATA + ">>>AES 加密结果>>>" + Hex.encodeHexString(desResult));

        byte[] desPlain = decrypt(desResult, key);
        System.out.println(DATA + ">>>AES 解密结果>>>" + new String(desPlain, Charset.forName("UTF-8")));
    }
}
