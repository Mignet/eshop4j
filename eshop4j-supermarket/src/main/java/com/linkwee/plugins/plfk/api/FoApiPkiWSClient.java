package com.linkwee.plugins.plfk.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bill99.schema.fo.settlement.SettlementPkiApiRequest;
import com.bill99.schema.fo.settlement.SettlementPkiApiResponse;
import com.linkwee.plugins.plfk.util.CustomerUtil;
import com.linkwee.plugins.plfk.util.StringUtils;


/**
 * webservices 请求应答码
 * */
public class FoApiPkiWSClient {
//	private static final String URL = "https://sandbox.99bill.com/fo-batch-settlement/services";
	private static final String URL = "https://www.99bill.com/fo-batch-settlement/services";
//	public static final String URL = "http://211.148.7.244/fo-batch-settlement/services";
	private static String responseXML="";//返回的xml
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerTool.class);
	
	/**
	 * 用于把请求信息发送给快钱的webservices服务，同时拿到对应的应答信息
	 * */
	public static SettlementPkiApiResponse doit(SettlementPkiApiRequest request) {
		SettlementPkiApiResponse response = null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		try {
			// 创建URL
			URL urlString = new URL(FoApiPkiWSClient.URL);
			URLConnection urlConn = urlString.openConnection();
			urlConn.setRequestProperty("content-type","text/xml;charset=utf-8");
			urlConn.setDoOutput(true);
			urlConn.setReadTimeout(1200000);
//			urlConn.setConnectTimeout(1200000);
			PrintWriter out = new PrintWriter(urlConn.getOutputStream());
			String postContent = StringUtils.ReqFormat(CustomerUtil.settlementPkiApiRequestToXml(request));
			if (postContent == null){
				return null;
			}
			out.print(postContent);
			out.close();
			urlConn.connect();
			
			/*获取服务器端返回信息*/
			isr=new InputStreamReader(urlConn.getInputStream());
			StringBuffer sb=new StringBuffer();
			if(isr!=null){
				br = new BufferedReader(isr);
	            String inputLine="";
	            while ((inputLine = br.readLine())!= null){
	                sb.append(inputLine);
	            }
			}
            
            String sbr=new String(sb.toString().getBytes(),"utf-8");
			if (sbr.length() > 0) {
				LOGGER.info("BILL99_doit[sbr.length() > 0]异常{}",sbr);
				responseXML=StringUtils.ResFormat(sbr);
				response=CustomerUtil.xmlToSettlementPkiApiResponse(responseXML);
			}
		} catch (MalformedURLException e) {
			LOGGER.info("BILL99_doit[MalformedURLException]异常{}",e.toString());
		} catch (UnsupportedEncodingException e) {
			LOGGER.info("BILL99_doit[UnsupportedEncodingException]异常{}",e.toString());
		} catch (IOException e) {
			LOGGER.info("BILL99_doit[IOException]异常{}",e.toString());
		}finally{
			try {
				br.close();
				isr.close();
			} catch (IOException e) {
				br=null;
				isr=null;
				e.printStackTrace();
			}
		}
		return response;
	}
	public static String getResponseXML() {
		return responseXML;
	}
	public static void setResponseXML(String responseXML) {
		FoApiPkiWSClient.responseXML = responseXML;
	}
}
