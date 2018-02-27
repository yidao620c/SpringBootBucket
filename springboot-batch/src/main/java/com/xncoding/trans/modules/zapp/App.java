package com.xncoding.trans.modules.zapp;

import com.xncoding.trans.modules.common.anno.TableName;

/**
 * App
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/5
 */
@TableName("Z_TEST_APP")
public class App {
    private int appid;
    private String zname;
    private String flag;

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
