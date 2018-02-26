package com.enzhico.pos.dao.repository;

import com.enzhico.pos.dao.entity.SysRole;
import org.springframework.stereotype.Repository;

/**
 * Description  :
 */

@Repository
public interface SysRoleDao {
    SysRole findByRole(String role);
}
