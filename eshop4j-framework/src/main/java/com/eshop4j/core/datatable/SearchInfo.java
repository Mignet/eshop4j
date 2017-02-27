package com.eshop4j.core.datatable;

public class SearchInfo {
	private String value;
	private boolean regex;
	public String getValue() {
		return value.replace("'", "").replace("-", "");
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isRegex() {
		return regex;
	}
	public void setRegex(boolean regex) {
		this.regex = regex;
	}
}
