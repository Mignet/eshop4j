package com.linkwee.test.sysprops;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import com.linkwee.api.controller.crm.InvitationController;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.web.model.vo.SingleImgInfo;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.helper.QRCodeUtil;

public class FilePathTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FilePathTest.class);
	
	public static void main(String[] args) throws IOException {
		/*try {
			//使用类路径方式加载文件
			Resource res = new ClassPathResource("icons/invest.jpg");
			String urlpath = res.getURL().getPath();
			String uriPath = res.getURI().getPath();
			String filePath = res.getFile().getPath();
			System.out.println(urlpath+"\n"+uriPath+"\n"+filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			File file = ResourceUtils.getFile("classpath:icons/invest.jpg");
			System.out.println(file.getPath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Resource res = new ClassPathResource("icons/invest.jpg");
		//OutputStream out = new ByteArrayOutputStream();
		/*try {
			FileCopyUtils.copy(res.getInputStream(), out);
			FileCopyUtils.copy(res.getFile(), new File(""));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(1024);
		try {
			QRCodeUtil.createQR(outStream,res.getFile().getPath(),"www.baidu.com");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//准备上传图片数据
        byte[] buffer = null;        
        buffer = outStream.toByteArray(); 
        //开始上传
        HttpPost httppost = new HttpPost("http://preimage.tophlc.com/");
        
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(buffer);
        httppost.setEntity(byteArrayEntity);
        httppost.addHeader("Content-Type", "jpeg");
        
        LOGGER.info("executing request " + httppost.getRequestLine());
        //Header[] headers =  httppost.getAllHeaders();
        response = httpclient.execute(httppost);
        
        LOGGER.info("----------------Response-----------------------");
        LOGGER.info(response.getStatusLine().toString());
        HttpEntity resEntity = response.getEntity();
        if (resEntity != null) {
            String resp = EntityUtils.toString(resEntity);
            LOGGER.debug("Response content length: " + resEntity.getContentLength());
            LOGGER.debug("Response content : " + resp);           
            SingleImgInfo o = JsonUtils.fromJsonToObject(resp, SingleImgInfo.class);
            if(o==null || o.getInfo()==null){
                return;
            }
            System.out.println(o.getInfo().getMd5());;
            //根据图片上传返回的结果获取第一个md5值
            /*String md5 = resp.split("\"md5\":\"")[1].split("\",")[0];
            return md5;*/
        }
        EntityUtils.consume(resEntity);
	}
}