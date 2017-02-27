/**
 * BatchPay.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.linkwee.plugins.bill99.apipay.services.batchpayws;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.linkwee.plugins.bill99.entity.BankRequestBean;
import com.linkwee.plugins.bill99.entity.BankResponseBean;
import com.linkwee.plugins.bill99.entity.PostRequestBean;
import com.linkwee.plugins.bill99.entity.PostResponseBean;
import com.linkwee.plugins.bill99.entity.QueryRequestBean;
import com.linkwee.plugins.bill99.entity.QueryResponseBean;
import com.linkwee.plugins.bill99.entity.SimpleRequestBean;
import com.linkwee.plugins.bill99.entity.SimpleResponseBean;

public interface BatchPay extends Remote {
    public QueryResponseBean[] queryDeal(QueryRequestBean input, String username, String ip) throws RemoteException;
    public BankResponseBean[] bankPay(BankRequestBean[] input, String username, String ip) throws RemoteException;
    public SimpleResponseBean[] simplePay(SimpleRequestBean[] input, String username, String ip) throws RemoteException;
    public PostResponseBean[] postPay(PostRequestBean[] input, String username, String ip) throws RemoteException;
}
