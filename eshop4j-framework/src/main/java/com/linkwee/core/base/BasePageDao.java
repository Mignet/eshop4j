package com.linkwee.core.base;

import java.util.List;

import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;


/**
 * 
 * @描述：分页基础dao
 *
 * @author Bob
 * @时间  2015年7月29日下午5:28:28
 *
 */
public interface BasePageDao<T> extends BaseDao{

	/**
	 * 批量新增
	 * @param 
	 * @return
	 */
	public Integer addBatch(List<T> list);
	
	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	public Integer updateBatch(List<T> list);
	
	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	public Integer updateBatchWithEmpty(List<T> list);
	
	/**
	 * 批量删除
	 * @param objects
	 * @return
	 */
	public Integer deleteBatch(Object[] objects);
	
	/**
	 * 新增
	 * @param objects
	 * @return
	 */
	public void add(T t);
	
	/**
	 * 更新
	 * @param objects
	 * @return
	 */
	public void update(T t);
	
	/**
	 * 更新
	 * @param objects
	 * @return
	 */
	public void updateWithEmpty(T t);

	/**
	 * 根据主键删除对象
	 * @param objects
	 * @return
	 */
	public void deleteByPrimaryKey(Object id);
	
	/**
	 * 根据主键获取对象
	 * @param objects
	 * @return
	 */
	public T getByPrimaryKey(Object id);

	/**
	 * 分页
	 * @param objects
	 * @return
	 */
	public PageList<T> list(PageRequest request);
	
	/**
	 * 条件查询
	 * @param objects
	 * @return
	 */
	public List<T> list(T t);
	
	/**
	 * 条件查询
	 * @param objects
	 * @return
	 */
	public T query(T t);

}
