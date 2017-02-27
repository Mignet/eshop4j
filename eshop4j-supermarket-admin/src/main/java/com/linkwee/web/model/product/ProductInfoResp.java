package com.linkwee.web.model.product;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.linkwee.core.base.BaseEntity;

public class ProductInfoResp extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6535074673518232348L;
	
	private String productId;
	private String productName;//产品名称
	private Date validBeginDate;//起息日
	private String validEndDate;//到期日
	private String productCate;//产品类型
	@NotNull(message="产品总额不能为空")
	private Double buyTotalMoney;//c产品总额
	@NotNull(message="起投金额不能为空")
	private Double buyMinMoney;//起投金额
	@NotNull(message="递增金额不能为空")
	private Double buyIncreaseMoney;//递增金额
	@NotNull(message="用户购买上限不能为空")
	private Double custBuyMaxMoney;//当用户购买上线
	private String productIllustrationUrl;//产品说明页面URL
	private String securityGuaranteeUrl;//产品安全保障页
	private Byte isRecommended;//首页推荐
	
	private Double fixRate; //年化收益
	private String lcsCornerIco;//
	private String invCornerIco;//角标
	private String productProtocalName;//协议名称
	private String productProtocalId;//产品购买协议ID
	private String ransferProtocalName;//协议名称
	private String ransferProtocalUrl;//收益转让协议
	private String invLabel1;
	private String invLabel2;
	private String lcsLabel1;
	private String lcsLabel2;
	private String invLable;
	private String lcsLable;
	private Double feeRatio;//年化佣金
	private Double saleReward;//销售奖励
	private Date beginSaleTime;//开售时间
	private int beginSaleType;//即时 定时 预售
	private Integer cateSort;//分类排序
	private Byte isListRecommended;//是否列表推荐
	private Integer listRecommendedSort;//列表推荐排序
	private String listRecomendedStr;//列表推荐语
	private String invDtlPageDes;//详情页推荐语(投资端)
	private String lcsDtlPageDes;//详情页推荐语（理财师端）
	
	private Byte deadLineType = 1;
	private Integer deadLineValue;//产品期限
	
	
	private Byte interestWay=2;//起息方式
	private Integer interestWayType;//募集完成or固定日期
	private Byte repaymentWay;//收益方式（还本付息方式）
	
	private Double buyMaxMoney;
	private Double showIndex;//分类显示顺序？
	private Byte tenderWay = 1;//投标方式(1=手动|2=自动|默认为1)
	private Byte isRedemption =1;//是否可赎回(1=不赎回|2=赎回)
	private Byte sourceWay = 1;//来源方式(1=公司内部产品|2=公司外部产品)
	private Byte moneyType = 1;//货币类型(1=RMB|2=港币|3=美元)
	private Byte riskControlType =1;//风控类型(1=抵押|2=担保|3=信贷)
	private Byte riskLevel = 1;//风险级别(1=一般|2=重要|3=紧急|4=非常紧急)
	private Byte flowLevel =1;//流动性级别
	private Byte productManageWay = 0;
	private Byte status;
	private String creator;
	private Date createTime;
	private String remark;
	private String openType;//打开方式 原页 新页面
	private String greyFlag;//灰度是否开启
	/**
	 * 募集类信息
	 */
	private Byte isCollect;//是否需要募集开始及截止时间(1=不需要|2=需要)
	private Date collectEndTime;//募集结束时间
	private int hasCollectRate;//募集期间投资客户是否有收益
	private Double collectRate;//募集期间产品收益
	private int hasCollectRatio;//募集期是否有佣金
	private Double collectRatio;//募集期间佣金率
	//private String[] bannerPics;//banner图片 前端没实现暂时不处理
	private Integer productTypeId;//天天牛or日益宝
	//private Date validBeginDate;//f_valid_begin_date
	private Integer collectLineMinValue;//募集产品最小期限天数或月数值或年数值
	private Integer collectLineMaxValue;//募集产品最大期限天数或月数值或年数值
	private Date collectLineBackDatetime;
	private Date collectFinshTime;//募集完成时间

	private String description;
	private String saleStatus;
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
	public Double getBuyTotalMoney() {
		return buyTotalMoney;
	}
	public void setBuyTotalMoney(Double buyTotalMoney) {
		this.buyTotalMoney = buyTotalMoney;
	}
	public Double getBuyMinMoney() {
		return buyMinMoney;
	}
	public void setBuyMinMoney(Double buyMinMoney) {
		this.buyMinMoney = buyMinMoney;
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
	public Byte getIsRecommended() {
		return isRecommended;
	}
	public void setIsRecommended(Byte isRecommended) {
		this.isRecommended = isRecommended;
	}
	public Double getFixRate() {
		return fixRate;
	}
	public void setFixRate(Double fixRate) {
		this.fixRate = fixRate;
	}
	public String getLcsCornerIco() {
		return lcsCornerIco;
	}
	public void setLcsCornerIco(String lcsCornerIco) {
		this.lcsCornerIco = lcsCornerIco;
	}
	public String getInvCornerIco() {
		return invCornerIco;
	}
	public void setInvCornerIco(String invCornerIco) {
		this.invCornerIco = invCornerIco;
	}
	public String getProductProtocalId() {
		return productProtocalId;
	}
	public void setProductProtocalId(String productProtocalId) {
		this.productProtocalId = productProtocalId;
	}
	public String getInvLabel1() {
		return invLabel1;
	}
	public void setInvLabel1(String invLabel1) {
		this.invLabel1 = invLabel1;
	}
	public String getInvLabel2() {
		return invLabel2;
	}
	public void setInvLabel2(String invLabel2) {
		this.invLabel2 = invLabel2;
	}
	public String getLcsLabel1() {
		return lcsLabel1;
	}
	public void setLcsLabel1(String lcsLabel1) {
		this.lcsLabel1 = lcsLabel1;
	}

	public String getLcsLabel2() {
		return lcsLabel2;
	}
	public void setLcsLabel2(String lcsLabel2) {
		this.lcsLabel2 = lcsLabel2;
	}
	public String getInvLable() {
		return invLable;
	}
	public void setInvLable(String invLable) {
		this.invLable = invLable;
	}
	public String getLcsLable() {
		return lcsLable;
	}
	public void setLcsLable(String lcsLable) {
		this.lcsLable = lcsLable;
	}
	public Double getFeeRatio() {
		return feeRatio;
	}
	public void setFeeRatio(Double feeRatio) {
		this.feeRatio = feeRatio;
	}
	public Double getSaleReward() {
		return saleReward;
	}
	public void setSaleReward(Double saleReward) {
		this.saleReward = saleReward;
	}
	public Date getBeginSaleTime() {
		return beginSaleTime;
	}
	public void setBeginSaleTime(Date beginSaleTime) {
		this.beginSaleTime = beginSaleTime;
	}
	public int getBeginSaleType() {
		return beginSaleType;
	}
	public void setBeginSaleType(int beginSaleType) {
		this.beginSaleType = beginSaleType;
	}
	public Integer getCateSort() {
		return cateSort;
	}
	public void setCateSort(Integer cateSort) {
		this.cateSort = cateSort;
	}
	public Byte getDeadLineType() {
		return deadLineType;
	}
	public void setDeadLineType(Byte deadLineType) {
		this.deadLineType = deadLineType;
	}
	public Integer getDeadLineValue() {
		return deadLineValue;
	}
	public void setDeadLineValue(Integer deadLineValue) {
		this.deadLineValue = deadLineValue;
	}
	public Byte getInterestWay() {
		return interestWay;
	}
	public void setInterestWay(Byte interestWay) {
		this.interestWay = interestWay;
	}
	public Double getBuyMaxMoney() {
		return buyMaxMoney;
	}
	public void setBuyMaxMoney(Double buyMaxMoney) {
		this.buyMaxMoney = buyMaxMoney;
	}
	public Double getShowIndex() {
		return showIndex;
	}
	public void setShowIndex(Double showIndex) {
		this.showIndex = showIndex;
	}
	public Byte getTenderWay() {
		return tenderWay;
	}
	public void setTenderWay(Byte tenderWay) {
		this.tenderWay = tenderWay;
	}
	public Byte getIsRedemption() {
		return isRedemption;
	}
	public void setIsRedemption(Byte isRedemption) {
		this.isRedemption = isRedemption;
	}
	public Byte getSourceWay() {
		return sourceWay;
	}
	public void setSourceWay(Byte sourceWay) {
		this.sourceWay = sourceWay;
	}
	public Byte getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(Byte moneyType) {
		this.moneyType = moneyType;
	}
	public Byte getRiskControlType() {
		return riskControlType;
	}
	public void setRiskControlType(Byte riskControlType) {
		this.riskControlType = riskControlType;
	}
	public Byte getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(Byte riskLevel) {
		this.riskLevel = riskLevel;
	}
	public Byte getFlowLevel() {
		return flowLevel;
	}
	public void setFlowLevel(Byte flowLevel) {
		this.flowLevel = flowLevel;
	}
	public Byte getProductManageWay() {
		return productManageWay;
	}
	public void setProductManageWay(Byte productManageWay) {
		this.productManageWay = productManageWay;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}
	public String getProductCate() {
		return productCate;
	}
	public void setProductCate(String productCate) {
		this.productCate = productCate;
	}
	public Byte getIsListRecommended() {
		return isListRecommended;
	}
	public void setIsListRecommended(Byte isListRecommended) {
		this.isListRecommended = isListRecommended;
	}
	public Integer getListRecommendedSort() {
		return listRecommendedSort;
	}
	public void setListRecommendedSort(Integer listRecommendedSort) {
		this.listRecommendedSort = listRecommendedSort;
	}
	public Date getValidBeginDate() {
		return validBeginDate;
	}
	public void setValidBeginDate(Date validBeginDate) {
		this.validBeginDate = validBeginDate;
	}
	public String getValidEndDate() {
		return validEndDate;
	}
	public void setValidEndDate(String validEndDate) {
		this.validEndDate = validEndDate;
	}
	public String getRansferProtocalUrl() {
		return ransferProtocalUrl;
	}
	public void setRansferProtocalUrl(String ransferProtocalUrl) {
		this.ransferProtocalUrl = ransferProtocalUrl;
	}
	public String getListRecomendedStr() {
		return listRecomendedStr;
	}
	public void setListRecomendedStr(String listRecomendedStr) {
		this.listRecomendedStr = listRecomendedStr;
	}
	
	public String getInvDtlPageDes() {
		return invDtlPageDes;
	}
	public void setInvDtlPageDes(String invDtlPageDes) {
		this.invDtlPageDes = invDtlPageDes;
	}
	public String getLcsDtlPageDes() {
		return lcsDtlPageDes;
	}
	public void setLcsDtlPageDes(String lcsDtlPageDes) {
		this.lcsDtlPageDes = lcsDtlPageDes;
	}
	
	public String getRansferProtocalName() {
		return ransferProtocalName;
	}
	public void setRansferProtocalName(String ransferProtocalName) {
		this.ransferProtocalName = ransferProtocalName;
	}
	public String getOpenType() {
		return openType;
	}
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	
	public String getGreyFlag() {
		return greyFlag;
	}
	public void setGreyFlag(String greyFlag) {
		this.greyFlag = greyFlag;
	}
	
	public String getProductProtocalName() {
		return productProtocalName;
	}
	public void setProductProtocalName(String productProtocalName) {
		this.productProtocalName = productProtocalName;
	}
	
	public Byte getRepaymentWay() {
		return repaymentWay;
	}
	public void setRepaymentWay(Byte repaymentWay) {
		this.repaymentWay = repaymentWay;
	}
	public boolean getCanShowIndexRecomonded(){
		if("0".equals(this.getGreyFlag()) && ("1".equals(this.saleStatus) || "2".equals(this.getSaleStatus()))){
			return true;
		}else{
			return false;
		}
	}
	
	public Integer getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
	
	public Integer getInterestWayType() {
		return interestWayType;
	}
	public void setInterestWayType(Integer interestWayType) {
		this.interestWayType = interestWayType;
	}
	public Byte getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(Byte isCollect) {
		this.isCollect = isCollect;
	}
	public Date getCollectEndTime() {
		return collectEndTime;
	}
	public void setCollectEndTime(Date collectEndTime) {
		this.collectEndTime = collectEndTime;
	}
	public int getHasCollectRate() {
		return hasCollectRate;
	}
	public void setHasCollectRate(int hasCollectRate) {
		this.hasCollectRate = hasCollectRate;
	}
	public Double getCollectRate() {
		return collectRate;
	}
	public void setCollectRate(Double collectRate) {
		this.collectRate = collectRate;
	}
	public int getHasCollectRatio() {
		return hasCollectRatio;
	}
	public void setHasCollectRatio(int hasCollectRatio) {
		this.hasCollectRatio = hasCollectRatio;
	}
	public Double getCollectRatio() {
		return collectRatio;
	}
	public void setCollectRatio(Double collectRatio) {
		this.collectRatio = collectRatio;
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
	
	public Date getCollectFinshTime() {
		return collectFinshTime;
	}
	public void setCollectFinshTime(Date collectFinshTime) {
		this.collectFinshTime = collectFinshTime;
	}
	@Override
	public String toString() {
		return "ProductInfoResp [productId=" + productId + ", productName="
				+ productName + ", validBeginDate=" + validBeginDate
				+ ", validEndDate=" + validEndDate + ", productCate="
				+ productCate + ", buyTotalMoney=" + buyTotalMoney
				+ ", buyMinMoney=" + buyMinMoney + ", buyIncreaseMoney="
				+ buyIncreaseMoney + ", custBuyMaxMoney=" + custBuyMaxMoney
				+ ", productIllustrationUrl=" + productIllustrationUrl
				+ ", securityGuaranteeUrl=" + securityGuaranteeUrl
				+ ", isRecommended=" + isRecommended + ", fixRate=" + fixRate
				+ ", lcsCornerIco=" + lcsCornerIco + ", invCornerIco="
				+ invCornerIco + ", productProtocalId=" + productProtocalId
				+ ", ransferProtocalUrl=" + ransferProtocalUrl + ", invLabel1="
				+ invLabel1 + ", invLabel2=" + invLabel2 + ", lcsLabel1="
				+ lcsLabel1 + ", lcsLable2=" + lcsLabel2 + ", invLable="
				+ invLable + ", lcsLable=" + lcsLable + ", feeRatio="
				+ feeRatio + ", saleReward=" + saleReward + ", beginSaleTime="
				+ beginSaleTime + ", beginSaleType=" + beginSaleType
				+ ", cateSort=" + cateSort + ", isListRecommended="
				+ isListRecommended + ", listRecommendedSort="
				+ listRecommendedSort + ", listRecomendedStr="
				+ listRecomendedStr + ", deadLineType=" + deadLineType
				+ ", deadLineValue=" + deadLineValue + ", interestWay="
				+ interestWay + ", buyMaxMoney=" + buyMaxMoney + ", showIndex="
				+ showIndex + ", tenderWay=" + tenderWay + ", isRedemption="
				+ isRedemption + ", sourceWay=" + sourceWay + ", moneyType="
				+ moneyType + ", riskControlType=" + riskControlType
				+ ", riskLevel=" + riskLevel + ", flowLevel=" + flowLevel
				+ ", productManageWay=" + productManageWay + ", status="
				+ status + ", creator=" + creator + ", createTime="
				+ createTime + ", remark=" + remark + ", description="
				+ description + ", saleStatus=" + saleStatus + "]";
	}
	
	
	
	
}
