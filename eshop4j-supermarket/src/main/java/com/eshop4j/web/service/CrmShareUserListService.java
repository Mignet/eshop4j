package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.crm.CrmShareUserList;
 /**
 * 
 * @描述： CrmShareUserListService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年01月03日 16:52:39
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmShareUserListService extends GenericService<CrmShareUserList,Long>{

	/**
	 * 查询CrmShareUserList列表,为data-tables封装	
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
