/**
 * BatchPayServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.linkwee.plugins.bill99.apipay.services.batchpayws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.Service;
import org.apache.axis.client.Stub;


public class BatchPayServiceLocator extends Service implements BatchPayService {

	private static final long serialVersionUID = -1305597712811750949L;

	public BatchPayServiceLocator() {
    }


    public BatchPayServiceLocator(EngineConfiguration config) {
        super(config);
    }

    public BatchPayServiceLocator(String wsdlLoc, QName sName) throws ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BatchPayWS
    private String BatchPayWS_address = "https://www.99bill.com/apipay/services/BatchPayWS";

    public String getBatchPayWSAddress() {
        return BatchPayWS_address;
    }

    // The WSDD service name defaults to the port name.
    private String BatchPayWSWSDDServiceName = "BatchPayWS";

    public String getBatchPayWSWSDDServiceName() {
        return BatchPayWSWSDDServiceName;
    }

    public void setBatchPayWSWSDDServiceName(String name) {
        BatchPayWSWSDDServiceName = name;
    }

    public BatchPay getBatchPayWS() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(BatchPayWS_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getBatchPayWS(endpoint);
    }

    public BatchPay getBatchPayWS(URL portAddress) throws ServiceException {
        try {
            BatchPayWSSoapBindingStub _stub = new BatchPayWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getBatchPayWSWSDDServiceName());
            return _stub;
        }
        catch (AxisFault e) {
            return null;
        }
    }

    public void setBatchPayWSEndpointAddress(String address) {
        BatchPayWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public Remote getPort(Class serviceEndpointInterface) throws ServiceException {
        try {
            if (BatchPay.class.isAssignableFrom(serviceEndpointInterface)) {
                BatchPayWSSoapBindingStub _stub = new BatchPayWSSoapBindingStub(new URL(BatchPayWS_address), this);
                _stub.setPortName(getBatchPayWSWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new ServiceException(t);
        }
        throw new ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public Remote getPort(QName portName, Class serviceEndpointInterface) throws ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("BatchPayWS".equals(inputPortName)) {
            return getBatchPayWS();
        }
        else  {
            Remote _stub = getPort(serviceEndpointInterface);
            ((Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public QName getServiceName() {
        return new QName("http://www.99bill.com/apipay/services/BatchPayWS", "BatchPayService");
    }

    private HashSet ports = null;

    public Iterator getPorts() {
        if (ports == null) {
            ports = new HashSet();
            ports.add(new QName("http://www.99bill.com/apipay/services/BatchPayWS", "BatchPayWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws ServiceException {
        
if ("BatchPayWS".equals(portName)) {
            setBatchPayWSEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(QName portName, String address) throws ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
