package com.enzhico.jwt;

import com.enzhico.jwt.api.model.VersionParam;
import com.enzhico.jwt.shiro.ShiroKit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

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

    @Test
    public void testJackson() throws JsonProcessingException {
        VersionParam req = new VersionParam();

        String reqBody = new ObjectMapper().writeValueAsString(req);

        System.out.println(reqBody);
    }
}
