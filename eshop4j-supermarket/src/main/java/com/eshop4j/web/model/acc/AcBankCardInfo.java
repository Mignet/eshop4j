package com.eshop4j.web.model.acc;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcBankCardInfo implements Serializable {
	
	private static final long serialVersionUID = 94163472249393581L;
	
    /**
     *自增id
     */
	private Integer id;
	
    /**
     *银行卡账户ID
     */
	private String bankCardId;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *用户姓名
     */
	private String userName;
	
    /**
     *手机号码
     */
	private String mobile;
	
    /**
     *银行卡号
     */
	private String bankCard;
	
    /**
     *银行短卡号
     */
	private String bankCardShortNumber;
	
    /**
     *银行编码
     */
	private String bankCode;
	
    /**
     *银行名称
     */
	private String bankName;
	
    /**
     *城市
     */
	private String city;
	
    /**
     *开户行
     */
	private String kaiHuHang;
	
    /**
     *身份证号
     */
	private String idCard;
	
    /**
     *提现标记
     */
	private String settleFlag;
	
    /**
     *关联银行Id
     */
	private Integer bankId;
	
    /**
     *支付token
     */
	private String payToken;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date lastUpdateTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setBankCardId(String bankCardId){
		this.bankCardId = bankCardId;
	}
	
	public String getBankCardId(){
		return bankCardId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setBankCard(String bankCard){
		this.bankCard = bankCard;
	}
	
	public String getBankCard(){
		return bankCard;
	}
	
	public void setBankCardShortNumber(String bankCardShortNumber){
		this.bankCardShortNumber = bankCardShortNumber;
	}
	
	public String getBankCardShortNumber(){
		return bankCardShortNumber;
	}
	
	public void setBankCode(String bankCode){
		this.bankCode = bankCode;
	}
	
	public String getBankCode(){
		return bankCode;
	}
	
	public void setBankName(String bankName){
		this.bankName = bankName;
	}
	
	public String getBankName(){
		return bankName;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setKaiHuHang(String kaiHuHang){
		this.kaiHuHang = kaiHuHang;
	}
	
	public String getKaiHuHang(){
		return kaiHuHang;
	}
	
	public void setIdCard(String idCard){
		this.idCard = idCard;
	}
	
	public String getIdCard(){
		return idCard;
	}
	
	public void setSettleFlag(String settleFlag){
		this.settleFlag = settleFlag;
	}
	
	public String getSettleFlag(){
		return settleFlag;
	}
	
	public void setBankId(Integer bankId){
		this.bankId = bankId;
	}
	
	public Integer getBankId(){
		return bankId;
	}
	
	public void setPayToken(String payToken){
		this.payToken = payToken;
	}
	
	public String getPayToken(){
		return payToken;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setLastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public Date getLastUpdateTime(){
		return lastUpdateTime;
	}
	
}

