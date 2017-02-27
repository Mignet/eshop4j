package com.linkwee.web.model.cim;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CimOrgAdvertises;
import com.linkwee.web.model.CimOrgDynamic;
import com.linkwee.web.model.CimOrgMemberInfo;
import com.linkwee.web.model.CimOrgPicture;

public class OrgInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8316151385656871271L;

	private Integer id;

	/***
	 * 平台编码
	 */
	private String orgNo;

	/**
	 * 机构logo
	 */
	private String platformIco;
	
	/**
	 * 机构前台照片
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
	 * 最小年化收益
	 */
	private BigDecimal feeRateMin;
	
	/**
	 * 最大年化收益
	 */
	private BigDecimal feeRateMax;
	
	/**
	 * 最小产品期限
	 */
	private Integer proDaysMin;
	
	/**
	 * 最大产品期限
	 */
	private Integer proDaysMax;
	
	/**
	 * 上线时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date upTime;
	
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
	 * 平台简介
	 */
	private String orgProfile;

	/**
	 * 安全保障
	 */
	private String orgSecurity;

	/**
	 * 团队信息
	 */
	private List<CimOrgMemberInfo> teamInfos;

	/**
	 * 最小佣金利率
	 */
	private BigDecimal minFeeRatio;
	
	/**
	 * 最大佣金利率
	 */
	private BigDecimal maxFeeRatio;
	
	/**
	 * 收费类型 1:cpa-按投资人数量进行收费 2:cps-按投资金额进行收费
	 */
	private Integer orgFeeType;

	/**
	 * 金额限制(元)
	 */
	private BigDecimal orgAmountLimit;

	/**
	 * 投资期限限制(天)
	 */
	private Integer orgInvestdeadlineLimit;
	
	/**
	 * 产品最小期限天数 自定义显示
	 */
	private String deadLineMinSelfDefined;

	/**
	 * 产品最大期限天数 自定义显示
	 */
	private String deadLineMaxSelfDefined;

	/**
	 * 产品期限
	 */
	private String deadLineValueText;

	/**
	 * 是否未对接的机构 (虚拟机构1：是 ,0：否)
	 */
	private Integer orgIsstaticproduct;

	/**
	 * 机构亮点介绍(多个以英文逗号分隔)
	 */
	private String orgAdvantage;

	/**
	 * 机构自定义标签(多个以英文逗号分隔)
	 */
	private String orgTag;

	/**
	 * 产品自定义标签(多个以英文逗号分隔)
	 */
	private String orgProductTag;

	/**
	 * 投资攻略
	 */
	private String orgInvestStrategy;

	/**
	 * 猎财攻略
	 */
	private String orgPlannerStrategy;

	/**
	 * 2.0机构活动宣传图
	 */
	private List<ActivityList> orgActivitys;
	/**
	 * 1.2机构活动宣传图
	 */
	private List<CimOrgAdvertises> orgAdvertises;
	
	/**
	 * 机构证件图片信息
	 */
	private List<CimOrgPicture> orgPapersList;
	
	/**
	 * 机构环境图片信息
	 */
	private List<CimOrgPicture> orgEnvironmentList;
	
	/**
	 * 机构动态
	 */
	private List<CimOrgDynamic> orgDynamicList;
	
	/**
	 * 机构荣誉证书
	 */
	private List<CimOrgPicture> orgHonorList;
	
	/**
	 * 荣誉
	 */
	private String orgHonor;
	
	/**
	 * 对接的机构类型(0:移动+PC端，1:移动端，2:PC端)
	 */
	private Integer orgJointType;
	
	/**
	 * 机构可用红包数
	 */
	private Integer platformRedPacketCount;
	
	
	
	
	public Integer getPlatformRedPacketCount() {
		return platformRedPacketCount;
	}

	public void setPlatformRedPacketCount(Integer platformRedPacketCount) {
		this.platformRedPacketCount = platformRedPacketCount;
	}

	public List<CimOrgAdvertises> getOrgAdvertises() {
		return orgAdvertises;
	}

	public void setOrgAdvertises(List<CimOrgAdvertises> orgAdvertises) {
		this.orgAdvertises = orgAdvertises;
	}

	public Integer getOrgJointType() {
		return orgJointType;
	}

	public void setOrgJointType(Integer orgJointType) {
		this.orgJointType = orgJointType;
	}
	
	
	public String getOrgHonor() {
		return orgHonor;
	}

	public void setOrgHonor(String orgHonor) {
		this.orgHonor = orgHonor;
	}

	public List<CimOrgPicture> getOrgHonorList() {
		return orgHonorList;
	}

	public void setOrgHonorList(List<CimOrgPicture> orgHonorList) {
		this.orgHonorList = orgHonorList;
	}

	public List<CimOrgDynamic> getOrgDynamicList() {
		return orgDynamicList;
	}

	public void setOrgDynamicList(List<CimOrgDynamic> orgDynamicList) {
		this.orgDynamicList = orgDynamicList;
	}

	public BigDecimal getFeeRateMin() {
		return feeRateMin;
	}

	public void setFeeRateMin(BigDecimal feeRateMin) {
		this.feeRateMin = feeRateMin;
	}

	public BigDecimal getFeeRateMax() {
		return feeRateMax;
	}

	public void setFeeRateMax(BigDecimal feeRateMax) {
		this.feeRateMax = feeRateMax;
	}

	public Integer getProDaysMin() {
		return proDaysMin;
	}

	public void setProDaysMin(Integer proDaysMin) {
		this.proDaysMin = proDaysMin;
	}

	public Integer getProDaysMax() {
		return proDaysMax;
	}

	public void setProDaysMax(Integer proDaysMax) {
		this.proDaysMax = proDaysMax;
	}

	public BigDecimal getMinFeeRatio() {
		return minFeeRatio;
	}

	public void setMinFeeRatio(BigDecimal minFeeRatio) {
		this.minFeeRatio = minFeeRatio;
	}

	public BigDecimal getMaxFeeRatio() {
		return maxFeeRatio;
	}

	public void setMaxFeeRatio(BigDecimal maxFeeRatio) {
		this.maxFeeRatio = maxFeeRatio;
	}

	

	public List<CimOrgPicture> getOrgPapersList() {
		return orgPapersList;
	}

	public void setOrgPapersList(List<CimOrgPicture> orgPapersList) {
		this.orgPapersList = orgPapersList;
	}

	public List<CimOrgPicture> getOrgEnvironmentList() {
		return orgEnvironmentList;
	}

	public void setOrgEnvironmentList(List<CimOrgPicture> orgEnvironmentList) {
		this.orgEnvironmentList = orgEnvironmentList;
	}

	public List<ActivityList> getOrgActivitys() {
		return orgActivitys;
	}

	public void setOrgActivitys(List<ActivityList> orgActivitys) {
		this.orgActivitys = orgActivitys;
	}

	public String getOrgInvestStrategy() {
		return orgInvestStrategy;
	}

	public void setOrgInvestStrategy(String orgInvestStrategy) {
		this.orgInvestStrategy = orgInvestStrategy;
	}

	public String getOrgPlannerStrategy() {
		return orgPlannerStrategy;
	}

	public void setOrgPlannerStrategy(String orgPlannerStrategy) {
		this.orgPlannerStrategy = orgPlannerStrategy;
	}



	public String getPlatformIco() {
		return platformIco;
	}

	public void setPlatformIco(String platformIco) {
		this.platformIco = platformIco;
	}

	public String getOrgAdvantage() {
		return orgAdvantage;
	}

	public void setOrgAdvantage(String orgAdvantage) {
		this.orgAdvantage = orgAdvantage;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public void setOrgTag(String orgTag) {
		this.orgTag = orgTag;
	}

	public String getOrgProductTag() {
		return orgProductTag;
	}

	public void setOrgProductTag(String orgProductTag) {
		this.orgProductTag = orgProductTag;
	}

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

		if (proDaysMin != null && proDaysMax != null && proDaysMin.equals(proDaysMax)) {
			if (StringUtils.isNotBlank(deadLineMinSelfDefined)) {
				deadLineValueText = deadLineMinSelfDefined;
			} else {
				deadLineValueText = proDaysMin + "天";
			}
		} else {
			if (StringUtils.isNotBlank(deadLineMinSelfDefined) && StringUtils.isNotBlank(deadLineMaxSelfDefined)) {
				deadLineValueText = deadLineMinSelfDefined + "~" + deadLineMaxSelfDefined;
			} else {
				deadLineValueText = proDaysMin + "天~" + proDaysMax + "天";
			}
		}

		return StringUtils.separateNumberChinese(deadLineValueText, ",");

	}

	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
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
		return capital + "万元";
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
	
	public BigDecimal getOrgAmountLimit(){
		return orgAmountLimit;
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
