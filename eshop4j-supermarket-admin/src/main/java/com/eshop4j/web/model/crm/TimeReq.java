package com.eshop4j.web.model.crm;

import java.util.Date;

import com.eshop4j.core.base.BaseEntity;

public class TimeReq extends BaseEntity{
	private static final long serialVersionUID = 3276282753870044896L;
	private Date y;//年
	private Date ym;//月
	private Date ymd;//日
	private Date time;//时间
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getY() {
		return y;
	}
	public void setY(Date y) {
		this.y = y;
	}
	public Date getYm() {
		return ym;
	}
	public void setYm(Date ym) {
		this.ym = ym;
	}
	public Date getYmd() {
		return ymd;
	}
	public void setYmd(Date ymd) {
		this.ymd = ymd;
	}
	
	
}
