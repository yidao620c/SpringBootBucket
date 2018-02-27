package com.xncoding.jwt.api;

import com.xncoding.jwt.api.model.BaseResponse;
import com.xncoding.jwt.api.model.LoginParam;
import com.xncoding.jwt.common.util.JWTUtil;
import com.xncoding.jwt.dao.entity.ManagerInfo;
import com.xncoding.jwt.service.ManagerInfoService;
import com.xncoding.jwt.shiro.ShiroKit;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登录接口类
 */
@RestController
public class LoginController {

    @Resource
    private ManagerInfoService managerInfoService;

    private static final Logger _logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public BaseResponse<String> login(@RequestHeader(name="Content-Type", defaultValue = "application/json") String contentType,
                                      @RequestBody LoginParam loginParam) {
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

    @PostMapping("/notifyLogin")
    public BaseResponse<String> notifyLogin(@RequestHeader(name="Content-Type", defaultValue = "application/json") String contentType,
                                            @RequestBody LoginParam loginParam) {
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
    public BaseResponse unauthorized() {
        return new BaseResponse<>(false, "Unauthorized", null);
    }

}
