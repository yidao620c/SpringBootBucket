package com.xncoding.jwt.socket.client;

import com.xncoding.jwt.api.model.BaseResponse;
import com.xncoding.jwt.api.model.JoinBindResponse;
import com.xncoding.jwt.api.model.PosParam;
import com.xncoding.jwt.common.util.JsonConverter;
import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

/**
 * 这个客户端和SpringBoot服务器连接测试
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/18
 */
public class SocketClient2 {
    private static Socket socket;
    private static final Logger logger = LoggerFactory.getLogger(SocketClient2.class);

    public static void main(String[] args) throws URISyntaxException {
        IO.Options options = new IO.Options();
        options.transports = new String[]{"websocket"};
        options.reconnectionAttempts = 2;
        options.reconnectionDelay = 1000;     // 失败重连的时间间隔(ms)
        options.timeout = 6000;               // 连接超时时间(ms)
        options.forceNew = true;
        options.query = "token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhcHBpZCI6ImNvbS5lbnpoaWNvLmFkbWlucGF5IiwiaW1laSI6Ijg2NjAzMzAzMDkwNjAwMCIsImV4cCI6MTUxNjc5OTM5MCwidXNlcm5hbWUiOiJhZG1pbiJ9.tmw6Ac46Wjad9hR-xT6RWHHwfviEqML5_2iK9zI1HeY";
        socket = IO.socket("https://test.xncoding.net/", options);
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                logger.info("我连接成功啦啦啦，开始进行入网查询....");
                // 客户端一旦连接成功，开始发起查询入网请求
                String imei = "313123123123123";
                socket.emit("askJoin", imei, (Ack) args1 -> {
                    JoinBindResponse r = JsonConverter.jsonObjectToObject(args1[0], JoinBindResponse.class);
                    logger.info("入网查询结果=" + r.getCode());
                });
                // 然后测试一下入网请求接口
                PosParam posParam = new PosParam();
                posParam.setImei("2222222222222222");
                socket.emit("doJoin", JsonConverter.objectToJSONObject(posParam), (Ack) args1 -> {
                    BaseResponse r = JsonConverter.jsonObjectToObject(args1[0], JoinBindResponse.class);
                    logger.info("执行POS机入网结果=" + r.isSuccess() + ",msg=" + r.getMsg());
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
        }).on(Socket.EVENT_PING, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ": Socket.EVENT_PING");
            }
        }).on(Socket.EVENT_PONG, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ": Socket.EVENT_PONG");
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
