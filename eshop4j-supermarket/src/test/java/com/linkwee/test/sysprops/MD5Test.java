package com.linkwee.test.sysprops;

import com.linkwee.xoss.util.MD5;

public class MD5Test {

	public static void main(String[] args) {
		String md5 = MD5.crypt("81443174b70145e9a97daa1f45fb58b7mobile15036248519orgKey1AFBD1D079464D4892E06F091FA8A578orgNumberOPEN_LUJINSUO_WEBpassword123564timestamp2015-09-21 16:08:0681443174b70145e9a97daa1f45fb58b7-09-21 16:08:0681443174b70145e9a97daa1f45fb58b7").toUpperCase();
		System.out.println(md5);
	}

}
