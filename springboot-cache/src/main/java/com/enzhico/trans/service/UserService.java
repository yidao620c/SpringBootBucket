package com.enzhico.trans.service;

import com.enzhico.trans.dao.entity.User;
import com.enzhico.trans.dao.repository.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@CacheConfig(cacheNames = "users")
public class UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserMapper userMapper;

    /**
     * cacheNames 设置缓存的值
     * key：指定缓存的key，这是指参数id值。 key可以使用spEl表达式
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "user1", key = "#id")
    public User getById(int id) {
        logger.info("获取用户start...");
        return userMapper.selectById(id);
    }

    /***
     * 如果设置sync=true，
     * 如果缓存中没有数据，多个线程同时访问这个方法，则只有一个方法会执行到方法，其它方法需要等待
     * 如果缓存中已经有数据，则多个线程可以同时从缓存中获取数据
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "user1", key = "#id", sync = true)
    public User getById2(int id) {
        logger.info("获取用户start...");
        return userMapper.selectById(id);
    }

    /**
     * 以上我们使用默认的keyGenerator，对应spring的SimpleKeyGenerator
     * 如果你的使用很复杂，我们也可以自定义myKeyGenerator的生成key
     * <p>
     * key和keyGenerator是互斥，如果同时制定会出异常
     * The key and keyGenerator parameters are mutually exclusive and an operation specifying both will result in an exception.
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "user1", keyGenerator = "myKeyGenerator")
    public User queryUserById(int id) {
        logger.info("queryUserById,id={}", id);
        return userMapper.selectById(id);
    }

    /**
     * 每次执行都会执行方法，同时使用新的返回值的替换缓存中的值
     *
     * @param user
     */
    @CachePut(cacheNames = "user1", key = "#user.id")
    public void createUser(User user) {
        logger.info("创建用户start...");
        userMapper.insert(user);
    }

    /**
     * 每次执行都会执行方法，同时使用新的返回值的替换缓存中的值
     *
     * @param user
     */
    @CachePut(cacheNames = "user1", key = "#user.id")
    public void updateUser(User user) {
        logger.info("更新用户start...");
        userMapper.updateById(user);
    }

    /**
     * 对符合key条件的记录从缓存中book1移除
     */
    @CacheEvict(cacheNames = "user1", key = "#id")
    public void deleteById(int id) {
        logger.info("删除用户start...");
        userMapper.deleteById(id);
    }

    /**
     * allEntries = true: 清空user1里的所有缓存
     */
    @CacheEvict(cacheNames="user1", allEntries=true)
    public void clearUser1All(){
        logger.info("clearAll");
    }

}
