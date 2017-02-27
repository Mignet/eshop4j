/**
 * PostResponseBean.java
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

public class PostResponseBean  implements Serializable {

	private static final long serialVersionUID = 2055904409790405754L;

	private String address;

    private double amount;

    private double creditCharge;

    private String creditName;

    private double dealCharge;

    private String dealId;

    private double debitCharge;

    private String description;

    private String failureCause;

    private String mac;

    private String orderId;

    private String postcode;

    private boolean resultFlag;

    public PostResponseBean() {
    }

    public PostResponseBean(
           String address,
           double amount,
           double creditCharge,
           String creditName,
           double dealCharge,
           String dealId,
           double debitCharge,
           String description,
           String failureCause,
           String mac,
           String orderId,
           String postcode,
           boolean resultFlag) {
           this.address = address;
           this.amount = amount;
           this.creditCharge = creditCharge;
           this.creditName = creditName;
           this.dealCharge = dealCharge;
           this.dealId = dealId;
           this.debitCharge = debitCharge;
           this.description = description;
           this.failureCause = failureCause;
           this.mac = mac;
           this.orderId = orderId;
           this.postcode = postcode;
           this.resultFlag = resultFlag;
    }


    /**
     * Gets the address value for this PostResponseBean.
     * 
     * @return address
     */
    public String getAddress() {
        return address;
    }


    /**
     * Sets the address value for this PostResponseBean.
     * 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * Gets the amount value for this PostResponseBean.
     * 
     * @return amount
     */
    public double getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this PostResponseBean.
     * 
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }


    /**
     * Gets the creditCharge value for this PostResponseBean.
     * 
     * @return creditCharge
     */
    public double getCreditCharge() {
        return creditCharge;
    }


    /**
     * Sets the creditCharge value for this PostResponseBean.
     * 
     * @param creditCharge
     */
    public void setCreditCharge(double creditCharge) {
        this.creditCharge = creditCharge;
    }


    /**
     * Gets the creditName value for this PostResponseBean.
     * 
     * @return creditName
     */
    public String getCreditName() {
        return creditName;
    }


    /**
     * Sets the creditName value for this PostResponseBean.
     * 
     * @param creditName
     */
    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }


    /**
     * Gets the dealCharge value for this PostResponseBean.
     * 
     * @return dealCharge
     */
    public double getDealCharge() {
        return dealCharge;
    }


    /**
     * Sets the dealCharge value for this PostResponseBean.
     * 
     * @param dealCharge
     */
    public void setDealCharge(double dealCharge) {
        this.dealCharge = dealCharge;
    }


    /**
     * Gets the dealId value for this PostResponseBean.
     * 
     * @return dealId
     */
    public String getDealId() {
        return dealId;
    }


    /**
     * Sets the dealId value for this PostResponseBean.
     * 
     * @param dealId
     */
    public void setDealId(String dealId) {
        this.dealId = dealId;
    }


    /**
     * Gets the debitCharge value for this PostResponseBean.
     * 
     * @return debitCharge
     */
    public double getDebitCharge() {
        return debitCharge;
    }


    /**
     * Sets the debitCharge value for this PostResponseBean.
     * 
     * @param debitCharge
     */
    public void setDebitCharge(double debitCharge) {
        this.debitCharge = debitCharge;
    }


    /**
     * Gets the description value for this PostResponseBean.
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this PostResponseBean.
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Gets the failureCause value for this PostResponseBean.
     * 
     * @return failureCause
     */
    public String getFailureCause() {
        return failureCause;
    }


    /**
     * Sets the failureCause value for this PostResponseBean.
     * 
     * @param failureCause
     */
    public void setFailureCause(String failureCause) {
        this.failureCause = failureCause;
    }


    /**
     * Gets the mac value for this PostResponseBean.
     * 
     * @return mac
     */
    public String getMac() {
        return mac;
    }


    /**
     * Sets the mac value for this PostResponseBean.
     * 
     * @param mac
     */
    public void setMac(String mac) {
        this.mac = mac;
    }


    /**
     * Gets the orderId value for this PostResponseBean.
     * 
     * @return orderId
     */
    public String getOrderId() {
        return orderId;
    }


    /**
     * Sets the orderId value for this PostResponseBean.
     * 
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    /**
     * Gets the postcode value for this PostResponseBean.
     * 
     * @return postcode
     */
    public String getPostcode() {
        return postcode;
    }


    /**
     * Sets the postcode value for this PostResponseBean.
     * 
     * @param postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


    /**
     * Gets the resultFlag value for this PostResponseBean.
     * 
     * @return resultFlag
     */
    public boolean isResultFlag() {
        return resultFlag;
    }


    /**
     * Sets the resultFlag value for this PostResponseBean.
     * 
     * @param resultFlag
     */
    public void setResultFlag(boolean resultFlag) {
        this.resultFlag = resultFlag;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PostResponseBean)) return false;
        PostResponseBean other = (PostResponseBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            this.amount == other.getAmount() &&
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
            ((this.mac==null && other.getMac()==null) || 
             (this.mac!=null &&
              this.mac.equals(other.getMac()))) &&
            ((this.orderId==null && other.getOrderId()==null) || 
             (this.orderId!=null &&
              this.orderId.equals(other.getOrderId()))) &&
            ((this.postcode==null && other.getPostcode()==null) || 
             (this.postcode!=null &&
              this.postcode.equals(other.getPostcode()))) &&
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
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        _hashCode += new Double(getAmount()).hashCode();
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
        if (getMac() != null) {
            _hashCode += getMac().hashCode();
        }
        if (getOrderId() != null) {
            _hashCode += getOrderId().hashCode();
        }
        if (getPostcode() != null) {
            _hashCode += getPostcode().hashCode();
        }
        _hashCode += (isResultFlag() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static TypeDesc typeDesc =
        new TypeDesc(PostResponseBean.class, true);

    static {
        typeDesc.setXmlType(new QName("http://complatible.dto.domain.seashell.bill99.com", "PostResponseBean"));
        ElementDesc elemField = new ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new QName("", "address"));
        elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new QName("", "amount"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
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
        elemField.setFieldName("postcode");
        elemField.setXmlName(new QName("", "postcode"));
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
