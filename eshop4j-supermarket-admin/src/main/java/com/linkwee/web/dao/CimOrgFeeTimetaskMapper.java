package com.linkwee.web.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimOrgFeeTimetask;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月11日 17:22:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimOrgFeeTimetaskMapper extends GenericDao<CimOrgFeeTimetask,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimOrgFeeTimetask> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
	 * 根据佣金的定时任务状态查询任务信息
	 * @author yalin 
	 * @date 2016年10月12日 上午10:30:42  
	 * @param taskStatus
	 * @return
	 */
	public List<CimOrgFeeTimetask> queryOrgFeeTimeTaskByStatus(@Param("taskStatus")int taskStatus,@Param("orgNumber")String orgNumber,@Param("currentTime")Date currentTime);
	
	/**
	 * 查询用户当前购买时间段的佣金率 
	 * @author yalin 
	 * @date 2016年10月25日 上午10:45:15  
	 * @param orgNumber 机构编码
	 * @param buyDate 购买时间
	 * @return
	 */
	public CimOrgFeeTimetask queryOrgCurrentBuyDateFee(@Param("orgNumber")String orgNumber,@Param("buyDate")Date buyDate);
}
