package com.linkwee.api.response.activity;

import java.io.Serializable;

public class ScratchDetailResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3021062843930525228L;
	
	/**
	 * 可用刮奖次数
	 */
	private Integer availableScratchTime;
	/**
	 * 总刮奖次数
	 */
	private Integer totalScratchTime;
	/**
	 * 下次刮奖金额
	 */
	private double nextScratchMoney;
	
	public Integer getAvailableScratchTime() {
		return availableScratchTime;
	}
	public void setAvailableScratchTime(Integer availableScratchTime) {
		this.availableScratchTime = availableScratchTime;
	}
	public Integer getTotalScratchTime() {
		return totalScratchTime;
	}
	public void setTotalScratchTime(Integer totalScratchTime) {
		this.totalScratchTime = totalScratchTime;
	}
	public double getNextScratchMoney() {
		return nextScratchMoney;
	}
	public void setNextScratchMoney(double nextScratchMoney) {
		this.nextScratchMoney = nextScratchMoney;
	}
	
}
