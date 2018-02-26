package com.enzhico.pos.crypto.symmetric;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * 对称加密/解密算法：3DES（DES加强版，使用3次DES计算，Triple DES，DESede）
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/21
 */
public class DESTripleUtil {
    private static final String DATA = "这个是内容";
    /* 算法名称 */
    private static final String KEY_ALGORITHM = "DESede";
    /* 算法名称/加密模式/填充方式 */
    private static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";
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
        SecretKey secretKey = build3DesKey(key);
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
        SecretKey secretKey = build3DesKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    /*
     * 根据字符串生成密钥字节数组
     * @param key 密钥字节数组
     * @return
     * @throws UnsupportedEncodingException
     */
    public static SecretKey build3DesKey(byte[] keyTemp) throws UnsupportedEncodingException {
        byte[] keyResult = new byte[24];    //声明一个24位的字节数组，默认里面都是0
        // 执行数组拷贝
        if (keyResult.length > keyTemp.length) {
            //如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
            System.arraycopy(keyTemp, 0, keyResult, 0, keyTemp.length);
        } else {
            //如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
            System.arraycopy(keyTemp, 0, keyResult, 0, keyResult.length);
        }
        return new SecretKeySpec(keyResult, KEY_ALGORITHM);
    }

    public static void main(String[] args) throws Exception {
        String key = "1233344534534423423";
        byte[] desResult = encrypt(DATA, key);
        System.out.println(DATA + ">>>DESede 加密结果>>>" + Hex.encodeHexString(desResult));

        byte[] desPlain = decrypt(desResult, key);
        System.out.println(DATA + ">>>DESede 解密结果>>>" + new String(desPlain, Charset.forName("UTF-8")));
    }
}
