package com.enzhico.jwt.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtil {

    private static final Logger log = LoggerFactory.getLogger(JWTUtil.class);

    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            log.error("校验token失败", exception);
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /*----------------------------以下是socket校验--------------------------*/
    /**
     * 生成Socket Token签名, 5min后过期
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @param appid    applicationId
     * @param imei     IMEI码
     * @return 加密的token
     */
    public static String signSocket(String username, String secret, String appid, String imei) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    .withClaim("appid", appid)
                    .withClaim("imei", imei)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verifySocket(String token, String secret) {
        try {
            DecodedJWT jwt1 = JWT.decode(token);
            String username = jwt1.getClaim("username").asString();
            String appid = jwt1.getClaim("appid").asString();
            String imei = jwt1.getClaim("imei").asString();

            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .withClaim("appid", appid)
                    .withClaim("imei", imei)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            log.error("校验token失败", exception);
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getSocketUsername(String token) {
        try {
            DecodedJWT jwt1 = JWT.decode(token);
            return jwt1.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的Appid
     */
    public static String getSocketAppid(String token) {
        try {
            DecodedJWT jwt1 = JWT.decode(token);
            return jwt1.getClaim("appid").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的IMEI码
     */
    public static String getSocketImei(String token) {
        try {
            DecodedJWT jwt1 = JWT.decode(token);
            return jwt1.getClaim("imei").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
