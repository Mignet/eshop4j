package com.linkwee.api.activity.response;

import com.linkwee.web.model.ActActivityWinningRecord;

public class ActivityDrawResponse extends ActActivityWinningRecord{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1735342952231731683L;
	/**
	 * 剩余抽奖次数
	 */
	Integer leftTimes;
	
	public Integer getLeftTimes() {
		return leftTimes;
	}
	public void setLeftTimes(Integer leftTimes) {
		this.leftTimes = leftTimes;
	}
	
}
