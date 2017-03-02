package com.eshop4j.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.web.dao.UserMapper;
import com.eshop4j.web.model.User;
import com.eshop4j.web.service.UserService;

/**
 * 用户Service实现类
 *
 * @author Mignet
 * @since 2014年7月5日 上午11:54:24
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public GenericDao<User, Integer> getDao() {
        return userMapper;
    }
    
    @Override
    public User authentication(User user) {
        return userMapper.authentication(user);
    }

}
