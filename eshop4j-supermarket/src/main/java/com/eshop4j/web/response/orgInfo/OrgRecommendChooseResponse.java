package com.eshop4j.web.response.orgInfo;

import java.util.List;

import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.web.model.CrmInvestorRecommend;

public class OrgRecommendChooseResponse extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 是否虚拟机构(未技术对接,1：是 ,0：否)
	 */
	private Integer orgIsstaticproduct;
	/**
	 * 拥有佣金的投资人
	 */
	private List<CrmInvestorRecommend> haveFeeList;
	/**
	 * 无佣金的投资人
	 */
	private List<CrmInvestorRecommend> notHaveFeeList;
	/**
	 * 未对接平台所有投资人
	 */
	private List<CrmInvestorRecommend> allFeeList;
	
	public Integer getOrgIsstaticproduct() {
		return orgIsstaticproduct;
	}
	public void setOrgIsstaticproduct(Integer orgIsstaticproduct) {
		this.orgIsstaticproduct = orgIsstaticproduct;
	}
	public List<CrmInvestorRecommend> getHaveFeeList() {
		return haveFeeList;
	}
	public void setHaveFeeList(List<CrmInvestorRecommend> haveFeeList) {
		this.haveFeeList = haveFeeList;
	}
	public List<CrmInvestorRecommend> getNotHaveFeeList() {
		return notHaveFeeList;
	}
	public void setNotHaveFeeList(List<CrmInvestorRecommend> notHaveFeeList) {
		this.notHaveFeeList = notHaveFeeList;
	}
	public List<CrmInvestorRecommend> getAllFeeList() {
		return allFeeList;
	}
	public void setAllFeeList(List<CrmInvestorRecommend> allFeeList) {
		this.allFeeList = allFeeList;
	}
}
