package com.linkwee.web.model.crm;

import com.linkwee.core.base.BaseEntity;

/**
 * 平台帐号管理列表
 * 
 * @Author ZhongLing
 * @Date 2016年1月20日 下午4:20:57
 */
public class OrgSimpleResp extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 平台名称
	 */
	private String orgName;
	/**
	 * 图标
	 */
	private String orgLogo;
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
	
	
	

}
