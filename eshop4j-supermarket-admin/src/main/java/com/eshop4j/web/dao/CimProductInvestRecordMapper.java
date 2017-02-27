package com.eshop4j.web.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.web.model.cim.CimProductInvestRecord;
import com.eshop4j.web.model.vo.InvestInfo;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年07月20日 18:15:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductInvestRecordMapper extends GenericDao<CimProductInvestRecord,Long>{
	
	/**
	 * 查询被推荐已投资用户的userId
	 * @param refRegCustomers
	 * @return
	 */
	List<CimProductInvestRecord> selectRefInvestRecord(List<String> refRegCustomers);
	
	
	/**
	 * 更新回款状态
	 * @param investRecordNo 投资交易流水号
	 * @param status 回款状态 2=回款中 3=回款成功
	 * @param repaymentTime 回款时间
	 * @param accurateProfit 精准收益
	 */
	void updateRepaymentStatus(@Param("investRecordNo") String investRecordNo,@Param("status")Integer status,@Param("repaymentTime")Date repaymentTime,@Param("repaymentAmount")BigDecimal repaymentAmount,@Param("accurateProfit")BigDecimal accurateProfit);
	
	
	/**
	 * 获取客户投资总额
	 * @param userId 客户编号
	 * @return
	 */
	BigDecimal queryCustomerInvestTotalAmount(@Param("userId")String userId);
	
	/**
	 * 获取客户投资收益
	 * @param userId 客户编号
	 * @return
	 */
	BigDecimal queryCustomerInvestTotalProfit(@Param("userId")String userId);
	
	/**
	 * 查询用户首次投资时间
	 * @param userId
	 * @return
	 */
	int queryFirsInvestTime(@Param("userId") String userId,@Param("orgNumber")String orgNumber);
	
	/**
	 * 申购动态消息数量
	 * @param time
	 * @param userId
	 * @return
	 */
	int queryCustomerInvestTradeMsgCount(@Param("readTime") Date time,@Param("userId")String userId);
	
	
	/**
	 * 赎回动态消息数量
	 * @param time
	 * @param userId
	 * @return
	 */
	int queryCustomerRepaymentTradeMsgCount(@Param("readTime") Date time,@Param("userId")String userId);

	/**
	 * 当前在投总额
	 * @param userId
	 * @return
	 */
	BigDecimal queryCurrInvestAmount(String userId);
	/**
	 * 理财师最新动态汇总 未读count
	 * @param userId
	 * @return
	 */
	int queryCfpNewlyDynamicUnReadCount(@Param("userId")String userId,@Param("date")String date);
	
	/**
	 * 根据产品编号查询在投投资记录 
	 * @param productIds 产品编号集合
	 * @return
	 */
	List<CimProductInvestRecord> getInvestRecordByProductIds(@Param("productIds")Set<String> productIds);
	/**
	 * 根据机构编码查询机构当月的销售总额
	 */
	BigDecimal queryMonthInvestAmount(@Param("orgNumber")String orgNumber);
	
	/**
	 * 查询用户投资次数
	 * @param userId
	 * @return
	 */
	int queryUserInvestCount(@Param("userId")String userId);
	
	int queryUserPlatfromInvestCount(@Param("userId")String userId,@Param("platfromId")String platfromId);
	
	/**
	 * 更新募集期 投资记录到期日期
	 * @param investRecord
	 * @return
	 */
	int updateInvestRecordEndTimeByProductId(CimProductInvestRecord investRecord);
	
	/**
	 * 获取用户投资记录数量
	 * @param userId
	 * @return
	 */
	Map<String, String> getInvestRecordCounts(@Param("userId")String userId);
	
	
	List<String> queryUserInvestCountByPlatFormModel(@Param("userId")String userId,@Param("model")Integer model);
	
	
	List<InvestInfo> getInvestInfoByDate(@Param("date")String date);
	
	List<InvestInfo> getInvestInfoByNow(@Param("today")String today);
	
}
