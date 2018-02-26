package com.enzhico.jwt.api;

import com.enzhico.jwt.api.model.BaseResponse;
import io.swagger.annotations.Api;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 注意这个统一异常处理器只对认证过的用户调用接口中的异常有作用，对AuthenticationException没有用
 */
@RestControllerAdvice
@Api(value = "机具管理API接口类", tags = "机具管理接口", description = "提供POS机的入网和监控的统一管理")
public class ExceptionController {

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public BaseResponse handle401(ShiroException e) {
        return new BaseResponse(false, "shiro的异常", null);
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public BaseResponse handle401() {
        return new BaseResponse(false, "UnauthorizedException", null);
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse globalException(HttpServletRequest request, Throwable ex) {
        return new BaseResponse(false, "其他异常", null);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}

