package com.xncoding.trans.modules.vtoll;

import javax.validation.constraints.Size;

/**
 * BudgetVtoll
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/3
 */
public class BudgetVtoll {
    private String id;
    private String year;
    private String tollid;
    private String budgetid;
    private String cbudgetid;
    private String version;
    /**
     * 使用JSR-303注解来校验数据
     */
    @Size(max = 100)
    private String auditmsg;
    private String trialstatus;
    private String firauditer;
    private String firaudittime;
    private String finauditer;
    private String finaudittime;
    private String edittime;
    private String startdate;
    private String enddate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTollid() {
        return tollid;
    }

    public void setTollid(String tollid) {
        this.tollid = tollid;
    }

    public String getBudgetid() {
        return budgetid;
    }

    public void setBudgetid(String budgetid) {
        this.budgetid = budgetid;
    }

    public String getCbudgetid() {
        return cbudgetid;
    }

    public void setCbudgetid(String cbudgetid) {
        this.cbudgetid = cbudgetid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuditmsg() {
        return auditmsg;
    }

    public void setAuditmsg(String auditmsg) {
        this.auditmsg = auditmsg;
    }

    public String getTrialstatus() {
        return trialstatus;
    }

    public void setTrialstatus(String trialstatus) {
        this.trialstatus = trialstatus;
    }

    public String getFirauditer() {
        return firauditer;
    }

    public void setFirauditer(String firauditer) {
        this.firauditer = firauditer;
    }

    public String getFiraudittime() {
        return firaudittime;
    }

    public void setFiraudittime(String firaudittime) {
        this.firaudittime = firaudittime;
    }

    public String getFinauditer() {
        return finauditer;
    }

    public void setFinauditer(String finauditer) {
        this.finauditer = finauditer;
    }

    public String getFinaudittime() {
        return finaudittime;
    }

    public void setFinaudittime(String finaudittime) {
        this.finaudittime = finaudittime;
    }

    public String getEdittime() {
        return edittime;
    }

    public void setEdittime(String edittime) {
        this.edittime = edittime;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
