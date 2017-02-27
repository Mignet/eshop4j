package com.linkwee.web.request.act;

import java.util.Date;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.xoss.helper.DateUtils;

public class RedpacketStatisticsRequest extends DataTable{
	
	
	private String date = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
