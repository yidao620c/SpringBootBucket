package com.xncoding.echarts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IndexController
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/24
 */
@Controller
public class IndexController {
    /**
     * 首页
     */
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
}
