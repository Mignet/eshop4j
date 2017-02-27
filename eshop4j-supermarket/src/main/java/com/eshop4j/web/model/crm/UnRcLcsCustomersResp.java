package com.eshop4j.web.model.crm;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;

/**
 * 
 * @描述：未被邀请客户列表
 *
 * @author Bob
 * @时间 2015年10月14日下午3:32:46
 *
 */
public class UnRcLcsCustomersResp extends BaseEntity {

	private static final long serialVersionUID = -1932817863208942524L;

	/**
	 * 用户编码
	 */
	private String customerId;

	/**
	 * 用户名
	 */
	private String customerName;

	/**
	 * 手机号码
	 */
	private String customerMobile;

	/**
	 * 注册时间
	 */
	private Date registerTime;

	/**
	 * 到期日期
	 */
	private Date nearEndDate;

	/**
	 * 最近投资
	 */
	private Double nearInvestAmt;

	/**
	 * 投资金额
	 */
	private Integer totalInvestAmt;

	/**
	 * 投资笔数
	 */
	private Integer totalInvestCount;

	/**
	 * 是否重要客户
	 */
	private Integer important;

	/**
	 * 是否自由客户 1-是 0-否
	 */
	private Integer freecustomer;
	/**
	 * 是否新客户 
	 */
	private boolean isNewRegist;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getNearEndDate() {
		return nearEndDate;
	}

	public void setNearEndDate(Date nearEndDate) {
		this.nearEndDate = nearEndDate;
	}

	public Double getNearInvestAmt() {
		return nearInvestAmt;
	}

	public void setNearInvestAmt(Double nearInvestAmt) {
		this.nearInvestAmt = nearInvestAmt;
	}

	public Integer getTotalInvestAmt() {
		return totalInvestAmt;
	}

	public void setTotalInvestAmt(Integer totalInvestAmt) {
		this.totalInvestAmt = totalInvestAmt;
	}

	public Integer getTotalInvestCount() {
		return totalInvestCount;
	}

	public void setTotalInvestCount(Integer totalInvestCount) {
		this.totalInvestCount = totalInvestCount;
	}

	public Integer getImportant() {
		return important;
	}

	public void setImportant(Integer important) {
		this.important = important;
	}

	public Integer getFreecustomer() {
		return freecustomer;
	}

	public void setFreecustomer(Integer freecustomer) {
		this.freecustomer = freecustomer;
	}

	public boolean isNewRegist() {
		return isNewRegist;
	}

	public void setNewRegist(boolean isNewRegist) {
		this.isNewRegist = isNewRegist;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
