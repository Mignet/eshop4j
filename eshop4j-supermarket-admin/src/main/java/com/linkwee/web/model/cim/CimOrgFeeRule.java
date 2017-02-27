package com.linkwee.web.model.cim;

import java.io.Serializable;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月09日 18:26:56
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgFeeRule implements Serializable {
	
	private static final long serialVersionUID = -1175876297437165857L;
	
    /**
     *主键id, 自增
     */
	private Long id;
	
    /**
     *收费类型 ,cpa-按投资人数量进行收费,cps-按投资金额进行收费
     */
	private String feeTypeCode;
	
    /**
     *收费类型类别,fixed-每个新投资人固定费用,float-根据区间设置类别，proportion根据首投金额比较设置类别,amtproportion-根据用户年化和产品期限按比例计算
     */
	private String feeAttr;
	
    /**
     *
     */
	private String needFirstInvest;
	
    /**
     *收费比例
     */
	private String intervalRemark;
	


	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setFeeTypeCode(String feeTypeCode){
		this.feeTypeCode = feeTypeCode;
	}
	
	public String getFeeTypeCode(){
		return feeTypeCode;
	}
	
	public void setFeeAttr(String feeAttr){
		this.feeAttr = feeAttr;
	}
	
	public String getFeeAttr(){
		return feeAttr;
	}
	
	public void setNeedFirstInvest(String needFirstInvest){
		this.needFirstInvest = needFirstInvest;
	}
	
	public String getNeedFirstInvest(){
		return needFirstInvest;
	}
	
	public void setIntervalRemark(String intervalRemark){
		this.intervalRemark = intervalRemark;
	}
	
	public String getIntervalRemark(){
		return intervalRemark;
	}
	
}

