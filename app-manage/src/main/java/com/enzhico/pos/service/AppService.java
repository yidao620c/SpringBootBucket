package com.enzhico.pos.service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.enzhico.pos.common.dao.entity.*;
import com.enzhico.pos.common.dao.repository.AppPublishMapper;
import com.enzhico.pos.common.dao.repository.PosMapper;
import com.enzhico.pos.common.dao.repository.ProjectMapper;
import com.enzhico.pos.common.util.CommonUtil;
import com.enzhico.pos.config.properties.MyProperties;
import com.enzhico.pos.dao.entity.*;
import com.enzhico.pos.dao.repository.AppInfoDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * App管理Service
 */

@Service
public class AppService {

    @Resource
    private AppInfoDao appInfoDao;
    @Resource
    private PosMapper posMapper;
    @Resource
    private AppPublishMapper appPublishMapper;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private MyProperties myProperties;

    /**
     * 查询所有应用名称
     * @param pidList 用户所属项目ID
     * @return 所有应用名称
     */
    public List<String> selectAllAppNames(List<Integer> pidList) {
        return appInfoDao.selectAllAppNames(pidList);
    }

    /**
     * 应用名称与版本号的对应关系
     * @param pidList 用户所属项目ID
     * @return 应用名称与版本号的对应关系
     */
    public Map<String, List<String>> selectAppVersionList(List<Integer> pidList) {
        List<AppVersionInfo> list = appInfoDao.selectAppVersionList(pidList);
        Map<String, List<String>> map = new HashMap<>();
        if (list != null) {
            for (AppVersionInfo appVersionInfo : list) {
                map.put(appVersionInfo.getAppName(), appVersionInfo.getVersions());
            }
        }
        return map;
    }

    /**
     * 查询所有版本号
     * @param pidList 用户所属项目ID
     * @return 所有版本号
     */
    public List<String> selectAllVersions(List<Integer> pidList) {
        return appInfoDao.selectAllVersions(pidList);
    }

    /**
     * 查询所有项目
     * @param pidList 用户所属项目ID
     * @return
     */
    public List<Project> selectAllProjects(List<Integer> pidList) {
        return projectMapper.selectList(Condition.create().in("id", pidList));
    }

    /**
     * 查询所有网点
     * @param pidList 用户所属项目ID
     * @return
     */
    public List<String> selectAllLocations(List<Integer> pidList) {
        return appInfoDao.selectAllLocations(pidList);
    }

    /**
     * 根据网点列表查询Pos机
     * @param locations
     * @return
     */
    public List<Pos> selectPosListByLocations(List<String> locations) {
        return appInfoDao.selectPosListByLocations(locations);
    }

    /**
     * 分页查询
     * @param page
     * @param searchApp
     * @return
     */
    public List<AppInfo> searchList(Page<AppInfo> page, SearchApp searchApp) {
        List<AppInfo> list = appInfoDao.searchList(page, searchApp);
        for (AppInfo appInfo : list) {
            appInfo.buildTable();
            appInfo.setDownloadUrl(myProperties.getFilesUrlPrefix()
                    + appInfo.getName() + "_" + appInfo.getVersion() + ".apk");
        }
        return list;
    }

    /**
     * 灰度发布查询
     * @return
     */
    public List<Pos> grayList(Integer appId) {
        return appInfoDao.grayList(appId);
    }

    /**
     * 根据imei查询POS
     * @param imei
     * @return
     */
    public Pos selectByImei(String imei) {
        Pos p = new Pos();
        p.setImei(imei);
        return posMapper.selectOne(p);
    }

    /**
     * 全网发布
     * @param appId
     */
    public void publishAll(Integer appId) {
        // 先删除灰度发布记录
        appPublishMapper.delete(Condition.create().eq("app_id", appId));
        // 然后更新App
        App app = new App();
        app.setPublishRange(1);
        app.setUpdatedTime(new Date());
        app.setId(appId);
        appInfoDao.updateById(app);
    }

    /**
     * 用户发布权限检查
     * @param userId 登录用户ID
     * @param applicationId 应用ID
     * @return 是否有权限发布
     */
    public boolean checkUserPublishPermission(Integer userId, String applicationId) {
        return appInfoDao.countUserPermission(userId, applicationId) > 0;
    }

    /**
     * 检查版本号的合法性，只能升版本发布
     * @param applicationId APP唯一标识
     * @param version 版本号
     * @return 版本号是否更高
     */
    public boolean checkAppVersion(String applicationId, String version) {
        // 先检查是否存在至少一个APP记录
        int appCount = appInfoDao.selectCount(Condition.create().eq("application_id", applicationId));
        if (appCount == 0) {
            return true;
        }
        App nowApp = appInfoDao.selectNowVersionApp(applicationId);
        if (nowApp == null) {
            throw new NullPointerException();
        }
        return CommonUtil.isNewer(version, nowApp.getVersion());
    }


    /**
     * 添加新版本
     */
    public void addVersion(PublishParam param, ManagerInfo manager) {
        // 通过application Id获取Project
        Project p = new Project();
        p.setApplicationId(param.getApplicationId());
        Project project = projectMapper.selectOne(p);
        // 新建APP
        App app = new App();
        app.setName(param.getName());
        app.setVersion(param.getVersion());
        app.setApplicationId(param.getApplicationId());
        app.setProjectId(project.getId());
        app.setTips(param.getTips());
        Date now = new Date();
        app.setPublishtime(now);
        app.setPublishRange(param.getPublishRange());
        app.setOperatorId(manager.getId());
        appInfoDao.insert(app);

        // 如果是灰度发布，则更新APP发布表
        if (param.getPublishRange() == 2 && StringUtils.isNotEmpty(param.getGrayIds())) {
            String grayIds = param.getGrayIds();
            for (String posId : grayIds.split(",")) {
                AppPublish appPublish = new AppPublish();
                appPublish.setAppId(app.getId());
                appPublish.setPosId(Integer.valueOf(posId));
                appPublishMapper.insert(appPublish);
            }
        }
    }


}
