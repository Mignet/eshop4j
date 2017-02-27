package com.linkwee.web.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.Permission;

/**
 * 权限 业务接口
 * 
 * @author Mignet
 * @since 2014年6月10日 下午12:02:39
 **/
public interface PermissionService extends GenericService<Permission, Integer> {

    /**
     * 通过角色id 查询角色 拥有的权限
     * 
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(Integer roleId);

    /**
     * 查询所有数据<翻页>
     * @param page
     * @return
     */
	List<Permission> selectList(RowBounds page);

	List<Permission> selectByName(String permissionName);

	/**
	 * 更新角色权限
	 * @param roleid
	 * @param permissions
	 * @return
	 */
	boolean updateRolePermissions(String roleid, String[] permissions);

	List<Permission> selectListAll();

}
