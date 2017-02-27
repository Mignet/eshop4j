package com.linkwee.web.service.impl;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.MenuDao;
import com.linkwee.web.model.MenusModel;
import com.linkwee.web.request.CfpCommonRequest;
import com.linkwee.web.response.MenuTreeResp;
import com.linkwee.web.response.MenusResp;
import com.linkwee.web.service.MenuSerivce;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mignet on 2016/6/22.
 *
 * @Author Libin
 * @Date 2016/6/22
 */
@Service
public class MenuSerivceImpl implements MenuSerivce {

    @Resource
    private MenuDao menuDao;

    @Override
    public DataTableReturn findMenuList(CfpCommonRequest<MenusModel> cfpCommonRequest) throws Exception {

        Page<MenusModel> menusModelPage = new Page<MenusModel>(cfpCommonRequest.getPageIndex(),cfpCommonRequest.getLength());
        List<MenusResp> menusModelList = menuDao.findMenuList(cfpCommonRequest,menusModelPage);
        DataTableReturn dataTableReturn = new DataTableReturn();
        dataTableReturn.setDraw(0);
        if(menusModelPage.getTotalCount()>0){
            dataTableReturn.setRecordsFiltered(menusModelPage.getTotalCount());
            dataTableReturn.setRecordsTotal(menusModelPage.getTotalCount());
            dataTableReturn.setData(menusModelList);
        }
        else {
            dataTableReturn.setRecordsFiltered(0);
            dataTableReturn.setRecordsTotal(0);
            dataTableReturn.setData(new ArrayList<Object>());
        }
        return dataTableReturn;
    }

    @Override
    public List<MenusResp> findMenuAllList(CfpCommonRequest<MenusModel> cfpCommonRequest) throws Exception {
        return menuDao.findMenuList(cfpCommonRequest);
    }

    @Override
    public MenusModel findMenuOne(MenusModel menusModel) throws Exception {
        if(null == menusModel || menusModel.getId() == null){
            return  new MenusModel();
        }
        return menuDao.findMenuOne(menusModel);
    }

    @Override
    public boolean saveMenus(MenusModel menusModel) throws Exception {
        if(null == menusModel){
            return false;
        }
        int result;
        if(menusModel.getId()!=null && menusModel.getId()>0){
            result= menuDao.updateMenus(menusModel);
        }
        else{
            result=menuDao.insertMenu(menusModel);
        }
        return result>0;
    }

    @Override
    public boolean deleteMenus(List<Integer> iids) throws Exception {
        if(null !=iids && !iids.isEmpty()){
            return menuDao.deleteMenus(iids)>0;
        }
        return false;
    }

    @Override
    public List<MenuTreeResp> findMenuTree() throws Exception {
        CfpCommonRequest<MenusModel> cfpCommonRequest = new CfpCommonRequest<MenusModel>();
        cfpCommonRequest.setParams(new MenusModel());
        cfpCommonRequest.getParams().setIsDisable(0);
        List<MenusResp> menusRespList = menuDao.findMenuList(cfpCommonRequest);
        return getChildMenus(0,menusRespList);
    }

    /**
     * 递归菜单
     * @param menusResps
     * @return
     */
    public List<MenuTreeResp> getChildMenus(Integer parentId,List<MenusResp> menusResps){
        List<MenuTreeResp> result = new ArrayList<MenuTreeResp>();
        MenuTreeResp menuTreeResp =null;
        for(MenusResp menusResp : menusResps){
            if(menusResp.getParentId().equals(parentId)){
                menuTreeResp = new MenuTreeResp();
                menuTreeResp.setId(menusResp.getId());
                menuTreeResp.setMenuIcon(menusResp.getMenuIcon());
                menuTreeResp.setParentId(menusResp.getParentId());
                menuTreeResp.setMenuUrl(menusResp.getMenuUrl());
                menuTreeResp.setMenuName(menusResp.getMenuName());
                menuTreeResp.setPermissionSign(menusResp.getPermissionSign());
                menuTreeResp.setChildList(getChildMenus(menuTreeResp.getId(),menusResps));
                result.add(menuTreeResp);
            }
        }
        return result;
    }
}
