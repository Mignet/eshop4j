package com.linkwee.web.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年11月24日 14:15:40
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class UnconventionalRecord implements Serializable {
	
	private static final long serialVersionUID = -1807547505821590642L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *理财师编码
     */
	private String lcsNumber;
	
    /**
     *操作类别:1理财师回退业务员
     */
	private Integer optType;
	
    /**
     *生效时间
     */
	private Date effectiveTime;
	
    /**
     *扩展,保存jason格式
     */
	private String extended;
	
    /**
     *操作人员编号
     */
	private String optUserNumber;
	
    /**
     *操作人员姓名
     */
	private String optUserName;
	
    /**
     *创建时间
     */
	private Date crtTime;
	
    /**
     *修改时间
     */
	private Date modifyTime;
	
    /**
     *备注
     */
	private String remark;
	/**
	 * 
	 * @param id
	 */
	private String cfpMobile;
	private String customerMobile;
	private String optTypes;

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setLcsNumber(String lcsNumber){
		this.lcsNumber = lcsNumber;
	}
	
	public String getLcsNumber(){
		return lcsNumber;
	}
	
	public void setOptType(Integer optType){
		this.optType = optType;
	}
	
	public Integer getOptType(){
		return optType;
	}
	
	public void setEffectiveTime(Date effectiveTime){
		this.effectiveTime = effectiveTime;
	}
	
	public Date getEffectiveTime(){
		return effectiveTime;
	}
	
	public void setExtended(String extended){
		this.extended = extended;
	}
	
	public String getExtended(){
		return extended;
	}
	
	public void setOptUserNumber(String optUserNumber){
		this.optUserNumber = optUserNumber;
	}
	
	public String getOptUserNumber(){
		return optUserNumber;
	}
	
	public void setOptUserName(String optUserName){
		this.optUserName = optUserName;
	}
	
	public String getOptUserName(){
		return optUserName;
	}
	
	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	public Date getCrtTime(){
		return crtTime;
	}
	
	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}
	
	public Date getModifyTime(){
		return modifyTime;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}

	public String getCfpMobile() {
		return cfpMobile;
	}

	public void setCfpMobile(String cfpMobile) {
		this.cfpMobile = cfpMobile;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	

	public String getOptTypes() {
		return optTypes;
	}

	public void setOptTypes(String optTypes) {
		this.optTypes = optTypes;
	}

	@Override
	public String toString() {
		return "UnconventionalRecord [id=" + id + ", lcsNumber=" + lcsNumber
				+ ", optType=" + optType + ", effectiveTime=" + effectiveTime
				+ ", optUserNumber=" + optUserNumber + ", optUserName="
				+ optUserName + ", crtTime=" + crtTime + ", modifyTime="
				+ modifyTime + ", remark=" + remark + ", cfpMobile="
				+ cfpMobile + ", customerMobile=" + customerMobile + "]";
	}

	
}

