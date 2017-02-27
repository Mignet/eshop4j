package com.eshop4j.core.base.api;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eshop4j.core.base.BaseEntity;
import com.eshop4j.core.base.PaginatorSevResp;
import com.eshop4j.core.orm.paging.Page;

/**
 * 
 * @描述：api分页返回数据
 *
 * @author Bob
 * @时间  2015年7月31日上午10:15:22
 *
 */
public class PaginatorResponse<E> extends BaseEntity{
	
	private final static Logger logger = LoggerFactory.getLogger(PaginatorResponse.class);
	private static final long serialVersionUID = 3548744260313101555L;
	/**
	 * 页码
	 */
	private int pageIndex;
	/**
	 * 页码大小
	 */
	private int pageSize;
	/**
	 * 总页数
	 */
	private int pageCount;
	/**
	 * 数据总条数
	 */
	private int totalCount;
	/**
	 * 数据
	 */
	private List<E> datas = new ArrayList<E>();
	
	public PaginatorResponse(){
		
	}
	
	public PaginatorResponse(PaginatorSevResp<E> pageSevResp){
		pageIndex = pageSevResp.getPageIndex();
		pageSize = pageSevResp.getPageSize();
		pageCount = pageSevResp.getPageCount();
		totalCount =  pageSevResp.getTotalCount();
		if(pageSevResp.getDatas()!=null&&pageSevResp.getDatas().size()>0){
			datas = pageSevResp.getDatas();
		}
	}
	
	public PaginatorResponse(PaginatorSevResp<?> pageSevResp,PaginatorDecoration<E> decoration){
		pageIndex = pageSevResp.getPageIndex();
		pageSize = pageSevResp.getPageSize();
		pageCount = pageSevResp.getPageCount();
		totalCount =  pageSevResp.getTotalCount();
		if(pageSevResp.getDatas()!=null&&pageSevResp.getDatas().size()>0){
			for(Object data:pageSevResp.getDatas()){
					datas.add(decoration.doInvoke(data));
			}
		}
	}
	
	public PaginatorResponse(PaginatorSevResp<?> pageSevResp,final Class<E> clzz){
		this(pageSevResp,new PaginatorDecoration<E>() {
			@Override
			public E doInvoke(Object item) {
				try {
					Constructor<E> con = clzz.getConstructor(item.getClass());
					return con.newInstance(item);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(clzz.getName()+"找不到构造函数(params={}):{}",item.getClass(),e);
				}
				return null;
			}
		});
	}

	
	public PaginatorResponse(PaginatorResponse<E> pageResp){
		pageIndex = pageResp.getPageIndex();
		pageSize = pageResp.getPageSize();
		pageCount = pageResp.getPageCount();
		totalCount =  pageResp.getTotalCount();
		if(pageResp.getDatas()!=null&&pageResp.getDatas().size()>0){
			datas = pageResp.getDatas();
		}
	}
	
	public PaginatorResponse(PaginatorResponse<?> pageResp,PaginatorDecoration<E> decoration){
		pageIndex = pageResp.getPageIndex();
		pageSize = pageResp.getPageSize();
		pageCount = pageResp.getPageCount();
		totalCount =  pageResp.getTotalCount();
		if(pageResp.getDatas()!=null&&pageResp.getDatas().size()>0){
			for(Object data:pageResp.getDatas()){
					datas.add(decoration.doInvoke(data));
			}
		}
	}
	
	public PaginatorResponse(PaginatorResponse<?> pageResp,final Class<E> clzz){
		this(pageResp,new PaginatorDecoration<E>() {
			@Override
			public E doInvoke(Object item) {
				try {
					Constructor<E> con = clzz.getConstructor(item.getClass());
					return con.newInstance(item);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(clzz.getName()+"找不到构造函数(params={}):{}",item.getClass(),e);
				}
				return null;
			}
		});
	}

	public void setValuesByPage(Page<E> page){
		pageIndex = page.getPageNo();
		pageSize = page.getPageSize();
		pageCount = page.getTotalPages();
		totalCount =  page.getTotalCount();
	}
	
	public List<E> getDatas() {
		return datas;
	}

	public void setDatas(List<E> datas) {
		this.datas = datas;
	}

	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "PaginatorResponse [pageIndex=" + pageIndex + ", pageSize="
				+ pageSize + ", pageCount=" + pageCount + ", totalCount="
				+ totalCount + ", datas=" + datas + "]";
	}
}
