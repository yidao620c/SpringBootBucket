package com.xncoding.jwt.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.xncoding.jwt.api.model.*;
import com.xncoding.jwt.common.dao.entity.Pos;
import com.xncoding.jwt.common.dao.entity.Project;
import com.xncoding.jwt.common.util.JWTUtil;
import com.xncoding.jwt.service.ApiService;
import io.socket.client.Socket;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * 消息事件处理器
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/19
 */
@Component
public class MessageEventHandler {

    private final SocketIOServer server;
    private final ApiService apiService;

    private static final Logger logger = LoggerFactory.getLogger(MessageEventHandler.class);

    @Autowired
    public MessageEventHandler(SocketIOServer server, ApiService apiService) {
        this.server = server;
        this.apiService = apiService;
    }

    //添加connect事件，当客户端发起连接时调用
    @OnConnect
    public void onConnect(SocketIOClient client) {
        if (client != null) {
            String token = client.getHandshakeData().getSingleUrlParam("token");
            String applicationId = JWTUtil.getSocketAppid(token);
            String imei = JWTUtil.getSocketImei(token);
            String sessionId = client.getSessionId().toString();
            logger.info("连接成功, applicationId=" + applicationId + ", imei=" + imei + ", sessionId=" + sessionId);
            client.joinRoom(applicationId);
            // 更新POS监控，sessionId和状态
            ReportParam param = new ReportParam();
            param.setImei(imei);
            apiService.updateJustState(param, sessionId, 1);
        } else {
            logger.error("客户端为空");
        }
    }

    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String token = client.getHandshakeData().getSingleUrlParam("token");
        String imei = JWTUtil.getSocketImei(token);
        logger.info("客户端断开连接, imei=" + imei + ", sessionId=" + client.getSessionId().toString());
        // 更新POS监控，sessionId和状态
        ReportParam param = new ReportParam();
        param.setImei(imei);
        apiService.updateJustState(param, "", 2);
        client.disconnect();
    }

    // 消息接收入口
    @OnEvent(value = Socket.EVENT_MESSAGE)
    public void onEvent(SocketIOClient client, AckRequest ackRequest, Object data) {
        logger.info("接收到客户端消息");
        if (ackRequest.isAckRequested()) {
            // send ack response with data to client
            ackRequest.sendAckData("服务器回答Socket.EVENT_MESSAGE", "好的");
        }
    }

    // 广播消息接收入口
    @OnEvent(value = "broadcast")
    public void onBroadcast(SocketIOClient client, AckRequest ackRequest, Object data) {
        logger.info("接收到广播消息");
        // 房间广播消息
        String token = client.getHandshakeData().getSingleUrlParam("token");
        String room = JWTUtil.getSocketAppid(token);
        server.getRoomOperations(room).sendEvent("broadcast", "广播啦啦啦啦");
    }

    /**
     * 入网绑定查询接口
     * @param client 客户端
     * @param ackRequest 回执消息
     * @param imei imei码
     */
    @OnEvent(value = "askJoin")
    public void onAskJoin(SocketIOClient client, AckRequest ackRequest, String imei) {
        logger.info("入网绑定查询接口，imei=" + imei);
        JoinBindResponse result = searchJoin(imei);
        ackRequest.sendAckData(result);
    }

    /**
     * 执行入网绑定接口
     * @param client 客户端
     * @param ackRequest 回执消息
     * @param param 入网绑定参数
     */
    @OnEvent(value = "doJoin")
    public void onDoJoin(SocketIOClient client, AckRequest ackRequest, PosParam param) {
        logger.info("执行入网绑定接口 start....");
        BaseResponse result = postJoin(param);
        ackRequest.sendAckData(result);
    }

    /**
     * 绑定网点接口
     * @param client 客户端
     * @param ackRequest 回执消息
     * @param param 绑定网点参数
     */
    @OnEvent(value = "doBind")
    public void onDoBind(SocketIOClient client, AckRequest ackRequest,BindParam param) {
        logger.info("绑定网点接口 start....");
        BaseResponse result = postBind(param);
        ackRequest.sendAckData(result);
    }

    /**
     * 报告地址接口
     * @param client 客户端
     * @param ackRequest 回执消息
     * @param param 报告地址参数
     */
    @OnEvent(value = "doReport")
    public void onDoReport(SocketIOClient client, AckRequest ackRequest, ReportParam param) {
        logger.info("报告地址接口 start....");
        BaseResponse result = postReport(param);
        ackRequest.sendAckData(result);
    }

    /**
     * 版本检查接口
     * @param client 客户端
     * @param ackRequest 回执消息
     * @param param 报告地址参数
     */
    @OnEvent(value = "askVersion")
    public void onAskVersion(SocketIOClient client, AckRequest ackRequest, VersionParam param) {
        logger.info("版本检查接口 start....");
        VersionResult result = apiService.selectPublishCount(param);;
        ackRequest.sendAckData(result);
    }

    /*----------------------------------------下面是私有方法-------------------------------------*/
    private JoinBindResponse searchJoin(String imei) {
        JoinBindResponse result = new JoinBindResponse();
        int posCount = apiService.selectCount(imei);
        if (posCount > 0) {
            // 如果入网了再去查询是否绑定了网点
            int bindCount = apiService.selectBindCount(imei);
            Pos pos = apiService.selectByImei(imei);
            result.setPosState(pos.getPosState());
            if (bindCount == 0) {
                result.setSuccess(false);
                result.setCode(2);
                result.setMsg("已入网但尚未绑定网点");
            } else {
                result.setSuccess(true);
                result.setCode(1);
                result.setMsg("已入网并绑定了网点");
            }
        } else {
            result.setSuccess(false);
            result.setCode(3);
            result.setMsg("未入网");
        }
        return result;
    }

    private BaseResponse postJoin(PosParam posParam) {
        BaseResponse result = new BaseResponse();
        // imei码约束检查
        if (StringUtils.isEmpty(posParam.getImei()) || posParam.getImei().length() > 32) {
            result.setSuccess(false);
            result.setMsg("IMEI码长度不是1-32位，入网失败。");
            return result;
        }
        // 序列号SN约束检查
        if (StringUtils.isEmpty(posParam.getSn()) || posParam.getSn().length() > 64) {
            result.setSuccess(false);
            result.setMsg("序列号长度不是1-64位，入网失败。");
            return result;
        }
        // 机具型号约束检查
        if (StringUtils.isEmpty(posParam.getSeries()) || posParam.getSeries().length() > 32) {
            result.setSuccess(false);
            result.setMsg("机具型号不是1-32位，入网失败。");
            return result;
        }
        // Android版本约束检查
        if (StringUtils.isEmpty(posParam.getAndroidVersion()) || posParam.getAndroidVersion().length() > 32) {
            result.setSuccess(false);
            result.setMsg("Android版本号不是1-32位，入网失败。");
            return result;
        }
        // 版本号约束检查
        if (StringUtils.isEmpty(posParam.getVersion()) || posParam.getVersion().length() > 32) {
            result.setSuccess(false);
            result.setMsg("版本号不是1-32位，入网失败。");
            return result;
        }
        // 归属网点约束检查
        if (StringUtils.isEmpty(posParam.getLocation()) || posParam.getLocation().length() > 64) {
            result.setSuccess(false);
            result.setMsg("归属网点不是1-64位，入网失败。");
            return result;
        }
        // 产权方约束检查
        if (StringUtils.isEmpty(posParam.getOwner()) || posParam.getOwner().length() > 64) {
            result.setSuccess(false);
            result.setMsg("产权方不是1-64位，入网失败。");
            return result;
        }
        // 应用ID约束检查
        if (StringUtils.isEmpty(posParam.getApplicationId()) || posParam.getApplicationId().length() > 64) {
            result.setSuccess(false);
            result.setMsg("应用ID不是1-64位，入网失败。");
            return result;
        }
        // 备注约束检查
        if (StringUtils.isNotEmpty(posParam.getTips()) && posParam.getTips().length() > 255) {
            result.setSuccess(false);
            result.setMsg("备注超过255个字符，入网失败。");
            return result;
        }
        Pos pos = new Pos();
        Date now = new Date();
        pos.setJointime(now);
        pos.setBindtime(now);
        BeanUtils.copyProperties(posParam, pos);
        // 根据applicationId设置归属项目ID
        Project project = apiService.selectProjectByApplicationId(posParam.getApplicationId());
        if (project == null) {
            result.setSuccess(false);
            result.setMsg("Application Id不正确，入网失败。");
            return result;
        }
        // 重复检查
        int posCount = apiService.selectCount(posParam.getImei());
        if (posCount > 0) {
            result.setSuccess(false);
            result.setMsg("入网失败，该机具之前已经入网了。");
            return result;
        }
        // 插入一条新纪录
        pos.setProjectId(project.getId());
        int insert = apiService.insertPos(pos);
        if (insert > 0) {
            result.setSuccess(true);
            result.setMsg("入网成功");
            return result;
        } else {
            result.setSuccess(false);
            result.setMsg("入网失败，请联系管理员。");
            return result;
        }
    }
    
    private BaseResponse postBind(BindParam bindParam) {
        BaseResponse result = new BaseResponse();
        // imei码约束检查
        if (StringUtils.isEmpty(bindParam.getImei()) || bindParam.getImei().length() > 32) {
            result.setSuccess(false);
            result.setMsg("IMEI码长度不是1-32位，绑定网点失败。");
            return result;
        }
        // 归属网点约束检查
        if (StringUtils.isEmpty(bindParam.getLocation()) || bindParam.getLocation().length() > 64) {
            result.setSuccess(false);
            result.setMsg("归属网点不是1-64位，绑定网点失败。");
            return result;
        }

        Pos pos = apiService.selectByImei(bindParam.getImei());
        if (pos == null) {
            result.setSuccess(false);
            result.setMsg("该POS机尚未入网。");
            return result;
        }

        Pos pos2 = apiService.selectBindByImei(bindParam.getImei());
        if (pos2 != null) {
            result.setSuccess(false);
            result.setMsg("该POS机已经绑定了网点，请先解绑。");
            return result;
        }

        pos.setLocation(bindParam.getLocation());
        Date now = new Date();
        pos.setBindtime(now);
        pos.setUpdatedTime(now);
        apiService.bindLocation(pos);

        result.setSuccess(true);
        result.setMsg("绑定网点成功");
        return result;
    }

    private BaseResponse postReport(ReportParam param) {
        BaseResponse result = new BaseResponse();
        // IMEI码约束检查
        if (StringUtils.isEmpty(param.getImei()) || param.getImei().length() > 32) {
            result.setSuccess(false);
            result.setMsg("IMEI码不是1-32位，报告地址接口失败。");
            return result;
        }
        // 地址约束检查
        if (StringUtils.isEmpty(param.getLocation()) || param.getLocation().length() > 255) {
            result.setSuccess(false);
            result.setMsg("地址不是1-255位，报告地址接口失败。");
            return result;
        }

        int r = apiService.report(param);
        if (r > 0) {
            result.setSuccess(true);
            result.setMsg("报告地址成功");
        } else {
            result.setSuccess(false);
            result.setMsg("该POS机还没有入网，报告地址接口失败。");
        }
        return result;
    }

}
