package com.xncoding.pos.mq;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消息监听器
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/3/1
 */
@Component
public class Receiver {
    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    /**
     * FANOUT广播队列监听一.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception  这里异常需要处理
     */
    @RabbitListener(queues = {"FANOUT_QUEUE_A"})
    public void on(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.debug("FANOUT_QUEUE_A " + new String(message.getBody()));
    }

    /**
     * FANOUT广播队列监听二.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception   这里异常需要处理
     */
    @RabbitListener(queues = {"FANOUT_QUEUE_B"})
    public void t(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.debug("FANOUT_QUEUE_B " + new String(message.getBody()));
    }

    /**
     * DIRECT模式.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception  这里异常需要处理
     */
    @RabbitListener(queues = {"DIRECT_QUEUE"})
    public void message(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.debug("DIRECT " + new String(message.getBody()));
    }
}
