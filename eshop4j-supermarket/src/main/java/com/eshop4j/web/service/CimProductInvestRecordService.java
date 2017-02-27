package com.eshop4j.web.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.eshop4j.api.request.tc.CfplannerCustomerInvestRecordRequest;
import com.eshop4j.api.response.tc.CustomerInvestRecordResponse;
import com.eshop4j.api.response.tc.CustomerInvestRecordStatisticResponse;
import com.eshop4j.api.response.tc.CustomerTradeMsgResponse;
import com.eshop4j.api.response.tc.InvestRecordResponse;
import com.eshop4j.api.response.tc.RepaymentResponse;
import com.eshop4j.api.response.tc.TradeNewlyDynamicResponse;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.openapi.request.InvestRecordReq;
import com.eshop4j.web.model.CimProduct;
import com.eshop4j.web.model.cim.CimProductInvestRecord;
import com.eshop4j.web.model.vo.InvestRecordWrapper;
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
	 * 插入投资记录
	 * @param investRecordReq 投资记录信息
	 */
	void insertInvestRecordProcess(InvestRecordReq investRecordReq) throws Exception;
	
	
	/**
	 * 更新回款状态
	 * @param investRecordNo 投资交易流水号
	 * @param status 回款状态 2=回款中 3=回款成功
	 * @param repaymentTime 回款时间
	 * @param accurateProfit 精准收益
	 */
	void updateRepaymentStatus(String investRecordNo,Integer status,Date repaymentTime,BigDecimal repaymentAmount,BigDecimal accurateProfit);
	
	/**
	 * 获取客户历史累计投资总额
	 * @param userId 客户编号
	 * @return 投资总额
	 */
	Double queryCustomerInvestTotalAmount(String userId);
	/**
	 * 当前在投总额
	 * @param userId
	 * @return
	 */
	Double queryCurrInvestAmount(String userId);
	
	/**
	 * 获取客户投资收益总额
	 * @param userId 客户编号
	 * @return 投资收益总额
	 */
	Double queryCustomerInvestTotalProfit(String userId);
	
	/**
	 * 查询客户投资记录 
	 * @param userId 客户编号
	 * @param status 投资状态
	 * @return
	 */
	PaginatorResponse<InvestRecordResponse> queryCustomerInvestRecord(String userId,Integer status,Page<InvestRecordResponse> page);
	
	
	/**
	 * 查询理财师用户投资总计
	 * @param customerInvestRecordRequest
	 * @return
	 */
	CustomerInvestRecordStatisticResponse queryCfplannerCustomerInvestRecordStatistic(CfplannerCustomerInvestRecordRequest customerInvestRecordRequest);
	
	/**
	 * 查询理财师用户投资信息
	 * @param customerInvestRecordRequest
	 * @return
	 */
	PaginatorResponse<CustomerInvestRecordResponse> queryCfplannerCustomerInvestRecord(CfplannerCustomerInvestRecordRequest customerInvestRecordRequest);
	
	/**
	 * 查询理财师用户投资信息
	 * @param customerInvestRecordRequest
	 * @return
	 */
	PaginatorResponse<CustomerInvestRecordResponse> queryCfplannerInvestCustomerDetail(CfplannerCustomerInvestRecordRequest customerInvestRecordRequest);
	
	/**
	 * 查询理财师客户即将回款列表
	 * @param userId
	 * @return
	 */
	PaginatorResponse<RepaymentResponse> queryCustomerRepayment(String userId,String customerId,Page<RepaymentResponse> page);
	
	/**
	 * 查询用户所有交易动态
	 * @param customerId
	 * @param userId
	 * @param page
	 * @return
	 */
	PaginatorResponse<CustomerTradeMsgResponse> queryCustomerTradeMsg(String customerId,String userId,Page<CustomerTradeMsgResponse> page);
	
	/**
	 * 申购动态消息数量
	 * @param userId
	 * @return
	 */
	int queryCustomerInvestTradeMsgCount(String userId);
	
	/**
	 * 客户申购交易消息
	 * @param userId
	 * @return
	 */
	PaginatorResponse<CustomerTradeMsgResponse> queryCustomerInvestTradeMsg(String userId,String customerId,Page<CustomerTradeMsgResponse> page);
	
	/**
	 * 赎回动态消息数量
	 * @param userId
	 * @return
	 */
	int queryCustomerRepaymentTradeMsgCount(String userId);
	/**
	 * 客户赎回交易消息
	 * @param userId
	 * @return
	 */
	PaginatorResponse<CustomerTradeMsgResponse> queryCustomerRepaymentTradeMsg(String userId,String customerId,Page<CustomerTradeMsgResponse> page);
	
	/**
	 * 理财师动态汇总
	 * @param page
	 * @param conditions
	 * @return
	 */
	PaginatorResponse<TradeNewlyDynamicResponse> queryCfpNewlyDynamic(Page<TradeNewlyDynamicResponse> page,String userId);
	
	/**
	 * 理财师动态汇总
	 * @param page
	 * @param conditions
	 * @return
	 */
	int queryCfpNewlyDynamicUnReadCount(String userId);
	
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
	
	/**
	 * 用户在平台的投资记录数
	 * @author yalin 
	 * @date 2016年12月21日 上午10:54:01  
	 * @param userId
	 * @param platfromId
	 * @return
	 */
	int queryUserPlatfromInvestCount(String userId,String platfromId);
}
