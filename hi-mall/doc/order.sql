/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50519
Source Host           : 127.0.0.1:3306
Source Database       : order

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2019-03-11 13:14:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orderx
-- ----------------------------
DROP TABLE IF EXISTS `orderx`;
CREATE TABLE `orderx` (
  `id` bigint(11) NOT NULL,
  `no` varchar(50) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `totalAmount` decimal(19,5) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderx
-- ----------------------------
INSERT INTO `orderx` VALUES ('1', '20190101123010001', 'Iphone X (深空灰色，256G) 1台', '10000.00000', '2019-01-26 18:00:07', 'PAID');
