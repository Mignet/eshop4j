package com.eshop4j.tc.fee.common.strategy;

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
	public void orgFeeCalc(CimOrginfo orginfo,InvestRecordWrapper investRecord) throws Exception{
		
		if(investRecord.getInvestAmt().compareTo(orginfo.getOrgAmountLimit())==1){
			investRecord.setInvestAmt(orginfo.getOrgAmountLimit());
		}
		if(investRecord.getProductDays().compareTo(orginfo.getOrgInvestdeadlineLimit())==1){
			investRecord.setProductDays(orginfo.getOrgInvestdeadlineLimit());
		}
		delegate.feeCalc(investRecord);
	
	}

}
