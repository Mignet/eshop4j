package com.eshop4j.web.response;

/**
 * Created by Mignet on 2016/6/2.
 *理财师-团队列表详情
 * @Author Libin
 * @Date 2016/6/2
 */
public class CfpTeamListResp {

    /**
     * 理财师ID
     */
    private String number;

    private String mobile;
    private String name;
    private String parentId;
    private String createTime;
    private String level;
    /**
     * 理财师总的销售额
     */
    private Double totalAmount;
    /**
     * 理财师创造的收益
     */
    private Double feeAmount;
    private int totalNums;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getTotalNums() {
        return totalNums;
    }

    public void setTotalNums(int totalNums) {
        this.totalNums = totalNums;
    }
}
