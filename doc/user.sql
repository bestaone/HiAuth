/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50519
Source Host           : 127.0.0.1:3306
Source Database       : user

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2018-12-28 14:16:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', '1');

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

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三', 'zhangsan', '123456', '13788888881', 'UNKNOWN', '2018-12-03 17:57:12');
INSERT INTO `user` VALUES ('2', '李四', 'lisi', '123456', '13788888882', 'UNKNOWN', '2018-12-03 17:57:12');
INSERT INTO `user` VALUES ('3', '王五', 'wangwu', '123456', '13788888883', 'UNKNOWN', '2018-12-04 17:57:32');
