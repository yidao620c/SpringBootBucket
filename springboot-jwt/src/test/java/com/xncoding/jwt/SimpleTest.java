package com.xncoding.jwt;

import com.xncoding.jwt.shiro.ShiroKit;
import org.junit.Test;

/**
 * SimpleTest
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/4
 */
public class SimpleTest {
    @Test
    public void testMd5() {
        //盐（用户名+随机数）
        String username = "admin";
        String salt = ShiroKit.getRandomSalt(16);
        //原密码
        String password = "12345678";
        String encodedPassword = ShiroKit.md5(password, username + salt);
        System.out.println("这个是保存进数据库的密码:" + encodedPassword);
        System.out.println("这个是保存进数据库的盐:" + salt);
    }
}
