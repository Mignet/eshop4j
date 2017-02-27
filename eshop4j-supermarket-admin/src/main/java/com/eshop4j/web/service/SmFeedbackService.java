package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.SmFeedback;
import com.eshop4j.web.service.SmFeedbackService;
 /**
 * 
 * @描述： SmFeedbackService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月28日 10:43:04
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmFeedbackService extends GenericService<SmFeedback,Long>{

	/**
	 * 查询SmFeedback列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 查询SmFeedback替换UserId为mobile后的列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatablesRepUidByMob(DataTable dataTable);
	
}
