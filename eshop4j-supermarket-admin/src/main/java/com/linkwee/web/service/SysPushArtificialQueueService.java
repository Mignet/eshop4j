package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.mc.SysNotice;
import com.linkwee.web.model.mc.SysPushArtificialQueue;
 /**
 * 
 * @描述： SysPushArtificialQueueService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月10日 15:50:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysPushArtificialQueueService extends GenericService<SysPushArtificialQueue,Long>{

	/**
	 * 查询SysPushArtificialQueue列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	public PaginatorResponse<SysPushArtificialQueue> querySysPushMessageList(Page<SysPushArtificialQueue> page, Map<String, Object> conditions);
	
	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	public Integer renewBatch(List<SysPushArtificialQueue> list);
	/**
	 * 发布公告，推送消息
	 * @param notice
	 * @param queue
	 */
	public void  releaseNotice(SysNotice notice,SysPushArtificialQueue queue);
}
