package com.linkwee.web.dao;

import com.linkwee.web.model.MenusModel;
import com.linkwee.web.request.CfpCommonRequest;
import com.linkwee.web.response.MenusResp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by Mignet on 2016/6/22.
 *
 * @Author Libin
 * @Date 2016/6/22
 */
public interface MenuDao {

    public List<MenusResp> findMenuList(@Param("query") CfpCommonRequest<MenusModel> cfpCommonRequest, RowBounds rowBounds) throws Exception;

    public List<MenusResp> findMenuList(@Param("query") CfpCommonRequest<MenusModel> cfpCommonRequest) throws Exception;

    public MenusModel findMenuOne(@Param("query") MenusModel menusModel) throws Exception;

    public int deleteMenus(@Param("iids") List<Integer> iids) throws Exception;

    public int updateMenus(@Param("query") MenusModel menusModel) throws Exception;

    public int insertMenu(MenusModel menusModel) throws Exception;
}
