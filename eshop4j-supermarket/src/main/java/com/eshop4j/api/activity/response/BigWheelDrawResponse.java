package com.eshop4j.api.activity.response;

public class BigWheelDrawResponse {
	/**
	 * 中奖奖项id
	 */
	Integer prizeId;
	/**
	 * 剩余抽奖次数
	 */
	Integer leftTimes;
	
	public Integer getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}
	public Integer getLeftTimes() {
		return leftTimes;
	}
	public void setLeftTimes(Integer leftTimes) {
		this.leftTimes = leftTimes;
	}
	
}
