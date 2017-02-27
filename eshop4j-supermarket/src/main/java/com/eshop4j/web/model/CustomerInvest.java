package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.eshop4j.core.base.BaseEntity;
 import java.lang.Byte;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月03日 15:53:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CustomerInvest extends BaseEntity {
	
	private static final long serialVersionUID = -1442643838535851690L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *用户名
     */
	private String userName;
	
    /**
     *手机号码
     */
	private String mobile;
	
    /**
     *用户来源:0投资者,1理财师,2钱罐子,3众筹
     */
	private Integer userSource;
	
    /**
     *二维码
     */
	private String qrcode;
	
    /**
     *推荐用户
     */
	private String refUser;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date crtTime;
	
    /**
     *修改时间
     */
	private Date modifyTime;
	
    /**
     *状态(0可用,1不可用)
     */
	private Integer status;
	
    /**
     *是否锁定账户 0未锁定 1锁定
     */
	private Byte locked;
	
    /**
     *上传头像图片
     */
	private String image;
	
	/**
	 * 累计收益
	 */
	private Double totalProfit;
	/**
	 * 投资笔数
	 */
	private Double investCount;
	/**
	 * 在投金额
	 */
	private Double investingTotal;
	/**
	 * 投资总额
	 */
	private Double investTotal;
	
	/**
	 * 使用红包总额
	 */
	private Double totalUsedHongbao;
	/**
	 * 其他奖励
	 */
	private Double otherRward;
	
	/**
	 * 最近投资时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date recentDate;


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setUserSource(Integer userSource){
		this.userSource = userSource;
	}
	
	public Integer getUserSource(){
		return userSource;
	}
	
	public void setQrcode(String qrcode){
		this.qrcode = qrcode;
	}
	
	public String getQrcode(){
		return qrcode;
	}
	
	public void setRefUser(String refUser){
		this.refUser = refUser;
	}
	
	public String getRefUser(){
		return refUser;
	}
	
	public void setCrtTime(Date crtTime){
		this.crtTime = crtTime;
	}
	
	public Date getCrtTime(){
		return crtTime;
	}
	
	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}
	
	public Date getModifyTime(){
		return modifyTime;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setLocked(Byte locked){
		this.locked = locked;
	}
	
	public Byte getLocked(){
		return locked;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	
	public String getImage(){
		return image;
	}

	public Double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public Double getInvestCount() {
		return investCount;
	}

	public void setInvestCount(Double investCount) {
		this.investCount = investCount;
	}

	public Double getInvestingTotal() {
		return investingTotal;
	}

	public void setInvestingTotal(Double investingTotal) {
		this.investingTotal = investingTotal;
	}

	public Double getInvestTotal() {
		return investTotal;
	}

	public void setInvestTotal(Double investTotal) {
		this.investTotal = investTotal;
	}

	public Date getRecentDate() {
		return recentDate;
	}

	public void setRecentDate(Date recentDate) {
		this.recentDate = recentDate;
	}

	public Double getOtherRward() {
		return otherRward;
	}

	public void setOtherRward(Double otherRward) {
		this.otherRward = otherRward;
	}

	public Double getTotalUsedHongbao() {
		return totalUsedHongbao;
	}

	public void setTotalUsedHongbao(Double totalUsedHongbao) {
		this.totalUsedHongbao = totalUsedHongbao;
	}
	
}

