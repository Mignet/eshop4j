package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.Role;

/**
 * 角色 业务接口
 * 
 * @author Mignet
 * @since 2014年6月10日 下午4:15:01
 **/
public interface RoleService extends GenericService<Role, Integer> {
    /**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Integer userId);

	/**
	 * 查询角色列表,为data tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	boolean updateUserRoles(String id,String[] roles);

	List<Role> selectListAll();

}
