package com.linkwee.web.model.fee;

import java.sql.Timestamp;

import com.linkwee.core.base.BaseEntity;

public class FeePay extends BaseEntity {
	private static final long serialVersionUID = 6134528000300945751L;
	private int id; //主键
	private String department; //部门
	private String empno; //工号
	private String mobile; //手机号码
	private String name; //姓名
	private double feeamount; //佣金
	private String billnumber; //单据编号
	private String month; //年月
	private String saleuserno; //业务员编码
	private String customerid; //客户id
	private int resultcode; //操作结果码
	private String resultmsg; //结构描述
	private long totaltime; //执行耗时
	private int status; //状态
	private Timestamp createtime; //创建时间
	private String thread_id; //执行的线程id
	private String thread_name; //执行的线程名称
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getFeeamount() {
		return feeamount;
	}
	public void setFeeamount(double feeamount) {
		this.feeamount = feeamount;
	}
	public String getBillnumber() {
		return billnumber;
	}
	public void setBillnumber(String billnumber) {
		this.billnumber = billnumber;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getSaleuserno() {
		return saleuserno;
	}
	public void setSaleuserno(String saleuserno) {
		this.saleuserno = saleuserno;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public int getResultcode() {
		return resultcode;
	}
	public void setResultcode(int resultcode) {
		this.resultcode = resultcode;
	}
	public String getResultmsg() {
		return resultmsg;
	}
	public void setResultmsg(String resultmsg) {
		this.resultmsg = resultmsg;
	}
	public long getTotaltime() {
		return totaltime;
	}
	public void setTotaltime(long totaltime) {
		this.totaltime = totaltime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public String getThread_id() {
		return thread_id;
	}
	public void setThread_id(String thread_id) {
		this.thread_id = thread_id;
	}
	public String getThread_name() {
		return thread_name;
	}
	public void setThread_name(String thread_name) {
		this.thread_name = thread_name;
	}
}