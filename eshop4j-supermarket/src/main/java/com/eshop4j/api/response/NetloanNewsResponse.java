package com.eshop4j.api.response;

import java.util.Date;

import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.util.DateUtils;
import com.eshop4j.core.util.WebUtil;
import com.eshop4j.web.model.SmNews;

/**
 * 网贷新闻
 * 
 * @Author hxb
 * @Date 2015年12月25日 下午5:18:12
 */
public class NetloanNewsResponse extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 摘要
	 */
	private String summary;
	
	/**
	 * 地址
	 */
	private String linkUrl;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * ID
	 */
	private Integer id;
	
	/**
	 * slug
	 */
	private String slug;
	
	/**
	 * 模型
	 */
	private String module;
	
	/**
	 * 创建时间
	 */
	private Date created;
	
	/**
	 * 详情链接
	 */
	private String detailUrl;

	/**
	 * 缩略图
	 */
	private String thumbnail;
	
	/**
	 * 更多链接
	 */
	private String moreUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getMoreUrl() {
		return moreUrl;
	}

	public void setMoreUrl(String moreUrl) {
		this.moreUrl = moreUrl;
	}
	
}
