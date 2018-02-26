package com.enzhico.jwt.socket.server;

import com.corundumstudio.socketio.listener.ExceptionListenerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * MyExceptionListener
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/30
 */
public class SocketExceptionListener extends ExceptionListenerAdapter {
    @Override
    public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        System.out.println(e.getMessage());
        ctx.close();
        return true;
    }
}
