package com.linkwee.web.request.act;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class RedPacketInfoRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7490334549827671603L;
	/**
	 * base info
	 */
	private String activityId;//或者编号
	private String activityName;//活动名称
	private String redpacketId;
	@NotNull(message="红包类型不能为空")
	@Range(min=1,max=1,message="红包类型必须为1")
	private Integer type;//红包类型 1=投资返现红包
	@NotBlank(message="红包名称不能为空")
	private String name;//红包名称
	@NotNull(message="红包金额不能为空")
	@Min(value=1,message="红包最小金额为1")
	private Double money;//红包金额
	@NotBlank(message="红包描述不能为空")
	private String remark;//红包描述
	
	
	/**
	 * use rule
	 */
	@NotNull(message="平台限制不能为空")
	@Range(min=0,max=1,message="平台限制值必须为0~1之间")
	private Integer limitPlatform;//平台限制
	private String platformId;//平台编号
	@NotNull(message="金额限制不能为空")
	@Range(min=0,max=2,message="金额限制值必须为0~2之间")
	private Integer limitMoney;//0=不限 | 1=等于金额 | 2=大于金额 
	private Double investMoney;//投资金额  仅limitMoney=1有效
	
	@NotNull(message="金额限制不能为空")
	@Range(min=0,max=2,message="金额限制值必须为0~2之间") 
	private Integer investLlimit;//用户投资限制 0=不限|1=用户首投|2=平台首投
	
	//private Integer limitInvestUser;//0=不限|1=首次投资用户使用;
	@NotNull(message="产品限制不能为空")
	@Range(min=0,max=2,message="产品限制值必须为0~2之间")
	private Integer limitProduct;//0=不限|1=投资期限|2=产品编号
	private Integer relationalOperator;//0=等于|1=大于等于  仅limitInvestProduct=1有效
	private Integer deadline;//期限 仅limitInvestProduct=1有效
	private String pids;//产品编号 仅limitInvestProduct=2有效
	
	private String operator;
	
	

	public String getActivityId() {
		return activityId;
	}







	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}







	public String getActivityName() {
		return activityName;
	}







	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}







	public String getRedpacketId() {
		return redpacketId;
	}







	public void setRedpacketId(String redpacketId) {
		this.redpacketId = redpacketId;
	}







	public Integer getType() {
		return type;
	}







	public void setType(Integer type) {
		this.type = type;
	}







	public String getName() {
		return name;
	}







	public void setName(String name) {
		this.name = name;
	}







	public Double getMoney() {
		return money;
	}







	public void setMoney(Double money) {
		this.money = money;
	}







	public String getRemark() {
		return remark;
	}







	public void setRemark(String remark) {
		this.remark = remark;
	}







	public Integer getLimitPlatform() {
		return limitPlatform;
	}







	public void setLimitPlatform(Integer limitPlatform) {
		this.limitPlatform = limitPlatform;
	}







	public String getPlatformId() {
		return platformId;
	}







	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}







	public Integer getLimitMoney() {
		return limitMoney;
	}







	public void setLimitMoney(Integer limitMoney) {
		this.limitMoney = limitMoney;
	}







	public Double getInvestMoney() {
		return investMoney;
	}







	public void setInvestMoney(Double investMoney) {
		this.investMoney = investMoney;
	}




	


	public Integer getInvestLlimit() {
		return investLlimit;
	}







	public void setInvestLlimit(Integer investLlimit) {
		this.investLlimit = investLlimit;
	}







	public Integer getLimitProduct() {
		return limitProduct;
	}







	public void setLimitProduct(Integer limitProduct) {
		this.limitProduct = limitProduct;
	}







	public Integer getRelationalOperator() {
		return relationalOperator;
	}







	public void setRelationalOperator(Integer relationalOperator) {
		this.relationalOperator = relationalOperator;
	}







	public Integer getDeadline() {
		return deadline;
	}







	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}







	public String getPids() {
		return pids;
	}







	public void setPids(String pids) {
		this.pids = pids;
	}







	public String getOperator() {
		return operator;
	}







	public void setOperator(String operator) {
		this.operator = operator;
	}







	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	
}
