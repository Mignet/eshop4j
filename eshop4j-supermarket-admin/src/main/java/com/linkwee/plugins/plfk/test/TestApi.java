package com.linkwee.plugins.plfk.test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bill99.schema.fo.settlement.BatchSettlementApplyResponse;
import com.bill99.schema.fo.settlement.BatchidQueryResponse;
import com.bill99.schema.fo.settlement.ComplexQueryResponse;
import com.bill99.schema.fo.settlement.SettlementPkiApiResponse;
import com.linkwee.plugins.plfk.api.CustomerTool;
import com.linkwee.plugins.plfk.entity.DealInfoEntity;
import com.linkwee.plugins.plfk.entity.OrderInfoEntity;

public class TestApi {
	public static String pay(String batchId) {
		/** 版本号 */
		String version = "1.0.1";
		/** 提交类型 */
		String serviceType = "fo.batch.settlement.pay";
		/** 商户编号 */
		//String memberCode = "10011629536";
		/*stage2*/
		String memberCode = "10012138842";  //10012138842    10012138840
		/** 加密类型 */
		String featureCode = "F889";
		/** 付款方帐号 */
		String payerAcctCode = memberCode + "01";
		/** 批次号 */
		String batchNo =batchId; //"batchNo_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		/** 发起日期 */
		String applyDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		/** 付款商户名称 */
		String merchantName = "测试商户";
		/** 总金额 */
		String totalAmt = "2000";
		/** 总笔额 */
		String totalCnt = "2";
		/** 付费方式 */
		String feeType = "1";
		/** 币种 */
		String cur = "RMB";
		/** 是否验证金额 */
		String checkAmtCnt = "0";
		/** 是否整批失败 */
		String batchFail = "1";
		/** 充值方式 */
		String rechargeType = "1";
		/** 是否自动退款 */
		String autoRefund = "0";   
		/** 是否短信通知 0:通知; 1:不通知*/
		String phoneNoteFlag = "0";
		/** 预留字段1 */
		String merchantMemo1 = "memo1";
		/** 预留字段2 */
		String merchantMemo2 = "memo2";
		/** 预留字段3 */
		String merchantMemo3 = "memo3";
		
		/** 商家订单号 */
		String merchantId = "orderid_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		/** 金额 */
		String amt = "1000";
		/** 银行名称 */
		String bank = "中国工行";
		/** 户名 */
		String name = "李梦丽";
		/** 卡号 */
		String bankCardNo = "6225380017381023";
		/** 开户行 */
		String branchBank = "南京支行";
		/** 对公对私0:企业; 1:个人 */ 
		String payeeType = "0";
		/** 省份 */
		String province = "";
		/** 城市 */
		String city = "南京市";
		/** 快钱交易备注 */
		String memo = "快钱备注";
		/** 银行交易用备注 */
		String bankPurpose = "银行交易备注";
		/** 银行交易备注 */
		String bankMemo = "交易备注";
		/** 收款方通知知内容 */
		String payeeNote = "通知内容";
		/** 收款方手机号 */
		String payeeMobile = "13000000000";
		/** 收款方邮件 */
		String payeeEmail = "123@126.com";	
		/** 到账时效 */
		String period = "";
		/** 商户预留字段1 */
		String orderMemo1 = "order1";
		/** 商户预留字段2 */
		String orderMemo2 = "order2";
		/** 商户预留字段3 */
		String orderMemo3 = "order3";

		List<OrderInfoEntity> ordersInfo = new ArrayList<OrderInfoEntity>();
		for (int i = 0; i < 2; i++) {
			OrderInfoEntity orderInfo = new OrderInfoEntity();
			orderInfo.setMerchantId(merchantId + i);
			orderInfo.setAmt(amt);
			orderInfo.setBank(bank);
			orderInfo.setName(name);
			orderInfo.setBankCardNo(bankCardNo);
			orderInfo.setBranchBank(branchBank);
			orderInfo.setPayeeType(payeeType);
			orderInfo.setProvince(province);
			orderInfo.setCity(city);
			orderInfo.setMemo(memo);
			orderInfo.setBankPurpose(bankPurpose);
			orderInfo.setBankMemo(bankMemo);
			orderInfo.setPayeeNote(payeeNote);
			orderInfo.setPayeeMobile(payeeMobile);
			orderInfo.setPayeeEmail(payeeEmail);
			orderInfo.setPeriod(period);
			orderInfo.setMerchantMemo1(orderMemo1);
			orderInfo.setMerchantMemo2(orderMemo2);
			orderInfo.setMerchantMemo3(orderMemo3);
			ordersInfo.add(orderInfo);
		}

		DealInfoEntity dealInfo = new DealInfoEntity();
		dealInfo.setPayerAcctCode(payerAcctCode);
		dealInfo.setBatchNo(batchNo);
		dealInfo.setApplyDate(applyDate);
		dealInfo.setName(merchantName);
		dealInfo.setTotalAmt(totalAmt);
		dealInfo.setTotalCnt(totalCnt);
		dealInfo.setFeeType(feeType);
		dealInfo.setCur(cur);
		dealInfo.setCheckAmtCnt(checkAmtCnt);
		dealInfo.setBatchFail(batchFail);
		dealInfo.setRechargeType(rechargeType);
		dealInfo.setAutoRefund(autoRefund);
		dealInfo.setPhoneNoteFlag(phoneNoteFlag);
		dealInfo.setMerchantMemo1(merchantMemo1);
		dealInfo.setMerchantMemo2(merchantMemo2);
		dealInfo.setMerchantMemo3(merchantMemo3);
		dealInfo.setOrdersInfo(ordersInfo);

		dealInfo.setServiceType(serviceType);
		dealInfo.setVersion(version);
		dealInfo.setFeatureCode(featureCode);
		dealInfo.setMemberCode(memberCode);

		CustomerTool ct = new CustomerTool();
		String result="提交失败";
		if ("F889".equalsIgnoreCase(dealInfo.getFeatureCode())) {
			SettlementPkiApiResponse response = ct.apply_ws(dealInfo);
			BatchSettlementApplyResponse bsar = (BatchSettlementApplyResponse) ct
					.unseal(response, dealInfo);// 得到解密数据
			System.out.println("提交的批次号为:"
					+ bsar.getResponseBody().getBatchNo());
			System.out.println("申请成功笔数为:"
					+ bsar.getResponseBody().getTotalApplySuccCnt());
			result="提交成功";
		} else {
			if (ct.apply_ftp(dealInfo)) {
				System.out.println("上传成功");
				result="上传成功";
			}
		}
		System.out.println("执行完毕");
		return result;
	/*	<?xml version='1.0' encoding='UTF-8'?><soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Body><tns:settlement-pki-api-response xmlns:tns="http://www.99bill.com/schema/fo/settlement" xmlns:ns1="http://www.99bill.com/schema/fo/commons" xmlns:ns0="http://www.99bill.com/schema/commons"><tns:response-header><tns:version xmlns:tns="http://www.99bill.com/schema/fo/commons"><ns0:version>1.0.1</ns0:version><ns0:service>fo.batch.settlement.pay</ns0:service></tns:version><ns1:time>20160725192024</ns1:time></tns:response-header><tns:response-body><tns:member-code>10012138842</tns:member-code><tns:status>0</tns:status><tns:error-code>0000</tns:error-code><tns:error-msg /><tns:data><ns1:original-data /><ns1:signed-data>NzBZPTcYpYdNSAqnIRis5k2VfRvzS5iuXkUmCpG93Ty7dGVImHkTjLM/uutwrzG+jWMdght4D7NDke870/dpSWsXAiddhuwIRVQj9+biT9ohzdVvMvWZBHYXcvQUEPq4Vmiw7B5cNbaCXciQvAMOhRmuokYmqyr+CZZTBuv723hg5DSH0qqQvtJSzwLo6HQF91yr0ID1udIzrhJ2/LNxZwfcN1FNFrcQMlOfeudKfZyozaW67pQ/n+aINkKE/flDS61C5AgVlp0pvY3TJhUovpTcIZK7305WoKBCD0flPlDGkOxNMtK5cHlOsPKmziQyp2Z/WJw9aC9AvtwMhTeu0Q==</ns1:signed-data><ns1:encrypted-data>WAbPhV6TRxnIi0ulLCfwOpvPlpPcT8UTOnnt/nQObgFvE0upUAR5WVCVaO8hCFnLs13+uYrWgjoFtbV5S1osiJ9pQ9wP512BsZTKy/nQEVyBBwtmg96mUou88vHGUmIg99MXrwap1ER2xLbr9wTSlAkgpNHzhTb3guRDSnLwjxUReBDu9V8BCq3kyX4llSLTKp2zfUT46unhg/SFtOFTUgCS8kNMgo67w0eYiRGNclYOa8oaGOmqR4cptaf/9DBPeBT6So5Veftu9f2IspAXOBOFg1sEbAxTR9wJqcZY5Ig/ZJ5hlhwhwQqyc+G+wWqBU/KJg/xNg0iKUc5T21uLsXI0fmHibCwMyGOaq02pJDU2UQcYCx7756c9zBmlMugQSD+9L3NBLzBI7mijIpWuf5x4q47i68e7uHfE6VH0RsdIk62e11X3kqT9Bt32yeVkNxOHoziie4cc/X77Z5zElSr4yBVd15Tzff9N0mO6xtAR5XzuaxRI1siooH5g9hbiAbqqVK2w2+uZQ8YcL2oj4ffYN3ufEbwGDngcdh6LXZA8xfUPwWLHIhyZoAiZYUEL45FKo+0p1BJ4q+2hXNDngemas0mBjkqFsU3qp6IYn0d2jtyODYB6naVmP+ykwUFmFKt6Un5Jtx4iS+zwFIPcO6qxQ7aao+3bF3JdUXYL/ZhQllCHltfVP6hhKiOa0fZQslSInSqM9zDLR4Ju6a0OkQK0i5xLFPKmRPr/sFO5zhs8nvg5LWXA5zvPmDbb68HmhRhStL3GacwltbOYqsQEL7+bmal8HHgM/zkgOOAicEk9RAbBGQeu1CB31F10tkijDTaWZGDydRmo8EYTVUQv6fFbFPFwQ7Osh22CnMlH1SNKNvt2YrS13T5kriis1GKVh3O4WDTC2fRSm6a7X+5QlOl2FT3Qc2is0DNFjCIefQzbHt/rFlvKYBSo6oSHpwaa++mdLFSPQarKEV7p23bRDFhwDajLIpTHUN6DBNI7KI088vjYzDtoGQL54d/LrdJ6lqVLvi9tCkcUCj3wfQ83znHZU3ZIBjiZtKNgrTC5UhyJeBdz5877sDJB5SJTPEIpH05KvdHqY2midSVCW5UV/JCizcSYauPuqcYfrboOZuOps2RcsJrB44FEoC7cLEQtx7RAE3UjsBr9gSzCMRdCIHrQfHaTGeGGWanNVrinR9QhOQs2rFPDV8Tn9CFqgxWoSd57wAeU7lip/O1uLxuYS/M0vNvFdx2RQwDRZOmxBoqobd7x1P3Bwb76EA1+b8g6</ns1:encrypted-data><ns1:digital-envelope>BDX65kbZ8HezIlVrCQ2BA+FNp45lmfOSy1CvXrWwUygWAOhWYvw2AGK9cnkRuaRoRerCsnSQB55pPbcjUgutN9E1/Nm5rtsXpb+ADEVlj/PO3V9Mi5ocCzvoyH8MopadK8ut/wSr2+eKzsWiYc9Y+vjdDLxoxzkuyxHefmCd5L4=</ns1:digital-envelope></tns:data></tns:response-body></tns:settlement-pki-api-response></soapenv:Body></soapenv:Envelope>
		付款商户号10012138842
		应答状态0
		申请成功
		解密后的应答报文：
		<tns:batch-settlement-apply-response xmlns:ns0="http://www.99bill.com/schema/commons" xmlns:ns1="http://www.99bill.com/schema/fo/commons" xmlns:tns="http://www.99bill.com/schema/fo/settlement">
		  <tns:response-header>
		    <tns:version xmlns:tns="http://www.99bill.com/schema/fo/commons">
		      <ns0:version>1.0.1</ns0:version>
		      <ns0:service>fo.batch.settlement.pay</ns0:service>
		    </tns:version>
		    <ns1:time>20160725192024</ns1:time>
		  </tns:response-header>
		  <tns:response-body>
		    <tns:payer-acctCode>1001213884201</tns:payer-acctCode>
		    <tns:batch-no>batchNo_20160725191953</tns:batch-no>
		    <tns:apply-date>20160725192112</tns:apply-date>
		    <tns:name>测试商户</tns:name>
		    <tns:total-amt>2000</tns:total-amt>
		    <tns:total-cnt>2</tns:total-cnt>
		    <tns:fee-type>1</tns:fee-type>
		    <tns:cur>RMB</tns:cur>
		    <tns:checkAmt-cnt>0</tns:checkAmt-cnt>
		    <tns:batch-fail>1</tns:batch-fail>
		    <tns:recharge-type>1</tns:recharge-type>
		    <tns:auto-refund>0</tns:auto-refund>
		    <tns:phoneNote-flag>0</tns:phoneNote-flag>
		    <tns:merchant-memo1>memo1</tns:merchant-memo1>
		    <tns:merchant-memo2>memo2</tns:merchant-memo2>
		    <tns:merchant-memo3>memo3</tns:merchant-memo3>
		    <tns:status>100</tns:status>
		    <tns:order-seq-id/>
		    <tns:total-applySucc-amt>2000</tns:total-applySucc-amt>
		    <tns:total-applySucc-cnt>2</tns:total-applySucc-cnt>
		    <tns:total-fee>0</tns:total-fee>
		    <tns:finishPay-date/>
		    <tns:memo/>
		    <tns:pay2bank-list>
		      <tns:pay2bank-result>
		        <tns:apply-date>20160725192112</tns:apply-date>
		        <tns:end-date/>
		        <tns:order-seq-id/>
		        <tns:fee>0</tns:fee>
		        <tns:status>101</tns:status>
		        <tns:error-code>0000</tns:error-code>
		        <tns:error-msg/>
		        <tns:bank-error-code/>
		        <tns:bank-error-msg/>
		        <tns:pay2bank>
		          <ns1:merchant-id>orderid_201607251919590</ns1:merchant-id>
		          <ns1:amt>1000</ns1:amt>
		          <ns1:bank>中国工行</ns1:bank>
		          <ns1:name>李梦丽</ns1:name>
		          <ns1:bank-card-no>6225380017381023</ns1:bank-card-no>
		          <ns1:branch-bank>南京支行</ns1:branch-bank>
		          <ns1:payee-type>0</ns1:payee-type>
		          <ns1:province/>
		          <ns1:city>南京市</ns1:city>
		          <ns1:memo>快钱备注</ns1:memo>
		          <ns1:bank-purpose>银行交易备注</ns1:bank-purpose>
		          <ns1:bank-memo>交易备注</ns1:bank-memo>
		          <ns1:payee-note>通知内容</ns1:payee-note>
		          <ns1:payee-mobile>13000000000</ns1:payee-mobile>
		          <ns1:payee-email>123@126.com</ns1:payee-email>
		          <ns1:period/>
		          <ns1:merchant-memo1>order1</ns1:merchant-memo1>
		          <ns1:merchant-memo2>order2</ns1:merchant-memo2>
		          <ns1:merchant-memo3>order3</ns1:merchant-memo3>
		        </tns:pay2bank>
		      </tns:pay2bank-result>
		      <tns:pay2bank-result>
		        <tns:apply-date>20160725192112</tns:apply-date>
		        <tns:end-date/>
		        <tns:order-seq-id/>
		        <tns:fee>0</tns:fee>
		        <tns:status>101</tns:status>
		        <tns:error-code>0000</tns:error-code>
		        <tns:error-msg/>
		        <tns:bank-error-code/>
		        <tns:bank-error-msg/>
		        <tns:pay2bank>
		          <ns1:merchant-id>orderid_201607251919591</ns1:merchant-id>
		          <ns1:amt>1000</ns1:amt>
		          <ns1:bank>中国工行</ns1:bank>
		          <ns1:name>李梦丽</ns1:name>
		          <ns1:bank-card-no>6225380017381023</ns1:bank-card-no>
		          <ns1:branch-bank>南京支行</ns1:branch-bank>
		          <ns1:payee-type>0</ns1:payee-type>
		          <ns1:province/>
		          <ns1:city>南京市</ns1:city>
		          <ns1:memo>快钱备注</ns1:memo>
		          <ns1:bank-purpose>银行交易备注</ns1:bank-purpose>
		          <ns1:bank-memo>交易备注</ns1:bank-memo>
		          <ns1:payee-note>通知内容</ns1:payee-note>
		          <ns1:payee-mobile>13000000000</ns1:payee-mobile>
		          <ns1:payee-email>123@126.com</ns1:payee-email>
		          <ns1:period/>
		          <ns1:merchant-memo1>order1</ns1:merchant-memo1>
		          <ns1:merchant-memo2>order2</ns1:merchant-memo2>
		          <ns1:merchant-memo3>order3</ns1:merchant-memo3>
		        </tns:pay2bank>
		      </tns:pay2bank-result>
		    </tns:pay2bank-list>
		  </tns:response-body>
		</tns:batch-settlement-apply-response>
		提交的批次号为:batchNo_20160725191953
		申请成功笔数为:2
		执行完毕*/
	}

