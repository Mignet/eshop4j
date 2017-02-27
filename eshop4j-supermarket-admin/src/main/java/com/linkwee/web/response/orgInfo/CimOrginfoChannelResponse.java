package com.linkwee.web.response.orgInfo;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.CimOrginfo;


/**
 * 
 * 描述：猎财大师端平台返回信息
 * @author yalin
 * @date 2016年7月20日 下午4:14:14 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public class CimOrginfoChannelResponse extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5223481236068856084L;

	public CimOrginfoChannelResponse() {
		
	}
	
	public CimOrginfoChannelResponse(CimOrginfo cimOrginfo) {
		 WebUtil.initObj(this,cimOrginfo);
		 //WebUtil.initObj(this,cimOrginfo,"yyyy-MM-dd hh:mm:ss");
		 this.setPlatformIco(cimOrginfo.getPlatformlistIco()); //平台列表logo
		 if(cimOrginfo.getUsableProductNums().equals(0)){ //可投产品数
			 this.setUsableProductNums("待发布");
		  }else{
			 this.setUsableProductNums(WebUtil.getDefaultFormat(cimOrginfo.getUsableProductNums())); 
		    }
		 this.setMinProfit(WebUtil.getDefaultFormat(cimOrginfo.getMinProfit()));
		 this.setMaxProfit(WebUtil.getDefaultFormat(cimOrginfo.getMaxProfit()));
		 this.setMinDeadLine(WebUtil.getDefaultFormat(cimOrginfo.getMinDeadLine()));
		 this.setMaxDeadLine(WebUtil.getDefaultFormat(cimOrginfo.getMaxDeadLine()));
		 this.setMinFeeRatio(WebUtil.getDefaultFormat(cimOrginfo.getMinFeeRatio()));
		 this.setMaxFeeRatio(WebUtil.getDefaultFormat(cimOrginfo.getMaxFeeRatio()));
		 this.setOrgFeeRatio(WebUtil.getDefaultFormat(cimOrginfo.getOrgFeeRatio()));
		 
		//this.setStartDate(DateUtils.format(obj.getStartDate(), DateUtils.FORMAT_SHORT));
	}
	
    /**
     *机构编码-固定8位编码，不重复字段
     */
	private String orgNumber;
	
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
	 * 机构官网的url
	 */
	private String orgUrl;
	
	/**
	 * 平台下可投的产品数量
	 */
	private String usableProductNums;
	
	
	/**
	 * 最小佣金利率
	 */
	private String minFeeRatio;
	
	/**
	 * 最大佣金利率
	 */
	private String maxFeeRatio;
	
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
     *	机构产品佣金率
     */
	private String orgFeeRatio;
	
	

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
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

	public String getOrgUrl() {
		return orgUrl;
	}

	public void setOrgUrl(String orgUrl) {
		this.orgUrl = orgUrl;
	}

	public String getUsableProductNums() {
		return usableProductNums;
	}

	public void setUsableProductNums(String usableProductNums) {
		this.usableProductNums = usableProductNums;
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
		return deadLineValueText;
	}

	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
	}

	public String getOrgFeeRatio() {
		return orgFeeRatio;
	}

	public void setOrgFeeRatio(String orgFeeRatio) {
		this.orgFeeRatio = orgFeeRatio;
	}
	
	

}
