package com.linkwee.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.base.SuccessCode;
import com.linkwee.core.constant.AdPositionConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.SmAdvertisementMapper;
import com.linkwee.web.model.news.SmAdvertisement;
import com.linkwee.web.service.AdvertisementService;


 /**
 * 
 * @描述：banner 广告 服务
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年10月17日 15:18:02
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
@Service("advertisementService")
public class AdvertisementServiceImpl implements AdvertisementService{
	

	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SmAdvertisementMapper smAdvertisementMapper;

	@Override
	public List<SmAdvertisement> queryBanner(Integer appType) {
		SmAdvertisement advertisement = new SmAdvertisement();
		advertisement.setPageIndex(AdPositionConstant.APP_HOME_PAGE);
		advertisement.setAppType(appType);
		advertisement.setStatus(0);//0- 显示 | -1不显示
		return smAdvertisementMapper.queryAdvertisement(advertisement);
	}


	@Override
	public SmAdvertisement findAdvDtl(String fid) {
		return smAdvertisementMapper.selectByPrimaryKey(Long.valueOf(fid));
	}

	@Override
	public ReturnCode DeleteAdv(Integer fid) {
		
		 try {
			 smAdvertisementMapper.deleteByPrimaryKey(Long.valueOf(fid));
				return new SuccessCode();
			} catch (Exception e) {
				logger.error("advertisementDao.deleteByPrimaryKey invoke error:"+e.getMessage());
				e.printStackTrace();
				return Error.DB_ERROR;
			}
	}

	@Override
	public ReturnCode SaveAdv(SmAdvertisement adv) {
		 try {
			 smAdvertisementMapper.insertSelective(adv);
			return new SuccessCode();
		} catch (Exception e) {
			logger.error("advertisementDao.add invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

	@Override
	public ReturnCode updateAdv(SmAdvertisement adv) {
		 
		 try {
			 smAdvertisementMapper.updateByPrimaryKeySelective(adv);
				return new SuccessCode();
			} catch (Exception e) {
				logger.error("advertisementDao.update invoke error:"+e.getMessage());
				e.printStackTrace();
				return Error.DB_ERROR;
			}
	}



	@Override
	public DataTableReturn findAdvList(SmAdvertisement pageRequest,DataTable dataTable) throws Exception{
		 Page<SmAdvertisement> page = new Page<SmAdvertisement>(dataTable.getStart() / dataTable.getLength() + 1,dataTable.getLength());
		 List<SmAdvertisement> newsRequestList = smAdvertisementMapper.findAdvList(pageRequest,page);
		 DataTableReturn dataTableReturn =new DataTableReturn();
		 dataTableReturn.setRecordsFiltered(page.getTotalCount());
		 dataTableReturn.setRecordsTotal(page.getTotalCount());
		 dataTableReturn.setData(newsRequestList);
		 return dataTableReturn;
	}

/*	*//**
	 * 分页查询
	 *//*
	public PaginatorSevResp<SmAdvertisement> queryAdvList(PaginatorSevReq pageRequest) {
		PageRequest req = PaginatorUtil.toPageRequest(pageRequest);
		PageList<SmAdvertisement> datas = smAdvertisementMapper.query(req);
		return  PaginatorUtil.toPaginatorSevResp(datas);
	}*/


}