package com.eshop4j.web.response;

/**
 * Created by Mignet on 2016/6/2.
 *理财师-客户列表详情
 * @Author Libin
 * @Date 2016/6/2
 */
public class CfpCustomerProfitListResp {

    private String customerName;
    private String idCard;
    private String customerMobile;
    /**
     * 来源
     */
    private String orgName;
    private String createTime;
    /**
     * 总的投资总额度
     */
    private Double totalAmount;
    /**
     * 总的投资笔数
     */
    private Double totalNums;
    /**
     *当前投资总额度
     */
    private Double curAmount;
    /**
     * 当前投资总笔数
     */
    private int curNums;
    private Double feeAmount;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    public Double getTotalNums() {
        return totalNums;
    }

    public void setTotalNums(Double totalNums) {
        this.totalNums = totalNums;
    }

    public Double getCurAmount() {
        return curAmount;
    }

    public void setCurAmount(Double curAmount) {
        this.curAmount = curAmount;
    }

    public int getCurNums() {
        return curNums;
    }

    public void setCurNums(int curNums) {
        this.curNums = curNums;
    }

    public Double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }
}
