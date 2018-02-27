package com.xncoding.echarts.common;

import com.corundumstudio.socketio.SocketIOServer;
import com.xncoding.echarts.config.properties.MyProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SocketIOServer server;
    // 下载地址，版本2.1.1：https://bitbucket.org/ariya/phantomjs/downloads/
    private static final String PHANTOM_PATH = "phantomjs";
    @Resource
    private MyProperties p;

    @Autowired
    public ServerRunner(SocketIOServer server) {
        this.server = server;
    }

    @Override
    public void run(String... args) {
        logger.info("ServerRunner 开始启动啦...");
        server.start();
        logger.info("SocketServer 启动成功！");
        logger.info("点击打开首页: http://localhost:9075");
        // 启动socker服务器后，通过phantomjs启动浏览器网页客户端
//        openHtml(p.getLoadJs());
//        logger.info("Phantomjs 启动成功！");
    }

//    private void openHtml(String loadJs) {
//        String cmdStr = PHANTOM_PATH + " " + loadJs + " " + "http://localhost:9075";
//        logger.info("cmdStr=" + cmdStr);
//        Runtime rt = Runtime.getRuntime();
//        try {
//            rt.exec(cmdStr);
//        } catch (IOException e) {
//            logger.error("执行phantomjs的指令失败！PhantomJs详情参考这里:http://phantomjs.org", e);
//        }
//    }
}
