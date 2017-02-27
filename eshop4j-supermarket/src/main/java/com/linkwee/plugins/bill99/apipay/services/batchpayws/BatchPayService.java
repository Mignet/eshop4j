/**
 * BatchPayService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.linkwee.plugins.bill99.apipay.services.batchpayws;

import java.net.URL;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

public interface BatchPayService extends Service {
    public String getBatchPayWSAddress();

    public BatchPay getBatchPayWS() throws ServiceException;

    public BatchPay getBatchPayWS(URL portAddress) throws ServiceException;
}
