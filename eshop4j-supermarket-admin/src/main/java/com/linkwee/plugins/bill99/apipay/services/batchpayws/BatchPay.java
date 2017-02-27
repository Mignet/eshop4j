/**
 * BatchPay.java
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

public interface BatchPay extends java.rmi.Remote {
    public QueryResponseBean[] queryDeal(QueryRequestBean input, java.lang.String username, java.lang.String ip) throws java.rmi.RemoteException;
    public BankResponseBean[] bankPay(BankRequestBean[] input, java.lang.String username, java.lang.String ip) throws java.rmi.RemoteException;
    public SimpleResponseBean[] simplePay(SimpleRequestBean[] input, java.lang.String username, java.lang.String ip) throws java.rmi.RemoteException;
    public PostResponseBean[] postPay(PostRequestBean[] input, java.lang.String username, java.lang.String ip) throws java.rmi.RemoteException;
}
