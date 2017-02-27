package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActActivityPrizeStrategy;
import com.linkwee.web.service.ActActivityPrizeStrategyService;
 /**
 * 
 * @描述： ActActivityPrizeStrategyService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月04日 11:29:37
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActActivityPrizeStrategyService extends GenericService<ActActivityPrizeStrategy,Long>{

	/**
	 * 查询ActActivityPrizeStrategy列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
