package com.linkwee.web.response;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.CimOrginfo;



/**
 * 
 * 描述：机构推荐
 * @author yalin
 * @date 2016年9月5日 下午3:15:51 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public class PcPlannerRecommendResponse extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8901779260884423396L;


	public PcPlannerRecommendResponse() {
		
	}
	
	public PcPlannerRecommendResponse(CimOrginfo cimOrginfo) {
		WebUtil.initObj(this,cimOrginfo);
		 if(cimOrginfo.isHashRedpacket()){
			 this.setHashRedpacket(true);
		 }else{
			 this.setHashRedpacket(false);
		 }
		this.orgName = cimOrginfo.getName();
		this.orgLogo = cimOrginfo.getPlatformlistIco();
		 if(cimOrginfo.getUsableProductNums().equals(0)){ //可投产品数
			 this.setUsableProductNums("待发布");
		  }else{
			 this.setUsableProductNums(WebUtil.getDefaultFormat(cimOrginfo.getUsableProductNums())); 
		    }
		 this.setMinProfit(WebUtil.getDefaultFormat(cimOrginfo.getMinProfit()));
		 this.setMaxProfit(WebUtil.getDefaultFormat(cimOrginfo.getMaxProfit()));
		 this.setOrgFeeRatio(WebUtil.getDefaultFormat(cimOrginfo.getOrgFeeRatio()));
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
     *最小年化收益
     */
	private String minProfit;
	
    /**
     *最大年化收益
     */
	private String maxProfit;
	
	/**
     *	机构产品佣金率
     */
	private String orgFeeRatio;
	
	/**
	 * 产品期限
	 */
	private String deadLineValueText;
	
	/**
	 * 平台下可投的产品数量
	 */
	private String usableProductNums;
	
	/**
	 * pc端 优质平台广告
	 */
	private String orgAdvertisement;
	
	/**
	 * pc端 优质平台广告图片链接
	 */
	private String orgAdvertisementUrl;
	
	/**
	 * 是否有红包
	 */
	private boolean hashRedpacket;
	
	/**
	 * 投呗端平台自定义标签(多个以英文逗号分隔)
	 */
	private String orgInvestTag;
	
	/**
	 * 猎财端平台自定义标签(多个以英文逗号分隔)
	 */
	private String orgPlannerTag;
	
	
	

	public String getOrgInvestTag() {
		return orgInvestTag;
	}

	public void setOrgInvestTag(String orgInvestTag) {
		this.orgInvestTag = orgInvestTag;
	}

	public String getOrgPlannerTag() {
		return orgPlannerTag;
	}

	public void setOrgPlannerTag(String orgPlannerTag) {
		this.orgPlannerTag = orgPlannerTag;
	}

	public boolean isHashRedpacket() {
		return hashRedpacket;
	}

	public void setHashRedpacket(boolean hashRedpacket) {
		this.hashRedpacket = hashRedpacket;
	}

	public String getOrgAdvertisement() {
		return orgAdvertisement;
	}

	public void setOrgAdvertisement(String orgAdvertisement) {
		this.orgAdvertisement = orgAdvertisement;
	}

	public String getOrgAdvertisementUrl() {
		return orgAdvertisementUrl;
	}

	public void setOrgAdvertisementUrl(String orgAdvertisementUrl) {
		this.orgAdvertisementUrl = orgAdvertisementUrl;
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

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
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

	public String getOrgFeeRatio() {
		return orgFeeRatio;
	}

	public void setOrgFeeRatio(String orgFeeRatio) {
		this.orgFeeRatio = orgFeeRatio;
	}

	public String getDeadLineValueText() {
		return deadLineValueText;
	}

	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
	}

	public String getUsableProductNums() {
		return usableProductNums;
	}

	public void setUsableProductNums(String usableProductNums) {
		this.usableProductNums = usableProductNums;
	}

	
}
