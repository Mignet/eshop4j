package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.SysGrayRelease;
import com.eshop4j.web.service.SysGrayReleaseService;
 /**
 * 
 * @描述： SysGrayReleaseService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2016年11月10日 10:29:41
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysGrayReleaseService extends GenericService<SysGrayRelease,Long>{

	/**
	 * 查询SysGrayRelease列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 查询用户是否拥有灰度权限
	 * @param userId 用户id
	 * @param grayType  权限类型 0-公共 1-产品 2-机构  多个请使用","分割  如 0,1
	 * @return
	 */
	Boolean ifHaveGrayPermission(String userId,String grayType);
	
	/**
	 * 根据token查询用户是否拥有灰度权限
	 * @param userId 用户id
	 * @param grayType  权限类型 0-公共 1-产品 2-机构  多个请使用","分割  如 0,1
	 * @return
	 */
	Boolean ifHaveGrayPermissionByToken(String token,String grayType);
}
