package com.xncoding.pos.dao.repository;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xncoding.pos.common.dao.entity.PosHistory;
import com.xncoding.pos.common.dao.repository.PosMapper;
import com.xncoding.pos.dao.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Description  :
 */
public interface PosInfoDao extends PosMapper {
    /**
     * 分页查询
     * @param page
     * @param param
     * @return
     */
    List<PosInfo> searchList(Pagination page, SearchPos param);

    /**
     * 以网点分组进行分页查询
     * @param page
     * @param param
     * @return
     */
    List<PosGroupInfo> searchListGroupByLocation(Pagination page, SearchPos param);

    PosInfo searchDetail(Integer id);

    PosHistory selectLastHistory(@Param("posId") Integer posId,
                                 @Param("location") String location);

    /**
     * 布放网点数量
     * @param projectId
     * @return
     */
    int searchLocationCount(@Param("projectId") Integer projectId,
                            @Param("pidList") List<Integer> pidList);

    /**
     * 网点机具列表分页
     * @param searchMonitor
     * @return
     */
    List<MonitorInfo> selectPosMonitorList(Pagination page, SearchMonitor searchMonitor);

    /**
     * 更新POS监控在线状态
     *
     * @param start
     * @return
     */
    int updateOnlineState(@Param("start") Date start);
}
