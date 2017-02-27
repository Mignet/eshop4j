package com.linkwee.web.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.lang.Byte;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
 /**
 * 
 * @描述： 基础用户实体
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmUserInfo implements Serializable {
	
	private static final long serialVersionUID = -438083460521255773L;
	
    /**
     *自增id
     */
	private Integer id;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *用户姓名
     */
	private String userName;
	
    /**
     *电子邮件
     */
	private String email;
	
    /**
     *手机号码
     */
	private String mobile;
	
    /**
     *身份证
     */
	private String idcard;
	
    /**
     *头像
     */
	private byte[] headImage;
	
    /**
     *登录密码,md5加密（user+pwd）
     */
	private String password;
	
    /**
     *手势密码
     */
	private String signPwd;
	
    /**
     *是否启用
     *0：禁用 1：启用
     */
	private Byte enable;
	
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
     *删除状态 0：正常 1：删除
     */
	private Byte delStatus;
	


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
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setIdcard(String idcard){
		this.idcard = idcard;
	}
	
	public String getIdcard(){
		return idcard;
	}
	
	public void setHeadImage(byte[] headImage){
		this.headImage = headImage;
	}
	
	public byte[] getHeadImage(){
		return headImage;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setSignPwd(String signPwd){
		this.signPwd = signPwd;
	}
	
	public String getSignPwd(){
		return signPwd;
	}
	
	public void setEnable(Byte enable){
		this.enable = enable;
	}
	
	public Byte getEnable(){
		return enable;
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
	
	public void setDelStatus(Byte delStatus){
		this.delStatus = delStatus;
	}
	
	public Byte getDelStatus(){
		return delStatus;
	}
	
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	
}

