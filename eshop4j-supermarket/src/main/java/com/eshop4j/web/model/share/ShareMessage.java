package com.eshop4j.web.model.share;

public class ShareMessage {

	/**
	 * 分享内容
	 */
	private ShareContent shareContent;
	
	public ShareMessage() {

	}
	
	public ShareMessage(ShareContent shareContent){
		this.shareContent = shareContent;
	}

	public ShareContent getShareContent() {
		return shareContent;
	}

	public void setShareContent(ShareContent shareContent) {
		this.shareContent = shareContent;
	}
}
