package com.xncoding.trans.service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.xncoding.trans.dao.entity.User;
import com.xncoding.trans.dao.repository.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserMapper userMapper;

    /**
     * cacheNames 设置缓存的值
     * key：指定缓存的key，这是指参数id值。key可以使用spEl表达式
     *
     * @param id
     * @return
     */
    @Cacheable(value = "userCache", key = "#id", unless="#result == null")
    public User getById(int id) {
        logger.info("获取用户start...");
        return userMapper.selectById(id);
    }

    @Cacheable(value = "allUsersCache", unless = "#result.size() == 0")
    public List<User> getAllUsers() {
        logger.info("获取所有用户列表");
        return userMapper.selectList(null);
    }

    /**
     * 创建用户，同时使用新的返回值的替换缓存中的值
     * 创建用户后会将allUsersCache缓存全部清空
     */
    @Caching(
            put = {@CachePut(value = "userCache", key = "#user.id")},
            evict = {@CacheEvict(value = "allUsersCache", allEntries = true)}
    )
    public User createUser(User user) {
        logger.info("创建用户start..., user.id=" + user.getId());
        userMapper.insert(user);
        return user;
    }

    /**
     * 更新用户，同时使用新的返回值的替换缓存中的值
     * 更新用户后会将allUsersCache缓存全部清空
     */
    @Caching(
            put = {@CachePut(value = "userCache", key = "#user.id")},
            evict = {@CacheEvict(value = "allUsersCache", allEntries = true)}
    )
    public User updateUser(User user) {
        logger.info("更新用户start...");
        userMapper.updateById(user);
        return user;
    }

    /**
     * 对符合key条件的记录从缓存中移除
     * 删除用户后会将allUsersCache缓存全部清空
     */
    @Caching(
            evict = {
                    @CacheEvict(value = "userCache", key = "#id"),
                    @CacheEvict(value = "allUsersCache", allEntries = true)
            }
    )
    public void deleteById(int id) {
        logger.info("删除用户start...");
        userMapper.deleteById(id);
    }

}
