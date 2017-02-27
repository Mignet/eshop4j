package com.eshop4j.web.service;

import com.eshop4j.core.base.api.BaseResponse;
import com.eshop4j.openapi.request.ProductPushRequest;
import com.eshop4j.openapi.request.ProductUpdatRequest;
import com.eshop4j.xoss.api.OpenRequestHead;

/**
 * 开放平台产品接口
 * @author liqimoon
 *
 */
public interface CimOpenProductService {

	/**
	 * 产品推送
	 * @param productPushRequest
	 * @param openRequestHead
	 * @return
	 */
	BaseResponse pushProduct(ProductPushRequest productPushRequest,OpenRequestHead openRequestHead);
	
	/**
	 * 产品更新
	 * @param productUpdatRequest
	 * @param openRequestHead
	 * @return
	 */
	BaseResponse updateProduct(ProductUpdatRequest productUpdatRequest,OpenRequestHead openRequestHead);
}
