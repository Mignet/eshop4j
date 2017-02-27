package com.linkwee.tc.fee.common.strategy;

import com.linkwee.web.model.vo.InvestRecordWrapper;

/**
 * 佣金计算策略
 * 
 * @author ch
 * 
 * @serial 2016-07-22 16:11:16
 *
 */
public interface FeeCalcStrategy {
	
	/**
	 * 是否需要计算佣金
	 * @param type
	 * @return
	 */
	boolean matchCalcStrategy(InvestRecordWrapper investRecordWrapper);
	
	/**
	 * 佣金计算
	 * @param investor  投资者信息
	 * @param cfplanner 理财师信息 
	 * @param investRecord 投资记录
	 */
	void feeCalc(InvestRecordWrapper investRecord) throws Exception;
}
