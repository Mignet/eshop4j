package com.linkwee.core.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigFileUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFileUtil.class);
	
	public static final String SERVER_HOME = "server.home";
	public static final String CONFIG_HOME = "config.home";
	
	
	public static String getServerHome(){
		return System.getProperty(SERVER_HOME);
	}
	
	public static void initConfigHome(String defaultConfigHome){
		System.setProperty(CONFIG_HOME,defaultConfigHome);
	}
	
	/**
	 * 获取服务根目录
	 * @return
	 */
	public static String findFile(String fileName){
		LOGGER.info("CONFIG_HOME:{}",CONFIG_HOME);
		String configHome =  System.getProperty(CONFIG_HOME);
		
		String file = findFile(configHome,removePath(fileName));
		if(StringUtils.isNotBlank(file)){
			return file;
		}
		return null;
	}
	/**
	 * 获取文件名-
	 * @param fileName
	 * @return
	 */
	public static String removePath(String fileName) {
		String ss = fileName;
		if(ss.contains("/")){
			ss=fileName.substring(fileName.lastIndexOf("/")+1);
		}else if(ss.contains("\\")){
			ss=fileName.substring(fileName.lastIndexOf("\\")+1);
		}
		return ss;
	}
	
	/**
	 * 根据配置文件夹 、文件名 获取文件目录
	 * @param configDir 配置文件夹
	 * @param fileName 文件名
	 * @return
	 */
	private static String findFile(String configDir,String fileName){
		if(configDir!=null&&!"".equals(configDir)) {
			StringBuilder path = new StringBuilder(configDir).append(File.separator).append(fileName);
			File file = new File(path.toString());
			if(file.exists()){
				return file.getPath();
			}
		}
		return null;
	}
	
	
}
