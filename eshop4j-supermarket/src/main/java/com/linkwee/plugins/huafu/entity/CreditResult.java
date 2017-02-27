package com.linkwee.plugins.huafu.entity;

import java.io.Serializable;
 /**
 * 
 * @描述： 信用卡验证
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月03日 14:35:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CreditResult implements Serializable {
	
	private static final long serialVersionUID = -6941427020761839097L;
	
    /**
     *银行名称 "农业银行"
     */
	private String bank;
	
    /**
     *"金穗通宝卡（个人普卡）"
     */
	private String type;
    /**
     *卡的type "借记卡"
     */
	private String nature;
	/**
     *客服电话
     */
    private String kefu;
    
    /**
     *logo
     */
    private String logo; 

    /**
     *地方
     */
    private String info;

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getKefu() {
		return kefu;
	}

	public void setKefu(String kefu) {
		this.kefu = kefu;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
    

}

