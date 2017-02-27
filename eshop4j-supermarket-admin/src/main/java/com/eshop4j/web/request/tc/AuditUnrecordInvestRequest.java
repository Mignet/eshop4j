package com.eshop4j.web.request.tc;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class AuditUnrecordInvestRequest {
	
	@NotNull(message="编号不能为空")
	private Integer id;
	
	@NotNull(message="状态不能为空")
	@Range(min=1,max=2,message = "类别只能为1,2")
	private Integer status;
	private String remark;
	/*private String deadLine;
	
	private Date investTime;*/
	private BigDecimal feeAmt;

	
	
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

	public BigDecimal getFeeAmt() {
		return feeAmt;
	}
	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	
	
}
