package com.eshop4j.web.response;

import com.eshop4j.web.model.MenusModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mignet on 2016/6/23.
 *
 * @Author Libin
 * @Date 2016/6/23
 */
public class MenuTreeResp  extends MenusModel{

    private List<MenuTreeResp> childList = new ArrayList<MenuTreeResp>();


    public List<MenuTreeResp> getChildList() {
        return childList;
    }

    public void setChildList(List<MenuTreeResp> childList) {
        this.childList = childList;
    }
}
