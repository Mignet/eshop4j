package com.eshop4j.web.response.orgInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.core.util.WebUtil;
import com.eshop4j.web.model.CimOrgMemberInfo;


/**
 * 
 * 描述：平台详情
 * @author yalin
 * @date 2016年7月22日 上午11:09:38 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public class OrgInfoResponse extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	
   private Integer id;
	
	/***
	 * 平台编码
	 */
   private String orgNo;
   /**
    * 平台logo
    */
   private String orgLogo;
   /**
    * 平台名称
    */
   private String orgName;
   /**
    * 平台级别
    */
   private String orgLevel;
   /**
    * 年化收益
    */
   private String feeRateMin;
   private String feeRateMax;
   /**
    * 产品期限
    */
   private String proDaysMin;
   private String proDaysMax;
   /**
    * 上线时间
    */
   @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
   private  Date upTime;
   /**
    * 平台背景
    */
   private String orgBack;
   /**
    * 资金托管
    */
   private String trusteeship;
   /**
    * ICP备案
    */
   private String ICP;
   /**
    * 所在城市
    */
   private String city;
   /**
    * 注册资金
    */
   private String capital;
   
   /**
	 * 机构官网的url
	 */
   private String orgUrl;
   
   /**
    *  平台简介
    */
   private String orgProfile;
   
   /**
    * 安全保障
    */
   private String orgSecurity;
   
   /**
    *  团队信息
    */
   private List<CimOrgMemberInfo> teamInfos;
   
   
	/**
	 * 最小佣金利率
	 */
	private String minFeeRatio;
	/**
	 * 最大佣金利率
	 */
	private String maxFeeRatio;
    /**
     *	收费类型 
     *	1:cpa-按投资人数量进行收费
     *	2:cps-按投资金额进行收费
     */
	private Integer orgFeeType;
	
    /**
     *金额限制(元)
     */
	private BigDecimal orgAmountLimit;
	
    /**
     *投资期限限制(天)
     */
	private Integer orgInvestdeadlineLimit;
	/**
     *产品最小期限天数 自定义显示
     */
	private String deadLineMinSelfDefined;
	
    /**
     *产品最大期限天数 自定义显示
     */
	private String deadLineMaxSelfDefined;
	
	/**
	 * 产品期限
	 */
	private String deadLineValueText;
	
	 /**
     *是否静态产品 (1：是 ,0：否)
     */
	private Integer orgIsstaticproduct;
	

	public String getDeadLineMinSelfDefined() {
		return deadLineMinSelfDefined;
	}

	public void setDeadLineMinSelfDefined(String deadLineMinSelfDefined) {
		this.deadLineMinSelfDefined = deadLineMinSelfDefined;
	}

	public String getDeadLineMaxSelfDefined() {
		return deadLineMaxSelfDefined;
	}

	public void setDeadLineMaxSelfDefined(String deadLineMaxSelfDefined) {
		this.deadLineMaxSelfDefined = deadLineMaxSelfDefined;
	}

	public String getDeadLineValueText() {
		
		if (StringUtils.isNotBlank(proDaysMin) && StringUtils.isNotBlank(proDaysMax) && proDaysMin.equals(proDaysMax)){
			if(StringUtils.isNotBlank(deadLineMinSelfDefined)){
				deadLineValueText = deadLineMinSelfDefined;
			} else {
				deadLineValueText = proDaysMin+"天";
			}
		} else {
			if(StringUtils.isNotBlank(deadLineMinSelfDefined) && StringUtils.isNotBlank(deadLineMaxSelfDefined)){
				deadLineValueText = deadLineMinSelfDefined+"~"+deadLineMaxSelfDefined;
			} else {
				deadLineValueText = proDaysMin+"天~"+proDaysMax+"天";
			}
		}
		
		return StringUtils.separateNumberChinese(deadLineValueText, ",");
		
	}

	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
	}

	public String getMinFeeRatio() {
		return minFeeRatio;
	}

	public void setMinFeeRatio(String minFeeRatio) {
		this.minFeeRatio = minFeeRatio;
	}

	public String getMaxFeeRatio() {
		return maxFeeRatio;
	}

	public void setMaxFeeRatio(String maxFeeRatio) {
		this.maxFeeRatio = maxFeeRatio;
	}

	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrgProfile() {
		return orgProfile;
	}
	
	public void setOrgProfile(String orgProfile) {
		this.orgProfile = orgProfile;
	}
	
	public String getOrgSecurity() {
		return orgSecurity;
	}
	
	public void setOrgSecurity(String orgSecurity) {
		this.orgSecurity = orgSecurity;
	}
	
	public List<CimOrgMemberInfo> getTeamInfos() {
		return teamInfos;
	}
	
	public void setTeamInfos(List<CimOrgMemberInfo> teamInfos) {
		this.teamInfos = teamInfos;
	}

	public String getOrgUrl() {
	return orgUrl;
	}
	
	public void setOrgUrl(String orgUrl) {
		this.orgUrl = orgUrl;
	}

	public String getOrgNo() {
		return orgNo;
	}
	
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	
	public String getOrgLogo() {
		return orgLogo;
	}
	
	public void setOrgLogo(String orgLogo) {
		this.orgLogo = orgLogo;
	}
	
	public String getOrgName() {
		return orgName;
	}
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getOrgLevel() {
		return orgLevel;
	}
	
	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}
	
	public String getFeeRateMin() {
		return feeRateMin;
	}

	public void setFeeRateMin(String feeRateMin) {
		this.feeRateMin = feeRateMin;
	}

	public String getFeeRateMax() {
		return feeRateMax;
	}

	public void setFeeRateMax(String feeRateMax) {
		this.feeRateMax = feeRateMax;
	}

	public String getProDaysMin() {
		return proDaysMin;
	}

	public void setProDaysMin(String proDaysMin) {
		this.proDaysMin = proDaysMin;
	}

	public String getProDaysMax() {
		return proDaysMax;
	}

	public void setProDaysMax(String proDaysMax) {
		this.proDaysMax = proDaysMax;
	}

	public Date getUpTime() {
		return upTime;
	}
	
	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}
	
	public String getOrgBack() {
		return orgBack;
	}
	
	public void setOrgBack(String orgBack) {
		this.orgBack = orgBack;
	}
	
	public String getTrusteeship() {
		return trusteeship;
	}
	
	public void setTrusteeship(String trusteeship) {
		this.trusteeship = trusteeship;
	}
	
	public String getICP() {
		return ICP;
	}
	
	public void setICP(String iCP) {
		ICP = iCP;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCapital() {
		return capital+"万元";
	}
	
	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Integer getOrgFeeType() {
		return orgFeeType;
	}

	public void setOrgFeeType(Integer orgFeeType) {
		this.orgFeeType = orgFeeType;
	}

	public String getOrgAmountLimit() {
		//return orgAmountLimit;
		return WebUtil.getDefaultFormat(orgAmountLimit);
	}

	public void setOrgAmountLimit(BigDecimal orgAmountLimit) {
		this.orgAmountLimit = orgAmountLimit;
	}

	public Integer getOrgInvestdeadlineLimit() {
		return orgInvestdeadlineLimit;
	}

	public void setOrgInvestdeadlineLimit(Integer orgInvestdeadlineLimit) {
		this.orgInvestdeadlineLimit = orgInvestdeadlineLimit;
	}

	public Integer getOrgIsstaticproduct() {
		return orgIsstaticproduct;
	}

	public void setOrgIsstaticproduct(Integer orgIsstaticproduct) {
		this.orgIsstaticproduct = orgIsstaticproduct;
	}
   
   

}
