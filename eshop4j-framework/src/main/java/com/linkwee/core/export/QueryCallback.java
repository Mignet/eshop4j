package com.linkwee.core.export;

import java.util.List;


public interface QueryCallback<T> {
	/**
	 * 查询回调
	 * @param service
	 * @param t
	 * @return
	 */
	List<?> query(Object service,T t,int page,int limit);

}
