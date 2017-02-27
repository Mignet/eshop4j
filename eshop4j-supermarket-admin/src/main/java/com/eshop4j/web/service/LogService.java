package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.web.request.CfpCommonRequest;

/**
 * Created by Mignet on 2016/6/7.
 *
 * @Author Libin
 * @Date 2016/6/7
 */
public interface LogService {
    /**
     * 分页查询帐户操作日志表
     * @param cfpCommonRequest
     * @return
     */
    public DataTableReturn queryAccountOpLogList(CfpCommonRequest cfpCommonRequest);
}
