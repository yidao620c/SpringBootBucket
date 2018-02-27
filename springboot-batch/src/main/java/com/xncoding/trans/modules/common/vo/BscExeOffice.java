package com.xncoding.trans.modules.common.vo;

import com.xncoding.trans.modules.common.anno.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

/**
 * BscExeOffice
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/5
 */
@TableName("NT_BSC_EXEOFFICE")
public class BscExeOffice {
    private String F_ID;
    private String F_CANTONID;
    private String F_CODE;
    private String F_NAME;
    private String F_MEMCODE;
    private String F_SUPDEPTID;
    private String F_COMDEPTID;
    private String F_CONTACTMAN;
    private String F_TEL;
    private String F_MOBIL;
    private String F_EMAIL;
    private String F_BGOFFICEID;
    private String F_INFOMOBIL;
    private String F_INFOMAN;
    private String F_LOGPASS;
    private String F_STARTDATE;
    private String F_STOPDATE;
    private String F_STATUS;
    private String F_MEMO;
    private String F_AUDITER;
    private String F_AUDITTIME;
    private String F_ISAUDIT;
    private Timestamp F_EDITTIME;
    private Integer F_PLATFORM_ID;
    private String F_ISPRINTBILL;

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

    public String getF_CODE() {
        return F_CODE;
    }

    public void setF_CODE(String f_CODE) {
        F_CODE = f_CODE;
    }

    public String getF_NAME() {
        return F_NAME;
    }

    public void setF_NAME(String f_NAME) {
        F_NAME = f_NAME;
    }

    public String getF_MEMCODE() {
        return F_MEMCODE;
    }

    public void setF_MEMCODE(String f_MEMCODE) {
        F_MEMCODE = f_MEMCODE;
    }

    public String getF_SUPDEPTID() {
        return F_SUPDEPTID;
    }

    public void setF_SUPDEPTID(String f_SUPDEPTID) {
        F_SUPDEPTID = f_SUPDEPTID;
    }

    public String getF_COMDEPTID() {
        return F_COMDEPTID;
    }

    public void setF_COMDEPTID(String f_COMDEPTID) {
        F_COMDEPTID = f_COMDEPTID;
    }

    public String getF_CONTACTMAN() {
        return F_CONTACTMAN;
    }

    public void setF_CONTACTMAN(String f_CONTACTMAN) {
        F_CONTACTMAN = f_CONTACTMAN;
    }

    public String getF_TEL() {
        return F_TEL;
    }

    public void setF_TEL(String f_TEL) {
        F_TEL = f_TEL;
    }

    public String getF_MOBIL() {
        return F_MOBIL;
    }

    public void setF_MOBIL(String f_MOBIL) {
        F_MOBIL = f_MOBIL;
    }

    public String getF_EMAIL() {
        return F_EMAIL;
    }

    public void setF_EMAIL(String f_EMAIL) {
        F_EMAIL = f_EMAIL;
    }

    public String getF_BGOFFICEID() {
        return F_BGOFFICEID;
    }

    public void setF_BGOFFICEID(String f_BGOFFICEID) {
        F_BGOFFICEID = f_BGOFFICEID;
    }

    public String getF_INFOMOBIL() {
        return F_INFOMOBIL;
    }

    public void setF_INFOMOBIL(String f_INFOMOBIL) {
        F_INFOMOBIL = f_INFOMOBIL;
    }

    public String getF_INFOMAN() {
        return F_INFOMAN;
    }

    public void setF_INFOMAN(String f_INFOMAN) {
        F_INFOMAN = f_INFOMAN;
    }

    public String getF_LOGPASS() {
        return F_LOGPASS;
    }

    public void setF_LOGPASS(String f_LOGPASS) {
        F_LOGPASS = f_LOGPASS;
    }

    public String getF_STARTDATE() {
        return F_STARTDATE;
    }

    public void setF_STARTDATE(String f_STARTDATE) {
        F_STARTDATE = f_STARTDATE;
    }

    public String getF_STOPDATE() {
        return F_STOPDATE;
    }

    public void setF_STOPDATE(String f_STOPDATE) {
        F_STOPDATE = f_STOPDATE;
    }

    public String getF_STATUS() {
        return F_STATUS;
    }

    public void setF_STATUS(String f_STATUS) {
        F_STATUS = f_STATUS;
    }

    public String getF_MEMO() {
        return F_MEMO;
    }

    public void setF_MEMO(String f_MEMO) {
        F_MEMO = f_MEMO;
    }

    public String getF_AUDITER() {
        return F_AUDITER;
    }

    public void setF_AUDITER(String f_AUDITER) {
        F_AUDITER = f_AUDITER;
    }

    public String getF_AUDITTIME() {
        return F_AUDITTIME;
    }

    public void setF_AUDITTIME(String f_AUDITTIME) {
        F_AUDITTIME = f_AUDITTIME;
    }

    public String getF_ISAUDIT() {
        return F_ISAUDIT;
    }

    public void setF_ISAUDIT(String f_ISAUDIT) {
        F_ISAUDIT = f_ISAUDIT;
    }

    public Timestamp getF_EDITTIME() {
        return F_EDITTIME;
    }

    public void setF_EDITTIME(Timestamp f_EDITTIME) {
        F_EDITTIME = f_EDITTIME;
    }

    public Integer getF_PLATFORM_ID() {
        return F_PLATFORM_ID;
    }

    public void setF_PLATFORM_ID(Integer f_PLATFORM_ID) {
        F_PLATFORM_ID = f_PLATFORM_ID;
    }

    public String getF_ISPRINTBILL() {
        return F_ISPRINTBILL;
    }

    public void setF_ISPRINTBILL(String f_ISPRINTBILL) {
        F_ISPRINTBILL = f_ISPRINTBILL;
    }
}
