package com.eshop4j.web.model;

public class CrmInvestorRecommend extends CrmInvestor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 是否理财师推荐   0-否  其他-是
	 */
    private Integer ifRecommend;

	public Integer getIfRecommend() {
		return ifRecommend;
	}

	public void setIfRecommend(Integer ifRecommend) {
		this.ifRecommend = ifRecommend;
	}

}
