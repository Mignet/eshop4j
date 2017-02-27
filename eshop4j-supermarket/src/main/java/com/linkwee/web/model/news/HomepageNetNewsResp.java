package com.linkwee.web.model.news;

import com.linkwee.core.base.BaseEntity;

/**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月12日 14:05:45
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class HomepageNetNewsResp extends BaseEntity {

	private static final long serialVersionUID = 8582514793750203221L;

	/**
	 * 资讯列表
	 */
	private HomepageNetNewsListResp homepageNetNewsList;

	/**
	 * 更多资讯url
	 */
	private String url;

	public HomepageNetNewsListResp getHomepageNetNewsList() {
		return homepageNetNewsList;
	}

	public void setHomepageNetNewsList(HomepageNetNewsListResp homepageNetNewsList) {
		this.homepageNetNewsList = homepageNetNewsList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
