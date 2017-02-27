/**
 * SimpleRequestBean.java
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

public class SimpleRequestBean  implements Serializable {

	private static final long serialVersionUID = 1L;

	private double amount;//交易金额 整数或小数数字  以人民币“元”为单位   非空

    private String correctName;//验 证收款方姓名标志

    private String creditContact;//收 款方快钱用户名（Email或手机号）中文或英文。和商户提交时一致

    private String creditName;//收款方姓名 中文或英文 非空

    private String currencyCode;//货币类型  固定值：1 1 代表人民币。和商户提交时一致

    private String description;//交易备注 可以是中文或英文

    private String mac;//加密字符串 非空 按照如下规则组成加密字符串 {bankCardNumber}{amount}{orderId}{key}加密后加密字符串应为大写。

    private String orderId;//订单号 非空

    private String tempAccount;//收 款方还不是快钱用户时是否付款标志  固定选择值：y、ny：可以对非快钱用户直接付款n：不能对非快钱用户付款和商户提交时一致

    public SimpleRequestBean() {
    }

    public SimpleRequestBean(
           double amount,
           String correctName,
           String creditContact,
           String creditName,
           String currencyCode,
           String description,
           String mac,
           String orderId,
           String tempAccount) {
           this.amount = amount;
           this.correctName = correctName;
           this.creditContact = creditContact;
           this.creditName = creditName;
           this.currencyCode = currencyCode;
           this.description = description;
           this.mac = mac;
           this.orderId = orderId;
           this.tempAccount = tempAccount;
    }


    /**
     * Gets the amount value for this SimpleRequestBean.
     * 
     * @return amount
     */
    public double getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this SimpleRequestBean.
     * 
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }


    /**
     * Gets the correctName value for this SimpleRequestBean.
     * 
     * @return correctName
     */
    public String getCorrectName() {
        return correctName;
    }


    /**
     * Sets the correctName value for this SimpleRequestBean.
     * 
     * @param correctName
     */
    public void setCorrectName(String correctName) {
        this.correctName = correctName;
    }


    /**
     * Gets the creditContact value for this SimpleRequestBean.
     * 
     * @return creditContact
     */
    public String getCreditContact() {
        return creditContact;
    }


    /**
     * Sets the creditContact value for this SimpleRequestBean.
     * 
     * @param creditContact
     */
    public void setCreditContact(String creditContact) {
        this.creditContact = creditContact;
    }


    /**
     * Gets the creditName value for this SimpleRequestBean.
     * 
     * @return creditName
     */
    public String getCreditName() {
        return creditName;
    }


    /**
     * Sets the creditName value for this SimpleRequestBean.
     * 
     * @param creditName
     */
    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }


    /**
     * Gets the currencyCode value for this SimpleRequestBean.
     * 
     * @return currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }


    /**
     * Sets the currencyCode value for this SimpleRequestBean.
     * 
     * @param currencyCode
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


    /**
     * Gets the description value for this SimpleRequestBean.
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this SimpleRequestBean.
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Gets the mac value for this SimpleRequestBean.
     * 
     * @return mac
     */
    public String getMac() {
        return mac;
    }


    /**
     * Sets the mac value for this SimpleRequestBean.
     * 
     * @param mac
     */
    public void setMac(String mac) {
        this.mac = mac;
    }


    /**
     * Gets the orderId value for this SimpleRequestBean.
     * 
     * @return orderId
     */
    public String getOrderId() {
        return orderId;
    }


    /**
     * Sets the orderId value for this SimpleRequestBean.
     * 
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    /**
     * Gets the tempAccount value for this SimpleRequestBean.
     * 
     * @return tempAccount
     */
    public String getTempAccount() {
        return tempAccount;
    }


    /**
     * Sets the tempAccount value for this SimpleRequestBean.
     * 
     * @param tempAccount
     */
    public void setTempAccount(String tempAccount) {
        this.tempAccount = tempAccount;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SimpleRequestBean)) return false;
        SimpleRequestBean other = (SimpleRequestBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.amount == other.getAmount() &&
            ((this.correctName==null && other.getCorrectName()==null) || 
             (this.correctName!=null &&
              this.correctName.equals(other.getCorrectName()))) &&
            ((this.creditContact==null && other.getCreditContact()==null) || 
             (this.creditContact!=null &&
              this.creditContact.equals(other.getCreditContact()))) &&
            ((this.creditName==null && other.getCreditName()==null) || 
             (this.creditName!=null &&
              this.creditName.equals(other.getCreditName()))) &&
            ((this.currencyCode==null && other.getCurrencyCode()==null) || 
             (this.currencyCode!=null &&
              this.currencyCode.equals(other.getCurrencyCode()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.mac==null && other.getMac()==null) || 
             (this.mac!=null &&
              this.mac.equals(other.getMac()))) &&
            ((this.orderId==null && other.getOrderId()==null) || 
             (this.orderId!=null &&
              this.orderId.equals(other.getOrderId()))) &&
            ((this.tempAccount==null && other.getTempAccount()==null) || 
             (this.tempAccount!=null &&
              this.tempAccount.equals(other.getTempAccount())));
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
        if (getCorrectName() != null) {
            _hashCode += getCorrectName().hashCode();
        }
        if (getCreditContact() != null) {
            _hashCode += getCreditContact().hashCode();
        }
        if (getCreditName() != null) {
            _hashCode += getCreditName().hashCode();
        }
        if (getCurrencyCode() != null) {
            _hashCode += getCurrencyCode().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getMac() != null) {
            _hashCode += getMac().hashCode();
        }
        if (getOrderId() != null) {
            _hashCode += getOrderId().hashCode();
        }
        if (getTempAccount() != null) {
            _hashCode += getTempAccount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static TypeDesc typeDesc =
        new TypeDesc(SimpleRequestBean.class, true);

    static {
        typeDesc.setXmlType(new QName("http://complatible.dto.domain.seashell.bill99.com", "SimpleRequestBean"));
        ElementDesc elemField = new ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new QName("", "amount"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("correctName");
        elemField.setXmlName(new QName("", "correctName"));
        elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("creditContact");
        elemField.setXmlName(new QName("", "creditContact"));
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
        elemField.setFieldName("currencyCode");
        elemField.setXmlName(new QName("", "currencyCode"));
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
        elemField.setFieldName("tempAccount");
        elemField.setXmlName(new QName("", "tempAccount"));
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
