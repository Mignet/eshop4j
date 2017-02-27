/**
 * BankRequestBean.java
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

public class BankRequestBean  implements Serializable {

	private static final long serialVersionUID = 3006185332498890632L;

	private double amount;//交易金额 整数或小数数字  以人民币“元”为单位   非空

    private String bankCardNumber;//银行卡号 收款人的银行卡卡号 非空

    private String bankName;//银行名称 请填写银行的标准名称 详见下文支持银行列表 非空

    private String creditName;//收款方姓名 中文或英文 非空

    private String description;//交易备注 可以是中文或英文

    private String kaihuhang;//开户行 银行卡开户行的名称 非空

    private String mac;//加密字符串 非空 按照如下规则组成加密字符串 {bankCardNumber}{amount}{orderId}{key}加密后加密字符串应为大写。

    private String orderId;//订单号 非空

    private String province_city;//城市  非空 主要只需要城市名，不需要省份名。也不要带“市” “自治区（县）”等 非空

    public BankRequestBean() {
    }

    public BankRequestBean(
           double amount,
           String bankCardNumber,
           String bankName,
           String creditName,
           String description,
           String kaihuhang,
           String mac,
           String orderId,
           String province_city) {
           this.amount = amount;
           this.bankCardNumber = bankCardNumber;
           this.bankName = bankName;
           this.creditName = creditName;
           this.description = description;
           this.kaihuhang = kaihuhang;
           this.mac = mac;
           this.orderId = orderId;
           this.province_city = province_city;
    }


    /**
     * Gets the amount value for this BankRequestBean.
     * 
     * @return amount
     */
    public double getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this BankRequestBean.
     * 
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }


    /**
     * Gets the bankCardNumber value for this BankRequestBean.
     * 
     * @return bankCardNumber
     */
    public String getBankCardNumber() {
        return bankCardNumber;
    }


    /**
     * Sets the bankCardNumber value for this BankRequestBean.
     * 
     * @param bankCardNumber
     */
    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }


    /**
     * Gets the bankName value for this BankRequestBean.
     * 
     * @return bankName
     */
    public String getBankName() {
        return bankName;
    }


    /**
     * Sets the bankName value for this BankRequestBean.
     * 
     * @param bankName
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }


    /**
     * Gets the creditName value for this BankRequestBean.
     * 
     * @return creditName
     */
    public String getCreditName() {
        return creditName;
    }


    /**
     * Sets the creditName value for this BankRequestBean.
     * 
     * @param creditName
     */
    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }


    /**
     * Gets the description value for this BankRequestBean.
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this BankRequestBean.
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Gets the kaihuhang value for this BankRequestBean.
     * 
     * @return kaihuhang
     */
    public String getKaihuhang() {
        return kaihuhang;
    }


    /**
     * Sets the kaihuhang value for this BankRequestBean.
     * 
     * @param kaihuhang
     */
    public void setKaihuhang(String kaihuhang) {
        this.kaihuhang = kaihuhang;
    }


    /**
     * Gets the mac value for this BankRequestBean.
     * 
     * @return mac
     */
    public String getMac() {
        return mac;
    }


    /**
     * Sets the mac value for this BankRequestBean.
     * 
     * @param mac
     */
    public void setMac(String mac) {
        this.mac = mac;
    }


    /**
     * Gets the orderId value for this BankRequestBean.
     * 
     * @return orderId
     */
    public String getOrderId() {
        return orderId;
    }


    /**
     * Sets the orderId value for this BankRequestBean.
     * 
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    /**
     * Gets the province_city value for this BankRequestBean.
     * 
     * @return province_city
     */
    public String getProvince_city() {
        return province_city;
    }


    /**
     * Sets the province_city value for this BankRequestBean.
     * 
     * @param province_city
     */
    public void setProvince_city(String province_city) {
        this.province_city = province_city;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof BankRequestBean)) return false;
        BankRequestBean other = (BankRequestBean) obj;
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
            ((this.creditName==null && other.getCreditName()==null) || 
             (this.creditName!=null &&
              this.creditName.equals(other.getCreditName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
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
              this.province_city.equals(other.getProvince_city())));
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
        if (getCreditName() != null) {
            _hashCode += getCreditName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static TypeDesc typeDesc =
        new TypeDesc(BankRequestBean.class, true);

    static {
        typeDesc.setXmlType(new QName("http://complatible.dto.domain.seashell.bill99.com", "BankRequestBean"));
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
        elemField.setFieldName("creditName");
        elemField.setXmlName(new QName("", "creditName"));
        elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new QName("", "description"));
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
