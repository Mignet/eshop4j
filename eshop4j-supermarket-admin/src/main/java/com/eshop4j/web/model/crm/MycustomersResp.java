package com.eshop4j.web.model.crm;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;

/**
 * @描述：我的客户列表
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class MycustomersResp extends BaseEntity {

	private static final long serialVersionUID = -608452619418393092L;

	/**
	 * 客户id
	 */
	private String userId;
	/**
	 * 客户名称
	 */
	private String userName;

	/**
	 * 客户手机号码
	 */
	private String mobile;

	/**
	 * 最近投资
	 */
	private Double nearInvestAmt;
	
	/**
	 * 注册时间
	 */
	private Date registerTime;
	/**
	 * 最近投资日期
	 */
	private Date nearInvestDate;

	/**
	 * 最近到期日期
	 */
	private Date nearEndDate;
	
	/**
	 * 累计投资
	 */
	private String totalInvestAmt;
	/**
	 * 投资笔数
	 */
	private Integer totalInvestCount;

	/**
	 * 是否总要客户
	 */
	private Integer important;
	
	/**
	 * 环信帐号
	 */
	private String easemobAcct;
	/**
	 * 是否自由客户 1-是 0-否
	 */
	private Integer freecustomer;
	/**
	 * 图片
	 */
	private String headImage;
	
	/**
	 * 姓名首字母
	 */
	private String firstLetter;
	
	/**
	 * 是否已读
	 */
	private int isRead;

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getImportant() {
		return important;
	}

	public void setImportant(Integer important) {
		this.important = important;
	}

	public Date getNearInvestDate() {
		return nearInvestDate;
	}

	public void setNearInvestDate(Date nearInvestDate) {
		this.nearInvestDate = nearInvestDate;
	}

	public Double getNearInvestAmt() {
		return nearInvestAmt;
	}

	public void setNearInvestAmt(Double nearInvestAmt) {
		this.nearInvestAmt = nearInvestAmt;
	}

	public Date getNearEndDate() {
		return nearEndDate;
	}

	public void setNearEndDate(Date nearEndDate) {
		this.nearEndDate = nearEndDate;
	}

	public Integer getTotalInvestCount() {
		return totalInvestCount;
	}

	public void setTotalInvestCount(Integer totalInvestCount) {
		this.totalInvestCount = totalInvestCount;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getEasemobAcct() {
		return easemobAcct;
	}

	public void setEasemobAcct(String easemobAcct) {
		this.easemobAcct = easemobAcct;
	}

	public Integer getFreecustomer() {
		return freecustomer;
	}

	public void setFreecustomer(Integer freecustomer) {
		this.freecustomer = freecustomer;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTotalInvestAmt() {
		return totalInvestAmt;
	}

	public void setTotalInvestAmt(String totalInvestAmt) {
		this.totalInvestAmt = totalInvestAmt;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	
	
}
