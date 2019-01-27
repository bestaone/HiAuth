/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50519
Source Host           : 127.0.0.1:3306
Source Database       : user

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2019-01-27 09:16:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
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
  UNIQUE KEY `unique_accessToken` (`accessToken`),
  UNIQUE KEY `unique_bindUser` (`userId`,`type`,`openid`) USING BTREE COMMENT '确定user与第三方账号的绑定唯一性'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', '1', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL,
  `operate` varchar(10) DEFAULT NULL COMMENT '操作类型：GET,POST,PUT',
  `code` varchar(128) DEFAULT NULL COMMENT '资源标识，如果是接口则为url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', 'GET', '/user/me');
INSERT INTO `resource` VALUES ('2', 'GET', '/user/list');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `code` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN', '管理员', null);
INSERT INTO `role` VALUES ('2', 'ROLE_USER', '普通用户', null);

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` bigint(20) NOT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  `resourceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('1', '1', '1');
INSERT INTO `role_resource` VALUES ('2', '1', '2');
INSERT INTO `role_resource` VALUES ('3', '2', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(128) NOT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username` (`username`),
  UNIQUE KEY `unique_tel` (`tel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三', 'admin', '123456', '13712345678', 'UNKNOWN', '2018-12-03 17:57:12');
INSERT INTO `user` VALUES ('2', '李四', 'user', '123456', '13812345678', 'UNKNOWN', '2018-12-03 17:57:12');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');