	public static String query1(String batchId) {
		String version = "1.0.1";
		String serviceType = "fo.batch.settlement.batchidquery";
		String memberCode = "10012138842";  //  10012138842  10012138842
		String featureCode = "F889";
//		String batchNo = "batchNo_20110216133440";
		String batchNo =batchId;
		String listFlag = "0";// 是否显示详细明细 0：查询返回明细；1：不返回明细
		String page = "1";//页码为空，则默认为1
		String pageSize = "10";//页码显示条数

		DealInfoEntity dealInfo = new DealInfoEntity();
		dealInfo.setBatchNo(batchNo);
		dealInfo.setListFlag(listFlag);
		dealInfo.setPage(page);
		dealInfo.setPageSize(pageSize);
		dealInfo.setServiceType(serviceType);
		dealInfo.setVersion(version);
		dealInfo.setFeatureCode(featureCode);
		dealInfo.setMemberCode(memberCode);

		CustomerTool ct = new CustomerTool();
		
		SettlementPkiApiResponse response = ct.apply_ws(dealInfo);
		BatchidQueryResponse bidqr = (BatchidQueryResponse) ct.unseal(response,
				dealInfo);// 得到解密数据
		System.out.println("查询到的付款成功笔数为：" + bidqr.getResponseBody().getBatchList().getTotalApplySuccCnt());
		return "查询到付款成功笔数为：" + bidqr.getResponseBody().getBatchList().getTotalApplySuccCnt();
		/*付款商户号10012138842
		应答状态0
		申请成功
		解密后的应答报文：
		<tns:batchid-query-response xmlns:ns0="http://www.99bill.com/schema/commons" xmlns:ns1="http://www.99bill.com/schema/fo/commons" xmlns:tns="http://www.99bill.com/schema/fo/settlement">
		  <tns:response-header>
		    <tns:version xmlns:tns="http://www.99bill.com/schema/fo/commons">
		      <ns0:version>1.0.1</ns0:version>
		      <ns0:service>fo.batch.settlement.batchidquery</ns0:service>
		    </tns:version>
		    <ns1:time>20160725192738</ns1:time>
		  </tns:response-header>
		  <tns:response-body>
		    <tns:query-condition>
		      <tns:batch-no>batchNo_20160725191953</tns:batch-no>
		      <tns:page>1</tns:page>
		      <tns:page-size>10</tns:page-size>
		      <tns:list-flag>0</tns:list-flag>
		    </tns:query-condition>
		    <tns:total-page>1</tns:total-page>
		    <tns:total-cnt>2</tns:total-cnt>
		    <tns:batchList>
		      <tns:payer-acctCode>1001213884201</tns:payer-acctCode>
		      <tns:batch-no>batchNo_20160725191953</tns:batch-no>
		      <tns:apply-date>20160725192112</tns:apply-date>
		      <tns:name>测试商户</tns:name>
		      <tns:total-amt>2000</tns:total-amt>
		      <tns:total-cnt>2</tns:total-cnt>
		      <tns:fee-type>1</tns:fee-type>
		      <tns:cur>RMB</tns:cur>
		      <tns:checkAmt-cnt>0</tns:checkAmt-cnt>
		      <tns:batch-fail>1</tns:batch-fail>
		      <tns:recharge-type>1</tns:recharge-type>
		      <tns:auto-refund>0</tns:auto-refund>
		      <tns:phoneNote-flag>0</tns:phoneNote-flag>
		      <tns:merchant-memo1>memo1</tns:merchant-memo1>
		      <tns:merchant-memo2>memo2</tns:merchant-memo2>
		      <tns:merchant-memo3>memo3</tns:merchant-memo3>
		      <tns:status>101</tns:status>
		      <tns:order-seq-id>69712312</tns:order-seq-id>
		      <tns:total-applySucc-amt>2000</tns:total-applySucc-amt>
		      <tns:total-applySucc-cnt>2</tns:total-applySucc-cnt>
		      <tns:total-fee>400</tns:total-fee>
		      <tns:finishPay-date/>
		      <tns:memo/>
		      <tns:pay2bank-list>
		        <tns:pay2bank-result>
		          <tns:apply-date>20160725192112</tns:apply-date>
		          <tns:end-date/>
		          <tns:order-seq-id>69712676</tns:order-seq-id>
		          <tns:fee>200</tns:fee>
		          <tns:status>101</tns:status>
		          <tns:error-code/>
		          <tns:error-msg/>
		          <tns:bank-error-code/>
		          <tns:bank-error-msg/>
		          <tns:pay2bank>
		            <ns1:merchant-id>orderid_201607251919590</ns1:merchant-id>
		            <ns1:amt>1000</ns1:amt>
		            <ns1:bank>中国工行</ns1:bank>
		            <ns1:name>李梦丽</ns1:name>
		            <ns1:bank-card-no>6225380017381023</ns1:bank-card-no>
		            <ns1:branch-bank>南京支行</ns1:branch-bank>
		            <ns1:payee-type>0</ns1:payee-type>
		            <ns1:province/>
		            <ns1:city>南京市</ns1:city>
		            <ns1:memo>快钱备注</ns1:memo>
		            <ns1:bank-purpose>银行交易备注</ns1:bank-purpose>
		            <ns1:bank-memo>交易备注</ns1:bank-memo>
		            <ns1:payee-note>通知内容</ns1:payee-note>
		            <ns1:payee-mobile>13000000000</ns1:payee-mobile>
		            <ns1:payee-email>123@126.com</ns1:payee-email>
		            <ns1:period/>
		            <ns1:merchant-memo1>order1</ns1:merchant-memo1>
		            <ns1:merchant-memo2>order2</ns1:merchant-memo2>
		            <ns1:merchant-memo3>order3</ns1:merchant-memo3>
		          </tns:pay2bank>
		        </tns:pay2bank-result>
		        <tns:pay2bank-result>
		          <tns:apply-date>20160725192112</tns:apply-date>
		          <tns:end-date/>
		          <tns:order-seq-id>69712673</tns:order-seq-id>
		          <tns:fee>200</tns:fee>
		          <tns:status>101</tns:status>
		          <tns:error-code/>
		          <tns:error-msg/>
		          <tns:bank-error-code/>
		          <tns:bank-error-msg/>
		          <tns:pay2bank>
		            <ns1:merchant-id>orderid_201607251919591</ns1:merchant-id>
		            <ns1:amt>1000</ns1:amt>
		            <ns1:bank>中国工行</ns1:bank>
		            <ns1:name>李梦丽</ns1:name>
		            <ns1:bank-card-no>6225380017381023</ns1:bank-card-no>
		            <ns1:branch-bank>南京支行</ns1:branch-bank>
		            <ns1:payee-type>0</ns1:payee-type>
		            <ns1:province/>
		            <ns1:city>南京市</ns1:city>
		            <ns1:memo>快钱备注</ns1:memo>
		            <ns1:bank-purpose>银行交易备注</ns1:bank-purpose>
		            <ns1:bank-memo>交易备注</ns1:bank-memo>
		            <ns1:payee-note>通知内容</ns1:payee-note>
		            <ns1:payee-mobile>13000000000</ns1:payee-mobile>
		            <ns1:payee-email>123@126.com</ns1:payee-email>
		            <ns1:period/>
		            <ns1:merchant-memo1>order1</ns1:merchant-memo1>
		            <ns1:merchant-memo2>order2</ns1:merchant-memo2>
		            <ns1:merchant-memo3>order3</ns1:merchant-memo3>
		          </tns:pay2bank>
		        </tns:pay2bank-result>
		      </tns:pay2bank-list>
		    </tns:batchList>
		  </tns:response-body>
		</tns:batchid-query-response>
		查询到的付款成功笔数为：2*/
	}

