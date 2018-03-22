package com.xncoding.jwt.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xncoding.jwt.commons.JacksonUtil;
import com.xncoding.jwt.model.WsParam;
import com.xncoding.jwt.model.WsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * SocketHandler
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/3/22
 */
@Component
public class SocketHandler extends TextWebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("handleTextMessage start");
        // 将消息进行转化，因为是消息是json数据，可能里面包含了发送给某个人的信息，所以需要用json相关的工具类处理之后再封装成TextMessage，
        // 我这儿并没有做处理，消息的封装格式一般有{from:xxxx,to:xxxxx,msg:xxxxx}，来自哪里，发送给谁，什么消息等等
        String msg = message.getPayload();
        logger.info("msg = " + msg);
        WsParam<String> wsParam = JacksonUtil.json2Bean(msg, new TypeReference<WsParam<String>>(){});
        if ("list".equals(wsParam.getMethod())) {
            logger.info("call list method...");
            WsResponse<String> response = new WsResponse<>();
            response.setResult("hello list");
            sendMessageToUser(session, new TextMessage(JacksonUtil.bean2Json(response)));
        }
        logger.info("handleTextMessage end");
        // 给所有用户群发消息
        //sendMessagesToUsers(msg);
        // 给指定用户群发消息
        //sendMessageToUser(userId, msg);
    }



    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Connected ... " + session.getId());
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        sessions.remove(session);
        logger.info(String.format("Session %s closed because of %s", session.getId(), status.getReason()));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        logger.error("error occured at sender " + session, throwable);
    }

    /**
     * 给所有的用户发送消息
     */
    public void sendMessagesToUsers(TextMessage message) {
        for (WebSocketSession user : sessions) {
            try {
                // isOpen()在线就发送
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送消息给指定的用户
     */
    private void sendMessageToUser(WebSocketSession user, TextMessage message) {
        try {
            // 在线就发送
            if (user.isOpen()) {
                user.sendMessage(message);
            }
        } catch (IOException e) {
           logger.error("发送消息给指定的用户出错", e);
        }
    }
}
