package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.CrmInvestorLoginLog;
import com.eshop4j.web.service.CrmInvestorLoginLogService;
 /**
 * 
 * @描述： CrmInvestorLoginLogService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月17日 19:11:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmInvestorLoginLogService extends GenericService<CrmInvestorLoginLog,Long>{

	/**
	 * 查询CrmInvestorLoginLog列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
