package com.eshop4j.web.request.act;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.datatable.DataTable;

public class ProductSearchRequest extends DataTable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4352963953252985383L;
	
	private String name;
	private String orgNumer;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrgNumer() {
		return orgNumer;
	}
	public void setOrgNumer(String orgNumer) {
		this.orgNumer = orgNumer;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
