package com.eshop4j.web.model;

/**
 *理财师正在投资中的客户实体
 * @Author Libin
 * @Date 2016/6/1
 */
public class CfpCustomerInvertingModel {

    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品期限
     */
    private Integer deadLineValue;

    /**
     * 投资购买时间
     */
    private String bizDate;

    /**
     * 投资回赎或到期时间
     */
    private String redeemDate;

    /**
     * 单笔投资金额
     */
    private Double amount;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getDeadLineValue() {
        return deadLineValue;
    }

    public void setDeadLineValue(Integer deadLineValue) {
        this.deadLineValue = deadLineValue;
    }

    public String getBizDate() {
        return bizDate;
    }

    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    public String getRedeemDate() {
        return redeemDate;
    }

    public void setRedeemDate(String redeemDate) {
        this.redeemDate = redeemDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
