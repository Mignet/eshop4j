package com.linkwee.xoss.constant;

import com.eshop4j.core.base.api.BaseResponse;

public class InnerResponseConstant {
	
	/**
	 * 请求参数app_key不能为空
	 */
	public static final BaseResponse ORGNUMBER_NOTNULL = new BaseResponse("ORGNUMBER_NOTNULL","请求参数orgNumber不能为空");
	/**
	 * 无效的app_key
	 */
	public static final BaseResponse ORGNUMBER_INVALID = new BaseResponse("ORGNUMBER_INVALID","无效的orgNumber");
	/**
	 * 该app_key对应的密钥配置不正确，请检查请求参数app_key是否正确
	 */
	public static final BaseResponse ORG_SECRET_INVALID = new BaseResponse("ORG_SECRET_INVALID","机构对应的密钥配置不正确，请【领会科技】配合检查请求参数是否正确或者是否配置该密钥");
	/**
	 * 密钥查询为空或秘钥处于关闭状态，请检查配置该密钥是否正确
	 */
	public static final BaseResponse ORG_SECRET_CLOSE_INVALID = new BaseResponse("ORG_SECRET_CLOSE_INVALID","密钥查询为空或秘钥处于关闭状态，请【领会科技】配合检查配置该密钥是否正确");
	/**
	 * 请求参数app_version不能为空
	 */
	public static final BaseResponse APPKIND_NOTNULL = new BaseResponse("APPKIND_NOTNULL","请求参数appKind不能为空");
	/**
	 * 请求参数app_version不能为空
	 */
	public static final BaseResponse APPCLIENT_NOTNULL = new BaseResponse("APPCLIENT_NOTNULL","请求参数appClient不能为空");
	/**
	 * 请求参数app_version不能为空
	 */
	public static final BaseResponse APPVERSION_NOTNULL = new BaseResponse("APPVERSION_NOTNULL","请求参数appVersion不能为空");
	/**
	 * 请求参数timestamp不能为空
	 */
	public static final BaseResponse TIMESTAMP_NOTNULL = new BaseResponse("TIMESTAMP_NOTNULL","请求参数timestamp不能为空");
	/**
	 * 请求参数sign不能为空
	 */
	public static final BaseResponse SIGN_NOTNULL = new BaseResponse("SIGN_NOTNULL","请求参数sign不能为空");
	/**
	 * 签名校验失败,请检查所传参数是否有问题
	 */
	public static final BaseResponse SIGN_INVALID = new BaseResponse("SIGN_INVALID","签名校验失败,请检查所传参数是否有问题");
	/**
	 * 请求参数V(api版本号)不能为空
	 */
	public static final BaseResponse V_NOTNULL = new BaseResponse("V_NOTNULL","请求参数V(api版本号)不能为空");
	/**
	 * 请求参数timestamp时间格式不正确
	 */
	public static final BaseResponse TIMESTAMP_FORMATEERROR = new BaseResponse("TIMESTAMP_FORMATEERROR","请求参数timestamp时间格式不正确");
	/**
	 * token校验失败，token不能为空
	 */
	public static final BaseResponse TOKEN_NOTNULL = new BaseResponse("TOKEN_NOTNULL","token校验失败，token不能为空");
	/**
	 * token校验失败，用户未登陆
	 */
	public static final BaseResponse TOKEN_INVALID_NOTLOGIN = new BaseResponse("TOKEN_INVALID_NOTLOGIN","token校验失败，用户未登陆");
	/**
	 * token校验过程中异常，请检查该token
	 */
	public static final BaseResponse TOKEN_INVALID_EXCEPTION = new BaseResponse("TOKEN_INVALID_EXCEPTION","token校验过程中异常，请检查该token");
	/**
	 * token校验失败，请检查该token是否有效
	 */
	public static final BaseResponse TOKEN_INVALID_FAIL = new BaseResponse("TOKEN_INVALID_FAIL","token校验失败，请检查该token是否有效");
	/**
	 * token校验失败，token超时
	 */
	public static final BaseResponse TOKEN_INVALID_TIMEOUT = new BaseResponse("TOKEN_INVALID_TIMEOUT","token校验失败，token超时");
	/**
	 * token校验失败，解析token获得参数不正确
	 */
	public static final BaseResponse TOKEN_INVALID_PARAMERRO = new BaseResponse("TOKEN_INVALID_PARAMERRO","token校验失败，解析token获得参数不正确");
	/**
	 * token校验成功
	 */
	public static final BaseResponse TOKEN_VALID_SUCCESS = new BaseResponse("TOKEN_VALID_SUCCESS","token校验成功");
	/**
	 * 机构编码不能为空
	 */
	public static final BaseResponse OPEN_ORGNUMBER_NOTNULL = new BaseResponse("OPEN_ORGNUMBER_NOTNULL","机构编码不能为空");
	/**
	 * 机构公钥不能为空
	 */
	public static final BaseResponse OPEN_ORGKEY_NOTNULL = new BaseResponse("OPEN_ORGKEY_NOTNULL","机构公钥不能为空");
}
