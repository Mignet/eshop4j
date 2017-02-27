package com.xiaoniu.mybatis.paginator;

public interface ResultValueProcessor<T> {
	
	public abstract Object process(T value);
}
