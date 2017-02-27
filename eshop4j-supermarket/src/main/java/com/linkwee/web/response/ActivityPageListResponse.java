package com.linkwee.web.response;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.WebUtil;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.share.ShareContent;


/**
 * 
 * @描述：
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class ActivityPageListResponse extends BaseEntity {

	private static final long serialVersionUID = -7881118760894550092L;

	public ActivityPageListResponse() {

	}

	public ActivityPageListResponse(ActivityList obj) {
	    WebUtil.initObj(this,obj);
	    this.setActivityId(WebUtil.getDefaultFormat(obj.getId()));
	    this.setStatus(WebUtil.getDefaultFormat(obj.getStatus()));
	    
		shareContent.setShareDesc(obj.getShareDesc());
		shareContent.setShareTitle(obj.getShareTitle());
		shareContent.setShareImgurl(obj.getShareIcon());
		shareContent.setShareLink(obj.getShareLink());
	    
	    if("0".equals(this.getStatus())){
	    	Date now = new Date();
	    	if(now.after(obj.getEndDate())){ //当now大于EndDate()时，返回TRUE
	    		this.setStatus("1"); //已结束
	    	}
	    }
	    if("1".equals(this.getStatus())){
	    	this.setActivityImg(obj.getActivityImg()+"?g=1"); //如果活动结束 显示活动结束后的URL图片
	    }
		this.setStartDate(DateUtils.format(obj.getStartDate(), DateUtils.FORMAT_SHORT));
		this.setEndDate(DateUtils.format(obj.getEndDate(), DateUtils.FORMAT_SHORT));
	}

	/**
	 * 活动id
	 */
	private String activityId;
	/**
	 * 活动名称
	 */
	private String activityName;
	/**
	 * 活动图片
	 */
	private String activityImg;
	
	/**
	 * 开始日期
	 */
	private String startDate;
	
	/**
	 * 开始日期
	 */
	private String endDate;
	/**
	 * 开始日期
	 */
	private String linkUrl;

	/**
	 * 结束标识: 0未结束，1已结束
	 */
	private String status;
	
	
	/**
	 * 分享内容
	 */
	private ShareContent shareContent = new ShareContent();
	

	

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityImg() {
		return activityImg;
	}

	public void setActivityImg(String activityImg) {
		this.activityImg = activityImg;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public ShareContent getShareContent() {
		return shareContent;
	}

	public void setShareContent(ShareContent shareContent) {
		this.shareContent = shareContent;
	}
	
	

}
