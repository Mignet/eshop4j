package com.xiaoniu.mybatis.paginator.processor;

import java.text.DecimalFormat;

import com.xiaoniu.mybatis.paginator.ResultValueProcessor;

public class DefaultDecimalProcessor implements ResultValueProcessor<Object> {
	private  DecimalFormat df = null;
	
	public DefaultDecimalProcessor(){
		this("###,###.##");
	}
	
	public DefaultDecimalProcessor(String formate){
		df = new DecimalFormat(formate);
	}
	
	@Override
	public Object process(Object value) {
		return df.format(value);
	}

}
