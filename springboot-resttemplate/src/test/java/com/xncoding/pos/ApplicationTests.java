package com.xncoding.pos;

import com.xncoding.pos.model.LoginParam;
import com.xncoding.pos.model.UnbindParam;
import com.xncoding.pos.model.BaseResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 测试任务
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationTests.class);
    /**
     * @LocalServerPort 提供了 @Value("${local.server.port}") 的代替
     */
    @LocalServerPort
    private int port;
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testRestTemplate() {
        LoginParam param = new LoginParam();
        param.setUsername("admin");
        param.setPassword("12345678");
        String loginUrl = String.format("http://localhost:%d/login", port);
        BaseResponse r = restTemplate.postForObject(loginUrl, param, BaseResponse.class);
        assertThat(r.isSuccess(), is(true));

        String token = (String) r.getData();
        UnbindParam unbindParam = new UnbindParam();
        unbindParam.setImei("imei");
        unbindParam.setLocation("location");
        // 设置HTTP Header信息
        String unbindUrl = String.format("http://localhost:%d/unbind", port);
        URI uri;
        try {
            uri = new URI(unbindUrl);
        } catch (URISyntaxException e) {
            logger.error("URI构建失败", e);
            throw new RuntimeException("URI构建失败");
        }
        RequestEntity<UnbindParam> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .body(unbindParam);
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(requestEntity, BaseResponse.class);
        BaseResponse r2 = responseEntity.getBody();
        assertThat(r2.isSuccess(), is(true));
        assertThat(r2.getData(), is("unbind"));
    }
}
