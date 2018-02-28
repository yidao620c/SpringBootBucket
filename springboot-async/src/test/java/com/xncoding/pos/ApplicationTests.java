package com.xncoding.pos;

import com.xncoding.pos.async.AsyncTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * 测试异步任务
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(ApplicationTests.class);
    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void testAsync() throws InterruptedException, ExecutionException {
        asyncTask.dealNoReturnTask();

        Future<String> f = asyncTask.dealHaveReturnTask(5);

        log.info("主线程执行finished");

        log.info(f.get());
        assertThat(f.get(), is("success:" + 5));
    }
}
