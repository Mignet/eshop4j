/*
Navicat MySQL Data Transfer

Source Server         : 10.16.0.200-测试
Source Server Version : 50629
Source Host           : 10.16.0.200:3306
Source Database       : supermarket

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2016-07-11 14:18:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tsys_config
-- ----------------------------
DROP TABLE IF EXISTS `tsys_config`;
CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `config_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `config_type` varchar(50) DEFAULT NULL COMMENT '类别',
  `config_key` varchar(64) DEFAULT NULL COMMENT '键',
  `config_value` varchar(255) DEFAULT NULL COMMENT '值',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `crt_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `app_type` int(11) NOT NULL DEFAULT '0' COMMENT '应用类别:0全局，1理财师，2投资者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_key` (`app_type`,`config_type`,`config_key`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8 COMMENT='系统配置';

-- ----------------------------
-- Records of tsys_config
-- ----------------------------
INSERT INTO `tsys_config` VALUES ('4', '短信partnerid', '', 'send_vcode_partnerid', 'LHLC', null, '2015-08-18 10:29:35', '1');
INSERT INTO `tsys_config` VALUES ('6', '注册验证短信模板', null, 'reg_vcode_format', '理财师注册验证码%s,15分钟内有效。', null, '2015-08-18 10:29:35', '0');
INSERT INTO `tsys_config` VALUES ('7', '微信公众号appId', 'wechat', 'appid', 'wx88b4dd62e8a3c887', null, '2015-08-18 10:29:35', '1');
INSERT INTO `tsys_config` VALUES ('8', '微信公众号secret', 'wechat', 'app_secret', 'd15c26c4db28776142e0f1bfdb078048', null, '2015-08-18 10:29:35', '1');
INSERT INTO `tsys_config` VALUES ('9', '获取accessToken凭证url', 'wechat_url', 'url_access_token', 'https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code', null, '2015-08-18 10:29:35', '0');
INSERT INTO `tsys_config` VALUES ('10', '获取用户信息url', 'wechat_url', 'url_user_info', 'https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN', null, '2015-08-18 10:29:35', '0');
INSERT INTO `tsys_config` VALUES ('11', '安卓应用秘钥', 'app_secret', 'channel_android', '921snkktkznlxct12', '', '2015-09-23 10:21:32', '1');
INSERT INTO `tsys_config` VALUES ('12', 'ios应用秘钥', 'app_secret', 'channel_ios', '921ruyalkcnbfda13', '', '2015-09-23 10:21:45', '1');
INSERT INTO `tsys_config` VALUES ('15', 'wechat应用秘钥', 'app_secret', 'channel_wechat', '921ertkcbnjtaqd52', null, '2015-10-13 11:58:45', '1');
INSERT INTO `tsys_config` VALUES ('16', '微信分享-邀请理财师', 'wechat_share', 'wechat_share_link', 'http://mchannel.xiaoniuapp.com/pages/user/register.html?recommendCode=%s&name=%s', null, '2015-10-13 14:30:46', '0');
INSERT INTO `tsys_config` VALUES ('17', '微信分享-邀请理财师', 'wechat_share', 'wechat_share_desc', '加入领会理财师，高收益产品自由代理，高比例佣金实时计算，更享丰厚推荐奖励！', null, '2015-10-13 17:28:37', '0');
INSERT INTO `tsys_config` VALUES ('18', '微信分享-邀请理财师', 'wechat_share', 'wechat_share_imgurl', 'http://mchannel.xiaoniuapp.com/static/images/shareImg100_100.png', null, '2015-10-13 17:28:42', '0');
INSERT INTO `tsys_config` VALUES ('19', '微信分享-邀请理财师', 'wechat_share', 'wechat_share_title', '领会邀您成为理财师', null, '2015-10-13 17:28:46', '0');
INSERT INTO `tsys_config` VALUES ('20', '消息推送', 'pushmessage', 'pushmessage_partnerid', 'LHLC', '领会理财消息推送partnerid', '2015-10-13 17:53:23', '1');
INSERT INTO `tsys_config` VALUES ('21', '消息推送', 'pushmessage', 'pushmessage_rclcs_sign', 'RCLCS_SIGN', null, '2015-10-13 17:53:41', '0');
INSERT INTO `tsys_config` VALUES ('22', '邀请理财师推荐信息', null, 'rc_message_sign', '【小牛钱罐子】亲爱的罐主，您的理财师%s邀请您加入他的团队，晋级为领会理财师，既能赚收益又能赚佣金！限量邀请，马上加入吧 %s', null, '2015-10-13 18:02:40', '0');
INSERT INTO `tsys_config` VALUES ('23', '邀请理财师推荐信息', null, 'rc_message_anonymous', '【小牛钱罐子】亲爱的罐主，您已经成为钱罐子理财达人啦，我们推荐您晋级为领会理财师，既能赚收益又能赚佣金！限量开放，马上加入吧 %s', null, '2015-10-13 19:23:52', '0');
INSERT INTO `tsys_config` VALUES ('24', '消息推送', 'pushmessage', 'pushmessage_rclcs_anonymous', 'RCLCS_ANONYMOUS', null, '2015-10-13 19:24:47', '0');
INSERT INTO `tsys_config` VALUES ('25', '微信分享-邀请客户', 'wechat_share_customer', 'wechat_share_customer_link', 'http://minvestor.xiaoniuapp.com/pages/user/register.html?recommendCode=%s&name=%s', null, '2015-10-15 15:18:58', '0');
INSERT INTO `tsys_config` VALUES ('26', '微信分享-邀请客户', 'wechat_share_customer', 'wechat_share_customer_desc', '领会科技旗下创新理财平台！专业理财师贴身服务，年化收益高达11%！PICC全额保障账户安全！', '', '2015-10-13 17:28:37', '0');
INSERT INTO `tsys_config` VALUES ('27', '微信分享-邀请客户', 'wechat_share_customer', 'wechat_share_customer_imgurl', 'http://minvestor.xiaoniuapp.com/static/images/shareImg100_100.png', '', '2015-10-13 17:28:42', '0');
INSERT INTO `tsys_config` VALUES ('28', '微信分享-邀请客户', 'wechat_share_customer', 'wechat_share_customer_title', '领会金服', '', '2015-10-13 17:28:46', '0');
INSERT INTO `tsys_config` VALUES ('29', '交易中心分配的签名key', null, 'TRANS_MD5_SIGN_KEY', '495256cf3723b9087b4a7', null, '2015-09-17 17:12:33', '0');
INSERT INTO `tsys_config` VALUES ('30', '业务编码', '', 'account_partnerId', '10002', '账户中心分配的业务编码', '2015-10-16 09:07:15', '0');
INSERT INTO `tsys_config` VALUES ('31', '不发送短信', null, 'notype_no_send_vcode', '1', null, '2015-10-17 10:53:08', '0');
INSERT INTO `tsys_config` VALUES ('33', '账户明细类别', 'myaccountdetailtype', 'withdraw', '申请提现', '账户明细类别', '2015-10-17 15:16:41', '0');
INSERT INTO `tsys_config` VALUES ('34', '账户明细类别', 'myaccountdetailtype', 'feeinaccount', '佣金到账', '账户明细类别', '2015-10-17 15:16:41', '0');
INSERT INTO `tsys_config` VALUES ('38', '客户端配置', 'client_config', 'aboutme', 'http://mchannel.xiaoniuapp.com/pages/agreement/about.html', '关于我们的链接地址', '2015-10-30 14:28:56', '1');
INSERT INTO `tsys_config` VALUES ('39', '客户端配置', 'client_config', 'question', 'http://mchannel.xiaoniuapp.com/pages/questions/questions.html', '常见问题的链接地址', '2015-10-30 14:29:00', '1');
INSERT INTO `tsys_config` VALUES ('40', '客户端配置', 'client_config', 'showlevel', 'http://mchannel.xiaoniuapp.com/pages/agreement/levelInterest.html', '查看等级职级介绍的链接地址', '2015-10-30 14:29:44', '1');
INSERT INTO `tsys_config` VALUES ('43', '客户端配置', 'client_config', 'rcintroduction', 'http://mchannel.xiaoniuapp.com/pages/agreement/partnerService.html', '邀请理财师规则介绍的链接地址', '2015-10-30 14:31:28', '1');
INSERT INTO `tsys_config` VALUES ('44', '客户端配置', 'client_config', 'serviceMail', 'kefu.linghui@tophlc.com', '客服邮箱', '2015-10-30 14:43:39', '1');
INSERT INTO `tsys_config` VALUES ('45', '客户端配置', 'client_config', 'wechatNumber', 'ProAdvisor', '微信公众号', '2015-10-30 14:43:39', '1');
INSERT INTO `tsys_config` VALUES ('46', '客户端配置', 'client_config', 'serviceTelephone', '400-888-6987', '客服电话', '2015-10-30 14:44:04', '1');
INSERT INTO `tsys_config` VALUES ('47', '客户端配置', 'client_config', 'shareLogoIcon', 'http://mchannel.xiaoniuapp.com/images/logo/80x80.png', '微信分享logo', '2015-10-30 14:47:11', '1');
INSERT INTO `tsys_config` VALUES ('48', '客户端配置', 'client_config', 'zfxy', 'http://mchannel.xiaoniuapp.com/pages/agreement/payService.html', '支付协议地址', '2015-11-09 18:30:43', '1');
INSERT INTO `tsys_config` VALUES ('49', '客户端配置', 'client_config', 'question', 'http://minvestor.xiaoniuapp.com/pages/questions/questions.html', '关于我们的链接地址', '2015-12-21 12:04:28', '2');
INSERT INTO `tsys_config` VALUES ('50', '客户端配置', 'client_config', 'serviceMail', 'kefu.linghui@tophlc.com', '客服邮箱', '2015-12-21 12:04:28', '2');
INSERT INTO `tsys_config` VALUES ('51', '客户端配置', 'client_config', 'serviceTelephone', '400-888-6987', '客服电话', '2015-12-21 12:04:28', '2');
INSERT INTO `tsys_config` VALUES ('52', '客户端配置', 'client_config', 'shareLogoIcon', 'http://minvestor.xiaoniuapp.com/images/logo/80x80.png', '微信分享logo', '2015-12-21 12:04:28', '2');
INSERT INTO `tsys_config` VALUES ('53', '客户端配置', 'client_config', 'wechatNumber', 'linghuijinfu', '微信公众号', '2015-12-21 12:04:28', '2');
INSERT INTO `tsys_config` VALUES ('54', '客户端配置', 'client_config', 'zfxy', 'http://minvestor.xiaoniuapp.com/pages/agreement/payService.html', '支付协议地址', '2015-12-21 12:04:28', '2');
INSERT INTO `tsys_config` VALUES ('55', '客户端配置', 'client_config', 'aboutme', 'http://minvestor.xiaoniuapp.com/pages/agreement/about.html', '关于我们的链接地址', '2015-12-21 12:05:18', '2');
INSERT INTO `tsys_config` VALUES ('57', '银行充值额度明细表', 'client_config', 'bankLimitDetail', 'http://minvestor.xiaoniuapp.com/pages/agreement/chongzhixiane.html', '银行充值额度明细表', '2016-04-08 16:16:09', '1');
INSERT INTO `tsys_config` VALUES ('58', '客户端配置', 'client_config', 'bankLimitDetail', 'http://minvestor.xiaoniuapp.com/pages/agreement/chongzhixiane.html', '银行充值额度明细表', '2016-04-08 17:58:27', '2');
INSERT INTO `tsys_config` VALUES ('62', '安卓应用秘钥', 'app_secret', 'investor_android', '120snkktkznlxczandr', null, '2015-12-22 11:14:00', '2');
INSERT INTO `tsys_config` VALUES ('63', 'ios应用秘钥', 'app_secret', 'investor_ios', '120ruyalkcnbfdaxios', null, '2015-12-22 11:14:00', '2');
INSERT INTO `tsys_config` VALUES ('64', 'wechat应用秘钥', 'app_secret', 'investor_wechat', '120ertkcbnjtaqdchat', null, '2015-12-22 11:14:00', '2');
INSERT INTO `tsys_config` VALUES ('65', '微信公众号appId', 'wechat', 'appid', 'wx5d4c7b2896ab7f9d', '', '2015-12-28 15:05:54', '2');
INSERT INTO `tsys_config` VALUES ('66', '微信公众号secret', 'wechat', 'app_secret', '129d8ae16c15fe6f682ac5497fb61e9b', '', '2015-12-28 15:05:54', '2');
INSERT INTO `tsys_config` VALUES ('67', '领会理财业务编码', null, 'lhlc_account_partnerId', '10004', '账户中心分配的业务编码', '2015-12-28 19:21:17', '0');
INSERT INTO `tsys_config` VALUES ('68', '账户中心分配给领会理财的签名key', null, 'lhlc_trans_md5_sign_key', '495256cf3723b9087bca1f830188', null, '2015-12-28 19:21:17', '0');
INSERT INTO `tsys_config` VALUES ('76', '理财师账户明细类别', 'cfp_myaccountdetailtype', 'cfp_1', '提现', null, '2015-12-30 10:32:11', '1');
INSERT INTO `tsys_config` VALUES ('77', '理财师账户明细类别', 'cfp_myaccountdetailtype', 'cfp_9', '收益到账', null, '2015-12-30 10:32:17', '1');
INSERT INTO `tsys_config` VALUES ('78', '理财师账户明细类别', 'cfp_myaccountdetailtype', 'cfp_14', '活动奖励', null, '2015-12-30 10:32:23', '1');
INSERT INTO `tsys_config` VALUES ('79', '理财师账户明细类别', 'cfp_myaccountdetailtype', 'cfp_13', '充值', null, '2015-12-30 10:32:29', '1');
INSERT INTO `tsys_config` VALUES ('80', '投资者账户明细类别', 'investor_myaccountdetailtype', 'investor_1', '提现', null, '2015-12-30 10:32:34', '2');
INSERT INTO `tsys_config` VALUES ('81', '投资者账户明细类别', 'investor_myaccountdetailtype', 'investor_14', '活动奖励', null, '2015-12-30 10:32:42', '2');
INSERT INTO `tsys_config` VALUES ('82', '投资者账户明细类别', 'investor_myaccountdetailtype', 'investor_13', '充值', null, '2015-12-30 10:32:47', '2');
INSERT INTO `tsys_config` VALUES ('83', '投资者收益类别', 'investor_profittype', 'fixed', '固定收益产品', null, '2016-01-18 19:01:59', '0');
INSERT INTO `tsys_config` VALUES ('84', '投资者收益类别', 'investor_profittype', 'float', '浮动收益产品', null, '2016-01-19 09:57:35', '0');
INSERT INTO `tsys_config` VALUES ('85', '投资者收益类别', 'investor_profittype', 'activity', '活动奖励', null, '2016-01-19 09:58:31', '0');
INSERT INTO `tsys_config` VALUES ('86', '判断是否新人注册时间参考点', 'new_regist_time', 'new_regist_time', '2016-02-24', null, '2016-01-20 10:14:43', '0');
INSERT INTO `tsys_config` VALUES ('87', '投资者产品详情url', null, 'url_product_detail_investor', 'http://minvestor.xiaoniuapp.com/pages/manage_finances/product_detail_credits.html', null, '2016-02-16 05:58:22', '0');
INSERT INTO `tsys_config` VALUES ('88', '通讯录-邀请客户', 'mail_invitation', 'mail_invitation_customer', 'Hi，我是理财师%s，邀您来领会金服投资，私享高收益产品和专业理财服务，限时送投资红包哦！马上注册吧 http://minvestor.xiaoniuapp.com/pages/user/register.html?recommendCode=%s', '理财师邀请客户短信文案（通讯录方式)', '2016-02-26 10:49:55', '0');
INSERT INTO `tsys_config` VALUES ('89', '通讯录-邀请理财师', 'mail_invitation', 'mail_invitation_lcs', 'Hi，我是%s，推荐您加入领会理财师，高收益产品自由代理，高比例佣金实时计算，限量推荐哦，点击注册 http://mchannel.xiaoniuapp.com/pages/user/register.html?recommendCode=%s', '理财师推荐理财师短信文案（通讯录方式)', '2016-02-26 10:49:57', '0');
INSERT INTO `tsys_config` VALUES ('90', '客户端配置', 'client_config', 'userService', 'http://mchannel.xiaoniuapp.com/pages/agreement/userService.html', '理财师用户协议', '2016-03-03 16:05:53', '1');
INSERT INTO `tsys_config` VALUES ('91', '客户端配置', 'client_config', 'userService', 'http://minvestor.xiaoniuapp.com/pages/agreement/userService.html', '金服用户协议', '2016-02-29 17:46:27', '2');
INSERT INTO `tsys_config` VALUES ('92', '通讯录-客户邀请客户', 'mail_invitation', 'mail_customer_invitation_customer', 'Hi，我是（%s），快来领会金服和我一起投资，私享高收益产品加专业理财师服务！马上注册吧 http://minvestor.xiaoniuapp.com/pages/user/register.html?recommendCode=%s', '客户邀请客户短信文案（通讯录方式）', '2016-03-09 15:01:29', '0');
INSERT INTO `tsys_config` VALUES ('93', '消息推送', 'pushmessage', 'pushmessage_partnerid', 'LHJF', '领会金服消息推送partnerid', '2015-10-14 05:53:23', '2');
INSERT INTO `tsys_config` VALUES ('94', '短信partnerid', '', 'send_vcode_partnerid', 'LHJF', null, '0000-00-00 00:00:00', '2');
INSERT INTO `tsys_config` VALUES ('95', '消息推送', 'pushmessage', 'pushmessage_cregister', '欢迎加入领会金服！您的专属理财师已准备好为您服务啦，投资问题随时互动，轻松开启专业理财！关注官方微信linghuijinfu还可以“骚扰”领会君哦', '用户注册成功', '2016-03-21 01:06:46', '2');
INSERT INTO `tsys_config` VALUES ('96', '消息推送', 'pushmessage', 'pushmessage_cfixedreturn', '亲，您于%s投资的%s已到期回款，快找理财师帮您安排投资计划吧', '定期产品到期回款', '2016-03-21 01:07:40', '2');
INSERT INTO `tsys_config` VALUES ('97', '消息推送', 'pushmessage', 'pushmessage_cfixedbigamountreturn', '亲，您投资的%s将于3天后到期，记得找理财师帮您安排后续理财计划哦', '定期产品在投资额超（含）10万元，且3天后到期回款', '2016-03-21 01:33:15', '2');
INSERT INTO `tsys_config` VALUES ('98', '消息推送', 'pushmessage', 'pushmessage_ctxapplysuccess', '亲，您于%s提交的提现申请已处理完成', '金服  提现申请处理完成', '2016-03-21 02:22:51', '2');
INSERT INTO `tsys_config` VALUES ('99', '消息推送', 'pushmessage', 'pushmessage_ctxapplyfail', '亲，您于%s提交的提现申请失败，提现金额已退回您的账户，请重新申请。如有疑问，请咨询客服400-888-6987', '金服  提现申请处理失败', '2016-03-21 02:24:19', '2');
INSERT INTO `tsys_config` VALUES ('100', '消息推送', 'pushmessage', 'pushmessage_lregister', '亲爱的理财师，欢迎加入领会!现在就去邀请客户注册【领会金服】并投资吧，超高佣金等着你哦~', '理财师 用户（理财师自己）注册成功', '2016-03-21 02:27:47', '1');
INSERT INTO `tsys_config` VALUES ('101', '消息推送', 'pushmessage', 'pushmessage_lcustomerregister', '恭喜您，您的专属客户%s已成功注册，快去为Ta推荐理财产品吧', '理财师   客户注册完成', '2016-03-21 02:29:10', '1');
INSERT INTO `tsys_config` VALUES ('102', '消息推送', 'pushmessage', 'pushmessage_lassigancustomer', '恭喜您，领会平台为您分配了一位新客户%s，快去为Ta制定投资计划吧', '理财师   平台分配客户', '2016-03-21 02:31:33', '1');
INSERT INTO `tsys_config` VALUES ('103', '消息推送', 'pushmessage', 'pushmessage_lgradeoneregister', '恭喜您，理财师%s已加入您的团队，以后Ta销售产品您将享受推荐收益哦~', '理财师  团队一级成员注册完成', '2016-03-21 02:32:55', '1');
INSERT INTO `tsys_config` VALUES ('104', '消息推送', 'pushmessage', 'pushmessage_lcustomerbuy', '您的客户%s投资了%s，金额%s，您的佣金又添一笔啦~', '理财师  客户购买成功', '2016-03-21 02:34:30', '1');
INSERT INTO `tsys_config` VALUES ('105', '消息推送', 'pushmessage', 'pushmessage_lgradeonesale', '您的一级团队成员%s销售了%s，金额%s，您的直接推荐收益又添一笔啦~', '理财师  团队一级成员销售成功', '2016-03-21 02:36:50', '1');
INSERT INTO `tsys_config` VALUES ('106', '消息推送', 'pushmessage', 'pushmessage_lgradetwosale', '您的二级团队成员销售了%s，金额%s，您的间接推荐收益又添一笔啦~', '理财师  团队二级成员销售成功', '2016-03-21 02:40:03', '1');
INSERT INTO `tsys_config` VALUES ('107', '消息推送', 'pushmessage', 'pushmessage_lcustomercurrentreurn', '您的客户%s赎回了一笔活期宝，金额%s，记得提醒客户复投哦', '理财师  客户活期宝赎回完成', '2016-03-21 02:41:33', '1');
INSERT INTO `tsys_config` VALUES ('108', '消息推送', 'pushmessage', 'pushmessage_lcustomerfixedreturn', '您的客户%s投资的%s已到期回款，金额%s，记得提醒客户复投哦', '理财师  客户定期产品到期回款', '2016-03-21 02:42:59', '1');
INSERT INTO `tsys_config` VALUES ('109', '消息推送', 'pushmessage', 'pushmessage_lcustomerbigamountreturn', '您的客户%s购买的%s将于3天后到期，金额%S，请您关注客户后续理财计划', '理财师  客户定期产品在投资额超（含）10万元，且3天后到期回款', '2016-03-21 02:45:00', '1');
INSERT INTO `tsys_config` VALUES ('110', '消息推送', 'pushmessage', 'pushmessage_lfeereceived', '您%s的佣金收益已经到账啦！', '理财师  佣金收益到账', '2016-03-21 02:49:35', '1');
INSERT INTO `tsys_config` VALUES ('111', '消息推送', 'pushmessage', 'pushmessage_ltxapplysuccess', '亲，您于%s提交的提现申请已处理完成', '理财师  提现申请处理完成', '2016-03-21 02:51:38', '1');
INSERT INTO `tsys_config` VALUES ('112', '消息推送', 'pushmessage', 'pushmessage_ltxapplyfail', '亲，您于%s提交的提现申请失败，提现金额已退回您的账户，请重新申请。如有疑问，请咨询客服', '理财师  提现申请处理失败', '2016-03-21 03:02:34', '1');
INSERT INTO `tsys_config` VALUES ('114', '推荐产品名称', 'recommend', 'recommend_product_name', '领会宝180天期', '推荐产品名称', '2016-04-29 10:13:54', '0');
INSERT INTO `tsys_config` VALUES ('115', '推荐产品名称1', 'recommend1', 'recommend_product_name1', '领会宝180天期1', '推荐产品名称', '2016-04-29 10:13:54', '0');
INSERT INTO `tsys_config` VALUES ('127', '领会用户中心签名Key', 'userCenter', 'lh_userCenter_signKey', 'S5Yn9H5qk0Yf1ZhwGum3SdwnVZ7fMAUD', '领会用户中心签名KEY', '2016-05-12 21:55:02', '0');
INSERT INTO `tsys_config` VALUES ('132', '初始化职级', null, 'init_level', '0', '初始化职级', '2016-05-13 13:52:23', '0');
INSERT INTO `tsys_config` VALUES ('140', '理财学院url', null, 'elearning_url', 'https://www.baidu.com', '理财学院url', '2016-05-18 15:38:00', '0');
INSERT INTO `tsys_config` VALUES ('141', '客户端配置', 'client_config', 'img_server_url', 'http://preimage.tophlc.com/', '图片服务器访问地址', '2016-05-13 12:48:01', '1');
INSERT INTO `tsys_config` VALUES ('142', '客户端配置', 'client_config', 'img_server_url', 'http://preimage.tophlc.com/', '图片服务器访问地址', '2016-05-13 12:48:01', '2');
INSERT INTO `tsys_config` VALUES ('143', '客户端配置', 'client_config', 'saleUserLevelUrl', 'http://mchannel.xiaoniuapp.com/pages/mine/mine_level.html', '职级界面', '2016-05-16 16:31:56', '1');
INSERT INTO `tsys_config` VALUES ('144', '客户端配置', 'client_config', 'kefuEasemobileName', 'tophlcTest', '客服环信账号', '2016-05-16 16:33:37', '1');
INSERT INTO `tsys_config` VALUES ('145', '客户端配置', 'client_config', 'kefuEasemobileName', 'tophlcTest', '客服环信账号', '2016-05-16 16:33:37', '2');
INSERT INTO `tsys_config` VALUES ('146', '资讯详情页面模板访问地址', 'client_config', 'news_dtl_templet_url', 'http://www.baidu.com', '纯文本资讯详情页面模板访问地址', '2016-05-13 12:48:01', '0');
INSERT INTO `tsys_config` VALUES ('148', '前景FTP取数据配置', 'qianjing', 'qj_getFtpData', '1', '前景FTP取数据配置 0-全部文件数据 1-单个文件数据', '2016-06-02 11:01:22', '0');
INSERT INTO `tsys_config` VALUES ('149', '初始化职级', null, 'init_level', '1', '初始化职级', '2016-05-13 13:52:23', '0');
INSERT INTO `tsys_config` VALUES ('150', 'test', 'test', 'tes', 'tes', '12', '2016-06-17 17:40:18', '1');

-- ----------------------------
-- Table structure for tsys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tsys_role_permission`;
CREATE TABLE `tsys_role_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) unsigned DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=243 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色与权限关联表';

-- ----------------------------
-- Records of tsys_role_permission
-- ----------------------------
INSERT INTO `tsys_role_permission` VALUES ('129', '2', '2');
INSERT INTO `tsys_role_permission` VALUES ('130', '2', '3');
INSERT INTO `tsys_role_permission` VALUES ('131', '2', '5');
INSERT INTO `tsys_role_permission` VALUES ('132', '2', '6');
INSERT INTO `tsys_role_permission` VALUES ('133', '2', '7');
INSERT INTO `tsys_role_permission` VALUES ('134', '2', '8');
INSERT INTO `tsys_role_permission` VALUES ('135', '2', '9');
INSERT INTO `tsys_role_permission` VALUES ('205', '1', '1');
INSERT INTO `tsys_role_permission` VALUES ('206', '1', '2');
INSERT INTO `tsys_role_permission` VALUES ('207', '1', '3');
INSERT INTO `tsys_role_permission` VALUES ('208', '1', '4');
INSERT INTO `tsys_role_permission` VALUES ('209', '1', '5');
INSERT INTO `tsys_role_permission` VALUES ('210', '1', '6');
INSERT INTO `tsys_role_permission` VALUES ('211', '1', '7');
INSERT INTO `tsys_role_permission` VALUES ('212', '1', '8');
INSERT INTO `tsys_role_permission` VALUES ('213', '1', '9');
INSERT INTO `tsys_role_permission` VALUES ('214', '1', '10');
INSERT INTO `tsys_role_permission` VALUES ('215', '1', '11');
INSERT INTO `tsys_role_permission` VALUES ('216', '1', '12');
INSERT INTO `tsys_role_permission` VALUES ('217', '1', '13');
INSERT INTO `tsys_role_permission` VALUES ('218', '1', '14');
INSERT INTO `tsys_role_permission` VALUES ('219', '1', '15');
INSERT INTO `tsys_role_permission` VALUES ('220', '1', '16');
INSERT INTO `tsys_role_permission` VALUES ('221', '1', '17');
INSERT INTO `tsys_role_permission` VALUES ('222', '1', '18');
INSERT INTO `tsys_role_permission` VALUES ('223', '1', '19');
INSERT INTO `tsys_role_permission` VALUES ('224', '1', '20');
INSERT INTO `tsys_role_permission` VALUES ('225', '1', '21');
INSERT INTO `tsys_role_permission` VALUES ('226', '1', '22');
INSERT INTO `tsys_role_permission` VALUES ('227', '1', '23');
INSERT INTO `tsys_role_permission` VALUES ('228', '1', '24');
INSERT INTO `tsys_role_permission` VALUES ('229', '1', '25');
INSERT INTO `tsys_role_permission` VALUES ('230', '1', '26');
INSERT INTO `tsys_role_permission` VALUES ('231', '1', '27');
INSERT INTO `tsys_role_permission` VALUES ('232', '1', '28');
INSERT INTO `tsys_role_permission` VALUES ('233', '1', '29');
INSERT INTO `tsys_role_permission` VALUES ('234', '1', '30');
INSERT INTO `tsys_role_permission` VALUES ('235', '1', '31');
INSERT INTO `tsys_role_permission` VALUES ('236', '1', '32');
INSERT INTO `tsys_role_permission` VALUES ('237', '1', '33');
INSERT INTO `tsys_role_permission` VALUES ('238', '1', '34');
INSERT INTO `tsys_role_permission` VALUES ('239', '1', '35');
INSERT INTO `tsys_role_permission` VALUES ('240', '1', '36');
INSERT INTO `tsys_role_permission` VALUES ('241', '4', '18');
INSERT INTO `tsys_role_permission` VALUES ('242', '4', '19');

-- ----------------------------
-- Table structure for tsys_menus
-- ----------------------------
DROP TABLE IF EXISTS `tsys_menus`;
CREATE TABLE `tsys_menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(100) NOT NULL COMMENT '菜单名称',
  `menu_url` varchar(500) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `is_disable` smallint(6) DEFAULT NULL COMMENT '是否禁用: 1=禁用,0=启用',
  `sort` int(11) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `permission_sign` varchar(200) DEFAULT NULL,
  `menu_icon` varchar(200) DEFAULT NULL COMMENT '图标字体',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='运营平台-菜单管理表';

-- ----------------------------
-- Records of tsys_menus
-- ----------------------------
INSERT INTO `tsys_menus` VALUES ('4', '理财师管理', 'javascript:;', '0', '0', '1', '2016-06-22 16:49:48', 'investor-pwd:modify', 'fa-users');
INSERT INTO `tsys_menus` VALUES ('5', '理财师信息概览', 'rest/lcsDataView/dataViewPage', '4', '0', '10', '2016-06-23 10:13:19', 'cfp-dataview:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('6', '理财师管理', 'rest/lcsList/lcsListPage', '4', '0', '12', '2016-06-23 10:14:23', 'cfp-list:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('7', '理财师销售与收益管理', 'rest/cfprelevant/salelist', '4', '0', '13', '2016-06-23 10:15:31', 'cfp-sale:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('8', '投资客户管理', 'javascript:;', '0', '0', '2', '2016-06-23 10:18:54', 'investor-list:read', 'fa-money');
INSERT INTO `tsys_menus` VALUES ('9', '客户信息概览', 'rest/invest/dataview', '8', '0', '13', '2016-06-23 10:21:31', 'investor-dateview:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('10', '客户管理', 'rest/invest/toinvestorlist', '8', '0', '14', '2016-06-23 10:21:58', 'investor-list:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('11', '客户 投资与收益管理', 'rest/customerInvest/list', '8', '0', '15', '2016-06-23 10:22:26', 'investor-sale:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('12', '理财产品管理', 'javascript:;', '0', '0', '3', '2016-06-23 10:22:57', 'product-list:read', 'fa-mortar-board');
INSERT INTO `tsys_menus` VALUES ('13', '产品管理', 'rest/product/toprolist', '12', '0', '16', '2016-06-23 10:23:23', 'product-list:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('14', '产品销售管理', 'rest/product/toprosalelist', '12', '0', '17', '2016-06-23 10:24:08', 'product-sale:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('15', '活动管理', 'javascript:;', '0', '0', '4', '2016-06-23 10:25:05', 'act-list:*', 'fa-bullhorn');
INSERT INTO `tsys_menus` VALUES ('16', '红包活动管理', 'rest/page/blank', '15', '0', '18', '2016-06-23 10:25:53', 'act-list:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('17', '红包列表', 'rest/redpaper/initRedpaperList', '15', '0', '19', '2016-06-23 10:26:20', 'act-list:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('18', '灰度管理', 'javascript:;', '0', '0', '6', '2016-06-23 10:28:14', 'sys-graylist:*', 'fa-certificate');
INSERT INTO `tsys_menus` VALUES ('19', '灰度管理', 'rest/gray/graylist', '18', '0', '19', '2016-06-23 10:28:44', 'sys-graylist:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('20', '内容管理及配置', 'javascript:;', '0', '0', '7', '2016-06-23 10:37:24', 'cms-info:*', 'fa-book');
INSERT INTO `tsys_menus` VALUES ('21', '资讯管理', 'rest/news/list', '20', '0', '20', '2016-06-23 10:38:31', 'cms-info:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('22', '插屏广告管理 ', 'rest/adv/init', '20', '0', '21', '2016-06-23 10:38:51', 'cms-info:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('23', 'APP参数配置', 'rest/sysconfig/list', '20', '0', '22', '2016-06-23 10:39:30', 'sys-permission:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('24', '公告消息管理', 'rest/cms/msg/list', '20', '0', '23', '2016-06-23 10:40:03', 'cms-msg:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('25', '日志管理', 'javascript:;', '0', '0', '8', '2016-06-23 10:41:44', 'cfp-dataview:read', 'fa-cogs');
INSERT INTO `tsys_menus` VALUES ('26', '账号日志操作列表', 'rest/log/account', '25', '0', '24', '2016-06-23 10:42:04', 'cfp-dataview:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('27', '系统管理', 'javascript:;', '0', '0', '9', '2016-06-23 10:42:31', 'sys-permission:*', 'fa-cogs');
INSERT INTO `tsys_menus` VALUES ('28', '用户管理', 'rest/user', '27', '0', '25', '2016-06-23 10:42:50', 'sys-user:create', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('29', '角色管理', 'rest/role', '27', '0', '26', '2016-06-23 10:43:31', 'sys-role:create', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('30', '权限管理', 'rest/permissions/list', '27', '0', '27', '2016-06-23 10:44:15', 'sys-permission:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('31', '菜单管理', 'rest/menus/list', '27', '0', '28', '2016-06-23 10:44:35', 'sys-permission:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('32', '个人中心', 'javascipt:;', '0', '0', '11', '2016-06-23 10:46:01', '', 'fa-user-secret');
INSERT INTO `tsys_menus` VALUES ('33', '个人信息', 'rest/page/blank', '32', '0', '29', '2016-06-23 10:46:21', '', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('34', '密码修改', 'rest/user/toresetpwd', '32', '0', '30', '2016-06-23 10:47:06', '', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('35', '红包每日数据', 'rest/redpaper/initRedpaperEveryDayList', '15', '0', '0', '2016-06-28 17:49:47', 'act-red-dataview:read', '');
INSERT INTO `tsys_menus` VALUES ('36', '活动列表管理', 'rest/cms/activitylist/list', '20', '0', '31', '2016-06-29 10:00:17', 'cms-info:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('37', '发放奖励', 'rest/activityprofit/list', '15', '0', '0', '2016-06-29 10:13:35', 'act-list:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('38', '佣金管理', 'rest/feepay/init', '0', '0', '11', '2016-06-29 11:19:47', '', 'fa-cubes');
INSERT INTO `tsys_menus` VALUES ('39', '佣金发放', 'rest/feepay/init', '38', '0', '0', '2016-06-29 11:20:24', '', '');
INSERT INTO `tsys_menus` VALUES ('40', '佣金记录', 'rest/feedetail/init', '38', '0', '2', '2016-06-29 11:22:37', '', '');
INSERT INTO `tsys_menus` VALUES ('41', '佣金汇总记录', 'rest/feesummary/init', '38', '0', '2', '2016-06-29 11:23:06', '', '');

-- ----------------------------
-- Table structure for tsys_permission
-- ----------------------------
DROP TABLE IF EXISTS `tsys_permission`;
CREATE TABLE `tsys_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_name` varchar(32) DEFAULT NULL COMMENT '权限名',
  `permission_sign` varchar(128) DEFAULT NULL COMMENT '权限标识,程序中判断使用,如"user:create"',
  `description` varchar(256) DEFAULT NULL COMMENT '权限描述,UI界面显示使用',
  `permission_category` varchar(128) DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of tsys_permission
-- ----------------------------
INSERT INTO `tsys_permission` VALUES ('1', '理财师信息概览', 'cfp-dataview:read', '页面浏览权限', '理财师');
INSERT INTO `tsys_permission` VALUES ('2', '理财师管理页面', 'cfp-list:read', '页面浏览权限', '理财师');
INSERT INTO `tsys_permission` VALUES ('3', '理财师详情', 'cfp-detail:read', '页面浏览权限', '理财师');
INSERT INTO `tsys_permission` VALUES ('4', '销售与管理收益', 'cfp-sale:read', '页面浏览权限', '理财师');
INSERT INTO `tsys_permission` VALUES ('5', '修改密码', 'cfp-pwd:modify', '操作权限', '理财师');
INSERT INTO `tsys_permission` VALUES ('6', '解绑银行卡', 'cfp-bankcard:unbind', '操作权限', '理财师');
INSERT INTO `tsys_permission` VALUES ('7', '修改上级', 'cfp-parent:modify', '操作权限', '理财师');
INSERT INTO `tsys_permission` VALUES ('8', '修改机构', 'cfp-group:modify', '操作权限', '理财师');
INSERT INTO `tsys_permission` VALUES ('9', '取消理财师资格', 'cfp-cfp:delete', '操作权限', '理财师');
INSERT INTO `tsys_permission` VALUES ('10', '禁止理财师登陆', 'cfp-login:modify', '操作权限', '理财师');
INSERT INTO `tsys_permission` VALUES ('11', '客户信息概览', 'investor-dateview:read', '页面浏览权限', '投资客户');
INSERT INTO `tsys_permission` VALUES ('12', '客户管理页面', 'investor-list:read', '页面浏览权限', '投资客户');
INSERT INTO `tsys_permission` VALUES ('13', '客户详情', 'investor-detail:read', '页面浏览权限', '投资客户');
INSERT INTO `tsys_permission` VALUES ('14', '客户投资与收益管理', 'investor-sale:read', '页面浏览权限', '投资客户');
INSERT INTO `tsys_permission` VALUES ('15', '修改密码', 'investor-pwd:modify', '操作权限', '投资客户');
INSERT INTO `tsys_permission` VALUES ('16', '解绑银行卡', 'investor-bankcard:unbind', '操作权限', '投资客户');
INSERT INTO `tsys_permission` VALUES ('17', '变更理财师', 'investor-parent:modify', '操作权限', '投资客户');
INSERT INTO `tsys_permission` VALUES ('18', '产品管理', 'product-list:read', '页面浏览权限', '产品');
INSERT INTO `tsys_permission` VALUES ('19', '销售明细', 'product-sale:read', '页面浏览权限', '产品');
INSERT INTO `tsys_permission` VALUES ('20', '新增/编辑产品', 'product-sale:modify', '操作权限', '产品');
INSERT INTO `tsys_permission` VALUES ('21', '发布产品', 'product-sale:publish', '操作权限', '产品');
INSERT INTO `tsys_permission` VALUES ('22', '下架产品', 'product-sale:back', '操作权限', '产品');
INSERT INTO `tsys_permission` VALUES ('23', '灰度用户管理模块', 'sys-graylist:*', '整个模块的权限', '系统');
INSERT INTO `tsys_permission` VALUES ('24', '用户管理', 'sys-user:create', '页面浏览权限', '系统');
INSERT INTO `tsys_permission` VALUES ('25', '角色管理', 'sys-role:create', '页面浏览权限', '系统');
INSERT INTO `tsys_permission` VALUES ('26', '权限管理', 'sys-permission:*', '页面浏览权限', '系统');
INSERT INTO `tsys_permission` VALUES ('27', '红包列表', 'act-red-list:read', '页面浏览权限', '活动');
INSERT INTO `tsys_permission` VALUES ('28', '红包每日数据', 'act-red-dataview:read', '页面浏览权限', '活动');
INSERT INTO `tsys_permission` VALUES ('29', '新增/编辑红包', 'act-red:modify', '操作权限', '活动');
INSERT INTO `tsys_permission` VALUES ('30', '正式发送红包', 'act-red:publish', '操作权限', '活动');
INSERT INTO `tsys_permission` VALUES ('31', '资讯管理模块', 'cms-info:*', '整个模块的权限', '资讯');
INSERT INTO `tsys_permission` VALUES ('32', '活动管理模块', 'act-list:*', '整个模块的权限', '活动');
INSERT INTO `tsys_permission` VALUES ('33', '公告消息管理模块', 'cms-msg:*', '整个模块的权限', '公告');
INSERT INTO `tsys_permission` VALUES ('34', '开屏广告', 'banner-ad:read', '页面浏览权限', '广告');
INSERT INTO `tsys_permission` VALUES ('35', '首页轮播', 'banner-flip:read', '页面浏览权限', '广告');
INSERT INTO `tsys_permission` VALUES ('36', '头像审核模块', 'cms-avator:*', '金服，理财师头像审核', '头像');

-- ----------------------------
-- Table structure for tsys_role
-- ----------------------------
DROP TABLE IF EXISTS `tsys_role`;
CREATE TABLE `tsys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名',
  `role_sign` varchar(128) DEFAULT NULL COMMENT '角色标识,程序中判断使用,如"admin"',
  `description` varchar(256) DEFAULT NULL COMMENT '角色描述,UI界面显示使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of tsys_role
-- ----------------------------
INSERT INTO `tsys_role` VALUES ('1', '管理员', 'admin', '管理员');
INSERT INTO `tsys_role` VALUES ('2', '产品经理', 'product-manager', '产品经理');
INSERT INTO `tsys_role` VALUES ('3', '渠道人员', 'consultant', '客户发展经理');
INSERT INTO `tsys_role` VALUES ('4', '客服人员', 'customer-service', '客户服务');
INSERT INTO `tsys_role` VALUES ('5', '运营经理', 'operation-manager', '运营经理');
INSERT INTO `tsys_role` VALUES ('6', '超级管理员', 'super_admin', '超级管理员');

-- ----------------------------
-- Table structure for tsys_user
-- ----------------------------
DROP TABLE IF EXISTS `tsys_user`;
CREATE TABLE `tsys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` char(200) DEFAULT NULL COMMENT '密码',
  `state` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `description` varchar(256) DEFAULT NULL COMMENT '用户描述,UI界面显示使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of tsys_user
-- ----------------------------
INSERT INTO `tsys_user` VALUES ('1', 'kermit', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Y', '2014-07-17 12:59:08', '我是创始人');
INSERT INTO `tsys_user` VALUES ('2', 'test', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'N', '2016-05-25 16:09:00', '不同于注册界面,管理界面自动为用户设置初始密码');
INSERT INTO `tsys_user` VALUES ('4', 'chenjialiang', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Y', '2016-05-26 20:06:12', '陈家洛');
INSERT INTO `tsys_user` VALUES ('5', 'chenchunyan', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Y', '2016-06-23 16:01:39', '不同于注册界面,管理界面自动为用户设置初始密码');

-- ----------------------------
-- Table structure for tsys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tsys_user_role`;
CREATE TABLE `tsys_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户与角色关联表';

-- ----------------------------
-- Records of tsys_user_role
-- ----------------------------
INSERT INTO `tsys_user_role` VALUES ('12', '1', '1');
INSERT INTO `tsys_user_role` VALUES ('13', '1', '6');
INSERT INTO `tsys_user_role` VALUES ('14', '5', '4');
