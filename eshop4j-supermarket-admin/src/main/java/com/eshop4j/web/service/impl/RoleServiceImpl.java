package com.eshop4j.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.dao.PermissionMapper;
import com.eshop4j.web.dao.RoleMapper;
import com.eshop4j.web.model.Role;
import com.eshop4j.web.model.User;
import com.eshop4j.web.service.RoleService;

/**
 * 角色Service实现类
 *
 * @author Mignet
 * @since 2014年6月10日 下午4:16:33
 */
@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Integer> implements RoleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
	
    @Resource
    private RoleMapper roleMapper;
    
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public GenericDao<Role, Integer> getDao() {
        return roleMapper;
    }

    @Override
    public List<Role> selectRolesByUserId(Integer userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

    @Override
	public List<Role> selectListAll() {
		return roleMapper.selectListAll();
	}

	@Override
	public boolean updateUserRoles(String id,String[] roles) {
		roleMapper.deleteUserRolesByUserid(Integer.parseInt(id));
		for(String roleid:roles){
			if(!StringUtils.isBlank(roleid)){
				roleMapper.insertUserRole(Integer.parseInt(id),Integer.parseInt(roleid));
			}
		}
		return true;
	}

	@Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" 排序和模糊查询 ");
		Page<Role> page = new Page<Role>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<Role> list = this.roleMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public int delete(Integer id) {
		permissionMapper.deleteRolePermissionsByRoleid(id);
		return roleMapper.deleteByPrimaryKey(id);
	}

}
