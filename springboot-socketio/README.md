
## 简介

在SpringBoot中有两种实现WebSocket实时通信的方式：

1. 一个是使用WebSocket的一个子协议stomp
2. 另外一个是使用Socket.IO协议实现。

本项目演示如何通过Socket.IO协议实现

## WeSocket长连接

Socket.IO服务器端和客户端，使用Java语言实现，用于和浏览器或Android客户端进行长连接的WebSocket通信。
服务器端可以实时将消息推送给客户端，以此来取代客户端轮询机制。

Sockt.io官网：<https://socket.io/>

服务器端使用 [netty-socketio](https://github.com/mrniko/netty-socketio)

客户端使用 [socket.io-client-java](https://github.com/socketio/socket.io-client-java)

### WebSocket js客户端测试

```
/client/html/index.html
```

### WebSocket Java客户端测试

```
com.xncoding.pos.socket.client.SocketClient
```

## 许可证

Copyright (c) 2018 Xiong Neng

基于 MIT 协议发布: <http://www.opensource.org/licenses/MIT>

