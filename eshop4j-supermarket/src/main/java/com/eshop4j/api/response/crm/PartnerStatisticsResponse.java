package com.eshop4j.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.web.model.crm.PartnerStatisticsResp;
import com.eshop4j.xoss.util.WebUtil;

/**
 * 
 * @描述：我的团队
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class PartnerStatisticsResponse extends BaseEntity {

	private static final long serialVersionUID = -4738504504992142738L;

	public PartnerStatisticsResponse() {
	}

	public PartnerStatisticsResponse(PartnerStatisticsResp obj) {
		WebUtil.initObj(this, obj);
	}
	/**
	 * 直接推荐
	 */
	private String myRcCount;

	/**
	 * 间接推荐
	 */
	private String childrenRcCount;

	public String getMyRcCount() {
		return myRcCount;
	}

	public void setMyRcCount(String myRcCount) {
		this.myRcCount = myRcCount;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getChildrenRcCount() {
		return childrenRcCount;
	}

	public void setChildrenRcCount(String childrenRcCount) {
		this.childrenRcCount = childrenRcCount;
	}

}
