package com.linkwee.web.response;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;

public class LcsActivityProfitDetailResp extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1406706891232815896L;
	private Integer id;
	private String name;//理财师名称
	private String activityName;//活动名称
	private Double profit;//活动收益
	private Date startDate;//开始时间
	private Date endDate;//结束时间
	private String issueTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Double getProfit() {
		return profit;
	}
	public void setProfit(Double profit) {
		this.profit = profit;
	}
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getStartDateStr() {
		return startDate==null?"-":DateUtils.format(startDate, DateUtils.FORMAT_LONG);
	}
	public String getEndDateStr() {
		return endDate==null?"-":DateUtils.format(endDate, DateUtils.FORMAT_LONG);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
}
