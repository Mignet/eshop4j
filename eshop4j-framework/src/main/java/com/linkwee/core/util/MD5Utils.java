package com.linkwee.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
	
	/**
	 * 计算byte[]的MD5
	 * @param byteBuffer
	 * @return
	 */
	public static String getMd5ByByte(byte[] byteBuffer) {
		BigInteger bi = new BigInteger(1, DigestUtils.md5(byteBuffer));
		return bi.toString(16); 
	}
	
	public static String md5(String str, String charset) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes(charset));
			byte[] b = md.digest();

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				int i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return str;
	}
	
	/**
	 * 计算文件的MD5
	 * @param byteBuffer
	 * @return
	 */
	public static String getMd5ByFile(File file) {
		String value = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

}
