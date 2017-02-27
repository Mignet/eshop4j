package com.eshop4j.plugins.bill99.util;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import com.eshop4j.plugins.bill99.apipay.services.batchpayws.BatchPayServiceLocator;
import com.eshop4j.plugins.bill99.entity.QueryRequestBean;
import com.eshop4j.plugins.bill99.entity.QueryResponseBean;

public class QueryDeal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	城市:nj
		银行名称: 招商银行
		开户行: 招商银行南京分行鼓楼支行
		收款人姓名: 张三
		银行卡号: 6226584150965236
		交易金额: 111.0
		交易备注: 测试'城市活动'红包奖励
		订单号: 20160725162537
		快钱交易号: 69590599
		快钱手续费: 3.01
		付款方费用: 3.01
		收款方费用: 0.0
		命令执行结果: true
		失败原因代码: 0000*/

		String merchant_id = "10012138842";
		String merchant_ip = "183.3.219.162";
		QueryRequestBean query = new QueryRequestBean();
//		query.setDealBeginDate(new SimpleDateFormat("yyyy-mm-dd HH:MM:SS").format(new Date()));
//        query.setDealEndDate(new SimpleDateFormat("yyyy-mm-dd HH:MM:SS").format(new Date()));
        query.setDealId("69590599");//快钱交易号
        query.setOrderId("20160725162537");//我们自己生成的订单号
        query.setQueryType("bankPay");
		BatchPayServiceLocator locator = new BatchPayServiceLocator();
		QueryResponseBean[] responseBean = new QueryResponseBean[1];
		try {
			responseBean = locator.getBatchPayWS().queryDeal(query, merchant_id,merchant_ip);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("交易开始时间:"+responseBean[0].getDealBeginDate()
				+"\n交易结束时间: "+responseBean[0].getDealEndDate()
				+"\n交易金额: "+responseBean[0].getAmount()
				+"\ndealFee: "+responseBean[0].getDealFee()
				+"\n交易号: "+responseBean[0].getDealId()
				+"\ndealStatus: "+responseBean[0].getDealStatus()
				+"\n失败原因代码: "+responseBean[0].getFailureCause()
				+"\n订单号: "+responseBean[0].getOrderId()
				+"\n查询类型 : "+responseBean[0].getQueryType()
				+"\n命令执行结果: "+responseBean[0].isResultFlag()
				);
	/*	交易开始时间:2016-07-25 16:25:52
		交易结束时间: 2016-07-25 16:25:52
		交易金额: 111.0
		dealFee: 3.01
		交易号: 69590599
		dealStatus: 101
		失败原因代码: null
		订单号: 20160725162537
		查询类型 : 925
		命令执行结果: true*/
	}

}
