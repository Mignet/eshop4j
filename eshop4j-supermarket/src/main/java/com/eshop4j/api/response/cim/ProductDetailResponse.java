package com.eshop4j.api.response.cim;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.eshop4j.core.util.EnumUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.enums.cim.RepaymentWayEnum;
import com.eshop4j.web.model.CimProduct;
import com.eshop4j.web.model.cim.OrgInfo;


public class ProductDetailResponse extends CimProduct{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	产品总额度
	 */
	private double  buyedTotalMoney;
	/**
	 * 产品已投资人数
	 */
	private Integer  buyedTotalPeople;
	/**
	 * 平台信息
	 */
	private OrgInfo orgInfoResponse;
	/**
	 * 是否理财师推荐	0-未推荐 非0-已推荐
	 */
	private String cfpRecommend;
	/**
	 * 还本付息方式 名称
	 */
	private String repaymentWayText;
	/**
	 * 产品期限(30,天,6,个月)
	 */
	private String deadLineValueText;
	/**
	 * 产品期限 (30天~6个月)
	 */
	private String deadLineValueNewText;
	/**
	 * 产品利率Text
	 */
	private String productRateText;
	/**
	 * 标签列表
	 */
	private ArrayList<String> tagList;
	/**
	 * 标签列表-右上角
	 */
	private ArrayList<String> tagListRight;
	/**
	 * 标签列表-右上角新手标（区分样式）
	 */
	private ArrayList<String> tagListRightNewer;
    /**
     *系统当前时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date timeNow;
	/**
	 * 产品收益描述
	 */
	private String productProfitDesc;
	/**
	 * 能够使用的红包数量
	 */
	private Integer couldUseRedPacketCounts;
	
	public double getBuyedTotalMoney() {
		if(buyedTotalMoney > getBuyTotalMoney().doubleValue()) {
			//修复卖超显示错误
			buyedTotalMoney =  getBuyTotalMoney().doubleValue();
		}
		return buyedTotalMoney;
	}

	public void setBuyedTotalMoney(double buyedTotalMoney) {
		this.buyedTotalMoney = buyedTotalMoney;
	}

	public Integer getBuyedTotalPeople() {
		return buyedTotalPeople;
	}

	public void setBuyedTotalPeople(Integer buyedTotalPeople) {
		this.buyedTotalPeople = buyedTotalPeople;
	}

	public OrgInfo getOrgInfoResponse() {
		return orgInfoResponse;
	}

	public void setOrgInfoResponse(OrgInfo orgInfoResponse) {
		this.orgInfoResponse = orgInfoResponse;
	}

	public String getCfpRecommend() {
		return cfpRecommend;
	}

	public void setCfpRecommend(String cfpRecommend) {
		this.cfpRecommend = cfpRecommend;
	}

	public String getRepaymentWayText() {
		repaymentWayText = EnumUtils.getValueByKeyNull(getRepaymentWay(), RepaymentWayEnum.values());
		return repaymentWayText;
	}

	public void setRepaymentWayText(String repaymentWayText) {
		this.repaymentWayText = repaymentWayText;
	}

	public String getDeadLineValueText() {
		if (getIsFixedDeadline() == 1){
			if(StringUtils.isNotBlank(getDeadLineMinSelfDefined())){
				deadLineValueText = getDeadLineMinSelfDefined();
			} else {
				deadLineValueText = getDeadLineMinValue()+"天";
			}
		} else {
			if(StringUtils.isNotBlank(getDeadLineMinSelfDefined()) && StringUtils.isNotBlank(getDeadLineMaxSelfDefined())){
				deadLineValueText = getDeadLineMinSelfDefined()+"~"+getDeadLineMaxSelfDefined();
			} else {
				deadLineValueText = getDeadLineMinValue()+"天~"+getDeadLineMaxValue()+"天";
			}
		}
		return StringUtils.separateNumberChinese(deadLineValueText, ",");
	}

	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
	}
	public String getDeadLineValueNewText() {
		if (getIsFixedDeadline() == 1){
			if(StringUtils.isNotBlank(getDeadLineMinSelfDefined())){
				deadLineValueNewText = getDeadLineMinSelfDefined();
			} else {
				deadLineValueNewText = getDeadLineMinValue()+"天";
			}
		} else {
			if(StringUtils.isNotBlank(getDeadLineMinSelfDefined()) && StringUtils.isNotBlank(getDeadLineMaxSelfDefined())){
				deadLineValueNewText = getDeadLineMinSelfDefined()+"~"+getDeadLineMaxSelfDefined();
			} else {
				deadLineValueNewText = getDeadLineMinValue()+"天~"+getDeadLineMaxValue()+"天";
			}
		}
		return deadLineValueNewText;
	}

	public void setDeadLineValueNewText(String deadLineValueNewText) {
		this.deadLineValueNewText = deadLineValueNewText;
	}

	public String getProductRateText() {
		if(getIsFlow() == 1){
			productRateText = getFlowMinRate()+"%";
		} else if(getIsFlow() == 2){
			productRateText = getFlowMinRate()+"%~"+getFlowMaxRate()+"%";
		}
		return productRateText;
	}

	public void setProductRateText(String productRateText) {
		this.productRateText = productRateText;
	}

	public ArrayList<String> getTagList() {
		return tagList;
	}

	public void setTagList(ArrayList<String> tagList) {
		this.tagList = tagList;
	}

	public ArrayList<String> getTagListRight() {
		tagListRight = new ArrayList<String>();
		if(orgInfoResponse != null && orgInfoResponse.getOrgFeeType() != null){
			if(orgInfoResponse.getOrgFeeType() == 1){
				tagListRight.add("首投");
			} else if (orgInfoResponse.getOrgFeeType() == 2){
				tagListRight.add("复投");
			}
		}
		return tagListRight;
	}

	public void setTagListRight(ArrayList<String> tagListRight) {
		this.tagListRight = tagListRight;
	}

	public ArrayList<String> getTagListRightNewer() {
		return tagListRightNewer;
	}

	public void setTagListRightNewer(ArrayList<String> tagListRightNewer) {
		this.tagListRightNewer = tagListRightNewer;
	}

	public Date getTimeNow() {
		timeNow = new Date();
		return timeNow;
	}

	public void setTimeNow(Date timeNow) {
		this.timeNow = timeNow;
	}

	public String getProductProfitDesc() {
		return productProfitDesc;
	}

	public void setProductProfitDesc(String productProfitDesc) {
		this.productProfitDesc = productProfitDesc;
	}

	public Integer getCouldUseRedPacketCounts() {
		return couldUseRedPacketCounts;
	}

	public void setCouldUseRedPacketCounts(Integer couldUseRedPacketCounts) {
		this.couldUseRedPacketCounts = couldUseRedPacketCounts;
	}
	
}
