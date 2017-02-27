package com.eshop4j.api.request.tc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UnRecordInvestRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6849890793614455417L;
	
	/**
	 * 用户名称
	 */
	@NotBlank(message="用户名称")
	private String name;
	/**
	 * 用户手机
	 */
	@NotBlank(message="手机号码不能为空")
	private String mobile;
	
	 /**
     *平台编号
     */
	@NotBlank(message="平台编号不能为空")
	private String platfrom;
	
    /**
     *平台名称
     */
	@NotBlank(message="平台名称不能为空")
	private String platfromName;
	
	/**
     *投资产品
     */
	@NotBlank(message="产品名称不能为空")
	private String productName;
	
    /**
     *投资编号
     */
	
	private String investId;
	
    /**
     *投资金额
     */
	@NotNull(message="投资金额不能为空")
	private BigDecimal investAmt;
	
	/**
	 * 产品期限类型  1=天|2=月|3=年
	 */
	@NotNull(message="产品期限类型不能为空")
	@Range(min=1,max=3,message="产品期限类型只能是1,2,3")
	private Integer productDeadLineType;
	
	/**
	 * 产品期限
	 */
	@NotNull(message="产品期限不能为空")
	@Min(value=1,message="产品最小期限为1")
	private Integer productDeadLine;
	
	/**
     *投资时间
     */
	@NotNull(message="投资时间不能为空")
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private String investTime;
	
	
    /**
     *投资截图
     */
	@NotNull(message="截图不能为空")
	private String investImg;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPlatfrom() {
		return platfrom;
	}

	public void setPlatfrom(String platfrom) {
		this.platfrom = platfrom;
	}

	public String getPlatfromName() {
		return platfromName;
	}

	public void setPlatfromName(String platfromName) {
		this.platfromName = platfromName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public BigDecimal getInvestAmt() {
		return investAmt;
	}

	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}

	public String getInvestImg() {
		return investImg;
	}

	public void setInvestImg(String investImg) {
		this.investImg = investImg;
	}


	public String getInvestTime() {
		return investTime;
	}

	public void setInvestTime(String investTime) {
		this.investTime = investTime;
	}

	public Integer getProductDeadLineType() {
		return productDeadLineType;
	}

	public void setProductDeadLineType(Integer productDeadLineType) {
		this.productDeadLineType = productDeadLineType;
	}

	public Integer getProductDeadLine() {
		return productDeadLine;
	}

	public void setProductDeadLine(Integer productDeadLine) {
		this.productDeadLine = productDeadLine;
	}
	
	
}
