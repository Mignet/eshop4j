package com.linkwee.web.request;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DataStatisticsRequest implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3549187197373212552L;

	/**
	 * 时间标记
	 */
	private Integer tag;
	
	/**
     *开始时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
	private Date startTime;
	
	/**
     *截止时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
	private Date endTime;
	
	/**
	 * 展示图表横轴日期格式
	 */
	private String dateFormat;

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
}
