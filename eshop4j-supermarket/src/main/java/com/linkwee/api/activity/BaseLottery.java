package com.linkwee.api.activity;

import java.io.Serializable;

public class BaseLottery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3958471990716748009L;

	private Integer id;
	/**
	 * 奖项
	 */
	private String prize;
	/**
	 * 概率
	 */
	private Integer variable;
	
	public BaseLottery(Integer id, String prize, Integer variable) {
		super();
		this.id = id;
		this.prize = prize;
		this.variable = variable;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPrize() {
		return prize;
	}
	public void setPrize(String prize) {
		this.prize = prize;
	}
	public Integer getVariable() {
		return variable;
	}
	public void setVariable(Integer variable) {
		this.variable = variable;
	}
}
