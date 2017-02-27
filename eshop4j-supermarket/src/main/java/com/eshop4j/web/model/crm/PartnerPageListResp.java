package com.eshop4j.web.model.crm;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;

/**
 * 
 * @描述：我的团队-分页
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class PartnerPageListResp extends BaseEntity {

	private static final long serialVersionUID = 5753516043009306844L;

	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户编码
	 */
	private String userNumber;
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 注册时间
	 */
	private Date registerTime;
	/**
	 * 累计佣金
	 */
	private Double totalFee;
	
	/**
	 * 本季度佣金
	 */
	private Double quarterFee;
	
	/**
	 * 下级人数
	 */
	private Integer childrenCount;
	
	


	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getUserNumber() {
		return userNumber;
	}




	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}




	public String getMobile() {
		return mobile;
	}




	public void setMobile(String mobile) {
		this.mobile = mobile;
	}




	public Date getRegisterTime() {
		return registerTime;
	}




	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}




	public Double getTotalFee() {
		return totalFee;
	}




	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}




	public Double getQuarterFee() {
		return quarterFee;
	}




	public void setQuarterFee(Double quarterFee) {
		this.quarterFee = quarterFee;
	}




	public Integer getChildrenCount() {
		return childrenCount;
	}




	public void setChildrenCount(Integer childrenCount) {
		this.childrenCount = childrenCount;
	}




	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
