package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.math.BigDecimal;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年08月12日 10:39:26
 * 
 * Copyright (c) 深圳ESHOP有限公司-版权所有
 */
public class CimProduct implements Serializable {
	
	private static final long serialVersionUID = 714729507633818745L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *产品名称
     */
	private String productName;
	
    /**
     *产品描述
     */
	private String productDesc;
	
    /**
     *产品类型(1=P2P|2=信托 |3=资管|4=基金|401=公募基金|402=阳光私募|403=股权基金|5=保险|6=众筹|999=其他)
     */
	private Integer productType;
	
    /**
     *还本付息方式(1=一次性到期|101=一次性按日|102=一次性按月|103=一次性按季|2=等额本息|3=等额本金|4=先息后本)
     */
	private Integer repaymentWay;
	
    /**
     *产品销售开始时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date saleStartTime;
	
    /**
     *产品销售结束时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date saleEndTime;
	
    /**
     *是否浮动利率(1=固定利率|2=浮动利率)
     */
	private Integer isFlow;
	
    /**
     *浮动最小利率
     */
	private BigDecimal flowMinRate;
	
    /**
     *浮动最大利率
     */
	private BigDecimal flowMaxRate;
	
    /**
     *加息利率
     */
	private BigDecimal addRate;
	
    /**
     *平台返现利率
     */
	private BigDecimal platformCashback;
	
    /**
     *是否固定期限(1=固定期限|2=浮动期限)
     */
	private Integer isFixedDeadline;
	
    /**
     *产品最小期限天数
     */
	private Integer deadLineMinValue;
	
    /**
     *产品最大期限天数
     */
	private Integer deadLineMaxValue;
	
    /**
     *产品最小期限天数 自定义显示
     */
	private String deadLineMinSelfDefined;
	
    /**
     *产品最大期限天数 自定义显示
     */
	private String deadLineMaxSelfDefined;
	
    /**
     *是否需要募集开始及截止时间(1=不需要|2=需要)
     */
	private Integer isCollect;
	
    /**
     *募集开始时间|格式yyyy-mm-ddhh:mm:ss
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date collectBeginTime;
	
    /**
     *募集截止时间可以为nul格式yyyy-mm-ddhh:mm:ss
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date collectEndTime;
	
    /**
     *起息方式(1=购买当日|2=购买次日|3=募集完成当日|4=募集完成次日|5=指定日期)
     */
	private Integer interestWay;
	
    /**
     *产品起息日格式yyyy-mm-dd
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date validBeginDate;
	
    /**
     *产品到期日格式yyyy-mm-dd
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date validEndDate;
	
    /**
     *产品单笔购买最小额度
     */
	private BigDecimal buyMinMoney;
	
    /**
     *产品单笔购买最大额度
     */
	private BigDecimal buyMaxMoney;
	
    /**
     *产品总额度产品募集要达到的总额度数不能为空作为募集完成时间确定条件之一
     */
	private BigDecimal buyTotalMoney;
	
    /**
     *移动端显示排序索引
     */
	private Integer showIndex;
	
    /**
     *是否拥有产品进度(0=有|1没有)
     */
	private Integer isHaveProgress;
	
    /**
     *是否可赎回可转让(0=不支持赎回和转让|1=可赎回|2=可转让|3=可赎回且可转让)
     */
	private Integer isRedemption;
	
    /**
     *可赎回天数
     */
	private Integer redemptionTime;
	
    /**
     *可转让天数
     */
	private Integer assignmentTime;
	
    /**
     *货币类型(1=rmb|2=港币|3=美元)
     */
	private Integer moneyType;
	
    /**
     *风控类型(1=抵押|2=担保|3=信贷)
     */
	private Integer riskControlType;
	
    /**
     *风险级别(1=一般|2=重要|3=紧急|4=非常紧急)
     */
	private Integer riskLevel;
	
    /**
     *产品状态(1-在售|2-售罄|3-募集失败)
     */
	private Integer status;
	
    /**
     *创建者用户名
     */
	private String creator;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *最后一次修改者用户名
     */
	private String updater;
	
    /**
     *最后一次修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *修改或审核操作的说明
     */
	private String remark;
	
    /**
     *产品ID
     */
	private String productId;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *外部产品ID
     */
	private String thirdProductId;
	
    /**
     *'是否限额产品。1-限额、2-不限额'
     */
	private Integer isQuota;
	
