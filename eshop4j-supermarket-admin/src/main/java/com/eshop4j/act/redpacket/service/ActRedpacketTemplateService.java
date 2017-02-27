package com.eshop4j.act.redpacket.service;
import com.eshop4j.act.redpacket.model.ActRedpacketTemplate;
import com.eshop4j.core.datatable.DataTable;
import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.request.act.RedPacketTemplateInfoRequest;
 /**
 * 
 * @描述： ActRedpacketTemplateService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年12月22日 20:13:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRedpacketTemplateService extends GenericService<ActRedpacketTemplate,Long>{


	/**
	 * 获取红包模板列表
	 * @param page
	 * @return
	 */
	DataTableReturn getRedpacketTemplateList(DataTable dt);
	
	void insetRedpacketTemplate(RedPacketTemplateInfoRequest infoRequest)throws Exception;
	
	RedPacketTemplateInfoRequest getRedPacketTemplateInfo(String redPacketTemplateId);
	
	void updateRedpacketTemplate(RedPacketTemplateInfoRequest infoRequest)throws Exception;
}
