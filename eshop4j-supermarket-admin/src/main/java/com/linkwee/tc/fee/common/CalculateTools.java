package com.linkwee.tc.fee.common;

import java.math.BigDecimal;

/**
 * 佣金 , 年化金额计算相关工具类
 * @author ch
 *
 */
public final class CalculateTools {
	
	private CalculateTools() {}
	
	/**
	 * 年化计算 公式 ( 投资金额  * 产品期限  / 360  )
	 * @param investAmount 投资金额
	 * @param deadLineValue 产品期限
	 * @return
	 */
	public static BigDecimal yearpurAmountCompute(BigDecimal investAmount,Integer deadLine){
		return (investAmount.multiply(new BigDecimal(deadLine)).divide(new BigDecimal(360), 4, BigDecimal.ROUND_DOWN));
	}
	
	/**
	 * 佣金收益计算  公式 ( 年化  * 佣金费率    ) 。 如 1000 * 2%  其中2% 转化为(2 * 0.01) 最后 公式等于  ( 1000  * 2 * 0.01 )
	 * @param yearpurAmount
	 * @param feeRatio
	 * @return
	 */
	public static BigDecimal feeAmountCompute(BigDecimal yearpurAmount,Double feeRatio){
		return (yearpurAmount.multiply(new BigDecimal(feeRatio)).multiply(new BigDecimal(0.01d)).setScale(4,BigDecimal.ROUND_DOWN));
	}

}
