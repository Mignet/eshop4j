package com.linkwee.api.request.cim;

public class ProductPageListRecommendRequest extends ProductPageListRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 理财师userId
	 */
	private String cfplannerUserId;

	public String getCfplannerUserId() {
		return cfplannerUserId;
	}

	public void setCfplannerUserId(String cfplannerUserId) {
		this.cfplannerUserId = cfplannerUserId;
	}
}
