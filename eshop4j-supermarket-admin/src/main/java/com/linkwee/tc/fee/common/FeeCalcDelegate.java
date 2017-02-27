package com.linkwee.tc.fee.common;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.linkwee.tc.fee.common.config.FeeConfig;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.tc.fee.service.TCFeeDetailService;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.xoss.util.RejectedExecuteRetry;
/**
 * 佣金计算委托
 * @author ch
 *
 */
@Component
public class FeeCalcDelegate {
	
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(FeeCalcDelegate.class);
	
	
	@Autowired
	protected FeeConfig feeConfig;
	
	@Autowired
	protected TCFeeDetailService feeDetailService;
	
	@Autowired
	protected CrmCfplannerService cfplannerService;
	
	@Autowired
	protected CrmInvestorService investorService;
	
	@Autowired
	private CimOrginfoService orginfoService;
	
	@RejectedExecuteRetry
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void everyDayFeeCalc(InvestRecordWrapper investRecord) throws Exception{
		feeCalc(investRecord);
	}
	
	
	
	public void feeCalc(InvestRecordWrapper investRecord) throws Exception{
		//查询用户
		CrmInvestor investor = investorService.queryInvestorByUserId(investRecord.getUserId());
		
		if(StringUtils.isBlank(investor.getCfplanner())){
			LOGGER.warn("calculate Fee Cfplanner do not exist investor id ={}", investRecord.getUserId());
			return;
		}
		//查询理财师
		CrmCfplanner cfplanner =  cfplannerService.queryCfplannerByUserId(investor.getCfplanner());
		
		if(cfplanner==null){
			LOGGER.warn("calculate Fee Cfplanner do not exist investor id ={}", investRecord.getUserId());
			return;
		}
		
		FeedetailWrapper feedetailWrapper = new FeedetailWrapper();
		setBaseInfo(feedetailWrapper,investRecord);
		setRatio(feedetailWrapper, investRecord,cfplanner,investor);
		internalFeeCalc(feedetailWrapper, investRecord);
	}
	
	
	/**
	 * 设置佣金明细基础信息
	 * @param feedetailWrapper
	 * @param investor
	 * @param cfplanner
	 * @param investRecord
	 */
	private void setBaseInfo(FeedetailWrapper feedetailWrapper,InvestRecordWrapper investRecord){		
		feedetailWrapper.setBillId(investRecord.getBizId());
		feedetailWrapper.setInvestorId(investRecord.getUserId());
		feedetailWrapper.setProductId(investRecord.getProductId());
		feedetailWrapper.setProductName(investRecord.getProductName());
		feedetailWrapper.setProductOrgId(investRecord.getProductOrgId());
		feedetailWrapper.setInvestmentAmount(investRecord.getInvestAmt());
		feedetailWrapper.setInvestDate(investRecord.getInvestTime());
	}
	
	/**
	 * 设置祖先路径
	 * @param feedetailWrapper 明细包装类
	 * @param cfplannerId 理财师编号
	 */
	private CrmCfplanner getCfplanner(FeedetailWrapper feedetailWrapper,String cfplannerId){
		if(StringUtils.isBlank(cfplannerId))return null;
		return cfplannerService.queryCfplannerByUserId(cfplannerId);
	}
	
	/**
	 * 设置佣金率
	 * @param feedetailWrapper 明细包装类
	 * @param investRecord 投资记录包装类
	 */
	private void setRatio(FeedetailWrapper feedetailWrapper,InvestRecordWrapper investRecord,CrmCfplanner cfplanner,CrmInvestor investor){
		String remark = investRecord.getRemark();
		String name = StringUtils.join(new Object[]{investor.getUserName(),investor.getMobile().substring(investor.getMobile().length()-4)},'*');
		String productName= investRecord.getProductName();
		BigDecimal amt = investRecord.getInvestAmt();
		String cfplannerId = cfplanner.getUserId();
		feedetailWrapper.setCfplannerId(cfplannerId);
		feedetailWrapper.setRatio(investRecord.getFeeRatio().doubleValue());
		
		feedetailWrapper.setCfplannerRemak(remark==null?String.format("客户%s购买 %s，金额%s元",name,productName,amt):remark);
		
		name =  StringUtils.join(new Object[]{cfplanner.getUserName(),cfplanner.getMobile().substring(cfplanner.getMobile().length()-4)},'*');;
		//上级理财师
		cfplannerId =cfplanner.getParentId();
		CrmCfplanner pCfplanner = getCfplanner(feedetailWrapper,cfplannerId);
		if(pCfplanner == null ) return;
		feedetailWrapper.setParentCfplannerId(cfplannerId);
		feedetailWrapper.setParentRatio(feeConfig.getSecond_ratio());
		feedetailWrapper.setParentCfplannerRemak(remark==null?String.format("团队成员%s销售 %s，金额%s元", name,productName,amt):remark);
		name =  StringUtils.join(new Object[]{pCfplanner.getUserName(),pCfplanner.getMobile().substring(pCfplanner.getMobile().length()-4)},'*');
		
		//上上级理财师
		cfplannerId = pCfplanner.getParentId();
		pCfplanner = getCfplanner(feedetailWrapper,cfplannerId);
		if(pCfplanner == null ) return;
		feedetailWrapper.setGrandParentCfplannerId(cfplannerId);
		feedetailWrapper.setGrandparentratio(feeConfig.getThird_ratio());
		feedetailWrapper.setGrandParentCfplannerRemak(remark==null?String.format("团队成员%s的下级销售 %s，金额%s元", name,productName,amt):remark);
	}

