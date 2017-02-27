package com.linkwee.api.response.act;

import java.math.BigDecimal;
import java.util.Date;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;

public class RedpacketResponse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3791871733235249147L;
	
	
	private String rid;
	private String name;
	private String remark;
	private BigDecimal money;
	private Integer count;
	private Integer status;
	private String userMobile;
	private String userName;
	private String userImage;
	private Date expireDate;
	private String cfplannerId;
	
	//用户投资限制 0=不限|1=用户首投|2=平台首投
	private Integer investLimit;
	
	//平台限制 0=不限 | 1=不限
	private Boolean platformLimit;//平台限制
	private String platform;//平台编号
	
	
	private Integer productLimit;//0=不限|1=投资期限|2=产品编号
	private Integer deadline;//期限 仅limitInvestProduct=1有效
	private String productName;//产品编号 仅limitInvestProduct=2有效
	
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRedpacketMoney(){
		return this.money.setScale(0,BigDecimal.ROUND_DOWN).toString();
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	public String getRedpacketCount(){
		return count == null ? "" : count.toString();
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}

	public String getUseStatus(){
		if(status!=null){
			switch (status) {
			case 2:
				return "0";
			case 3:
				return "1";
			case 4:
				return "2";
			default:
				return "";
			}
		}
		return "";
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
	public String getExpireTime() {
		return DateUtils.format(this.expireDate);
	}
	
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public String getRedpacketType(){
		return this.cfplannerId==null?"0":"1";
	}
	
	public void setCfplannerId(String cfplannerId) {
		this.cfplannerId = cfplannerId;
	}

	
	public Integer getInvestLimit() {
		return investLimit;
	}
	public void setInvestLimit(Integer investLimit) {
		this.investLimit = investLimit;
	}
	public Boolean getPlatformLimit() {
		return platformLimit;
	}
	
	public void setPlatformLimit(Boolean platformLimit) {
		this.platformLimit = platformLimit;
	}
	
	public String getPlatform() {
		return platform;
	}
	
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	public Integer getProductLimit() {
		int result = 0;
		switch (productLimit) {
		case 1000:
			result = 0;
			break;
		case 1001:
			result = 1;
			break;
		case 1002:	
			result = 2;
			break;
		case 1003:
			result = 3;
			break;
		}
		return result;
	}
	
	public void setProductLimit(Integer productLimit) {
		
		this.productLimit = productLimit;
	}
	public Integer getDeadline() {
		return deadline;
	}
	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}
