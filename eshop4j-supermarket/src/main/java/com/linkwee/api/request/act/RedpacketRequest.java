package com.linkwee.api.request.act;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.linkwee.core.base.api.PaginatorRequest;

public class RedpacketRequest  extends PaginatorRequest{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 974945193835265294L;
	
	@NotNull(message="红包状态不能为空")
	@Range(min=1,max=3,message="投资状态必须为1-3之间的数值")
	private Integer type;
	
	
	private String patform;
	
	private String productId;
	
	/**
	 * 产品最小期限
	 */
	private Integer deadline;
	
	@Range(min=1,max=2,message="机构收费模式必须为1-2之间的数值")
	Integer model;
	

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPatform() {
		return patform;
	}

	public void setPatform(String patform) {
		this.patform = patform;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getDeadline() {
		return deadline;
	}

	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}

	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}

}
