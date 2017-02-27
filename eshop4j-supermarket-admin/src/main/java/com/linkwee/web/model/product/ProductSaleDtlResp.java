package com.linkwee.web.model.product;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.linkwee.core.base.BaseEntity;


public class ProductSaleDtlResp extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String productName;
	private String statusName;
	private String buyUserId;
	private String buyUserName;
	private String buyUserMobile;
	private Double buyedMoney;
	private Date buyedTime;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getBuyUserId() {
		return buyUserId;
	}
	public void setBuyUserId(String buyUserId) {
		this.buyUserId = buyUserId;
	}
	public String getBuyUserName() {
		return buyUserName;
	}
	public void setBuyUserName(String buyUserName) {
		this.buyUserName = buyUserName;
	}
	public Double getBuyedMoney() {
		return buyedMoney;
	}
	public void setBuyedMoney(Double buyedMoney) {
		this.buyedMoney = buyedMoney;
	}
	public Date getBuyedTime() {
		return buyedTime;
	}
	public String getBuyedTimeStr(){
		if(buyedTime!=null){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  df.format(buyedTime);
		}else{
			return "-";
		}
	}
	public void setBuyedTime(Date buyedTime) {
		this.buyedTime = buyedTime;
	}
	public String getBuyUserMobile() {
		return buyUserMobile;
	}
	public void setBuyUserMobile(String buyUserMobile) {
		this.buyUserMobile = buyUserMobile;
	}
	

}
