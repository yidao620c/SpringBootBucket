package com.xncoding.trans.modules.common.vo;

import com.xncoding.trans.modules.common.anno.TableName;

/**
 * BscOfficeExeItem
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/6
 */
@TableName("NT_BSC_OFFICEEXEITEM")
public class BscOfficeExeItem {
    private String F_ID;
    private String F_CANTONID;
    private String F_OFFICEID;
    private String F_TOLLID;
    private String F_TOLLCODE;
    private String F_START;
    private String F_END;
    private String F_STATUS;
    private String F_VERSION;

    public String getF_ID() {
        return F_ID;
    }

    public void setF_ID(String f_ID) {
        F_ID = f_ID;
    }

    public String getF_CANTONID() {
        return F_CANTONID;
    }

    public void setF_CANTONID(String f_CANTONID) {
        F_CANTONID = f_CANTONID;
    }

    public String getF_OFFICEID() {
        return F_OFFICEID;
    }

    public void setF_OFFICEID(String f_OFFICEID) {
        F_OFFICEID = f_OFFICEID;
    }

    public String getF_TOLLID() {
        return F_TOLLID;
    }

    public void setF_TOLLID(String f_TOLLID) {
        F_TOLLID = f_TOLLID;
    }

    public String getF_TOLLCODE() {
        return F_TOLLCODE;
    }

    public void setF_TOLLCODE(String f_TOLLCODE) {
        F_TOLLCODE = f_TOLLCODE;
    }

    public String getF_START() {
        return F_START;
    }

    public void setF_START(String f_START) {
        F_START = f_START;
    }

    public String getF_END() {
        return F_END;
    }

    public void setF_END(String f_END) {
        F_END = f_END;
    }

    public String getF_STATUS() {
        return F_STATUS;
    }

    public void setF_STATUS(String f_STATUS) {
        F_STATUS = f_STATUS;
    }

    public String getF_VERSION() {
        return F_VERSION;
    }

    public void setF_VERSION(String f_VERSION) {
        F_VERSION = f_VERSION;
    }
}
