package com.eshop4j.xoss.api;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.eshop4j.core.util.DateEditor;

/**
 * 
 * 描述：基础控制层
 *
 * @创建人： Bob
 *
 * @时间：2015年12月1日上午11:40:23
 *
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class BaseController {

	protected  final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 日期转换
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
