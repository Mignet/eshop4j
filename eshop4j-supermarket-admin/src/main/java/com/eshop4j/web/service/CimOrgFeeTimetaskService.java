package com.eshop4j.web.service;

import java.util.Date;
import java.util.List;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.CimOrgFeeTimetask;
import com.eshop4j.web.model.cim.CimOrginfoWeb;
import com.eshop4j.web.request.CimOrgFeeTimetaskRequest;
 /**
 * 
 * @描述： CimOrgFeeTimetaskService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月11日 17:22:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgFeeTimetaskService extends GenericService<CimOrgFeeTimetask,Long>{

	/**
	 * 查询CimOrgFeeTimetask列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 根据佣金的定时任务状态查询任务信息
	 * @author yalin 
	 * @date 2016年10月12日 上午10:30:42  
	 * @param taskStatus
	 * @return
	 */
	public List<CimOrgFeeTimetask> queryOrgFeeTimeTaskByStatus(int taskStatus,String orgNumber,Date currentTime);
	
	/**
	 * 插入机构佣金设置定时任务
	 * @author yalin 
	 * @date 2016年10月12日 上午10:56:38  
	 * @param orginfo
	 * @param request
	 * @return
	 */
	public void insertOrgFeeTimetask(CimOrginfoWeb orginfo,CimOrgFeeTimetaskRequest request);
	

}
