package com.linkwee.web.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.api.PaginatorRequest;

/**
 * 
 * 描述：机构列表分页筛选请求参数
 * @author yalin
 * @date 2016年9月6日 下午3:07:14 
 * Copyright (c) 深圳市前海领会科技有限公司
 */
public class OrgInfoRequest extends PaginatorRequest {
	
	private static final long serialVersionUID = -6403667546670342519L;
	
	private String securityLevel; //安全评级
	private String yearProfit; //年化收益
	private String productDeadLine; //产品期限
	private String city; //所在城市
	private String background; //机构背景

	
	public String getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}

	public String getYearProfit() {
		return yearProfit;
	}

	public void setYearProfit(String yearProfit) {
		this.yearProfit = yearProfit;
	}

	public String getProductDeadLine() {
		return productDeadLine;
	}

	public void setProductDeadLine(String productDeadLine) {
		this.productDeadLine = productDeadLine;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
