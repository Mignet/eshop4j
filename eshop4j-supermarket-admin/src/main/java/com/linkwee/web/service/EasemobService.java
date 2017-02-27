package com.linkwee.web.service;

/**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface EasemobService {

	/**
	 * 生成环信帐号
	 * @param userId
	 */
	void generateEasemob(String userId);
	
	/**
	 * 异步生成环信帐号
	 * @param userId
	 */
	void generateEasemobThread(String userId);
	
	/**
	 * 生成城市信息
	 * @param userId
	 */
	void generateCityInfo(String userId);
	
	/**
	 * 异步生成城市信息
	 * @param userId
	 */
	void generateCityInfoThread(String userId);
	
}
