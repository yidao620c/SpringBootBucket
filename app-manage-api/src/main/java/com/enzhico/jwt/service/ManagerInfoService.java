package com.enzhico.jwt.service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.enzhico.jwt.common.dao.entity.Manager;
import com.enzhico.jwt.common.dao.entity.Project;
import com.enzhico.jwt.common.dao.entity.ProjectUser;
import com.enzhico.jwt.common.dao.repository.ProjectMapper;
import com.enzhico.jwt.common.dao.repository.ProjectUserMapper;
import com.enzhico.jwt.dao.entity.ManagerInfo;
import com.enzhico.jwt.dao.entity.SearchUser;
import com.enzhico.jwt.dao.repository.ManagerInfoDao;
import com.enzhico.jwt.exception.ForbiddenUserException;
import com.enzhico.jwt.shiro.ShiroKit;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 后台用户管理
 */

@Service
public class ManagerInfoService {

    @Resource
    private ManagerInfoDao managerInfoDao;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private ProjectUserMapper projectUserMapper;

    /**
     * 通过名称查找用户
     *
     * @param username
     * @return
     */
    public ManagerInfo findByUsername(String username) {
        ManagerInfo managerInfo = managerInfoDao.findByUsername(username);
        if (managerInfo == null) {
            throw new UnknownAccountException();
        }
        if (managerInfo.getState() == 2) {
            throw new ForbiddenUserException();
        }
        if (managerInfo.getPidsList() == null) {
            managerInfo.setPidsList(Collections.singletonList(0));
        } else if (managerInfo.getPidsList().size() == 0) {
            managerInfo.getPidsList().add(0);
        }
        return managerInfo;
    }

    /**
     * 检查用户名重复
     *
     * @param username
     * @return
     */
    public int checkUsername(String username) {
        return managerInfoDao.selectCount(Condition.create().eq("username", username));
    }

    /**
     * 通过ID查找用户
     *
     * @param id
     * @return
     */
    public ManagerInfo selectUser(Integer id) {
        ManagerInfo managerInfo = managerInfoDao.selectUser(id);
        List<Integer> li = new ArrayList<>();
        if (StringUtils.isNotEmpty(managerInfo.getPids())) {
            for (String s : managerInfo.getPids().split(",")) {
                li.add(Integer.parseInt(s));
            }
        }
        managerInfo.setPidsList(li);
        return managerInfo;
    }

    /**
     * 所有项目列表
     *
     * @return
     */
    public List<Project> allProjects() {
        return projectMapper.selectList(null);
    }

    /**
     * 执行添加用户
     *
     * @param manager
     * @return
     */
    public int addUser(ManagerInfo manager) {
        String salt = ShiroKit.getRandomSalt(16);
        //进行散列
        SimpleHash hash = new SimpleHash("md5", "12345678", manager.getUsername() + salt, 2);
        manager.setSalt(salt);
        manager.setPassword(hash.toHex());
        managerInfoDao.insert(manager);

        // 更新项目用户关联表
        if (StringUtils.isNotEmpty(manager.getPids())) {
            for (String pid : manager.getPids().split(",")) {
                ProjectUser projectUser = new ProjectUser();
                projectUser.setUserId(manager.getId());
                projectUser.setProjectId(Integer.valueOf(pid));
                projectUserMapper.insert(projectUser);
            }
        }
        return 1;
    }

    /**
     * 执行更新用户
     *
     * @param manager
     * @return
     */
    public int editUser(ManagerInfo manager) {
        managerInfoDao.updateById(manager);
        // 更新项目用户关联表
        if (StringUtils.isNotEmpty(manager.getPids())) {
            // 先把该用户的项目关联删了
            projectUserMapper.delete(Condition.create().eq("user_id", manager.getId()));
            // 然后插入选择的项目关系
            for (String pid : manager.getPids().split(",")) {
                ProjectUser projectUser = new ProjectUser();
                projectUser.setUserId(manager.getId());
                projectUser.setProjectId(Integer.valueOf(pid));
                projectUserMapper.insert(projectUser);
            }
        }
        return 1;
    }

    /**
     * 执行重置密码
     *
     * @param id 用户ID
     * @return
     */
    public int resetPassword(Integer id, String username) {
        String salt = ShiroKit.getRandomSalt(16);
        //进行散列
        SimpleHash hash = new SimpleHash("md5", "12345678", username + salt, 2);
        String password = hash.toHex();
        Manager p = new Manager();
        p.setId(id);
        p.setPassword(password);
        p.setSalt(salt);
        p.setUpdatedTime(new Date());
        return managerInfoDao.updateById(p);
    }

    /**
     * 执行删除用户
     *
     * @param id 用户ID
     * @return
     */
    public int deleteUer(Integer id) {
        return managerInfoDao.deleteById(id);
    }

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param password 加密后的密码
     * @return result
     */
    public int updatePassword(String username, String password) {
        Manager v = new Manager();
        v.setPassword(password);
        v.setUpdatedTime(new Date());
        return managerInfoDao.update(v, Condition.create().eq("username", username));
    }

    /**
     * 分页查询用户
     *
     * @param page
     * @param searchUser
     * @return
     */
    public List<ManagerInfo> searchUsers(Page<ManagerInfo> page, SearchUser searchUser) {
        List<ManagerInfo> list = managerInfoDao.selectUsers(page, searchUser);
        for (ManagerInfo managerInfo : list) {
            managerInfo.buildTable();
        }
        return list;
    }

}
