package com.linkwee.web.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimOrgFeeTimetask;
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
	 * 查询用户当前购买时间段的佣金率 
	 * @author yalin 
	 * @date 2016年10月25日 上午10:45:15  
	 * @param orgNumber 机构编码
	 * @param buyDate 购买时间
	 * @return
	 */
	public BigDecimal queryOrgCurrentBuyDateFee(String orgNumber,Date buyDate);
}
