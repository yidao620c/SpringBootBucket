package com.enzhico.pos.dao.repository;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.enzhico.pos.common.dao.repository.ManagerMapper;
import com.enzhico.pos.dao.entity.ManagerInfo;
import com.enzhico.pos.dao.entity.SearchUser;

import java.util.List;
import java.util.Map;

/**
 * Description  :
 */
public interface ManagerInfoDao extends ManagerMapper {
    ManagerInfo findByUsername(String username);

    List<ManagerInfo> selectUsers(Pagination page, SearchUser param);

    ManagerInfo selectUser(Integer id);
}
