package com.eshop4j.web.request.orgInfo;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class CimOrgRiskRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4281604484383010583L;

	/**
	 * 机构编码
	 */
	private String orgNumber;

	/**
	 * 机构名称
	 */
	private String orgName;

	/**
	 * 机构背景实力评分
	 */
	private BigDecimal backgroundScore;
	
	/**
	 * 机构风险控制评分
	 */
	private BigDecimal riskControlScore;
	
	/**
	 * 机构运营能力评分
	 */
	private BigDecimal runPowerScore;
	
	/**
	 * 机构信息披露评分
	 */
	private BigDecimal inforDisclosureScore;
	
	/**
	 * 机构用户体验评分
	 */
	private BigDecimal userExperienceScore;
	
	/**
     *排序
     */
	private Integer sort;
	
    /**
     *是否有效,0-有效，1-失效
     */
	private Integer isshow;
	
    /**
     * 备注
     */
	private String remark;
	
	
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

	public BigDecimal getBackgroundScore() {
		return backgroundScore;
	}

	public void setBackgroundScore(BigDecimal backgroundScore) {
		this.backgroundScore = backgroundScore;
	}

	public BigDecimal getRiskControlScore() {
		return riskControlScore;
	}

	public void setRiskControlScore(BigDecimal riskControlScore) {
		this.riskControlScore = riskControlScore;
	}

	public BigDecimal getRunPowerScore() {
		return runPowerScore;
	}

	public void setRunPowerScore(BigDecimal runPowerScore) {
		this.runPowerScore = runPowerScore;
	}

	public BigDecimal getInforDisclosureScore() {
		return inforDisclosureScore;
	}

	public void setInforDisclosureScore(BigDecimal inforDisclosureScore) {
		this.inforDisclosureScore = inforDisclosureScore;
	}

	public BigDecimal getUserExperienceScore() {
		return userExperienceScore;
	}

	public void setUserExperienceScore(BigDecimal userExperienceScore) {
		this.userExperienceScore = userExperienceScore;
	}
	

}
