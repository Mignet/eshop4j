package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.mc.SysPushMessage;
 /**	
 * 
 * @描述： SysPushMessageService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月25日 16:17:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysPushMessageService extends GenericService<SysPushMessage,Long>{

	/**
	 * 查询SysPushMessage列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	/**
	 * 分页查询
	 * @param page
	 * @param conditions
	 * @return
	 */
	public PaginatorResponse<SysPushMessage> querySysPushMessageList(Page<SysPushMessage> page,Map<String,Object> conditions);
	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	public Integer renewBatch(List<SysPushMessage> list);
	/**
	 * 批量保存
	 * @param sysPushMessage
	 * @return
	 */
	public Integer saveBatch(List<SysPushMessage> list);
}
