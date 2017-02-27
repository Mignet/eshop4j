package com.linkwee.web.model.product;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月31日 16:04:47
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ProductStatistics extends BaseEntity {
	
	private static final long serialVersionUID = -9193746161748708249L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *产品ID
     */
	private String productId;
	
    /**
     *募集完成时间
     */
	private Date collectFinishTime;
	
    /**
     *产品被投资总额
     */
	private BigDecimal buyedTotalMoney;
	
    /**
     *剩余金额
     */
	private BigDecimal remaMoney;
	
    /**
     *产品已投资人数
     */
	private Integer buyedTotalPeople;
	
    /**
     *创建时间
     */
	private Date createTime;
	
    /**
     *更新时间
     */
	private Date updateTime;
	


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
	
	public void setCollectFinishTime(Date collectFinishTime){
		this.collectFinishTime = collectFinishTime;
	}
	
	public Date getCollectFinishTime(){
		return collectFinishTime;
	}
	
	public void setBuyedTotalMoney(BigDecimal buyedTotalMoney){
		this.buyedTotalMoney = buyedTotalMoney;
	}
	
	public BigDecimal getBuyedTotalMoney(){
		return buyedTotalMoney;
	}
	
	public void setRemaMoney(BigDecimal remaMoney){
		this.remaMoney = remaMoney;
	}
	
	public BigDecimal getRemaMoney(){
		return remaMoney;
	}
	
	public void setBuyedTotalPeople(Integer buyedTotalPeople){
		this.buyedTotalPeople = buyedTotalPeople;
	}
	
	public Integer getBuyedTotalPeople(){
		return buyedTotalPeople;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

