package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimProductSaleReward;
import com.linkwee.web.service.CimProductSaleRewardService;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月14日 18:23:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductSaleRewardService extends GenericService<CimProductSaleReward,Long>{

	/**
	 * 查询CimProductSaleReward列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
