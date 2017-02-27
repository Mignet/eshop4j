package com.xiaoniu.mybatis.paginator;

import com.xiaoniu.mybatis.paginator.domain.PageRequest;
import com.xiaoniu.mybatis.paginator.domain.PageResponse;

public interface PageSearchHandler {
	public PageResponse getData(PageRequest page);
}
