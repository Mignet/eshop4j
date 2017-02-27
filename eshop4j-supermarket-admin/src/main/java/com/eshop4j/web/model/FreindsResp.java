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
 * Copyright (c) 深圳ESHOP有限公司-版权所有
 */
public class FreindsResp extends BaseEntity {

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
	 * 用户来源:0投资者,1理财师,2钱罐子,3众筹
	 */
	private Integer userSource;

	/**
	 * 二维码
	 */
	private String qrcode;

	/**
	 * 推荐用户
	 */
	private String refUser;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 状态(0可用,1不可用)
	 */
	private Integer status;

	/**
	 * 是否锁定账户 0未锁定 1锁定
	 */
	private Byte locked;

	/**
	 * 上传头像图片
	 */
	private String image;

	/**
	 * 是否认证
	 */
	private String authentication;

	/**
	 * 累计投资金额
	 */
	private Double investAmount;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setUserSource(Integer userSource) {
		this.userSource = userSource;
	}

	public Integer getUserSource() {
		return userSource;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setRefUser(String refUser) {
		this.refUser = refUser;
	}

	public String getRefUser() {
		return refUser;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setLocked(Byte locked) {
		this.locked = locked;
	}

	public Byte getLocked() {
		return locked;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public Double getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(Double investAmount) {
		this.investAmount = investAmount;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
