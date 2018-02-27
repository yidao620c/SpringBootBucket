package com.xncoding.jwt.api;

import com.xncoding.jwt.api.model.BaseResponse;
import com.xncoding.jwt.api.model.UnbindParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * 通知接口类
 */
@Api(value = "通知接口类", tags = "通知", description = "服务器给相应客户端推送通知")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "请求已完成"),
        @ApiResponse(code = 201, message = "资源成功被创建"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
        @ApiResponse(code = 401, message = "未授权客户机访问数据"),
        @ApiResponse(code = 403, message = "服务器接受请求，但是拒绝处理"),
        @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
        @ApiResponse(code = 500, message = "服务器出现异常")}
)
@RestController
@RequestMapping(value = "/notify")
public class NotifyController {

    private static final Logger _logger = LoggerFactory.getLogger(NotifyController.class);

    @ApiOperation(value = "解绑通知接口", notes = "后台管理员解除网点绑定后需调用此接口通知相应的POS机", produces = "application/json")
    @PostMapping("/unbind")
    public BaseResponse unbind(@RequestHeader(name = "Content-Type", defaultValue = "application/json") String contentType,
                               @RequestHeader(name = "Authorization", defaultValue = "token") String token,
                               @RequestBody UnbindParam unbindParam) {
        _logger.info("解绑通知接口start");
        return new BaseResponse<>(true, "解绑通知发送成功", null);
    }

}
