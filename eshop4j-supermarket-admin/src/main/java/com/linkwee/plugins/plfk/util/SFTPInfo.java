package com.linkwee.plugins.plfk.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 加载配置文件
 * */
public class SFTPInfo {
	public static Properties getFTPInfo(String path){
		Properties sftpInfo=new Properties();
		try {
			sftpInfo.load(SftpUtil.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sftpInfo;
	}
}
