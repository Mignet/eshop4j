package com.eshop4j.web.model.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.datatable.DataTable;

public class SimpleProductReq extends DataTable{
	
	private String pname;
	private Integer pdeadline;
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public Integer getPdeadline() {
		return pdeadline;
	}
	public void setPdeadline(Integer pdeadline) {
		this.pdeadline = pdeadline;
	}
	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
