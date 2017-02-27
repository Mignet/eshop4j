package com.linkwee.act.redpacket.service;

import com.linkwee.act.redpacket.model.ActRedpacketDetail;
import com.linkwee.act.redpacket.model.ActRedpacketUseRecord;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.vo.InvestRecordWrapper;
 /**
 * 
 * @描述： ActRedpacketUseRecordService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年08月19日 19:49:49
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActRedpacketUseRecordService extends GenericService<ActRedpacketUseRecord,Long>{

	int insertRedpacketUseRecord( String rechargeId,InvestRecordWrapper investRecord,ActRedpacketDetail redpacketDetail);
}
