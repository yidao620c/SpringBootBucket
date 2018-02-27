package com.xncoding.aop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xncoding.aop.aspect.UserAccess;

/**
 * Description:
 */
@RestController
public class UserController {
    @RequestMapping("/first")
    public Object first() {
        return "first controller";
    }

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }

	@RequestMapping("/second")
    @UserAccess(desc = "second")
    public Object second() {
        return "second controller";
    }

}
