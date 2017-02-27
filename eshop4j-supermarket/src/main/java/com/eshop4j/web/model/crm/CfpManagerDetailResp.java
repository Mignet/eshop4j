package com.eshop4j.web.model.crm;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.StringUtils;
import com.eshop4j.web.enums.CfpLevelEnum;

public class CfpManagerDetailResp extends BaseEntity {

	private static final long serialVersionUID = 1840107652864359699L;

	/**
	 * ID
	 */
	private int id;
	/**
	 * 理财师名称
	 */
	private String userName;
	/**
	 * 理财师手机号码
	 */
	private String mobile;
	/**
	 * 理财师身份证
	 */
	private String idCard;
	/**
	 * 理财师所属机构
	 */
	private String department;

	private String searchText;

	/**
	 * 理财师头像
	 */
	private String headImage;
	/**
	 * 当前所属机构父机构
	 */
	private String departmentParentId;
	/**
	 * 当前所属机构
	 */
	private String departmentId;

	/**
	 * 理财师等级
	 */
	private String cfpLevel;
	/**
	 * 理财师团队人数
	 */
	private Integer teamMemberCount;
	/**
	 * 理财师客户人数
	 */
	private Integer customerCount;
	/**
	 * 理财师注册时间
	 */
	private Date createTime;
	/**
	 * 禁止登录90天开始时间
	 */
	private Date disabledLoginTime;
	/**
	 * 禁止登录90天结束时间
	 */
	private Date disabledLoginEndTime;
	/**
	 * 上级姓名
	 */
	private String parentName;
	/**
	 * 上级电话
	 */
	private String parentMobile;
	/**
	 * 上级userId
	 */
	private String parentId;
	/**
	 * 理财师等级
	 */
	private List<CrmCfplannerOperation> changeParentRecordList;
	/**
	 * 理财师等级
	 */
	private String userRegTime;
	/**
	 * 经验值
	 */
	private Integer experience = 0;

	/**
	 * 是否新财富理财师
	 */
	private Integer isNew = 0;
	/**
	 * 理财师信息ID
	 */
	private String userId;
	/**
	 * 银行卡
	 */
	private String bankCard;
	/**
	 * 银行名称
	 */
	private String bankName;

	public CfpManagerDetailResp() {
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}


	public Date getDisabledLoginTime() {
		return disabledLoginTime;
	}

	public void setDisabledLoginTime(Date disabledLoginTime) {
		this.disabledLoginTime = disabledLoginTime;
	}

	public Date getDisabledLoginEndTime() {
		return disabledLoginEndTime;
	}

	public void setDisabledLoginEndTime(Date disabledLoginEndTime) {
		this.disabledLoginEndTime = disabledLoginEndTime;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentMobile() {
		return parentMobile;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<CrmCfplannerOperation> getChangeParentRecordList() {
		return changeParentRecordList;
	}

	public void setChangeParentRecordList(List<CrmCfplannerOperation> changeParentRecordList) {
		this.changeParentRecordList = changeParentRecordList;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public String getDepartmentParentId() {
		return departmentParentId;
	}

	public void setDepartmentParentId(String departmentParentId) {
		this.departmentParentId = departmentParentId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getUserRegTime() {
		return userRegTime;
	}

	public void setUserRegTime(String userRegTime) {
		this.userRegTime = userRegTime;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}


	public Integer getTeamMemberCount() {
		return teamMemberCount;
	}

	public void setTeamMemberCount(Integer teamMemberCount) {
		this.teamMemberCount = teamMemberCount;
	}

	public Integer getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(Integer customerCount) {
		this.customerCount = customerCount;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCfpLevel() {
		if(StringUtils.isNotBlank(cfpLevel)){
			return CfpLevelEnum.valueOf(cfpLevel).getMsg();
		}
		return cfpLevel;
	}

	public void setCfpLevel(String cfpLevel) {
		this.cfpLevel = cfpLevel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
