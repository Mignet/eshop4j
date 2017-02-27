package com.linkwee.web.service;

import java.util.Collection;
import java.util.Map;

import com.linkwee.api.request.crm.DeviceInfoRequest;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.SmCustomerDevice;
import com.linkwee.web.service.SmCustomerDeviceService;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月14日 15:08:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmCustomerDeviceService extends GenericService<SmCustomerDevice,Long>{

	/**
	 * 查询SmCustomerDevice列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 记录用户设备信息
	 * @param userId 用户编号
	 * @param deviceInfoRequest 设备信息
	 * @return
	 */
	boolean recordCustomerDeviceInfo(String userId,String platform,Integer appType,DeviceInfoRequest deviceInfoRequest);
	
	/**
	 * 查询用户设备信息
	 * @Date 2016年07月14日 15:08:18
	 * @param appType
	 * @param userId
	 * @return
	 */
	SmCustomerDevice queryCustomerDevice(int appType, String userId);
	
	/**
	 * 记录用户设备信息
	 * @Date 2016年07月14日 15:08:18
	 * @param smCustomerDevice
	 */
	void doDeviceInfo(SmCustomerDevice smCustomerDevice);
	
	/**
	 * 删除设备信息
	 * @Date 2016年07月14日 15:08:18
	 * @param smCustomerDevice
	 */
	void delete(String userId,int appType);
	/**
	 * 查询用户设备信息
	 * @Date 2016年07月14日 15:08:18
	 * @param appType
	 * @param userId
	 * @return
	 */
	Map<String,SmCustomerDevice> queryCustomerDevices(int appType, Collection<String> userIds);
}
