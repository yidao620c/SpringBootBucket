package com.xncoding.pos.dao.repository;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xncoding.pos.common.dao.repository.ManagerMapper;
import com.xncoding.pos.dao.entity.ManagerInfo;

import java.util.List;
import java.util.Map;

/**
 * Description  :
 */
public interface ManagerInfoDao extends ManagerMapper {
    ManagerInfo findByUsername(String username);
}
