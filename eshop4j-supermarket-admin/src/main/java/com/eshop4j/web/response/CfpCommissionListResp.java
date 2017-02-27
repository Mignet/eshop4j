package com.eshop4j.web.response;

/**
 * Created by Mignet on 2016/6/1.
 *
 * @Author Libin
 * @Date 2016/6/1
 */
public class CfpCommissionListResp {

    private String id;
    private String customerName;
    private String customerMobile;
    private String productName;
    /**
     * 产品期限
     */
    private Integer deadLineValue;
    /**
     * 销售金额
     */
    private Double saleAmount;
    /**
     * 佣金率
     */
    private Double commissionRate;
    private Double commissionAmount;
    private String saleTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
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

    public Double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(Double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public Double getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(Double commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }
}
