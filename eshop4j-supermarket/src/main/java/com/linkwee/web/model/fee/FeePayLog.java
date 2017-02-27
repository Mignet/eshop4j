package com.linkwee.web.model.fee;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;

public class FeePayLog extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private Integer fid;

	private String fDepartment;

	private String fEmpNo;

	private String fMobile;

	private String fName;

	private Double fFeeAmount;

	private String fBillNumber;

	private String fMonth;

	private String fSaleUserNo;

	private String fCustomerId;

	private Integer fResultCode;

	private String fResultMsg;

	private Integer fTotalTime;

	private String fThreadId;

	private String fThreadName;

	private Date fCreateTime;

	public FeePayLog() {
		
	}

	public FeePayLog(FeePay feePay) {
		this.fDepartment=feePay.getDepartment();
		this.fEmpNo=feePay.getEmpno();
		this.fMobile=feePay.getMobile();
		this.fName=feePay.getName();
		this.fFeeAmount=feePay.getFeeamount();
		this.fBillNumber=feePay.getBillnumber();
		this.fMonth=feePay.getMonth();
		this.fSaleUserNo=feePay.getSaleuserno();
		this.fCustomerId=feePay.getCustomerid();
		this.fResultCode=feePay.getResultcode();
		this.fResultMsg=feePay.getResultmsg();
		this.fTotalTime=new Integer(Long.toString(feePay.getTotaltime()));
		this.fCreateTime=new Date();
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getfDepartment() {
		return fDepartment;
	}

	public void setfDepartment(String fDepartment) {
		this.fDepartment = fDepartment == null ? null : fDepartment.trim();
	}

	public String getfEmpNo() {
		return fEmpNo;
	}

	public void setfEmpNo(String fEmpNo) {
		this.fEmpNo = fEmpNo == null ? null : fEmpNo.trim();
	}

	public String getfMobile() {
		return fMobile;
	}

	public void setfMobile(String fMobile) {
		this.fMobile = fMobile == null ? null : fMobile.trim();
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName == null ? null : fName.trim();
	}

	public Double getfFeeAmount() {
		return fFeeAmount;
	}

	public void setfFeeAmount(Double fFeeAmount) {
		this.fFeeAmount = fFeeAmount;
	}

	public String getfBillNumber() {
		return fBillNumber;
	}

	public void setfBillNumber(String fBillNumber) {
		this.fBillNumber = fBillNumber == null ? null : fBillNumber.trim();
	}

	public String getfMonth() {
		return fMonth;
	}

	public void setfMonth(String fMonth) {
		this.fMonth = fMonth == null ? null : fMonth.trim();
	}

	public String getfSaleUserNo() {
		return fSaleUserNo;
	}

	public void setfSaleUserNo(String fSaleUserNo) {
		this.fSaleUserNo = fSaleUserNo == null ? null : fSaleUserNo.trim();
	}

	public String getfCustomerId() {
		return fCustomerId;
	}

	public void setfCustomerId(String fCustomerId) {
		this.fCustomerId = fCustomerId == null ? null : fCustomerId.trim();
	}

	public Integer getfResultCode() {
		return fResultCode;
	}

	public void setfResultCode(Integer fResultCode) {
		this.fResultCode = fResultCode;
	}

	public String getfResultMsg() {
		return fResultMsg;
	}

	public void setfResultMsg(String fResultMsg) {
		this.fResultMsg = fResultMsg == null ? null : fResultMsg.trim();
	}

	public Integer getfTotalTime() {
		return fTotalTime;
	}

	public void setfTotalTime(Integer fTotalTime) {
		this.fTotalTime = fTotalTime;
	}

	public String getfThreadId() {
		return fThreadId;
	}

	public void setfThreadId(String fThreadId) {
		this.fThreadId = fThreadId == null ? null : fThreadId.trim();
	}

	public String getfThreadName() {
		return fThreadName;
	}

	public void setfThreadName(String fThreadName) {
		this.fThreadName = fThreadName == null ? null : fThreadName.trim();
	}

	public Date getfCreateTime() {
		return fCreateTime;
	}

	public void setfCreateTime(Date fCreateTime) {
		this.fCreateTime = fCreateTime;
	}
}