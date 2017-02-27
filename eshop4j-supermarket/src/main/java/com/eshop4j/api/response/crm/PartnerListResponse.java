package com.eshop4j.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.model.crm.PartnerListResp;
import com.eshop4j.xoss.util.WebUtil;

/**
 * 
 * @描述：我的团队-分页
 *
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class PartnerListResponse extends BaseEntity {
	private static final long serialVersionUID = 6483914096704371712L;

	public PartnerListResponse() {
	}

	public PartnerListResponse(PartnerListResp obj) {
		WebUtil.initObj(this,obj);
		if(StringUtils.isBlank(this.getUserName()) || isNumeric(this.getUserName())){
			this.setUserName("未认证");
		}
		this.setRegisterTime(DateUtils.format(obj.getRegisterTime(),DateUtils.FORMAT_SHORT));
	}

	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户编码
	 */
	private String userId;
	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 注册时间
	 */
	private String registerTime;
	/**
	 * 直接收益
	 */
	private String allowance;

	/**
	 * 间接收益
	 */
	private String childrenAllowance;

	/**
	 * 下级人数
	 */
	private String childrenCount;
	/**
	 * 是否已读
	 */
	private String isRead;
	/**
	 * 新下级数量
	 */
	private String newSubordinateCount;
	
	/**
	 * 头像
	 */
	private String headImage;
	
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

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getNewSubordinateCount() {
		return newSubordinateCount;
	}

	public void setNewSubordinateCount(String newSubordinateCount) {
		this.newSubordinateCount = newSubordinateCount;
	}

	public void setChildrenCount(String childrenCount) {
		this.childrenCount = childrenCount;
	}

	public String getAllowance() {
		return allowance;
	}

	public void setAllowance(String allowance) {
		this.allowance = allowance;
	}

	public String getChildrenAllowance() {
		return childrenAllowance;
	}

	public void setChildrenAllowance(String childrenAllowance) {
		this.childrenAllowance = childrenAllowance;
	}

	public String getChildrenCount() {
		return childrenCount;
	}
	
	private boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
}