	private void internalFeeCalc(FeedetailWrapper feedetailWrapper,InvestRecordWrapper investRecord) throws Exception {
		
		try {
			List<FeedetailWrapper> wrappers = Lists.newArrayListWithCapacity(3);
			//用户理财师佣金
			FeedetailWrapper wrapper = new FeedetailWrapper();
			feedetailWrapper.setYearPurAmount(CalculateTools.yearpurAmountCompute(investRecord.getInvestAmt(), investRecord.getProductDays()));
			BeanUtils.copyProperties(wrapper, feedetailWrapper);
			BigDecimal feeAmount = CalculateTools.feeAmountCompute(feedetailWrapper.getYearPurAmount(),feedetailWrapper.getRatio());
			wrapper.setFeeamount(feeAmount);
			wrapper.setCurCfplannerId(feedetailWrapper.getCfplannerId());
			wrapper.setCurRatio(feedetailWrapper.getRatio());
			wrapper.setRemak(feedetailWrapper.getCfplannerRemak());
			
			wrapper.setFeetype("1001");
			wrappers.add(wrapper);
			
			
			if(StringUtils.isNotBlank( feedetailWrapper.getParentCfplannerId())){
				//一级理财师佣金
				wrapper = new FeedetailWrapper();
				BeanUtils.copyProperties(wrapper, feedetailWrapper);
				
				if(!ObjectUtils.equals(investRecord.getFeeRatio(), BigDecimal.ZERO)){
					Double differential = orginfoService.queryOrgDiffFeeRatio(investRecord.getProductOrgId()).doubleValue();
					feeAmount = CalculateTools.feeAmountCompute(feedetailWrapper.getYearPurAmount(),differential);
				}
				
				
				
				wrapper.setFeeamount(CalculateTools.feeAmountCompute(feeAmount, feedetailWrapper.getParentRatio()));
				wrapper.setCurCfplannerId(feedetailWrapper.getParentCfplannerId());
				wrapper.setCurRatio(feedetailWrapper.getParentRatio());
				wrapper.setFeetype("1002");
				wrapper.setRemak(feedetailWrapper.getParentCfplannerRemak());
				wrappers.add(wrapper);
			}
			if(StringUtils.isNotBlank( feedetailWrapper.getGrandParentCfplannerId())){
				//二级理财师佣金
				wrapper = new FeedetailWrapper();
				BeanUtils.copyProperties(wrapper, feedetailWrapper);
				wrapper.setFeeamount(CalculateTools.feeAmountCompute(feeAmount, feedetailWrapper.getGrandparentratio()));
				wrapper.setCurCfplannerId(feedetailWrapper.getGrandParentCfplannerId());
				wrapper.setCurRatio(feedetailWrapper.getGrandparentratio());
				wrapper.setFeetype("1002");
				wrapper.setRemak(feedetailWrapper.getGrandParentCfplannerRemak());
				wrappers.add(wrapper);
			}
			savaFeedetails(wrappers.toArray(new FeedetailWrapper[0]));
			
		} catch (Exception e) {
			LOGGER.warn("internalExecuteCalcu exception feedetailWrapper={},investRecord={},e={}", new Object[]{feedetailWrapper,investRecord,e});
			throw e;
		} 
	}
	
	
	/***
	 * 保存佣金明细
	 * @param feedetailWrappers
	 */
	private void savaFeedetails(FeedetailWrapper... feedetailWrappers){
		
		try {
			feeDetailService.insertFeedetail(feedetailWrappers);
		} catch (Exception e) {
			LOGGER.warn("SavaFeedetail Exception feedetailWrapper = {}", feedetailWrappers);
		} 
	}
}
