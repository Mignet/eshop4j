package com.linkwee.plugin.ssdb;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.linkwee.core.util.MD5Utils;

/**
 * <p>SSDB通用工具类</p>
 * @author Mignet
 * @since 2015-03-20 11:12
 */
//如果项目需要，将其移植到具体的项目，并打开开关
//@Component(value = "ssdbManager")
public class SSDBManager {
	
	private static final  Logger LOGGER = LoggerFactory.getLogger(SSDBManager.class);
	private  String host = "127.0.0.1";
	private  int port = 8888;
	
	private SSDB ssdb = null;

	public SSDBManager(){	
		if(ssdb==null){
			try {
				Properties prop = new Properties();
				prop.load(this.getClass().getResource("/ssdb.properties").openStream());
				host = prop.getProperty("ssdb.host");
				port = Integer.parseInt(prop.getProperty("ssdb.port"));
				ssdb = new SSDB(host, port);
			} catch (IOException e) {
				//ssdb.properties not found
				LOGGER.debug("ssdb.properties not found:",e);
			} catch (Exception e) {
				// ssdb server exception
				LOGGER.debug("ssdb server exception:",e);
			}
		}
	}
	
	public void closeSSDB(){
		if(ssdb!=null){
			ssdb.close();
			ssdb = null;
		}
	}
	/**
	 * 上传文件
	 * @param file
	 * @throws Exception 
	 */
	public String upload(File file) throws Exception {
			//准备上传文件数据
			InputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);  
			byte[] b = new byte[1024];  
			int n;  
			while ((n = fis.read(b)) != -1) {  
				bos.write(b, 0, n);  
			}  
			byte[] buffer = bos.toByteArray();  
			//使用文件的MD5作为key
			String key = MD5Utils.getMd5ByByte(buffer);
			String fileName = file.getName();
			String fileExt =fileName.substring(fileName.lastIndexOf(".")+1);
			ssdb.set(key+"."+fileExt, buffer);
			fis.close();  
			bos.close();  
			return key+"."+fileExt;
	}
	/**
	 * 下载文件
	 * @param key - 文件MD5码+后缀
	 * @param filePath - 下载存储路径
	 * @throws Exception 
	 */
	public  void download(String key,String filePath) throws Exception{
			//下载文件数据
			byte[] b = ssdb.get(key);
			FileOutputStream fos = new FileOutputStream(filePath);
			fos.write(b);
			fos.close();
	}
	
	public static void main(String[] args) {
		//String key = MD5Utils.getMd5ByFile(new File("src/main/java/com/linkwee/plugin/ssdb/ssdb.properties"));
		//LOGGER.debug("key:{}",key);
		SSDBManager ssdb = new SSDBManager();
		ssdb.closeSSDB();
	}
	

}
