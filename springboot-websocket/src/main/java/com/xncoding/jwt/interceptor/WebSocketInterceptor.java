package com.xncoding.jwt.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * WebSocketInterceptor
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/3/22
 */
public class WebSocketInterceptor implements HandshakeInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse arg1,
                                   WebSocketHandler arg2, Map<String, Object> arg3) throws Exception {
        // 将ServerHttpRequest转换成request请求相关的类，用来获取request域中的用户信息
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpServletRequest httpRequest = servletRequest.getServletRequest();
        }
        logger.info("beforeHandshake完成");
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
        logger.info("afterHandshake完成");
    }
}
