package com.xncoding.pos.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Description  : 验证码错误异常
 */
public class IncorrectCaptchaException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public IncorrectCaptchaException() {
        super();
    }

    public IncorrectCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCaptchaException(String message) {
        super(message);
    }

    public IncorrectCaptchaException(Throwable cause) {
        super(cause);
    }

}
