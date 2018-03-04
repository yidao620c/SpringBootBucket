package com.xncoding.pos.controller;

import com.xncoding.pos.model.BaseResponse;
import com.xncoding.pos.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 接口类
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger _logger = LoggerFactory.getLogger(UserController.class);

    // 创建线程安全的Map
    private static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public BaseResponse<List<User>> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> r = new ArrayList<>(users.values());
        return new BaseResponse<>(true, "查询列表成功", r);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BaseResponse<String> postUser(@ModelAttribute User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(), user);
        return new BaseResponse<>(true, "新增成功", "");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<User> getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return new BaseResponse<>(true, "查询成功", users.get(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public BaseResponse<String> putUser(@PathVariable Long id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return new BaseResponse<>(true, "更新成功", "");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public BaseResponse<String> deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);
        return new BaseResponse<>(true, "删除成功", "");
    }

}
