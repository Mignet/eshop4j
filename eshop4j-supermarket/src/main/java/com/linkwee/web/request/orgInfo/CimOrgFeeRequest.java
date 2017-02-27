package com.linkwee.web.request.orgInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.linkwee.web.model.cim.CimOrgFeeInterval;
 /**
 * 
 * @描述： 收费模式
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月11日 16:27:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgFeeRequest implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 3636370398152543751L;

	/**
     *主键，自增长
     */
	private Integer id;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
	
	/**
	 * 收费类型
	 * cpa
	 */
	private String cpaFeeType;
	
	/**
	 * 收费类型
	 * cps
	 */
	private String cpsFeeType;
	
	/**
	 * cpa收费类型类别
	 * fixed-每个新投资人固定费用(cpa)
	 * float_fixed-首投按投资额区间设定收费(cpa)
	 * propertion-按首投比例计算费用(cpa)
	 */
	private String cpaFeeAttr;
	
	/**
	 * cps收费类型类别
	 * year_propertion-按产品期限设置年化收费比例收费(cps)
	 * month_amount_propertion-按当月销售总额设置年化比例收费(cps)
	 */
	private String cpsFeeAttr;
	
	/**
     *收费额度
     */
	private BigDecimal fixedMoney;
	
    /**
     *收费比例
     */
	private BigDecimal fixedMoneyRatio;
	
	/**
	 * 限投金额(cpa)
	 */
	private BigDecimal orgAmountLimit;
	
	/**
	 * 限投期限(cpa)
	 */
	private Integer orgInvestdeadlineLimit;
	
    /**
     *
     */
	private Date creattime;
	
    /**
     *创建人
     */
	private String creator;
	
    /**
     *更新时间
     */
	private Date updatetime;
	
    /**
     *修改人
     */
	private String updater;
	
    /**
     *备注
     */
	private String remark;
	
	
	/**
	 * 收费区间
	 */
	private List<CimOrgFeeInterval> orgFeeRecords;
	
	
	
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public BigDecimal getOrgAmountLimit() {
		return orgAmountLimit;
	}


	public void setOrgAmountLimit(BigDecimal orgAmountLimit) {
		this.orgAmountLimit = orgAmountLimit;
	}


	public Integer getOrgInvestdeadlineLimit() {
		return orgInvestdeadlineLimit;
	}


	public void setOrgInvestdeadlineLimit(Integer orgInvestdeadlineLimit) {
		this.orgInvestdeadlineLimit = orgInvestdeadlineLimit;
	}


	public String getCpaFeeType() {
		return cpaFeeType;
	}


	public void setCpaFeeType(String cpaFeeType) {
		this.cpaFeeType = cpaFeeType;
	}


	public String getCpsFeeType() {
		return cpsFeeType;
	}


	public void setCpsFeeType(String cpsFeeType) {
		this.cpsFeeType = cpsFeeType;
	}



	public String getCpaFeeAttr() {
		return cpaFeeAttr;
	}


	public void setCpaFeeAttr(String cpaFeeAttr) {
		this.cpaFeeAttr = cpaFeeAttr;
	}


	public String getCpsFeeAttr() {
		return cpsFeeAttr;
	}


	public void setCpsFeeAttr(String cpsFeeAttr) {
		this.cpsFeeAttr = cpsFeeAttr;
	}


	public BigDecimal getFixedMoney() {
		return fixedMoney;
	}


	public void setFixedMoney(BigDecimal fixedMoney) {
		this.fixedMoney = fixedMoney;
	}


	public BigDecimal getFixedMoneyRatio() {
		return fixedMoneyRatio;
	}


	public void setFixedMoneyRatio(BigDecimal fixedMoneyRatio) {
		this.fixedMoneyRatio = fixedMoneyRatio;
	}


	public String getOrgNumber() {
		return orgNumber;
	}


	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}


	
	public Date getCreattime() {
		return creattime;
	}


	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}


	public String getCreator() {
		return creator;
	}


	public void setCreator(String creator) {
		this.creator = creator;
	}


	public Date getUpdatetime() {
		return updatetime;
	}


	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}


	public String getUpdater() {
		return updater;
	}


	public void setUpdater(String updater) {
		this.updater = updater;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public List<CimOrgFeeInterval> getOrgFeeRecords() {
		return orgFeeRecords;
	}


	public void setOrgFeeRecords(List<CimOrgFeeInterval> orgFeeRecords) {
		this.orgFeeRecords = orgFeeRecords;
	}
	
	
	
}

