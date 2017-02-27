package com.eshop4j.web.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.eshop4j.api.response.tc.CfplannerProfitTotalResponse;
import com.eshop4j.api.response.tc.ProfitItemsResponse;
import com.eshop4j.api.response.tc.ProfitItemsTotalResponse;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.tc.fee.model.TCFeedetail;
import com.eshop4j.web.model.crm.PersonCenterResp;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月22日 15:50:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface TCFeedetailMapper extends GenericDao<TCFeedetail,Long>{
	
	
	/**
	 * 批量插入
	 * @param feedetails
	 * @return
	 */
	int inserts(@Param("feedetails") List<TCFeedetail> feedetails);
	
	/**
	 * 批量更新佣金结算状态
	 * @param cfplannerIds 已经汇总的理财师编号
	 * @param balanceMaps 理财师编号与汇总编号映射map
	 * @param balanceStatus	结算状态
	 * @param begintime	开始时间
	 * @param endTime	结束时间
	 * @return
	 */
	int batchUpdateBalanceStatus(@Param("cfplannerIds")List<String> cfplannerIds, @Param("balanceMaps")List<Map<String, String>> balanceMaps,@Param("balanceStatus")int balanceStatus,@Param("beginTime")String begintime,@Param("endTime")String endTime);
	
	
	/**
	 * 获取理财师本月总佣金收益
	 * @param userId 理财师编号
	 * @return
	 */
	PersonCenterResp queryCfplannerMonthProfitTotal(@Param("userId")String userId);
	
	/**
	 * 理财师佣金收益总额
	 * @param userId 理财师编号
	 * @param dateType 时间类别: 1:年；2:季度；3:月；4:日
	 * @param date 时间格式:2016-07-24
	 * @return
	 */
	CfplannerProfitTotalResponse queryCfplannerProfitTotal(@Param("userId")String userId,@Param("dateType") Integer dateType,@Param("date")Date date);
	
	
	/**
	 * 查询理财师所有佣金收益总额
	 * @param userId
	 * @param dateType
	 * @param date
	 * @return
	 */
	List<ProfitItemsTotalResponse> queryCfplannerProfitItemsTotal(@Param("userId")String userId,@Param("dateType") Integer dateType,@Param("date")Date date);
	
	/**
	 * 查询理财师单个佣金收益总额
	 * @param userId 理财师编号
	 * @param dateType 时间类别: 1:年；2:季度；3:月；4:日
	 * @param date 时间格式:2016-07-24
	 * @param profitType 收益类别
	 * @return
	 */
	BigDecimal queryCfplannerProfitItemTotal(@Param("userId")String userId,@Param("dateType") Integer dateType,@Param("date")Date date,@Param("profitType")Integer profitType);
	
	/**
	 * 查询理财师单个佣金收益明细
	 * @param userId 理财师编号
	 * @param dateType 时间类别: 1:年；2:季度；3:月；4:日
	 * @param date 时间格式:2016-07-24
	 * @param profitType 收益类别
	 * @param row
	 * @return
	 */
	List<ProfitItemsResponse> queryCfplannerProfitItem(@Param("userId")String userId,@Param("dateType") Integer dateType,@Param("date")Date date,@Param("profitType")Integer profitType,RowBounds row);

	/**
	 * 查一条佣金明细
	 * @param userId
	 * @param month
	 * @return
	 */
	TCFeedetail queryFeedetailByUserIdAndMonthLimitOne(@Param("userId")String userId, @Param("month")String month);

	/**
	 * 查佣金发放记录
	 * @param month
	 * @return
	 */
	int isGrantFeeByMonth(String month);
	
	
}
