package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.Role;

public interface RoleMapper extends GenericDao<Role, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    /**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param id
     * @return
     */
    List<Role> selectRolesByUserId(Integer userId);

    /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<Role> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 获取所有角色
	 * @return
	 */
	List<Role> selectListAll();

	void deleteUserRolesByUserid(@Param("userId")Integer userId);

	void insertUserRole(@Param("userId")int userId, @Param("roleId")int roleId);

}