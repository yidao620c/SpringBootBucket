package com.xncoding.jwt.socket.server;


import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.xncoding.jwt.socket.model.LoginRequest;
import io.socket.engineio.client.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class NamespaceSocketServer {
    private static final Logger logger = LoggerFactory.getLogger(NamespaceSocketServer.class);

    public static void main(String[] args) {
        /*
         * 创建Socket，并设置监听端口
         */
        Configuration config = new Configuration();
        //设置主机名
        config.setHostname("localhost");
        //设置监听端口
        config.setPort(9092);
        // 协议升级超时时间（毫秒），默认10秒。HTTP握手升级为ws协议超时时间
        config.setUpgradeTimeout(10000);
        // Ping消息超时时间（毫秒），默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
        config.setPingTimeout(180000);
        // Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔
        config.setPingInterval(60000);
        // 连接认证，这里使用token更合适
        config.setAuthorizationListener(new AuthorizationListener() {
            @Override
            public boolean isAuthorized(HandshakeData data) {
                // 客户端使用 options.query = "username=test&password=test";
                // 可以使用如下代码获取用户密码信息，本文不做身份验证
                 String username = data.getSingleUrlParam("username");
                 String password = data.getSingleUrlParam("password");
                logger.info("认证信息：username=" + username + ",password=" + password);
                // 如果认证不通过会返回一个Socket.EVENT_CONNECT_ERROR事件
                return true;
            }
        });

        final SocketIOServer server = new SocketIOServer(config);

        /*
         * 添加连接监听事件，监听是否与客户端连接到服务器
         */
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                // 判断是否有客户端连接
                if (client != null) {
                    logger.info("连接成功。clientId=" + client.getSessionId().toString());
                    client.joinRoom(client.getHandshakeData().getSingleUrlParam("appid"));
                } else {
                    logger.error("并没有人连接上。。。");
                }
            }
        });

        /*
         * 添加监听事件，监听客户端的事件
         * 1.第一个参数eventName需要与客户端的事件要一致
         * 2.第二个参数eventClase是传输的数据类型
         * 3.第三个参数listener是用于接收客户端传的数据，数据类型需要与eventClass一致
         */
        server.addEventListener("login", LoginRequest.class, new DataListener<LoginRequest>() {
            @Override
            public void onData(SocketIOClient client, LoginRequest data, AckRequest ackRequest) {
                logger.info("接收到客户端login消息：code = " + data.getCode() + ",body = " + data.getBody());
                // check is ack requested by client,
                // but it's not required check
                if (ackRequest.isAckRequested()) {
                    // send ack response with data to client
                    ackRequest.sendAckData("已成功收到客户端登录请求", "yeah");
                }
                // 向客户端发送消息
                List<String> list = new ArrayList<>();
                list.add("登录成功，sessionId=" + client.getSessionId());
                // 第一个参数必须与eventName一致，第二个参数data必须与eventClass一致
                String room = client.getHandshakeData().getSingleUrlParam("appid");
                server.getRoomOperations(room).sendEvent("login", list.toString());
//                client.disconnect();
            }
        });

        //启动服务
        server.start();
    }

    private static void addNamespace(SocketIOServer server, String namespace) {
        final SocketIONamespace chat1namespace = server.addNamespace(namespace);
        chat1namespace.addEventListener(Socket.EVENT_MESSAGE, ChatObject.class, new DataListener<ChatObject>() {
            @Override
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) {
                // broadcast messages to all clients
                chat1namespace.getBroadcastOperations().sendEvent(Socket.EVENT_MESSAGE, data);
            }
        });
        /*
         * 添加监听事件，监听客户端的事件
         * 1.第一个参数eventName需要与客户端的事件要一致
         * 2.第二个参数eventClase是传输的数据类型
         * 3.第三个参数listener是用于接收客户端传的数据，数据类型需要与eventClass一致
         */
        chat1namespace.addEventListener("login", LoginRequest.class, new DataListener<LoginRequest>() {
            @Override
            public void onData(SocketIOClient client, LoginRequest data, AckRequest ackRequest) {
                logger.info("接收到客户端login消息：code = " + data.getCode() + ",body = " + data.getBody());
                // check is ack requested by client,
                // but it's not required check
                if (ackRequest.isAckRequested()) {
                    // send ack response with data to client
                    ackRequest.sendAckData("命名空间" + namespace + "已成功收到客户端登录请求", "yeah");
                }
                // 向客户端发送消息
                List<String> list = new ArrayList<>();
                list.add("登录成功");
                // 第一个参数必须与eventName一致，第二个参数data必须与eventClass一致
                chat1namespace.getBroadcastOperations().sendEvent("loginReply", list.toString());
//                client.disconnect();
            }
        });

    }

}
