package com.eshop4j.web.dao;

import com.eshop4j.web.request.CfpCommonRequest;
import com.eshop4j.web.response.AccountLogResp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * Created by Mignet on 2016/6/7.
 *
 * @Author Libin
 * @Date 2016/6/7
 */
public interface LogsDao {

    public List<AccountLogResp> queryAccountOpLogList(@Param("query") CfpCommonRequest cfpCommonRequest, @Param("odd") Map<String,Object> orderMap, RowBounds rowBounds);
}
