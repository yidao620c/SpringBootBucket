package com.xncoding.jwt.dao.entity;

import com.xncoding.jwt.common.constant.DictMap;
import com.xncoding.jwt.common.dao.entity.Manager;
import com.xncoding.jwt.common.dao.entity.Pos;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 机具信息
 */
public class PosInfo extends Pos implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 机具状态
     */
    private String posStateStr;
    /**
     * 归属项目名称
     */
    private String projectName;

    public String getPosStateStr() {
        return posStateStr;
    }

    public void setPosStateStr(String posStateStr) {
        this.posStateStr = posStateStr;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void buildTable() {
        posStateStr = DictMap.value(DictMap.KEY_POS_POS_STATUS, getPosState());
    }
}