	public static void query2() {
		String version = "1.0.1";
		String serviceType = "fo.batch.settlement.complexquery";
//		String serviceType = "fo.batch.settlement.batchidquery";
		String memberCode = "10012138842";
		String featureCode = "F889";

		String beginApplyDate = "20160125000000";
		String endApplyDate = "20160125235959";
		String pageSize = "100";
		String page="1";

		DealInfoEntity dealInfo = new DealInfoEntity();
		dealInfo.setBeginApplyDate(beginApplyDate);
		dealInfo.setPage(page);
		dealInfo.setEndApplyDate(endApplyDate);
		dealInfo.setPageSize(pageSize);
		dealInfo.setServiceType(serviceType);
		dealInfo.setVersion(version);
		dealInfo.setFeatureCode(featureCode);
		dealInfo.setMemberCode(memberCode);
		//dealInfo.setMerchantId(merchantId);

		CustomerTool ct = new CustomerTool();
		SettlementPkiApiResponse response = ct.apply_ws(dealInfo);
		ComplexQueryResponse cqr = (ComplexQueryResponse) ct.unseal(response,
				dealInfo);//得到解密数据
		System.out.println("查询到的笔数为：" + cqr.getResponseBody().getTotalCnt());
	}

	//org.springframework.orm.hibernate3.HibernateSystemException: a different object	with the same identifier value was already associated with the session
	
	public static void main(String args[]) {
		String batchNo = "batchNo_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//		System.out.println(batchNo);//batchNo_20110713172538
//	pay(batchNo);
			query1("batchNo_20160817195550"); //batchNo_20150116104047   2015011601
		
//			query2();
	}
}
