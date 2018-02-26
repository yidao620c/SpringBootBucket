package com.enzhico.pos.service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.enzhico.pos.common.dao.entity.App;
import com.enzhico.pos.common.dao.entity.Pos;
import com.enzhico.pos.common.dao.entity.Project;
import com.enzhico.pos.common.dao.entity.ProjectUser;
import com.enzhico.pos.common.dao.repository.AppMapper;
import com.enzhico.pos.common.dao.repository.PosMapper;
import com.enzhico.pos.common.dao.repository.ProjectMapper;
import com.enzhico.pos.common.dao.repository.ProjectUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 项目管理Service
 */

@Service
public class ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private ProjectUserMapper projectUserMapper;

    @Resource
    protected PosMapper posMapper;

    @Resource
    private AppMapper appMapper;

    /**
     * 查询所有项目的数量
     * @return 项目的数量
     */
    public int selectProjectNum() {
        return projectMapper.selectCount(null);
    }

    /**
     * 查询该项目下所有用户数量
     * @return
     */
    public int selectUsersByPrjectId(Integer projectId) {
        return projectUserMapper.selectCount(Condition.create().eq("project_id", projectId));
    }

    /**
     * 查询所有项目
     * @return 所有项目
     */
    public List<Project> selectAll() {
        return projectMapper.selectList(null);
    }

    /**
     * 添加项目
     * @param project 项目
     * @return 结果
     */
    public int add(Project project) {
        return projectMapper.insert(project);
    }

    /**
     * 删除项目
     * @param id 项目ID
     * @return 结果
     */
    public int delete(Integer id) {
        // 先删项目表
        projectMapper.deleteById(id);
        // 删项目用户关联表记录
        projectUserMapper.delete(Condition.create().eq("project_id", id));
        // 更新pos机记录
        Pos pos = new Pos();
        pos.setProjectId(0);
        Date now = new Date();
        pos.setUpdatedTime(now);
        posMapper.update(pos, Condition.create().eq("project_id", id));
        // 更新App表记录
        App app = new App();
        app.setProjectId(0);
        app.setUpdatedTime(now);
        appMapper.update(app, Condition.create().eq("project_id", id));
        return 1;
    }

}
