package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.model.MenusModel;
import com.linkwee.web.request.CfpCommonRequest;
import com.linkwee.web.response.MenuTreeResp;
import com.linkwee.web.response.MenusResp;

import java.util.List;

/**
 * Created by Mignet on 2016/6/22.
 *
 * @Author Libin
 * @Date 2016/6/22
 */
public interface MenuSerivce {

    public DataTableReturn findMenuList(CfpCommonRequest<MenusModel> cfpCommonRequest) throws Exception;

    public List<MenusResp> findMenuAllList(CfpCommonRequest<MenusModel> cfpCommonRequest) throws Exception;

    public MenusModel findMenuOne(MenusModel menusModel) throws Exception;

    public boolean saveMenus(MenusModel menusModel) throws Exception;

    public boolean deleteMenus(List<Integer> iids) throws Exception;

    public List<MenuTreeResp> findMenuTree() throws Exception;
}
