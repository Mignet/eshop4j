package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.mc.SysNotice;
	

 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2015年10月26日 20:05:52
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface SysNoticeService extends GenericService<SysNotice,Long>{

	/**
	 * 查询Msg列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable,Integer appType);

	
}
