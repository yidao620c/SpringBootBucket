package com.xncoding.pos.controller;

import com.xncoding.pos.config.properties.MyProperties;
import com.xncoding.pos.exception.ForbiddenUserException;
import com.xncoding.pos.service.ManagerInfoService;
import com.xncoding.pos.shiro.IncorrectCaptchaException;
import com.xncoding.pos.shiro.ShiroKit;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Description: 登录验证
 */

// 只用同时具有permission:view和permission:aix权限才能访问
//@RequiresPermissions(value={"permission:view","permission:aix"}, logical= Logical.AND)
//@RequiresPermissions(value={"permission:view","permission:aix"}, logical= Logical.OR)一个就行

@Controller
public class LoginController {

    @Resource
    private ManagerInfoService managerInfoService;
    @Resource
    private MyProperties myProperties;

    private static final Logger _logger = LoggerFactory.getLogger(LoginController.class);

    //登录页(shiro配置需要两个/login 接口,一个是get用来获取登陆页面,一个用post用于登录)
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        if (ShiroKit.isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }

    // 登录提交地址和applicationontext-shiro.xml配置的loginurl一致。 (配置文件方式的说法)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Map<String, Object> map) {
        _logger.info("登录方法start.........");
        // 登录失败从request中获取shiro处理的异常信息。shiroLoginFailure:就是shiro异常类的全类名.
        Object exception = request.getAttribute("shiroLoginFailure");
        String msg;
        if (exception != null) {
            if (UnknownAccountException.class.isInstance(exception)) {
                msg = "用户名不正确，请重新输入";
            } else if (IncorrectCredentialsException.class.isInstance(exception)) {
                msg = "密码错误，请重新输入";
            } else if (IncorrectCaptchaException.class.isInstance(exception)) {
                msg = "验证码错误";
            } else if (ForbiddenUserException.class.isInstance(exception)) {
                msg = "该用户已被禁用，如有疑问请联系系统管理员。";
            } else {
                msg = "发生未知错误，请联系管理员。";
            }
            map.put("username", request.getParameter("username"));
            map.put("password", request.getParameter("password"));
            map.put("msg", msg);
            return "login";
        }
        //如果已经登录，直接跳转主页面
        return "index";
    }

    /**
     * 主页
     * @param session
     * @param model
     * @return
     */
    @RequestMapping({"/", "/index"})
    public String index(HttpSession session, Model model) {
        // _logger.info("访问首页start...");
        // 做一些其他事情，比如把项目的数量放到session中
        if (ShiroKit.hasRole("admin") && session.getAttribute("projectNum") == null) {
            session.setAttribute("projectNum", 2);
        }
        if (session.getAttribute("picsUrlPrefix") == null) {
            // 图片访问URL前缀
            session.setAttribute("picsUrlPrefix", myProperties.getPicsUrlPrefix());
        }
        return "index";
    }

    /**
     * 欢迎页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request, Model model) {
        return "modules/common/welcome";
    }


}
