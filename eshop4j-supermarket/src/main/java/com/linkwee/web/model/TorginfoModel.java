package com.linkwee.web.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by Mignet on 2016/5/27.
 *Torginfo 理财师组织机构实体
 * @Author Libin
 * @Date 2016/5/27
 */
public class TorginfoModel {

  private String  number  ;
    private String  name  ;
    private String linkman  ;
    private String telephone  ;
    private String mobile  ;
    private String fax  ;
    private String signtime ;
    private String canceltime ;
    private String contractno  ;
    private String  parentid  ;
    private String remark  ;
    private Integer  enable;
    private String ancestor  ;
    private String createtime  ;
    private String updatetime  ;
    private Integer delstatus ;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getSigntime() {
        return signtime;
    }

    public void setSigntime(String signtime) {
        this.signtime = signtime;
    }

    public String getCanceltime() {
        return canceltime;
    }

    public void setCanceltime(String canceltime) {
        this.canceltime = canceltime;
    }

    public String getContractno() {
        return contractno;
    }

    public void setContractno(String contractno) {
        this.contractno = contractno;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getAncestor() {
        return ancestor;
    }

    public void setAncestor(String ancestor) {
        this.ancestor = ancestor;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getDelstatus() {
        return delstatus;
    }

    public void setDelstatus(Integer delstatus) {
        this.delstatus = delstatus;
    }
    
    @Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
