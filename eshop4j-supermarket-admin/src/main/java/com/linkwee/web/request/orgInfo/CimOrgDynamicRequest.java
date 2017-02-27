package com.linkwee.web.request.orgInfo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月11日 16:27:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgDynamicRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2325519021167607205L;

	/**
    *
    */
	private Integer id;

	/**
	 * 机构编码
	 */
	private String orgNumber;

	/**
	 * 机构名称
	 */
	private String orgName;

	/**
	 * 机构动态摘要
	 */
	private String orgSummary;

	/**
	 * 机构动态标题
	 */
	private String orgTitle;

	/**
	 * 机构动态内容
	 */
	private String orgContent;

	/**
	 * 机构动态h5链接
	 */
	private String orgDynamicUrl;

	/**
	 * 创建人
	 */
	private String creator;

	/**
	 * 修改人
	 */
	private String updater;
	
	/**
	 * 单选框
	 */
	private String dynamicrdo;
	
	/**
	 * 发布时间
	 */
	private Date releaseTime;
	
	

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getDynamicrdo() {
		return dynamicrdo;
	}

	public void setDynamicrdo(String dynamicrdo) {
		this.dynamicrdo = dynamicrdo;
	}

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 是否有效,0-有效，1-失效
	 */
	private Integer isshow;

	/**
    *
    */
	private String remark;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgSummary() {
		return orgSummary;
	}

	public void setOrgSummary(String orgSummary) {
		this.orgSummary = orgSummary;
	}

	public String getOrgTitle() {
		return orgTitle;
	}

	public void setOrgTitle(String orgTitle) {
		this.orgTitle = orgTitle;
	}

	public String getOrgContent() {
		return orgContent;
	}

	public void setOrgContent(String orgContent) {
		this.orgContent = orgContent;
	}

	public String getOrgDynamicUrl() {
		return orgDynamicUrl;
	}

	public void setOrgDynamicUrl(String orgDynamicUrl) {
		this.orgDynamicUrl = orgDynamicUrl;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsshow() {
		return isshow;
	}

	public void setIsshow(Integer isshow) {
		this.isshow = isshow;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
