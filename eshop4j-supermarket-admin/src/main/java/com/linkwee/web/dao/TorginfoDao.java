package com.linkwee.web.dao;

import com.linkwee.web.model.TorginfoModel;

import java.util.List;

/**
 * Created by Mignet on 2016/5/27.
 *理财师组识机构操作
 * @Author Libin
 * @Date 2016/5/27
 */
public interface TorginfoDao {

    /**
     * 查子级组织机构列表
     * @param torginfoModel
     * @return
     */
  public List<TorginfoModel> findTorgginNodeListByParentId(TorginfoModel torginfoModel);


}
