package com.linkwee.openapi.response;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkwee.xoss.constant.CryptoConstants;
import com.linkwee.xoss.crypto.Cipher3DES;
import com.linkwee.xoss.crypto.RsaSignCoder;

public class CryptoResponse {
	private static final Logger LOGGER = LoggerFactory.getLogger(CryptoResponse.class);
	
	private String signature;
	private String contents;
	
	public CryptoResponse(HttpServletRequest request,String data) throws Exception {
//		String privateKey = "l4mdofLTvHkyONpdlyXBiaTv";//平台提供的加密秘钥
//		String vector = "12345678"; //用户自己生成的 随机8位数字或者字母
		String privateKey =request.getAttribute("privateKey").toString();
		String vector = request.getParameter("vector");
		//2、加密请求报文
		String encrData = Cipher3DES.encrypt(data, privateKey, vector);
		LOGGER.info("加密报文："+encrData);
		
		//3、对请求报文进行签名
		String signature=RsaSignCoder.sign(encrData, CryptoConstants.signPrivateKey ); 
		LOGGER.info("签名值："+signature);
		
		this.signature = signature;
		this.contents = encrData;
	}
	
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
