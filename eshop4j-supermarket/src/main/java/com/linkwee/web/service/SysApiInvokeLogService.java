package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SysApiInvokeLog;
import com.linkwee.web.service.SysApiInvokeLogService;
 /**
 * 
 * @描述： SysApiInvokeLogService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月21日 09:32:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysApiInvokeLogService extends GenericService<SysApiInvokeLog,Long>{

	/**
	 * 查询SysApiInvokeLog列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	public void updateApiInvokeLog(String apiName,String userId,Integer appType);
	
	public SysApiInvokeLog queryApiInvokeLog(String apiName,String userId,Integer appType);
	
	/**
	 * 调用api中具体数据信息
	 * @param apiName
	 * @param contentId
	 * @param userId
	 * @param appType
	 */
	public void updateApiInvokeLog(String apiName,String contentId,String userId,Integer appType);
}
