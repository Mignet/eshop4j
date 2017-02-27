package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.User;

public interface UserMapper extends GenericDao<User, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List<User> selectListByUsername(@Param("username")String username, RowBounds page);

	User selectByUsername(String username);

	User authentication(@Param("record")User user);


}