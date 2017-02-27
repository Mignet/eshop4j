package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.CrmOrgAcctRel;
import com.eshop4j.web.service.CrmOrgAcctRelService;
 /**
 * 
 * @描述： CrmOrgAcctRelService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2016年10月12日 14:52:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmOrgAcctRelService extends GenericService<CrmOrgAcctRel,Long>{

	/**
	 * 查询CrmOrgAcctRel列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
