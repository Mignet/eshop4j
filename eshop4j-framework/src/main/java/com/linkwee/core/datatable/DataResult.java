package com.linkwee.core.datatable;

import java.util.List;

/**
 * CRUD result
 * 
 * @author Mignet
 * 
 */
public class DataResult {
	/**
	 * On create: Data of the added / edited row(s) 
	 * On edit: Data of the added / edited row(s) 
	 * On remove: Not used / not required 
	 */
	private List<?> data;
	/**
	 * Optional - Error message to display to the end user 
	 */
	private String error;
	
	/**
	 * Optional - Individual field error messages 
	 */
	private List<ErrorField> fieldErrors;

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<?> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<ErrorField> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	
	
}