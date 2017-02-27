package com.linkwee.web.model.crm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年11月03日 15:12:23
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmSalesOrg implements Serializable {
	
	private static final long serialVersionUID = 4874328293229118015L;
	
    /**
     *自增ID
     */
	private Integer id;
	
    /**
     *销售机构编码
     */
	private String salesOrgId;
	
    /**
     *销售机构名称
     */
	private String name;
	
    /**
     *城市
     */
	private String city;
	
    /**
     *法人
     */
	private String legalPerson;
	
    /**
     *联系人
     */
	private String contacts;
	
    /**
     *联系方式
     */
	private String contactMobile;
	
    /**
     *管理账户
     */
	private String managerAccount;
	
    /**
     *登录密码
     */
	private String password;
	
    /**
     *合作时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date cooperationTime;
	
    /**
     *合作状态
     */
	private Integer cooperationStatus;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *最后修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date lastUpdateTime;
	
	/**
	 * 下属理财师数量
	 */
	private int cfplannerCount;
	
	/**
	 * 历史累计销售额
	 */
	private BigDecimal historySales;
	
	/**
	 * 本月销售额
	 */
	private BigDecimal thisMonthSales;
	/**
	 * 本月销佣金
	 */
	private BigDecimal thisMonthFee;
	/**
	 * 上月销售额
	 */
	private BigDecimal lastMonthSales;
	/**
	 * 上月年化销售额
	 */
	private BigDecimal lastMonthYearSales;
	/**
	 * 上月存量销售额
	 */
	private BigDecimal lastMonthStockSales;
	/**
	 * 上月存量年化销售额
	 */
	private BigDecimal lastMonthStockYearSales;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setSalesOrgId(String salesOrgId){
		this.salesOrgId = salesOrgId;
	}
	
	public String getSalesOrgId(){
		return salesOrgId;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setLegalPerson(String legalPerson){
		this.legalPerson = legalPerson;
	}
	
	public String getLegalPerson(){
		return legalPerson;
	}
	
	public void setContacts(String contacts){
		this.contacts = contacts;
	}
	
	public String getContacts(){
		return contacts;
	}
	
	public void setContactMobile(String contactMobile){
		this.contactMobile = contactMobile;
	}
	
	public String getContactMobile(){
		return contactMobile;
	}
	
	public void setManagerAccount(String managerAccount){
		this.managerAccount = managerAccount;
	}
	
	public String getManagerAccount(){
		return managerAccount;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setCooperationTime(Date cooperationTime){
		this.cooperationTime = cooperationTime;
	}
	
	public Date getCooperationTime(){
		return cooperationTime;
	}
	
	public void setCooperationStatus(Integer cooperationStatus){
		this.cooperationStatus = cooperationStatus;
	}
	
	public Integer getCooperationStatus(){
		return cooperationStatus;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setLastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public Date getLastUpdateTime(){
		return lastUpdateTime;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public int getCfplannerCount() {
		return cfplannerCount;
	}

	public void setCfplannerCount(int cfplannerCount) {
		this.cfplannerCount = cfplannerCount;
	}

	public BigDecimal getHistorySales() {
		return historySales.setScale(4,BigDecimal.ROUND_DOWN);
	}

	public void setHistorySales(BigDecimal historySales) {
		this.historySales = historySales;
	}

	public BigDecimal getThisMonthSales() {
		return thisMonthSales.setScale(4,BigDecimal.ROUND_DOWN);
	}

	public void setThisMonthSales(BigDecimal thisMonthSales) {
		this.thisMonthSales = thisMonthSales;
	}

	public BigDecimal getThisMonthFee() {
		return thisMonthFee.setScale(4,BigDecimal.ROUND_DOWN);
	}

	public void setThisMonthFee(BigDecimal thisMonthFee) {
		this.thisMonthFee = thisMonthFee;
	}

	public BigDecimal getLastMonthSales() {
		return lastMonthSales.setScale(4,BigDecimal.ROUND_DOWN);
	}

	public void setLastMonthSales(BigDecimal lastMonthSales) {
		this.lastMonthSales = lastMonthSales;
	}

	public BigDecimal getLastMonthYearSales() {
		return lastMonthYearSales.setScale(4,BigDecimal.ROUND_DOWN);
	}

	public void setLastMonthYearSales(BigDecimal lastMonthYearSales) {
		this.lastMonthYearSales = lastMonthYearSales;
	}

	public BigDecimal getLastMonthStockSales() {
		return lastMonthStockSales.setScale(4,BigDecimal.ROUND_DOWN);
	}

	public void setLastMonthStockSales(BigDecimal lastMonthStockSales) {
		this.lastMonthStockSales = lastMonthStockSales;
	}

	public BigDecimal getLastMonthStockYearSales() {
		return lastMonthStockYearSales.setScale(4,BigDecimal.ROUND_DOWN);
	}

	public void setLastMonthStockYearSales(BigDecimal lastMonthStockYearSales) {
		this.lastMonthStockYearSales = lastMonthStockYearSales;
	}
}

