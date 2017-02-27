package com.linkwee.xoss.rbac;

/**
 * 权限标识配置类, <br>
 * 与 permission 权限表 中的 permission_sign 字段 相对应 <br>
 * 使用:
 * 
 * <pre>
 * &#064;RequiresPermissions(value = PermissionSign.USER_CREATE)
 * public String create() {
 *     return &quot;拥有user:create权限,能访问&quot;;
 * }
 * </pre>
 * 
 * @author Mignet
 * @since 2014年6月17日 下午3:58:51
 **/
public class PermissionSign {

	/**
	* 理财师信息概览
	*/
	public static final String CFP_DATAVIEW_READ = "cfp-dataview:read";
	/**
	* 理财师管理页面
	*/
	public static final String CFP_LIST_READ = "cfp-list:read";
	/**
	* 理财师详情
	*/
	public static final String CFP_DETAIL_READ = "cfp-detail:read";
	/**
	* 销售与管理收益
	*/
	public static final String CFP_SALE_READ = "cfp-sale:read";
	/**
	* 修改密码
	*/
	public static final String CFP_PWD_MODIFY = "cfp-pwd:modify";
	/**
	* 解绑银行卡
	*/
	public static final String CFP_BANKCARD_UNBIND = "cfp-bankcard:unbind";
	/**
	* 修改上级
	*/
	public static final String CFP_PARENT_MODIFY = "cfp-parent:modify";
	/**
	* 修改机构
	*/
	public static final String CFP_GROUP_MODIFY = "cfp-group:modify";
	/**
	* 取消理财师资格
	*/
	public static final String CFP_CFP_DELETE = "cfp-cfp:delete";
	/**
	* 禁止理财师登陆
	*/
	public static final String CFP_LOGIN_MODIFY = "cfp-login:modify";
	/**
	* 客户信息概览
	*/
	public static final String INVESTOR_DATEVIEW_READ = "investor-dateview:read";
	/**
	* 客户管理页面
	*/
	public static final String INVESTOR_LIST_READ = "investor-list:read";
	/**
	* 客户详情
	*/
	public static final String INVESTOR_DETAIL_READ = "investor-detail:read";
	/**
	* 客户投资与收益管理
	*/
	public static final String INVESTOR_SALE_READ = "investor-sale:read";
	/**
	* 修改密码
	*/
	public static final String INVESTOR_PWD_MODIFY = "investor-pwd:modify";
	/**
	* 解绑银行卡
	*/
	public static final String INVESTOR_BANKCARD_UNBIND = "investor-bankcard:unbind";
	/**
	* 变更理财师
	*/
	public static final String INVESTOR_PARENT_MODIFY = "investor-parent:modify";
	/**
	* 产品管理
	*/
	public static final String PRODUCT_LIST_READ = "product-list:read";
	/**
	* 销售明细
	*/
	public static final String PRODUCT_SALE_READ = "product-sale:read";
	/**
	* 新增/编辑产品
	*/
	public static final String PRODUCT_SALE_MODIFY = "product-sale:modify";
	/**
	* 发布产品
	*/
	public static final String PRODUCT_SALE_PUBLISH = "product-sale:publish";
	/**
	* 下架产品
	*/
	public static final String PRODUCT_SALE_BACK = "product-sale:back";
	/**
	* 灰度用户管理模块
	*/
	public static final String SYS_GRAYLIST_ALL = "sys-graylist:*";
	/**
	* 用户管理
	*/
	public static final String SYS_USER_CREATE = "sys-user:create";
	/**
	* 角色管理
	*/
	public static final String SYS_ROLE_CREATE = "sys-role:create";
	/**
	* 权限管理
	*/
	public static final String SYS_PERMISSION_ALL = "sys-permission:*";
	/**
	* 红包列表
	*/
	public static final String ACT_RED_LIST_READ = "act-red-list:read";
	/**
	* 红包每日数据
	*/
	public static final String ACT_RED_DATAVIEW_READ = "act-red-dataview:read";
	/**
	* 新增/编辑红包
	*/
	public static final String ACT_RED_MODIFY = "act-red:modify";
	/**
	* 正式发送红包
	*/
	public static final String ACT_RED_PUBLISH = "act-red:publish";
	/**
	* 资讯管理模块
	*/
	public static final String CMS_INFO_ALL = "cms-info:*";
	/**
	* 活动管理模块
	*/
	public static final String ACT_LIST_ALL = "act-list:*";
	/**
	* 公告消息管理模块
	*/
	public static final String CMS_MSG_ALL = "cms-msg:*";
	/**
	* 开屏广告
	*/
	public static final String BANNER_AD_READ = "banner-ad:read";
	/**
	* 首页轮播
	*/
	public static final String BANNER_FLIP_READ = "banner-flip:read";
	/**
	* 头像审核模块
	*/
	public static final String CMS_AVATOR_ALL = "cms-avator:*";


}
