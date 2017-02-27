package com.linkwee.core.datatable;

/**
 * 列信息
 * 
 * @author Mignet
 * @date 2011-7-26 下午3:07:40
 */
public class ColumnInfo {

	/**
	 * 列名Column's name
	 */
	private String name;
	
	/**
	 * Column's data source
	 */
	private String data;
	/**
	 * 是否正则
	 */
	private boolean orderable;
	/**
	 * 查找
	 */
	private boolean searchable;
	
	private SearchInfo search;
	/**
	 * 排序
	 */
	private Boolean sortable;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public boolean isOrderable() {
		return orderable;
	}
	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}
	public boolean isSearchable() {
		return searchable;
	}
	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}
	public SearchInfo getSearch() {
		return search;
	}
	public void setSearch(SearchInfo search) {
		this.search = search;
	}
	public Boolean getSortable() {
		return sortable;
	}
	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}

	public static String exChange(String str){  
	    StringBuffer sb = new StringBuffer();  
	    if(str!=null){  
	        for(int i=0;i<str.length();i++){  
	            char c = str.charAt(i);  
	            if(Character.isUpperCase(c)){  
	                sb.append("_").append(Character.toLowerCase(c));  
	            }else if(Character.isLowerCase(c)){  
	                sb.append(c);   
	            }  
	        }  
	    }  
	      
	    return sb.toString();  
	}  

}
