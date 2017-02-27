package com.eshop4j.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月21日 17:02:43
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimProductExtends implements Serializable {
	
	private static final long serialVersionUID = -8532657432075247749L;
	
    /**
     *主键
     */
	private Integer id;
	
    /**
     *产品uuid
     */
	private String productId;
	
    /**
     *分享描述
     */
	private String shareDesc;
	
    /**
     *分享图片链接
     */
	private String shareImgurl;
	
    /**
     *分享链接
     */
	private String shareLink;
	
    /**
     *分享标题
     */
	private String shareTitle;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date creatTime;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public String getProductId(){
		return productId;
	}
	
	public void setShareDesc(String shareDesc){
		this.shareDesc = shareDesc;
	}
	
	public String getShareDesc(){
		return shareDesc;
	}
	
	public void setShareImgurl(String shareImgurl){
		this.shareImgurl = shareImgurl;
	}
	
	public String getShareImgurl(){
		return shareImgurl;
	}
	
	public void setShareLink(String shareLink){
		this.shareLink = shareLink;
	}
	
	public String getShareLink(){
		return shareLink;
	}
	
	public void setShareTitle(String shareTitle){
		this.shareTitle = shareTitle;
	}
	
	public String getShareTitle(){
		return shareTitle;
	}
	
	public void setCreatTime(Date creatTime){
		this.creatTime = creatTime;
	}
	
	public Date getCreatTime(){
		return creatTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
}

