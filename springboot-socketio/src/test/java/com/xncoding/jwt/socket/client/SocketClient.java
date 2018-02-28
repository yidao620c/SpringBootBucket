package com.xncoding.jwt.socket.client;

import com.xncoding.jwt.common.JsonConverter;
import com.xncoding.jwt.model.LoginRequest;
import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * SocketClient
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/18
 */
public class SocketClient {
    private static Socket socket;
    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);

    public static void main(String[] args) throws URISyntaxException {
        IO.Options options = new IO.Options();
        options.transports = new String[]{"websocket"};
        options.reconnectionAttempts = 2;
        options.reconnectionDelay = 1000;     // 失败重连的时间间隔(ms)
        options.timeout = 20000;              // 连接超时时间(ms)
        options.forceNew = true;
        options.query = "username=test1&password=test1";
        socket = IO.socket("http://localhost:9099/", options);
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                // 客户端一旦连接成功，开始发起登录请求
                LoginRequest message = new LoginRequest(12, "这是客户端消息体");
                socket.emit("login", JsonConverter.objectToJSONObject(message), (Ack) args1 -> {
                    logger.info("回执消息=" + Arrays.stream(args1).map(Object::toString).collect(Collectors.joining(",")));
                });
            }
        }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                logger.info("Socket.EVENT_CONNECT_ERROR");
                socket.disconnect();
            }
        }).on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                logger.info("Socket.EVENT_CONNECT_TIMEOUT");
                socket.disconnect();
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                logger.info("客户端断开连接啦。。。");
                socket.disconnect();
            }
        });
        socket.connect();
    }
}
