package com.linkwee.xoss.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MD5 {
	private static final Logger logger = LoggerFactory.getLogger(MD5.class);
	
	private MD5(){}
	
	public static String crypt(String word){
		return crypt(word,32);
	}
	
	public static String cryptByKey(String word,String key){
		return crypt(crypt(word)+key);
	}
	
	public static String crypt(String plainText,int bit) {
		String result = "";
		
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				logger.error("MD5 error: {}", e);
			}
			if(md==null)
				return "";
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			if(bit==16)
				result = buf.toString().substring(8, 24);
			else if(bit==32)
				result = buf.toString();			
		
		return result;
	}

}

