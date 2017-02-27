package com.eshop4j.tc.fee.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.tc.fee.common.FeeCalcDelegate;
import com.eshop4j.tc.fee.common.strategy.FeeCalcStrategy;
import com.eshop4j.tc.fee.common.strategy.OrgFeeCalcPatternStrategy;
import com.eshop4j.tc.fee.service.FeeCalcService;
import com.eshop4j.web.model.CimProduct;
import com.eshop4j.web.model.vo.InvestRecordWrapper;
import com.eshop4j.web.service.CimOrginfoService;
import com.eshop4j.web.service.CimProductInvestRecordService;
import com.eshop4j.web.service.CimProductService;
import com.eshop4j.web.service.InvestRecordAware;
import com.eshop4j.xoss.util.RejectedExecuteRetry;

@Service("feeCalcService")
public class FeeCalcServiceImpl implements FeeCalcService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FeeCalcServiceImpl.class);


	@Autowired
	private List<OrgFeeCalcPatternStrategy> orgFeeCalcPatternStrategies;
	
	@Autowired
	private List<FeeCalcStrategy> feeCalcStrategys;
	
	@Resource
	private FeeCalcDelegate delegate;
	
	@Resource
	private CimProductService productService;
	
	@Resource(name="crmCfgLevelService")
	private InvestRecordAware levelService;
	
	@Resource
	private CimProductInvestRecordService investRecordService;
	
	@Resource(name="cimOrgFeeGatherService")
	private InvestRecordAware cimOrgFeeGatherService;
	
	
	private List<InvestRecordAware> investRecordAwares;
	
	@Autowired
	private CimOrginfoService orginfoService;
	
	@PostConstruct
	private void init(){
		investRecordAwares = Lists.newArrayList(levelService,cimOrgFeeGatherService);
	}
	
	/**
	 * 匹配机构佣金计算策略 并进行计算
	 * @param investRecordWrapper
	 */
	protected void feeCalc(InvestRecordWrapper investRecordWrapper) throws Exception{
		for (FeeCalcStrategy feeCalcStrategy : feeCalcStrategys) {
			if(feeCalcStrategy.matchCalcStrategy(investRecordWrapper)){
				feeCalcStrategy.feeCalc(investRecordWrapper);
			}
		}
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@RejectedExecuteRetry
	@Override
	public void investRecordProcess(InvestRecordWrapper investRecord)throws Exception {
		try{
			feeCalc(investRecord);
		}catch(Exception e){
			LOGGER.warn("calculateFee Exception investRecordWrapper={},exception={}", investRecord,e);
			throw e;
		}
	}
	
	@RejectedExecuteRetry
	@Override
	public void everyDayFeeCalc(Date investTime ) throws Exception{
		String dateStr = null;
		try{
			dateStr = DateUtils.format(investTime, DateUtils.FORMAT_SHORT);
			
			LOGGER.debug(dateStr +" DayFeeCalc start" );
			
			List<CimProduct> products = productService.getFlowProducts();
			
			if(products==null||products.isEmpty())return;		
			
			List<InvestRecordWrapper> investRecords = investRecordService.getInvestRecordByProducts(products);
			
			if(investRecords==null||investRecords.isEmpty())return;
			
			for (InvestRecordWrapper investRecordWrapper : investRecords) {
				try{
					investRecordWrapper.setRemark(StringUtils.join(new Object[]{investRecordWrapper.getProductName(),dateStr,"佣金计算"}, "-"));
					investRecordWrapper.setProductDays(1);
					investRecordWrapper.setInvestTime(investTime);
					
					try{
						delegate.everyDayFeeCalc(investRecordWrapper);
					}catch(Exception e){
						LOGGER.warn("everyDayFeeCalc exception investRecordWrapper={},exception={}", new Object[]{investRecordWrapper,e});
					}
					
					for (InvestRecordAware investRecordAware : investRecordAwares) {
						try{
							investRecordAware.investRecordProcess(investRecordWrapper);
						}catch(Exception e){
							LOGGER.warn("everyDayFeeCalc exception investRecordAware={},investRecordWrapper={},exception={}", new Object[]{investRecordAware,investRecordWrapper,e});
						}
						
					}
					/*levelService.investRecordProcess(investRecordWrapper);
					cimOrgFeeGatherService.investRecordProcess(investRecordWrapper);*/
				}catch(Exception e){
					LOGGER.warn("everyDayFeeCalc investRecordWrapper  exception investTime={},investRecordWrapper={},e={}",new Object[]{dateStr,investRecordWrapper, e});
				}
			}
			LOGGER.debug(dateStr +" Day FeeCalc end" );
		}catch(Exception e){
			LOGGER.warn("everyDayFeeCalc exception investTime={}", dateStr,e);
			throw e;
		}
	}


}
