/**
 * QueryResponseBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.linkwee.plugins.bill99.entity;

import java.io.Serializable;

import javax.xml.namespace.QName;

import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class QueryResponseBean  implements Serializable {

	private static final long serialVersionUID = 7678310664537787463L;

	private double amount;//交易金额 整数或小数数字  以人民币“元”为单位   非空

    private String dealBeginDate;//交易开始时间  格式为：yyyy-mm-dd HI:MM:SS。和商户提交时一致。

    private String dealEndDate;//交易结束时间

    private double dealFee;

    private String dealId;//交易号  返回该交易在快钱的交易号

    private String dealStatus;

    private String failureCause;//失败原因代码

    private String orderId;//订单号 非空

    private String queryType;//查询类型  simplePay 代表付款到快钱账户  bankPay 代表付款到银行账户

    private boolean resultFlag;//命令执行结果

    public QueryResponseBean() {
    }

    public QueryResponseBean(
           double amount,
           String dealBeginDate,
           String dealEndDate,
           double dealFee,
           String dealId,
           String dealStatus,
           String failureCause,
           String orderId,
           String queryType,
           boolean resultFlag) {
           this.amount = amount;
           this.dealBeginDate = dealBeginDate;
           this.dealEndDate = dealEndDate;
           this.dealFee = dealFee;
           this.dealId = dealId;
           this.dealStatus = dealStatus;
           this.failureCause = failureCause;
           this.orderId = orderId;
           this.queryType = queryType;
           this.resultFlag = resultFlag;
    }


    /**
     * Gets the amount value for this QueryResponseBean.
     * 
     * @return amount
     */
    public double getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this QueryResponseBean.
     * 
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }


    /**
     * Gets the dealBeginDate value for this QueryResponseBean.
     * 
     * @return dealBeginDate
     */
    public String getDealBeginDate() {
        return dealBeginDate;
    }


    /**
     * Sets the dealBeginDate value for this QueryResponseBean.
     * 
     * @param dealBeginDate
     */
    public void setDealBeginDate(String dealBeginDate) {
        this.dealBeginDate = dealBeginDate;
    }


    /**
     * Gets the dealEndDate value for this QueryResponseBean.
     * 
     * @return dealEndDate
     */
    public String getDealEndDate() {
        return dealEndDate;
    }


    /**
     * Sets the dealEndDate value for this QueryResponseBean.
     * 
     * @param dealEndDate
     */
    public void setDealEndDate(String dealEndDate) {
        this.dealEndDate = dealEndDate;
    }


    /**
     * Gets the dealFee value for this QueryResponseBean.
     * 
     * @return dealFee
     */
    public double getDealFee() {
        return dealFee;
    }


    /**
     * Sets the dealFee value for this QueryResponseBean.
     * 
     * @param dealFee
     */
    public void setDealFee(double dealFee) {
        this.dealFee = dealFee;
    }


    /**
     * Gets the dealId value for this QueryResponseBean.
     * 
     * @return dealId
     */
    public String getDealId() {
        return dealId;
    }


    /**
     * Sets the dealId value for this QueryResponseBean.
     * 
     * @param dealId
     */
    public void setDealId(String dealId) {
        this.dealId = dealId;
    }


    /**
     * Gets the dealStatus value for this QueryResponseBean.
     * 
     * @return dealStatus
     */
    public String getDealStatus() {
        return dealStatus;
    }


    /**
     * Sets the dealStatus value for this QueryResponseBean.
     * 
     * @param dealStatus
     */
    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }


    /**
     * Gets the failureCause value for this QueryResponseBean.
     * 
     * @return failureCause
     */
    public String getFailureCause() {
        return failureCause;
    }


    /**
     * Sets the failureCause value for this QueryResponseBean.
     * 
     * @param failureCause
     */
    public void setFailureCause(String failureCause) {
        this.failureCause = failureCause;
    }


    /**
     * Gets the orderId value for this QueryResponseBean.
     * 
     * @return orderId
     */
    public String getOrderId() {
        return orderId;
    }


    /**
     * Sets the orderId value for this QueryResponseBean.
     * 
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    /**
     * Gets the queryType value for this QueryResponseBean.
     * 
     * @return queryType
     */
    public String getQueryType() {
        return queryType;
    }


    /**
     * Sets the queryType value for this QueryResponseBean.
     * 
     * @param queryType
     */
    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }


    /**
     * Gets the resultFlag value for this QueryResponseBean.
     * 
     * @return resultFlag
     */
    public boolean isResultFlag() {
        return resultFlag;
    }


    /**
     * Sets the resultFlag value for this QueryResponseBean.
     * 
     * @param resultFlag
     */
    public void setResultFlag(boolean resultFlag) {
        this.resultFlag = resultFlag;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof QueryResponseBean)) return false;
        QueryResponseBean other = (QueryResponseBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.amount == other.getAmount() &&
            ((this.dealBeginDate==null && other.getDealBeginDate()==null) || 
             (this.dealBeginDate!=null &&
              this.dealBeginDate.equals(other.getDealBeginDate()))) &&
            ((this.dealEndDate==null && other.getDealEndDate()==null) || 
             (this.dealEndDate!=null &&
              this.dealEndDate.equals(other.getDealEndDate()))) &&
            this.dealFee == other.getDealFee() &&
            ((this.dealId==null && other.getDealId()==null) || 
             (this.dealId!=null &&
              this.dealId.equals(other.getDealId()))) &&
            ((this.dealStatus==null && other.getDealStatus()==null) || 
             (this.dealStatus!=null &&
              this.dealStatus.equals(other.getDealStatus()))) &&
            ((this.failureCause==null && other.getFailureCause()==null) || 
             (this.failureCause!=null &&
              this.failureCause.equals(other.getFailureCause()))) &&
            ((this.orderId==null && other.getOrderId()==null) || 
             (this.orderId!=null &&
              this.orderId.equals(other.getOrderId()))) &&
            ((this.queryType==null && other.getQueryType()==null) || 
             (this.queryType!=null &&
              this.queryType.equals(other.getQueryType()))) &&
            this.resultFlag == other.isResultFlag();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += new Double(getAmount()).hashCode();
        if (getDealBeginDate() != null) {
            _hashCode += getDealBeginDate().hashCode();
        }
        if (getDealEndDate() != null) {
            _hashCode += getDealEndDate().hashCode();
        }
        _hashCode += new Double(getDealFee()).hashCode();
        if (getDealId() != null) {
            _hashCode += getDealId().hashCode();
        }
        if (getDealStatus() != null) {
            _hashCode += getDealStatus().hashCode();
        }
        if (getFailureCause() != null) {
            _hashCode += getFailureCause().hashCode();
        }
        if (getOrderId() != null) {
            _hashCode += getOrderId().hashCode();
        }
        if (getQueryType() != null) {
            _hashCode += getQueryType().hashCode();
        }
        _hashCode += (isResultFlag() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static TypeDesc typeDesc =
        new TypeDesc(QueryResponseBean.class, true);

    static {
        typeDesc.setXmlType(new QName("http://complatible.dto.domain.seashell.bill99.com", "QueryResponseBean"));
        ElementDesc elemField = new ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new QName("", "amount"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("dealBeginDate");
        elemField.setXmlName(new QName("", "dealBeginDate"));
        elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("dealEndDate");
        elemField.setXmlName(new QName("", "dealEndDate"));
        elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("dealFee");
        elemField.setXmlName(new QName("", "dealFee"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("dealId");
        elemField.setXmlName(new QName("", "dealId"));
        elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("dealStatus");
        elemField.setXmlName(new QName("", "dealStatus"));
        elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("failureCause");
        elemField.setXmlName(new QName("", "failureCause"));
        elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("orderId");
        elemField.setXmlName(new QName("", "orderId"));
        elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("queryType");
        elemField.setXmlName(new QName("", "queryType"));
        elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("resultFlag");
        elemField.setXmlName(new QName("", "resultFlag"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static Serializer getSerializer(
           String mechType, 
           Class _javaType,  
           QName _xmlType) {
        return 
          new  BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static Deserializer getDeserializer(
           String mechType, 
           Class _javaType,  
           QName _xmlType) {
        return 
          new  BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
