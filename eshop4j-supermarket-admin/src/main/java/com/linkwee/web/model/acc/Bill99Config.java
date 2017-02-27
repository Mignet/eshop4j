package com.linkwee.web.model.acc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Bill99Config {
	
	
	@Value("${key}")
	private String key;
	
	@Value("${merchantId}")
	private String merchantId;
	
	@Value("${merchantIp}")
	private String merchantIp;
	
	
	@Value("${version}")
	private String version;

	@Value("${memberCode}")
	private String memberCode;
	
	@Value("${payerAcctCode}")
	private String payerAcctCode;
	
	@Value("${featureCode}")
	private String featureCode;
	
	@Value("${queryServiceType}")
	private String queryServiceType;
	
	@Value("${plfkServiceType}")
	private String plfkServiceType;
	
	@Value("${merchantName}")
	private String merchantName;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantIp() {
		return merchantIp;
	}

	public void setMerchantIp(String merchantIp) {
		this.merchantIp = merchantIp;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getPayerAcctCode() {
		return payerAcctCode;
	}

	public void setPayerAcctCode(String payerAcctCode) {
		this.payerAcctCode = payerAcctCode;
	}

	public String getFeatureCode() {
		return featureCode;
	}

	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}

	public String getQueryServiceType() {
		return queryServiceType;
	}

	public void setQueryServiceType(String queryServiceType) {
		this.queryServiceType = queryServiceType;
	}

	public String getPlfkServiceType() {
		return plfkServiceType;
	}

	public void setPlfkServiceType(String plfkServiceType) {
		this.plfkServiceType = plfkServiceType;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	

}
