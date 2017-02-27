package com.eshop4j.web.response.act;

import java.math.BigDecimal;

import com.eshop4j.core.base.BaseEntity;

public class RedpacketStatisticsResponse extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1657795613418122954L;
	
	private Integer cfplannerNum;
	private Integer cfplannerRedpacketCount;
	private BigDecimal cfplannerRedpacketMoney;
	
	private Integer customerNum;
	private Integer customerRedpacketCount;
	private BigDecimal customerRedpacketMoney; 
	
	private Integer useNum;
	private Integer useCount;
	private BigDecimal totalMoney;
	
	public Integer getCfplannerNum() {
		return cfplannerNum;
	}
	public void setCfplannerNum(Integer cfplannerNum) {
		this.cfplannerNum = cfplannerNum;
	}
	public Integer getCfplannerRedpacketCount() {
		return cfplannerRedpacketCount;
	}
	public void setCfplannerRedpacketCount(Integer cfplannerRedpacketCount) {
		this.cfplannerRedpacketCount = cfplannerRedpacketCount;
	}
	public BigDecimal getCfplannerRedpacketMoney() {
		return cfplannerRedpacketMoney;
	}
	public void setCfplannerRedpacketMoney(BigDecimal cfplannerRedpacketMoney) {
		this.cfplannerRedpacketMoney = cfplannerRedpacketMoney;
	}
	public Integer getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(Integer customerNum) {
		this.customerNum = customerNum;
	}
	public Integer getCustomerRedpacketCount() {
		return customerRedpacketCount;
	}
	public void setCustomerRedpacketCount(Integer customerRedpacketCount) {
		this.customerRedpacketCount = customerRedpacketCount;
	}
	public BigDecimal getCustomerRedpacketMoney() {
		return customerRedpacketMoney;
	}
	public void setCustomerRedpacketMoney(BigDecimal customerRedpacketMoney) {
		this.customerRedpacketMoney = customerRedpacketMoney;
	}
	public Integer getUseNum() {
		return useNum;
	}
	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}
	public Integer getUseCount() {
		return useCount;
	}
	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	} 
	
	

}
