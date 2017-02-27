package com.eshop4j.xoss.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @描述：http请求
 *
 * @创建人： zhongling
 *
 * @时间：2015年12月16日下午4:20:53
 *
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class HttpClientUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	public static String httpPost(String url, JSONObject jsonParam) throws Exception {
        // 创建默认的httpClient实例. 
		CloseableHttpClient httpclient = null;
		if(url.contains("https")){
			httpclient = SSLClient.createSSLClientDefault();
		}else{
			httpclient = HttpClients.createDefault();
		}
        // 创建httppost    
        HttpPost httppost = new HttpPost(url);
        
        try {
        	StringEntity entity1 = new StringEntity(jsonParam.toString(),"UTF-8");   
            entity1.setContentEncoding("UTF-8");
            entity1.setContentType("application/json");
            httppost.setEntity(entity1);
        	CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                InputStream  in = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while ((str = reader.readLine()) != null) {
					stringBuffer.append(str);
				}
				in.close();
				return stringBuffer.toString();
            } finally {  
                response.close();  
            }  
        } finally {  
            // 关闭连接,释放资源    
        	httpclient.close();
        }  
    }
	
	public static String httpPost(String url, JSON jsonParam, String token) throws Exception {
        // 创建默认的httpClient实例. 
		CloseableHttpClient httpclient = null;
		if(url.contains("https")){
			httpclient = SSLClient.createSSLClientDefault();
		}else{
			httpclient = HttpClients.createDefault();
		}
        // 创建httppost    
        HttpPost httppost = new HttpPost(url);
        
        try {
        	StringEntity entity1 = new StringEntity(jsonParam.toString(),"utf-8");   
            entity1.setContentEncoding("UTF-8");
            entity1.setContentType("application/json");
            httppost.setEntity(entity1);
            if(null != token && !"".equals(token)) {
            	//token
            	httppost.addHeader("Authorization", token);
            }
        	CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                InputStream  in = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while ((str = reader.readLine()) != null) {
					stringBuffer.append(str);
				}
				in.close();
				return stringBuffer.toString();
            } finally {  
                response.close();  
            }  
        } finally {  
            // 关闭连接,释放资源    
        	httpclient.close();
        }  
    }
	
	public static String httpPut(String url, JSON jsonParam, String token) throws Exception {
        // 创建默认的httpClient实例. 
		CloseableHttpClient httpclient = null;
		if(url.contains("https")){
			httpclient = SSLClient.createSSLClientDefault();
		}else{
			httpclient = HttpClients.createDefault();
		}
        // 创建httppost    
        HttpPut httppost = new HttpPut(url);
        
        try {
        	StringEntity entity1 = new StringEntity(jsonParam.toString(),"utf-8");   
            entity1.setContentEncoding("UTF-8");
            entity1.setContentType("application/json");
            httppost.setEntity(entity1);
            if(null != token && !"".equals(token)) {
            	//token
            	httppost.addHeader("Authorization", token);
            }
        	CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                InputStream  in = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while ((str = reader.readLine()) != null) {
					stringBuffer.append(str);
				}
				in.close();
				return stringBuffer.toString();
            } finally {  
                response.close();  
            }  
        } finally {  
            // 关闭连接,释放资源    
        	httpclient.close();
        }  
    }
	
	public static String httpDelete(String url, String token) throws Exception {
        // 创建默认的httpClient实例. 
		CloseableHttpClient httpclient = null;
		if(url.contains("https")){
			httpclient = SSLClient.createSSLClientDefault();
		}else{
			httpclient = HttpClients.createDefault();
		}
        // 创建httppost    
        HttpDelete httpDelete = new HttpDelete(url);
        
        try {
            if(null != token && !"".equals(token)) {
            	//token
            	httpDelete.addHeader("Authorization", token);
            }
        	CloseableHttpResponse response = httpclient.execute(httpDelete);  
            try {  
                HttpEntity entity = response.getEntity();
                InputStream  in = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while ((str = reader.readLine()) != null) {
					stringBuffer.append(str);
				}
				in.close();
				return stringBuffer.toString();
            } finally {  
                response.close();  
            }  
        } finally {  
            // 关闭连接,释放资源    
        	httpclient.close();
        }  
    }
	
	public static String httpPost(String url,Map<String,String> params) throws Exception {
        // 创建默认的httpClient实例. 
		CloseableHttpClient httpclient = null;
		if(url.contains("https")){
			httpclient = SSLClient.createSSLClientDefault();
		}else{
			httpclient = HttpClients.createDefault();
		}
        // 创建httppost    
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列    
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for(String key:params.keySet()){
        	formparams.add(new BasicNameValuePair(key,params.get(key)));  
        } 
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "utf-8");  
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {  
                HttpEntity entity = response.getEntity();  
                InputStream  in = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while ((str = reader.readLine()) != null) {
					//str = new String(str.getBytes(),"utf-8");
					stringBuffer.append(str);
				}
				in.close();
				return stringBuffer.toString();
            } finally {  
                response.close();  
            }  
        } finally {  
            // 关闭连接,释放资源    
        	httpclient.close();
        }  
    }  
	/**
	 * 参数排序固定
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String httpPost(String url,LinkedHashMap<String,String> params) throws Exception {
        // 创建默认的httpClient实例. 
		CloseableHttpClient httpclient = null;
		if(url.contains("https")){
			httpclient = SSLClient.createSSLClientDefault();
		}else{
			httpclient = HttpClients.createDefault();
		}
        // 创建httppost    
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        
        // 创建参数队列    
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for(String key:params.keySet()){
        	formparams.add(new BasicNameValuePair(key,params.get(key)));  
        }  
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "utf-8");  
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {  
                HttpEntity entity = response.getEntity();  
                InputStream  in = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while ((str = reader.readLine()) != null) {
					stringBuffer.append(str);
				}
				in.close();
				return stringBuffer.toString();
            } finally {  
                response.close();  
            }  
        } finally {  
            // 关闭连接,释放资源    
        	httpclient.close();
        }  
    }  
	
	
	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String request(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl = httpUrl + "?" + httpArg;

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
	        connection.setRequestProperty("apikey",  "2212ccb41449198d9f211b0a9caf1387");
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	 public static String get(String url)
	  {
	    RequestConfig config = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(10000).build();

	    CloseableHttpClient httpclient = HttpClients.createDefault();
	    HttpGet httpGet = new HttpGet(url);
	    httpGet.setConfig(config);
	    try {
	      CloseableHttpResponse response = null;
	      try {
	        response = httpclient.execute(httpGet);
	        HttpEntity entity = response.getEntity();
	        InputStream in = entity.getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	        StringBuffer stringBuffer = new StringBuffer();
	        String str = "";
	        while ((str = reader.readLine()) != null) {
	          stringBuffer.append(str);
	        }
	        String str1 = stringBuffer.toString();

	        if (response != null) {
	          try {
	            response.close();
	          } catch (IOException e) {
	            logger.error("close CloseableHttpResponse error", e);
	          }

	        }

	        return str1;
	      }
	      catch (Exception e)
	      {
	        logger.error("access[" + url + "]error!", e);
	      } finally {
	        if (response != null)
	          try {
	            response.close();
	          } catch (IOException e) {
	            logger.error("close CloseableHttpResponse error", e);
	          }
	      }
	    }
	    finally {
	      try {
	        if (httpclient != null)
	          httpclient.close();
	      }
	      catch (IOException e) {
	        logger.error("close httpclient error", e);
	      }
	    }
	    return null;
	  }

	  public static String httpsGet(String url)
	  {
	    CloseableHttpClient httpclient = createSSLClientDefault();
	    HttpGet httpGet = new HttpGet(url);
	    try {
	      CloseableHttpResponse response = null;
	      try {
	        response = httpclient.execute(httpGet);
	        HttpEntity entity = response.getEntity();
	        InputStream in = entity.getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	        StringBuffer stringBuffer = new StringBuffer();
	        String str = "";
	        while ((str = reader.readLine()) != null) {
	          stringBuffer.append(str);
	        }
	        String str1 = stringBuffer.toString();

	        if (response != null) {
	          try {
	            response.close();
	          } catch (IOException e) {
	            logger.error("close CloseableHttpResponse error", e);
	          }

	        }

	        return str1;
	      }
	      catch (Exception e)
	      {
	        logger.error("access[" + url + "]error!", e);
	      } finally {
	        if (response != null)
	          try {
	            response.close();
	          } catch (IOException e) {
	            logger.error("close CloseableHttpResponse error", e);
	          }
	      }
	    }
	    finally {
	      try {
	        if (httpclient != null)
	          httpclient.close();
	      }
	      catch (IOException e) {
	        logger.error("close httpclient error", e);
	      }
	    }
	    return null;
	  }

	  public static CloseableHttpClient createSSLClientDefault()
	  {
	    try {
	      SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
	      {
	        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	          return true;
	        }
	      }).build();

	      SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
	      return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	    } catch (KeyManagementException e) {
	      logger.error("SSL error!", e);
	    } catch (NoSuchAlgorithmException e) {
	      logger.error("SSL error!", e);
	    } catch (KeyStoreException e) {
	      logger.error("SSL error!", e);
	    }
	    return HttpClients.createDefault();
	  }
	  
	  public static void main(String[] args) {
		  Map<String ,String> params = new HashMap<String,String>();
		  params.put("userId", "JC2085");
		  params.put("password", "325150");
		  params.put("pszMobis", "18822898541");
		  params.put("pszMsg", new String("同事您好，感谢您对此次测试的配合。[123456]"));
		  params.put("iMobiCount", "1");
		  params.put("pszSubPort", "*");
		  params.put("MsgId", "0");
		  
		  try {
			System.out.println(HttpClientUtil.httpPost("http://61.145.229.29:9006/MWGate/wmgw.asmx/MongateSendSubmit", params));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
