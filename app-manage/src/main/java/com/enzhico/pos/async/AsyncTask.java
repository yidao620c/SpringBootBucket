package com.enzhico.pos.async;

import com.enzhico.pos.common.dao.entity.Pos;
import com.enzhico.pos.config.properties.MyProperties;
import com.enzhico.pos.dao.entity.ManagerInfo;
import com.enzhico.pos.http.HttpBaseResponse;
import com.enzhico.pos.http.LoginParam;
import com.enzhico.pos.http.UnbindParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Future;

/**
 * AsyncDemo
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/25
 */
@Component
public class AsyncTask {
    private static final Logger logger = LoggerFactory.getLogger(AsyncTask.class);

    @Resource
    private MyProperties p;
    @Resource
    private RestTemplate restTemplate;

    @Async
    public void dealNoReturnTask() {
        logger.info("返回值为void的异步调用开始" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("返回值为void的异步调用结束" + Thread.currentThread().getName());
    }

    @Async
    public Future<String> dealHaveReturnTask(int i) {
        logger.info("asyncInvokeReturnFuture, parementer=" + i);
        Future<String> future;
        try {
            Thread.sleep(1000 * i);
            future = new AsyncResult<String>("success:" + i);
        } catch (InterruptedException e) {
            future = new AsyncResult<String>("error");
        }
        return future;
    }

    /**
     * 异步推送消息
     */
    @Async
    public void pushUnbindMsg(ManagerInfo managerInfo, Pos pos, String location) {
        logger.info("解绑成功后推送消息给对应的POS机");
        LoginParam loginParam = new LoginParam();
        loginParam.setUsername(managerInfo.getUsername());
        loginParam.setPassword(managerInfo.getPassword());
        HttpBaseResponse r = restTemplate.postForObject(
                p.getPosapiUrlPrefix() + "/notifyLogin", loginParam, HttpBaseResponse.class);
        if (r.isSuccess()) {
            logger.info("推送消息登录认证成功");
            String token = (String) r.getData();
            UnbindParam unbindParam = new UnbindParam();
            unbindParam.setImei(pos.getImei());
            unbindParam.setLocation(location);
            // 设置HTTP Header信息
            URI uri;
            try {
                uri = new URI(p.getPosapiUrlPrefix() + "/notify/unbind");
            } catch (URISyntaxException e) {
                logger.error("URI构建失败", e);
                throw new AsyncException("URI构建失败");
            }
            RequestEntity<UnbindParam> requestEntity = RequestEntity
                    .post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", token)
                    .body(unbindParam);
            ResponseEntity<HttpBaseResponse> responseEntity = restTemplate.exchange(requestEntity, HttpBaseResponse.class);
            HttpBaseResponse r2 = responseEntity.getBody();
            if (r2.isSuccess()) {
                logger.info("推送消息解绑网点成功");
            } else {
                logger.error("推送消息解绑网点失败，errmsg = " + r2.getMsg());
            }
        } else {
            logger.error("推送消息登录认证失败");
        }
    }
}
