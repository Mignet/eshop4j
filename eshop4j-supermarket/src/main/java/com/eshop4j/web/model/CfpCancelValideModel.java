package com.eshop4j.web.model;

/**
 * Created by Mignet on 2016/5/26.
 *
 * @Author Libin
 * @Date 2016/5/26
 */
public class CfpCancelValideModel {

    /**
     * 是否理财师1=是 0为否
     */
    private Integer cfp;

    /**
     * 理财师客户数
     */
    private Integer customeNums;
    /**
     * 理财师上级编号
     */
    private Integer parentId;

    public Integer getCfp() {
        return cfp;
    }

    public void setCfp(Integer cfp) {
        this.cfp = cfp;
    }

    public Integer getCustomeNums() {
        return customeNums;
    }

    public void setCustomeNums(Integer customeNums) {
        this.customeNums = customeNums;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
