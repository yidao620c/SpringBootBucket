package com.xncoding.trans.service;

import com.xncoding.trans.dao.entity.User;
import com.xncoding.trans.dao.repository.UserMapper;
import com.xncoding.trans.exception.MyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(readOnly = true)
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User getById(int id) {
        return userMapper.selectById(id);
    }

    /**
     * 增删改要写 ReadOnly=false 为可写
     * @param user 用户
     */
    @Transactional(readOnly = false)
    public void updateUserError(User user) {
        userMapper.updateById(user);
        errMethod(); // 执行一个会抛出异常的方法
    }

    @Transactional(readOnly = false, noRollbackFor = {MyException.class})
    public void updateUserError2(User user) {
        userMapper.updateById(user);
        errMethod2(); // 执行一个会抛出自定义异常的方法
    }

    private void errMethod() {
        System.out.println("error");
        throw new RuntimeException("runtime");
    }
    private void errMethod2() {
        System.out.println("error");
        throw new MyException("runtime");
    }
}
