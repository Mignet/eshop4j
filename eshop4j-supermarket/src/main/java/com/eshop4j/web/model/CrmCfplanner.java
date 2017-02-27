package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Byte;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 理财师实体
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:25:55
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmCfplanner implements Serializable {
	
	private static final long serialVersionUID = 6594565576453713428L;
	
    /**
     *自增id
     */
	private Integer id;
	
    /**
     *理财师编码
     */
	private String number;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *手机号码
     */
	private String mobile;
	
    /**
     *电子邮件
     */
	private String email;
	
    /**
     *上级理财师
     */
	private String parentId;
	
    /**
     *二维码
     */
	private String qrcode;
	
    /**
     *业务员工号
     */
	private String empNo;
	
    /**
     *业务员所属机构
     */
	private String department;
	
    /**
     *佣金入账方式 1：工资账户 2：理财账户
     */
	private Byte settlementType;
	
    /**
     *理财师等级
     */
	private String cfpLevel;
	
    /**
     *理财师注册时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date cfpRegTime;
	
    /**
     *理财师转正时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date cfpBenormalTime;
	
    /**
     *理财师更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date cfpUpdateTime;
	
    /**
     *职级经验数值
     */
	private Integer levelExperience;
	
    /**
     *上传头像图片
     */
	private String headImage;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *禁止登录开始时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date disabledLoginTime;
	/**
	 * 最近访问时间
	 */
	private Date rectVisitTime;
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
     *是否锁定
     */
	private Byte isLocked;
	
    /**
     * 是否启用0：启用 1：禁用
     */
	private Byte enable;
	
    /**
     *删除状态 0：正常 1：删除
     */
	private Byte delStatus;
	
	/**
	 * 销售机构编码
	 */
	private String salesOrgId;
	
	/**
     *姓名
     */
	private String userName;
	
	/**
     *用户微信openId
     */
	private String weiXinOpenId;

	
	public String getWeiXinOpenId() {
		return weiXinOpenId;
	}

	public void setWeiXinOpenId(String weiXinOpenId) {
		this.weiXinOpenId = weiXinOpenId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setNumber(String number){
		this.number = number;
	}
	
	public String getNumber(){
		return number;
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
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setParentId(String parentId){
		this.parentId = parentId;
	}
	
	public String getParentId(){
		return parentId;
	}
	
	public void setQrcode(String qrcode){
		this.qrcode = qrcode;
	}
	
	public String getQrcode(){
		return qrcode;
	}
	
	public void setEmpNo(String empNo){
		this.empNo = empNo;
	}
	
	public String getEmpNo(){
		return empNo;
	}
	
	public void setDepartment(String department){
		this.department = department;
	}
	
	public String getDepartment(){
		return department;
	}
	
	public void setSettlementType(Byte settlementType){
		this.settlementType = settlementType;
	}
	
	public Byte getSettlementType(){
		return settlementType;
	}
	
	public void setCfpLevel(String cfpLevel){
		this.cfpLevel = cfpLevel;
	}
	
	public String getCfpLevel(){
		return cfpLevel;
	}
	
	public void setCfpRegTime(Date cfpRegTime){
		this.cfpRegTime = cfpRegTime;
	}
	
	public Date getCfpRegTime(){
		return cfpRegTime;
	}
	
	public void setCfpBenormalTime(Date cfpBenormalTime){
		this.cfpBenormalTime = cfpBenormalTime;
	}
	
	public Date getCfpBenormalTime(){
		return cfpBenormalTime;
	}
	
	public void setCfpUpdateTime(Date cfpUpdateTime){
		this.cfpUpdateTime = cfpUpdateTime;
	}
	
	public Date getCfpUpdateTime(){
		return cfpUpdateTime;
	}
	
	public void setLevelExperience(Integer levelExperience){
		this.levelExperience = levelExperience;
	}
	
	public Integer getLevelExperience(){
		return levelExperience;
	}
	
	public void setHeadImage(String headImage){
		this.headImage = headImage;
	}
	
	public String getHeadImage(){
		return headImage;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setDisabledLoginTime(Date disabledLoginTime){
		this.disabledLoginTime = disabledLoginTime;
	}
	
	public Date getDisabledLoginTime(){
		return disabledLoginTime;
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
	
	public void setIsLocked(Byte isLocked){
		this.isLocked = isLocked;
	}
	
	public Byte getIsLocked(){
		return isLocked;
	}
	
	public void setEnable(Byte enable){
		this.enable = enable;
	}
	
	public Byte getEnable(){
		return enable;
	}
	
	public void setDelStatus(Byte delStatus){
		this.delStatus = delStatus;
	}
	
	public Byte getDelStatus(){
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

	public Date getRectVisitTime() {
		return rectVisitTime;
	}

	public void setRectVisitTime(Date rectVisitTime) {
		this.rectVisitTime = rectVisitTime;
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

	public String getSalesOrgId() {
		return salesOrgId;
	}

	public void setSalesOrgId(String salesOrgId) {
		this.salesOrgId = salesOrgId;
	}

	
}