    /**
     *产品详情打开URL
     */
	private String detailOpenUrl;
	
    /**
     *产品详情打开方式  0-原页打开  1-新页面打开
     */
	private Integer detailOpenType;
	
    /**
     *购买递增金额
     */
	private BigDecimal buyIncreaseMoney;
	
    /**
     *佣金率(固定产品佣金率)
     */
	private BigDecimal feeRatio;
	
    /**
     *审核状态(1=审核通过(上架) 2-待审核 3-审核未通过(下架))
     */
	private Integer auditStatus;
	
    /**
     *审核时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date auditTime;
	
    /**
     *备用字段
     */
	private Integer sourceWay;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public String getProductName(){
		return productName;
	}
	
	public void setProductDesc(String productDesc){
		this.productDesc = productDesc;
	}
	
	public String getProductDesc(){
		return productDesc;
	}
	
	public void setProductType(Integer productType){
		this.productType = productType;
	}
	
	public Integer getProductType(){
		return productType;
	}
	
	public void setRepaymentWay(Integer repaymentWay){
		this.repaymentWay = repaymentWay;
	}
	
	public Integer getRepaymentWay(){
		return repaymentWay;
	}
	
	public void setSaleStartTime(Date saleStartTime){
		this.saleStartTime = saleStartTime;
	}
	
	public Date getSaleStartTime(){
		return saleStartTime;
	}
	
	public void setSaleEndTime(Date saleEndTime){
		this.saleEndTime = saleEndTime;
	}
	
	public Date getSaleEndTime(){
		return saleEndTime;
	}
	
	public void setIsFlow(Integer isFlow){
		this.isFlow = isFlow;
	}
	
	public Integer getIsFlow(){
		return isFlow;
	}
	
	public void setFlowMinRate(BigDecimal flowMinRate){
		this.flowMinRate = flowMinRate;
	}
	
	public BigDecimal getFlowMinRate(){
		return flowMinRate;
	}
	
	public void setFlowMaxRate(BigDecimal flowMaxRate){
		this.flowMaxRate = flowMaxRate;
	}
	
	public BigDecimal getFlowMaxRate(){
		return flowMaxRate;
	}
	
	public void setAddRate(BigDecimal addRate){
		this.addRate = addRate;
	}
	
	public BigDecimal getAddRate(){
		return addRate;
	}
	
	public void setPlatformCashback(BigDecimal platformCashback){
		this.platformCashback = platformCashback;
	}
	
	public BigDecimal getPlatformCashback(){
		return platformCashback;
	}
	
	public void setIsFixedDeadline(Integer isFixedDeadline){
		this.isFixedDeadline = isFixedDeadline;
	}
	
	public Integer getIsFixedDeadline(){
		return isFixedDeadline;
	}
	
	public void setDeadLineMinValue(Integer deadLineMinValue){
		this.deadLineMinValue = deadLineMinValue;
	}
	
	public Integer getDeadLineMinValue(){
		return deadLineMinValue;
	}
	
	public void setDeadLineMaxValue(Integer deadLineMaxValue){
		this.deadLineMaxValue = deadLineMaxValue;
	}
	
	public Integer getDeadLineMaxValue(){
		return deadLineMaxValue;
	}
	
	public void setDeadLineMinSelfDefined(String deadLineMinSelfDefined){
		this.deadLineMinSelfDefined = deadLineMinSelfDefined;
	}
	
	public String getDeadLineMinSelfDefined(){
		return deadLineMinSelfDefined;
	}
	
	public void setDeadLineMaxSelfDefined(String deadLineMaxSelfDefined){
		this.deadLineMaxSelfDefined = deadLineMaxSelfDefined;
	}
	
	public String getDeadLineMaxSelfDefined(){
		return deadLineMaxSelfDefined;
	}
	
	public void setIsCollect(Integer isCollect){
		this.isCollect = isCollect;
	}
	
	public Integer getIsCollect(){
		return isCollect;
	}
	
	public void setCollectBeginTime(Date collectBeginTime){
		this.collectBeginTime = collectBeginTime;
	}
	
	public Date getCollectBeginTime(){
		return collectBeginTime;
	}
	
	public void setCollectEndTime(Date collectEndTime){
		this.collectEndTime = collectEndTime;
	}
	
	public Date getCollectEndTime(){
		return collectEndTime;
	}
	
	public void setInterestWay(Integer interestWay){
		this.interestWay = interestWay;
	}
	
