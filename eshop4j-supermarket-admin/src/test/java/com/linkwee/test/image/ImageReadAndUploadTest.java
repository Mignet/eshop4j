package com.linkwee.test.image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.linkwee.core.util.JsonUtils;


public class ImageReadAndUploadTest {

	private static final String NEW_IMG_SERVER_URL = "http://image.tophlc.com/";
	public static void main(String[] args) {
		readAndUpload( "http://images2015.cnblogs.com/","blog/356994/201511/356994-20151111224027665-337762194.png");
	}
	public static  String readAndUpload(String serverpath,String imgid) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
	     try {
	         // 创建httpget
	         URL url = new URL(serverpath+imgid);
	         URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
	         HttpGet httpget = new HttpGet(uri);
	         System.out.println("executing request " + httpget.getURI());
	         // 执行get请求.  
	         response = httpclient.execute(httpget);
	         // 获取响应实体  
	         HttpEntity entity = response.getEntity();
	         System.out.println("--------------------------------------");
	         // 打印响应状态  
	         System.out.println(response.getStatusLine());
	         if (entity != null) {
	             long len = entity.getContentLength();
	             // 打印响应内容长度  
	             System.out.println("image content length: " + len);
	             System.out.println("------------------------------------");
	             //准备上传图片数据
	             byte[] buffer = null;  InputStream fis=null;
	                 fis = entity.getContent();  
	                     ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);  
	                     byte[] b = new byte[1024];  
	                     int n;  
	                     while ((n = fis.read(b)) != -1) {  
	                         bos.write(b, 0, n);  
	                     }  
	                     fis.close();  
	                     bos.close();  
	                     buffer = bos.toByteArray();  
	                 EntityUtils.consume(entity);
	                 
	                 //开始上传
	                 HttpPost httppost = new HttpPost(NEW_IMG_SERVER_URL);
	                 
	                 ByteArrayEntity byteArrayEntity = new ByteArrayEntity(buffer);
	                 httppost.setEntity(byteArrayEntity);
	                 httppost.addHeader("Content-Type", "jpeg");
	                 
	                 System.out.println("executing request " + httppost.getRequestLine());
	                 Header[] headers =  httppost.getAllHeaders();
	                 response = httpclient.execute(httppost);
	                 
	                 System.out.println("----------------Response-----------------------");
	                 System.out.println(response.getStatusLine());
	                 HttpEntity resEntity = response.getEntity();
	                 if (resEntity != null) {
	                     String resp = EntityUtils.toString(resEntity);
	                     System.out.println("Response content length: " + resEntity.getContentLength());
	                     System.out.println("Response content : " + resp);
	                     ImgInfo o = JsonUtils.fromJsonToObject(resp, ImgInfo.class);
	                     if(o==null || o.getInfo()==null){
	                         return null;
	                     }
	                     return  o.getInfo().get(0).getMd5();
	                 }
	                 
	                 EntityUtils.consume(resEntity);
	         }else{
	             return null;
	         }
	         
	     } catch (ClientProtocolException e) {
	        e.printStackTrace();
	        return null;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	         try {
	             if(response!=null){
	                 response.close();
	                 response = null;
	             }
	            httpclient.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	     }
	    return null;
    }
    
}
