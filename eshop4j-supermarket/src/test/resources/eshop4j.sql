/*
Navicat MySQL Data Transfer

Source Server         : 10.16.0.200-测试
Source Server Version : 50629
Source Host           : 10.16.0.200:3306
Source Database       : eshop4j

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-02-27 19:04:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbiz_brand
-- ----------------------------
DROP TABLE IF EXISTS `tbiz_brand`;
CREATE TABLE `tbiz_brand` (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbiz_brand
-- ----------------------------

-- ----------------------------
-- Table structure for tbiz_cash_record
-- ----------------------------
DROP TABLE IF EXISTS `tbiz_cash_record`;
CREATE TABLE `tbiz_cash_record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` date DEFAULT NULL,
  `consumer_id` varchar(20) DEFAULT NULL,
  `credit` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbiz_cash_record
-- ----------------------------

-- ----------------------------
-- Table structure for tbiz_categoty
-- ----------------------------
DROP TABLE IF EXISTS `tbiz_categoty`;
CREATE TABLE `tbiz_categoty` (
  `categoty_id` int(11) NOT NULL AUTO_INCREMENT,
  `categoty_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`categoty_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbiz_categoty
-- ----------------------------

-- ----------------------------
-- Table structure for tbiz_credit
-- ----------------------------
DROP TABLE IF EXISTS `tbiz_credit`;
CREATE TABLE `tbiz_credit` (
  `credit_id` varchar(32) NOT NULL,
  `credit_type` int(11) DEFAULT NULL COMMENT '1.积分=钱 2.商城积分=商城 3.股权积分=股权',
  `consumer_id` varchar(20) NOT NULL,
  `credit` int(11) DEFAULT NULL COMMENT '*10000',
  `create_time` date DEFAULT NULL,
  `create_order` varchar(20) DEFAULT NULL COMMENT '由哪个订单产生的',
  `remark` varchar(200) DEFAULT NULL COMMENT '公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注',
  PRIMARY KEY (`credit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='credit';

-- ----------------------------
-- Records of tbiz_credit
-- ----------------------------

-- ----------------------------
-- Table structure for tbiz_customer
-- ----------------------------
DROP TABLE IF EXISTS `tbiz_customer`;
CREATE TABLE `tbiz_customer` (
  `consumer_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `ancestor` text,
  `team_amount` int(11) DEFAULT NULL,
  `wechat_nickname` varchar(20) DEFAULT NULL,
  `wechat_subscribe` int(11) DEFAULT NULL COMMENT '用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息，只有openid和UnionID（在该公众号绑定到了微信开放平台账号时才有）。',
  `wechat_openid` varchar(40) DEFAULT NULL,
  `wechat_sex` int(11) DEFAULT NULL COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `wechat_city` varchar(40) DEFAULT NULL,
  `wechat_country` varchar(40) DEFAULT NULL,
  `wechat_province` varchar(40) DEFAULT NULL,
  `wechat_language` varchar(10) DEFAULT NULL,
  `wechat_headimgurl` varchar(40) DEFAULT NULL COMMENT '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。',
  `wechat_subscribe_time` timestamp NULL DEFAULT NULL,
  `wechat_unionid` varchar(40) DEFAULT NULL,
  `wechat_remark` varchar(200) DEFAULT NULL COMMENT '公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注',
  `wechat_groupid` varchar(40) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`consumer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4 COMMENT='销售、消费';

-- ----------------------------
-- Records of tbiz_customer
-- ----------------------------

-- ----------------------------
-- Table structure for tbiz_order
-- ----------------------------
DROP TABLE IF EXISTS `tbiz_order`;
CREATE TABLE `tbiz_order` (
  `order_id` varchar(32) NOT NULL,
  `consumer_id` varchar(20) NOT NULL,
  `create_time` date DEFAULT NULL,
  `amount` int(11) DEFAULT NULL COMMENT '*10000',
  `address` varchar(200) DEFAULT NULL,
  `is_payed` int(11) DEFAULT NULL,
  `is_send` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbiz_order
-- ----------------------------

-- ----------------------------
-- Table structure for tbiz_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tbiz_order_item`;
CREATE TABLE `tbiz_order_item` (
  `order_item_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL COMMENT '当前只能是1',
  `create_time` date DEFAULT NULL,
  `amount` int(11) DEFAULT NULL COMMENT '*10000',
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbiz_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for tbiz_producer
-- ----------------------------
DROP TABLE IF EXISTS `tbiz_producer`;
CREATE TABLE `tbiz_producer` (
  `producer_id` int(11) NOT NULL AUTO_INCREMENT,
  `producer_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`producer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbiz_producer
-- ----------------------------

-- ----------------------------
-- Table structure for tbiz_product
-- ----------------------------
DROP TABLE IF EXISTS `tbiz_product`;
CREATE TABLE `tbiz_product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `producer_id` varchar(10) NOT NULL,
  `brand_id` varchar(10) NOT NULL,
  `categoty_id` varchar(10) NOT NULL,
  `product_name` varchar(20) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `inventory` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbiz_product
-- ----------------------------

-- ----------------------------
-- Table structure for tbiz_team_profit_key
-- ----------------------------
DROP TABLE IF EXISTS `tbiz_team_profit_key`;
CREATE TABLE `tbiz_team_profit_key` (
  `profit_id` int(11) NOT NULL AUTO_INCREMENT,
  `amount_begin` int(11) DEFAULT NULL,
  `amount_end` int(11) DEFAULT NULL,
  `keyx` int(11) DEFAULT NULL,
  PRIMARY KEY (`profit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tbiz_team_profit_key
-- ----------------------------

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
  UNIQUE KEY `uniq_key` (`app_type`,`config_type`,`config_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=402 DEFAULT CHARSET=utf8 COMMENT='系统配置';

-- ----------------------------
-- Records of tsys_config
-- ----------------------------
INSERT INTO `tsys_config` VALUES ('1', '微信公众号appId', 'wechat', 'appid', 'wx83677e6da548b99e', 'bz', '2015-08-18 10:29:35', '1');
INSERT INTO `tsys_config` VALUES ('2', '微信公众号secret', 'wechat', 'app_secret', '03ad6bab2052b1adee85818a50babadb', '', '2015-08-18 10:29:35', '1');
INSERT INTO `tsys_config` VALUES ('3', '获取微信accessToken', 'wechat_url', 'url_access_token', 'https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code', null, '2015-08-18 10:29:35', '0');
INSERT INTO `tsys_config` VALUES ('4', '获取微信用户信息url', 'wechat_url', 'url_user_info', 'https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN', null, '2015-08-18 10:29:35', '0');
INSERT INTO `tsys_config` VALUES ('5', '安卓应用秘钥', 'app_secret', 'channel_android', '921snkktkznlxct12', '', '2015-09-23 10:21:32', '1');
INSERT INTO `tsys_config` VALUES ('6', 'ios应用秘钥', 'app_secret', 'channel_ios', '921ruyalkcnbfda13', '', '2015-09-23 10:21:45', '1');
INSERT INTO `tsys_config` VALUES ('7', 'wechat应用秘钥', 'app_secret', 'channel_wechat', '921ertkcbnjtaqd52', null, '2015-10-13 11:58:45', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 COMMENT='运营平台-菜单管理表';

-- ----------------------------
-- Records of tsys_menus
-- ----------------------------
INSERT INTO `tsys_menus` VALUES ('4', '理财师管理', 'javascript:;', '0', '0', '1', '2016-06-22 16:49:48', 'cfp-dataview:read', 'fa-users');
INSERT INTO `tsys_menus` VALUES ('5', '理财师信息概览', 'rest/lcsList/dataViewPage', '4', '0', '10', '2016-06-23 10:13:19', 'cfp-dataview:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('6', '理财师管理', 'rest/lcsList/lcsListPage', '4', '0', '12', '2016-06-23 10:14:23', 'cfp-list:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('7', '理财师销售与收益管理', 'rest/cfplannerSale/cfplannerSaleList', '4', '0', '13', '2016-06-23 10:15:31', 'cfp-sale:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('8', '投资客户管理', 'javascript:;', '0', '0', '2', '2016-06-23 10:18:54', 'investor-list:read', 'fa-money');
INSERT INTO `tsys_menus` VALUES ('9', '客户信息概览', 'rest/investor/dataview', '8', '0', '13', '2016-06-23 10:21:31', 'investor-dateview:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('10', '客户管理', 'rest/investor/investorListPage', '8', '0', '14', '2016-06-23 10:21:58', 'investor-list:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('11', '客户 投资与收益管理', 'rest/investorInvest/investorInvestPage', '8', '0', '15', '2016-06-23 10:22:26', 'investor-sale:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('12', '理财产品管理', 'javascript:;', '0', '0', '4', '2016-06-23 10:22:57', 'product-list:read', 'fa-diamond');
INSERT INTO `tsys_menus` VALUES ('13', '产品管理', 'rest/cim/cimproduct/list', '12', '0', '16', '2016-06-23 10:23:23', 'product-list:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('14', '产品销售管理', 'rest/cim/cimproduct/saleList', '12', '0', '17', '2016-06-23 10:24:08', 'product-sale:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('15', '活动管理', 'javascript:;', '0', '0', '5', '2016-06-23 10:25:05', 'act-list:*', 'fa-bullhorn');
INSERT INTO `tsys_menus` VALUES ('16', '红包活动管理', 'rest/page/blank', '15', '1', '18', '2016-06-23 10:25:53', 'act-list:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('17', '红包列表', 'rest/redpacket/initPage', '15', '0', '19', '2016-06-23 10:26:20', 'act-list:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('18', '灰度管理', 'javascript:;', '0', '0', '6', '2016-06-23 10:28:14', 'sys-graylist:*', 'fa-certificate');
INSERT INTO `tsys_menus` VALUES ('19', '灰度管理', 'rest/sys/gray/list', '18', '0', '19', '2016-06-23 10:28:44', 'sys-graylist:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('20', '内容管理及配置', 'javascript:;', '0', '0', '7', '2016-06-23 10:37:24', 'cms-info:*', 'fa-book');
INSERT INTO `tsys_menus` VALUES ('21', '资讯管理', 'rest/news/list', '20', '0', '20', '2016-06-23 10:38:31', 'cms-info:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('22', '插屏广告管理 ', 'rest/adv/init', '20', '0', '21', '2016-06-23 10:38:51', 'cms-info:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('23', 'APP参数配置', 'rest/sysconfig/list', '47', '0', '22', '2016-06-23 10:39:30', 'app:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('24', '公告消息管理', 'rest/cms/msg/list', '20', '0', '23', '2016-06-23 10:40:03', 'cms-msg:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('25', '日志管理', 'javascript:;', '0', '0', '8', '2016-06-23 10:41:44', 'cfp-dataview:read', 'fa-cogs');
INSERT INTO `tsys_menus` VALUES ('26', '账号日志操作列表', 'rest/log/account', '25', '0', '24', '2016-06-23 10:42:04', 'cfp-dataview:read', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('27', '系统管理', 'javascript:;', '0', '0', '10', '2016-06-23 10:42:31', 'sys-permission:*', 'fa-cogs');
INSERT INTO `tsys_menus` VALUES ('28', '用户管理', 'rest/user', '27', '0', '25', '2016-06-23 10:42:50', 'sys-user:create', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('29', '角色管理', 'rest/role', '27', '0', '26', '2016-06-23 10:43:31', 'sys-role:create', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('30', '权限管理', 'rest/permissions/list', '27', '0', '27', '2016-06-23 10:44:15', 'sys-permission:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('31', '菜单管理', 'rest/menus/list', '27', '0', '28', '2016-06-23 10:44:35', 'sys-permission:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('32', '个人中心', 'javascipt:;', '0', '0', '11', '2016-06-23 10:46:01', '', 'fa-user-secret');
INSERT INTO `tsys_menus` VALUES ('33', '个人信息', 'rest/page/blank', '32', '0', '29', '2016-06-23 10:46:21', '', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('34', '密码修改', 'rest/user/toresetpwd', '32', '0', '30', '2016-06-23 10:47:06', '', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('35', '红包每日数据', 'rest/redpacket/redpacketStatisticsPage', '15', '0', '1', '2016-06-28 17:49:47', 'act-red-dataview:read', '');
INSERT INTO `tsys_menus` VALUES ('36', '活动列表管理', 'rest/cms/activitylist/list', '20', '0', '31', '2016-06-29 10:00:17', 'cms-info:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('38', '佣金管理', 'rest/feepay/init', '0', '0', '9', '2016-06-29 11:19:47', 'sys-permission:*', 'fa-cubes');
INSERT INTO `tsys_menus` VALUES ('40', '佣金记录', 'rest/fee/initPage', '38', '0', '2', '2016-06-29 11:22:37', '', '');
INSERT INTO `tsys_menus` VALUES ('41', '佣金汇总记录', 'rest/feesummary/init', '38', '1', '2', '2016-06-29 11:23:06', '', '');
INSERT INTO `tsys_menus` VALUES ('43', '合作机构管理', '/rest/page/blank', '0', '0', '3', '2016-07-21 17:08:45', 'product-sale:publish', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('44', '合作机构列表', '/rest/cim/cimorginfo/list', '43', '0', '19', '2016-07-21 17:15:26', 'product-sale:publish', '');
INSERT INTO `tsys_menus` VALUES ('45', '订单管理', '/rest/page/blank', '38', '1', '3', '2016-07-22 15:07:13', 'sys-permission:*', 'fa-barcode');
INSERT INTO `tsys_menus` VALUES ('47', 'APP管理', '/rest/page/blank', '0', '0', '9', '2016-07-28 16:48:49', 'sys-permission:*', 'fa-cloud-upload');
INSERT INTO `tsys_menus` VALUES ('48', 'APP列表', '/rest/sm/smappversion/list', '47', '0', '1', '2016-07-28 16:52:36', 'app:*', 'fa-bars');
INSERT INTO `tsys_menus` VALUES ('49', 'APP意见反馈', '/rest/sm/smfeedback/list', '47', '0', '3', '2016-07-29 10:26:36', 'app:*', 'fa-anchor');
INSERT INTO `tsys_menus` VALUES ('50', '账户管理', '/rest/acc/acwithdrawapply/list', '0', '0', '1', '2016-08-12 17:48:17', 'sys-permission:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('52', '绑卡账户', '/rest/acc/acaccountbind/list', '50', '0', '1', '2016-08-12 17:51:27', 'sys-permission:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('53', '账户异常', '/rest/acc/acaccounterrorlog/list', '50', '0', '1', '2016-08-31 17:35:56', 'sys-permission:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('54', '投资统计', 'javascript:;', '0', '0', '0', '2016-09-05 17:43:42', 'product-sale:read', 'fa-bar-chart');
INSERT INTO `tsys_menus` VALUES ('55', '总体数据', '/rest/orgsalefee/init', '54', '0', '0', '2016-09-05 17:50:29', '', '');
INSERT INTO `tsys_menus` VALUES ('56', '产品销售', '/rest/cim/cimproduct/salesStatistics', '54', '0', '0', '2016-09-05 17:51:05', '', '');
INSERT INTO `tsys_menus` VALUES ('57', '投资用户', '/rest/invest/investList', '54', '0', '0', '2016-09-05 17:51:23', '', 'fa-area-chart');
INSERT INTO `tsys_menus` VALUES ('58', '账户发放奖励', '/rest/acc/acbalancerecord/grantlist', '50', '0', '1', '2016-09-02 19:04:47', 'sys-permission:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('59', '账户流水明细', '/rest/acc/acbalancerecord/list', '50', '0', '1', '2016-09-05 18:46:40', 'sys-permission:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('60', '提现记录明细', '/rest/acc/acwithdrawapply/detaillist', '50', '0', '1', '2016-09-07 19:44:02', 'sys-permission:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('61', '报单审批', 'rest/unRecordInvest/initPage', '4', '0', '0', '2016-09-12 11:37:24', '', 'fa-legal');
INSERT INTO `tsys_menus` VALUES ('62', '产品分类管理', '/rest/cim/cimproductcate/list', '12', '0', '18', '2016-09-20 17:16:10', '', 'fa-certificate');
INSERT INTO `tsys_menus` VALUES ('63', '数据统计', 'javascript:;', '0', '0', '0', '2016-09-27 10:14:38', null, null);
INSERT INTO `tsys_menus` VALUES ('64', '理财师业绩', 'rest/cfpAchievement/cfpAchievementPage', '63', '0', '0', '2016-09-27 10:15:40', null, null);
INSERT INTO `tsys_menus` VALUES ('65', '客户已绑定的机构', 'rest/investor/queryInvestorBindPlatformList	', '8', '0', '16', '2016-10-08 18:39:06', '', 'fa-users');
INSERT INTO `tsys_menus` VALUES ('66', '系统推送', 'rest/cim/syspushartificialqueue/list', '20', '0', '23', '2016-10-13 10:29:51', 'sys-push:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('67', '小心不要乱点-提现审批', '/rest/acc/acwithdrawapply/list', '50', '1', '1', '2016-10-26 10:31:04', 'sys-permission:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('68', '销售机构管理', 'rest/crmSalesOrg/salesOrgListPage', '0', '0', '0', '2016-11-07 14:14:36', '', 'fa-users');
INSERT INTO `tsys_menus` VALUES ('69', '销售机构管理', 'rest/crmSalesOrg/salesOrgListPage', '68', '0', '0', '2016-11-07 14:15:29', '', 'fa-users');
INSERT INTO `tsys_menus` VALUES ('70', '合作机构动态', '/rest/cim/cimorgdynamic/list', '43', '0', '20', '2016-11-09 15:48:03', '', '');
INSERT INTO `tsys_menus` VALUES ('71', '课堂', 'rest/acc/smclassroom/list', '20', '0', '20', '2016-11-14 17:00:06', 'cms-info:*', 'fa-group');
INSERT INTO `tsys_menus` VALUES ('72', '产品详情编辑管理', 'rest/cim/cimproductedit/list', '12', '0', '20', '2016-12-01 12:32:19', 'product-list:read', 'fa-edit');
INSERT INTO `tsys_menus` VALUES ('73', '新闻动态', '/rest/sm/smdynamicnews/list', '20', '0', '0', '2016-12-05 17:37:19', 'cms-info:*', 'fa-asterisk');
INSERT INTO `tsys_menus` VALUES ('74', '机构风控信息', '/rest/cim/cimorgrisk/list', '43', '0', '21', '2016-11-30 15:12:51', '', 'fa-users');
INSERT INTO `tsys_menus` VALUES ('75', '用户投资率', 'rest/cfpAchievement/investmentRatePage', '54', '0', '0', '2016-12-14 15:06:33', '', 'fa-bar-chart-o');
INSERT INTO `tsys_menus` VALUES ('76', '账户待发放奖励', 'rest/acc/acofflinerewarddraft/list', '50', '0', '3', '2017-01-03 18:51:55', '', '');
INSERT INTO `tsys_menus` VALUES ('77', '投资分布', 'rest/invest/investmentDistributionStatisticsPage', '54', '0', '0', '2017-01-06 14:32:05', '', '');
INSERT INTO `tsys_menus` VALUES ('78', '用户投资分布', '/rest/investmentDistribution/initPage', '63', '0', '0', '2017-01-10 14:12:30', '', '');
INSERT INTO `tsys_menus` VALUES ('79', '理财师佣金分布', '/rest/feeDistribution/initPage', '63', '0', '0', '2017-02-07 11:06:22', '', '');
INSERT INTO `tsys_menus` VALUES ('80', '理财师信息', 'rest/lcsList/lcsListPageNew', '4', '0', '0', '2017-02-23 16:37:28', '', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='权限表';

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
INSERT INTO `tsys_permission` VALUES ('37', 'app管理', 'app:*', '权限标识规则=资源:操作', 'APP管理');
INSERT INTO `tsys_permission` VALUES ('38', '账户管理', 'ac:*', '权限标识规则=资源:操作', '账户管理');
INSERT INTO `tsys_permission` VALUES ('39', '理财师报单审批', 'cfp-record:audit', '权限标识规则=资源:操作', '理财师');
INSERT INTO `tsys_permission` VALUES ('40', '佣金计算', 'fee:calc', '', '佣金管理');
INSERT INTO `tsys_permission` VALUES ('41', '佣金发放', 'fee:pay', '权限标识规则=资源:操作', '佣金管理');
INSERT INTO `tsys_permission` VALUES ('42', '系统推送', 'sys-push:*', '权限标识规则=资源:操作', '推送');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色表';

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
-- Table structure for tsys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tsys_role_permission`;
CREATE TABLE `tsys_role_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) unsigned DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=776 DEFAULT CHARSET=utf8 COMMENT='角色与权限关联表';

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
INSERT INTO `tsys_role_permission` VALUES ('241', '4', '18');
INSERT INTO `tsys_role_permission` VALUES ('242', '4', '19');
INSERT INTO `tsys_role_permission` VALUES ('692', '1', '1');
INSERT INTO `tsys_role_permission` VALUES ('693', '1', '2');
INSERT INTO `tsys_role_permission` VALUES ('694', '1', '3');
INSERT INTO `tsys_role_permission` VALUES ('695', '1', '4');
INSERT INTO `tsys_role_permission` VALUES ('696', '1', '5');
INSERT INTO `tsys_role_permission` VALUES ('697', '1', '6');
INSERT INTO `tsys_role_permission` VALUES ('698', '1', '7');
INSERT INTO `tsys_role_permission` VALUES ('699', '1', '8');
INSERT INTO `tsys_role_permission` VALUES ('700', '1', '9');
INSERT INTO `tsys_role_permission` VALUES ('701', '1', '10');
INSERT INTO `tsys_role_permission` VALUES ('702', '1', '11');
INSERT INTO `tsys_role_permission` VALUES ('703', '1', '12');
INSERT INTO `tsys_role_permission` VALUES ('704', '1', '13');
INSERT INTO `tsys_role_permission` VALUES ('705', '1', '14');
INSERT INTO `tsys_role_permission` VALUES ('706', '1', '15');
INSERT INTO `tsys_role_permission` VALUES ('707', '1', '16');
INSERT INTO `tsys_role_permission` VALUES ('708', '1', '17');
INSERT INTO `tsys_role_permission` VALUES ('709', '1', '18');
INSERT INTO `tsys_role_permission` VALUES ('710', '1', '19');
INSERT INTO `tsys_role_permission` VALUES ('711', '1', '20');
INSERT INTO `tsys_role_permission` VALUES ('712', '1', '21');
INSERT INTO `tsys_role_permission` VALUES ('713', '1', '22');
INSERT INTO `tsys_role_permission` VALUES ('714', '1', '23');
INSERT INTO `tsys_role_permission` VALUES ('715', '1', '24');
INSERT INTO `tsys_role_permission` VALUES ('716', '1', '25');
INSERT INTO `tsys_role_permission` VALUES ('717', '1', '26');
INSERT INTO `tsys_role_permission` VALUES ('718', '1', '27');
INSERT INTO `tsys_role_permission` VALUES ('719', '1', '28');
INSERT INTO `tsys_role_permission` VALUES ('720', '1', '29');
INSERT INTO `tsys_role_permission` VALUES ('721', '1', '30');
INSERT INTO `tsys_role_permission` VALUES ('722', '1', '31');
INSERT INTO `tsys_role_permission` VALUES ('723', '1', '32');
INSERT INTO `tsys_role_permission` VALUES ('724', '1', '33');
INSERT INTO `tsys_role_permission` VALUES ('725', '1', '34');
INSERT INTO `tsys_role_permission` VALUES ('726', '1', '35');
INSERT INTO `tsys_role_permission` VALUES ('727', '1', '36');
INSERT INTO `tsys_role_permission` VALUES ('728', '1', '37');
INSERT INTO `tsys_role_permission` VALUES ('729', '1', '38');
INSERT INTO `tsys_role_permission` VALUES ('730', '1', '39');
INSERT INTO `tsys_role_permission` VALUES ('731', '1', '40');
INSERT INTO `tsys_role_permission` VALUES ('732', '1', '41');
INSERT INTO `tsys_role_permission` VALUES ('733', '1', '42');
INSERT INTO `tsys_role_permission` VALUES ('734', '6', '1');
INSERT INTO `tsys_role_permission` VALUES ('735', '6', '2');
INSERT INTO `tsys_role_permission` VALUES ('736', '6', '3');
INSERT INTO `tsys_role_permission` VALUES ('737', '6', '4');
INSERT INTO `tsys_role_permission` VALUES ('738', '6', '5');
INSERT INTO `tsys_role_permission` VALUES ('739', '6', '6');
INSERT INTO `tsys_role_permission` VALUES ('740', '6', '7');
INSERT INTO `tsys_role_permission` VALUES ('741', '6', '8');
INSERT INTO `tsys_role_permission` VALUES ('742', '6', '9');
INSERT INTO `tsys_role_permission` VALUES ('743', '6', '10');
INSERT INTO `tsys_role_permission` VALUES ('744', '6', '11');
INSERT INTO `tsys_role_permission` VALUES ('745', '6', '12');
INSERT INTO `tsys_role_permission` VALUES ('746', '6', '13');
INSERT INTO `tsys_role_permission` VALUES ('747', '6', '14');
INSERT INTO `tsys_role_permission` VALUES ('748', '6', '15');
INSERT INTO `tsys_role_permission` VALUES ('749', '6', '16');
INSERT INTO `tsys_role_permission` VALUES ('750', '6', '17');
INSERT INTO `tsys_role_permission` VALUES ('751', '6', '18');
INSERT INTO `tsys_role_permission` VALUES ('752', '6', '19');
INSERT INTO `tsys_role_permission` VALUES ('753', '6', '20');
INSERT INTO `tsys_role_permission` VALUES ('754', '6', '21');
INSERT INTO `tsys_role_permission` VALUES ('755', '6', '22');
INSERT INTO `tsys_role_permission` VALUES ('756', '6', '23');
INSERT INTO `tsys_role_permission` VALUES ('757', '6', '24');
INSERT INTO `tsys_role_permission` VALUES ('758', '6', '25');
INSERT INTO `tsys_role_permission` VALUES ('759', '6', '26');
INSERT INTO `tsys_role_permission` VALUES ('760', '6', '27');
INSERT INTO `tsys_role_permission` VALUES ('761', '6', '28');
INSERT INTO `tsys_role_permission` VALUES ('762', '6', '29');
INSERT INTO `tsys_role_permission` VALUES ('763', '6', '30');
INSERT INTO `tsys_role_permission` VALUES ('764', '6', '31');
INSERT INTO `tsys_role_permission` VALUES ('765', '6', '32');
INSERT INTO `tsys_role_permission` VALUES ('766', '6', '33');
INSERT INTO `tsys_role_permission` VALUES ('767', '6', '34');
INSERT INTO `tsys_role_permission` VALUES ('768', '6', '35');
INSERT INTO `tsys_role_permission` VALUES ('769', '6', '36');
INSERT INTO `tsys_role_permission` VALUES ('770', '6', '37');
INSERT INTO `tsys_role_permission` VALUES ('771', '6', '38');
INSERT INTO `tsys_role_permission` VALUES ('772', '6', '39');
INSERT INTO `tsys_role_permission` VALUES ('773', '6', '40');
INSERT INTO `tsys_role_permission` VALUES ('774', '6', '41');
INSERT INTO `tsys_role_permission` VALUES ('775', '6', '42');

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tsys_user
-- ----------------------------
INSERT INTO `tsys_user` VALUES ('1', 'kermit', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Y', '2014-07-17 12:59:08', '我是创始人');
INSERT INTO `tsys_user` VALUES ('2', 'test', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'N', '2016-05-25 16:09:00', '测试');
INSERT INTO `tsys_user` VALUES ('4', 'chenjialiang', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Y', '2016-05-26 20:06:12', '陈家良');
INSERT INTO `tsys_user` VALUES ('5', 'chenchunyan', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Y', '2016-06-23 16:01:39', '陈春燕');
INSERT INTO `tsys_user` VALUES ('6', 'houxiaobi', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Y', '2016-07-12 09:29:19', '侯晓碧');
INSERT INTO `tsys_user` VALUES ('9', 'liqi', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Y', '2016-08-22 14:58:37', '李启');
INSERT INTO `tsys_user` VALUES ('12', 'herongdou', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Y', '2016-09-12 14:06:53', '何荣兜');
INSERT INTO `tsys_user` VALUES ('14', '20160912', 'c3bb5e3d9b876e3c4da34f5be5711d5a79074f724cd3fb6b1f409b2bf1097e31', 'N', '2016-09-12 14:37:30', '不同于注册界面,管理界面自动为用户设置初始密码');
INSERT INTO `tsys_user` VALUES ('15', 'chenheng', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Y', '2016-09-12 16:26:56', '陈衡');
INSERT INTO `tsys_user` VALUES ('16', 'huangmengxi', 'bf6e2ea95031069460be1cc1df8152f5c68a94eadfac8f916d5bfa8111900323', 'Y', '2016-09-20 19:23:18', '不同于注册界面,管理界面自动为用户设置初始密码');
INSERT INTO `tsys_user` VALUES ('17', 'Mignet', '1000:3bc0c4e977bcc48b5f4f87c4933d1f65:1cd8cdca905b6d2fb498ee5c36d5248a', null, '2016-10-24 11:00:37', null);
INSERT INTO `tsys_user` VALUES ('18', 'dujinlong', 'a498b184ebf7fc50ce34dd06393a338edc0de86d42058057a2c918df653ae80b', 'Y', '2016-10-27 16:45:10', '');
INSERT INTO `tsys_user` VALUES ('19', 'chenguoqing', 'a25e5e7d88a9c4ac8327d4e2e13467642fb6d2c31d00196d9e0bce380fd1c8e0', 'Y', '2016-11-10 10:41:21', '不同于注册界面,管理界面自动为用户设置初始密码');
INSERT INTO `tsys_user` VALUES ('20', 'qingyc', '4400c82ee41f9a8875fe9f45e17aa0fee4005ce4c4a5a2ed5960ff4d836c7f4c', 'Y', '2016-11-17 17:04:35', '不同于注册界面,管理界面自动为用户设置初始密码');
INSERT INTO `tsys_user` VALUES ('21', 'zhousheng', 'ba3ca7bc65dced7d3ce8d5461c3e74a88ecc552e73047e2ed7d913bdf1264a69', 'Y', '2016-12-02 14:53:26', '不同于注册界面,管理界面自动为用户设置初始密码');
INSERT INTO `tsys_user` VALUES ('23', 'hutao', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Y', '2017-01-10 11:47:20', '胡涛');
INSERT INTO `tsys_user` VALUES ('24', 'huangyalin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Y', '2017-02-07 11:00:41', '黄亚林');

-- ----------------------------
-- Table structure for tsys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tsys_user_role`;
CREATE TABLE `tsys_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='用户与角色关联表';

-- ----------------------------
-- Records of tsys_user_role
-- ----------------------------
INSERT INTO `tsys_user_role` VALUES ('25', '14', '4');
INSERT INTO `tsys_user_role` VALUES ('29', '6', '1');
INSERT INTO `tsys_user_role` VALUES ('30', '6', '6');
INSERT INTO `tsys_user_role` VALUES ('33', '15', '1');
INSERT INTO `tsys_user_role` VALUES ('34', '9', '1');
INSERT INTO `tsys_user_role` VALUES ('35', '5', '1');
INSERT INTO `tsys_user_role` VALUES ('36', '16', '1');
INSERT INTO `tsys_user_role` VALUES ('46', '18', '1');
INSERT INTO `tsys_user_role` VALUES ('47', '12', '1');
INSERT INTO `tsys_user_role` VALUES ('48', '12', '6');
INSERT INTO `tsys_user_role` VALUES ('49', '1', '1');
INSERT INTO `tsys_user_role` VALUES ('50', '1', '6');
INSERT INTO `tsys_user_role` VALUES ('51', '19', '1');
INSERT INTO `tsys_user_role` VALUES ('52', '19', '6');
INSERT INTO `tsys_user_role` VALUES ('53', '20', '1');
INSERT INTO `tsys_user_role` VALUES ('54', '20', '6');
INSERT INTO `tsys_user_role` VALUES ('55', '23', '1');
INSERT INTO `tsys_user_role` VALUES ('56', '23', '6');
INSERT INTO `tsys_user_role` VALUES ('57', '24', '1');
INSERT INTO `tsys_user_role` VALUES ('58', '24', '6');
