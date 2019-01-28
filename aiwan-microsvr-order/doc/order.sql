/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50519
Source Host           : 127.0.0.1:3306
Source Database       : order

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2019-01-26 18:50:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orderx
-- ----------------------------
DROP TABLE IF EXISTS `orderx`;
CREATE TABLE `orderx` (
  `id` bigint(11) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `totalAmount` decimal(19,5) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderx
-- ----------------------------
INSERT INTO `orderx` VALUES ('1', 'iphone x', '10000.00000', '2019-01-26 18:00:07', 'PAID');
