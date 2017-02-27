package com.eshop4j.web.model.product;

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
 * @创建时间：2016年05月26日 17:36:52
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ProductProtocal extends BaseEntity {
	
	private static final long serialVersionUID = -7854945564306250480L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *协议名称
     */
	private String protocalName;
	
    /**
     *协议描述
     */
	private String protocalDesc;
	
    /**
     *协议文件URL
     */
	private String protocalFileUrl;
	
    /**
     *删除状态(0=正常|1=已删除)
     */
	private Byte delStatus;
	
    /**
     *创建者用户名
     */
	private String creator;
	
    /**
     *创建时间
     */
	private Date createTime;
	
    /**
     *更新者用户名
     */
	private String updater;
	
    /**
     *更新时间
     */
	private Date updateTime;
	
    /**
     *备注
     */
	private String remark;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setProtocalName(String protocalName){
		this.protocalName = protocalName;
	}
	
	public String getProtocalName(){
		return protocalName;
	}
	
	public void setProtocalDesc(String protocalDesc){
		this.protocalDesc = protocalDesc;
	}
	
	public String getProtocalDesc(){
		return protocalDesc;
	}
	
	public void setProtocalFileUrl(String protocalFileUrl){
		this.protocalFileUrl = protocalFileUrl;
	}
	
	public String getProtocalFileUrl(){
		return protocalFileUrl;
	}
	
	public void setDelStatus(Byte delStatus){
		this.delStatus = delStatus;
	}
	
	public Byte getDelStatus(){
		return delStatus;
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
	

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

