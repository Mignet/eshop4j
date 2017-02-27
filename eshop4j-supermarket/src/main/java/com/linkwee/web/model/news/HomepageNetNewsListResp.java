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
public class HomepageNetNewsListResp extends BaseEntity {

	private static final long serialVersionUID = 8582514793750203221L;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 图片
	 */
	private String image;
	/**
	 * url
	 */
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
