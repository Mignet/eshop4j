package com.eshop4j.web.service;

import com.eshop4j.core.datatable.DataTableReturn;
import com.eshop4j.web.model.MenusModel;
import com.eshop4j.web.request.CommonRequest;
import com.eshop4j.web.response.MenuTreeResp;
import com.eshop4j.web.response.MenusResp;

import java.util.List;

/**
 * Created by Mignet on 2016/6/22.
 *
 * @Author Libin
 * @Date 2016/6/22
 */
public interface MenuSerivce {

    public DataTableReturn findMenuList(CommonRequest<MenusModel> cfpCommonRequest) throws Exception;

    public List<MenusResp> findMenuAllList(CommonRequest<MenusModel> cfpCommonRequest) throws Exception;

    public MenusModel findMenuOne(MenusModel menusModel) throws Exception;

    public boolean saveMenus(MenusModel menusModel) throws Exception;

    public boolean deleteMenus(List<Integer> iids) throws Exception;

    public List<MenuTreeResp> findMenuTree() throws Exception;
}
