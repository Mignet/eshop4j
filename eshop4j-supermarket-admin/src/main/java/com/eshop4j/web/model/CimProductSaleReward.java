package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月14日 18:23:34
 * 
 * Copyright (c) 深圳ESHOP有限公司-版权所有
 */
public class CimProductSaleReward implements Serializable {
	
	private static final long serialVersionUID = 6417134960339895724L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *产品id
     */
	private String productId;
	
    /**
     *销售奖励
     */
	private String saleReward;
	
    /**
     *销售奖励开始时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date rewardBeginTime;
	
    /**
     *销售奖励截止时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date rewardEndTime;
	
    /**
     *产品是否需要销售奖励(n=不需要|y=需要)
     */
	private String isReward;
	
    /**
     *备注
     */
	private String remark;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setSaleReward(String saleReward){
		this.saleReward = saleReward;
	}
	
	public String getSaleReward(){
		return saleReward;
	}
	
	public void setRewardBeginTime(Date rewardBeginTime){
		this.rewardBeginTime = rewardBeginTime;
	}
	
	public Date getRewardBeginTime(){
		return rewardBeginTime;
	}
	
	public void setRewardEndTime(Date rewardEndTime){
		this.rewardEndTime = rewardEndTime;
	}
	
	public Date getRewardEndTime(){
		return rewardEndTime;
	}
	
	public void setIsReward(String isReward){
		this.isReward = isReward;
	}
	
	public String getIsReward(){
		return isReward;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
}

