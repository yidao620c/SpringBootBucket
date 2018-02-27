
## 简介
同时实现了RESTful API接口和WebSocket接口，并且都需要授权才能访问，授权机制基于Token，实现技术是JWT+Shiro

访问RESTful API接口或连接到WebSocket服务之前，都需要先调用登录接口拿到Token。

## JWT + Shiro实现接口认证

SpringBoot + JWT + Shiro实现RESTful API的授权访问

## WeSocket长连接

Socket.IO服务器端和客户端，使用Java语言实现，用于和POS机进行长连接的WebSocket通信。

服务器端可以实时将消息推送给POS端，比如机具解绑操作，以此来取代机具管理系统中客户端轮询机制。

Sockt.io官网：<https://socket.io/>

服务器端使用 [netty-socketio](https://github.com/mrniko/netty-socketio)

客户端使用 [socket.io-client-java](https://github.com/socketio/socket.io-client-java)

### WebSocket js客户端测试

```
com.xncoding.pos.socket.server.ChatLauncher - /client/html/index.html
com.xncoding.pos.socket.server.EventChatLauncher - /client/html/event-index.html
com.xncoding.pos.socket.server.SslChatLauncher - /client/html/ssl-event-index.html
com.xncoding.pos.socket.server.NamespaceChatLauncher - /client/html/namespace-index.html
com.xncoding.pos.socket.server.AckChatLauncher - /client/html/ack-index.html
com.xncoding.pos.socket.server.BinaryEventLauncher - /client/html/binary-event-index.html
```

### WebSocket Java客户端测试

```
服务器：
com.xncoding.pos.socket.server.SocketServer
客户端：
com.xncoding.pos.socket.client.SocketClient
```

### 广播消息中的namespace和room

如果不指定namespace，有个默认namespace，默认room，房间在namespace下面。

netty-socketio服务器端完整实现了所有Socket.IO功能，而客户端再浏览器下面表现完美，
但是socket.io-client-java只要加上namespace就报错，不过它的room广播功能可用。
所以目前的做法是对于每个项目下的APP，加入到对应applicationId房间中，可实现房间广播。

## JWT + Netty-SocketIO实现WebSocket的授权访问

对于需要进行认证的接口，通过JWT的Token方式实现了用户认证访问。

## 运行

该工程和app-manage工程配套，请参考app-manage工程的数据库初始化schema.sql

## 许可证

Copyright (c) 2018 Xiong Neng

基于 MIT 协议发布: <http://www.opensource.org/licenses/MIT>
