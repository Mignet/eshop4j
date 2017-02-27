package com.eshop4j.tc.fee.common.strategy;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eshop4j.tc.fee.common.FeeCalcDelegate;
import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.vo.InvestRecordWrapper;

@Component
public class CPSPatternStrategy implements OrgFeeCalcPatternStrategy{
	
	@Autowired
	private FeeCalcDelegate delegate;

	@Override
	public boolean matchPattern(CimOrginfo orginfo,InvestRecordWrapper investRecordWrapper) {
		return  ObjectUtils.equals(orginfo.getOrgFeeType(), 2);
	}

	@Override
	public void orgFeeCalc(CimOrginfo orginfo,InvestRecordWrapper investRecordWrapper) throws Exception{
		delegate.feeCalc(investRecordWrapper);
	}

}

