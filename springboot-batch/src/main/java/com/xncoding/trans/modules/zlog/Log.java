package com.xncoding.trans.modules.zlog;

/**
 * Log
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/5
 */
public class Log {
    private int logid;
    private String msg;
    private String logtime;

    public int getLogid() {
        return logid;
    }

    public void setLogid(int logid) {
        this.logid = logid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLogtime() {
        return logtime;
    }

    public void setLogtime(String logtime) {
        this.logtime = logtime;
    }
}
