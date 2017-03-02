package com.eshop4j.xoss.helper;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.eshop4j.core.util.StringUtils;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 
 * @描述：二维码工具
 *
 * @author Mignet
 * @时间  2015年8月5日上午10:09:35
 *
 */
public class QRCodeUtil {
	/**
	 * 生成二维码到流
	 * @param content 二维码内容
	 * @param imgFormate 图片格式
	 * @param width 宽度
	 * @param height 高度
	 * @param stream 流
	 * @throws QRCodeException 二维码异常
	 *//*
	public static void getRcCode(String content,String imgFormate,int width,int height,OutputStream stream)throws QRCodeException{
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix;
		try {
			bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE, width, height, hints);
			MatrixToImageWriter.writeToStream(bitMatrix, imgFormate, stream);
		} catch (WriterException | IOException e) {
			throw new QRCodeException("生成二维码失败:",e);
		}
	}
	
	*//**
	 * 生成二维码到文件
	 * @param content 文件内容
	 * @param imgFormate 图片格式
	 * @param width 宽度
	 * @param height 高度
	 * @param filePath 文件路径
	 * @throws QRCodeException
	 */
	public static void getRcCode(String content,String logoPath,String imgFormate,int width,int height,String filePath) throws QRCodeException{
		 try {
			MatrixToImageWriterEx.createQR(filePath,imgFormate, logoPath, content, null, width, height);
		} catch (Exception e) {
			throw new QRCodeException("生成二维码失败:",e);
		}
	}
	
	public static void createQR(OutputStream out,String logoPath, String content, int width, int height) throws Exception{
		String  imgType= "jpg";
		BitMatrix matrix = MatrixToImageWriterEx.createQRCode(content, width, height);
		if(StringUtils.isBlank(logoPath)){
			MatrixToImageWriter.writeToStream(matrix,imgType,out, new MatrixToImageConfig());
		}else{
			addLogo(out, logoPath, imgType, matrix);
		}
	}
	
	public static void createQR(OutputStream out, String content, int width, int height) throws Exception{
		createQR(out,null,content,width,height);
	}
	
	public static void createQR(OutputStream out, String content) throws Exception{
		createQR(out,null,content,240,240);
	}
	
	public static void createQR(OutputStream out,String logoPath, String content) throws Exception{
		createQR(out,logoPath,content,600,600);
	}
	
	public static void main(String[] args) throws Exception {
		for(int i=0;i<100;i++){
			String fileName = UUID.randomUUID().toString()+".jpg";
			createQR(new FileOutputStream(new File("D:/share/2015-11-16/"+fileName)),"http://www.baidu.com");
		}
	}
	
	private static void addLogo(OutputStream out, String logoPath,
			String imgType, BitMatrix matrix) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		MatrixToLogoImageConfig logoConfig = new MatrixToLogoImageConfig(Color.white, 4);
		BufferedImage logo = ImageIO.read(new File(logoPath));
		Graphics2D g = image.createGraphics();
		//考虑到logo照片贴到二维码中，建议大小不要超过二维码的1/5;
		int width = image.getWidth() / logoConfig.getLogoPart();
		int height = image.getHeight() / logoConfig.getLogoPart();
		//logo起始位置，此目的是为logo居中显示
		int x = (image.getWidth() - width) / 2;
		int y = (image.getHeight() - height) / 2;
		//绘制图
		g.drawImage(logo, x, y, width, height, null);
		
		//给logo画边框
		//构造一个具有指定线条宽度以及 cap 和 join 风格的默认值的实心 BasicStroke
		g.setStroke(new BasicStroke(logoConfig.getBorder()));
		g.setColor(logoConfig.getBorderColor());
		g.drawRect(x, y, width, height);
		g.dispose();
		ImageIO.write(image, imgType,out);
	}
	
	
	private static BufferedImage toBufferedImage(BitMatrix matrix){  
        int width = matrix.getWidth();  
        int height = matrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        for(int x=0;x<width;x++){  
            for(int y=0;y<height;y++){  
                image.setRGB(x, y, matrix.get(x, y) ? Color.black.getRGB() : Color.white.getRGB());  
            }  
        }  
        return image;     
    } 
	
	
}