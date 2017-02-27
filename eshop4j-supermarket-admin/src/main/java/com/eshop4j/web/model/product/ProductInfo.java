package com.eshop4j.web.model.product;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月26日 17:01:46
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ProductInfo extends BaseEntity {
	
	private static final long serialVersionUID = 571477381694208505L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *是否推荐产品（1=是| 2=否），首页推荐
     */
	private Byte isRecommended;
	
    /**
     *产品名称
     */
	private String productName;
	
    /**
     *产品描述
     */
	private String productDesc;
	
    /**
     *产品协议表t_product_protocal主键，信用咨询与管理服务协议
     */
	private String productProtocalId;
	
    /**
     *产品计息方式表t_product_interest_way的主键计息ID
     */
	private String productInterestWayId;
	
    /**
     *产品类型表t_product_type的主键
     */
	private Integer productTypeId;
	
    /**
     *还本付息方式(1=一次性到期|2=一次性按日|3=一次性按月|4=一次性按季|5=等额本息(按月)|6=等额本息(按季)
     */
	private Byte repaymentWay;
	
    /**
     *是否浮动利率(1=固定利率|2=浮动利率)
     */
	private Byte isFlow;
	
    /**
     *固定利率
     */
	private BigDecimal fixRate;
	/**
	 * 募集产品募集期利率
	 */
	private BigDecimal collectRate;
	
    /**
     *浮动最小利率
     */
	private BigDecimal flowMinRate;
	
    /**
     *浮动最大利率
     */
	private BigDecimal flowMaxRate;
	
    /**
     *浮动最后利率
     */
	private BigDecimal flowFinalRate;
	
    /**
     *期限类型(1=天数|2=自然月|3=年)
     */
	private Byte deadLineType;
	
    /**
     *产品期限天数或月数值或年数值
     */
	private Integer deadLineValue;
	
    /**
     *是否需要募集开始及截止时间(1=不需要|2=需要)
     */
	private Byte isCollect;
	
    /**
     *募集开始时间|格式yyyy-MM-ddHH:mm:ss
     */
	private Date collectBeginTime;
	
    /**
     *募集截止时间可以为nul格式yyyy-MM-ddHH:mm:ss
     */
	private Date collectEndTime;
	
    /**
     *起息方式(1=购买当日|2=购买次日|3=募集完成当日|4=募集完成次日|5=指定日期)
     */
	private Byte interestWay;
	
    /**
     *产品起息日格式yyyy-MM-dd
     */
	private Date validBeginDate;
	
    /**
     *产品到期日格式yyyy-MM-dd
     */
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
     *产品购买总人数
     */
	private Integer buyTotalPeople;
	
    /**
     *是否要显示在前端(1= 显示在列表|2= 显示在首页和列表|3= 显示在首页|4= 隐藏)
     */
	private Byte isShow;
	
    /**
     *移动端显示排序索引 理财产品列表排序
     */
	private Integer showIndex;
	
    /**
     *投标方式(1=手动|2=自动|默认为1)
     */
	private Byte tenderWay;
	
    /**
     *是否可赎回(1=不赎回|2=赎回)
     */
	private Byte isRedemption;
	
    /**
     *来源方式(1=公司内部产品|2=公司外部产品)
     */
	private Byte sourceWay;
	
    /**
     *货币类型(1=RMB|2=港币|3=美元)
     */
	private Byte moneyType;
	
    /**
     *风控类型(1=抵押|2=担保|3=信贷)
     */
	private Byte riskControlType;
	
    /**
     *风险级别(1=一般|2=重要|3=紧急|4=非常紧急)
     */
	private Byte riskLevel;
	
    /**
     *流动性级别
     */
	private Byte flowLevel;
	
    /**
     *产品管理方式1=利息复投
     */
	private Byte productManageWay;
	
    /**
     *产品状态(1=待审核(数据初始化)|2=审核通过(上架)|3=募集完成|4=还款中|5=(已到期)下架|6=已还款|7=已删除|8=驳回|说明状态是顺序的不能往回审核通过的也可以修改除状态外的其它字段
     */
	private Byte status;
	
    /**
     *创建者用户名
     */
	private String creator;
	
    /**
     *创建时间
     */
	private Date createTime;
	
    /**
     *最后一次修改者用户名
     */
	private String updater;
	
    /**
     *最后一次修改时间
     */
	private Date updateTime;
	
    /**
     *修改或审核操作的说明
     */
	private String remark;
	
    /**
     *产品UUID
     */
	private String productId;
	
    /**
     *产品详细描述(大文本)
     */
	private String description;
	
    /**
     *购买递增金额
     */
	private BigDecimal buyIncreaseMoney;
	
    /**
     *每个用户可购买的最大额度
     */
	private BigDecimal custBuyMaxMoney;
	
    /**
     *产品说明页面url
     */
	private String productIllustrationUrl;
	
    /**
     *安全保障页面url
     */
	private String securityGuaranteeUrl;
	
    /**
     *收益权转让协议名称
     */
	private String ransferProtocalName;
	
    /**
     *收益权转让协议地址
     */
	private String ransferProtocalUrl;
	
    /**
     *投资者标签
     */
	private String invLabel;
	
    /**
     *理财师标签
     */
	private String lcsLabel;
	
    /**
     *产品销售状态:1-预售、2-在售、3-售罄、4-下架、5-定时发售
     */
	private String saleStatus;
	
    /**
     *是否参加宝箱活动
     */
	private String activityCode;
	
    /**
     *是否限额产品。1-限额、2-不限额
     */
	private Integer isQuota;
	
    /**
     *产品开售时间
     */
	private Date beginSaleTime;
	
    /**
     *灰度是否开启，0-灰度关闭，1-灰度开启
     */
	private Integer greyFlag;
	
    /**
     *打开方式 0-原页打开 1-新页面打开
     */
	private String openType;
	
    /**
     *打开链接地址页面url
     */
	private String openLinkurl;
	
    /**
     *购买成功返回url地址
     */
	private String buySucceedReturnurl;
	
    /**
     *
     */
	private String lcsCornerIco;
	
    /**
     *
     */
	private String invCornerIco;
	
    /**
     *详情页推荐语(投资端)
     */
	private String invDtlPageDes;
	
    /**
     *详情页推荐语（理财师端）
     */
	private String lcsDtlPageDes;
	/**
	 * 开售设置类型
	 */
	private Byte beginSaleType; 
	/**
     *募集产品最小期限天数或月数值或年数值
     */
	private Integer collectLineMinValue;
	
    /**
     *募集产品最大期限天数或月数值或年数值
     */
	private Integer collectLineMaxValue;
	
    /**
     *募集产品回款时间
     */
	private Date collectLineBackDatetime;
	
    /**
     *理财师打开链接地址页面url
     */
	private String openLcsLinkurl;
	
    /**
     *募集成功或者失败的时间,可以为null格式yyyy-MM-ddHH:mm:ss
     */
	private Date collectFinshTime;
	
	

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setIsRecommended(Byte isRecommended){
		this.isRecommended = isRecommended;
	}
	
	public Byte getIsRecommended(){
		return isRecommended;
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
	
	public void setProductProtocalId(String productProtocalId){
		this.productProtocalId = productProtocalId;
	}
	
	public String getProductProtocalId(){
		return productProtocalId;
	}
	
	public void setProductInterestWayId(String productInterestWayId){
		this.productInterestWayId = productInterestWayId;
	}
	
	public String getProductInterestWayId(){
		return productInterestWayId;
	}
	
	public void setProductTypeId(Integer productTypeId){
		this.productTypeId = productTypeId;
	}
	
	public Integer getProductTypeId(){
		return productTypeId;
	}
	
	public void setRepaymentWay(Byte repaymentWay){
		this.repaymentWay = repaymentWay;
	}
	
	public Byte getRepaymentWay(){
		return repaymentWay;
	}
	
	public void setIsFlow(Byte isFlow){
		this.isFlow = isFlow;
	}
	
	public Byte getIsFlow(){
		return isFlow;
	}
	
	public void setFixRate(BigDecimal fixRate){
		this.fixRate = fixRate;
	}
	
	public BigDecimal getFixRate(){
		return fixRate;
	}
	
	public BigDecimal getCollectRate() {
		return collectRate;
	}

	public void setCollectRate(BigDecimal collectRate) {
		this.collectRate = collectRate;
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
	
	public void setFlowFinalRate(BigDecimal flowFinalRate){
		this.flowFinalRate = flowFinalRate;
	}
	
	public BigDecimal getFlowFinalRate(){
		return flowFinalRate;
	}
	
	public void setDeadLineType(Byte deadLineType){
		this.deadLineType = deadLineType;
	}
	
	public Byte getDeadLineType(){
		return deadLineType;
	}
	
	public void setDeadLineValue(Integer deadLineValue){
		this.deadLineValue = deadLineValue;
	}
	
	public Integer getDeadLineValue(){
		return deadLineValue;
	}
	
	public void setIsCollect(Byte isCollect){
		this.isCollect = isCollect;
	}
	
	public Byte getIsCollect(){
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
	
	public void setInterestWay(Byte interestWay){
		this.interestWay = interestWay;
	}
	
	public Byte getInterestWay(){
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
	
	public void setBuyTotalPeople(Integer buyTotalPeople){
		this.buyTotalPeople = buyTotalPeople;
	}
	
	public Integer getBuyTotalPeople(){
		return buyTotalPeople;
	}
	
	public void setIsShow(Byte isShow){
		this.isShow = isShow;
	}
	
	public Byte getIsShow(){
		return isShow;
	}
	
	public void setShowIndex(Integer showIndex){
		this.showIndex = showIndex;
	}
	
	public Integer getShowIndex(){
		return showIndex;
	}
	
	public void setTenderWay(Byte tenderWay){
		this.tenderWay = tenderWay;
	}
	
	public Byte getTenderWay(){
		return tenderWay;
	}
	
	public void setIsRedemption(Byte isRedemption){
		this.isRedemption = isRedemption;
	}
	
	public Byte getIsRedemption(){
		return isRedemption;
	}
	
	public void setSourceWay(Byte sourceWay){
		this.sourceWay = sourceWay;
	}
	
	public Byte getSourceWay(){
		return sourceWay;
	}
	
	public void setMoneyType(Byte moneyType){
		this.moneyType = moneyType;
	}
	
	public Byte getMoneyType(){
		return moneyType;
	}
	
	public void setRiskControlType(Byte riskControlType){
		this.riskControlType = riskControlType;
	}
	
	public Byte getRiskControlType(){
		return riskControlType;
	}
	
	public void setRiskLevel(Byte riskLevel){
		this.riskLevel = riskLevel;
	}
	
	public Byte getRiskLevel(){
		return riskLevel;
	}
	
	public void setFlowLevel(Byte flowLevel){
		this.flowLevel = flowLevel;
	}
	
	public Byte getFlowLevel(){
		return flowLevel;
	}
	
	public void setProductManageWay(Byte productManageWay){
		this.productManageWay = productManageWay;
	}
	
	public Byte getProductManageWay(){
		return productManageWay;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	
	public Byte getStatus(){
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
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setBuyIncreaseMoney(BigDecimal buyIncreaseMoney){
		this.buyIncreaseMoney = buyIncreaseMoney;
	}
	
	public BigDecimal getBuyIncreaseMoney(){
		return buyIncreaseMoney;
	}
	
	public void setCustBuyMaxMoney(BigDecimal custBuyMaxMoney){
		this.custBuyMaxMoney = custBuyMaxMoney;
	}
	
	public BigDecimal getCustBuyMaxMoney(){
		return custBuyMaxMoney;
	}
	
	public void setProductIllustrationUrl(String productIllustrationUrl){
		this.productIllustrationUrl = productIllustrationUrl;
	}
	
	public String getProductIllustrationUrl(){
		return productIllustrationUrl;
	}
	
	public void setSecurityGuaranteeUrl(String securityGuaranteeUrl){
		this.securityGuaranteeUrl = securityGuaranteeUrl;
	}
	
	public String getSecurityGuaranteeUrl(){
		return securityGuaranteeUrl;
	}
	
	public void setRansferProtocalName(String ransferProtocalName){
		this.ransferProtocalName = ransferProtocalName;
	}
	
	public String getRansferProtocalName(){
		return ransferProtocalName;
	}
	
	public void setRansferProtocalUrl(String ransferProtocalUrl){
		this.ransferProtocalUrl = ransferProtocalUrl;
	}
	
	public String getRansferProtocalUrl(){
		return ransferProtocalUrl;
	}
	
	public void setInvLabel(String invLabel){
		this.invLabel = invLabel;
	}
	
	public String getInvLabel(){
		return invLabel;
	}
	
	public void setLcsLabel(String lcsLabel){
		this.lcsLabel = lcsLabel;
	}
	
	public String getLcsLabel(){
		return lcsLabel;
	}
	
	public void setSaleStatus(String saleStatus){
		this.saleStatus = saleStatus;
	}
	
	public String getSaleStatus(){
		return saleStatus;
	}
	
	public void setActivityCode(String activityCode){
		this.activityCode = activityCode;
	}
	
	public String getActivityCode(){
		return activityCode;
	}
	
	public void setIsQuota(Integer isQuota){
		this.isQuota = isQuota;
	}
	
	public Integer getIsQuota(){
		return isQuota;
	}
	
	public void setBeginSaleTime(Date beginSaleTime){
		this.beginSaleTime = beginSaleTime;
	}
	
	public Date getBeginSaleTime(){
		return beginSaleTime;
	}
	
	public void setGreyFlag(Integer greyFlag){
		this.greyFlag = greyFlag;
	}
	
	public Integer getGreyFlag(){
		return greyFlag;
	}
	
	public void setOpenType(String openType){
		this.openType = openType;
	}
	
	public String getOpenType(){
		return openType;
	}
	
	public void setOpenLinkurl(String openLinkurl){
		this.openLinkurl = openLinkurl;
	}
	
	public String getOpenLinkurl(){
		return openLinkurl;
	}
	
	public void setBuySucceedReturnurl(String buySucceedReturnurl){
		this.buySucceedReturnurl = buySucceedReturnurl;
	}
	
	public String getBuySucceedReturnurl(){
		return buySucceedReturnurl;
	}
	
	public void setLcsCornerIco(String lcsCornerIco){
		this.lcsCornerIco = lcsCornerIco;
	}
	
	public String getLcsCornerIco(){
		return lcsCornerIco;
	}
	
	public void setInvCornerIco(String invCornerIco){
		this.invCornerIco = invCornerIco;
	}
	
	public String getInvCornerIco(){
		return invCornerIco;
	}
	
	public void setInvDtlPageDes(String invDtlPageDes){
		this.invDtlPageDes = invDtlPageDes;
	}
	
	public String getInvDtlPageDes(){
		return invDtlPageDes;
	}
	
	public void setLcsDtlPageDes(String lcsDtlPageDes){
		this.lcsDtlPageDes = lcsDtlPageDes;
	}
	
	public String getLcsDtlPageDes(){
		return lcsDtlPageDes;
	}

	public Byte getBeginSaleType() {
		return beginSaleType;
	}

	public void setBeginSaleType(Byte beginSaleType) {
		this.beginSaleType = beginSaleType;
	}

	public Integer getCollectLineMinValue() {
		return collectLineMinValue;
	}

	public void setCollectLineMinValue(Integer collectLineMinValue) {
		this.collectLineMinValue = collectLineMinValue;
	}

	public Integer getCollectLineMaxValue() {
		return collectLineMaxValue;
	}

	public void setCollectLineMaxValue(Integer collectLineMaxValue) {
		this.collectLineMaxValue = collectLineMaxValue;
	}

	public Date getCollectLineBackDatetime() {
		return collectLineBackDatetime;
	}

	public void setCollectLineBackDatetime(Date collectLineBackDatetime) {
		this.collectLineBackDatetime = collectLineBackDatetime;
	}

	public String getOpenLcsLinkurl() {
		return openLcsLinkurl;
	}

	public void setOpenLcsLinkurl(String openLcsLinkurl) {
		this.openLcsLinkurl = openLcsLinkurl;
	}

	public Date getCollectFinshTime() {
		return collectFinshTime;
	}

	public void setCollectFinshTime(Date collectFinshTime) {
		this.collectFinshTime = collectFinshTime;
	}

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

