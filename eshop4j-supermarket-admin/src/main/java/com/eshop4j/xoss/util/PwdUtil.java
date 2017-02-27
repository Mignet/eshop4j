package com.eshop4j.xoss.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class PwdUtil {
	/**
	 * 登录密码加密
	 * @param decript 源密码
	 * @return
	 */
	public static String SHA1ToBase64(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			return Base64.encodeBase64String(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
