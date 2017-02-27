/**
 * BatchPayWSSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.linkwee.plugins.bill99.apipay.services.batchpayws;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.axis.description.FaultDesc;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import com.linkwee.plugins.bill99.entity.BankRequestBean;
import com.linkwee.plugins.bill99.entity.BankResponseBean;
import com.linkwee.plugins.bill99.entity.PostRequestBean;
import com.linkwee.plugins.bill99.entity.PostResponseBean;
import com.linkwee.plugins.bill99.entity.QueryRequestBean;
import com.linkwee.plugins.bill99.entity.QueryResponseBean;
import com.linkwee.plugins.bill99.entity.SimpleRequestBean;
import com.linkwee.plugins.bill99.entity.SimpleResponseBean;

public class BatchPayWSSoapBindingSkeleton implements BatchPay, org.apache.axis.wsdl.Skeleton {

	private static final long serialVersionUID = -3086124222282306946L;
	private BatchPay impl;
    private static Map _myOperations = new Hashtable();
    private static Collection _myOperationsList = new ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static List getOperationDescByName(String methodName) {
        return (List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        OperationDesc _oper;
        FaultDesc _fault;
        ParameterDesc [] _params;
        _params = new ParameterDesc [] {
            new ParameterDesc(new QName("", "input"), ParameterDesc.IN, new QName("http://complatible.dto.domain.seashell.bill99.com", "QueryRequestBean"), QueryRequestBean.class, false, false), 
            new ParameterDesc(new QName("", "username"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false), 
            new ParameterDesc(new QName("", "ip"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false), 
        };
        _oper = new OperationDesc("queryDeal", _params, new QName("", "queryDealReturn"));
        _oper.setReturnType(new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_QueryResponseBean"));
        _oper.setElementQName(new QName("http://compatible.api.seashell.bill99.com", "queryDeal"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("queryDeal") == null) {
            _myOperations.put("queryDeal", new ArrayList());
        }
        ((List)_myOperations.get("queryDeal")).add(_oper);
        _params = new ParameterDesc [] {
            new ParameterDesc(new QName("", "input"), ParameterDesc.IN, new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_BankRequestBean"), BankRequestBean[].class, false, false), 
            new ParameterDesc(new QName("", "username"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false), 
            new ParameterDesc(new QName("", "ip"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false), 
        };
        _oper = new OperationDesc("bankPay", _params, new QName("", "bankPayReturn"));
        _oper.setReturnType(new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_BankResponseBean"));
        _oper.setElementQName(new QName("http://compatible.api.seashell.bill99.com", "bankPay"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("bankPay") == null) {
            _myOperations.put("bankPay", new ArrayList());
        }
        ((List)_myOperations.get("bankPay")).add(_oper);
        _params = new ParameterDesc [] {
            new ParameterDesc(new QName("", "input"), ParameterDesc.IN, new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_SimpleRequestBean"), SimpleRequestBean[].class, false, false), 
            new ParameterDesc(new QName("", "username"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false), 
            new ParameterDesc(new QName("", "ip"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false), 
        };
        _oper = new OperationDesc("simplePay", _params, new QName("", "simplePayReturn"));
        _oper.setReturnType(new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_SimpleResponseBean"));
        _oper.setElementQName(new QName("http://compatible.api.seashell.bill99.com", "simplePay"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("simplePay") == null) {
            _myOperations.put("simplePay", new ArrayList());
        }
        ((List)_myOperations.get("simplePay")).add(_oper);
        _params = new ParameterDesc [] {
            new ParameterDesc(new QName("", "input"), ParameterDesc.IN, new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_PostRequestBean"), PostRequestBean[].class, false, false), 
            new ParameterDesc(new QName("", "username"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false), 
            new ParameterDesc(new QName("", "ip"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false), 
        };
        _oper = new OperationDesc("postPay", _params, new QName("", "postPayReturn"));
        _oper.setReturnType(new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_PostResponseBean"));
        _oper.setElementQName(new QName("http://compatible.api.seashell.bill99.com", "postPay"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("postPay") == null) {
            _myOperations.put("postPay", new ArrayList());
        }
        ((List)_myOperations.get("postPay")).add(_oper);
    }

    public BatchPayWSSoapBindingSkeleton() {
        this.impl = new BatchPayWSSoapBindingImpl();
    }

    public BatchPayWSSoapBindingSkeleton(BatchPay impl) {
        this.impl = impl;
    }
    public QueryResponseBean[] queryDeal(QueryRequestBean input, String username, String ip) throws RemoteException
    {
        QueryResponseBean[] ret = impl.queryDeal(input, username, ip);
        return ret;
    }

    public BankResponseBean[] bankPay(BankRequestBean[] input, String username, String ip) throws RemoteException
    {
        BankResponseBean[] ret = impl.bankPay(input, username, ip);
        return ret;
    }

    public SimpleResponseBean[] simplePay(SimpleRequestBean[] input, String username, String ip) throws RemoteException
    {
        SimpleResponseBean[] ret = impl.simplePay(input, username, ip);
        return ret;
    }

    public PostResponseBean[] postPay(PostRequestBean[] input, String username, String ip) throws RemoteException
    {
        PostResponseBean[] ret = impl.postPay(input, username, ip);
        return ret;
    }

}
