package com.eshop4j.web.model.product;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月26日 17:17:45
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ProductSaleReward extends BaseEntity {
	
	private static final long serialVersionUID = 4442520186971094431L;
	
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
     *销售奖励比例
     */
	private Double rewardRatio;
	
    /**
     *销售奖励开始时间|格式yyyy-MM-ddHH:mm:ss
     */
	private Date rewardBeginTime;
	
    /**
     *销售奖励截止时间可以为nul格式yyyy-MM-ddHH:mm:ss
     */
	private Date rewardEndTime;
	
    /**
     *产品是否需要销售奖励(N=不需要|Y=需要)
     */
	private String isReward;
	
    /**
     *备注
     */
	private String fremark;
	
    /**
     *销售奖励(YEAR年化|PERCENTAGE百分比)
     */
	private String rewardCode;
	


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
	
	public void setRewardRatio(Double rewardRatio){
		this.rewardRatio = rewardRatio;
	}
	
	public Double getRewardRatio(){
		return rewardRatio;
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
	
	public void setFremark(String fremark){
		this.fremark = fremark;
	}
	
	public String getFremark(){
		return fremark;
	}
	
	public void setRewardCode(String rewardCode){
		this.rewardCode = rewardCode;
	}
	
	public String getRewardCode(){
		return rewardCode;
	}
	

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

