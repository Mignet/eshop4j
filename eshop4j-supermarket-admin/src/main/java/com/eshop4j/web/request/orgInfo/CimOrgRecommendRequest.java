package com.eshop4j.web.request.orgInfo;

import java.io.Serializable;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月11日 16:27:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgRecommendRequest implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6817773358152398656L;


	/**
     *机构编码-不重复字段
     */
	private String orgNumber;
	
   
    /**
     *首页推荐，是否首页推荐0-不推荐、1-推荐
     */
	private Integer recommend;
	
    /**
     *机构列表排名
     */
	private Integer top;
	
    /**
     *首页推荐机构排名
     */
	private Integer homepageSort;
	
	

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getHomepageSort() {
		return homepageSort;
	}

	public void setHomepageSort(Integer homepageSort) {
		this.homepageSort = homepageSort;
	}
	
    
}

