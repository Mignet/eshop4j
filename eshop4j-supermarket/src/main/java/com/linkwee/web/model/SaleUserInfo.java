package com.linkwee.web.model;

 import java.util.Arrays;
import java.util.Date;

import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年07月31日 11:49:53
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class SaleUserInfo  extends BaseEntity{
	private static final long serialVersionUID = 4703747973884763234L;
	
	private String number; //业务员编码
	private String name; //业务员姓名
	private String email; //电子邮件
	private String mobile; //手机号码
	private String qrcode; //二维码
	private String idcard; //身份证
	private byte[] headImage; //头像
	private String password; //登录密码
	private String signPwd; //手势密码
	private String parentId; //上级业务员
	private Byte isLock; //是否锁定
	private String remark; //备注
	private String department; //业务员所属机构
	private String customerId; //投资者
	private Byte settlementType; //佣金入账方式 1：工资账户 2：理财账户
	private Byte enable; //是否启用  0：禁用 1：启用
	private String ancestor; //祖先路径
	private Byte isCfp; //是否理财师
	private String cfpLevel; //理财师等级
	private Date cfpRegTime; //理财师注册时间
	private Date cfpBenormalTime; //理财师转正时间
	private Date cfpUpdateTime; //理财师更新时间
	private Byte isPartner; //是否合伙人
	private String partnerLevel; //合伙人等级
	private Date partnerRegTime; //合伙人注册时间
	private Date partnerUpTime; //合伙人更新时间
	private Date createTime; //创建时间
	private Date updateTime; //修改时间
	private Byte delStatus; //删除状态  0：正常 1：删除
	private Byte type; //用户类别
	private String easemobAcct;//环信帐号
	private String easemobPassword;//环信密码
	private int easemobStatus;//环信状态
	private Double levelExperience;//经验值
	private String image;
	private Date disabledLoginTime;//禁止登录时间

	public void setNumber(String number){
		this.number = number;
	}
	
	public String getNumber(){
		return number;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setQrcode(String qrcode){
		this.qrcode = qrcode;
	}
	
	public String getQrcode(){
		return qrcode;
	}
	
	public void setIdcard(String idcard){
		this.idcard = idcard;
	}
	
	public String getIdcard(){
		return idcard;
	}
	
	public void setHeadImage(byte[] headImage){
		this.headImage = headImage;
	}
	
	public byte[] getHeadImage(){
		return headImage;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setSignPwd(String signPwd){
		this.signPwd = signPwd;
	}
	
	public String getSignPwd(){
		return signPwd;
	}
	
	public void setParentId(String parentId){
		this.parentId = parentId;
	}
	
	public String getParentId(){
		return parentId;
	}
	
	public void setIsLock(Byte isLock){
		this.isLock = isLock;
	}
	
	public Byte getIsLock(){
		return isLock;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setDepartment(String department){
		this.department = department;
	}
	
	public String getDepartment(){
		return department;
	}
	
	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}
	
	public String getCustomerId(){
		return customerId;
	}
	
	public void setSettlementType(Byte settlementType){
		this.settlementType = settlementType;
	}
	
	public Byte getSettlementType(){
		return settlementType;
	}
	
	public void setEnable(Byte enable){
		this.enable = enable;
	}
	
	public Byte getEnable(){
		return enable;
	}
	
	public void setAncestor(String ancestor){
		this.ancestor = ancestor;
	}
	
	public String getAncestor(){
		return ancestor;
	}
	
	public void setIsCfp(Byte isCfp){
		this.isCfp = isCfp;
	}
	
	public Byte getIsCfp(){
		return isCfp;
	}
	
	public void setCfpLevel(String cfpLevel){
		this.cfpLevel = cfpLevel;
	}
	
	public String getCfpLevel(){
		return cfpLevel;
	}
	
	public void setCfpRegTime(Date cfpRegTime){
		this.cfpRegTime = cfpRegTime;
	}
	
	public Date getCfpRegTime(){
		return cfpRegTime;
	}
	
	public void setCfpBenormalTime(Date cfpBenormalTime){
		this.cfpBenormalTime = cfpBenormalTime;
	}
	
	public Date getCfpBenormalTime(){
		return cfpBenormalTime;
	}
	
	public void setCfpUpdateTime(Date cfpUpdateTime){
		this.cfpUpdateTime = cfpUpdateTime;
	}
	
	public Date getCfpUpdateTime(){
		return cfpUpdateTime;
	}
	
	public void setIsPartner(Byte isPartner){
		this.isPartner = isPartner;
	}
	
	public Byte getIsPartner(){
		return isPartner;
	}
	
	public void setPartnerLevel(String partnerLevel){
		this.partnerLevel = partnerLevel;
	}
	
	public String getPartnerLevel(){
		return partnerLevel;
	}
	
	public void setPartnerRegTime(Date partnerRegTime){
		this.partnerRegTime = partnerRegTime;
	}
	
	public Date getPartnerRegTime(){
		return partnerRegTime;
	}
	
	public void setPartnerUpTime(Date partnerUpTime){
		this.partnerUpTime = partnerUpTime;
	}
	
	public Date getPartnerUpTime(){
		return partnerUpTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setDelStatus(Byte delStatus){
		this.delStatus = delStatus;
	}
	
	public Byte getDelStatus(){
		return delStatus;
	}
	
	public void setType(Byte type){
		this.type = type;
	}
	
	public Byte getType(){
		return type;
	}

	@Override
	public String toString() {
		return "SaleUserInfo [number=" + number + ", name=" + name + ", email="
				+ email + ", mobile=" + mobile + ", qrcode=" + qrcode
				+ ", idcard=" + idcard + ", headImage="
				+ Arrays.toString(headImage) + ", password=" + password
				+ ", signPwd=" + signPwd + ", parentId=" + parentId
				+ ", isLock=" + isLock + ", remark=" + remark + ", department="
				+ department + ", customerId=" + customerId
				+ ", settlementType=" + settlementType + ", enable=" + enable
				+ ", ancestor=" + ancestor + ", isCfp=" + isCfp + ", cfpLevel="
				+ cfpLevel + ", cfpRegTime=" + cfpRegTime
				+ ", cfpBenormalTime=" + cfpBenormalTime + ", cfpUpdateTime="
				+ cfpUpdateTime + ", isPartner=" + isPartner
				+ ", partnerLevel=" + partnerLevel + ", partnerRegTime="
				+ partnerRegTime + ", partnerUpTime=" + partnerUpTime
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", delStatus=" + delStatus + ", type=" + type
				+ ", easemobAcct=" + easemobAcct + ", easemobPassword="
				+ easemobPassword + ", easemobStatus=" + easemobStatus
				+ ", levelExperience=" + levelExperience + "]";
	}

	public String getEasemobAcct() {
		return easemobAcct;
	}

	public void setEasemobAcct(String easemobAcct) {
		this.easemobAcct = easemobAcct;
	}

	public String getEasemobPassword() {
		return easemobPassword;
	}

	public void setEasemobPassword(String easemobPassword) {
		this.easemobPassword = easemobPassword;
	}

	public int getEasemobStatus() {
		return easemobStatus;
	}

	public void setEasemobStatus(int easemobStatus) {
		this.easemobStatus = easemobStatus;
	}

	public Double getLevelExperience() {
		return levelExperience;
	}

	public void setLevelExperience(Double levelExperience) {
		this.levelExperience = levelExperience;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getDisabledLoginTime() {
		return disabledLoginTime;
	}

	public void setDisabledLoginTime(Date disabledLoginTime) {
		this.disabledLoginTime = disabledLoginTime;
	}
	
}

