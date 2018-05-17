package com.xncoding.pos.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;

/**
 * RabbitConfig
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/3/1
 */
@Configuration
public class RabbitConfig {
    /**
     * 同步RPC队列
     */
    public static final String QUEUE_SYNC_RPC = "rpc.sync";

    /**
     * 异步RPC队列，使用临时回复队列，或者使用“Direct reply-to”特性
     */
    public static final String QUEUE_ASYNC_RPC = "rpc.async";

    /**
     * 异步RPC队列，每个客户端使用不同的固定回复队列，需要额外提供correlationId以关联请求和响应
     */
    public static final String QUEUE_ASYNC_RPC_WITH_FIXED_REPLY = "rpc.with.fixed.reply";

    @Bean
    public Queue syncRPCQueue() {
        return new Queue(QUEUE_SYNC_RPC);
    }

    @Bean
    public Queue asyncRPCQueue() {
        return new Queue(QUEUE_ASYNC_RPC);
    }

    @Bean
    public Queue fixedReplyRPCQueue() {
        return new Queue(QUEUE_ASYNC_RPC_WITH_FIXED_REPLY);
    }

    @Bean
    public Queue repliesQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Primary
    public SimpleMessageListenerContainer replyContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(repliesQueue().getName());
        return container;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public AsyncRabbitTemplate asyncRabbitTemplate(RabbitTemplate template, SimpleMessageListenerContainer container) {
        return new AsyncRabbitTemplate(template, container);
    }
}
