package com.eshop4j.web.service.impl;

import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.core.datatable.OrderInfo;
import com.eshop4j.core.orm.paging.Page;
import com.eshop4j.web.dao.LogsDao;
import com.eshop4j.web.request.CfpCommonRequest;
import com.eshop4j.web.response.AccountLogResp;
import com.eshop4j.web.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mignet on 2016/6/7.
 *
 * @Author Libin
 * @Date 2016/6/7
 */
@Service("logService")
public class LogServiceImpl implements LogService{

    @Resource
    private LogsDao logsDao;

    /**
     * 分页查询帐户操作日志表
     *
     * @param cfpCommonRequest
     * @return
     */
    @Override
    public DataTableReturn queryAccountOpLogList(CfpCommonRequest cfpCommonRequest) {
        DataTableReturn dataTableReturn = new DataTableReturn();
        Page<AccountLogResp> page = new Page<AccountLogResp>(cfpCommonRequest.getPageIndex(),cfpCommonRequest.getLength());
        List<OrderInfo> orderInfoList = cfpCommonRequest.getOrder();
        Map<String,Object> orderMap = new HashMap<String, Object>();
        if(orderInfoList.size()>0){
            for(OrderInfo orderInfo:orderInfoList){
                orderMap.put(cfpCommonRequest.getColumns().get(orderInfo.getColumn()).getName(),orderInfo.getDir().equals("asc")?"ASC":"DESC");
            }
        }
        List<AccountLogResp> accountLogRespList = logsDao.queryAccountOpLogList(cfpCommonRequest,orderMap,page);


        if(accountLogRespList == null){
            accountLogRespList = new ArrayList<AccountLogResp>();
            dataTableReturn.setRecordsTotal(0);
            dataTableReturn.setRecordsFiltered(0);
        }else{
            dataTableReturn.setRecordsFiltered(page.getTotalCount());
            dataTableReturn.setRecordsTotal(page.getTotalCount());
        }
        dataTableReturn.setDraw(0);
        dataTableReturn.setData(accountLogRespList);
        return dataTableReturn;
    }
}
