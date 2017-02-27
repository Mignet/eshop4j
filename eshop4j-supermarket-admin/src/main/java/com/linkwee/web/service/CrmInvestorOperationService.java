package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmInvestorOperation;
import com.linkwee.web.service.CrmInvestorOperationService;
 /**
 * 
 * @描述： CrmInvestorOperationService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月11日 18:17:22
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmInvestorOperationService extends GenericService<CrmInvestorOperation,Long>{

	/**
	 * 查询CrmInvestorOperation列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 归属理财师变更记录
	 * @param userId
	 * @return
	 */
	List<CrmInvestorOperation> queryChangeCfpRecordList(String userId);
}
