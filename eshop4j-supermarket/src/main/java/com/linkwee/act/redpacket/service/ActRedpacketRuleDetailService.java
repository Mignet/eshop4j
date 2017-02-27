package com.linkwee.act.redpacket.service;

import java.util.Date;

import org.apache.commons.lang.ObjectUtils;

import com.linkwee.act.redpacket.model.ActRedpacketRule;
import com.linkwee.act.redpacket.model.ActRedpacketRuleDetail;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.request.act.RedPacketInfoRequest;
 /**
 * 
 * @描述： ActRedpacketRuleDetailService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月19日 15:52:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRedpacketRuleDetailService extends GenericService<ActRedpacketRuleDetail,Long>{
	
	public enum ProductLimit{
		
		unLimit(0,1000),
		eqProductDeadlineLimit(10,1002),
		geProductDeadlineLimit(11,1003),
		productIdLimit(2,1001);
		
		private int limitCode;
		private int useCondition;
		
		private ProductLimit(int limitCode,int useCondition) {
			this.limitCode=limitCode;
			this.useCondition=useCondition;
		}

		public int getLimitCode() {
			return limitCode;
		}

		public int getUseCondition() {
			return useCondition;
		}
		
		public static ProductLimit getProductLimit(int limitCode){
			for (ProductLimit productLimit : values()) {
				if(ObjectUtils.equals(productLimit.limitCode,limitCode)){
					return productLimit;
				}
			}
			return null;
		}
		
		public static ProductLimit getUseConditionLimit(int useCondition){
			for (ProductLimit productLimit : values()) {
				if(ObjectUtils.equals(productLimit.useCondition,useCondition)){
					return productLimit;
				}
			}
			return null;
		}
		
	}
	
	boolean insertRedpacketRuleDetail(ActRedpacketRule redpacketRule,RedPacketInfoRequest  redPacketInfo,Date date)throws Exception;
	
	boolean updateRedpacketRuleDetail(ActRedpacketRule redpacketRule,RedPacketInfoRequest  redPacketInfo,Date date)throws Exception;
}
