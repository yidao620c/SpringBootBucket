package com.xncoding.pos.dao.repository;

import com.xncoding.pos.dao.entity.SysRole;
import org.springframework.stereotype.Repository;

/**
 * Description  :
 */

@Repository
public interface SysRoleDao {
    SysRole findByRole(String role);
}
