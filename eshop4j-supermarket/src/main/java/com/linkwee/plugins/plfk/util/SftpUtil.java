package com.linkwee.plugins.plfk.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpUtil {
	
	private Properties sftpInfo=SFTPInfo.getFTPInfo("/sftpInfo.properties");
	private ChannelSftp sftp = null;//sftp连接
	private Session sshSession=null;
	
	/**
	 * 连接sftp服务器
	 * @return ChannelSftp sftp连接
	 */
	public ChannelSftp connect() {
		String username=sftpInfo.getProperty("username");//用户名
		String host=sftpInfo.getProperty("host");//主机
		int port=Integer.parseInt(sftpInfo.getProperty("port"));//端口
		String password=sftpInfo.getProperty("password");//密码
		if(this.sftp==null){
			try {
				JSch jsch = new JSch();
				jsch.getSession(username, host, port);
				this.sshSession = jsch.getSession(username, host, port);//创建session
	
				//下面开始设置session参数
				this.sshSession.setPassword(password);
				Properties sshConfig = new Properties();
				sshConfig.put("StrictHostKeyChecking", "no");
				this.sshSession.setConfig(sshConfig);
				this.sshSession.setTimeout(60000);
				//session连接
				this.sshSession.connect();
				Channel channel = sshSession.openChannel("sftp");
				channel.connect();
				this.sftp = (ChannelSftp) channel;//转换为sftp连接
			} catch (Exception e) {
				System.out.println("连接异常");
				e.printStackTrace();
			}
		}
		return sftp;
	}

	/**
	 * 上传文件
	 * @param uploadFile 要上传的文件
	 * @param filePath 要上传文件的路径
	 * @return boolean 是否上传成功
	 */
	public boolean upload(String uploadFile,String filePath) {
		try {
			this.sftp.cd(sftpInfo.getProperty("uploadPath"));
			File file = new File(uploadFile);
			this.sftp.put(new FileInputStream(file), file.getName());
			return true;
		} catch (SftpException e) {
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 下载文件
	 * @param downloadFile 下载的文件
	 * @param saveFile 存在本地的路径
	 * @param filePath 要下载文件的路径
	 * @return boolean 是否下载成功
	 */
	public boolean download(String downloadFile,String saveFile,String filePath) {
		try {
			this.sftp.cd(sftpInfo.getProperty(filePath));
			File file = new File(saveFile);
			this.sftp.get(downloadFile, new FileOutputStream(file));
			return true;
		} catch (SftpException e) {
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 下载文件
	 * @param downloadFile 下载的文件
	 * @param filePath 要下载文件的路径
	 * @return InputStream 返回一个输入流
	 */
	public InputStream download(String downloadFile,String filePath) {
		try {
//			System.out.println(sftpInfo.getProperty(filePath));
			this.sftp.cd(sftpInfo.getProperty(filePath));
			return sftp.get(downloadFile);
		} catch (SftpException e) {
			e.printStackTrace();
			return null;
		} 
	}

	/**
	 * 删除文件
	 * @param flag 标志位，用来确定下载路径
	 * @param deleteFile 要删除的文件
	 * @return boolean 是否删除成功
	 */
	public boolean delete(String deleteFile,String filePath) {
		try {
			this.sftp.cd(sftpInfo.getProperty(filePath));
			this.sftp.rm(deleteFile);
			return true;
		} catch (SftpException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 列出目录下的全部文件
	 * @param filePath 要遍历文件的路径
	 * @return Vector 一个保存有文件的Vector
	 */
	@SuppressWarnings("unchecked")
	public Vector listFiles(String filePath)
			throws SftpException {
		return sftp.ls(sftpInfo.getProperty(filePath));
	}
	
	/**
	 * 关闭连接
	 * */
	public void disconnect(){
		if(this.sftp != null){
            if(this.sftp.isConnected()){
                this.sftp.disconnect();
                System.out.println("sftp连接关闭");
            }else if(this.sftp.isClosed()){
                System.out.println("连接已经被关闭");
            }
        }
		if(this.sshSession!=null){
			if(this.sshSession.isConnected()){
				this.sshSession.disconnect();
				System.out.println("session连接关闭");
			}
		}
	}
	
//	public static void main(String args[]){
//		try {
//			if(SftpUtil.connect().isConnected())
//				SftpUtil.download("OUTBOUND_10011630790_20100719220834_PC2010071921_T1.PKI", "d:\\OUTBOUND_10011630790_20100719220834_PC2010071921_T1.PKI");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			disconnect();
//		}
//	}
}
