package com.eshop4j.core.base;

import java.io.Serializable;


/**
 * 
 * 描述：dubbo入参通用
 *
 * @创建人： Bob
 *
 * @时间：2015年11月30日上午10:45:37
 *
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ServiceRequest implements Serializable{

	private static final long serialVersionUID = 2477867453782344066L;
	
	/**
	 * 
	 * 描述：系统来源
	 *
	 * @创建人： Bob
	 *
	 * @时间：2015年11月30日下午2:30:35
	 *
	 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
	 */
	public static enum SysSourceType{
		/**
		 * 控制台系统
		 */
		BACK_MANAGER("backmanager"),
		/**
		 * 钱罐子
		 */
		QGZ("qgz"),
		/**
		 * 其他
		 */
		OTHER("other");
		
		private String sysSource;
		SysSourceType(String sysSource){
			this.sysSource = sysSource;
		}
		public String getSysSource() {
			return sysSource;
		}
	}
	
	/**
	 * 系统来源
	 */
	private String sysSource;

	public String getSysSource() {
		return sysSource;
	}

	public void setSysSource(String sysSource) {
		this.sysSource = sysSource;
	}

	@Override
	public String toString() {
		return "ServiceRequest [sysSource=" + sysSource + "]";
	}
	
}
