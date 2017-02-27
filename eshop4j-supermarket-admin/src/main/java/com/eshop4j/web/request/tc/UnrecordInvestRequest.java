package com.eshop4j.web.request.tc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.datatable.DataTable;

public class UnrecordInvestRequest extends DataTable{
	
	private Integer status;
	private String mobile;
	private String investorsMobiel;
	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	public String getInvestorsMobiel() {
		return investorsMobiel;
	}

	public void setInvestorsMobiel(String investorsMobiel) {
		this.investorsMobiel = investorsMobiel;
	}

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
