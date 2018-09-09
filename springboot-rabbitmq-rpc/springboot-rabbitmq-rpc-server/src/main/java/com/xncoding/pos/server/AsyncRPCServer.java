package com.xncoding.pos.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xncoding.pos.model.User;
import com.xncoding.pos.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.nio.charset.Charset;

import static com.xncoding.pos.config.RabbitConfig.QUEUE_ASYNC_RPC;
import static com.xncoding.pos.config.RabbitConfig.QUEUE_ASYNC_RPC_WITH_FIXED_REPLY;


@Component
public class AsyncRPCServer {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    AsyncTask asyncTask;

    Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = QUEUE_ASYNC_RPC)
    public void processAsyncRpc(Message message, @Header(AmqpHeaders.REPLY_TO) String replyTo) {
        String body = new String(message.getBody(), Charset.forName("UTF-8"));
	    User user = JacksonUtil.json2Bean(body, new TypeReference<User>(){});
        logger.info("recevie message {} and reply to {}, user.name={}", body, replyTo, user.getName());
        if (replyTo.startsWith("amq.rabbitmq.reply-to")) {
            logger.debug("starting with version 3.4.0, the RabbitMQ server now supports Direct reply-to");
        } else {
            logger.info("fall back to using a temporary reply queue");
        }
        ListenableFuture<String> asyncResult = asyncTask.expensiveOperation(body);
        asyncResult.addCallback(new ListenableFutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                amqpTemplate.convertAndSend(replyTo, result);
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("接受到QUEUE_ASYNC_RPC失败", ex);
            }
        });
    }

    @RabbitListener(queues = QUEUE_ASYNC_RPC_WITH_FIXED_REPLY)
    public void processAsyncRpcFixed(User user,
                                     @Header(AmqpHeaders.REPLY_TO) String replyTo,
                                     @Header(AmqpHeaders.CORRELATION_ID) byte[] correlationId) {
//        String body = new String(message.getBody(), Charset.forName("UTF-8"));
//        User user = JacksonUtil.json2Bean(body, new TypeReference<User>(){});
        logger.info("user.name={}", user.getName());
        logger.info("use a fixed reply queue={}, correlationId={}", replyTo, new String(correlationId));
        ListenableFuture<String> asyncResult = asyncTask.expensiveOperation(user.getName());
        asyncResult.addCallback(new ListenableFutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                amqpTemplate.convertAndSend(replyTo, (Object) result, m -> {
                    //https://stackoverflow.com/questions/42382307/messageproperties-setcorrelationidstring-is-not-working
                    m.getMessageProperties().setCorrelationId(new String(correlationId));
                    return m;
                });
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("接受到QUEUE_ASYNC_RPC_WITH_FIXED_REPLY失败", ex);
            }
        });
    }

}
