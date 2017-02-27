package com.eshop4j.web.model;

import java.io.Serializable;
/**
 * 
 * 描述：团队介绍
 * @author yalin
 * @date 2016年7月20日 上午10:17:43 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public class CimOrgMemberInfo implements Serializable {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7702636293545916876L;
	
	private Integer tid;
	
	/**
	 * 平台信息
	 */
	//private OrgInfoResponse orgInfoResponse;
	
	/**
	 * 机构主键id
	 */
	private Integer orgId;
	/**
     * 团队logo
     */
	private String orgIcon;
	
    /**
     * 职位
     */
	private String orgMemberGrade;
	
    /**
     * 姓名
     */
	private String orgMemberName;
	
	/**
     * 团队介绍
     */
	private String orgDescribe;
	/**
     *排序
     */
	private Integer sort;
	
    /**
     *是否有效,0-有效，1-失效
     */
	private Integer isshow;
	
	
	
	
	
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

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgIcon() {
		return orgIcon;
	}

	public void setOrgIcon(String orgIcon) {
		this.orgIcon = orgIcon;
	}

	public String getOrgMemberGrade() {
		return orgMemberGrade;
	}

	public void setOrgMemberGrade(String orgMemberGrade) {
		this.orgMemberGrade = orgMemberGrade;
	}

	public String getOrgMemberName() {
		return orgMemberName;
	}

	public void setOrgMemberName(String orgMemberName) {
		this.orgMemberName = orgMemberName;
	}

	public String getOrgDescribe() {
		return orgDescribe;
	}

	public void setOrgDescribe(String orgDescribe) {
		this.orgDescribe = orgDescribe;
	}
	
	
}

