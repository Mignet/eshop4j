package com.linkwee.core.base;

import java.io.Serializable;

/**
 * 
 * 描述：dubbo服务返回信息
 *
 * @创建人： Bob
 *
 * @时间：2015年11月30日上午10:45:45
 *
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ServiceResponse<T> implements Serializable{

	private static final long serialVersionUID = -1308723420701237097L;
	
	/**
	 * 消息头
	 */
	private ReturnCode head;
	
	/**
	 * 消息内容
	 */
	private T data;
	/**
	 * 构造函数
	 * @param data 数据
	 * @param head 消息头
	 */
	public ServiceResponse(T data,ReturnCode head){
		this.head = head;
		this.data = data;
	}
	/**
	 * 构造函数(成功)
	 * @param data 数据
	 */
	public ServiceResponse(T data){
		this(data,new SuccessCode());
	}
	/**
	 * 构造函数(失败)
	 * @param errorCode
	 */
	public ServiceResponse(ErrorCode errorCode){
		this(null,errorCode);
	}
	

	public ReturnCode getHead() {
		return head;
	}

	public void setHead(ReturnCode head) {
		this.head = head;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	/**
	 * 是否成功
	 * @return true成功，false失败
	 */
	public boolean isSuccess(){
		if(this.head==null){
			return true;
		}
		return this.head.getCode() == 0;
	}
	/**
	 * 是否错误
	 * @return true 失败，false成功
	 */
	public boolean isError(){
		return isError(null);
	}
	/**
	 * 是什么类型错误
	 * @param error 具体错误
	 * @return
	 */
	public boolean isError(ReturnCode error){
		if(error==null){
			return !isSuccess();
		}
		return this.head.getCode() == error.getCode();
	}
	/**
	 * 获取错误码
	 * @return
	 */
	public int getCode(){
		return this.head.getCode();
	}
	/**
	 * 获取错误信息
	 * @return
	 */
	public String getMessage(){
		return this.head.getMessage();
	}

	@Override
	public String toString() {
		return "ServiceResponse[code=" + this.getCode() + ", message=" + this.getMessage() + ", data=" + data + "]";
	}
	
}
