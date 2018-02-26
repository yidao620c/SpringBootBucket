package com.enzhico.jwt.api;

import com.enzhico.jwt.api.model.BaseResponse;
import com.enzhico.jwt.api.model.LoginParam;
import com.enzhico.jwt.common.util.JWTUtil;
import com.enzhico.jwt.dao.entity.ManagerInfo;
import com.enzhico.jwt.service.ManagerInfoService;
import com.enzhico.jwt.shiro.ShiroKit;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * 登录接口类
 */
@Api(value = "登录请求接口类", tags = "登录", description = "用户请求登录获取Token")
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
public class LoginController {

    @Resource
    private ManagerInfoService managerInfoService;

    private static final Logger _logger = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation(value = "登录认证接口", notes = "不管是接口还是WebSocket连接都需要先调用此接口拿到Token，然后再通过Token调用相应接口，最好使用HTTPS协议。调用RESTful API方式不需要传后面两个参数，如果通过WebSocket连接需要传递", produces = "application/json")
    @PostMapping("/login")
    public BaseResponse<String> login(@RequestHeader(name="Content-Type", defaultValue = "application/json") String contentType,
                                      @ApiParam(value = "登录参数") @RequestBody LoginParam loginParam) {
        _logger.info("用户请求登录获取Token");
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        String appid = loginParam.getAppid();
        String imei = loginParam.getImei();
        ManagerInfo user = managerInfoService.findByUsername(username);
        //盐（用户名+随机数）
        String salt = user.getSalt();
        //原密码
        String encodedPassword = ShiroKit.md5(password, username + salt);
        if (user.getPassword().equals(encodedPassword)) {
            if (StringUtils.isNotEmpty(appid) && StringUtils.isNotEmpty(imei)) {
                return new BaseResponse<>(true, "Login success", JWTUtil.signSocket(username, encodedPassword, appid, imei));
            }
            return new BaseResponse<>(true, "Login success", JWTUtil.sign(username, encodedPassword));
        } else {
            throw new UnauthorizedException();
        }
    }

    @ApiOperation(value = "推送消息登录认证接口", notes = "这里的密码是加密后的密码", produces = "application/json")
    @PostMapping("/notifyLogin")
    public BaseResponse<String> notifyLogin(@RequestHeader(name="Content-Type", defaultValue = "application/json") String contentType,
                                            @ApiParam(value = "登录参数") @RequestBody LoginParam loginParam) {
        _logger.info("登录用户推送请求登录获取Token");
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        ManagerInfo user = managerInfoService.findByUsername(username);
        if (user.getPassword().equals(password)) {
            return new BaseResponse<>(true, "Login success", JWTUtil.sign(username, password));
        } else {
            throw new UnauthorizedException();
        }
    }

    @GetMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ApiIgnore
    public BaseResponse unauthorized() {
        return new BaseResponse<>(false, "Unauthorized", null);
    }

}
