package com.xncoding.pos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 消息发送服务
 */
@Service
public class SenderService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试广播模式.
     *
     * @param p the p
     * @return the response entity
     */
    public void broadcast(String p) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("FANOUT_EXCHANGE", "", p, correlationData);
    }

    /**
     * 测试Direct模式.
     *
     * @param p the p
     * @return the response entity
     */
    public void direct(String p) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("DIRECT_EXCHANGE", "DIRECT_ROUTING_KEY", p, correlationData);
    }

}
