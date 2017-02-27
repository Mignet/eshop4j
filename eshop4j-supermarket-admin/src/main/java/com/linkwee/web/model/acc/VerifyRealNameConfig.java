package com.linkwee.web.model.acc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VerifyRealNameConfig {
	
	
	@Value("${signPrivateKey}")
	private String signPrivateKey;
	
	@Value("${signPublicKey}")
	private String signPublicKey;
	
	@Value("${userCode}")
	private String userCode;

	@Value("${sysCode}")
	private String sysCode;
	
	@Value("${privateKey}")
	private String privateKey;
	
	@Value("${httpUrl}")
	private String httpUrl;
	
	@Value("${juheUrl}")
	private String juheUrl;
	
	@Value("${juheKey}")
	private String juheKey;

	public String getSignPrivateKey() {
		return signPrivateKey;
	}

	public void setSignPrivateKey(String signPrivateKey) {
		this.signPrivateKey = signPrivateKey;
	}

	public String getSignPublicKey() {
		return signPublicKey;
	}

	public void setSignPublicKey(String signPublicKey) {
		this.signPublicKey = signPublicKey;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	public String getJuheUrl() {
		return juheUrl;
	}

	public void setJuheUrl(String juheUrl) {
		this.juheUrl = juheUrl;
	}

	public String getJuheKey() {
		return juheKey;
	}

	public void setJuheKey(String juheKey) {
		this.juheKey = juheKey;
	}
	

}
