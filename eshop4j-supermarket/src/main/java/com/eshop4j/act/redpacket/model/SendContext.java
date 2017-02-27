package com.eshop4j.act.redpacket.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.web.model.CrmUserInfo;

public class SendContext{
	
	private CrmUserInfo userInfo;
	private boolean needSwitch;
	private boolean switching;
	private String switchKey;
	private boolean isMulti;
	private boolean useValue;
	private String redpacektIdKey;
	private String[] redpacektIds;
	private String redpacektSendIdKey;
	private String[] redpacektSendIds;
	
	public SendContext(CrmUserInfo userInfo,String switchKey,boolean isMulti,String redpacektIdKey,String redpacektSendIdKey) {
		super();
		this.userInfo = userInfo;
		this.needSwitch = true;
		this.switchKey = switchKey;
		this.isMulti = isMulti;
		this.redpacektIdKey = redpacektIdKey;
		this.redpacektSendIdKey = redpacektSendIdKey;
	}
	
	public SendContext(CrmUserInfo userInfo,boolean isMulti,String redpacektIdKey, String redpacektSendIdKey) {
		super();
		this.userInfo = userInfo;
		this.isMulti = isMulti;
		this.redpacektIdKey = redpacektIdKey;
		this.redpacektSendIdKey = redpacektSendIdKey;
	}
	
	
	public SendContext(CrmUserInfo userInfo,String[] redpacektIds, String[] redpacektSendIds) {
		super();
		this.userInfo = userInfo;
		this.useValue = true;
		this.redpacektIds = redpacektIds;
		this.redpacektSendIds = redpacektSendIds;
	}
	
	
	public SendContext(CrmUserInfo userInfo,String switchKey, String[] redpacektIds, String[] redpacektSendIds) {
		super();
		this.userInfo = userInfo;
		this.needSwitch = true;
		this.switchKey = switchKey;
		this.useValue = true;
		this.redpacektIds = redpacektIds;
		this.redpacektSendIds = redpacektSendIds;
	}
	
	

	public CrmUserInfo getUserInfo() {
		return userInfo;
	}

	public boolean isNeedSwitch() {
		return needSwitch;
	}

	public boolean isSwitching() {
		return switching;
	}

	public String getSwitchKey() {
		return switchKey;
	}

	public boolean isMulti() {
		return isMulti;
	}

	public boolean isUseValue() {
		return useValue;
	}

	public String getRedpacektIdKey() {
		return redpacektIdKey;
	}

	public String[] getRedpacektIds() {
		return redpacektIds;
	}

	public String getRedpacektSendIdKey() {
		return redpacektSendIdKey;
	}

	public String[] getRedpacektSendIds() {
		return redpacektSendIds;
	}
	
	

	public void setUserInfo(CrmUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public void setNeedSwitch(boolean needSwitch) {
		this.needSwitch = needSwitch;
	}

	public void setSwitching(boolean switching) {
		this.switching = switching;
	}

	public void setSwitchKey(String switchKey) {
		this.switchKey = switchKey;
	}

	public void setMulti(boolean isMulti) {
		this.isMulti = isMulti;
	}

	public void setUseValue(boolean useValue) {
		this.useValue = useValue;
	}

	public void setRedpacektIdKey(String redpacektIdKey) {
		this.redpacektIdKey = redpacektIdKey;
	}

	public void setRedpacektIds(String[] redpacektIds) {
		this.redpacektIds = redpacektIds;
	}

	public void setRedpacektSendIdKey(String redpacektSendIdKey) {
		this.redpacektSendIdKey = redpacektSendIdKey;
	}

	public void setRedpacektSendIds(String[] redpacektSendIds) {
		this.redpacektSendIds = redpacektSendIds;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}	
}
