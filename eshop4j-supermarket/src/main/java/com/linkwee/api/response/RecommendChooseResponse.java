package com.linkwee.api.response;

import java.util.List;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.web.model.CrmInvestor;

public class RecommendChooseResponse extends BaseEntity {

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
	private List<CrmInvestor> haveFeeList;
	/**
	 * 无佣金的投资人
	 */
	private List<CrmInvestor> notHaveFeeList;
	/**
	 * 未对接平台所有投资人
	 */
	private List<CrmInvestor> allFeeList;
	
	public Integer getOrgIsstaticproduct() {
		return orgIsstaticproduct;
	}
	public void setOrgIsstaticproduct(Integer orgIsstaticproduct) {
		this.orgIsstaticproduct = orgIsstaticproduct;
	}
	public List<CrmInvestor> getHaveFeeList() {
		return haveFeeList;
	}
	public void setHaveFeeList(List<CrmInvestor> haveFeeList) {
		this.haveFeeList = haveFeeList;
	}
	public List<CrmInvestor> getNotHaveFeeList() {
		return notHaveFeeList;
	}
	public void setNotHaveFeeList(List<CrmInvestor> notHaveFeeList) {
		this.notHaveFeeList = notHaveFeeList;
	}
	public List<CrmInvestor> getAllFeeList() {
		return allFeeList;
	}
	public void setAllFeeList(List<CrmInvestor> allFeeList) {
		this.allFeeList = allFeeList;
	}
}
