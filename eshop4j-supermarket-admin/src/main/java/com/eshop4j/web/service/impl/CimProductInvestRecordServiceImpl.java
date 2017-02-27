package com.eshop4j.web.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.dao.CimProductInvestRecordMapper;
import com.eshop4j.web.model.CimProduct;
import com.eshop4j.web.model.cim.CimProductInvestRecord;
import com.eshop4j.web.model.vo.InvestInfo;
import com.eshop4j.web.model.vo.InvestRecordWrapper;
import com.eshop4j.web.response.orgInfo.OrgInfoResponse;
import com.eshop4j.web.service.CimOrginfoService;
import com.eshop4j.web.service.CimProductInvestRecordService;
import com.eshop4j.web.service.CimProductService;
import com.eshop4j.web.service.CrmInvestorService;
import com.eshop4j.web.service.InvestRecordAware;
import com.eshop4j.web.service.SysApiInvokeLogService;
import com.eshop4j.xoss.util.RejectedExecuteRetry;


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
@Service("cimProductInvestRecordService")
public class CimProductInvestRecordServiceImpl extends GenericServiceImpl<CimProductInvestRecord, Long> implements CimProductInvestRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductInvestRecordServiceImpl.class);
	
	@Autowired
	private CimProductInvestRecordMapper investRecordMapper;
	
	@Autowired
	private CrmInvestorService investorService;
	
	@Autowired
	private CimProductService productService;
	
	@Autowired
	private List<InvestRecordAware> investRecordAwares;
	
	@Autowired
	private SysApiInvokeLogService sysApiInvokeLogService;
	
	@Autowired
	private CimOrginfoService orginfoService;
	
	@Override
    public GenericDao<CimProductInvestRecord, Long> getDao() {
        return investRecordMapper;
    }
	
	/**
	 * 查询被推荐已投资用户的userId
	 */
	@Override
	public List<CimProductInvestRecord> selectRefInvestRecord(List<String> refRegCustomers) {
		return investRecordMapper.selectRefInvestRecord(refRegCustomers);
	}

	
	@Override
	public void updateRepaymentStatus(String investRecordNo,Integer status,Date repaymentTime,BigDecimal repaymentAmount,BigDecimal accurateProfit) {
		investRecordMapper.updateRepaymentStatus(investRecordNo,status,repaymentTime,repaymentAmount, accurateProfit);
	}
	
	
	

	
	/**
	 * 根据产品编号查询在投投资记录 
	 * @param product 产品
	 * @return
	 */
	@Override
	public List<InvestRecordWrapper> getInvestRecordByProduct(CimProduct product){
		//根据产品编号查询投资记录
		List<CimProductInvestRecord> investRecords = investRecordMapper.getInvestRecordByProductIds(Sets.newHashSet(product.getProductId()));
		if(investRecords==null|| investRecords.isEmpty())return Lists.newArrayListWithCapacity(0);
		//投资记录包装类
		List<InvestRecordWrapper> investRecordWrappers = Lists.newArrayListWithCapacity(investRecords.size());
		for (CimProductInvestRecord investRecord : investRecords) {
			investRecordWrappers.add(createInvestRecordWrapper(product, investRecord));
		}
		return investRecordWrappers;
	}
	
	/**
	 * 根据产品编号查询在投投资记录 
	 * @param products 产品集合
	 * @return
	 */
	@Override
	public List<InvestRecordWrapper> getInvestRecordByProducts(List<CimProduct> products){
		//映射产品 方便遍历投资记录时根据产品编号获取具体产品信息
		Map<String, CimProduct> productMaps = Maps.newHashMap();
		for (CimProduct cimProduct : products) {
			productMaps.put(cimProduct.getProductId(), cimProduct);
		}
		
		//根据产品编号查询投资记录
		List<CimProductInvestRecord> investRecords = investRecordMapper.getInvestRecordByProductIds(productMaps.keySet());
		if(investRecords==null|| investRecords.isEmpty())return Lists.newArrayListWithCapacity(0);
		//格式化 当前日期 格式 yyyy-MM-dd
		SimpleDateFormat format = new SimpleDateFormat(DateUtils.FORMAT_SHORT);
		String curTime = format.format(DateUtils.getCurrentDate());
		//投资日期 与 解除日期 映射 以便减少解锁日期计算次数
		Map<String, String> timeMaps = Maps.newLinkedHashMap();
		
		//投资记录包装类
		List<InvestRecordWrapper> investRecordWrappers = Lists.newArrayListWithCapacity(investRecords.size());
		
		for (CimProductInvestRecord investRecord : investRecords) {
			//根据投资记录中的产品编号获取具体产品信息
			CimProduct p = productMaps.get(investRecord.getProductId());
			if(p==null) continue;
			//不需要募集 取投资时间,否则取募集成功时间
			Date investSuccessTime = ObjectUtils.equals(p.getIsCollect(), 1) ? investRecord.getStartTime() : p.getSaleEndTime();
			
			if(investSuccessTime==null) continue;
			
			//缓存key = 产品编号与日期
			String key = org.apache.commons.lang.StringUtils.join(new Object[]{investRecord.getProductId(),format.format(investSuccessTime)});
			//根据 key 获取  该笔投资的 的 解除日期 没有则计算 
			String lockTime = timeMaps.get(key);
			
			if(StringUtils.isBlank(lockTime)){
				
				//起息日  = 投资成功时间或者募集成功时间 + 1 
				lockTime =format.format(org.apache.commons.lang.time.DateUtils.addDays(investSuccessTime, p.getDeadLineMinValue()+1));
				timeMaps.put(key, lockTime);	
			}
			
			//当前日期大于解锁日期 创建投资记录包装类
			if(curTime.compareTo(lockTime)>0){
				investRecordWrappers.add(createInvestRecordWrapper(p, investRecord));
			}
			
		}
		return investRecordWrappers;
	}

	/**
	 * 创建投资记录包装类
	 * @param investRecordReq
	 * @param product
	 * @param investRecord
	 * @return
	 */
	private InvestRecordWrapper createInvestRecordWrapper(CimProduct product,CimProductInvestRecord investRecord) {
		InvestRecordWrapper investRecordWrapper = new InvestRecordWrapper();
		investRecordWrapper.setBizId(investRecord.getInvestId());
		investRecordWrapper.setInvestId(investRecord.getInvestRecordNo());
		investRecordWrapper.setUserId(investRecord.getUserId());
		investRecordWrapper.setProductId(product.getProductId());
		investRecordWrapper.setProductType(product.getIsCollect());
		investRecordWrapper.setProductName(product.getProductName());
		//如果是平台老用户不计算佣金
		boolean isOrgNewUser = orginfoService.isOrgNewUser(investRecord.getUserId(), investRecord.getPlatfrom());
		investRecordWrapper.setFeeRatio(isOrgNewUser ?  investRecord.getProductFeeRate():BigDecimal.ZERO );
		//平台老用户
		if(!isOrgNewUser){
			OrgInfoResponse orgInfoResponse =  orginfoService.findOrgInfo(investRecord.getPlatfrom());
			if(orgInfoResponse!=null)
				investRecordWrapper.setRemark(isOrgNewUser?null:orgInfoResponse.getOrgName() + "平台老用户,不计算佣金".intern());
		}
		investRecordWrapper.setIsPlatfromNewUser(isOrgNewUser);
		investRecordWrapper.setProductOrgId(investRecord.getPlatfrom());
		investRecordWrapper.setProductDays(product.getDeadLineMinValue());
		investRecordWrapper.setIsRedemption(product.getIsRedemption());
		investRecordWrapper.setDeadLineMaxValue(product.getDeadLineMaxValue());
		investRecordWrapper.setInvestAmt(investRecord.getInvestAmt());
		investRecordWrapper.setInvestTime(investRecord.getStartTime());
		investRecordWrapper.setFirstInvest(ObjectUtils.equals(investRecord.getIsFirstInvest(), 1));
		investRecordWrapper.setPlatfromFirstInvest(ObjectUtils.equals(investRecord.getIsPlatfromFirstInvest(), 1));
		return investRecordWrapper;
	}
	

	@Transactional(propagation=Propagation.REQUIRED)
	@RejectedExecuteRetry
	@Override
	public boolean updateInvestRecordEndTimeByProductId(CimProductInvestRecord investRecord)throws Exception {
		return investRecordMapper.updateInvestRecordEndTimeByProductId(investRecord)>0;
	}

	
	@Override
	public Map<String, String> getInvestRecordCounts(String userId) {
		return investRecordMapper.getInvestRecordCounts(userId);
	}

	@Override
	public List<InvestInfo> getInvestInfoByDate(String date) {
		return investRecordMapper.getInvestInfoByDate(date);
	}

	@Override
	public List<InvestInfo> getInvestInfoByNow(String now) {
		
		return investRecordMapper.getInvestInfoByNow(now);
	}

	@Override
	public int queryUserPlatfromInvestCount(String userId, String platfromId) {
		return investRecordMapper.queryUserPlatfromInvestCount(userId, platfromId);
	}

	@Override
	public List<String> queryUserInvestCountByPlatFormModel(
			String userId, Integer model) {
		return investRecordMapper.queryUserInvestCountByPlatFormModel(userId, model);
	}

	
}
