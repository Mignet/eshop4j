package com.eshop4j.web.request.act;

import java.util.Date;

import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.xoss.helper.DateUtils;

public class RedpacketStatisticsRequest extends DataTable{
	
	
	private String date = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
