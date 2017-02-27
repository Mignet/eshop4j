package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CrmCfpUpgradeRecord;
import com.linkwee.web.service.CrmCfpUpgradeRecordService;
 /**
 * 
 * @描述： CrmCfpUpgradeRecordService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月11日 16:04:56
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmCfpUpgradeRecordService extends GenericService<CrmCfpUpgradeRecord,Long>{

	/**
	 * 查询CrmCfpUpgradeRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
}
