package com.linkwee.web.model;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年12月25日 15:06:26
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class Usercustomerrel extends BaseEntity {
	
	private static final long serialVersionUID = -2666414032440174411L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *
     */
	private String customerid;
	
    /**
     *
     */
	private String customername;
	
    /**
     *
     */
	private String customermobile;
	
    /**
     *
     */
	private String regbizfrom;
	
    /**
     *
     */
	private Byte regrefereetype;
	
    /**
     *
     */
	private String regrefuser;
	
    /**
     *
     */
	private String regrefcustomer;
	
    /**
     *
     */
	private Date regtime;
	
    /**
     *
     */
	private String currsaleuser;
	
	private String currsaleuserUUID;
	
	private String currSaleUserName;
	
    /**
     *
     */
	private Date rectranstime;
	
    /**
     *
     */
	private Date rectlogintime;
	
    /**
     *
     */
	private Date createtime;
	
    /**
     *
     */
	private Date updatetime;
	
    /**
     *
     */
	private Byte delstatus;
	
    /**
     *是否总要客户：0否；1是
     */
	private Byte important;
	
	/**
	 * 环信帐号
	 */
	private String easemobAcct;
	/**
	 * 是否自由客户 1-是 | 0-否
	 */
	private Integer freecustomer;
	/**
	 * 注册来源
	 */
	private int userSource;
	/**
	 * 来源渠道
	 * @return
	 */
	private String regorgfrom;

	public String getRegorgfrom() {
		return regorgfrom;
	}

	public void setRegorgfrom(String regorgfrom) {
		this.regorgfrom = regorgfrom;
	}

	public int getUserSource() {
		return userSource;
	}

	public void setUserSource(int userSource) {
		this.userSource = userSource;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setCustomerid(String customerid){
		this.customerid = customerid;
	}
	
	public String getCustomerid(){
		return customerid;
	}
	
	public void setCustomername(String customername){
		this.customername = customername;
	}
	
	public String getCustomername(){
		return customername;
	}
	
	public void setCustomermobile(String customermobile){
		this.customermobile = customermobile;
	}
	
	public String getCustomermobile(){
		return customermobile;
	}
	
	public void setRegbizfrom(String regbizfrom){
		this.regbizfrom = regbizfrom;
	}
	
	public String getRegbizfrom(){
		return regbizfrom;
	}
	
	public void setRegrefereetype(Byte regrefereetype){
		this.regrefereetype = regrefereetype;
	}
	
	public Byte getRegrefereetype(){
		return regrefereetype;
	}
	
	public void setRegrefuser(String regrefuser){
		this.regrefuser = regrefuser;
	}
	
	public String getRegrefuser(){
		return regrefuser;
	}
	
	public void setRegrefcustomer(String regrefcustomer){
		this.regrefcustomer = regrefcustomer;
	}
	
	public String getRegrefcustomer(){
		return regrefcustomer;
	}
	
	public void setRegtime(Date regtime){
		this.regtime = regtime;
	}
	
	public Date getRegtime(){
		return regtime;
	}
	
	public void setCurrsaleuser(String currsaleuser){
		this.currsaleuser = currsaleuser;
	}
	
	public String getCurrsaleuser(){
		return currsaleuser;
	}
	
	public void setRectranstime(Date rectranstime){
		this.rectranstime = rectranstime;
	}
	
	public Date getRectranstime(){
		return rectranstime;
	}
	
	public void setRectlogintime(Date rectlogintime){
		this.rectlogintime = rectlogintime;
	}
	
	public Date getRectlogintime(){
		return rectlogintime;
	}
	
	public void setCreatetime(Date createtime){
		this.createtime = createtime;
	}
	
	public Date getCreatetime(){
		return createtime;
	}
	
	public void setUpdatetime(Date updatetime){
		this.updatetime = updatetime;
	}
	
	public Date getUpdatetime(){
		return updatetime;
	}
	
	public void setDelstatus(Byte delstatus){
		this.delstatus = delstatus;
	}
	
	public Byte getDelstatus(){
		return delstatus;
	}
	
	public void setImportant(Byte important){
		this.important = important;
	}
	
	public Byte getImportant(){
		return important;
	}
	

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getEasemobAcct() {
		return easemobAcct;
	}

	public void setEasemobAcct(String easemobAcct) {
		this.easemobAcct = easemobAcct;
	}

	public Integer getFreecustomer() {
		return freecustomer;
	}

	public void setFreecustomer(Integer freecustomer) {
		this.freecustomer = freecustomer;
	}

	public String getCurrSaleUserName() {
		return currSaleUserName;
	}

	public void setCurrSaleUserName(String currSaleUserName) {
		this.currSaleUserName = currSaleUserName;
	}

	public String getCurrsaleuserUUID() {
		return currsaleuserUUID;
	}

	public void setCurrsaleuserUUID(String currsaleuserUUID) {
		this.currsaleuserUUID = currsaleuserUUID;
	}
	
	
}

