package com.linkwee.xoss.interceptors;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertEditor extends PropertyEditorSupport {
	private DateFormat format;

	public DateConvertEditor() {
		this.format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public DateConvertEditor(String format) {
		this.format = new SimpleDateFormat(format);
	}

	/** Date -> String */
	@Override
	public String getAsText() {
		if (getValue() == null)
			return "";
		return this.format.format(getValue());
	}

	/** String -> Date */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.length() == 0|| "null".equals(text.trim())) {
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
					throw new IllegalArgumentException("日期长度不符合要求!");
				}
			} catch (Exception ex) {
				throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);
			}
		}
	}
}
