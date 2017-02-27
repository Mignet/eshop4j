package com.linkwee.plugin.ssdb;

import java.io.File;

import com.linkwee.plugin.ssdb.SSDBManager;

public class SSDBManagerTest {
	private static String UPLOAD_DIR ="src/upload/"; 
	private static String DOWNLOAD_DIR ="src/vdown/"; 
	
	public static void main(String[] args) {
		try {
			SSDBManager ss= new SSDBManager();
			File file=new File(UPLOAD_DIR+"图片服务架构.vsd");
			//测试上传
			String key = ss.upload(file);
			System.out.println("key:"+key);
			//测试下载
			ss.download(key, DOWNLOAD_DIR+"图片服务架构.vsd");
			//关闭
			ss.closeSSDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
