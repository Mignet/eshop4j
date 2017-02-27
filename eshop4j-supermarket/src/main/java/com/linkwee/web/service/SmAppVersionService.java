package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.model.SmAppVersion;
import com.linkwee.web.service.SmAppVersionService;
 /**
 * 
 * @描述： SmAppVersionService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月26日 20:22:43
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmAppVersionService extends GenericService<SmAppVersion,Long>{

	/**
	 * 查询SmAppVersion列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 根据平台类型与应用类别查询版本信息
	 * @param platForm
	 * @param appType
	 * @return
	 */
	public SmAppVersion getAppVersion(PlatformEnum platForm,Integer appType);
	
	/**
	 * 查询最新版本信息
	 * @param appType
	 * @return
	 */
	public List<SmAppVersion> queryNewAppVersion(Integer appType);

	/**
	 * app日志列表
	 * @param appType
	 * @param platForm
	 * @return
	 */
	List<SmAppVersion> queryAppLogList(Integer appType, PlatformEnum platForm);
}
