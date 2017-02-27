package com.eshop4j.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.eshop4j.core.util.StringUtils;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年08月17日 14:20:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrginfo implements Serializable {
	
	private static final long serialVersionUID = 7782033589578297641L;
	
    /**
     *主键，自增长
     */
	private Integer id;
	
    /**
     *机构编码-不重复字段
     */
	private String orgNumber;
	
    /**
     *机构名称
     */
	private String name;
	
    /**
     *机构后台账户
     */
	private String orgAccount;
	
    /**
     *机构后台密码
     */
	private String orgPassword;
	
    /**
     *平台背景
     */
	private String context;
	
    /**
     *注册资本
     */
	private String capital;
	
    /**
     *上线时间
     */
	private Date upTime;
	
    /**
     *所在城市
     */
	private String city;
	
    /**
     *icp备案
     */
	private String icpFiling;
	
    /**
     *法人代表
     */
	private String representative;
	
    /**
     *联系方式
     */
	private String contact;
	
    /**
     *主页平台logo
     */
	private String platformIco;
	
    /**
     *平台列表logo
     */
	private String platformlistIco;
	
    /**
     *平台详情图片
     */
	private String platformDetailImg;
	
    /**
     *营业执照
     */
	private String businessLicense;
	
    /**
     *首页推荐，是否首页推荐0-不推荐、1-推荐
     */
	private Integer recommend;
	
    /**
     *安全评级 1-B,2-BB,3-BBB,4-A,5-AA,6-AAA
     */
	private String grade;
	
    /**
     *机构列表排名
     */
	private Integer top;
	
    /**
     *首页推荐机构排名
     */
	private Integer homepageSort;
	
    /**
     *合作状态.0-合作结束，1-合作中
     */
	private Integer status;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *创建时间
     */
	private Date createTime;
	
    /**
     *更新时间
     */
	private Date updateTime;
	
    /**
     *最小年化收益
     */
	private BigDecimal minProfit;
	
    /**
     *最大年化收益
     */
	private BigDecimal maxProfit;
	
    /**
     *平台产品最小佣金率
     */
	private BigDecimal minFeeRatio;
	
    /**
     *平台产品最大佣金率
     */
	private BigDecimal maxFeeRatio;
	
    /**
     *最小产品期限
     */
	private Integer minDeadLine;
	
    /**
     *最大产品期限
     */
	private Integer maxDeadLine;
	
    /**
     *产品最小期限天数 自定义显示
     */
	private String deadLineMinSelfDefined;
	
    /**
     *产品最大期限天数 自定义显示
     */
	private String deadLineMaxSelfDefined;
	
    /**
     *机构官网的url
     */
	private String orgUrl;
	
    /**
     *平台产品跳转地址
     */
	private String orgProductUrl;
	
    /**
     *平台用户中心跳转地址
     */
	private String orgUsercenterUrl;
	
    /**
     *绑定用户地址
     */
	private String orgBindUserUrl;
	
	/**
     *查帐号是否存在于第三方平台url
     */
	private String orgUserExistUrl;
	
    /**
     *用户资产余额查询接口（可选）
     */
	private String orgUserbalanceUrl;
	
    /**
     *资金托管
     */
	private String trusteeship;
	
    /**
     *机构简介
     */
	private String orgProfile;
	
    /**
     *安全保障
     */
	private String orgSecurity;
	
    /**
     *	机构产品佣金率
     */
	private BigDecimal orgFeeRatio;
	
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
	 * 产品期限
	 */
	private String deadLineValueText;
	
	/**
	 * 团队信息
	 */
	private List<CimOrgMemberInfo> teams; //= new ArrayList<CimOrgMemberInfo>()
	
	/**
	 * 平台下可投的产品数量
	 */
	private Integer usableProductNums;
	
	/**
	 * pc端 优质平台广告
	 */
	private String orgAdvertisement;
	
	/**
     *是否虚拟机构 (1：是 ,0：否)
     */
	private Integer orgIsstaticproduct;
	
	/**
     *机构考查报告
     */
	private String orgInvestigationReport;
	
    /**
     *机构缴纳的保证金
     */
	private BigDecimal orgMargin;
	
    /**
     *机构亮点介绍(多个以英文逗号分隔)
     */
	private String orgAdvantage;
	
    /**
     *机构自定义标签(多个以英文逗号分隔)
     */
	private String orgTag;
	
    /**
     *产品自定义标签(多个以英文逗号分隔)
     */
	private String orgProductTag;
	
	
	
	
	
	

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

	public String getOrgInvestigationReport() {
		return orgInvestigationReport;
	}

	public void setOrgInvestigationReport(String orgInvestigationReport) {
		this.orgInvestigationReport = orgInvestigationReport;
	}

	public BigDecimal getOrgMargin() {
		return orgMargin;
	}

	public void setOrgMargin(BigDecimal orgMargin) {
		this.orgMargin = orgMargin;
	}

	public Integer getOrgIsstaticproduct() {
		return orgIsstaticproduct;
	}

	public void setOrgIsstaticproduct(Integer orgIsstaticproduct) {
		this.orgIsstaticproduct = orgIsstaticproduct;
	}

	public Integer getUsableProductNums() {
		return usableProductNums;
	}

	public void setUsableProductNums(Integer usableProductNums) {
		this.usableProductNums = usableProductNums;
	}

	public String getDeadLineValueText() {
		if (minDeadLine != null  && maxDeadLine != null && minDeadLine.equals(maxDeadLine)){
			if(StringUtils.isNotBlank(deadLineMinSelfDefined)){
				deadLineValueText = deadLineMinSelfDefined;
			} else {
				deadLineValueText = minDeadLine+"天";
			}
		} else {
			if(StringUtils.isNotBlank(deadLineMinSelfDefined) && StringUtils.isNotBlank(deadLineMaxSelfDefined)){
				deadLineValueText = deadLineMinSelfDefined+"~"+deadLineMaxSelfDefined;
			} else {
				deadLineValueText = minDeadLine+"天~"+maxDeadLine+"天";
			}
		}
		
		return StringUtils.separateNumberChinese(deadLineValueText, ",");
	}

	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
	}

	public List<CimOrgMemberInfo> getTeams() {
		return teams;
	}

	public void setTeams(List<CimOrgMemberInfo> teams) {
		this.teams = teams;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setOrgAccount(String orgAccount){
		this.orgAccount = orgAccount;
	}
	
	public String getOrgAccount(){
		return orgAccount;
	}
	
	public void setOrgPassword(String orgPassword){
		this.orgPassword = orgPassword;
	}
	
	public String getOrgPassword(){
		return orgPassword;
	}
	
	public void setContext(String context){
		this.context = context;
	}
	
	public String getContext(){
		return context;
	}
	
	public void setCapital(String capital){
		this.capital = capital;
	}
	
	public String getCapital(){
		return capital;
	}
	
	public void setUpTime(Date upTime){
		this.upTime = upTime;
	}
	
	public Date getUpTime(){
		return upTime;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setIcpFiling(String icpFiling){
		this.icpFiling = icpFiling;
	}
	
	public String getIcpFiling(){
		return icpFiling;
	}
	
	public void setRepresentative(String representative){
		this.representative = representative;
	}
	
	public String getRepresentative(){
		return representative;
	}
	
	public void setContact(String contact){
		this.contact = contact;
	}
	
	public String getContact(){
		return contact;
	}
	
	public void setPlatformIco(String platformIco){
		this.platformIco = platformIco;
	}
	
	public String getPlatformIco(){
		return platformIco;
	}
	
	public void setPlatformlistIco(String platformlistIco){
		this.platformlistIco = platformlistIco;
	}
	
	public String getPlatformlistIco(){
		return platformlistIco;
	}
	
	public void setPlatformDetailImg(String platformDetailImg){
		this.platformDetailImg = platformDetailImg;
	}
	
	public String getPlatformDetailImg(){
		return platformDetailImg;
	}
	
	public void setBusinessLicense(String businessLicense){
		this.businessLicense = businessLicense;
	}
	
	public String getBusinessLicense(){
		return businessLicense;
	}
	
	public void setRecommend(Integer recommend){
		this.recommend = recommend;
	}
	
	public Integer getRecommend(){
		return recommend;
	}
	
	public void setGrade(String grade){
		this.grade = grade;
	}
	
	public String getGrade(){
		return grade;
	}
	
	public void setTop(Integer top){
		this.top = top;
	}
	
	public Integer getTop(){
		return top;
	}
	
	public void setHomepageSort(Integer homepageSort){
		this.homepageSort = homepageSort;
	}
	
	public Integer getHomepageSort(){
		return homepageSort;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
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
	
	public void setMinProfit(BigDecimal minProfit){
		this.minProfit = minProfit;
	}
	
	public BigDecimal getMinProfit(){
		return minProfit;
	}
	
	public void setMaxProfit(BigDecimal maxProfit){
		this.maxProfit = maxProfit;
	}
	
	public BigDecimal getMaxProfit(){
		return maxProfit;
	}
	
	public void setMinFeeRatio(BigDecimal minFeeRatio){
		this.minFeeRatio = minFeeRatio;
	}
	
	public BigDecimal getMinFeeRatio(){
		return minFeeRatio;
	}
	
	public void setMaxFeeRatio(BigDecimal maxFeeRatio){
		this.maxFeeRatio = maxFeeRatio;
	}
	
	public BigDecimal getMaxFeeRatio(){
		return maxFeeRatio;
	}
	
	public void setMinDeadLine(Integer minDeadLine){
		this.minDeadLine = minDeadLine;
	}
	
	public Integer getMinDeadLine(){
		return minDeadLine;
	}
	
	public void setMaxDeadLine(Integer maxDeadLine){
		this.maxDeadLine = maxDeadLine;
	}
	
	public Integer getMaxDeadLine(){
		return maxDeadLine;
	}
	
	public void setDeadLineMinSelfDefined(String deadLineMinSelfDefined){
		this.deadLineMinSelfDefined = deadLineMinSelfDefined;
	}
	
	public String getDeadLineMinSelfDefined(){
		return deadLineMinSelfDefined;
	}
	
	public void setDeadLineMaxSelfDefined(String deadLineMaxSelfDefined){
		this.deadLineMaxSelfDefined = deadLineMaxSelfDefined;
	}
	
	public String getDeadLineMaxSelfDefined(){
		return deadLineMaxSelfDefined;
	}
	
	public void setOrgUrl(String orgUrl){
		this.orgUrl = orgUrl;
	}
	
	public String getOrgUrl(){
		return orgUrl;
	}
	
	public void setOrgProductUrl(String orgProductUrl){
		this.orgProductUrl = orgProductUrl;
	}
	
	public String getOrgProductUrl(){
		return orgProductUrl;
	}
	
	public void setOrgUsercenterUrl(String orgUsercenterUrl){
		this.orgUsercenterUrl = orgUsercenterUrl;
	}
	
	public String getOrgUsercenterUrl(){
		return orgUsercenterUrl;
	}
	
	public void setOrgBindUserUrl(String orgBindUserUrl){
		this.orgBindUserUrl = orgBindUserUrl;
	}
	
	public String getOrgBindUserUrl(){
		return orgBindUserUrl;
	}
	
	public void setOrgUserbalanceUrl(String orgUserbalanceUrl){
		this.orgUserbalanceUrl = orgUserbalanceUrl;
	}
	
	public String getOrgUserbalanceUrl(){
		return orgUserbalanceUrl;
	}
	
	public void setTrusteeship(String trusteeship){
		this.trusteeship = trusteeship;
	}
	
	public String getTrusteeship(){
		return trusteeship;
	}
	
	public void setOrgProfile(String orgProfile){
		this.orgProfile = orgProfile;
	}
	
	public String getOrgProfile(){
		return orgProfile;
	}
	
	public void setOrgSecurity(String orgSecurity){
		this.orgSecurity = orgSecurity;
	}
	
	public String getOrgSecurity(){
		return orgSecurity;
	}
	
	public void setOrgFeeRatio(BigDecimal orgFeeRatio){
		this.orgFeeRatio = orgFeeRatio;
	}
	
	public BigDecimal getOrgFeeRatio(){
		return orgFeeRatio;
	}
	
	public void setOrgFeeType(Integer orgFeeType){
		this.orgFeeType = orgFeeType;
	}
	
	public Integer getOrgFeeType(){
		return orgFeeType;
	}
	
	public void setOrgAmountLimit(BigDecimal orgAmountLimit){
		this.orgAmountLimit = orgAmountLimit;
	}
	
	public BigDecimal getOrgAmountLimit(){
		return orgAmountLimit;
	}
	
	public void setOrgInvestdeadlineLimit(Integer orgInvestdeadlineLimit){
		this.orgInvestdeadlineLimit = orgInvestdeadlineLimit;
	}
	
	public Integer getOrgInvestdeadlineLimit(){
		return orgInvestdeadlineLimit;
	}

	public String getOrgAdvertisement() {
		return orgAdvertisement;
	}

	public void setOrgAdvertisement(String orgAdvertisement) {
		this.orgAdvertisement = orgAdvertisement;
	}

	public String getOrgUserExistUrl() {
		return orgUserExistUrl;
	}

	public void setOrgUserExistUrl(String orgUserExistUrl) {
		this.orgUserExistUrl = orgUserExistUrl;
	}
	
	
	
}

