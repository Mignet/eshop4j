package com.linkwee.api.response;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.SmNews;

/**
 * 资讯
 * 
 * @Author chenchy
 * @Date 2015年12月25日 下午5:18:12
 */
public class NewsListResponse extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public NewsListResponse() {}
	
	public NewsListResponse(SmNews obj) {
		WebUtil.initObj(this,obj);
		/*this.setCfpRecommend(obj.isCfpRecommend());
		this.setFixRate(NumberUtils.getDefaultFormatHalfDown(obj.getFixRate()));
		if(StringUtils.isNotEmpty(obj.getOpenLinkUrl())){		
			this.setOpenLinkUrl(obj.getOpenLinkUrl()+"?productId="+obj.getProductId());
		}*/
		this.newsId = obj.getId();
		this.name = obj.getName();
		this.img = obj.getImg();
		this.title = obj.getTitle();
		this.summary = obj.getSummary();
		this.crtTime = DateUtils.format(obj.getValidBegin(), DateUtils.FORMAT_MM);
		this.linkUrl = obj.getLinkUrl();
		this.typeName = obj.getTypeName();
		this.shareIcon = obj.getShareIcon();
	}
	/**
	 * 资讯ID
	 */
	private Integer newsId; 
	/**
     *资讯标签
     */
	private String name;
	
    /**
     *类别名称
     */
	private String typeName;
	
    /**
     *配图
     */
	private String img;
	
    /**
     *标题
     */
	private String title;
	
    /**
     *摘要
     */
	private String summary;
	
	
    /**
     *创建时间
     */
	private String crtTime;
	/**
	 * 详情链接地址
	 */
	private String linkUrl;
	
	/**
	 * 分享图标
	 */
	private String shareIcon;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

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

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getShareIcon() {
		return shareIcon;
	}

	public void setShareIcon(String shareIcon) {
		this.shareIcon = shareIcon;
	}
	
}
