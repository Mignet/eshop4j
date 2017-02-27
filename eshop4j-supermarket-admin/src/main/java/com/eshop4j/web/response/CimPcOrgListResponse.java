package com.eshop4j.web.response;

import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.core.util.WebUtil;
import com.eshop4j.web.model.CimOrginfo;



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
		//产品期限拼接处理
		if (cimOrginfo.getMinDeadLine() != null  && cimOrginfo.getMaxDeadLine() != null && cimOrginfo.getMinDeadLine().equals(cimOrginfo.getMaxDeadLine())){
			if(StringUtils.isNotBlank(cimOrginfo.getDeadLineMinSelfDefined())){
				this.deadLineValueText = cimOrginfo.getDeadLineMinSelfDefined();
			} else {
				this.deadLineValueText = cimOrginfo.getMinDeadLine()+"天";
			}
		} else {
			if(StringUtils.isNotBlank(cimOrginfo.getDeadLineMinSelfDefined()) && StringUtils.isNotBlank(cimOrginfo.getDeadLineMaxSelfDefined())){
				this.deadLineValueText = cimOrginfo.getDeadLineMinSelfDefined()+"~"+cimOrginfo.getDeadLineMaxSelfDefined();
			} else {
				this.deadLineValueText = cimOrginfo.getMinDeadLine()+"天~"+cimOrginfo.getMaxDeadLine()+"天";
			}
		}
		
		this.deadLineValueText =  StringUtils.separateNumberChinese(this.deadLineValueText, ",");
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
	
	
	
	public String getUsableProductNums() {
		return usableProductNums;
	}

	public void setUsableProductNums(String usableProductNums) {
		this.usableProductNums = usableProductNums;
	}

	public String getDeadLineValueText() {
		/*
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
		*/
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
