package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.crm.CrmShareUserList;
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
