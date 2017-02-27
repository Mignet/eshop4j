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
public class NewsDtlResponse extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public NewsDtlResponse() {}
	
	public NewsDtlResponse(SmNews obj) {
		WebUtil.initObj(this,obj);
		/*this.setCfpRecommend(obj.isCfpRecommend());
		this.setFixRate(NumberUtils.getDefaultFormatHalfDown(obj.getFixRate()));
		if(StringUtils.isNotEmpty(obj.getOpenLinkUrl())){		
			this.setOpenLinkUrl(obj.getOpenLinkUrl()+"?productId="+obj.getProductId());
		}*/
		this.name = obj.getName();
		this.typeName = obj.getTypeName();
		this.img = obj.getImg();
		this.title = obj.getTitle();
		this.summary = obj.getSummary();
		this.content = obj.getContent();
		this.crtTime = DateUtils.format(obj.getValidBegin(), DateUtils.FORMAT_MM);
		this.creator = obj.getCreator();
		this.shareIcon = obj.getShareIcon();
		
	}
	
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
     *资讯内容
     */
	private String content;
	
   
    /**
     *发布人
     */
	private String creator;
	
    /**
     *创建时间
     */
	private String crtTime;
	
	private String shareIcon;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}

	public String getShareIcon() {
		return shareIcon;
	}

	public void setShareIcon(String shareIcon) {
		this.shareIcon = shareIcon;
	}

}
