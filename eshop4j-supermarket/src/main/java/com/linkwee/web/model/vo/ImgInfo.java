package com.linkwee.web.model.vo;

import java.util.List;
/**
 * 图片服务器返回值json
 * @author Mignet
 *
 */
public class ImgInfo {

	public class Info {
		String md5;
		int size;
		double width;
		double height;
		double quality;
		String format;
		
		public String getMd5() {
			return md5;
		}
		public void setMd5(String md5) {
			this.md5 = md5;
		}
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
		public double getWidth() {
			return width;
		}
		public void setWidth(double width) {
			this.width = width;
		}
		public double getHeight() {
			return height;
		}
		public void setHeight(double height) {
			this.height = height;
		}
		public double getQuality() {
			return quality;
		}
		public void setQuality(double quality) {
			this.quality = quality;
		}
		public String getFormat() {
			return format;
		}
		public void setFormat(String format) {
			this.format = format;
		}
		
	}
	public class Error {
		int code;
		String message;
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}

	boolean ret;
	List<Info> info;
	List<Error> error;
	public boolean isRet() {
		return ret;
	}
	public void setRet(boolean ret) {
		this.ret = ret;
	}
	
	public List<Info> getInfo() {
		return info;
	}
	public void setInfo(List<Info> info) {
		this.info = info;
	}
	public List<Error> getError() {
		return error;
	}
	public void setError(List<Error> error) {
		this.error = error;
	}

}
