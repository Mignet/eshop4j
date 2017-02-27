package com.eshop4j.web.model.cim;

import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月27日 18:07:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgFeeAttr implements Serializable {
	
	private static final long serialVersionUID = -6499391532643881292L;
	
    /**
     *主键,自增长
     */
	private Integer id;
	
    /**
     *收费描述,按照cps、cpa模式收费
     */
	private String feeDesc;
	
    /**
     *收费类型 ,cpa-按投资人数量进行收费,cps-按投资金额进行收费
     */
	private String feeType;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setFeeDesc(String feeDesc){
		this.feeDesc = feeDesc;
	}
	
	public String getFeeDesc(){
		return feeDesc;
	}
	
	public void setFeeType(String feeType){
		this.feeType = feeType;
	}
	
	public String getFeeType(){
		return feeType;
	}
	
}

