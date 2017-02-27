/**
 * BankResponseBean.java
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

public class BankResponseBean  implements Serializable {

	private static final long serialVersionUID = 1389842660443292945L;

	private double amount; //交易金额 整数或小数数字  以人民币“元”为单位   非空

    private String bankCardNumber;//银行卡号 收款人的银行卡卡号 非空

    private String bankName;//银行名称 请填写银行的标准名称 详见下文支持银行列表 非空

    private double creditCharge;//收款方费用

    private String creditName;//收款方姓名 中文或英文 非空

    private double dealCharge;//快钱手续费

    private String dealId;//快钱交易号

    private double debitCharge;//付款方费用

    private String description;//交易备注 可以是中文或英文

    private String failureCause;//失败原因代码

    private String kaihuhang;//开户行 银行卡开户行的名称 非空

    private String mac;//加密字符串 非空 按照如下规则组成加密字符串 {bankCardNumber}{amount}{orderId}{key}加密后加密字符串应为大写。

    private String orderId;//订单号 非空

    private String province_city;//城市  非空 主要只需要城市名，不需要省份名。也不要带“市” “自治区（县）”等 非空

    private boolean resultFlag;//命令执行结果

    public BankResponseBean() {
    }

    public BankResponseBean(
           double amount,
           String bankCardNumber,
           String bankName,
           double creditCharge,
           String creditName,
           double dealCharge,
           String dealId,
           double debitCharge,
           String description,
           String failureCause,
           String kaihuhang,
           String mac,
           String orderId,
           String province_city,
           boolean resultFlag) {
           this.amount = amount;
           this.bankCardNumber = bankCardNumber;
           this.bankName = bankName;
           this.creditCharge = creditCharge;
           this.creditName = creditName;
           this.dealCharge = dealCharge;
           this.dealId = dealId;
           this.debitCharge = debitCharge;
           this.description = description;
           this.failureCause = failureCause;
           this.kaihuhang = kaihuhang;
           this.mac = mac;
           this.orderId = orderId;
           this.province_city = province_city;
           this.resultFlag = resultFlag;
    }


    /**
     * Gets the amount value for this BankResponseBean.
     * 
     * @return amount
     */
    public double getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this BankResponseBean.
     * 
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }


    /**
     * Gets the bankCardNumber value for this BankResponseBean.
     * 
     * @return bankCardNumber
     */
    public String getBankCardNumber() {
        return bankCardNumber;
    }


    /**
     * Sets the bankCardNumber value for this BankResponseBean.
     * 
     * @param bankCardNumber
     */
    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }


    /**
     * Gets the bankName value for this BankResponseBean.
     * 
     * @return bankName
     */
    public String getBankName() {
        return bankName;
    }


    /**
     * Sets the bankName value for this BankResponseBean.
     * 
     * @param bankName
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }


    /**
     * Gets the creditCharge value for this BankResponseBean.
     * 
     * @return creditCharge
     */
    public double getCreditCharge() {
        return creditCharge;
    }


    /**
     * Sets the creditCharge value for this BankResponseBean.
     * 
     * @param creditCharge
     */
    public void setCreditCharge(double creditCharge) {
        this.creditCharge = creditCharge;
    }


    /**
     * Gets the creditName value for this BankResponseBean.
     * 
     * @return creditName
     */
    public String getCreditName() {
        return creditName;
    }


    /**
     * Sets the creditName value for this BankResponseBean.
     * 
     * @param creditName
     */
    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }


    /**
     * Gets the dealCharge value for this BankResponseBean.
     * 
     * @return dealCharge
     */
    public double getDealCharge() {
        return dealCharge;
    }


    /**
     * Sets the dealCharge value for this BankResponseBean.
     * 
     * @param dealCharge
     */
    public void setDealCharge(double dealCharge) {
        this.dealCharge = dealCharge;
    }


    /**
     * Gets the dealId value for this BankResponseBean.
     * 
     * @return dealId
     */
    public String getDealId() {
        return dealId;
    }


    /**
     * Sets the dealId value for this BankResponseBean.
     * 
     * @param dealId
     */
    public void setDealId(String dealId) {
        this.dealId = dealId;
    }


    /**
     * Gets the debitCharge value for this BankResponseBean.
     * 
     * @return debitCharge
     */
    public double getDebitCharge() {
        return debitCharge;
    }


    /**
     * Sets the debitCharge value for this BankResponseBean.
     * 
     * @param debitCharge
     */
    public void setDebitCharge(double debitCharge) {
        this.debitCharge = debitCharge;
    }


    /**
     * Gets the description value for this BankResponseBean.
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this BankResponseBean.
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Gets the failureCause value for this BankResponseBean.
     * 
     * @return failureCause
     */
    public String getFailureCause() {
        return failureCause;
    }


    /**
     * Sets the failureCause value for this BankResponseBean.
     * 
     * @param failureCause
     */
    public void setFailureCause(String failureCause) {
        this.failureCause = failureCause;
    }


    /**
     * Gets the kaihuhang value for this BankResponseBean.
     * 
     * @return kaihuhang
     */
    public String getKaihuhang() {
        return kaihuhang;
    }


    /**
     * Sets the kaihuhang value for this BankResponseBean.
     * 
     * @param kaihuhang
     */
    public void setKaihuhang(String kaihuhang) {
        this.kaihuhang = kaihuhang;
    }


    /**
     * Gets the mac value for this BankResponseBean.
     * 
     * @return mac
     */
    public String getMac() {
        return mac;
    }


    /**
     * Sets the mac value for this BankResponseBean.
     * 
     * @param mac
     */
    public void setMac(String mac) {
        this.mac = mac;
    }


    /**
     * Gets the orderId value for this BankResponseBean.
     * 
     * @return orderId
     */
    public String getOrderId() {
        return orderId;
    }


    /**
     * Sets the orderId value for this BankResponseBean.
     * 
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    /**
     * Gets the province_city value for this BankResponseBean.
     * 
     * @return province_city
     */
    public String getProvince_city() {
        return province_city;
    }


    /**
     * Sets the province_city value for this BankResponseBean.
     * 
     * @param province_city
     */
    public void setProvince_city(String province_city) {
        this.province_city = province_city;
    }


    /**
     * Gets the resultFlag value for this BankResponseBean.
     * 
     * @return resultFlag
     */
    public boolean isResultFlag() {
        return resultFlag;
    }


    /**
     * Sets the resultFlag value for this BankResponseBean.
     * 
     * @param resultFlag
     */
    public void setResultFlag(boolean resultFlag) {
        this.resultFlag = resultFlag;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof BankResponseBean)) return false;
        BankResponseBean other = (BankResponseBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.amount == other.getAmount() &&
            ((this.bankCardNumber==null && other.getBankCardNumber()==null) || 
             (this.bankCardNumber!=null &&
              this.bankCardNumber.equals(other.getBankCardNumber()))) &&
            ((this.bankName==null && other.getBankName()==null) || 
             (this.bankName!=null &&
              this.bankName.equals(other.getBankName()))) &&
            this.creditCharge == other.getCreditCharge() &&
            ((this.creditName==null && other.getCreditName()==null) || 
             (this.creditName!=null &&
              this.creditName.equals(other.getCreditName()))) &&
            this.dealCharge == other.getDealCharge() &&
            ((this.dealId==null && other.getDealId()==null) || 
             (this.dealId!=null &&
              this.dealId.equals(other.getDealId()))) &&
            this.debitCharge == other.getDebitCharge() &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.failureCause==null && other.getFailureCause()==null) || 
             (this.failureCause!=null &&
              this.failureCause.equals(other.getFailureCause()))) &&
            ((this.kaihuhang==null && other.getKaihuhang()==null) || 
             (this.kaihuhang!=null &&
              this.kaihuhang.equals(other.getKaihuhang()))) &&
            ((this.mac==null && other.getMac()==null) || 
             (this.mac!=null &&
              this.mac.equals(other.getMac()))) &&
            ((this.orderId==null && other.getOrderId()==null) || 
             (this.orderId!=null &&
              this.orderId.equals(other.getOrderId()))) &&
            ((this.province_city==null && other.getProvince_city()==null) || 
             (this.province_city!=null &&
              this.province_city.equals(other.getProvince_city()))) &&
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
        if (getBankCardNumber() != null) {
            _hashCode += getBankCardNumber().hashCode();
        }
        if (getBankName() != null) {
            _hashCode += getBankName().hashCode();
        }
        _hashCode += new Double(getCreditCharge()).hashCode();
        if (getCreditName() != null) {
            _hashCode += getCreditName().hashCode();
        }
        _hashCode += new Double(getDealCharge()).hashCode();
        if (getDealId() != null) {
            _hashCode += getDealId().hashCode();
        }
        _hashCode += new Double(getDebitCharge()).hashCode();
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getFailureCause() != null) {
            _hashCode += getFailureCause().hashCode();
        }
        if (getKaihuhang() != null) {
            _hashCode += getKaihuhang().hashCode();
        }
        if (getMac() != null) {
            _hashCode += getMac().hashCode();
        }
        if (getOrderId() != null) {
            _hashCode += getOrderId().hashCode();
        }
        if (getProvince_city() != null) {
            _hashCode += getProvince_city().hashCode();
        }
        _hashCode += (isResultFlag() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static TypeDesc typeDesc =
        new TypeDesc(BankResponseBean.class, true);

    static {
        typeDesc.setXmlType(new QName("http://complatible.dto.domain.seashell.bill99.com", "BankResponseBean"));
        ElementDesc elemField = new ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new QName("", "amount"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("bankCardNumber");
        elemField.setXmlName(new QName("", "bankCardNumber"));
        elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("bankName");
        elemField.setXmlName(new QName("", "bankName"));
        elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("creditCharge");
        elemField.setXmlName(new QName("", "creditCharge"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("creditName");
        elemField.setXmlName(new QName("", "creditName"));
        elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("dealCharge");
        elemField.setXmlName(new QName("", "dealCharge"));
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
        elemField.setFieldName("debitCharge");
        elemField.setXmlName(new QName("", "debitCharge"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new QName("", "description"));
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
        elemField.setFieldName("kaihuhang");
        elemField.setXmlName(new QName("", "kaihuhang"));
        elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("mac");
        elemField.setXmlName(new QName("", "mac"));
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
        elemField.setFieldName("province_city");
        elemField.setXmlName(new QName("", "province_city"));
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
