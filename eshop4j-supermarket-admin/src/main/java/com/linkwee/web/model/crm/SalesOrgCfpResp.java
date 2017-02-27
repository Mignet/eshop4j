package com.linkwee.web.model.crm;

import java.math.BigDecimal;
import java.util.Date;

import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述： 理财师销售与收益列表
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月03日 15:53:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SalesOrgCfpResp extends BaseEntity {

	private static final long serialVersionUID = -1442643838535851690L;

	/**
	 * 流水号
	 */
	private Integer id;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 城市
	 */
	private String city;
	
	/**
	 * 累计销售
	 */
	private BigDecimal totalSales;
	/**
	 * 累计佣金
	 */
	private BigDecimal totalfee;
	/**
	 * 本月销售
	 */
	private BigDecimal thisMonthSales;
	/**
	 * 本月佣金
	 */
	private BigDecimal thisMonthtotalfee;
	/**
	 * 客户数
	 */
	private int customerCount;
	
	private Date createTime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public BigDecimal getTotalSales() {
		return totalSales.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public BigDecimal getTotalfee() {
		return totalfee.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setTotalfee(BigDecimal totalfee) {
		this.totalfee = totalfee;
	}
	public BigDecimal getThisMonthSales() {
		return thisMonthSales.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setThisMonthSales(BigDecimal thisMonthSales) {
		this.thisMonthSales = thisMonthSales;
	}
	public BigDecimal getThisMonthtotalfee() {
		return thisMonthtotalfee.setScale(4,BigDecimal.ROUND_DOWN);
	}
	public void setThisMonthtotalfee(BigDecimal thisMonthtotalfee) {
		this.thisMonthtotalfee = thisMonthtotalfee;
	}
	public int getCustomerCount() {
		return customerCount;
	}
	public void setCustomerCount(int customerCount) {
		this.customerCount = customerCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
