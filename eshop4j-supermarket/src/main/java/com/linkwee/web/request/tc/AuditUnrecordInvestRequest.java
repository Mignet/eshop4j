package com.linkwee.web.request.tc;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AuditUnrecordInvestRequest {
	
	@NotNull(message="编号不能为空")
	private Integer id;
	
	@NotNull(message="状态不能为空")
	@Range(min=1,max=2,message = "类别只能为1,2")
	private Integer status;
	private String remark;
	private String deadLine;
	
	private BigDecimal feeAmt;
	
	private Date investTime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	public BigDecimal getFeeAmt() {
		return feeAmt;
	}
	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}
	public Date getInvestTime() {
		return investTime;
	}
	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	
	
	
}
