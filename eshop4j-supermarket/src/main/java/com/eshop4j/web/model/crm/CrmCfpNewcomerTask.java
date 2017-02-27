package com.eshop4j.web.model.crm;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月09日 14:24:19
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmCfpNewcomerTask implements Serializable {
	
	private static final long serialVersionUID = -2763062497438008553L;
	
    /**
     *自增ID
     */
	private Integer id;
	
    /**
     *用户ID
     */
	private String userId;
	
    /**
     *邀请客户完成状态： 0未完成，1已完成, 2已领取
     */
	private Integer inviteCustomerStatus;
	
    /**
     *邀请理财师完成状态： 0未完成，1已完成, 2已领取
     */
	private Integer inviteCfplannerStatus;
	
    /**
     *推荐产品完成状态： 0未完成，1已完成, 2已领取
     */
	private Integer recommendProductStatus;
	
    /**
     *推荐平台完成状态： 0未完成，1已完成, 2已领取
     */
	private Integer recommendPlatformStatus;
	
    /**
     *发红包完成状态： 0未完成，1已完成, 2已领取
     */
	private Integer grantHongbaoStatus;
	
    /**
     *查看收益完成状态： 0未完成，1已完成, 2已领取
     */
	private Integer seeProfitStatus;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *最后修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date lastUpdateTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setInviteCustomerStatus(Integer inviteCustomerStatus){
		this.inviteCustomerStatus = inviteCustomerStatus;
	}
	
	public Integer getInviteCustomerStatus(){
		return inviteCustomerStatus;
	}
	
	public void setInviteCfplannerStatus(Integer inviteCfplannerStatus){
		this.inviteCfplannerStatus = inviteCfplannerStatus;
	}
	
	public Integer getInviteCfplannerStatus(){
		return inviteCfplannerStatus;
	}
	
	public void setRecommendProductStatus(Integer recommendProductStatus){
		this.recommendProductStatus = recommendProductStatus;
	}
	
	public Integer getRecommendProductStatus(){
		return recommendProductStatus;
	}
	
	public void setRecommendPlatformStatus(Integer recommendPlatformStatus){
		this.recommendPlatformStatus = recommendPlatformStatus;
	}
	
	public Integer getRecommendPlatformStatus(){
		return recommendPlatformStatus;
	}
	
	public void setGrantHongbaoStatus(Integer grantHongbaoStatus){
		this.grantHongbaoStatus = grantHongbaoStatus;
	}
	
	public Integer getGrantHongbaoStatus(){
		return grantHongbaoStatus;
	}
	
	public void setSeeProfitStatus(Integer seeProfitStatus){
		this.seeProfitStatus = seeProfitStatus;
	}
	
	public Integer getSeeProfitStatus(){
		return seeProfitStatus;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setLastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public Date getLastUpdateTime(){
		return lastUpdateTime;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

