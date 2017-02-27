package com.eshop4j.xoss.util;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.eshop4j.core.base.PaginatorSevResp;
import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.core.base.api.ErrorResponse;
import com.eshop4j.core.base.api.PaginatorResponse;
import com.eshop4j.core.base.api.SuccessResponse;
import com.eshop4j.core.util.StringUtils;

/**
 * 
 * @描述：api接口返回处理类
 *
 * @author Bob
 * @时间  2015年8月20日下午1:59:49
 *
 */
public final class ResponseUtil {
	protected static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);
	/**
	 * 无数据返回
	 */
	private static final SuccessResponse<Object> success = new SuccessResponse<Object>();
	/**
	 * 服务器异常错误
	 */
	private static final ErrorResponse errorServ =  new ErrorResponse("140001","服务器异常");

	/**
	 * 参数错误
	 */
	private static final ErrorResponse errorToken =  new ErrorResponse("140003","Token失效");
	
	/**
	 * 参数错误
	 */
	private static final ErrorResponse errorSign =  new ErrorResponse("140004","签名错误");
	/**
	 * 无数据返回
	 * @return
	 */
	public static BaseResponse getSuccessResponse(){
		return success;
	}
	
	/**
	 * 返回成功
	 * @param data 源数据
	 * @return
	 */
	public static <T>BaseResponse getSuccessResponse(T data){
		return getSuccessResponse(data,null);
	}
	
	/**
	 * 返回成功
	 * @param data 源数据
	 * @param clazz 装饰器
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T>BaseResponse getSuccessResponse(Object data,Class<T> clazz){
		if(data==null){
		  return getSuccessResponse();
		}
		if(data instanceof List){
			Map<String,Object> ret = new HashMap<String,Object>();
			if(clazz!=null){
				List<T> list = new LinkedList<T>();
				List<Object> datas = (List<Object>)data;
				if(datas!=null&&datas.size()>0){
					for(Object obj:datas){
						try {
							Constructor<T> con = clazz.getConstructor(obj.getClass());
							T retObj = con.newInstance(obj);
							list.add(retObj);
						} catch (Exception e) {
							logger.error(clazz.getName()+"找不到构造函数:",e);
						}
					}
				}
				ret.put("datas", list);
			}else{
				ret.put("datas", data);
			}
			return new SuccessResponse<Object>(ret);
		}else if(data instanceof String){
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("data", data);
			return new SuccessResponse<Object>(ret);
		}
		else{
			Object ret = null;
			if(clazz!=null){
				try {
					Constructor<T> con = clazz.getConstructor(data.getClass());
					ret = con.newInstance(data);
				} catch (Exception e) {
					logger.error(clazz.getName()+"找不到构造函数:",e);
				}
			}else{
				ret = data;
			}
			return new SuccessResponse<Object>(ret);
		}
	}
	
	/**
	 * 返回分页成功
	 * @param datas 源数据
	 * @param clazz 装饰器
	 * @return
	 */
	public static <T>BaseResponse getSuccessResponse(PaginatorSevResp<T> datas){
		return  getSuccessResponse(new PaginatorResponse<T>(datas));
	}
	
	
	/**
	 * 返回分页成功
	 * @param datas 源数据
	 * @param clazz 装饰器
	 * @return
	 */
	public static <T>BaseResponse getSuccessResponse(PaginatorSevResp<?> datas,Class<T> clazz){
		return  getSuccessResponse(new PaginatorResponse<T>(datas,clazz));
	}
	
	/**
	 * 获取服务器异常错误
	 * @return
	 */
	public static ErrorResponse getErrorServ() {
		return errorServ;
	}
	
	
	/**
	 * 获取token错误
	 * @return
	 */
	public static ErrorResponse getErrorToken() {
		return errorToken;
	}
	/**
	 * 获取签名错误
	 * @return
	 */
	public static ErrorResponse getErrorSign() {
		return errorSign;
	}
	
	/**
	 * 判断是否存在参数错误
	 * @return
	 */
	public static boolean existsParamsError(BindingResult... results) {
		if(results!=null&&results.length>0){
			for(BindingResult result:results){
				if(result.hasErrors()){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 获取参数错误
	 * @return
	 */
	public static ErrorResponse getErrorParams(BindingResult... results) {
		ErrorResponse errorParams =  new ErrorResponse("140002","参数错误");
		if(results!=null&&results.length>0){
			for(BindingResult result:results){
				if(result.hasErrors()) {
					List<BaseResponse> errors = new ArrayList<BaseResponse>();
					for(FieldError error:result.getFieldErrors()){
						String code = error.getField()+"_"+StringUtils.lowerCaseFirstChar(error.getCode());
						errors.add(new BaseResponse(code,error.getDefaultMessage()));
						errorParams.setErrors(errors);
					}
		        }
			}
		}
		return errorParams;
	}
	
	/**
	 * 获取参数错误
	 * @return
	 */
	public static ErrorResponse getErrorParams(List<BaseResponse> errors) {
		ErrorResponse errorParams =  new ErrorResponse("140002","参数错误");
		errorParams.setErrors(errors);
		return errorParams;
	}
	

	/**
	 * 返回失败
	 * @param code 错误编码
	 * @param message 错误信息
	 * @return
	 */
	public static BaseResponse getErrorBusi(String code,String message){
		return getErrorBusi(new BaseResponse(code,message));
	}

	/**
	 * 业务逻辑错误
	 * @param error
	 * @return
	 */
	public static BaseResponse getErrorBusi(BaseResponse error) {
		ErrorResponse errorBusi =  new ErrorResponse("140005","业务逻辑错误");
		List<BaseResponse> errors = new ArrayList<BaseResponse>();
		errors.add(error);
		errorBusi.setErrors(errors);
		return errorBusi;
	}
	
	/**
	 * 签名错误
	 * @param error
	 * @return
	 */
	public static BaseResponse getErrorSign(BaseResponse error) {
		ErrorResponse errorBusi =  new ErrorResponse("140004","签名错误");
		List<BaseResponse> errors = new ArrayList<BaseResponse>();
		errors.add(error);
		errorBusi.setErrors(errors);
		return errorBusi;
	}
	
	/**
	 * Token失效
	 * @return
	 */
	public static ErrorResponse getErrorToken(BaseResponse error) {
		ErrorResponse errorParams =  new ErrorResponse("140003","Token失效");
		List<BaseResponse> errors = new ArrayList<BaseResponse>();
		errors.add(error);
		errorParams.setErrors(errors);
		return errorParams;
	}
}
