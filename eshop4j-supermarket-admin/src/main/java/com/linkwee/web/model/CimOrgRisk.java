package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.String;
 import java.math.BigDecimal;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月22日 11:03:54
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgRisk implements Serializable {
	
	private static final long serialVersionUID = -6008919084370555821L;
	
    /**
     *
     */
	private Integer rid;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *机构名称
     */
	private String orgName;
	
    /**
     *机构指标名称
     */
	private String indicatorName;
	
    /**
     *机构指标评分-百分制
     */
	private BigDecimal indicatorScore;
	
    /**
     *创建人
     */
	private String creator;
	
    /**
     *修改人
     */
	private String updater;
	
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
	


	public void setRid(Integer rid){
		this.rid = rid;
	}
	
	public Integer getRid(){
		return rid;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}
	
	public String getOrgName(){
		return orgName;
	}
	
	public void setIndicatorName(String indicatorName){
		this.indicatorName = indicatorName;
	}
	
	public String getIndicatorName(){
		return indicatorName;
	}
	
	public void setIndicatorScore(BigDecimal indicatorScore){
		this.indicatorScore = indicatorScore;
	}
	
	public BigDecimal getIndicatorScore(){
		return indicatorScore;
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

