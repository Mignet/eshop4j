package com.linkwee.test.service;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import com.linkwee.plugins.bill99.apipay.services.batchpayws.BatchPayServiceLocator;
import com.linkwee.plugins.bill99.entity.BankRequestBean;
import com.linkwee.plugins.bill99.entity.BankResponseBean;
import com.linkwee.plugins.bill99.util.MD5Util;


public class BankTest {

	public static void main(String[] args) {
		//客户编号所对应的密钥。。在账户邮箱中获取
		String key = "YKB4WI4GS6UDNZXN0";
		//城市,中文字符 主要只需要城市名，不需要省份名。也不要带"市""自治区（县）"等
		String province_city = "nj";
		//银行名称 请填写银行的标准名,详见接口文档
		String bankName ="招商银行";
		//银行卡开户行的名称
		String  kaihuhang ="招商银行南京分行鼓楼支行";
		//收款人姓名,收款人的姓名，必须与银行卡账户名相同
		String creditName ="张三";
		//银行卡号  
		String bankCardNumber ="6226584150965236";
		//交易金额  整数或小数  小数为两位以内  但小数点的最后一位不能为0 如：0.10不行。单位为人民币元  
		String amount ="10.1" ; 
		//交易备注
		String description ="活动红包奖励";    
		//订单号
		String orderId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		//组合字符串。。必须按照此顺序组串
		String macVal= bankCardNumber + amount + orderId + key;
		String mac = null;
		try {
			mac = MD5Util.md5Hex(macVal.getBytes("gb2312")).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BankRequestBean requestBean = new BankRequestBean();
		requestBean.setProvince_city(province_city);
		requestBean.setBankName(bankName);
		requestBean.setKaihuhang(kaihuhang);
		requestBean.setCreditName(creditName);
		requestBean.setBankCardNumber(bankCardNumber);
		requestBean.setAmount(Double.parseDouble(amount));
		requestBean.setDescription(description);
		requestBean.setOrderId(orderId);
		requestBean.setMac(mac);
		BankRequestBean[] queryArray = new BankRequestBean[1];
		queryArray[0] = requestBean;

		String merchant_id = "10012138842";
		String merchant_ip = "192.168.8.1";
		//String merchant_ip = "222.190.107.178";//"10.16.1.56";
		BatchPayServiceLocator locator = new BatchPayServiceLocator();
		BankResponseBean[] responseBean = new BankResponseBean[1];
		try {
			responseBean = locator.getBatchPayWS().bankPay(queryArray,merchant_id,merchant_ip);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("城市:"+responseBean[0].getProvince_city()
				+"\n银行名称: "+responseBean[0].getBankName()
				+"\n开户行: "+responseBean[0].getKaihuhang()
				+"\n收款人姓名: "+responseBean[0].getCreditName()
				+"\n银行卡号: "+responseBean[0].getBankCardNumber()
				+"\n交易金额: "+responseBean[0].getAmount()
				+"\n交易备注: "+responseBean[0].getDescription()
				+"\n订单号: "+responseBean[0].getOrderId()
				+"\n快钱交易号: "+responseBean[0].getDealId()
				+"\n快钱手续费: "+responseBean[0].getDealCharge()
				+"\n付款方费用: "+responseBean[0].getDebitCharge()
				+"\n收款方费用: "+responseBean[0].getCreditCharge()
				+"\n命令执行结果: "+responseBean[0].isResultFlag()
				+"\n失败原因代码: "+responseBean[0].getFailureCause()
				);
		
	}

}
