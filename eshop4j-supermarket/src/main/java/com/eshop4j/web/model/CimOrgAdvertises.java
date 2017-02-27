package com.eshop4j.web.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年10月26日 18:23:35
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgAdvertises implements Serializable {
	
	private static final long serialVersionUID = 2316985068712034141L;
	
    /**
     *
     */
	private Integer aid;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *机构活动宣传图
     */
	private String orgActivityAdvertise;
	
    /**
     *机构活动宣传图跳转链接
     */
	private String orgActivityAdvertiseUrl;
	
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
	


	public void setAid(Integer aid){
		this.aid = aid;
	}
	
	public Integer getAid(){
		return aid;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setOrgActivityAdvertise(String orgActivityAdvertise){
		this.orgActivityAdvertise = orgActivityAdvertise;
	}
	
	public String getOrgActivityAdvertise(){
		return orgActivityAdvertise;
	}
	
	public void setOrgActivityAdvertiseUrl(String orgActivityAdvertiseUrl){
		this.orgActivityAdvertiseUrl = orgActivityAdvertiseUrl;
	}
	
	public String getOrgActivityAdvertiseUrl(){
		return orgActivityAdvertiseUrl;
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

