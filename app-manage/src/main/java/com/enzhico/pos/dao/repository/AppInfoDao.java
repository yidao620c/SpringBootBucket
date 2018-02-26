package com.enzhico.pos.dao.repository;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.enzhico.pos.common.dao.entity.App;
import com.enzhico.pos.common.dao.entity.Pos;
import com.enzhico.pos.common.dao.repository.AppMapper;
import com.enzhico.pos.dao.entity.AppInfo;
import com.enzhico.pos.dao.entity.AppVersionInfo;
import com.enzhico.pos.dao.entity.SearchApp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: AppInfoDao
 */
public interface AppInfoDao extends AppMapper {

    List<String> selectAllAppNames(List<Integer> pidList);

    List<String> selectAllVersions(List<Integer> pidList);

    List<String> selectAllLocations(List<Integer> pidList);

    List<Pos> selectPosListByLocations(@Param("locations") List<String> locations);

    App selectNowVersionApp(@Param("applicationId") String applicationId);

    int countUserPermission(@Param("userId") Integer userId, @Param("applicationId") String applicatioinId);

    /**
     * 分页查询
     * @param page
     * @param param
     * @return
     */
    List<AppInfo> searchList(Pagination page, SearchApp param);

    /**
     * 灰度发布列表
     * @param appId
     * @return
     */
    List<Pos> grayList(@Param("id") Integer appId);

    List<AppVersionInfo> selectAppVersionList(List<Integer> pidList);
}
