package com.xncoding.pos.controller;

import com.xncoding.pos.model.LoginParam;
import com.xncoding.pos.model.UnbindParam;
import com.xncoding.pos.model.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录接口类
 */
@RestController
public class LoginController {

    private static final Logger _logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public BaseResponse<String> login(@RequestHeader(name = "Content-Type", defaultValue = "application/json") String contentType,
                                      @RequestBody LoginParam loginParam) {
        _logger.info("用户请求登录获取Token");
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        return new BaseResponse<>(true, "Login success", username + password);
    }

    @PostMapping("/unbind")
    public BaseResponse<String> unbind(@RequestHeader(name = "Content-Type", defaultValue = "application/json") String contentType,
                                       @RequestHeader(name = "Authorization", defaultValue = "token") String token,
                                       @RequestBody UnbindParam unbindParam) {
        _logger.info("解绑通知接口start");
        String imei = unbindParam.getImei();
        String location = unbindParam.getLocation();
        return new BaseResponse<>(true, "解绑通知发送成功", "unbind");
    }

}
