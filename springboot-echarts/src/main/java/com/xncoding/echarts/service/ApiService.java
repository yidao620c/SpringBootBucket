package com.xncoding.echarts.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.xncoding.echarts.config.properties.MyProperties;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 专门用来服务对外接口用Service
 */

@Service
public class ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);
    /**
     * 保存最后一个连接上来的sessionID
     */
    private String sessionId;

    @Resource
    private MyProperties p;

//    private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    @Resource
    private SocketIOServer server;

    public synchronized void updateSessionId(String sid) {
        sessionId = sid;
    }

    /**
     * 服务器主动推送消息
     *
     * @param msgType 消息类型
     * @param jsonData echarts图表数据
     */
    public void pushMsg(String msgType, String jsonData) {
        SocketIOClient targetClient = this.server.getClient(UUID.fromString(sessionId));
        if (targetClient == null) {
            logger.error("sessionId=" + sessionId + "在server中获取不到client");
        } else {
            targetClient.sendEvent(msgType, jsonData);
        }
//        queue.offer(jsonData);
    }

//    public String popJson() {
//        try {
//            return queue.poll(100L, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    /**
     * 解析Base64位信息并输出到某个目录下面.
     *
     * @param base64Info base64串
     * @return 文件地址
     */
    public String saveBase64Pic(String base64Info) {
        if (StringUtils.isEmpty(base64Info)) {
            return "";
        }
        // 数据中：data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABI4AAAEsCAYAAAClh/jbAAA ...  在"base64,"之后的才是图片信息
        String[] arr = base64Info.split("base64,");

        // 将图片输出到系统某目录.
        OutputStream out = null;
        String now = FastDateFormat.getInstance("yyyyMMddHHmmss").format(new Date());
        String destFile = p.getImageDir() + now + ".png";
        try {
            // 使用了Apache commons codec的包来解析Base64
            byte[] buffer = Base64.decodeBase64(arr[1]);
            out = new FileOutputStream(destFile);
            out.write(buffer);
        } catch (IOException e) {
            logger.error("解析Base64图片信息并保存到某目录下出错!", e);
            return "";
        } finally {
            IOUtils.closeQuietly(out);
        }
        return destFile;
    }

}
