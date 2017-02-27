package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.linkwee.act.redpacket.model.ActRedpacketTemplate;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.response.act.RedpacketTemplateListResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年12月22日 20:13:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRedpacketTemplateMapper extends GenericDao<ActRedpacketTemplate,Long>{

	
	List<RedpacketTemplateListResponse> getRedpacketTemplateList(RowBounds page);
}
