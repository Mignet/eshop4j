package com.linkwee.core.util;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Application Utils : 工具类，提供大量的便捷方法
 *
 * @author Mignet
 * @since 2014-09-28 22:31
 */
public class ApplicationUtils {

    /**
     * 产生一个36个字符的UUID
     *
     * @return UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }
    
	/**
	 * 产生一个36个字符的UUID 
	 * @param ifTrimLine  是否去掉横线
	 * @param ifToUpperCase 是否变大写
	 * @return
	 */
    public static String randomUUID(boolean ifTrimLine,boolean ifToUpperCase) {
        String uuid = UUID.randomUUID().toString();
        if(ifTrimLine){
        	uuid = uuid.replace("-", "");
        }
        if(ifToUpperCase){
        	uuid = uuid.toUpperCase();
        }
        return uuid;
    }

    /**
     * md5加密
     *
     * @param value 要加密的值
     * @return md5加密后的值
     */
    public static String md5Hex(String value) {
        return DigestUtils.md5Hex(value);
    }

    /**
     * sha1加密
     *
     * @param value 要加密的值
     * @return sha1加密后的值
     */
    public static String sha1Hex(String value) {
        return DigestUtils.sha1Hex(value);
    }

    /**
     * sha256加密
     *
     * @param value 要加密的值
     * @return sha256加密后的值
     */
    public static String sha256Hex(String value) {
        return DigestUtils.sha256Hex(value);
    }

}
