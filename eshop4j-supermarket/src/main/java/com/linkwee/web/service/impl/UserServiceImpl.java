package com.linkwee.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.web.dao.RoleMapper;
import com.linkwee.web.dao.UserMapper;
import com.linkwee.web.model.User;
import com.linkwee.web.service.UserService;

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
    @Resource
    private RoleMapper roleMapper;

    @Override
    public int insert(User model) {
        return userMapper.insertSelective(model);
    }

    @Override
    public int update(User model) {
        return userMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public int delete(Integer id) {
    	roleMapper.deleteUserRolesByUserid(id);
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User authentication(User user) {
        return userMapper.authentication(user);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public GenericDao<User, Integer> getDao() {
        return userMapper;
    }

	@Override
	public List<User> selectListByName(String username, RowBounds page) {
		return userMapper.selectListByUsername(username,page);
	}

	@Override
	public User selectByUsername(String username) {
		return userMapper.selectByUsername(username);
	}

}
