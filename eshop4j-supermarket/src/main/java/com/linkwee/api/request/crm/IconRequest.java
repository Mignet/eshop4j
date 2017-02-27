package com.linkwee.api.request.crm;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @描述：个人中心-上传头像
 *
 * @author chenjl
 * @时间 2016年5月12日
 *
 */
public class IconRequest {

	/**
	 * 上传图像
	 */
	@NotNull(message="图片不能为空")
	private String image;



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
