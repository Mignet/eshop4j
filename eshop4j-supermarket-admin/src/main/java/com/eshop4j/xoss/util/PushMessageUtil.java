package com.eshop4j.xoss.util;

import java.util.HashMap;
import java.util.Map;


public class PushMessageUtil {
	public static Map<String, String> pushSuccessResultCodeMap = new HashMap<String, String>();
	public static Map<String, String> pushFailedResultCodeMap = new HashMap<String, String>();
	static{
		/**
		 * 成功返回
		 */
		pushSuccessResultCodeMap.put("ok", "成功");
		pushSuccessResultCodeMap.put("successed_online", "成功");
		pushSuccessResultCodeMap.put("successed_offline", "成功");
		pushSuccessResultCodeMap.put("lastLogin", "成功");
		pushSuccessResultCodeMap.put("msgTotal", "成功");
		pushSuccessResultCodeMap.put("msgProcess", "成功");
		pushSuccessResultCodeMap.put("clickNum", "成功");
		/**
		 * 失败返回
		 */
		pushFailedResultCodeMap.put("successed_ignore", "无效用户，消息丢弃");
		pushFailedResultCodeMap.put("failed", "将cid列表加入黑名单失败");
		pushFailedResultCodeMap.put("invalidCidList", "无效的cid列表");
		pushFailedResultCodeMap.put("StopTaskError", "停止任务失败");
		pushFailedResultCodeMap.put("NotInDealRange", "该任务不在停止任务的范围内");
		pushFailedResultCodeMap.put("Error", "	请求信息填写有误");
		pushFailedResultCodeMap.put("AppidError", "clientid绑定的appid与推送的appid不符");
		pushFailedResultCodeMap.put("AppKeyError", "Appkey填写错误");
		pushFailedResultCodeMap.put("sign_error", "Appkey与ClientId不匹配，鉴权失败");
		pushFailedResultCodeMap.put("domain_error", "填写的域名错误或者无法解析");
		pushFailedResultCodeMap.put("action_error", "未找到对应的action动作");
		pushFailedResultCodeMap.put("PushTotalNumOverLimit", "推送消息个数总数超限");
		pushFailedResultCodeMap.put("TokenMD5NoUsers", "在系统中未查找到用户");
		pushFailedResultCodeMap.put("TargetListIsNullOrSizeIs0", "目标用户列表为空");
		pushFailedResultCodeMap.put("taskIdNullError", "任务Id为空");
		pushFailedResultCodeMap.put("ServiceError！", "service错误");
		pushFailedResultCodeMap.put("AppidNoAppSecret！", "appId没有对应的appSecret");
		pushFailedResultCodeMap.put("AppidNoMatchAppKey", "appid未找到对应的appkey");
		pushFailedResultCodeMap.put("TaskIdNotMatchAppKey", "taskId找不到对应的appKey");
		pushFailedResultCodeMap.put("NullMsgCommon", "未找到消息公共体");
		pushFailedResultCodeMap.put("PushMsgToListTimesOverLimit", "群推消息次数超限");
		pushFailedResultCodeMap.put("PushMsgToAppTimesOverLimit", "群推消息次数超限");
		pushFailedResultCodeMap.put("TokenMD5NoUsers", "在系统中未查找到用户");
		pushFailedResultCodeMap.put("SendError", "消息发送失败");
		pushFailedResultCodeMap.put("SynSendError", "报文发送错误");
		pushFailedResultCodeMap.put("Online", "在线");
		pushFailedResultCodeMap.put("Offline", "离线");
		pushFailedResultCodeMap.put("Nobind", "cid未绑定appid");
		pushFailedResultCodeMap.put("FlowExceeded", "接口消息推送流量已超限");
		pushFailedResultCodeMap.put("BlackAppId", "appId为黑名单");
		pushFailedResultCodeMap.put("TokenMD5Error", "cid填写错误");
		pushFailedResultCodeMap.put("TagsNoUsers", "无标签找不到对应用户");
		pushFailedResultCodeMap.put("AppIdNoUsers", "appid下找不到对应用户");
		pushFailedResultCodeMap.put("PushTotalNumOverLimit", "推送总数超限");
		pushFailedResultCodeMap.put("NoSuchTaskId", "无效contentid");
		pushFailedResultCodeMap.put("OverLimit", "每个clientId在24小时内只能设置一次");
		pushFailedResultCodeMap.put("ParsePushInfoError", "pushinfo消息格式有误");
		pushFailedResultCodeMap.put("DeviceTokenError", "无效devicetoken");
		pushFailedResultCodeMap.put("NoTargetDeviceToken", "没有填写devicetoken");
		pushFailedResultCodeMap.put("TaskIdNotMatchAppKey", "taskId找不到对应的appKey");
		pushFailedResultCodeMap.put("NOTarget", "没有推送目标");
		pushFailedResultCodeMap.put("TagInvalidOrNoAuth", "无效的变迁或没鉴权");
		pushFailedResultCodeMap.put("AliasNotBind", "别名没有绑定");
		pushFailedResultCodeMap.put("OtherError", "未知错误，无法判定错误类型");
	}
	
}
