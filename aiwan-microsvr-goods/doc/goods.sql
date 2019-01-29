/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50519
Source Host           : 127.0.0.1:3306
Source Database       : goods

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2019-01-29 12:07:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', 'Iphone X 256G', '10001.00', '342', '2019-01-28 09:59:25');
INSERT INTO `goods` VALUES ('2', '华为P20 512G', '9999.00', '231', '2019-01-23 09:59:31');
INSERT INTO `goods` VALUES ('3', '小米9 512G', '5888.00', '343', '2019-01-21 09:59:35');
