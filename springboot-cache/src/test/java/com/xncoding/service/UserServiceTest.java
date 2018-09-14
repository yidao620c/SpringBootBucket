package com.xncoding.service;

import com.xncoding.trans.Application;
import com.xncoding.trans.dao.entity.User;
import com.xncoding.trans.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * UserServiceTest
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testCache() {
        // 创建一个用户admin
        int id = new Random().nextInt(1000);
        User user = new User(id, "admin", "admin");
        userService.createUser(user);

        // 再创建一个用户xiong
        int id2 = new Random().nextInt(1000);
        User user2 = new User(id2, "xiong", "neng");
        userService.createUser(user2);

        // 查询所有用户列表
        List<User> list = userService.getAllUsers();
        assertEquals(list.size(), 2);

        // 两次访问看看缓存命中情况
        User user3 = userService.getById(id); // 第1次访问
        assertEquals(user3.getPassword(), "admin");
        User user4 = userService.getById(id); // 第2次访问
        assertEquals(user4.getPassword(), "admin");

        // 更新用户密码
        user4.setPassword("123456");
        userService.updateUser(user4);

        // 更新完成后再次访问用户
        User user5 = userService.getById(id); // 第4次访问
        assertEquals(user5.getPassword(), "123456");

        // 删除用户admin
        userService.deleteById(id);
        assertNull(userService.getById(id));
    }
}
