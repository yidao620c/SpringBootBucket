package com.xncoding.jwt.dao.entity;

import com.xncoding.jwt.common.constant.DictMap;
import com.xncoding.jwt.common.dao.entity.Pos;
import com.xncoding.jwt.common.dao.entity.PosMonitor;

import java.io.Serializable;

/**
 * Description: 机具信息
 */
public class MonitorInfo extends PosMonitor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 机具状态
     */
    private String onlineStateStr;

    /**
     * 机具IMEI码
     */
    private String imei;

    /**
     * 机具型号
     */
    private String series;

    public String getOnlineStateStr() {
        return onlineStateStr;
    }

    public void setOnlineStateStr(String onlineStateStr) {
        this.onlineStateStr = onlineStateStr;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public void buildTable() {
        onlineStateStr = DictMap.value(DictMap.KEY_POS_MONITOR_STATUS, getOnlineState());
    }
}
