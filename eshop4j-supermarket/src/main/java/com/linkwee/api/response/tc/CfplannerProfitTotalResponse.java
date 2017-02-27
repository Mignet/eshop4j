package com.linkwee.api.response.tc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.BaseEntity;

public class CfplannerProfitTotalResponse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3705182924204989842L;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")   
	private Date minDate;
	
	private BigDecimal dayProfit;
	
	private BigDecimal sumProfit;
	
	private BigDecimal totalProfit;
	
	private List<ProfitItemsTotalResponse> items;

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Double getDayProfit() {
		return dayProfit.setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
	}

	public Double getSumProfit() {
		return sumProfit.setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
	}

	public Double getTotalProfit() {
		return totalProfit.setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
	}

	public List<ProfitItemsTotalResponse> getItems() {
		return items;
	}

	public void setItems(List<ProfitItemsTotalResponse> items) {
		this.items = items;
	}


}
