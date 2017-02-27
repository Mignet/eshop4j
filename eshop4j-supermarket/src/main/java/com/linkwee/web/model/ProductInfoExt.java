package com.linkwee.web.model;

import java.io.Serializable;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年11月17日 17:19:46
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ProductInfoExt implements Serializable {
	
	private static final long serialVersionUID = -1807547505821590642L;
	
    /**
     *产品id
     */
	private String productId;
	
    /**
     *促销语
     */
	private String promotionText;
	
    /**
     *佣金结算方式
     */
	private String commissionWay;
	
    /**
     *产品描述
     */
	private String productDesc;
	
    /**
     *产品类别名
     */
	private String typeName;
	
    /**
     *显示进度 0无进度,1有进度
     */
	private Integer progress;
	
    /**
     *产品详情的链接地址
     */
	private String productInfoUrl;
	
    /**
     *安全保障的链接地址
     */
	private String productSafeUrl;
	
    /**
     *展示状态:0默认展示;1展示在首页;2展示在推荐;3不展示
     */
	private Integer showStatus;
	
    /**
     *展示顺序(1-100)
     */
	private Integer showOrder;
	


	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setPromotionText(String promotionText){
		this.promotionText = promotionText;
	}
	
	public String getPromotionText(){
		return promotionText;
	}
	
	public void setCommissionWay(String commissionWay){
		this.commissionWay = commissionWay;
	}
	
	public String getCommissionWay(){
		return commissionWay;
	}
	
	public void setProductDesc(String productDesc){
		this.productDesc = productDesc;
	}
	
	public String getProductDesc(){
		return productDesc;
	}
	
	public void setTypeName(String typeName){
		this.typeName = typeName;
	}
	
	public String getTypeName(){
		return typeName;
	}
	
	public void setProgress(Integer progress){
		this.progress = progress;
	}
	
	public Integer getProgress(){
		return progress;
	}
	
	public void setProductInfoUrl(String productInfoUrl){
		this.productInfoUrl = productInfoUrl;
	}
	
	public String getProductInfoUrl(){
		return productInfoUrl;
	}
	
	public void setProductSafeUrl(String productSafeUrl){
		this.productSafeUrl = productSafeUrl;
	}
	
	public String getProductSafeUrl(){
		return productSafeUrl;
	}
	
	public void setShowStatus(Integer showStatus){
		this.showStatus = showStatus;
	}
	
	public Integer getShowStatus(){
		return showStatus;
	}
	
	public void setShowOrder(Integer showOrder){
		this.showOrder = showOrder;
	}
	
	public Integer getShowOrder(){
		return showOrder;
	}
	
}

