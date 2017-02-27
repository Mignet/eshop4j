package com.eshop4j.web.model.acc;

import java.io.Serializable;
 /**
 * 
 * @描述： 节假日导入数据实体
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年08月08日 19:56:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcHolidayData implements Serializable {
	
	private static final long serialVersionUID = -9148008533272418236L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *日期(年月日)
     */
	private String noWorkDay;
	
    /**
     *标记 休息日对应结果为 1|节假日对应的结果为 2
     */
	private String sign;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setNoWorkDay(String noWorkDay){
		this.noWorkDay = noWorkDay;
	}
	
	public String getNoWorkDay(){
		return noWorkDay;
	}
	
	public void setSign(String sign){
		this.sign = sign;
	}
	
	public String getSign(){
		return sign;
	}
	
}

