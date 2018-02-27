package com.xncoding.jwt.common;

import com.corundumstudio.socketio.SocketIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * SpringBoot启动之后执行
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2017/7/31
 */
@Component
@Order(1)
public class ServerRunner implements CommandLineRunner {
    private final SocketIOServer server;
    private static final Logger logger = LoggerFactory.getLogger(ServerRunner.class);

    @Autowired
    public ServerRunner(SocketIOServer server) {
        this.server = server;
    }

    @Override
    public void run(String... args) {
        logger.info("ServerRunner 开始启动啦...");
        server.start();
    }
}
