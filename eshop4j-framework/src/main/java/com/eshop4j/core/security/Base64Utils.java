package com.eshop4j.core.security;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class Base64Utils {
	
	/**
	 * 解密-Utf8
	 * 
	 * @param str
	 * @return Base64解密str的结果
	 */
    public static String decodeStr(String str)
    {
        byte[] debytes = Base64.decodeBase64(StringUtils.getBytesUtf8(str));
        return  StringUtils.newStringUtf8(debytes);
    }

   /**
    * 加密-Utf8
    * 
    * @param str
    * @return Base64加密str的结果
    */
    public static String encodeStr(String str)
    {
        byte[] enbytes = Base64.encodeBase64(StringUtils.getBytesUtf8(str));
        return  StringUtils.newStringUtf8(enbytes);
    }
    
  /*  public static void main(String[] args) {
		String test = "d666d904ce5c20540cd696bf9ca8e5d0";
		System.out.println("encode:"+Base64Utils.encodeStr(test));
		System.out.println("decode:"+Base64Utils.decodeStr(Base64Utils.encodeStr(test)));
	}*/
}
