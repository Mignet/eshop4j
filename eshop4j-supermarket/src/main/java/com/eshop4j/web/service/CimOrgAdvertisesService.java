package com.eshop4j.web.service;

import java.util.List;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.CimOrgAdvertises;
import com.eshop4j.web.service.CimOrgAdvertisesService;
 /**
 * 
 * @描述： CimOrgAdvertisesService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月26日 18:23:35
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgAdvertisesService extends GenericService<CimOrgAdvertises,Long>{

	/**
	 * 查询CimOrgAdvertises列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 查询机构所有活动图片
	 * @author yalin 
	 * @date 2016年10月26日 下午6:36:38  
	 * @param orgNumber 机构编码
	 * @return
	 */
	public List<CimOrgAdvertises> queryOrgAdvertisesList(String orgNumber);
}
