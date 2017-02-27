package com.eshop4j.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.web.model.CrmInvestor;
import com.eshop4j.xoss.util.WebUtil;

/**
 * 
 * 描述：用户信息
 *
 * @创建人： 何源
 *
 * @时间：2015年12月28日下午3:37:57
 *
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class InvestorResponse extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	public InvestorResponse() {
	}

	public InvestorResponse(CrmInvestor obj) {
		WebUtil.initObj(this,obj);
		if(obj != null && obj.isCfp()) {
			this.setIsCfp("true");
		} else {
			this.setIsCfp("false");
		}
		
	}

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 环信帐号
	 */
	private String easemobAcct;
	/**
	 * 环信密码
	 */
	private String easemobPassword;
	/**
	 * 是否理财师
	 */
	private String isCfp;
	/**
	 * 图片
	 */
	private String headImage;



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

	public String getEasemobPassword() {
		return easemobPassword;
	}

	public void setEasemobPassword(String easemobPassword) {
		this.easemobPassword = easemobPassword;
	}

	public String getIsCfp() {
		return isCfp;
	}

	public void setIsCfp(String isCfp) {
		this.isCfp = isCfp;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}


}
