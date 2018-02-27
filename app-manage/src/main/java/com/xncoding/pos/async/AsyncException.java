package com.xncoding.pos.async;

/**
 * 异步方法异常
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/25
 */
public class AsyncException extends RuntimeException {
    public AsyncException() {
        super();
    }

    public AsyncException(String msg) {
        super(msg);
    }

    public AsyncException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    /**
     * 错误代码
     */
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
