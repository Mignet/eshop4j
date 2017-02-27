/**
 * SimpleResponseBean.java
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

public class SimpleResponseBean  implements Serializable {

	private static final long serialVersionUID = 335696407972807072L;

	private double amount; //交易金额 整数或小数数字  以人民币“元”为单位   非空

    private String correctName;//验证收款方姓名标志   非空 固定选择值：y、n

    private String creditAccount;

    private double creditCharge;

    private String creditContact;//收款方快钱

    private String creditName;//收款方姓名 中文或英文 非空

    private String currencyCode;//货币类型 1 代表人民币

    private double dealCharge;

    private String dealId;

    private double debitCharge;

    private String description;//交易备注 可以是中文或英文

    private String emailId;

    private String failureCause;

    private String mac;//加密字符串 非空

    private String mobile;

    private String orderId;//订单号 非空

    private boolean resultFlag;

    private String tempAccount;

    private boolean unregister;

    public SimpleResponseBean() {
    }

    public SimpleResponseBean(
           double amount,
           String correctName,
           String creditAccount,
           double creditCharge,
           String creditContact,
           String creditName,
           String currencyCode,
           double dealCharge,
           String dealId,
           double debitCharge,
           String description,
           String emailId,
           String failureCause,
           String mac,
           String mobile,
           String orderId,
           boolean resultFlag,
           String tempAccount,
           boolean unregister) {
           this.amount = amount;
           this.correctName = correctName;
           this.creditAccount = creditAccount;
           this.creditCharge = creditCharge;
           this.creditContact = creditContact;
           this.creditName = creditName;
           this.currencyCode = currencyCode;
           this.dealCharge = dealCharge;
           this.dealId = dealId;
           this.debitCharge = debitCharge;
           this.description = description;
           this.emailId = emailId;
           this.failureCause = failureCause;
           this.mac = mac;
           this.mobile = mobile;
           this.orderId = orderId;
           this.resultFlag = resultFlag;
           this.tempAccount = tempAccount;
           this.unregister = unregister;
    }


    /**
     * Gets the amount value for this SimpleResponseBean.
     * 
     * @return amount
     */
    public double getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this SimpleResponseBean.
     * 
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }


    /**
     * Gets the correctName value for this SimpleResponseBean.
     * 
     * @return correctName
     */
    public String getCorrectName() {
        return correctName;
    }


    /**
     * Sets the correctName value for this SimpleResponseBean.
     * 
     * @param correctName
     */
    public void setCorrectName(String correctName) {
        this.correctName = correctName;
    }


    /**
     * Gets the creditAccount value for this SimpleResponseBean.
     * 
     * @return creditAccount
     */
    public String getCreditAccount() {
        return creditAccount;
    }


    /**
     * Sets the creditAccount value for this SimpleResponseBean.
     * 
     * @param creditAccount
     */
    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }


    /**
     * Gets the creditCharge value for this SimpleResponseBean.
     * 
     * @return creditCharge
     */
    public double getCreditCharge() {
        return creditCharge;
    }


    /**
     * Sets the creditCharge value for this SimpleResponseBean.
     * 
     * @param creditCharge
     */
    public void setCreditCharge(double creditCharge) {
        this.creditCharge = creditCharge;
    }


    /**
     * Gets the creditContact value for this SimpleResponseBean.
     * 
     * @return creditContact
     */
    public String getCreditContact() {
        return creditContact;
    }


    /**
     * Sets the creditContact value for this SimpleResponseBean.
     * 
     * @param creditContact
     */
    public void setCreditContact(String creditContact) {
        this.creditContact = creditContact;
    }


    /**
     * Gets the creditName value for this SimpleResponseBean.
     * 
     * @return creditName
     */
    public String getCreditName() {
        return creditName;
    }


    /**
     * Sets the creditName value for this SimpleResponseBean.
     * 
     * @param creditName
     */
    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }


    /**
     * Gets the currencyCode value for this SimpleResponseBean.
     * 
     * @return currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }


    /**
     * Sets the currencyCode value for this SimpleResponseBean.
     * 
     * @param currencyCode
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


    /**
     * Gets the dealCharge value for this SimpleResponseBean.
     * 
     * @return dealCharge
     */
    public double getDealCharge() {
        return dealCharge;
    }


    /**
     * Sets the dealCharge value for this SimpleResponseBean.
     * 
     * @param dealCharge
     */
    public void setDealCharge(double dealCharge) {
        this.dealCharge = dealCharge;
    }


    /**
     * Gets the dealId value for this SimpleResponseBean.
     * 
     * @return dealId
     */
    public String getDealId() {
        return dealId;
    }


    /**
     * Sets the dealId value for this SimpleResponseBean.
     * 
     * @param dealId
     */
    public void setDealId(String dealId) {
        this.dealId = dealId;
    }


    /**
     * Gets the debitCharge value for this SimpleResponseBean.
     * 
     * @return debitCharge
     */
    public double getDebitCharge() {
        return debitCharge;
    }


    /**
     * Sets the debitCharge value for this SimpleResponseBean.
     * 
     * @param debitCharge
     */
    public void setDebitCharge(double debitCharge) {
        this.debitCharge = debitCharge;
    }


    /**
     * Gets the description value for this SimpleResponseBean.
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this SimpleResponseBean.
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Gets the emailId value for this SimpleResponseBean.
     * 
     * @return emailId
     */
    public String getEmailId() {
        return emailId;
    }


    /**
     * Sets the emailId value for this SimpleResponseBean.
     * 
     * @param emailId
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


    /**
     * Gets the failureCause value for this SimpleResponseBean.
     * 
     * @return failureCause
     */
    public String getFailureCause() {
        return failureCause;
    }


    /**
     * Sets the failureCause value for this SimpleResponseBean.
     * 
     * @param failureCause
     */
    public void setFailureCause(String failureCause) {
        this.failureCause = failureCause;
    }


    /**
     * Gets the mac value for this SimpleResponseBean.
     * 
     * @return mac
     */
    public String getMac() {
        return mac;
    }


    /**
     * Sets the mac value for this SimpleResponseBean.
     * 
     * @param mac
     */
    public void setMac(String mac) {
        this.mac = mac;
    }


    /**
     * Gets the mobile value for this SimpleResponseBean.
     * 
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }


    /**
     * Sets the mobile value for this SimpleResponseBean.
     * 
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    /**
     * Gets the orderId value for this SimpleResponseBean.
     * 
     * @return orderId
     */
    public String getOrderId() {
        return orderId;
    }


    /**
     * Sets the orderId value for this SimpleResponseBean.
     * 
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    /**
     * Gets the resultFlag value for this SimpleResponseBean.
     * 
     * @return resultFlag
     */
    public boolean isResultFlag() {
        return resultFlag;
    }


    /**
     * Sets the resultFlag value for this SimpleResponseBean.
     * 
     * @param resultFlag
     */
    public void setResultFlag(boolean resultFlag) {
        this.resultFlag = resultFlag;
    }


    /**
     * Gets the tempAccount value for this SimpleResponseBean.
     * 
     * @return tempAccount
     */
    public String getTempAccount() {
        return tempAccount;
    }


    /**
     * Sets the tempAccount value for this SimpleResponseBean.
     * 
     * @param tempAccount
     */
    public void setTempAccount(String tempAccount) {
        this.tempAccount = tempAccount;
    }


    /**
     * Gets the unregister value for this SimpleResponseBean.
     * 
     * @return unregister
     */
    public boolean isUnregister() {
        return unregister;
    }


    /**
     * Sets the unregister value for this SimpleResponseBean.
     * 
     * @param unregister
     */
    public void setUnregister(boolean unregister) {
        this.unregister = unregister;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SimpleResponseBean)) return false;
        SimpleResponseBean other = (SimpleResponseBean) obj;
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
            ((this.creditAccount==null && other.getCreditAccount()==null) || 
             (this.creditAccount!=null &&
              this.creditAccount.equals(other.getCreditAccount()))) &&
            this.creditCharge == other.getCreditCharge() &&
            ((this.creditContact==null && other.getCreditContact()==null) || 
             (this.creditContact!=null &&
              this.creditContact.equals(other.getCreditContact()))) &&
            ((this.creditName==null && other.getCreditName()==null) || 
             (this.creditName!=null &&
              this.creditName.equals(other.getCreditName()))) &&
            ((this.currencyCode==null && other.getCurrencyCode()==null) || 
             (this.currencyCode!=null &&
              this.currencyCode.equals(other.getCurrencyCode()))) &&
            this.dealCharge == other.getDealCharge() &&
            ((this.dealId==null && other.getDealId()==null) || 
             (this.dealId!=null &&
              this.dealId.equals(other.getDealId()))) &&
            this.debitCharge == other.getDebitCharge() &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.emailId==null && other.getEmailId()==null) || 
             (this.emailId!=null &&
              this.emailId.equals(other.getEmailId()))) &&
            ((this.failureCause==null && other.getFailureCause()==null) || 
             (this.failureCause!=null &&
              this.failureCause.equals(other.getFailureCause()))) &&
            ((this.mac==null && other.getMac()==null) || 
             (this.mac!=null &&
              this.mac.equals(other.getMac()))) &&
            ((this.mobile==null && other.getMobile()==null) || 
             (this.mobile!=null &&
              this.mobile.equals(other.getMobile()))) &&
            ((this.orderId==null && other.getOrderId()==null) || 
             (this.orderId!=null &&
              this.orderId.equals(other.getOrderId()))) &&
            this.resultFlag == other.isResultFlag() &&
            ((this.tempAccount==null && other.getTempAccount()==null) || 
             (this.tempAccount!=null &&
              this.tempAccount.equals(other.getTempAccount()))) &&
            this.unregister == other.isUnregister();
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
        if (getCreditAccount() != null) {
            _hashCode += getCreditAccount().hashCode();
        }
        _hashCode += new Double(getCreditCharge()).hashCode();
        if (getCreditContact() != null) {
            _hashCode += getCreditContact().hashCode();
        }
        if (getCreditName() != null) {
            _hashCode += getCreditName().hashCode();
        }
        if (getCurrencyCode() != null) {
            _hashCode += getCurrencyCode().hashCode();
        }
        _hashCode += new Double(getDealCharge()).hashCode();
        if (getDealId() != null) {
            _hashCode += getDealId().hashCode();
        }
        _hashCode += new Double(getDebitCharge()).hashCode();
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getEmailId() != null) {
            _hashCode += getEmailId().hashCode();
        }
        if (getFailureCause() != null) {
            _hashCode += getFailureCause().hashCode();
        }
        if (getMac() != null) {
            _hashCode += getMac().hashCode();
        }
        if (getMobile() != null) {
            _hashCode += getMobile().hashCode();
        }
        if (getOrderId() != null) {
            _hashCode += getOrderId().hashCode();
        }
        _hashCode += (isResultFlag() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTempAccount() != null) {
            _hashCode += getTempAccount().hashCode();
        }
        _hashCode += (isUnregister() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static TypeDesc typeDesc =
        new TypeDesc(SimpleResponseBean.class, true);

    static {
        typeDesc.setXmlType(new QName("http://complatible.dto.domain.seashell.bill99.com", "SimpleResponseBean"));
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
        elemField.setFieldName("creditAccount");
        elemField.setXmlName(new QName("", "creditAccount"));
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
        elemField.setFieldName("emailId");
        elemField.setXmlName(new QName("", "emailId"));
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
        elemField.setFieldName("mobile");
        elemField.setXmlName(new QName("", "mobile"));
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
        elemField.setFieldName("resultFlag");
        elemField.setXmlName(new QName("", "resultFlag"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("tempAccount");
        elemField.setXmlName(new QName("", "tempAccount"));
        elemField.setXmlType(new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("unregister");
        elemField.setXmlName(new QName("", "unregister"));
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
