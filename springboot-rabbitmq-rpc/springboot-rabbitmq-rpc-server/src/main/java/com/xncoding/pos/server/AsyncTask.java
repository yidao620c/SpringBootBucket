package com.xncoding.pos.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class AsyncTask {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Async
    public ListenableFuture<String> expensiveOperation(String message) {
        int millis = (int) (Math.random() * 5 * 1000);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
        String result = message + " executed by " + Thread.currentThread().getName() + " for " + millis + " ms";
        logger.info("task result {}", result);
        return new AsyncResult<String>(result);
    }
}
