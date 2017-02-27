package com.eshop4j.xoss.util;

import java.util.Random;

public final class RandomTextCreator {

	private static final char[] CHARACTERS = "abcde2345678gfynmnpwx".toCharArray();

	private RandomTextCreator() {
	}

	public static String getText(final int length) {
		final StringBuffer text = new StringBuffer();
		final Random rand = new Random();
		for (int i = 0; i < length; i++) {
			final int index = rand.nextInt(CHARACTERS.length);
			text.append(CHARACTERS[index]);
		}

		return text.toString();
	}
	
	/**
	 *  手机号码打星
	 * */
	public static String encrypTion(String str) {
		String  copy = "**************************************";
		StringBuilder  restr = new StringBuilder();
		if(str!=null&&str.length()>7){
			int i = str.length()-7;
			restr = restr.append(str.substring(0, 3)).append(copy.substring(0, i)).append(str.substring(str.length()-4, str.length()));
			return restr.toString();
		}else{
			return str;
		}
	}
}