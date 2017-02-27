package com.linkwee.web.model.product;

/**
 * 用户提现流水
 * @author user
 *
 */
public class WithdrawLogEntry {
	private String outRecordNo; //提现流水号
	private String partnerId; //业务方id
	private String userId; //用户id
	private String userName; //用户姓名
	private String bisName; //业务名称
	private String bisTime; //提现时间
	private String totalAmount; //提现金额
	private String amount; //实际到账金额
	private String fee; //手续费
	private String status; //状态
	public String getOutRecordNo() {
		return outRecordNo;
	}
	public void setOutRecordNo(String outRecordNo) {
		this.outRecordNo = outRecordNo;
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
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "WithdrawLogEntry [outRecordNo=" + outRecordNo + ", partnerId="
				+ partnerId + ", userId=" + userId + ", userName=" + userName
				+ ", bisName=" + bisName + ", bisTime=" + bisTime
				+ ", totalAmount=" + totalAmount + ", amount=" + amount
				+ ", fee=" + fee + ", status=" + status + "]";
	}
}
