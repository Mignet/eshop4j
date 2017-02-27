package com.linkwee.openapi.demo;

import java.util.Map;

import com.linkwee.openapi.demo.utils.RsaSignCoder;


public class GenerateKey {

	public static void generateSignKeys(){
		//1、根据平台分配的签名种子生成 签名 密钥对
		try {
			Map<String, Object> keyMap = RsaSignCoder.initKey();
			//获取签名公钥
			String publicSignKey = RsaSignCoder.getPublicKey(keyMap);
			System.out.println("签名公钥："+publicSignKey);
			//获取签名私钥
			String privateSignKey = RsaSignCoder.getPrivateKey(keyMap);
			System.out.println("签名私钥："+privateSignKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		generateSignKeys();
	}
}
