package com.linkwee.web.model;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月27日 19:07:06
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class Tproduct extends BaseEntity {
	
	private static final long serialVersionUID = -2915636681794882348L;
	
    /**
     *
     */
	private Integer id;
	
    /**
     *
     */
	private String productName;
	
    /**
     *
     */
	private String productDesc;
	
    /**
     *
     */
	private String productTypeId;
	
    /**
     *
     */
	private Byte deadLineType;
	
    /**
     *
     */
	private Integer deadLineValue;
	
    /**
     *
     */
	private String fid;
	
    /**
     *
     */
	private Byte fdelstatus;
	
    /**
     *
     */
	private Date fcreatetime;
	
    /**
     *
     */
	private Date fupdatetime;
	
    /**
     *募集开始时间|格式yyyy-MM-ddHH:mm:ss
     */
	private Date collectBeginTime;
	
    /**
     *募集截止时间可以为nul格式yyyy-MM-ddHH:mm:ss
     */
	private Date collectEndTime;
	
    /**
     *募集完成时间，相比募集截止时间可能会提前的募集已完成的时间条件|已达到募集截止时间(COLLECT_END_TIME)或者已达到产品总额度数(BUY_TOTAL_MONEY)格式|yyyy-MM-ddHH:mm:ss
     */
	private Date collectFinishTime;
	
    /**
     *起息方式(1=购买当日|2=购买次日|3=募集完成当日|4=募集完成次日|5=指定日期)
     */
	private String interestWay;
	
    /**
     *产品起息日格式yyyy-MM-dd
     */
	private Date validBeginDate;
	
    /**
     *产品到期日格式yyyy-MM-dd
     */
	private Date validEndDate;
	
    /**
     *固定利率
     */
	private BigDecimal fixRate;
	
    /**
     *浮动最小利率
     */
	private BigDecimal flowMinRate;
	
    /**
     *浮动最大利率
     */
	private BigDecimal flowMaxRate;
	
    /**
     *是否浮动利率(1=固定利率|2=浮动利率)
     */
	private String isFlow;
	
    /**
     *
     */
	private String fbiznumber;
	 /**
     *募集产品最小期限天数或月数值或年数值
     */
	private Integer collectLineMinValue;
	
    /**
     *募集产品最大期限天数或月数值或年数值
     */
	private Integer collectLineMaxValue;
	
    /**
     *募集产品募集期利率
     */
	private BigDecimal collectRate;
	
    /**
     *产品销售状态:1-预售、2-在售、3-售罄、4-下架、5-定时发售、6-募集中、7-募集失败、8-募集成功
     */
	private String saleStatus;
	
    /**
     *产品类型(1=天添牛|2=指数牛|3=活期宝 | 4= 惠房宝|5=日益宝)
     */
	private Integer typeValue;
	


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
	
	public void setProductTypeId(String productTypeId){
		this.productTypeId = productTypeId;
	}
	
	public String getProductTypeId(){
		return productTypeId;
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
	
	public void setFid(String fid){
		this.fid = fid;
	}
	
	public String getFid(){
		return fid;
	}
	
	public void setFdelstatus(Byte fdelstatus){
		this.fdelstatus = fdelstatus;
	}
	
	public Byte getFdelstatus(){
		return fdelstatus;
	}
	
	public void setFcreatetime(Date fcreatetime){
		this.fcreatetime = fcreatetime;
	}
	
	public Date getFcreatetime(){
		return fcreatetime;
	}
	
	public void setFupdatetime(Date fupdatetime){
		this.fupdatetime = fupdatetime;
	}
	
	public Date getFupdatetime(){
		return fupdatetime;
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
	
	public void setCollectFinishTime(Date collectFinishTime){
		this.collectFinishTime = collectFinishTime;
	}
	
	public Date getCollectFinishTime(){
		return collectFinishTime;
	}
	
	public void setInterestWay(String interestWay){
		this.interestWay = interestWay;
	}
	
	public String getInterestWay(){
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
	
	public void setFixRate(BigDecimal fixRate){
		this.fixRate = fixRate;
	}
	
	public BigDecimal getFixRate(){
		return fixRate;
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
	
	public void setIsFlow(String isFlow){
		this.isFlow = isFlow;
	}
	
	public String getIsFlow(){
		return isFlow;
	}
	
	public void setFbiznumber(String fbiznumber){
		this.fbiznumber = fbiznumber;
	}
	
	public String getFbiznumber(){
		return fbiznumber;
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

	public BigDecimal getCollectRate() {
		return collectRate;
	}

	public void setCollectRate(BigDecimal collectRate) {
		this.collectRate = collectRate;
	}

	public String getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	public Integer getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(Integer typeValue) {
		this.typeValue = typeValue;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

