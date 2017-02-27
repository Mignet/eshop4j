package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActActivityPrizeCase;
import com.linkwee.web.service.ActActivityPrizeCaseService;
 /**
 * 
 * @描述： ActActivityPrizeCaseService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月04日 09:50:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActActivityPrizeCaseService extends GenericService<ActActivityPrizeCase,Long>{

	/**
	 * 查询ActActivityPrizeCase列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
