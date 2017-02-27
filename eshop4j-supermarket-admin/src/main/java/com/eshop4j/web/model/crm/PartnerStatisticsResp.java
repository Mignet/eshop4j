package com.eshop4j.web.model.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;

/**
 * 
 * @描述：我的团队
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class PartnerStatisticsResp extends BaseEntity {

	private static final long serialVersionUID = -5562583093829018629L;

	/**
	 * 直接推荐
	 */
	private Integer myRcCount;
	/**
	 * 间接推荐
	 */
	private Integer childrenRcCount;

	public Integer getMyRcCount() {
		return myRcCount;
	}

	public void setMyRcCount(Integer myRcCount) {
		this.myRcCount = myRcCount;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public Integer getChildrenRcCount() {
		return childrenRcCount;
	}

	public void setChildrenRcCount(Integer childrenRcCount) {
		this.childrenRcCount = childrenRcCount;
	}

}
