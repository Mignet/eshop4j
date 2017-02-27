package com.eshop4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.Permission;

public interface PermissionMapper extends GenericDao<Permission, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    /**
     * 通过角色id 查询角色 拥有的权限
     * 
     * @param roleId
     * @return
     */
	List<Permission> selectPermissionsByRoleId(Integer roleId);

	List<Permission> selectList(RowBounds page);

	List<Permission> selectByName(String permissionName);

	/**
	 * 可分配权限列表
	 * @return
	 */
	List<Permission> selectListAll();

	/**
	 * 删除某角色关联的权限
	 * @param roleid
	 */
	void deleteRolePermissionsByRoleid(@Param("roleId")int roleId);

	/**
	 * 插入角色权限关联
	 * @param roleid
	 * @param permissionid
	 */
	void insertRolePermission(@Param("roleId")int roleId, @Param("permissionId")int permissionId);

	void deleteRolePermissionsByPermissionId(@Param("permissionId")Integer permissionId);
}