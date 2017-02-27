package com.eshop4j.web.model.fee;

import java.sql.Timestamp;

import com.eshop4j.core.base.BaseEntity;


/**
 * 佣金明细实体
 * @author user
 *
 */
public class FeeDetail extends BaseEntity {
	private static final long serialVersionUID = -5700165235140800448L;
	private String billnumber; //单据编号
	private String customername; //客户名称
	private String customermobile; //客户手机号码
	private Timestamp bizdate; //业务时间
	private String bizdateformat; //格式化业务时间
	private float feeamount; //佣金金额
	private String orgnumber; //机构编码
	private String orgname; //机构名称
	private String saleusernumber; //业务员编码
	private String saleusername; //业务员名称
	private String saleusermobile; //业务员手机号码
	private String productname;//客户投资产品名
	private float puramount;//客户投资额
	public String getBillnumber() {
		return billnumber;
	}
	public void setBillnumber(String billnumber) {
		this.billnumber = billnumber;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getCustomermobile() {
		return customermobile;
	}
	public void setCustomermobile(String customermobile) {
		this.customermobile = customermobile;
	}
	public Timestamp getBizdate() {
		return bizdate;
	}
	public void setBizdate(Timestamp bizdate) {
		this.bizdate = bizdate;
	}
	public String getBizdateformat() {
		return bizdateformat;
	}
	public void setBizdateformat(String bizdateformat) {
		this.bizdateformat = bizdateformat;
	}
	public float getFeeamount() {
		return feeamount;
	}
	public void setFeeamount(float feeamount) {
		this.feeamount = feeamount;
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
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public float getPuramount() {
		return puramount;
	}
	public void setPuramount(float puramount) {
		this.puramount = puramount;
	}
}