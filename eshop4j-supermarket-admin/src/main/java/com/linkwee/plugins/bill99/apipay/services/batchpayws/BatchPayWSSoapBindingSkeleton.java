/**
 * BatchPayWSSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.linkwee.plugins.bill99.apipay.services.batchpayws;

import com.linkwee.plugins.bill99.entity.BankRequestBean;
import com.linkwee.plugins.bill99.entity.BankResponseBean;
import com.linkwee.plugins.bill99.entity.PostRequestBean;
import com.linkwee.plugins.bill99.entity.PostResponseBean;
import com.linkwee.plugins.bill99.entity.QueryRequestBean;
import com.linkwee.plugins.bill99.entity.QueryResponseBean;
import com.linkwee.plugins.bill99.entity.SimpleRequestBean;
import com.linkwee.plugins.bill99.entity.SimpleResponseBean;

public class BatchPayWSSoapBindingSkeleton implements BatchPay, org.apache.axis.wsdl.Skeleton {
    private BatchPay impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "input"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://complatible.dto.domain.seashell.bill99.com", "QueryRequestBean"), QueryRequestBean.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "username"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ip"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("queryDeal", _params, new javax.xml.namespace.QName("", "queryDealReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://sandbox.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_QueryResponseBean"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://compatible.api.seashell.bill99.com", "queryDeal"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("queryDeal") == null) {
            _myOperations.put("queryDeal", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("queryDeal")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "input"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://sandbox.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_BankRequestBean"), BankRequestBean[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "username"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ip"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("bankPay", _params, new javax.xml.namespace.QName("", "bankPayReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://sandbox.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_BankResponseBean"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://compatible.api.seashell.bill99.com", "bankPay"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("bankPay") == null) {
            _myOperations.put("bankPay", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("bankPay")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "input"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://sandbox.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_SimpleRequestBean"), SimpleRequestBean[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "username"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ip"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("simplePay", _params, new javax.xml.namespace.QName("", "simplePayReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://sandbox.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_SimpleResponseBean"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://compatible.api.seashell.bill99.com", "simplePay"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("simplePay") == null) {
            _myOperations.put("simplePay", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("simplePay")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "input"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://sandbox.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_PostRequestBean"), PostRequestBean[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "username"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ip"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("postPay", _params, new javax.xml.namespace.QName("", "postPayReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://sandbox.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_PostResponseBean"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://compatible.api.seashell.bill99.com", "postPay"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("postPay") == null) {
            _myOperations.put("postPay", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("postPay")).add(_oper);
    }

    public BatchPayWSSoapBindingSkeleton() {
        this.impl = new BatchPayWSSoapBindingImpl();
    }

    public BatchPayWSSoapBindingSkeleton(BatchPay impl) {
        this.impl = impl;
    }
    public QueryResponseBean[] queryDeal(QueryRequestBean input, java.lang.String username, java.lang.String ip) throws java.rmi.RemoteException
    {
        QueryResponseBean[] ret = impl.queryDeal(input, username, ip);
        return ret;
    }

    public BankResponseBean[] bankPay(BankRequestBean[] input, java.lang.String username, java.lang.String ip) throws java.rmi.RemoteException
    {
        BankResponseBean[] ret = impl.bankPay(input, username, ip);
        return ret;
    }

    public SimpleResponseBean[] simplePay(SimpleRequestBean[] input, java.lang.String username, java.lang.String ip) throws java.rmi.RemoteException
    {
        SimpleResponseBean[] ret = impl.simplePay(input, username, ip);
        return ret;
    }

    public PostResponseBean[] postPay(PostRequestBean[] input, java.lang.String username, java.lang.String ip) throws java.rmi.RemoteException
    {
        PostResponseBean[] ret = impl.postPay(input, username, ip);
        return ret;
    }

}
