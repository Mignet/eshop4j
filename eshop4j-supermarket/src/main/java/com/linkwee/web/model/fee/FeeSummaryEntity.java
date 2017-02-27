package com.linkwee.web.model.fee;

import com.linkwee.core.base.BaseEntity;

public class FeeSummaryEntity extends BaseEntity {
	private static final long serialVersionUID = 464503681380079751L;
	private String billnumber; //单据编号
	private String saleusernumber; //业务员编码
	private String saleusername; //业务员姓名
	private String saleusermobile; //业务员手机号码
	private String orgnumber; //机构编码
	private String orgname; //机构名称
	private float feeamount; //佣金总额
	private int recommendpeople; //推荐用户数
	private float recommendinvest; //推荐客户投资金额
	private String month; //年月
	private int billstatus; //状态
	public String getBillnumber() {
		return billnumber;
	}
	public void setBillnumber(String billnumber) {
		this.billnumber = billnumber;
	}
	public String getSaleusernumber() {
		return saleusernumber;
	}
	public void setSaleusernumber(String saleusernumber) {
		this.saleusernumber = saleusernumber;
	}
	public String getSaleusername() {
		return saleusername;
	}
	public void setSaleusername(String saleusername) {
		this.saleusername = saleusername;
	}
	public String getSaleusermobile() {
		return saleusermobile;
	}
	public void setSaleusermobile(String saleusermobile) {
		this.saleusermobile = saleusermobile;
	}
	public String getOrgnumber() {
		return orgnumber;
	}
	public void setOrgnumber(String orgnumber) {
		this.orgnumber = orgnumber;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public float getFeeamount() {
		return feeamount;
	}
	public void setFeeamount(float feeamount) {
		this.feeamount = feeamount;
	}
	public int getRecommendpeople() {
		return recommendpeople;
	}
	public void setRecommendpeople(int recommendpeople) {
		this.recommendpeople = recommendpeople;
	}
	public float getRecommendinvest() {
		return recommendinvest;
	}
	public void setRecommendinvest(float recommendinvest) {
		this.recommendinvest = recommendinvest;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getBillstatus() {
		return billstatus;
	}
	public void setBillstatus(int billstatus) {
		this.billstatus = billstatus;
	}
}