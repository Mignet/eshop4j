package com.eshop4j.tc.fee.common.strategy;

import java.rmi.ServerException;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eshop4j.web.model.CimOrginfo;
import com.eshop4j.web.model.vo.InvestRecordWrapper;
import com.eshop4j.web.service.CimOrginfoService;

/**
 * 固定期佣金计算策略
 * @author ch
 *
 */
@Component
public class FixedFeeCalcStrategy implements FeeCalcStrategy{
	

	@Autowired
	private List<OrgFeeCalcPatternStrategy> orgFeeCalcPatternStrategies;
	
	@Autowired
	private CimOrginfoService orginfoService;
	
	@Override
	public boolean matchCalcStrategy(InvestRecordWrapper investRecordWrapper) {
		return !ObjectUtils.equals(investRecordWrapper.getProductType(), 2);
	}

	@Override
	public void feeCalc(InvestRecordWrapper investRecordWrapper)throws Exception {
		CimOrginfo orginfo = new CimOrginfo();
		orginfo.setOrgNumber(investRecordWrapper.getProductOrgId());
		orginfo = orginfoService.selectOne(orginfo);
		if(orginfo == null ) throw new ServerException("机构不存在");
		for (OrgFeeCalcPatternStrategy orgFeeCalcPatternStrategy : orgFeeCalcPatternStrategies) {
			if (orgFeeCalcPatternStrategy.matchPattern(orginfo, investRecordWrapper)) {
				orgFeeCalcPatternStrategy.orgFeeCalc(orginfo, investRecordWrapper);
				break;
			}
		}
	}

}
