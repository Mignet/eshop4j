package com.eshop4j.web.enums;

import com.eshop4j.core.base.KeyValueEnum;

public enum QmSortFieldEnum implements KeyValueEnum {
	CURR_INVEST_AMT(1, "total_invest_amount"), // 投资额
	REGISTER_TIME(2, "register_time"), // 注册时间
	NEAR_INVEST_DATE(3, "near_invest_date"), // 投资时间
	NEAR_END_DATE(4, "near_end_date"), // 到期时间
	IMPORTANT(5, "is_important"), // 重要客户
	USER_NAME(6, "user_name");// 姓名

	QmSortFieldEnum(int key, String value) {
		this.key = key;
		this.value = value;
	}

	private int key;
	private String value;

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
