package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.math.BigDecimal;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月14日 18:23:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimProductStatistics implements Serializable {
	
	private static final long serialVersionUID = 8725940995866182216L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *产品id
     */
	private String productId;
	
    /**
     *募集完成时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date collectFinishTime;
	
    /**
     *产品被投资总额
     */
	private BigDecimal buyedTotalMoney;
	
    /**
     *产品已投资人数
     */
	private Integer buyedTotalPeople;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
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
	
}

