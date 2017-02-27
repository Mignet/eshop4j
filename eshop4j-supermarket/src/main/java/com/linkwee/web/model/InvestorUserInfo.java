package com.linkwee.web.model;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年12月25日 20:08:35
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class InvestorUserInfo extends BaseEntity {
	
	private static final long serialVersionUID = -6454238333125345993L;
	
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
	private Date crtTime;
	
    /**
     *修改时间
     */
	private Date modifyTime;
	
    /**
     *状态(0可用,1不可用)
     */
	private Integer status;
	
	private String easemobAcct;//环信帐号
	private String easemobPassword;//环信密码
	private int easemobStatus;//环信状态
	private boolean isCfp;//是否理财师 
	


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

	public int getEasemobStatus() {
		return easemobStatus;
	}

	public void setEasemobStatus(int easemobStatus) {
		this.easemobStatus = easemobStatus;
	}

	public boolean isCfp() {
		return isCfp;
	}

	public void setCfp(boolean isCfp) {
		this.isCfp = isCfp;
	}

}

