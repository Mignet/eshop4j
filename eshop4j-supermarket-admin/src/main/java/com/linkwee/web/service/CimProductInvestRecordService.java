package com.linkwee.web.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.model.cim.CimProductInvestRecord;
import com.linkwee.web.model.vo.InvestInfo;
import com.linkwee.web.model.vo.InvestRecordWrapper;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： hxb
 * 
 * @创建时间：2016年07月14日 18:04:53
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductInvestRecordService extends GenericService<CimProductInvestRecord,Long>{

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
	void updateRepaymentStatus(String investRecordNo,Integer status,Date repaymentTime,BigDecimal repaymentAmount,BigDecimal accurateProfit);
	/**
	 * 根据产品查询在投投资记录 
	 * @param product 产品集合
	 * @return
	 */
	List<InvestRecordWrapper> getInvestRecordByProduct(CimProduct product);
	
	/**
	 * 根据产品查询在投投资记录 
	 * @param products 产品集合
	 * @return
	 */
	List<InvestRecordWrapper> getInvestRecordByProducts(List<CimProduct> productIds);
	
	/**
	 * 更新募集期 投资记录到期日期
	 * @param investRecord
	 * @return
	 */
	boolean updateInvestRecordEndTimeByProductId(CimProductInvestRecord investRecord)throws Exception;
	
	/**
	 * 获取投资记录数量
	 * @param userId
	 * @return
	 */
	Map<String, String> getInvestRecordCounts(String userId);
	
	List<InvestInfo> getInvestInfoByDate(String date);
	
	List<InvestInfo> getInvestInfoByNow(String today);
	
	int queryUserPlatfromInvestCount(String userId,String platfromId);
	
	
	List<String> queryUserInvestCountByPlatFormModel(String userId,Integer model);
}
