package com.eshop4j.tc.fee.service;

import java.util.Date;
/**
 * 佣金计算服务
 * 
 * @author ch
 *
 */
public interface FeeCalcService  {
	
	 void everyDayFeeCalc(Date investTime)throws Exception;
}
