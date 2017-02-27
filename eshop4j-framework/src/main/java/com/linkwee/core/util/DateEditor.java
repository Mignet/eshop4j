package com.linkwee.core.util;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @描述：时间格式转换
 *
 * @author Bob
 * @时间  2015年8月20日下午1:56:44
 *
 */
public class DateEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.length() == 0) {
			setValue(null);
		} else {
			try {
				if (text.trim().length() == 7) {
					setValue(new SimpleDateFormat("yyyy-MM").parse(text));
				}else if (text.trim().length() == 10) {
					setValue(new SimpleDateFormat("yyyy-MM-dd").parse(text));
				} else if (text.trim().length() == 13) {
					setValue(new Date(Long.parseLong(text)));
				} else if (text.trim().length() == 16) {
					setValue(new SimpleDateFormat("yy-MM-dd HH:mm").parseObject(text));
				} else if (text.trim().length() == 19) {
					setValue(new SimpleDateFormat("yy-MM-dd HH:mm:ss").parseObject(text));
				} else {
					throw new IllegalArgumentException("转换日期失败: 日期长度不符合要求!");
				}
			} catch (Exception ex) {
				throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);
			}
		}
	}

}
