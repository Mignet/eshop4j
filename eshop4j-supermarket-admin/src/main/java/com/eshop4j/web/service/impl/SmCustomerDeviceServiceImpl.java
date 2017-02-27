package com.eshop4j.web.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericDao;
import com.eshop4j.core.generic.GenericServiceImpl;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.dao.SmCustomerDeviceMapper;
import com.eshop4j.web.model.SmCustomerDevice;
import com.eshop4j.web.service.SmCustomerDeviceService;


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
@Service("smCustomerDeviceService")
public class SmCustomerDeviceServiceImpl extends GenericServiceImpl<SmCustomerDevice, Long> implements SmCustomerDeviceService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmCustomerDeviceServiceImpl.class);
	
	@Resource
	private SmCustomerDeviceMapper smCustomerDeviceMapper;
	
	@Override
    public GenericDao<SmCustomerDevice, Long> getDao() {
        return smCustomerDeviceMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmCustomerDevice -- 排序和模糊查询 ");
		Page<SmCustomerDevice> page = new Page<SmCustomerDevice>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmCustomerDevice> list = this.smCustomerDeviceMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	
	
	/**
	 * 保存用户登录设备信息
	 */
	@Override
	public void doDeviceInfo(SmCustomerDevice customerDevice) {
		int num = smCustomerDeviceMapper.updateByPrimaryKeySelective(customerDevice);
		if (num == 0) {
			smCustomerDeviceMapper.insertSelective(customerDevice);
		}
	}

	/**
	 * 查询用户设备信息
	 */
	@Override
	public SmCustomerDevice queryCustomerDevice(int appType, String userId) {
		SmCustomerDevice customerDevice = new SmCustomerDevice();
		customerDevice.setAppType((byte)appType);
		customerDevice.setUserId(userId);
		return smCustomerDeviceMapper.selectOneByCondition(customerDevice);
	}

	@Override
	public void delete(String userId,int appType) {
		smCustomerDeviceMapper.deleteByUserIdAndAppType(userId, appType);
		
	}

	@Override
	public Map<String, SmCustomerDevice> queryCustomerDevices(int appType,
			Collection<String> userIds) {
	List<SmCustomerDevice> rltList = 	smCustomerDeviceMapper.queryUserDeviceToken(appType, userIds);
	Map<String,SmCustomerDevice> mapRlt = Maps.newHashMap();
	for(int i=0;i<rltList.size();i++){
		
		mapRlt.put(rltList.get(i).getUserId(),rltList.get(i));
		
	}
		return mapRlt;
	}

}
