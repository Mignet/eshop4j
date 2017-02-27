package com.linkwee.web.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.enums.cim.AuditStatusEnum;
import com.linkwee.web.enums.cim.ProductTypeEnums;
import com.linkwee.web.enums.cim.StatusEnum;
import com.linkwee.web.model.CimProductInfoCate;

public class ProductListForManageResponse implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     *自增长主键
     */
	private Integer id;
    /**
     *产品名称
     */
	private String productName;
	
    /**
     *产品类型(1=P2P|2=信托 |3=资管|4=基金|401=公募基金|402=阳光私募|403=股权基金|5=保险|6=众筹|999=其他)
     */
	private Integer productType;
	
    /**
     *产品销售开始时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date saleStartTime;
	
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
     *产品总额度产品募集要达到的总额度数不能为空作为募集完成时间确定条件之一
     */
	private BigDecimal buyTotalMoney;
	
    /**
     *移动端显示排序索引
     */
	private Integer showIndex;
	
    /**
     *产品状态(1-在售|2-售罄|3-募集失败)
     */
	private Integer status;
	
    /**
     *产品ID
     */
	private String productId;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
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
	 * 上架时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date auditPassTime;
	/**
     * 产品类型Text
     */
	private String productTypeText;
    /**
     * 产品状态Text
     */
	private String statusText;
	/**
	 * 审核状态Text
	 */
	private String auditStatusText;
	/**
	 * 期限\天Text
	 */
	private String deadLineValueText;
	/**
	 * 产品利率Text
	 */
	private String productRateText;
    /**
     *  机构名称
     */
	private String orgName;
    /**
     *  热门产品排序字段
     */
	private Integer hotIndex;
	/**
	 * 产品分类关联
	 */
	private List<CimProductInfoCate> cimProductInfoCateList;
	
	public Date getAuditPassTime() {
		if(getAuditStatus() == 1){
			auditPassTime = getAuditTime();
		}
		return auditPassTime;
	}
	public void setAuditPassTime(Date auditPassTime) {
		this.auditPassTime = auditPassTime;
	}
	public String getProductTypeText() {
    	productTypeText = EnumUtils.getValueByKeyNull(getProductType(), ProductTypeEnums.values());
		return productTypeText;
	}
	public void setProductTypeText(String productTypeText) {
		this.productTypeText = productTypeText;
	}
	public String getStatusText() {	
	   	statusText = EnumUtils.getValueByKeyNull(getStatus(), StatusEnum.values());
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getAuditStatusText() {
    	auditStatusText = EnumUtils.getValueByKeyNull(getAuditStatus(), AuditStatusEnum.values());
		return auditStatusText;
	}
	public void setAuditStatusText(String auditStatusText) {
		this.auditStatusText = auditStatusText;
	}
	public String getDeadLineValueText() {
		if (getIsFixedDeadline() == 1){
			if(StringUtils.isNotBlank(getDeadLineMinSelfDefined())){
				deadLineValueText = getDeadLineMinSelfDefined();
			} else {
				deadLineValueText = getDeadLineMinValue()+"天";
			}
		} else {
			if(StringUtils.isNotBlank(getDeadLineMinSelfDefined()) && StringUtils.isNotBlank(getDeadLineMaxSelfDefined())){
				deadLineValueText = getDeadLineMinSelfDefined()+"~"+getDeadLineMaxSelfDefined();
			} else {
				deadLineValueText = getDeadLineMinValue()+"天~"+getDeadLineMaxValue()+"天";
			}
		}
		return deadLineValueText;
	}
	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
	}
	public String getProductRateText() {
		if(getIsFlow() == 1){
			productRateText = getFlowMinRate()+"%";
		} else if(getIsFlow() == 2){
			productRateText = getFlowMinRate()+"%~"+getFlowMaxRate()+"%";
		}
		return productRateText;
	}
	public void setProductRateText(String productRateText) {
		this.productRateText = productRateText;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Integer getHotIndex() {
		return hotIndex;
	}
	public void setHotIndex(Integer hotIndex) {
		this.hotIndex = hotIndex;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductType() {
		return productType;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	public Date getSaleStartTime() {
		return saleStartTime;
	}
	public void setSaleStartTime(Date saleStartTime) {
		this.saleStartTime = saleStartTime;
	}
	public Integer getIsFlow() {
		return isFlow;
	}
	public void setIsFlow(Integer isFlow) {
		this.isFlow = isFlow;
	}
	public BigDecimal getFlowMinRate() {
		return flowMinRate;
	}
	public void setFlowMinRate(BigDecimal flowMinRate) {
		this.flowMinRate = flowMinRate;
	}
	public BigDecimal getFlowMaxRate() {
		return flowMaxRate;
	}
	public void setFlowMaxRate(BigDecimal flowMaxRate) {
		this.flowMaxRate = flowMaxRate;
	}
	public Integer getIsFixedDeadline() {
		return isFixedDeadline;
	}
	public void setIsFixedDeadline(Integer isFixedDeadline) {
		this.isFixedDeadline = isFixedDeadline;
	}
	public Integer getDeadLineMinValue() {
		return deadLineMinValue;
	}
	public void setDeadLineMinValue(Integer deadLineMinValue) {
		this.deadLineMinValue = deadLineMinValue;
	}
	public Integer getDeadLineMaxValue() {
		return deadLineMaxValue;
	}
	public void setDeadLineMaxValue(Integer deadLineMaxValue) {
		this.deadLineMaxValue = deadLineMaxValue;
	}
	public String getDeadLineMinSelfDefined() {
		return deadLineMinSelfDefined;
	}
	public void setDeadLineMinSelfDefined(String deadLineMinSelfDefined) {
		this.deadLineMinSelfDefined = deadLineMinSelfDefined;
	}
	public String getDeadLineMaxSelfDefined() {
		return deadLineMaxSelfDefined;
	}
	public void setDeadLineMaxSelfDefined(String deadLineMaxSelfDefined) {
		this.deadLineMaxSelfDefined = deadLineMaxSelfDefined;
	}
	public BigDecimal getBuyTotalMoney() {
		return buyTotalMoney;
	}
	public void setBuyTotalMoney(BigDecimal buyTotalMoney) {
		this.buyTotalMoney = buyTotalMoney;
	}
	public Integer getShowIndex() {
		return showIndex;
	}
	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getOrgNumber() {
		return orgNumber;
	}
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
	public BigDecimal getFeeRatio() {
		return feeRatio;
	}
	public void setFeeRatio(BigDecimal feeRatio) {
		this.feeRatio = feeRatio;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public List<CimProductInfoCate> getCimProductInfoCateList() {
		return cimProductInfoCateList;
	}
	public void setCimProductInfoCateList(
			List<CimProductInfoCate> cimProductInfoCateList) {
		this.cimProductInfoCateList = cimProductInfoCateList;
	}
}
