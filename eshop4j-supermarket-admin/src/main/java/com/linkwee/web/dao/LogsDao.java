package com.linkwee.web.dao;

import com.linkwee.web.request.CfpCommonRequest;
import com.linkwee.web.response.AccountLogResp;
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
