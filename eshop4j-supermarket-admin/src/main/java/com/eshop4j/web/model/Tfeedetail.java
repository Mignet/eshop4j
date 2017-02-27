package com.eshop4j.web.model;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshop4j.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2016年04月06日 20:23:46
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class Tfeedetail extends BaseEntity {
	
	private static final long serialVersionUID = -7894675919914682180L;
	
    /**
     *
     */
	private Integer fid;
	
    /**
     *
     */
	private Integer frequestid;
	
    /**
     *
     */
	private String fbillnumber;
	
    /**
     *
     */
	private Date fbizdate;
	
    /**
     *
     */
	private String fcustomerid;
	
    /**
     *
     */
	private String fcustomernumber;
	
    /**
     *
     */
	private String fbiznumber;
	
    /**
     *
     */
	private String fproductnumber;
	
    /**
     *
     */
	private String fsaleusernumber;
	
    /**
     *
     */
	private String foriginsaleuser;
	
    /**
     *
     */
	private Double fpuramount;
	
    /**
     *
     */
	private Double fyearpuramount;
	
    /**
     *
     */
	private String fremark;
	
    /**
     *
     */
	private String fraterulenumber;
	
    /**
     *
     */
	private Double ffeerate;
	
    /**
     *
     */
	private Double ffeeamount;
	
    /**
     *
     */
	private Date frequesttime;
	
    /**
     *
     */
	private Date fcreatetime;
	
    /**
     *
     */
	private Byte fbalancestatus;
	
    /**
     *
     */
	private String fbalancenumber;
	
    /**
     *
     */
	private String ffeetype;
	


	public void setFid(Integer fid){
		this.fid = fid;
	}
	
	public Integer getFid(){
		return fid;
	}
	
	public void setFrequestid(Integer frequestid){
		this.frequestid = frequestid;
	}
	
	public Integer getFrequestid(){
		return frequestid;
	}
	
	public void setFbillnumber(String fbillnumber){
		this.fbillnumber = fbillnumber;
	}
	
	public String getFbillnumber(){
		return fbillnumber;
	}
	
	public void setFbizdate(Date fbizdate){
		this.fbizdate = fbizdate;
	}
	
	public Date getFbizdate(){
		return fbizdate;
	}
	
	public void setFcustomerid(String fcustomerid){
		this.fcustomerid = fcustomerid;
	}
	
	public String getFcustomerid(){
		return fcustomerid;
	}
	
	public void setFcustomernumber(String fcustomernumber){
		this.fcustomernumber = fcustomernumber;
	}
	
	public String getFcustomernumber(){
		return fcustomernumber;
	}
	
	public void setFbiznumber(String fbiznumber){
		this.fbiznumber = fbiznumber;
	}
	
	public String getFbiznumber(){
		return fbiznumber;
	}
	
	public void setFproductnumber(String fproductnumber){
		this.fproductnumber = fproductnumber;
	}
	
	public String getFproductnumber(){
		return fproductnumber;
	}
	
	public void setFsaleusernumber(String fsaleusernumber){
		this.fsaleusernumber = fsaleusernumber;
	}
	
	public String getFsaleusernumber(){
		return fsaleusernumber;
	}
	
	public void setForiginsaleuser(String foriginsaleuser){
		this.foriginsaleuser = foriginsaleuser;
	}
	
	public String getForiginsaleuser(){
		return foriginsaleuser;
	}
	
	public void setFpuramount(Double fpuramount){
		this.fpuramount = fpuramount;
	}
	
	public Double getFpuramount(){
		return fpuramount;
	}
	
	public void setFyearpuramount(Double fyearpuramount){
		this.fyearpuramount = fyearpuramount;
	}
	
	public Double getFyearpuramount(){
		return fyearpuramount;
	}
	
	public void setFremark(String fremark){
		this.fremark = fremark;
	}
	
	public String getFremark(){
		return fremark;
	}
	
	public void setFraterulenumber(String fraterulenumber){
		this.fraterulenumber = fraterulenumber;
	}
	
	public String getFraterulenumber(){
		return fraterulenumber;
	}
	
	public void setFfeerate(Double ffeerate){
		this.ffeerate = ffeerate;
	}
	
	public Double getFfeerate(){
		return ffeerate;
	}
	
	public void setFfeeamount(Double ffeeamount){
		this.ffeeamount = ffeeamount;
	}
	
	public Double getFfeeamount(){
		return ffeeamount;
	}
	
	public void setFrequesttime(Date frequesttime){
		this.frequesttime = frequesttime;
	}
	
	public Date getFrequesttime(){
		return frequesttime;
	}
	
	public void setFcreatetime(Date fcreatetime){
		this.fcreatetime = fcreatetime;
	}
	
	public Date getFcreatetime(){
		return fcreatetime;
	}
	
	public void setFbalancestatus(Byte fbalancestatus){
		this.fbalancestatus = fbalancestatus;
	}
	
	public Byte getFbalancestatus(){
		return fbalancestatus;
	}
	
	public void setFbalancenumber(String fbalancenumber){
		this.fbalancenumber = fbalancenumber;
	}
	
	public String getFbalancenumber(){
		return fbalancenumber;
	}
	
	public void setFfeetype(String ffeetype){
		this.ffeetype = ffeetype;
	}
	
	public String getFfeetype(){
		return ffeetype;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

