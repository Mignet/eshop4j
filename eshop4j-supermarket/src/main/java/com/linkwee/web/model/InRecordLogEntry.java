package com.linkwee.web.model;

/**
 * 用户充值流水
 * @author user
 *
 */
public class InRecordLogEntry {
	private String inRecordNo; //充值流水号
	private String partnerId; //业务ID
	private String userId; //用户ID
	private String userName; //用户名
	private String bisType; //充值类型
	private String bisName; //名称
	private String bisTime; //充值时间
	private String amount; //充值金额
	private String status; //充值状态
	public String getInRecordNo() {
		return inRecordNo;
	}
	public void setInRecordNo(String inRecordNo) {
		this.inRecordNo = inRecordNo;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
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
	public String getBisType() {
		return bisType;
	}
	public void setBisType(String bisType) {
		this.bisType = bisType;
	}
	public String getBisName() {
		return bisName;
	}
	public void setBisName(String bisName) {
		this.bisName = bisName;
	}
	public String getBisTime() {
		return bisTime;
	}
	public void setBisTime(String bisTime) {
		this.bisTime = bisTime;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "InRecordLogEntry [inRecordNo=" + inRecordNo + ", partnerId="
				+ partnerId + ", userId=" + userId + ", userName=" + userName
				+ ", bisType=" + bisType + ", bisName=" + bisName
				+ ", bisTime=" + bisTime + ", amount=" + amount + ", status="
				+ status + "]";
	}
}
