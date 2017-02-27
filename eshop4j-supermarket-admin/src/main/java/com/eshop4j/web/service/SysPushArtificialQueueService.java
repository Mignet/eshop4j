package com.eshop4j.web.service;

import java.util.List;
import java.util.Map;

import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.model.mc.SysNotice;
import com.eshop4j.web.model.mc.SysPushArtificialQueue;
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
