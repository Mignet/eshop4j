package com.linkwee.api.response.tc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerUnrecordInvestResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2376730982373179040L;

	
	private String platfromName;
	
	private String productName;
	/**
	 * 期限
	 */
	private String deadLine;
	
	private Integer deadLineType;
	
	
	private BigDecimal investAmt;
	
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date time;
	
	private String url;
	

	public String getPlatfromName() {
		return platfromName;
	}
	public void setPlatfromName(String platfromName) {
		this.platfromName = platfromName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getInvestAmt() {
		return investAmt.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}
	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}
	
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	
	public String getDeadLineType() {
		return String.valueOf(deadLineType);
	}
	public void setDeadLineType(Integer deadLineType) {
		this.deadLineType = deadLineType;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
