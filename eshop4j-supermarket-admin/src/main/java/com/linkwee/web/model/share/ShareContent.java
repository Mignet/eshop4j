package com.linkwee.web.model.share;

import com.linkwee.core.base.BaseEntity;

public class ShareContent extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *分享描述
     */
	private String shareDesc;
	
    /**
     *分享图片链接
     */
	private String shareImgurl;
	
    /**
     *分享链接
     */
	private String shareLink;
	
    /**
     *分享标题
     */
	private String shareTitle;

	public String getShareDesc() {
		return shareDesc;
	}

	public void setShareDesc(String shareDesc) {
		this.shareDesc = shareDesc;
	}

	public String getShareImgurl() {
		return shareImgurl;
	}

	public void setShareImgurl(String shareImgurl) {
		this.shareImgurl = shareImgurl;
	}

	public String getShareLink() {
		return shareLink;
	}

	public void setShareLink(String shareLink) {
		this.shareLink = shareLink;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

}
