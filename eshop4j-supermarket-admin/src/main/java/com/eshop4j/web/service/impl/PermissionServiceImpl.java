package com.eshop4j.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.web.dao.PermissionMapper;
import com.eshop4j.web.model.Permission;
import com.eshop4j.web.service.PermissionService;

/**
 * 权限Service实现类
 *
 * @author Mignet
 * @since 2014年6月10日 下午12:05:03
 */
@Service
public class PermissionServiceImpl extends GenericServiceImpl<Permission, Integer> implements PermissionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionServiceImpl.class);
	
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public GenericDao<Permission, Integer> getDao() {
        return permissionMapper;
    }

    @Override
    public List<Permission> selectPermissionsByRoleId(Integer roleId) {
        return permissionMapper.selectPermissionsByRoleId(roleId);
    }

	@Override
	public List<Permission> selectList(RowBounds page) {
		LOGGER.debug(" 测试自动分页:%d,%d",page.getLimit(),page.getOffset());
		return permissionMapper.selectList(page);
	}

	@Override
	public List<Permission> selectByName(String permissionName) {
		return permissionMapper.selectByName(permissionName);
	}
	
    @Override
	public List<Permission> selectListAll() {
		return permissionMapper.selectListAll();
	}

	@Override
	public boolean updateRolePermissions(String roleId,String[] permissions) {
		permissionMapper.deleteRolePermissionsByRoleid(Integer.parseInt(roleId));
		for(String permissionId:permissions){
			if(!StringUtils.isBlank(permissionId)){
				permissionMapper.insertRolePermission(Integer.parseInt(roleId),Integer.parseInt(permissionId));
			}
		}
		return true;
	}
	
	@Override
	public int delete(Integer id) {
		//删除角色与权限表中该权限
		permissionMapper.deleteRolePermissionsByPermissionId(id);
		return permissionMapper.deleteByPrimaryKey(id);
	}
}
