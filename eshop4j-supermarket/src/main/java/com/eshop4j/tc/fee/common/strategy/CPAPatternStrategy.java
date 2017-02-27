package com.eshop4j.tc.fee.common.strategy;

import java.math.BigDecimal;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eshop4j.tc.fee.common.FeeCalcDelegate;
import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.vo.InvestRecordWrapper;
@Component
public class CPAPatternStrategy implements OrgFeeCalcPatternStrategy{
	
	@Autowired
	private FeeCalcDelegate delegate;

	@Override
	public boolean matchPattern(CimOrginfo orginfo,InvestRecordWrapper investRecordWrapper) {
		return ObjectUtils.equals(orginfo.getOrgFeeType(), 1);
	}

	@Override
	public void orgFeeCalc(CimOrginfo orginfo,InvestRecordWrapper investRecord) throws Exception{
		
		if(!investRecord.isPlatfromFirstInvest()){
			String remark = StringUtils.join(new Object[]{orginfo.getName(),"产品为首投,该客户之前已经投资过"});
			investRecord.setRemark(remark);
			//cpa模式不是首次投资理财师佣金为零
			investRecord.setFeeRatio(new BigDecimal(0));
			
		}else{
			if(investRecord.getInvestAmt().compareTo(orginfo.getOrgAmountLimit())==1){
				investRecord.setInvestAmt(orginfo.getOrgAmountLimit());
			}
			if(investRecord.getProductDays().compareTo(orginfo.getOrgInvestdeadlineLimit())==1){
				investRecord.setProductDays(orginfo.getOrgInvestdeadlineLimit());
			}	
		}
		delegate.feeCalc(investRecord);
	
	}

}
