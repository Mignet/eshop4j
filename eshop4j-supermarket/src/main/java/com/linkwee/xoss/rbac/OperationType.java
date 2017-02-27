package com.linkwee.xoss.rbac;

/**
 * 操作类型
 * 
 * @author Mignet
 * @since 2014年6月17日 下午4:03:00
 **/
public class OperationType {
    /**
     * 创建
     */
    public static final String CREATE = "create";
    
    /**
     * 修改
     */
    public static final String MODIFY = "modify";
    
    /**
     * 读取
     */
    public static final String READ = "read";
    
    /**
     * 删除
     */
    public static final String DELETE = "delete";
    
    /**
     * 解绑
     */
    public static final String UNBIND = "unbind";
    
    /**
     * 发布
     */
    public static final String PUBLISH = "publish";

    /**
     * 所有
     */
    public static final String ALL = "*";
}
