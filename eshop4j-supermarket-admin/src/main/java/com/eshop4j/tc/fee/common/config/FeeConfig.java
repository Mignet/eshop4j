package com.eshop4j.tc.fee.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FeeConfig {

	
	@Value("${Second_Ratio}")
	private Double second_ratio; //二级理财师佣金比例
	
	@Value("${Third_Ratio}")
	private Double third_ratio; //三级理财师佣金比率
	
	@Value("${FEE_SUMMARY_SCAN_COUNT}")
	private Integer fee_scan_count; //佣金汇总扫描数
	

	public Double getSecond_ratio() {
		return second_ratio;
	}

	public void setSecond_ratio(Double second_ratio) {
		this.second_ratio = second_ratio;
	}

	public Double getThird_ratio() {
		return third_ratio;
	}

	public void setThird_ratio(Double third_ratio) {
		this.third_ratio = third_ratio;
	}

	public Integer getFee_scan_count() {
		return fee_scan_count;
	}

	public void setFee_scan_count(Integer fee_scan_count) {
		this.fee_scan_count = fee_scan_count;
	}

	
	
}
