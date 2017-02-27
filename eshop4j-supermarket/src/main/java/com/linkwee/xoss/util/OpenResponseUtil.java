package com.linkwee.xoss.util;

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

import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.ErrorResponse;
import com.linkwee.core.base.api.SuccessResponse;
import com.linkwee.core.util.StringUtils;

public class OpenResponseUtil {
	
	protected static final Logger logger = LoggerFactory.getLogger(OpenResponseUtil.class);
	
	/**
	 * 成功返回-data无数据
	 * @return
	 */
	public static BaseResponse getSuccessResponse(){
		return new SuccessResponse<Object>();
	}
	
	/**
	 * 成功返回-data有数据
	 * @param data 源数据
	 * @return
	 */
	public static <T>BaseResponse getSuccessResponse(T data){
		return getSuccessResponse(data,null);
	}
	
	/**
	 * 获取参数错误
	 * @return
	 */
	public static ErrorResponse getErrorParams(List<BaseResponse> errors) {
		ErrorResponse errorParams =  new ErrorResponse("300002","参数错误");
		errorParams.setErrors(errors);
		return errorParams;
	}

	/**
	 * 签名错误
	 * @param error
	 * @return
	 */
	public static BaseResponse getErrorSign(BaseResponse error) {
		ErrorResponse errorBusi =  new ErrorResponse("300003","签名错误");
		List<BaseResponse> errors = new ArrayList<BaseResponse>();
		errors.add(error);
		errorBusi.setErrors(errors);
		return errorBusi;
	}
	

	/**
	 * 业务逻辑错误
	 * @param error
	 * @return
	 */
	public static BaseResponse getErrorBusi(BaseResponse error) {
		ErrorResponse errorBusi =  new ErrorResponse("300004","操作失败，请联系客服");
		List<BaseResponse> errors = new ArrayList<BaseResponse>();
		errors.add(error);
		errorBusi.setErrors(errors);
		return errorBusi;
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
							logger.info(clazz.getName()+"找不到构造函数:",e);
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
					logger.info(clazz.getName()+"找不到构造函数:",e);
				}
			}else{
				ret = data;
			}
			return new SuccessResponse<Object>(ret);
		}
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
		ErrorResponse errorParams =  new ErrorResponse("300002","参数错误");
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
}
