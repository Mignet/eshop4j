package com.linkwee.openapi.demo.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import com.linkwee.xoss.crypto.WebClientDevWrapper;

public class HttpRequestClient {
	public static String invokeHttps(String url, List<NameValuePair> parameters, String charSet) throws Exception {
		HttpClient client = WebClientDevWrapper.wrapClient(new org.apache.http.impl.client.DefaultHttpClient());
		HttpPost httpPost = new HttpPost(url);
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, charSet);

		formEntity.setContentEncoding(charSet);
		formEntity.getContentEncoding();
		httpPost.setEntity(formEntity);
		HttpResponse httpResponse = client.execute(httpPost);
		InputStream contentInputStream = httpResponse.getEntity().getContent();
		String data = new String(readAll(contentInputStream));
		return data;
	}

	public static String invokeHttp(String url, List<NameValuePair> parameters, String charSet) throws Exception {
		HttpClient client = new org.apache.http.impl.client.DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, charSet);

		formEntity.setContentEncoding(charSet);
		formEntity.getContentEncoding();
		httpPost.setEntity(formEntity);
		HttpResponse httpResponse = client.execute(httpPost);
		InputStream contentInputStream = httpResponse.getEntity().getContent();
		String data = new String(readAll(contentInputStream));
		return data;
	}

	public static byte[] readAll(InputStream is) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte['Ѐ'];
		int len = -1;
		while ((len = is.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		is.close();
		return outSteam.toByteArray();
	}
}
