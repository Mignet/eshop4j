package com.eshop4j.tc.fee.service.impl;

import java.rmi.ServerException;
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
import com.eshop4j.tc.fee.common.strategy.OrgFeeCalcPatternStrategy;
import com.eshop4j.tc.fee.service.FeeCalcService;
import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.CimProduct;
import com.eshop4j.web.model.vo.InvestRecordWrapper;
import com.eshop4j.web.service.CimOrginfoService;
import com.eshop4j.web.service.CimProductInvestRecordService;
import com.eshop4j.web.service.CimProductService;
import com.eshop4j.web.service.InvestRecordAware;
import com.eshop4j.xoss.util.RejectedExecuteRetry;

@Service("feeCalcService")
public class FeeCalcServiceImpl implements FeeCalcService,InvestRecordAware{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FeeCalcServiceImpl.class);

	@Autowired
	private OrgFeeCalcPatternStrategy orgFeeCalcPatternStrategy;

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
	
	@RejectedExecuteRetry
	@Override
	public void everyDayFeeCalc(Date investTime ) throws Exception{
		String dateStr = null;
		try{
			dateStr = DateUtils.format(investTime, DateUtils.FORMAT_SHORT);
			
			LOGGER.info(dateStr +" DayFeeCalc start" );
			
			List<CimProduct> products = productService.getFlowProducts();
			
			if(products==null||products.isEmpty())return;		
			
			List<InvestRecordWrapper> investRecords = investRecordService.getInvestRecordByProducts(products);
			
			if(investRecords==null||investRecords.isEmpty())return;
			
			for (InvestRecordWrapper investRecordWrapper : investRecords) {
				try{
					investRecordWrapper.setRemark(StringUtils.join(new Object[]{investRecordWrapper.getProductName(),dateStr,"佣金"}, "-"));
					investRecordWrapper.setProductDays(1);
					investRecordWrapper.setProductType(1);//无论募集期还是固定产品全按固定产品计算
					investRecordWrapper.setInvestTime(investTime);
					
					try{
						delegate.everyDayFeeCalc(investRecordWrapper);
					}catch(Exception e){
						LOGGER.error("everyDayFeeCalc exception investRecordWrapper={},exception={}", new Object[]{investRecordWrapper,e});
					}
					
					for (InvestRecordAware investRecordAware : investRecordAwares) {
						try{
							investRecordAware.investRecordProcess(investRecordWrapper);
						}catch(Exception e){
							LOGGER.error("everyDayFeeCalc exception investRecordAware={},investRecordWrapper={},exception={}", new Object[]{investRecordAware,investRecordWrapper,e});
						}
						
					}
				}catch(Exception e){
					LOGGER.error("everyDayFeeCalc investRecordWrapper  exception investTime={},investRecordWrapper={},e={}",new Object[]{dateStr,investRecordWrapper, e});
				}
			}
			LOGGER.info(dateStr +" Day FeeCalc end" );
		}catch(Exception e){
			LOGGER.error("everyDayFeeCalc exception investTime={}", dateStr,e);
			throw e;
		}
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void investRecordProcess(InvestRecordWrapper investRecordWrapper)throws Exception {
		try{
			CimOrginfo orginfo = new CimOrginfo();
			orginfo.setOrgNumber(investRecordWrapper.getProductOrgId());
			orginfo.setOrgIsstaticproduct(1);
			orginfo = orginfoService.selectOne(orginfo);
			if(orginfo == null ) throw new ServerException("机构不存在");
			orgFeeCalcPatternStrategy.orgFeeCalc(orginfo, investRecordWrapper);
		}catch(Exception e){
			LOGGER.warn("calculateFee Exception investRecordWrapper={},exception={}", investRecordWrapper,e);
			throw e;
		}
	}


}
