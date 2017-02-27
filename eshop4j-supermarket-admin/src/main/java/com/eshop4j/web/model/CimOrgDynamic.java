package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月02日 14:59:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgDynamic implements Serializable {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 9124237049770098649L;

	/**
     *
     */
	private Integer id;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
	/**
	 * 机构名称
	 */
	private String orgName;
	
	
    /**
     *机构动态摘要
     */
	private String orgSummary;
	
    /**
     *机构动态标题
     */
	private String orgTitle;
	
    /**
     *机构动态内容
     */
	private String orgContent;
	
    /**
     *机构动态h5链接
     */
	private String orgDynamicUrl;
	
    /**
     *创建人
     */
	private String creator;
	
    /**
     *修改人
     */
	private String updater;
	
	 /**
	  * 发布时间
	  */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date releaseTime;
	
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
	
    /**
     *排序
     */
	private Integer sort;
	
    /**
     *是否有效,0-有效，1-失效
     */
	private Integer isshow;
	
    /**
     *
     */
	private String remark;
	

	

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setOrgSummary(String orgSummary){
		this.orgSummary = orgSummary;
	}
	
	public String getOrgSummary(){
		return orgSummary;
	}
	
	public void setOrgTitle(String orgTitle){
		this.orgTitle = orgTitle;
	}
	
	public String getOrgTitle(){
		return orgTitle;
	}
	
	public void setOrgContent(String orgContent){
		this.orgContent = orgContent;
	}
	
	public String getOrgContent(){
		return orgContent;
	}
	
	public void setOrgDynamicUrl(String orgDynamicUrl){
		this.orgDynamicUrl = orgDynamicUrl;
	}
	
	public String getOrgDynamicUrl(){
		return orgDynamicUrl;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	
	public String getCreator(){
		return creator;
	}
	
	public void setUpdater(String updater){
		this.updater = updater;
	}
	
	public String getUpdater(){
		return updater;
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
	
	public void setSort(Integer sort){
		this.sort = sort;
	}
	
	public Integer getSort(){
		return sort;
	}
	
	public void setIsshow(Integer isshow){
		this.isshow = isshow;
	}
	
	public Integer getIsshow(){
		return isshow;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

