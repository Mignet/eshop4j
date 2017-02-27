package com.linkwee.web.response;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.web.model.news.SmAdvertisement;
import com.linkwee.web.model.share.ShareContent;

/**
 * 
 * @描述：首页-广告栏
 *
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class BannersResponse extends BaseEntity {
	
	private static final long serialVersionUID = -8780264576615250826L;
	
	/**
	 * 图片URL
	 */
	private String imgUrl;
	/**
	 * 跳转url
	 */
	private String linkUrl;
	/**
	 * 分享内容
	 */
	private ShareContent shareContent;

	public BannersResponse() {

	}

	public BannersResponse(SmAdvertisement obj) {
		linkUrl = obj.getLinkUrl();
		imgUrl = obj.getImgUrl();
		shareContent = new ShareContent();
		shareContent.setShareDesc(obj.getShareDesc());
		shareContent.setShareTitle(obj.getShareTitle());
		shareContent.setShareImgurl(obj.getShareIcon());
		shareContent.setShareLink(obj.getShareLink());
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public ShareContent getShareContent() {
		return shareContent;
	}

	public void setShareContent(ShareContent shareContent) {
		this.shareContent = shareContent;
	}

}
