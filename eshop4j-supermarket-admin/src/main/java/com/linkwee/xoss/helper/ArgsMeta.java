package com.linkwee.xoss.helper;

public class ArgsMeta {
	private String value;
	private String className;
	private int index;
	public ArgsMeta() {
	}
	
	public ArgsMeta( String value, String className, int index) {
		super();
		this.value = value;
		this.className = className;
		this.index = index;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
