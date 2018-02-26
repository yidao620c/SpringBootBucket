package com.enzhico.jwt.dao.repository;

import com.enzhico.jwt.api.model.VersionParam;
import com.enzhico.jwt.api.model.VersionResult;
import org.apache.ibatis.annotations.Param;

/**
 * Description  :
 */
public interface ApiDao {
    /**
     * 查询版本发布
     *
     * @param param
     * @return
     */
    VersionResult selectPublishCount(VersionParam param);

    /**
     * 根据imei码获取session id
     *
     * @param imei imei码
     * @return session id
     */
    String selectSessionId(@Param("imei") String imei);
}
