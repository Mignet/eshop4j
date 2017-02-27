package com.linkwee.web.model.news;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Activity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1826355212025544969L;

	private Long id;

    private String fid;

    private String name;

    private String img;

    private String url;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")  
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")  
    private Date endDate;

    private Integer type;

    private Integer showIndex;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")  
    private Date initDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")  
    private Date updateDate;

    private Integer deadlineCondition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getShowIndex() {
        return showIndex;
    }

    public void setShowIndex(Integer showIndex) {
        this.showIndex = showIndex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getDeadlineCondition() {
        return deadlineCondition;
    }

    public void setDeadlineCondition(Integer deadlineCondition) {
        this.deadlineCondition = deadlineCondition;
    }
    
	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}