package com.eshop4j.web.response.orgInfo;

import java.util.List;

import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.WebUtil;
import com.eshop4j.web.model.ActivityList;
import com.eshop4j.web.model.CimOrginfo;


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
		 if(cimOrginfo.isHashRedpacket()){
			 this.setHashRedpacket(true);
		 }else{
			 this.setHashRedpacket(false);
		 }
		 this.setPlatformIco(cimOrginfo.getPlatformIco()); //平台列表logo
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
		 this.setOrgActivitys(cimOrginfo.getOrgActivitys()); //机构活动宣传图
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
	
    /**
     *机构亮点介绍(多个以英文逗号分隔)
     */
	private String orgAdvantage;
	
    /**
     *机构标签(多个以英文逗号分隔)
     */
	private String orgTag;
	
	
	/**
	 * 机构活动宣传图
	 */
	private List<ActivityList> orgActivitys;
	
	
	/**
	 * 猎财端平台自定义标签(多个以英文逗号分隔)
	 */
	private String orgPlannerTag;
	
	
	/**
     *产品自定义标签(1.2版兼容多个以英文逗号分隔)
     */
	private String orgProductTag;
	
	
	/**
	 * 是否有红包
	 */
	private boolean hashRedpacket;
	
	
	
	
	
	public boolean isHashRedpacket() {
		return hashRedpacket;
	}

	public void setHashRedpacket(boolean hashRedpacket) {
		this.hashRedpacket = hashRedpacket;
	}

	public String getOrgProductTag() {
		return orgProductTag;
	}

	public void setOrgProductTag(String orgProductTag) {
		this.orgProductTag = orgProductTag;
	}

	public String getOrgPlannerTag() {
		return orgPlannerTag;
	}

	public void setOrgPlannerTag(String orgPlannerTag) {
		this.orgPlannerTag = orgPlannerTag;
	}

	
	
	public List<ActivityList> getOrgActivitys() {
		return orgActivitys;
	}

	public void setOrgActivitys(List<ActivityList> orgActivitys) {
		this.orgActivitys = orgActivitys;
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
