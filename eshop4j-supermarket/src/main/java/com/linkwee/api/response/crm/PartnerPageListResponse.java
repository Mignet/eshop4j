package com.linkwee.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.crm.PartnerPageListResp;
import com.linkwee.xoss.util.WebUtil;

/**
 * 
 * @描述：我的团队-分页
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class PartnerPageListResponse extends BaseEntity {
	private static final long serialVersionUID = 6483914096704371712L;

	public PartnerPageListResponse() {

	}

	public PartnerPageListResponse(PartnerPageListResp obj) {
		WebUtil.initObj(this,obj);
		if(StringUtils.isBlank(this.getUserName())){
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
	private String userNumber;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 注册时间
	 */
	private String registerTime;
	/**
	 * 累计佣金
	 */
	private String totalFee;
	/**
	 * 直接津贴
	 */
	private String allowance;
	/**
	 * 下级人数
	 */
	private String childrenCount;
	/**
	 * 间接津贴
	 */
	private String childrenAllowance;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
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

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getAllowance() {
		return allowance;
	}

	public void setAllowance(String allowance) {
		this.allowance = allowance;
	}

	public String getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(String childrenCount) {
		this.childrenCount = childrenCount;
	}

	public String getChildrenAllowance() {
		return childrenAllowance;
	}

	public void setChildrenAllowance(String childrenAllowance) {
		this.childrenAllowance = childrenAllowance;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
