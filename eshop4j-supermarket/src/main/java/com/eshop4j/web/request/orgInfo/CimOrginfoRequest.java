package com.eshop4j.web.request.orgInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.eshop4j.web.model.CimOrgMemberInfo;
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
public class CimOrginfoRequest implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5900072174289126880L;

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
	private String orgName;
	
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
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
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
	private String platformImg;
	
    /**
     *平台列表logo
     */
	private String platformListImg;
	
    /**
     *平台详情图片
     */
	private String platformDetailImg;
	
    /**
     *营业执照
     */
	private String businessLicenseImg;
	
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
     *用户资产余额查询接口（可选）
     */
	private String orgUserbalanceUrl;
	
	/**
     *查帐号是否存在于第三方平台url
     */
	private String orgUserExistUrl;
	
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
     *机构佣金
     */
	private BigDecimal orgFeeRatio;
	
    /**
     *收费类型 ,1:cpa-按投资人数量进行收费,2:cps-按投资金额进行收费
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
	 * 团队信息
	 */
	private List<CimOrgMemberInfo> teams;
	
	/**
     *创建人
     */
	private String orgCreator;
	
    /**
     *修改人
     */
	private String orgUpdater;
	
	/**
     *是否虚拟机构 (1：是 ,0：否)
     */
	private Integer orgIsstaticproduct;
	
	/**
	 * pc端 优质平台广告
	 */
	private String orgAdvertisement;
	
	
	
	


	public Integer getOrgIsstaticproduct() {
		return orgIsstaticproduct;
	}

	public void setOrgIsstaticproduct(Integer orgIsstaticproduct) {
		this.orgIsstaticproduct = orgIsstaticproduct;
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
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	
	
	public String getPlatformImg() {
		return platformImg;
	}

	public void setPlatformImg(String platformImg) {
		this.platformImg = platformImg;
	}

	public String getPlatformListImg() {
		return platformListImg;
	}

	public void setPlatformListImg(String platformListImg) {
		this.platformListImg = platformListImg;
	}

	public String getBusinessLicenseImg() {
		return businessLicenseImg;
	}

	public void setBusinessLicenseImg(String businessLicenseImg) {
		this.businessLicenseImg = businessLicenseImg;
	}

	public void setPlatformDetailImg(String platformDetailImg){
		this.platformDetailImg = platformDetailImg;
	}
	
	public String getPlatformDetailImg(){
		return platformDetailImg;
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

	public String getOrgCreator() {
		return orgCreator;
	}

	public void setOrgCreator(String orgCreator) {
		this.orgCreator = orgCreator;
	}

	public String getOrgUpdater() {
		return orgUpdater;
	}

	public void setOrgUpdater(String orgUpdater) {
		this.orgUpdater = orgUpdater;
	}
	
	
	
}

