package com.linkwee.web.service;

import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.openapi.request.ProductPushRequest;
import com.linkwee.openapi.request.ProductUpdatRequest;
import com.linkwee.xoss.api.OpenRequestHead;

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
