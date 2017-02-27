package com.linkwee.web.response.jpressPlatform;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.jpressPlatform.JpPlatform;


/**
 * 
 * 描述：投呗端 平台返回信息
 * @author yalin
 * @date 2016年7月20日 下午4:14:14 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public class PlatformInvestorResponse extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5223481236068856084L;

	public PlatformInvestorResponse() {
		
	}
	
	public PlatformInvestorResponse(JpPlatform platform) {
		 WebUtil.initObj(this,platform);
		 //WebUtil.initObj(this,cimOrginfo,"yyyy-MM-dd hh:mm:ss");
		 this.setPlatformIco(platform.getPlatformDetailImg()); //平台列表logo
		 this.setTransfer(platform.isIsTransfer() ? "支持" : "不支持");
		 switch(platform.getGrade())
			{
		 case  1:
			 this.setGrade("B");
			 break;
		 case  2:
			 this.setGrade("BB");
			 break;
		 case  3:
			 this.setGrade("BBB");
			 break;
		 case  4:
			 this.setGrade("A");
			 break;
		 case  5:
			 this.setGrade("AA");
			 break;
		 case  6:
			 this.setGrade("AAA");
			 break;
		 default:;
			}
			 
		 
			 
		 this.setMinProfit(WebUtil.getDefaultFormat(platform.getMinProfit()));
		 this.setMaxProfit(WebUtil.getDefaultFormat(platform.getMaxProfit()));
		 this.setMinDeadLine(WebUtil.getDefaultFormat(platform.getMinDeadLine()));
		 this.setMaxDeadLine(WebUtil.getDefaultFormat(platform.getMaxDeadLine()));
		//this.setStartDate(DateUtils.format(obj.getStartDate(), DateUtils.FORMAT_SHORT));
	}
	
	
    /**
     *机构名称
     */
	private String name;
	
    /**
     *平台logo
     */
	private String platformIco;
    /**
     *安全评级
     */
	private String grade;
	
	 /**
     *注册资本
     */
	private String capital;
	
	
	 /**
     *上线时间
     */
	private String upTime;
	
	 /**
     *所在城市
     */
	private String city;
	
	 /**
     *平台背景
     */
	private String context;
	
	 /**
     *资金托管
     */
	private String trusteeship;
	
	 /**
     *保障模式
     */
	private String securityModel;
	
	/**
	 * 投标保障
	 */
	private String bidSecurity;
	/**
	 * 债券转让0不支持；1支持
	 */
	private String transfer;
	
   /**
     *最小年化收益
     */
	private String minProfit;
	/**
	 *最大年化收益
	 */
	private String maxProfit;
	
	/**
	 * 最小产品期限
	 */
	private String minDeadLine;
	
	/**
	 * 最大产品期限
	 */
	private String maxDeadLine;
	
	/**
     *产品期限天数 自定义显示
     */
	private String deadLineSelfDefined;
	
	/**
	 * 详情页链接地址
	 */
	private String dtlLinkUrl;
	
	public String getDtlLinkUrl() {
		return dtlLinkUrl;
	}

	public void setDtlLinkUrl(String dtlLinkUrl) {
		this.dtlLinkUrl = dtlLinkUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlatformIco() {
		return platformIco;
	}

	public void setPlatformIco(String platformIco) {
		this.platformIco = platformIco;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMinProfit() {
		return minProfit;
	}

	public void setMinProfit(String minProfit) {
		this.minProfit = minProfit;
	}

	public String getMaxProfit() {
		return maxProfit;
	}

	public void setMaxProfit(String maxProfit) {
		this.maxProfit = maxProfit;
	}

	public String getMinDeadLine() {
		return minDeadLine;
	}

	public void setMinDeadLine(String minDeadLine) {
		this.minDeadLine = minDeadLine;
	}

	public String getMaxDeadLine() {
		return maxDeadLine;
	}

	public void setMaxDeadLine(String maxDeadLine) {
		this.maxDeadLine = maxDeadLine;
	}


	public String getDeadLineSelfDefined() {
		return deadLineSelfDefined;
	}

	public void setDeadLineSelfDefined(String deadLineSelfDefined) {
		this.deadLineSelfDefined = deadLineSelfDefined;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getUpTime() {
		return upTime;
	}

	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getTrusteeship() {
		return trusteeship;
	}

	public void setTrusteeship(String trusteeship) {
		this.trusteeship = trusteeship;
	}

	public String getSecurityModel() {
		return securityModel;
	}

	public void setSecurityModel(String securityModel) {
		this.securityModel = securityModel;
	}

	public String getBidSecurity() {
		return bidSecurity;
	}

	public void setBidSecurity(String bidSecurity) {
		this.bidSecurity = bidSecurity;
	}

	public String getTransfer() {
		return transfer;
	}

	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	
	

	
}
