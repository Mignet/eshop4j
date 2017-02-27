package com.eshop4j.web.model.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class InvestInfo {
	
	private String uid;
	private String uname;
	private String umobile;
	private String cid;
	private String cname;
	private String cmobile;
	private String productId;
	private String productName;
	private Integer deadlineType;
	private String platfrom;
	private Integer model;
	private BigDecimal investAmt;
	private BigDecimal amtLimit;
	private Integer isFirstInvest;
	private Integer isPlatfromFirstInvest;
	private Date endTime;//回款时间
	
	
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUmobile() {
		return umobile;
	}
	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCmobile() {
		return cmobile;
	}
	public void setCmobile(String cmobile) {
		this.cmobile = cmobile;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getDeadlineType() {
		return deadlineType;
	}
	public void setDeadlineType(Integer deadlineType) {
		this.deadlineType = deadlineType;
	}
	public String getPlatfrom() {
		return platfrom;
	}
	public void setPlatfrom(String platfrom) {
		this.platfrom = platfrom;
	}
	public Integer getModel() {
		return model;
	}
	public void setModel(Integer model) {
		this.model = model;
	}
	public BigDecimal getInvestAmt() {
		return investAmt;
	}
	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}
	public BigDecimal getAmtLimit() {
		return amtLimit;
	}
	public void setAmtLimit(BigDecimal amtLimit) {
		this.amtLimit = amtLimit;
	}
	public Integer getIsFirstInvest() {
		return isFirstInvest;
	}
	public void setIsFirstInvest(Integer isFirstInvest) {
		this.isFirstInvest = isFirstInvest;
	}
	public Integer getIsPlatfromFirstInvest() {
		return isPlatfromFirstInvest;
	}
	public void setIsPlatfromFirstInvest(Integer isPlatfromFirstInvest) {
		this.isPlatfromFirstInvest = isPlatfromFirstInvest;
	}
	
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
