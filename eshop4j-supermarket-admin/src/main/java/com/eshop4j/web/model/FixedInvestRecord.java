package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.eshop4j.core.base.BaseEntity;
 import java.lang.Byte;
 import java.lang.Integer;
 import java.lang.Long;
 import java.lang.String;
 import java.math.BigDecimal;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月07日 10:42:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class FixedInvestRecord extends BaseEntity {
	
	private static final long serialVersionUID = 3625951331734709011L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *投资记录ID(系统产生的唯一标识)
     */
	private String investId;
	
    /**
     *名称
     */
	private String name;
	
    /**
     *业务日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date bizTime;
	
    /**
     *用户ID
     */
	private String userId;
	
    /**
     *产品ID
     */
	private String productId;
	
    /**
     *计息日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date startTime;
	
    /**
     *回款日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date endTime;
	
    /**
     *购买本金
     */
	private BigDecimal investAmt;
	
    /**
     *收益
     */
	private BigDecimal profit;
	
    /**
     *精准收益
     */
	private BigDecimal accurateProfit;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *状态(1=初始申请|2=投资成功|3=回款成功)
     */
	private Byte status;
	
    /**
     *1=固定(默认),2=浮动,3-基金
     */
	private Byte isFlow;
	
    /**
     *最大收益
     */
	private BigDecimal maxProfit;
	
    /**
     *产品名称
     */
	private String productName;
	
    /**
     *来源平台
     */
	private String platfrom;
	
    /**
     *来源渠道
     */
	private String channel;
	
    /**
     *返息处理时间
     */
	private Date handlerTime;
	
    /**
     *平台返回投资流水号
     */
	private String investRecordNo;
	
    /**
     *期限类型(1=天数|2=自然月)
     */
	private Byte deadLineType;
	
    /**
     *产品期限天数或月数值
     */
	private Integer deadLineValue;
	
    /**
     *初始时间
     */
	private Long initDate;
	
    /**
     *创建时间
     */
	private Date createTime;
	
    /**
     *年利率
     */
	private BigDecimal rate;
	
    /**
     *扩展字段，json格式标注
     */
	private String extendText;


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setInvestId(String investId){
		this.investId = investId;
	}
	
	public String getInvestId(){
		return investId;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setBizTime(Date bizTime){
		this.bizTime = bizTime;
	}
	
	public Date getBizTime(){
		return bizTime;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public Date getStartTime(){
		return startTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	public Date getEndTime(){
		return endTime;
	}
	
	public void setInvestAmt(BigDecimal investAmt){
		this.investAmt = investAmt;
	}
	
	public BigDecimal getInvestAmt(){
		return investAmt;
	}
	
	public void setProfit(BigDecimal profit){
		this.profit = profit;
	}
	
	public BigDecimal getProfit(){
		return profit;
	}
	
	public void setAccurateProfit(BigDecimal accurateProfit){
		this.accurateProfit = accurateProfit;
	}
	
	public BigDecimal getAccurateProfit(){
		return accurateProfit;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setStatus(Byte status){
		this.status = status;
	}
	
	public Byte getStatus(){
		return status;
	}
	
	public void setIsFlow(Byte isFlow){
		this.isFlow = isFlow;
	}
	
	public Byte getIsFlow(){
		return isFlow;
	}
	
	public void setMaxProfit(BigDecimal maxProfit){
		this.maxProfit = maxProfit;
	}
	
	public BigDecimal getMaxProfit(){
		return maxProfit;
	}
	
	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public String getProductName(){
		return productName;
	}
	
	public void setPlatfrom(String platfrom){
		this.platfrom = platfrom;
	}
	
	public String getPlatfrom(){
		return platfrom;
	}
	
	public void setChannel(String channel){
		this.channel = channel;
	}
	
	public String getChannel(){
		return channel;
	}
	
	public void setHandlerTime(Date handlerTime){
		this.handlerTime = handlerTime;
	}
	
	public Date getHandlerTime(){
		return handlerTime;
	}
	
	public void setInvestRecordNo(String investRecordNo){
		this.investRecordNo = investRecordNo;
	}
	
	public String getInvestRecordNo(){
		return investRecordNo;
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
	
	public void setInitDate(Long initDate){
		this.initDate = initDate;
	}
	
	public Long getInitDate(){
		return initDate;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setRate(BigDecimal rate){
		this.rate = rate;
	}
	
	public BigDecimal getRate(){
		return rate;
	}
	
	public void setExtendText(String extendText){
		this.extendText = extendText;
	}
	
	public String getExtendText(){
		return extendText;
	}
	
}

