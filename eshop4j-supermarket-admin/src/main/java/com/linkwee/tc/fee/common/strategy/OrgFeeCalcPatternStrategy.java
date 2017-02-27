package com.linkwee.tc.fee.common.strategy;

import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.vo.InvestRecordWrapper;

public interface OrgFeeCalcPatternStrategy {
	
	
	/**
	 * 执行佣金计算
	 * @param investor  投资者信息
	 * @param cfplanner 理财师信息 
	 * @param investRecord 投资记录
	 */
	void orgFeeCalc(CimOrginfo orginfo,InvestRecordWrapper investRecordWrapper)throws Exception;
}
