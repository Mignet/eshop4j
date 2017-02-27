package com.linkwee.web.response;

import org.apache.commons.lang.StringUtils;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.CimOrginfo;



/**
 * 
 * 描述：PC端优质机构
 * @author yalin
 * @date 2016年9月5日 下午3:04:28 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public class CimPcOrgListResponse extends BaseEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2620572748420702032L;

	public CimPcOrgListResponse() {}
	
	public CimPcOrgListResponse(CimOrginfo cimOrginfo) {
		WebUtil.initObj(this,cimOrginfo);
		this.orgName = cimOrginfo.getName();
		this.orgLogo = cimOrginfo.getPlatformlistIco();
		if(cimOrginfo.getUsableProductNums().equals(0)){ //可投产品数
			 this.setUsableProductNums("待发布");
		  }else{
			 this.setUsableProductNums(WebUtil.getDefaultFormat(cimOrginfo.getUsableProductNums())); 
		    }
		if(StringUtils.isBlank(cimOrginfo.getOrgAdvertisement())){
			this.setOrgAdvertisement("敬请期待");
		}
		this.minProfit = WebUtil.getDefaultFormat(cimOrginfo.getMinProfit());
		this.maxProfit = WebUtil.getDefaultFormat(cimOrginfo.getMaxProfit());
		
	}
	/**
	 * 机构图片logo
	 */
	private String orgLogo;//platformlistIco
	
    /**
     *机构编码-不重复字段
     */
	private String orgNumber;
	
    /**
     *机构名称
     */
	private String orgName;
	
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
	 * 平台主页logo(PC端)
	 */
	private String pcPlatformImg;
	
	/**
	 * 平台列表logo(PC端)
	 */
	private String pcPlatformListImg;
	
	/**
	 * 平台详情图片(PC端)
	 */
	private String pcPlatformDetailImg;
	
	
	
	
	public String getPcPlatformImg() {
		return pcPlatformImg;
	}

	public void setPcPlatformImg(String pcPlatformImg) {
		this.pcPlatformImg = pcPlatformImg;
	}

	public String getPcPlatformListImg() {
		return pcPlatformListImg;
	}

	public void setPcPlatformListImg(String pcPlatformListImg) {
		this.pcPlatformListImg = pcPlatformListImg;
	}

	public String getPcPlatformDetailImg() {
		return pcPlatformDetailImg;
	}

	public void setPcPlatformDetailImg(String pcPlatformDetailImg) {
		this.pcPlatformDetailImg = pcPlatformDetailImg;
	}

	public String getOrgAdvertisementUrl() {
		return orgAdvertisementUrl;
	}

	public void setOrgAdvertisementUrl(String orgAdvertisementUrl) {
		this.orgAdvertisementUrl = orgAdvertisementUrl;
	}

	public String getUsableProductNums() {
		return usableProductNums;
	}

	public void setUsableProductNums(String usableProductNums) {
		this.usableProductNums = usableProductNums;
	}

	public String getDeadLineValueText() {
		return deadLineValueText;
	}

	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public String getOrgAdvertisement() {
		return orgAdvertisement;
	}

	public void setOrgAdvertisement(String orgAdvertisement) {
		this.orgAdvertisement = orgAdvertisement;
	}
	
	
}
