package  com.linkwee.api.response.tc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：首页-动态
 *
 * @author 何源
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class TradeNewlyDynamicResponse extends BaseEntity {
	private static final long serialVersionUID = 9120104410791039237L;
	/**
	 * 发生时间
	 */
	private String time;
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 已读标志
	 */
	private boolean readFlag;
	
	/**
	 *客户类别：1-客户  2-团队
	 */
	private String customertype;
	/**
	 * 用户id，1. 团队  销售 和团队注册时对应客户的用户编码；2.其他情况均为成员id
	 */
	private String userId;
	
	

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isReadFlag() {
		return readFlag;
	}

	public void setReadFlag(boolean readFlag) {
		this.readFlag = readFlag;
	}

	
	public String getCustomertype() {
		return customertype;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
