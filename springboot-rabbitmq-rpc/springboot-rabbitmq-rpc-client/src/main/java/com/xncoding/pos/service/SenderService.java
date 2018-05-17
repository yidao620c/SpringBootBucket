package com.xncoding.pos.service;

import com.xncoding.pos.model.User;
import com.xncoding.pos.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;

import static com.xncoding.pos.config.RabbitConfig.QUEUE_ASYNC_RPC;
import static com.xncoding.pos.config.RabbitConfig.QUEUE_ASYNC_RPC_WITH_FIXED_REPLY;

/**
 * 消息发送服务
 */
@Service
public class SenderService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    AsyncRabbitTemplate asyncRabbitTemplate;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Async
    public Future<String> sendAsync(User message) {
//        String result = (String) amqpTemplate.convertSendAndReceive(QUEUE_ASYNC_RPC, message, m -> {
//            m.getMessageProperties().setCorrelationIdString(StringUtil.generateUUId());
//            return m;
//        });
        String result = (String) amqpTemplate.convertSendAndReceive(QUEUE_ASYNC_RPC, message);
        return new AsyncResult<>(result);
    }

    public Future<String> sendWithFixedReplay(User message) {
//        ListenableFuture<String> future = asyncRabbitTemplate.convertSendAndReceive(QUEUE_ASYNC_RPC_WITH_FIXED_REPLY, message, m -> {
//            m.getMessageProperties().setCorrelationIdString(StringUtil.generateUUId());
//            return m;
//        });
        return asyncRabbitTemplate.convertSendAndReceive(QUEUE_ASYNC_RPC_WITH_FIXED_REPLY, message);
    }

}
