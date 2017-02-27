package com.eshop4j.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.crm.MycustomersResp;
import com.eshop4j.xoss.util.WebUtil;

/**
 * 
 * @描述：
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class MycustomersResponse extends BaseEntity {

	private static final long serialVersionUID = 8459996570900278358L;

	public MycustomersResponse(MycustomersResp obj) {
		WebUtil.initObj(this, obj);
		this.setRegisterTime(DateUtils.format(obj.getRegisterTime(), DateUtils.FORMAT_SHORT));
		this.setNearEndDate(DateUtils.format(obj.getNearEndDate(), DateUtils.FORMAT_SHORT));
		this.setNearInvestDate(DateUtils.format(obj.getNearInvestDate(), DateUtils.FORMAT_SHORT));
		if (StringUtils.isBlank(this.getUserName()) || StringUtils.isNumeric(this.getUserName())) {
			this.setUserName("未认证");
		} else {
			String firstLetterCN = this.getUserName().substring(0, 1);
			String firstLetter = PinYin2Abbreviation.cn2py(firstLetterCN);
			this.setFirstLetter(firstLetter);
		}
		this.setImportant(obj.getImportant() != null && obj.getImportant() == 1);
	}

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
	private String nearInvestAmt;

	/**
	 * 注册时间
	 */
	private String registerTime;
	/**
	 * 最近投资日期
	 */
	private String nearInvestDate;

	/**
	 * 到期日期
	 */
	private String nearEndDate;
	/**
	 * 累计投资
	 */
	private String totalInvestAmt;
	/**
	 * 投资笔数
	 */
	private String totalInvestCount;
	/**
	 * 重要客户
	 */
	private boolean important;
	/**
	 * 环信帐号
	 */
	private String easemobAcct;
	
	/**
	 * 是否自由客户 1-是 0-否
	 */
	private String freecustomer;
	
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
	private String isRead;

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}


	public String getNearInvestAmt() {
		return nearInvestAmt;
	}

	public void setNearInvestAmt(String nearInvestAmt) {
		this.nearInvestAmt = nearInvestAmt;
	}

	public String getNearEndDate() {
		return nearEndDate;
	}

	public void setNearEndDate(String nearEndDate) {
		this.nearEndDate = nearEndDate;
	}

	public String getTotalInvestCount() {
		return totalInvestCount;
	}

	public void setTotalInvestCount(String totalInvestCount) {
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

	public String getFreecustomer() {
		return freecustomer;
	}

	public void setFreecustomer(String freecustomer) {
		this.freecustomer = freecustomer;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
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

	public String getNearInvestDate() {
		return nearInvestDate;
	}

	public void setNearInvestDate(String nearInvestDate) {
		this.nearInvestDate = nearInvestDate;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	
	

}
