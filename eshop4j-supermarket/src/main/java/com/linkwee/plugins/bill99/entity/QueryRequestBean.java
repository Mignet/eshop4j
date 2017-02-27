/**
 * QueryRequestBean.java
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

public class QueryRequestBean  implements Serializable {

	private static final long serialVersionUID = 6276189617350536160L;

	private String dealBeginDate;//交易开始时间  格式为：yyyy-mm-dd HI:MM:SS。和商户提交时一致。

    private String dealEndDate;//交易结束时间

    private String dealId;//交易号 固定选择值：0、1 0 代表按商户订单号查询；1 代表按交易时间范围查询，也可填写正常的快钱交易号，按照快钱交易号查询

    private String orderId;//商家订单号    商户提交时的订单号

    private String queryType;//查询类型  固定选择值：simplePay代表付款到快钱账户，bankPay代表付款到银行账户

    public QueryRequestBean() {
    }

    public QueryRequestBean(
           String dealBeginDate,
           String dealEndDate,
           String dealId,
           String orderId,
           String queryType) {
           this.dealBeginDate = dealBeginDate;
           this.dealEndDate = dealEndDate;
           this.dealId = dealId;
           this.orderId = orderId;
           this.queryType = queryType;
    }


    /**
     * Gets the dealBeginDate value for this QueryRequestBean.
     * 
     * @return dealBeginDate
     */
    public String getDealBeginDate() {
        return dealBeginDate;
    }


    /**
     * Sets the dealBeginDate value for this QueryRequestBean.
     * 
     * @param dealBeginDate
     */
    public void setDealBeginDate(String dealBeginDate) {
        this.dealBeginDate = dealBeginDate;
    }


    /**
     * Gets the dealEndDate value for this QueryRequestBean.
     * 
     * @return dealEndDate
     */
    public String getDealEndDate() {
        return dealEndDate;
    }


    /**
     * Sets the dealEndDate value for this QueryRequestBean.
     * 
     * @param dealEndDate
     */
    public void setDealEndDate(String dealEndDate) {
        this.dealEndDate = dealEndDate;
    }


    /**
     * Gets the dealId value for this QueryRequestBean.
     * 
     * @return dealId
     */
    public String getDealId() {
        return dealId;
    }


    /**
     * Sets the dealId value for this QueryRequestBean.
     * 
     * @param dealId
     */
    public void setDealId(String dealId) {
        this.dealId = dealId;
    }


    /**
     * Gets the orderId value for this QueryRequestBean.
     * 
     * @return orderId
     */
    public String getOrderId() {
        return orderId;
    }


    /**
     * Sets the orderId value for this QueryRequestBean.
     * 
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    /**
     * Gets the queryType value for this QueryRequestBean.
     * 
     * @return queryType
     */
    public String getQueryType() {
        return queryType;
    }


    /**
     * Sets the queryType value for this QueryRequestBean.
     * 
     * @param queryType
     */
    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof QueryRequestBean)) return false;
        QueryRequestBean other = (QueryRequestBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dealBeginDate==null && other.getDealBeginDate()==null) || 
             (this.dealBeginDate!=null &&
              this.dealBeginDate.equals(other.getDealBeginDate()))) &&
            ((this.dealEndDate==null && other.getDealEndDate()==null) || 
             (this.dealEndDate!=null &&
              this.dealEndDate.equals(other.getDealEndDate()))) &&
            ((this.dealId==null && other.getDealId()==null) || 
             (this.dealId!=null &&
              this.dealId.equals(other.getDealId()))) &&
            ((this.orderId==null && other.getOrderId()==null) || 
             (this.orderId!=null &&
              this.orderId.equals(other.getOrderId()))) &&
            ((this.queryType==null && other.getQueryType()==null) || 
             (this.queryType!=null &&
              this.queryType.equals(other.getQueryType())));
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
        if (getDealBeginDate() != null) {
            _hashCode += getDealBeginDate().hashCode();
        }
        if (getDealEndDate() != null) {
            _hashCode += getDealEndDate().hashCode();
        }
        if (getDealId() != null) {
            _hashCode += getDealId().hashCode();
        }
        if (getOrderId() != null) {
            _hashCode += getOrderId().hashCode();
        }
        if (getQueryType() != null) {
            _hashCode += getQueryType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static TypeDesc typeDesc =
        new TypeDesc(QueryRequestBean.class, true);

    static {
        typeDesc.setXmlType(new QName("http://complatible.dto.domain.seashell.bill99.com", "QueryRequestBean"));
        ElementDesc elemField = new ElementDesc();
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
        elemField.setFieldName("dealId");
        elemField.setXmlName(new QName("", "dealId"));
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
