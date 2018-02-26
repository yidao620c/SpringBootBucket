package com.enzhico.jwt.dao.entity;

import com.enzhico.jwt.common.constant.DictMap;
import com.enzhico.jwt.common.dao.entity.Pos;

import java.io.Serializable;

/**
 * Description: 以网点为分组的机具信息
 */
public class PosGroupInfo extends Pos implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 归属项目名称
     */
    private String projectName;

    /**
     * 机具数量（网点分组）
     */
    private Integer posCount;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getPosCount() {
        return posCount;
    }

    public void setPosCount(Integer posCount) {
        this.posCount = posCount;
    }

}
