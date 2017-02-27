package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Byte;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 投资者实体
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmInvestor implements Serializable {
	
	private static final long serialVersionUID = -1902591082825503619L;
	
    /**
     *流水号
     */
	private Integer id;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *手机号码
     */
	private String mobile;
	
    /**
     *二维码
     */
	private String qrcode;
	
    /**
     *当前理财师
     */
	private String cfplanner;
	
    /**
     *推荐类型1理财师邀请2客户邀请
     */
	private Byte refType;
	
    /**
     *推荐用户
     */
	private String refUser;
	
    /**
     *头像图片url
     */
	private String headImage;
	
    /**
     *最近访问时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date rectVisitTime;
	 /**
     *最近投资时间
     */
	private Date rectInvestTime;
	 /**
     *首次投资时间
     */
	private Date firstInvestTime;
    /**
     *是否重要客户1是，0否
     */
	private Byte isImportant;
	
    /**
     *是否自由用户1是0否
     */
	private Byte isFreeCustomer;
	/**
	 * 注册来源地址
	 */
	private String registerFromUrl;
	
	/**
	 * 注册受访地址
	 */
	private String registerAccessUrl;
	/**
	 * 环信帐号
	 */
	private String easemobAcct;
	
	/**
	 * 环信密码
	 */
	private String easemobPassword;
	
	/**
	 * 环信注册状态 0未注册，1已注册
	 */
	private int easemobRegStatus;
	
	/**
	 * 环信昵称设置 0未注册，1已注册
	 */
	private int easemobNicknameStatus;
	
    /**
     *是否锁定账户 0未锁定 1锁定
     */
	private Byte isLocked;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *状态(0可用,1不可用)
     */
	private Integer delStatus;
	
	
	/**
     *姓名
     */
	private String userName;
	/**
	 * 是否为理财师
	 */
	private boolean isCfp;
	
	/**
     *微信openId
     */
	private String weiXinOpenId;


	public String getWeiXinOpenId() {
		return weiXinOpenId;
	}

	public void setWeiXinOpenId(String weiXinOpenId) {
		this.weiXinOpenId = weiXinOpenId;
	}

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
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setQrcode(String qrcode){
		this.qrcode = qrcode;
	}
	
	public String getQrcode(){
		return qrcode;
	}
	
	public void setCfplanner(String cfplanner){
		this.cfplanner = cfplanner;
	}
	
	public String getCfplanner(){
		return cfplanner;
	}
	
	public void setRefType(Byte refType){
		this.refType = refType;
	}
	
	public Byte getRefType(){
		return refType;
	}
	
	public void setRefUser(String refUser){
		this.refUser = refUser;
	}
	
	public String getRefUser(){
		return refUser;
	}
	
	public void setHeadImage(String headImage){
		this.headImage = headImage;
	}
	
	public String getHeadImage(){
		return headImage;
	}
	
	public void setIsImportant(Byte isImportant){
		this.isImportant = isImportant;
	}
	
	public Byte getIsImportant(){
		return isImportant;
	}
	
	public void setIsFreeCustomer(Byte isFreeCustomer){
		this.isFreeCustomer = isFreeCustomer;
	}
	
	public Byte getIsFreeCustomer(){
		return isFreeCustomer;
	}
	
	public void setIsLocked(Byte isLocked){
		this.isLocked = isLocked;
	}
	
	public Byte getIsLocked(){
		return isLocked;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setDelStatus(Integer delStatus){
		this.delStatus = delStatus;
	}
	
	public Integer getDelStatus(){
		return delStatus;
	}

	public String getEasemobAcct() {
		return easemobAcct;
	}

	public void setEasemobAcct(String easemobAcct) {
		this.easemobAcct = easemobAcct;
	}

	public String getEasemobPassword() {
		return easemobPassword;
	}

	public void setEasemobPassword(String easemobPassword) {
		this.easemobPassword = easemobPassword;
	}

	public int getEasemobRegStatus() {
		return easemobRegStatus;
	}

	public void setEasemobRegStatus(int easemobRegStatus) {
		this.easemobRegStatus = easemobRegStatus;
	}

	public int getEasemobNicknameStatus() {
		return easemobNicknameStatus;
	}

	public void setEasemobNicknameStatus(int easemobNicknameStatus) {
		this.easemobNicknameStatus = easemobNicknameStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isCfp() {
		return isCfp;
	}

	public void setCfp(boolean isCfp) {
		this.isCfp = isCfp;
	}

	public Date getRectVisitTime() {
		return rectVisitTime;
	}

	public void setRectVisitTime(Date rectVisitTime) {
		this.rectVisitTime = rectVisitTime;
	}

	public Date getRectInvestTime() {
		return rectInvestTime;
	}

	public void setRectInvestTime(Date rectInvestTime) {
		this.rectInvestTime = rectInvestTime;
	}

	public Date getFirstInvestTime() {
		return firstInvestTime;
	}

	public void setFirstInvestTime(Date firstInvestTime) {
		this.firstInvestTime = firstInvestTime;
	}


	public String getRegisterFromUrl() {
		return registerFromUrl;
	}

	public void setRegisterFromUrl(String registerFromUrl) {
		this.registerFromUrl = registerFromUrl;
	}

	public String getRegisterAccessUrl() {
		return registerAccessUrl;
	}

	public void setRegisterAccessUrl(String registerAccessUrl) {
		this.registerAccessUrl = registerAccessUrl;
	}


	
}

