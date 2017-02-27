package com.linkwee.plugins.huafu.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.linkwee.xoss.crypto.Base64;
import com.linkwee.xoss.crypto.CipherBase64;

public class Cipher3DES {
	private static final String Algorithm_DES = "DESede";
	private static final String AlgorithmMode_DES = "/CBC/PKCS5Padding";

	public static String encrypt(String toEncode, String key, String vector) throws Exception {
		Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		DESedeKeySpec dks = new DESedeKeySpec(key.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		SecretKey securekey = keyFactory.generateSecret(dks);
		IvParameterSpec iv = new IvParameterSpec(vector.getBytes(), 0, cipher.getBlockSize());
		cipher.init(1, securekey, iv);
		byte[] encoded = cipher.doFinal(toEncode.getBytes("UTF-8"));
		return CipherBase64.encryptBASE64(encoded);
	}

	public static String decrypt(String toDecode, String key, String vector) throws Exception {
		Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		DESedeKeySpec dks = new DESedeKeySpec(key.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		SecretKey securekey = keyFactory.generateSecret(dks);
		IvParameterSpec iv = new IvParameterSpec(vector.getBytes(), 0, cipher.getBlockSize());
		cipher.init(2, securekey, iv);
		byte[] todecodeBytes = CipherBase64.decryptBASE64(toDecode);
		String decoded = new String(cipher.doFinal(todecodeBytes), "utf-8");
		return decoded;
	}

	public static String generate3DesPrivateKey() throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance("DESede");
		String key = CipherBase64.encryptBASE64(kg.generateKey().getEncoded());
		key = key.substring(0, 24);
		return Base64.encodeToString(key.getBytes(), true);
	}
}
