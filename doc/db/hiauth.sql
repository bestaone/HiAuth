/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50519
Source Host           : 127.0.0.1:3306
Source Database       : hiauth

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2019-03-27 16:47:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails` (
  `appId` varchar(255) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clientdetails
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(2560) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('hiauth_swagger2', '', '$2a$10$1N/.LvTJuYpvxDzoJ1KdvuPDdV/kDSQE9Cxm9BzB1PreyzK6gmFRe', 'AUTH', 'authorization_code', 'http://localhost:8181/hiauth/webjars/springfox-swagger-ui/oauth2-redirect.html,http://hiauth.cn/hiauth/webjars/springfox-swagger-ui/oauth2-redirect.html', 'ROLE_USER', '1800', '86400', '', 'false');
INSERT INTO `oauth_client_details` VALUES ('himall_client_id', '', '$2a$10$1N/.LvTJuYpvxDzoJ1KdvuPDdV/kDSQE9Cxm9BzB1PreyzK6gmFRe', 'AUTH,GOODS,ORDER', 'authorization_code,client_credentials,password,refresh_token', 'http://localhost:8182/himall/callback,http://hiauth.cn/himall/callback', 'ROLE_USER', '1800', '86400', '', 'false');
INSERT INTO `oauth_client_details` VALUES ('umc_client_id', '', '$2a$10$1N/.LvTJuYpvxDzoJ1KdvuPDdV/kDSQE9Cxm9BzB1PreyzK6gmFRe', 'AUTH', 'password', '', 'ROLE_USER', '1800', '86400', '', 'false');

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for sys_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account` (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL COMMENT '管理用户',
  `type` varchar(20) DEFAULT NULL COMMENT '第三方账号类型',
  `openid` int(64) DEFAULT NULL COMMENT '第三方用户表示',
  `accessToken` varchar(100) DEFAULT NULL COMMENT 'accessToken',
  `refreshToken` varchar(100) DEFAULT NULL COMMENT 'refreshToken',
  `expireTime` datetime DEFAULT NULL COMMENT '失效时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_accessToken` (`accessToken`) USING BTREE,
  UNIQUE KEY `unique_bindUser` (`userId`,`type`,`openid`) USING BTREE COMMENT '确定user与第三方账号的绑定唯一性'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_account
-- ----------------------------

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `clientId` varchar(255) NOT NULL COMMENT '关联oauth_client_details主键client_id',
  `name` varchar(20) NOT NULL COMMENT '应用名称',
  `img1X` varchar(100) DEFAULT NULL COMMENT '应用图标一倍图',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `un_clientId` (`clientId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_app
-- ----------------------------
INSERT INTO `sys_app` VALUES ('1', 'umc_client_id', 'HiAuth用户管理中心', 'https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png', '2019-02-05 16:32:36');
INSERT INTO `sys_app` VALUES ('2', 'himall_client_id', '黑猫商城', 'https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png', '2019-03-27 15:39:02');
INSERT INTO `sys_app` VALUES ('3', 'hiauth_swagger2', 'HiAuth Swagger2', 'https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png', '2019-03-27 15:39:02');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL,
  `domain` varchar(20) DEFAULT NULL COMMENT '资源所属域',
  `operate` varchar(10) DEFAULT NULL COMMENT '操作类型：GET,POST,PUT',
  `code` varchar(128) DEFAULT NULL COMMENT '资源标识，如果是接口则为url',
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', 'USER', 'GET', '/user/me', '查询我的信息');
INSERT INTO `sys_resource` VALUES ('2', 'USER', 'GET', '/user/list', '查询用户列表');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
  `code` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN', '管理员', null);
INSERT INTO `sys_role` VALUES ('2', 'ROLE_USER', '普通用户', null);

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` bigint(20) NOT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  `resourceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('1', '1', '1');
INSERT INTO `sys_role_resource` VALUES ('2', '1', '2');
INSERT INTO `sys_role_resource` VALUES ('3', '2', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `avatarUrl` varchar(255) DEFAULT NULL COMMENT '头像',
  `username` varchar(20) NOT NULL,
  `password` varchar(128) NOT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最近一次登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username` (`username`) USING BTREE,
  UNIQUE KEY `unique_tel` (`tel`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '张三', 'https://avatar.csdn.net/7/3/C/3_weixin_39780047.jpg', 'admin', '123456', '13712345678', 'MALE', '2018-12-03 17:57:12', '2019-03-25 15:36:02');
INSERT INTO `sys_user` VALUES ('2', '李四', 'https://avatar.csdnimg.cn/2/2/D/2_ayuanhaoshuai.jpg', 'user', '123456', '13812345678', 'UNKNOWN', '2018-12-03 17:57:12', '2019-03-22 15:36:06');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '1', '2');
