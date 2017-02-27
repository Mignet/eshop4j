package com.linkwee.core.base;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @描述：基础bean
 *
 * @author Bob
 * @时间  2015年7月29日下午5:28:02
 *
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -7809346561546528726L;

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