	public Integer getInterestWay(){
		return interestWay;
	}
	
	public void setValidBeginDate(Date validBeginDate){
		this.validBeginDate = validBeginDate;
	}
	
	public Date getValidBeginDate(){
		return validBeginDate;
	}
	
	public void setValidEndDate(Date validEndDate){
		this.validEndDate = validEndDate;
	}
	
	public Date getValidEndDate(){
		return validEndDate;
	}
	
	public void setBuyMinMoney(BigDecimal buyMinMoney){
		this.buyMinMoney = buyMinMoney;
	}
	
	public BigDecimal getBuyMinMoney(){
		return buyMinMoney;
	}
	
	public void setBuyMaxMoney(BigDecimal buyMaxMoney){
		this.buyMaxMoney = buyMaxMoney;
	}
	
	public BigDecimal getBuyMaxMoney(){
		return buyMaxMoney;
	}
	
	public void setBuyTotalMoney(BigDecimal buyTotalMoney){
		this.buyTotalMoney = buyTotalMoney;
	}
	
	public BigDecimal getBuyTotalMoney(){
		return buyTotalMoney;
	}
	
	public void setShowIndex(Integer showIndex){
		this.showIndex = showIndex;
	}
	
	public Integer getShowIndex(){
		return showIndex;
	}
	
	public void setIsHaveProgress(Integer isHaveProgress){
		this.isHaveProgress = isHaveProgress;
	}
	
	public Integer getIsHaveProgress(){
		return isHaveProgress;
	}
	
	public void setIsRedemption(Integer isRedemption){
		this.isRedemption = isRedemption;
	}
	
	public Integer getIsRedemption(){
		return isRedemption;
	}
	
	public void setRedemptionTime(Integer redemptionTime){
		this.redemptionTime = redemptionTime;
	}
	
	public Integer getRedemptionTime(){
		return redemptionTime;
	}
	
	public void setAssignmentTime(Integer assignmentTime){
		this.assignmentTime = assignmentTime;
	}
	
	public Integer getAssignmentTime(){
		return assignmentTime;
	}
	
	public void setMoneyType(Integer moneyType){
		this.moneyType = moneyType;
	}
	
	public Integer getMoneyType(){
		return moneyType;
	}
	
	public void setRiskControlType(Integer riskControlType){
		this.riskControlType = riskControlType;
	}
	
	public Integer getRiskControlType(){
		return riskControlType;
	}
	
	public void setRiskLevel(Integer riskLevel){
		this.riskLevel = riskLevel;
	}
	
	public Integer getRiskLevel(){
		return riskLevel;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	
	public String getCreator(){
		return creator;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdater(String updater){
		this.updater = updater;
	}
	
	public String getUpdater(){
		return updater;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setThirdProductId(String thirdProductId){
		this.thirdProductId = thirdProductId;
	}
	
	public String getThirdProductId(){
		return thirdProductId;
	}
	
	public void setIsQuota(Integer isQuota){
		this.isQuota = isQuota;
	}
	
	public Integer getIsQuota(){
		return isQuota;
	}
	
	public void setDetailOpenUrl(String detailOpenUrl){
		this.detailOpenUrl = detailOpenUrl;
	}
	
	public String getDetailOpenUrl(){
		return detailOpenUrl;
	}
	
	public void setDetailOpenType(Integer detailOpenType){
		this.detailOpenType = detailOpenType;
	}
	
	public Integer getDetailOpenType(){
		return detailOpenType;
	}
	
	public void setBuyIncreaseMoney(BigDecimal buyIncreaseMoney){
		this.buyIncreaseMoney = buyIncreaseMoney;
	}
	
	public BigDecimal getBuyIncreaseMoney(){
		return buyIncreaseMoney;
	}
	
	public void setFeeRatio(BigDecimal feeRatio){
		this.feeRatio = feeRatio;
	}
	
	public BigDecimal getFeeRatio(){
		return feeRatio;
	}
	
	public void setAuditStatus(Integer auditStatus){
		this.auditStatus = auditStatus;
	}
	
	public Integer getAuditStatus(){
		return auditStatus;
	}
	
	public void setAuditTime(Date auditTime){
		this.auditTime = auditTime;
	}
	
	public Date getAuditTime(){
		return auditTime;
	}
	
	public void setSourceWay(Integer sourceWay){
		this.sourceWay = sourceWay;
	}
	
	public Integer getSourceWay(){
		return sourceWay;
	}
	
}

