package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.response.tc.CfplannerUnrecordInvestResponse;
import com.linkwee.api.response.tc.CustomerUnrecordInvestResponse;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.cim.CimProductUnrecordInvest;
import com.linkwee.web.response.tc.UnrecordInvestListResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月09日 14:27:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductUnrecordInvestMapper extends GenericDao<CimProductUnrecordInvest,Long>{
	
	/**
	 * 获取客户审核通过报单记录
	 * @param userId
	 * @param page
	 * @return
	 */
	List<CustomerUnrecordInvestResponse> getCustomerUnrecordInvest(@Param("userId")String userId,RowBounds page);
	
	
	
	int getCfplannerUnrecordInvestCount(@Param("userId")String userId);
	
	/**
	 * 获取理财师报单
	 * @param userId
	 * @param page
	 * @return
	 */
	List<CfplannerUnrecordInvestResponse> getCfplannerUnrecordInvest(@Param("userId")String userId,RowBounds page);
	
	List<UnrecordInvestListResponse> getUnrecordInvestList(@Param("mobile")String mobile,@Param("status")Integer status,RowBounds page);
	
}
