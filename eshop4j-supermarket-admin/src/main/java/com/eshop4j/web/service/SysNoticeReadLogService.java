package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.mc.SysNoticeReadLog;
 /**
 * 
 * @描述： SysNoticeReadLogService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月04日 17:09:02
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysNoticeReadLogService extends GenericService<SysNoticeReadLog,Long>{

	/**
	 * 查询SysNoticeReadLog列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	
	public void updateNoticeReadLog(String apiName,String userId,Integer appType,Integer noticeId);
	
	public SysNoticeReadLog queryNoticeReadLog(String apiName,String userId,Integer appType,Integer noticeId);
	/**
	 * 通知全部设置为已读
	 * @param userId
	 * @param appType
	 */
	public void setNoticeReaded(String userId,Integer appType) throws Exception;
	
	
}
