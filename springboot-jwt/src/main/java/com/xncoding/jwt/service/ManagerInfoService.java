package com.xncoding.jwt.service;

import com.xncoding.jwt.dao.entity.ManagerInfo;
import com.xncoding.jwt.dao.entity.SysRole;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 后台用户管理
 */

@Service
public class ManagerInfoService {

    /**
     * 通过名称查找用户
     *
     * @param username
     * @return
     */
    public ManagerInfo findByUsername(String username) {
        ManagerInfo managerInfo = new ManagerInfo();
        managerInfo.setPids("1,2,3");
        managerInfo.setPidsList(Arrays.asList(1, 2, 3));
        managerInfo.setPnames("第1个,第2个");
        managerInfo.setState(1);
        managerInfo.setCreatedTime(new Date());
        managerInfo.setName("系统管理员");
        managerInfo.setPassword("4a496ba2a4172c71540fa643ddc8bb7c");
        managerInfo.setSalt("b4752b4b73034de06afb2db30fe19061");
        List<SysRole> roles = new ArrayList<>();
        SysRole role = new SysRole();
        role.setId(1);
        role.setRole("admin");
        role.setDescription("超级管理员");
        roles.add(role);
        managerInfo.setRoles(roles);
        return managerInfo;
    }

}
