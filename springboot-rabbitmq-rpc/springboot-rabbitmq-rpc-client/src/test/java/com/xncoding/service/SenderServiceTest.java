package com.xncoding.service;

import com.xncoding.pos.Application;
import com.xncoding.pos.model.User;
import com.xncoding.pos.service.SenderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * SenderServiceTest
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SenderServiceTest {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SenderService senderService;

    private List<User> users;

    @Before
    public void prepare() {
        users = new ArrayList<>();
        User user1 = new User();
        user1.setName("用户1");
        user1.setAge(19);
        users.add(user1);

        User user2 = new User();
        user2.setName("用户2");
        user2.setAge(20);
        users.add(user2);
    }

    @Test
    public void testSendAsync() throws InterruptedException, ExecutionException {

        List<Future<String>> results = new ArrayList<>();
        for (User user : users) {
            Future<String> result = senderService.sendAsync(user);
            results.add(result);
        }
        for (Future<String> future : results) {
            String result = future.get();
            if (result == null) {
                Assert.fail("message will not timeout");
            } else {
                logger.info("tttttttttttt=" + result);
            }
        }
    }

    @Test
    public void testSendWithFixedReplay() throws InterruptedException, ExecutionException{
        List<Future<String>> results = new ArrayList<>();
        for (User user : users) {
            Future<String> result = senderService.sendWithFixedReplay(user);
            results.add(result);
        }
        for (Future<String> future : results) {
            String result = future.get();
            if (result == null) {
                Assert.fail("message will not timeout");
            } else {
                logger.info("tttttttttttt=" + result);
            }
        }
    }
}
