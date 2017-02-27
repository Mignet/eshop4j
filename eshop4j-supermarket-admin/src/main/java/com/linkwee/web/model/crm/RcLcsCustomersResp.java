package com.linkwee.web.model.crm;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：被邀请客户列表
 *
 * @author Bob
 * @时间 2015年10月14日下午3:32:46
 *
 */
public class RcLcsCustomersResp extends BaseEntity {

	private static final long serialVersionUID = 4081842445503606916L;

	/**
	 * 推荐id
	 */
	private Integer rcId;

	/**
	 * 理财师编号
	 */
	private String userNumber;

	/**
	 * 客户id
	 */
	private String customerId;

	/**
	 * 客户名称
	 */
	private String customerName;

	/**
	 * 客户手机号
	 */
	private String customerMobile;

	/**
	 * 邀请码
	 */
	private String rcCode;

	/**
	 * 邀请时间
	 */
	private Date rcDate;

	/**
	 * 注册时间
	 */
	private Date registerDate;

	/**
	 * 首单时间
	 */
	private Date firstRcpDate;

	/**
	 * 邀请方式(1外部邀请，2内部签名，3内部匿名)
	 */
	private Integer rcWay;

	/**
	 * 是否注册(0:未注册；1:已注册)
	 */
	private Integer regFlag;

	public String getRcCode() {
		return rcCode;
	}

	public void setRcCode(String rcCode) {
		this.rcCode = rcCode;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

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

	public Date getRcDate() {
		return rcDate;
	}

	public void setRcDate(Date rcDate) {
		this.rcDate = rcDate;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getFirstRcpDate() {
		return firstRcpDate;
	}

	public void setFirstRcpDate(Date firstRcpDate) {
		this.firstRcpDate = firstRcpDate;
	}

	public Integer getRcWay() {
		return rcWay;
	}

	public void setRcWay(Integer rcWay) {
		this.rcWay = rcWay;
	}

	public Integer getRegFlag() {
		return regFlag;
	}

	public void setRegFlag(Integer regFlag) {
		this.regFlag = regFlag;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
