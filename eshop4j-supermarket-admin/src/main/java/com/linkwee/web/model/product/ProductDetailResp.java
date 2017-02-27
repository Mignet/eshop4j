package com.linkwee.web.model.product;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;

/**
 * 产品详情
 * 
 * @Author ZhongLing
 * @Date 2015年12月25日 下午5:18:12
 */
public class ProductDetailResp extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 产品id
	 */
	private String productId;
	/**
	 * 产品名称
	 */
	private String productName;

	/**
	 * 产品类别
	 */
	private Integer typeValue;

	/**
	 * 产品类别
	 */
	private String typeName;
	
	/**
	 * 协议id
	 */
	private Integer productProtocalId;
	/**
	 * 协议名称
	 */
	private String productProtocalName;
	
	/**
	 * 协议URL
	 */
	private String productProtocalUrl;

	/**
	 * 1=固定利率|2=浮动利率
	 */
	private Integer isFlow;
	/**
	 * 固定利率
	 */
	private Double fixRate;

	/**
	 * 浮动最小利率
	 */
	private Double flowMinRate;

	/**
	 * 浮动最大利率
	 */
	private Double flowMaxRate;
	/**
	 * 期限
	 */
	private Integer deadLineValue;
	/**
	 * 期限类别：1天,2月
	 */
	private Integer deadLineType;

	/**
	 * 产品状态(1=待审核(数据初始化)|2=审核通过(上架)|3=募集完成|4=还款中|5=(已到期)下架|6=已还款|7=已删除|8=驳回
	 */
	private Integer status;
	private String statusName;

	/**
	 * 购买总人数
	 */
	private Integer buyedTotalPeople;

	/**
	 * 募集总额(单位元)
	 */
	private Double buyTotalMoney;

	/**
	 * 已募集总额(单位元)
	 */
	private Double buyedTotalMoney;

	/**
	 * 剩余额度
	 */
	private Double remaMoney;

	/**
	 * 产品起息日
	 */
	private Date validBeginDate;

	/**
	 * 结息日
	 */
	private Date validEndDate;

	/**
	 * 还本付息方式(1一次性到期;2一次性按日;3一次性按月;4一次性按季;5等额本息(按月);6等额本息(按季)
	 */
	private Integer repaymentWay;
	private String repaymentTypeName;
	
	/**
	 * 产品单笔购买最小额度
	 */
	private Double buyMinMoney;
	
	/**
	 * 产品单笔购买最大额度
	 */
	private Double buyMaxMoney;

	/**
	 * 产品描述
	 */
	private String productDescStr;
	
	/**
	 * 产品管理方式; 1:利息复投
	 */
	private Integer productManageWay;
	
	/**
	 * 是否可赎回(1=不赎回|2=赎回)
	 */
	private Integer isRedemption;
	/**
	 * 购买递增金额
	 */
	private Double buyIncreaseMoney;
	/**
	 * /每个用户可购买最大额度
	 */
	private Double custBuyMaxMoney;
	
	/**
	 * 个人账余额
	 */
	private String acountBalance;
	
	/**
	 * 佣金率
	 */
	private Double feeRatio;
	
	/**
	 * 是否理财师已推荐
	 */
	private boolean cfpRecommend;
	/**
	 * 当前推荐产品
	 */
	private String currentRecomendProduct;
	
	/**
	 * 产品说明连接
	 */
	private String productIllustrationUrl;
	/**
	 * 安全保障连接
	 */
	private String securityGuaranteeUrl;
	/**
	 * 募集开始时间
	 */
	private Date collectBeginTime;
	/**
	 * 募集截止时间
	 */
	private Date collectEndTime;
	
	/**
	 * 收益转让协议名称
	 */
	private String ransferProtocalName;
	
	/**
	 * 收益转让协议URL
	 */
	private String ransferProtocalUrl;
	
	/**
	 * 投资者标签
	 */
	private String invLabel;
	
	/**
	 * 理财师标签
	 */
	private String lcsLabel;
	
	/**
	 * 销售奖励
	 */
	private String saleReward;
	
	/**
	 * 销售奖励比例
	 */
	private Double rewardRatio;
	/**
	 * 购买成功返回URl
	 */
	private String buySucceedReturnUrl;
	
	/**
	 * 销售奖励code(YEAR年化|PERCENTAGE百分比)
	 */
	private String rewardCode;
	/**
	 * 产品类型id
	 */
	private Integer productTypeId;
	
	
	public String getRewardCode() {
		return rewardCode;
	}
	
	public void setRewardCode(String rewardCode) {
		this.rewardCode = rewardCode;
	}
	

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getRepaymentTypeName() {
		return repaymentTypeName;
	}

	public void setRepaymentTypeName(String repaymentTypeName) {
		this.repaymentTypeName = repaymentTypeName;
	}

	public Double getRewardRatio() {
		return rewardRatio;
	}

	public void setRewardRatio(Double rewardRatio) {
		this.rewardRatio = rewardRatio;
	}

	public String getSaleReward() {
		return saleReward;
	}

	public void setSaleReward(String saleReward) {
		this.saleReward = saleReward;
	}

	public String getInvLabel() {
		return invLabel;
	}

	public void setInvLabel(String invLabel) {
		this.invLabel = invLabel;
	}

	public String getLcsLabel() {
		return lcsLabel;
	}

	public void setLcsLabel(String lcsLabel) {
		this.lcsLabel = lcsLabel;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(Integer typeValue) {
		this.typeValue = typeValue;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getProductProtocalId() {
		return productProtocalId;
	}
	public void setProductProtocalId(Integer productProtocalId) {
		this.productProtocalId = productProtocalId;
	}
	public String getProductProtocalName() {
		return productProtocalName;
	}
	public void setProductProtocalName(String productProtocalName) {
		this.productProtocalName = productProtocalName;
	}
	public Integer getIsFlow() {
		return isFlow;
	}
	public void setIsFlow(Integer isFlow) {
		this.isFlow = isFlow;
	}
	public Double getFixRate() {
		return fixRate;
	}
	public void setFixRate(Double fixRate) {
		this.fixRate = fixRate;
	}
	public Double getFlowMinRate() {
		return flowMinRate;
	}
	public void setFlowMinRate(Double flowMinRate) {
		this.flowMinRate = flowMinRate;
	}
	public Double getFlowMaxRate() {
		return flowMaxRate;
	}
	public void setFlowMaxRate(Double flowMaxRate) {
		this.flowMaxRate = flowMaxRate;
	}
	public Integer getDeadLineValue() {
		return deadLineValue;
	}
	public void setDeadLineValue(Integer deadLineValue) {
		this.deadLineValue = deadLineValue;
	}
	public Integer getDeadLineType() {
		return deadLineType;
	}
	public void setDeadLineType(Integer deadLineType) {
		this.deadLineType = deadLineType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getBuyedTotalPeople() {
		return buyedTotalPeople;
	}
	public void setBuyedTotalPeople(Integer buyedTotalPeople) {
		this.buyedTotalPeople = buyedTotalPeople;
	}
	public Double getBuyTotalMoney() {
		return buyTotalMoney;
	}
	public void setBuyTotalMoney(Double buyTotalMoney) {
		this.buyTotalMoney = buyTotalMoney;
	}
	public Double getBuyedTotalMoney() {
		return buyedTotalMoney;
	}
	public void setBuyedTotalMoney(Double buyedTotalMoney) {
		this.buyedTotalMoney = buyedTotalMoney;
	}
	public Double getRemaMoney() {
		return remaMoney;
	}
	public void setRemaMoney(Double remaMoney) {
		this.remaMoney = remaMoney;
	}
	public Date getValidBeginDate() {
		return validBeginDate;
	}
	public void setValidBeginDate(Date validBeginDate) {
		this.validBeginDate = validBeginDate;
	}
	public Date getValidEndDate() {
		return validEndDate;
	}
	public void setValidEndDate(Date validEndDate) {
		this.validEndDate = validEndDate;
	}
	public Integer getRepaymentWay() {
		return repaymentWay;
	}
	public void setRepaymentWay(Integer repaymentWay) {
		this.repaymentWay = repaymentWay;
	}
	public Double getBuyMinMoney() {
		return buyMinMoney;
	}
	public void setBuyMinMoney(Double buyMinMoney) {
		this.buyMinMoney = buyMinMoney;
	}
	public Double getBuyMaxMoney() {
		return buyMaxMoney;
	}
	public void setBuyMaxMoney(Double buyMaxMoney) {
		this.buyMaxMoney = buyMaxMoney;
	}
	public String getProductDescStr() {
		return productDescStr;
	}
	public void setProductDescStr(String productDescStr) {
		this.productDescStr = productDescStr;
	}
	public Integer getProductManageWay() {
		return productManageWay;
	}
	public void setProductManageWay(Integer productManageWay) {
		this.productManageWay = productManageWay;
	}
	public Integer getIsRedemption() {
		return isRedemption;
	}
	public void setIsRedemption(Integer isRedemption) {
		this.isRedemption = isRedemption;
	}
	public Double getBuyIncreaseMoney() {
		return buyIncreaseMoney;
	}
	public void setBuyIncreaseMoney(Double buyIncreaseMoney) {
		this.buyIncreaseMoney = buyIncreaseMoney;
	}
	public Double getCustBuyMaxMoney() {
		return custBuyMaxMoney;
	}
	public void setCustBuyMaxMoney(Double custBuyMaxMoney) {
		this.custBuyMaxMoney = custBuyMaxMoney;
	}
	public String getAcountBalance() {
		return acountBalance;
	}
	public void setAcountBalance(String acountBalance) {
		this.acountBalance = acountBalance;
	}
	public Double getFeeRatio() {
		return feeRatio;
	}
	public void setFeeRatio(Double feeRatio) {
		this.feeRatio = feeRatio;
	}
	public boolean isCfpRecommend() {
		return cfpRecommend;
	}
	public void setCfpRecommend(boolean cfpRecommend) {
		this.cfpRecommend = cfpRecommend;
	}
	public String getCurrentRecomendProduct() {
		return currentRecomendProduct;
	}
	public void setCurrentRecomendProduct(String currentRecomendProduct) {
		this.currentRecomendProduct = currentRecomendProduct;
	}
	public String getProductIllustrationUrl() {
		return productIllustrationUrl;
	}
	public void setProductIllustrationUrl(String productIllustrationUrl) {
		this.productIllustrationUrl = productIllustrationUrl;
	}
	public String getSecurityGuaranteeUrl() {
		return securityGuaranteeUrl;
	}
	public void setSecurityGuaranteeUrl(String securityGuaranteeUrl) {
		this.securityGuaranteeUrl = securityGuaranteeUrl;
	}
	public String getProductProtocalUrl() {
		return productProtocalUrl;
	}
	public void setProductProtocalUrl(String productProtocalUrl) {
		this.productProtocalUrl = productProtocalUrl;
	}
	public Date getCollectBeginTime() {
		return collectBeginTime;
	}
	public void setCollectBeginTime(Date collectBeginTime) {
		this.collectBeginTime = collectBeginTime;
	}
	public Date getCollectEndTime() {
		return collectEndTime;
	}
	public void setCollectEndTime(Date collectEndTime) {
		this.collectEndTime = collectEndTime;
	}
	public String getRansferProtocalName() {
		return ransferProtocalName;
	}
	public void setRansferProtocalName(String ransferProtocalName) {
		this.ransferProtocalName = ransferProtocalName;
	}
	public String getRansferProtocalUrl() {
		return ransferProtocalUrl;
	}
	public void setRansferProtocalUrl(String ransferProtocalUrl) {
		this.ransferProtocalUrl = ransferProtocalUrl;
	}

	public String getBuySucceedReturnUrl() {
		return buySucceedReturnUrl;
	}

	public void setBuySucceedReturnUrl(String buySucceedReturnUrl) {
		this.buySucceedReturnUrl = buySucceedReturnUrl;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

}
