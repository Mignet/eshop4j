package com.eshop4j.web.response;

import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.WebUtil;
import com.eshop4j.web.model.CimOrginfo;



/**
 * 
 * 描述：APP端优质机构
 * @author yalin
 * @date 2016年9月5日 下午3:15:51 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public class CimOrgListResponse extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public CimOrgListResponse() {}
	
	public CimOrgListResponse(CimOrginfo obj) {
		WebUtil.initObj(this,obj);
		this.orgName = obj.getName();
		this.orgLogo = obj.getPlatformIco();//getPlatformlistIco
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


}
