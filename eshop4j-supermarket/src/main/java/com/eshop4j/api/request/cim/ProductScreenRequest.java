package com.eshop4j.api.request.cim;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.api.PaginatorRequest;

/**
 * 
 * @描述：平台-分页
 *
 * @author chenchy
 * @时间 2016年5月13日上午11:16:39
 *
 */
public class ProductScreenRequest extends PaginatorRequest {

	private static final long serialVersionUID = 149145940711084404L;
	
	private String yearProfit; //年化收益
	private String productDeadLine; //产品期限
	private String platform;
	private String background;
	private String securityLevel;
	
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
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
	
	public String getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
