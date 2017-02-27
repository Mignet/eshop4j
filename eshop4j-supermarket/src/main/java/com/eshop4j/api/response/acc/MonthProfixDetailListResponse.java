package com.eshop4j.api.response.acc;

import java.io.Serializable;

import com.eshop4j.core.util.DateUtils;
import com.eshop4j.web.model.acc.MonthProfixDetailListResp;
import com.eshop4j.xoss.util.WebUtil;
/**
* 
* @描述： 实体Bean
* 
* @创建人： chenjl
* 
* @创建时间：2016年07月22日 17:10:52
* 
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public class MonthProfixDetailListResponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	public MonthProfixDetailListResponse(){
	}
	public MonthProfixDetailListResponse(MonthProfixDetailListResp obj) {
		WebUtil.initObj(this, obj);
		this.setTime(DateUtils.format(obj.getCreateTime(), DateUtils.FORMAT_MM));
		if("1".equals(obj.getProfixType())) {
			this.setProfixTypeName("销售佣金");
			this.setDescription(obj.getRemark());
		} else if("2".equals(obj.getProfixType())) {
			this.setProfixTypeName("推荐奖励");
			String re = obj.getRemark();
			re = re.replace("佣金", "推荐奖励");
			this.setDescription(re);
			this.setRemark(re);
			//this.setDescription("队员" + obj.getOriginCfplannerName() + " 销售 " + obj.getProductName() + " " + obj.getDeadLineValueNewText() +  "， 金额" + WebUtil.getDefaultFormat(obj.getProductAmount()) + "元");
		} else if("3".equals(obj.getProfixType())) {
			this.setProfixTypeName("活动奖励");
			this.setDescription(obj.getRemark());
		} else if("4".equals(obj.getProfixType())) {
			this.setProfixTypeName("团队leader奖");
			this.setDescription(obj.getRemark());
		} 
		this.setFeeRate(obj.getFeeRate() + "%");
		if(obj.getDeadLineValueNewText() != null) {
			this.setDeadline(obj.getDeadLineValueNewText());
		}
		if(obj.getOrgFeeType() == 1) {
			if(obj.getIsRedemption() == 0) {
				this.setProductType("1");
			}else {
				this.setProductType("2");
			}
		} else if (obj.getOrgFeeType() == 2) {
			if(obj.getIsRedemption() == 0) {
				this.setProductType("3");
			}else {
				this.setProductType("4");
			}
		} else {
			this.setProductType("1");
		}
	}
	
	/**
     *金额
     */
	private String amount;
	/**
     *时间
     */
	private String time;
	/**
     *描述
     */
	private String description;
	/**
     *收益类型名称
     */
	private String profixTypeName;
	
	/**
     *收益类型:1销售佣金，2推荐津贴，3活动奖励，4团队leader奖励
     */
	private String profixType;
	
	/**
     *年化佣金率
     */
	private String feeRate;
	
	private String remark;
	
	/**
	 * 产品类型
	 */
	private String productType;
	
	/**
     *产品期限
     */
	private String deadline;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfixTypeName() {
		return profixTypeName;
	}

	public void setProfixTypeName(String profixTypeName) {
		this.profixTypeName = profixTypeName;
	}

	public String getProfixType() {
		return profixType;
	}

	public void setProfixType(String profixType) {
		this.profixType = profixType;
	}

	public String getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(String feeRate) {
		this.feeRate = feeRate;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	
	

}