package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgDynamic;
import com.linkwee.web.service.CimOrgDynamicService;
 /**
 * 
 * @描述： CimOrgDynamicService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月02日 14:59:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgDynamicService extends GenericService<CimOrgDynamic,Long>{

	/**
	 * 查询CimOrgDynamic列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 平台动态分页查询 显示最近10条动态
	 * @author yalin 
	 * @date 2016年11月2日 下午4:01:07  
	 * @return
	 */
	List<CimOrgDynamic> queryCimOrgDynamicList(String orgNumber);
	
	/**
	 * 平台动态详情
	 * @author yalin 
	 * @date 2016年11月2日 下午4:06:13  
	 * @param orgDynamicId
	 * @return
	 */
	CimOrgDynamic queryOrgDynamicInfo(int orgDynamicId);
}
