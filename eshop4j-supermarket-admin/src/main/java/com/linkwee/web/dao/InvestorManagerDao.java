package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.web.model.FreindsResp;
import com.linkwee.web.model.crm.InvestedUserDataStatisticResp;
import com.linkwee.web.model.crm.InvestorManagerDetailResp;
import com.linkwee.web.model.crm.UserDataStatisticResp;
import com.linkwee.web.request.LcsListRequest;
import com.linkwee.web.request.ListDetailRequest;
import com.xiaoniu.mybatis.paginator.domain.PageList;

/**
 * 
 * @描述： 理财师相关查询
 * 
 * @创建人：ch
 * 
 * @创建时间：2016年04月13日 17:27:15
 * 
 *  Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
	
public interface InvestorManagerDao  {
	
	
	/**
	 * 查询理财师列表
	 * @param request
	 * @return
	 */
	public PageList<InvestorManagerDetailResp> queryInvestorList(@Param("query")LcsListRequest pageRequest,RowBounds page);
	
	/**
	 * 查询理财师详情
	 * @param mobile
	 * @return
	 */
	public InvestorManagerDetailResp queryInvestorDetail(@Param("mobile")String mobile);

	/**
	 * 获取邀请的好友列表数据
	 * @param dataTable
	 * @param investRecordRequest
	 * @return
	 */
	public List<FreindsResp> selectFreindsList(@Param("query")ListDetailRequest req, RowBounds page);

	/**
	 * 数据概览统计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String,Object>> queryInvestorNumByTime(@Param("startDate") String startDate,@Param("endDate")String endDate);
	public List<Map<String,Object>> queryInvestMoneyByTime(@Param("startDate")String startDate,@Param("endDate") String endDate);
	public Map<String,Object> personAmoutStat();
	public Map<String,Object> investMoneyStat();
	
	/**
	 * 总投资人数和投资额
	 * @return
	 */
	public Map<String,Object> investorTotal();
	public Map<String,Object> investMoneyTotal();

	/**
	 * 用户注册数据统计
	 * @return
	 */
	public UserDataStatisticResp queryUserRegisterTotalData();

	/**
	 * 用户投资数据统计
	 * @return
	 */
	public InvestedUserDataStatisticResp queryUserInvestTotalData();
    
}
