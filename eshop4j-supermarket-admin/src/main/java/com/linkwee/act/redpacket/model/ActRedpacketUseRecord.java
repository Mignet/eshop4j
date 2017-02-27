package com.linkwee.act.redpacket.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.math.BigDecimal;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月19日 19:49:49
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActRedpacketUseRecord implements Serializable {
	
	private static final long serialVersionUID = -1557644908071612793L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *红包编号
     */
	private String redpacketId;
	
    /**
     *红包发放编号
     */
	private String redpacketSendId;
	
    /**
     *红包明细编号
     */
	private String redpacketDetailId;
	
    /**
     *充值流水
     */
	private String rechargeId;
	
    /**
     *投资编号
     */
	private String investId;
	
    /**
     *投资金额
     */
	private BigDecimal investMoney;
	
    /**
     *投资产品编号
     */
	private String productId;
	
    /**
     *红包名称
     */
	private String name;
	
    /**
     *红包描述
     */
	private String remark;
	
    /**
     *红包类型 1=投资返现|2=现金红包|3=抵现红包
     */
	private Integer type;
	
    /**
     *红包金额
     */
	private BigDecimal money;
	
    /**
     *用户编号
     */
	private String userId;
	
    /**
     *用户手机
     */
	private String userMobile;
	
    /**
     *用户名称
     */
	private String userName;
	
    /**
     *理财师名称
     */
	private String cfplannerId;
	
    /**
     *理财师手机
     */
	private String cfplannerMobile;
	
    /**
     *理财师姓名
     */
	private String cfplannerName;
	
    /**
     *使用时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date useDate;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createDate;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setRedpacketId(String redpacketId){
		this.redpacketId = redpacketId;
	}
	
	public String getRedpacketId(){
		return redpacketId;
	}
	
	public void setRedpacketSendId(String redpacketSendId){
		this.redpacketSendId = redpacketSendId;
	}
	
	public String getRedpacketSendId(){
		return redpacketSendId;
	}
	
	public void setRedpacketDetailId(String redpacketDetailId){
		this.redpacketDetailId = redpacketDetailId;
	}
	
	public String getRedpacketDetailId(){
		return redpacketDetailId;
	}
	
	public void setRechargeId(String rechargeId){
		this.rechargeId = rechargeId;
	}
	
	public String getRechargeId(){
		return rechargeId;
	}
	
	public void setInvestId(String investId){
		this.investId = investId;
	}
	
	public String getInvestId(){
		return investId;
	}
	
	public void setInvestMoney(BigDecimal investMoney){
		this.investMoney = investMoney;
	}
	
	public BigDecimal getInvestMoney(){
		return investMoney;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
	}
	
	public void setMoney(BigDecimal money){
		this.money = money;
	}
	
	public BigDecimal getMoney(){
		return money;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setUserMobile(String userMobile){
		this.userMobile = userMobile;
	}
	
	public String getUserMobile(){
		return userMobile;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setCfplannerId(String cfplannerId){
		this.cfplannerId = cfplannerId;
	}
	
	public String getCfplannerId(){
		return cfplannerId;
	}
	
	public void setCfplannerMobile(String cfplannerMobile){
		this.cfplannerMobile = cfplannerMobile;
	}
	
	public String getCfplannerMobile(){
		return cfplannerMobile;
	}
	
	public void setCfplannerName(String cfplannerName){
		this.cfplannerName = cfplannerName;
	}
	
	public String getCfplannerName(){
		return cfplannerName;
	}
	
	public void setUseDate(Date useDate){
		this.useDate = useDate;
	}
	
	public Date getUseDate(){
		return useDate;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	public Date getCreateDate(){
		return createDate;
	}
	
}

