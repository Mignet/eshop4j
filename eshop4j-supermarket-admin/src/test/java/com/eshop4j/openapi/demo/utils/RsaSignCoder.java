package com.eshop4j.openapi.demo.utils;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import com.eshop4j.xoss.crypto.Base64;

public class RsaSignCoder {
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	private static final int KEY_SIZE = 512;
	private static final String PUBLIC_KEY = "RSAPublicKey";
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

		keyPairGenerator.initialize(512);

		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		Map<String, Object> keyMap = new HashMap();
		keyMap.put("RSAPublicKey", publicKey);
		keyMap.put("RSAPrivateKey", privateKey);
		return keyMap;
	}

	public static String sign(String datas, String privates) throws Exception {
		byte[] data = datas.getBytes();
		byte[] privateKey = Base64.decode(privates);

		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

		Signature signature = Signature.getInstance("MD5withRSA");

		signature.initSign(priKey);

		signature.update(data);
		return Base64.encodeToString(signature.sign(), true);
	}

	public static boolean verify(String datas, String publicKeys, String signs) throws Exception {
		byte[] data = datas.getBytes();
		byte[] publicKey = Base64.decode(publicKeys);
		byte[] sign = Base64.decode(signs);

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);

		PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initVerify(pubKey);

		signature.update(data);

		return signature.verify(sign);
	}

	public static String getPrivateKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get("RSAPrivateKey");
		return Base64.encodeToString(key.getEncoded(), true);
	}

	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get("RSAPublicKey");
		return Base64.encodeToString(key.getEncoded(), true);
	}
}
