package com.linkwee.web.response;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.CimOrginfo;



/**
 * 
 * 描述：pc端最新入驻机构
 * @author yalin
 * @date 2016年9月5日 下午3:15:51 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public class CimNewOrgListResponse extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6519767124526741127L;


	public CimNewOrgListResponse() {}
	
	public CimNewOrgListResponse(CimOrginfo cimOrginfo) {
		WebUtil.initObj(this,cimOrginfo);
		this.orgName = cimOrginfo.getName();
		this.orgLogo = cimOrginfo.getPlatformlistIco();
	}
	
	/**
	 * 机构名称
	 */
	private String orgName;//name
	
	/**
	 * 机构logo
	 */
	private String orgLogo;//platformIco
	/**
     *机构编码-固定8位编码，不重复字段
     */
	private String orgNumber;
	
	/**
     *安全评级 1-B,2-BB,3-BBB,4-A,5-AA,6-AAA
     */
	private String grade;
	
	/**
     *机构简介
     */
	private String orgProfile;
	

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

	public String getOrgLogo() {
		return orgLogo;
	}

	public void setOrgLogo(String orgLogo) {
		this.orgLogo = orgLogo;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getOrgProfile() {
		return orgProfile;
	}

	public void setOrgProfile(String orgProfile) {
		this.orgProfile = orgProfile;
	}

}
