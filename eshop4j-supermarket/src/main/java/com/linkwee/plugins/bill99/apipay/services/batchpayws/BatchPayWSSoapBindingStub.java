/**
 * BatchPayWSSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.linkwee.plugins.bill99.apipay.services.batchpayws;

import java.rmi.RemoteException;
import java.util.Vector;
import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.client.Stub;
import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.ser.ArrayDeserializerFactory;
import org.apache.axis.encoding.ser.ArraySerializerFactory;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.encoding.ser.EnumDeserializerFactory;
import org.apache.axis.encoding.ser.EnumSerializerFactory;
import org.apache.axis.encoding.ser.SimpleDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleListDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleListSerializerFactory;
import org.apache.axis.encoding.ser.SimpleSerializerFactory;

import com.linkwee.plugins.bill99.entity.BankRequestBean;
import com.linkwee.plugins.bill99.entity.BankResponseBean;
import com.linkwee.plugins.bill99.entity.PostRequestBean;
import com.linkwee.plugins.bill99.entity.PostResponseBean;
import com.linkwee.plugins.bill99.entity.QueryRequestBean;
import com.linkwee.plugins.bill99.entity.QueryResponseBean;
import com.linkwee.plugins.bill99.entity.SimpleRequestBean;
import com.linkwee.plugins.bill99.entity.SimpleResponseBean;

public class BatchPayWSSoapBindingStub extends Stub implements BatchPay {
    private Vector cachedSerClasses = new Vector();
    private Vector cachedSerQNames = new Vector();
    private Vector cachedSerFactories = new Vector();
    private Vector cachedDeserFactories = new Vector();

    static OperationDesc [] _operations;

    static {
        _operations = new OperationDesc[4];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        OperationDesc oper;
        ParameterDesc param;
        oper = new OperationDesc();
        oper.setName("queryDeal");
        param = new ParameterDesc(new QName("", "input"), ParameterDesc.IN, new QName("http://complatible.dto.domain.seashell.bill99.com", "QueryRequestBean"), QueryRequestBean.class, false, false);
        oper.addParameter(param);
        param = new ParameterDesc(new QName("", "username"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
        oper.addParameter(param);
        param = new ParameterDesc(new QName("", "ip"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_QueryResponseBean"));
        oper.setReturnClass(QueryResponseBean[].class);
        oper.setReturnQName(new QName("", "queryDealReturn"));
        oper.setStyle(Style.RPC);
        oper.setUse(Use.ENCODED);
        _operations[0] = oper;

        oper = new OperationDesc();
        oper.setName("bankPay");
        param = new ParameterDesc(new QName("", "input"), ParameterDesc.IN, new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_BankRequestBean"), BankRequestBean[].class, false, false);
        oper.addParameter(param);
        param = new ParameterDesc(new QName("", "username"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
        oper.addParameter(param);
        param = new ParameterDesc(new QName("", "ip"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_BankResponseBean"));
        oper.setReturnClass(BankResponseBean[].class);
        oper.setReturnQName(new QName("", "bankPayReturn"));
        oper.setStyle(Style.RPC);
        oper.setUse(Use.ENCODED);
        _operations[1] = oper;

        oper = new OperationDesc();
        oper.setName("simplePay");
        param = new ParameterDesc(new QName("", "input"), ParameterDesc.IN, new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_SimpleRequestBean"), SimpleRequestBean[].class, false, false);
        oper.addParameter(param);
        param = new ParameterDesc(new QName("", "username"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
        oper.addParameter(param);
        param = new ParameterDesc(new QName("", "ip"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_SimpleResponseBean"));
        oper.setReturnClass(SimpleResponseBean[].class);
        oper.setReturnQName(new QName("", "simplePayReturn"));
        oper.setStyle(Style.RPC);
        oper.setUse(Use.ENCODED);
        _operations[2] = oper;

        oper = new OperationDesc();
        oper.setName("postPay");
        param = new ParameterDesc(new QName("", "input"), ParameterDesc.IN, new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_PostRequestBean"), PostRequestBean[].class, false, false);
        oper.addParameter(param);
        param = new ParameterDesc(new QName("", "username"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
        oper.addParameter(param);
        param = new ParameterDesc(new QName("", "ip"), ParameterDesc.IN, new QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_PostResponseBean"));
        oper.setReturnClass(PostResponseBean[].class);
        oper.setReturnQName(new QName("", "postPayReturn"));
        oper.setStyle(Style.RPC);
        oper.setUse(Use.ENCODED);
        _operations[3] = oper;

    }

    public BatchPayWSSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public BatchPayWSSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public BatchPayWSSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new Service();
        } else {
            super.service = service;
        }
        ((Service)super.service).setTypeMappingVersion("1.2");
            Class cls;
            QName qName;
            QName qName2;
            Class beansf = BeanSerializerFactory.class;
            Class beandf = BeanDeserializerFactory.class;
            Class enumsf = EnumSerializerFactory.class;
            Class enumdf = EnumDeserializerFactory.class;
            Class arraysf = ArraySerializerFactory.class;
            Class arraydf = ArrayDeserializerFactory.class;
            Class simplesf = SimpleSerializerFactory.class;
            Class simpledf = SimpleDeserializerFactory.class;
            Class simplelistsf = SimpleListSerializerFactory.class;
            Class simplelistdf = SimpleListDeserializerFactory.class;
            qName = new QName("http://complatible.dto.domain.seashell.bill99.com", "BankRequestBean");
            cachedSerQNames.add(qName);
            cls = BankRequestBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new QName("http://complatible.dto.domain.seashell.bill99.com", "BankResponseBean");
            cachedSerQNames.add(qName);
            cls = BankResponseBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new QName("http://complatible.dto.domain.seashell.bill99.com", "PostRequestBean");
            cachedSerQNames.add(qName);
            cls = PostRequestBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new QName("http://complatible.dto.domain.seashell.bill99.com", "PostResponseBean");
            cachedSerQNames.add(qName);
            cls = PostResponseBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new QName("http://complatible.dto.domain.seashell.bill99.com", "QueryRequestBean");
            cachedSerQNames.add(qName);
            cls = QueryRequestBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new QName("http://complatible.dto.domain.seashell.bill99.com", "QueryResponseBean");
            cachedSerQNames.add(qName);
            cls = QueryResponseBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new QName("http://complatible.dto.domain.seashell.bill99.com", "SimpleRequestBean");
            cachedSerQNames.add(qName);
            cls = SimpleRequestBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new QName("http://complatible.dto.domain.seashell.bill99.com", "SimpleResponseBean");
            cachedSerQNames.add(qName);
            cls = SimpleResponseBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_BankRequestBean");
            cachedSerQNames.add(qName);
            cls = BankRequestBean[].class;
            cachedSerClasses.add(cls);
            qName = new QName("http://complatible.dto.domain.seashell.bill99.com", "BankRequestBean");
            qName2 = null;
            cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new ArrayDeserializerFactory());

            qName = new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_BankResponseBean");
            cachedSerQNames.add(qName);
            cls = BankResponseBean[].class;
            cachedSerClasses.add(cls);
            qName = new QName("http://complatible.dto.domain.seashell.bill99.com", "BankResponseBean");
            qName2 = null;
            cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new ArrayDeserializerFactory());

            qName = new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_PostRequestBean");
            cachedSerQNames.add(qName);
            cls = PostRequestBean[].class;
            cachedSerClasses.add(cls);
            qName = new QName("http://complatible.dto.domain.seashell.bill99.com", "PostRequestBean");
            qName2 = null;
            cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new ArrayDeserializerFactory());

            qName = new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_PostResponseBean");
            cachedSerQNames.add(qName);
            cls = PostResponseBean[].class;
            cachedSerClasses.add(cls);
            qName = new QName("http://complatible.dto.domain.seashell.bill99.com", "PostResponseBean");
            qName2 = null;
            cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new ArrayDeserializerFactory());

            qName = new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_QueryResponseBean");
            cachedSerQNames.add(qName);
            cls = QueryResponseBean[].class;
            cachedSerClasses.add(cls);
            qName = new QName("http://complatible.dto.domain.seashell.bill99.com", "QueryResponseBean");
            qName2 = null;
            cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new ArrayDeserializerFactory());

            qName = new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_SimpleRequestBean");
            cachedSerQNames.add(qName);
            cls = SimpleRequestBean[].class;
            cachedSerClasses.add(cls);
            qName = new QName("http://complatible.dto.domain.seashell.bill99.com", "SimpleRequestBean");
            qName2 = null;
            cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new ArrayDeserializerFactory());

            qName = new QName("http://www.99bill.com/apipay/services/BatchPayWS", "ArrayOf_tns1_SimpleResponseBean");
            cachedSerQNames.add(qName);
            cls = SimpleResponseBean[].class;
            cachedSerClasses.add(cls);
            qName = new QName("http://complatible.dto.domain.seashell.bill99.com", "SimpleResponseBean");
            qName2 = null;
            cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new ArrayDeserializerFactory());

    }

    protected Call createCall() throws RemoteException {
        try {
            Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        Class cls = (Class) cachedSerClasses.get(i);
                        QName qName =
                                (QName) cachedSerQNames.get(i);
                        Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            Class sf = (Class)
                                 cachedSerFactories.get(i);
                            Class df = (Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public QueryResponseBean[] queryDeal(QueryRequestBean input, String username, String ip) throws RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new QName("http://compatible.api.seashell.bill99.com", "queryDeal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {input, username, ip});

        if (_resp instanceof RemoteException) {
            throw (RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (QueryResponseBean[]) _resp;
            } catch (Exception _exception) {
                return (QueryResponseBean[]) org.apache.axis.utils.JavaUtils.convert(_resp, QueryResponseBean[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public BankResponseBean[] bankPay(BankRequestBean[] input, String username, String ip) throws RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new QName("http://compatible.api.seashell.bill99.com", "bankPay"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {input, username, ip});

        if (_resp instanceof RemoteException) {
            throw (RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (BankResponseBean[]) _resp;
            } catch (Exception _exception) {
                return (BankResponseBean[]) org.apache.axis.utils.JavaUtils.convert(_resp, BankResponseBean[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public SimpleResponseBean[] simplePay(SimpleRequestBean[] input, String username, String ip) throws RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new QName("http://compatible.api.seashell.bill99.com", "simplePay"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {input, username, ip});

        if (_resp instanceof RemoteException) {
            throw (RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (SimpleResponseBean[]) _resp;
            } catch (Exception _exception) {
                return (SimpleResponseBean[]) org.apache.axis.utils.JavaUtils.convert(_resp, SimpleResponseBean[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public PostResponseBean[] postPay(PostRequestBean[] input, String username, String ip) throws RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new QName("http://compatible.api.seashell.bill99.com", "postPay"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {input, username, ip});

        if (_resp instanceof RemoteException) {
            throw (RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (PostResponseBean[]) _resp;
            } catch (Exception _exception) {
                return (PostResponseBean[]) org.apache.axis.utils.JavaUtils.convert(_resp, PostResponseBean[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
