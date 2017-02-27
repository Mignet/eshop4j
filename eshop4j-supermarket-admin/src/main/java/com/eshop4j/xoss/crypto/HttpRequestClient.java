package com.eshop4j.xoss.crypto;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class HttpRequestClient {
	
	protected final static Logger LOGGER = LoggerFactory.getLogger(HttpRequestClient.class);
	
	public static String invokeHttps(String url, List<NameValuePair> parameters, String charSet) throws Exception {
		HttpClient client = WebClientDevWrapper.wrapClient(new DefaultHttpClient());
		HttpPost httpPost = new HttpPost(url);
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, charSet);

		formEntity.setContentEncoding(charSet);
		formEntity.getContentEncoding();
		httpPost.setEntity(formEntity);
		LOGGER.info("HTTPS请求地址: " + httpPost.getURI());
		HttpResponse httpResponse = client.execute(httpPost);
		String data = EntityUtils.toString(httpResponse.getEntity(), charSet==null ? "UTF-8" : charSet);
		return data;
	}

	public static String invokePostHttp(String url, List<NameValuePair> parameters, String charSet) throws Exception {
		@SuppressWarnings("resource")
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, charSet);

		formEntity.setContentEncoding(charSet);
		formEntity.getContentEncoding();
		httpPost.setEntity(formEntity);
		LOGGER.info("HTTP POST请求地址: " + httpPost.getURI());
		HttpResponse httpResponse = client.execute(httpPost);
		String data = EntityUtils.toString(httpResponse.getEntity(), charSet==null ? "UTF-8" : charSet);
		return data;
	}
	
	public static String invokeGetHttp(String reqURL,List<NameValuePair> parameters,String charSet) throws Exception {
		@SuppressWarnings("resource")
		HttpClient httpClient = new DefaultHttpClient();
        StringBuilder requestURL = new StringBuilder();
        if (!reqURL.contains("?")) {
        	requestURL.append(reqURL).append("?").append(EntityUtils.toString(new UrlEncodedFormEntity(parameters)));
        } else {
        	requestURL.append(reqURL).append(EntityUtils.toString(new UrlEncodedFormEntity(parameters)));
        }		   
		HttpGet httpGet = new HttpGet(requestURL.toString());
		LOGGER.info("HTTP GET请求地址: " + httpGet.getURI());
		HttpResponse httpResponse = httpClient.execute(httpGet); //执行GET请求
		String data = EntityUtils.toString(httpResponse.getEntity(), charSet==null ? "UTF-8" : charSet);
		return data;
	}
	
	public static String invokeGetHttp(String reqURL,String charSet) throws Exception {
		@SuppressWarnings("resource")
		HttpClient httpClient = new DefaultHttpClient();		   
		HttpGet httpGet = new HttpGet(reqURL);
		LOGGER.info("HTTP GET请求地址: " + httpGet.getURI());
		HttpResponse httpResponse = httpClient.execute(httpGet); //执行GET请求
		String data = EntityUtils.toString(httpResponse.getEntity(), charSet==null ? "UTF-8" : charSet);
		return data;
	}
}
