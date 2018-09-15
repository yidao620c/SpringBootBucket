package com.xncoding.jwt.api;

import com.xncoding.jwt.api.model.BaseResponse;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * 机具管理API接口类
 */
@RestController
@RequestMapping(value = "/api/v1")
public class PublicController {

    private static final Logger _logger = LoggerFactory.getLogger(PublicController.class);

    /**
     * 入网绑定查询接口
     *
     * @return 是否入网
     */
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    @RequiresAuthentication
    public BaseResponse join(@RequestParam("imei") String imei) {
        _logger.info("入网查询接口 start... imei=" + imei);
        BaseResponse result = new BaseResponse();
        result.setSuccess(true);
        result.setMsg("已入网并绑定了网点");
        return result;
    }
}
