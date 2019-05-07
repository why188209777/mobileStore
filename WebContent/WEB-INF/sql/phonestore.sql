/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : phonestore

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-05-07 09:11:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', '浙江省杭州市', '1');
INSERT INTO `address` VALUES ('2', '浙江省舟山市', '1');

-- ----------------------------
-- Table structure for `cart`
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phoneid` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('1', '1', '1', '1');
INSERT INTO `cart` VALUES ('2', '2', '1', '1');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `content` longtext,
  `userid` int(11) DEFAULT NULL,
  `phoneid` varchar(0) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `equipment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemid` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `orderid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', '20190506', '小米', 'ss', '2999.00', '1', '20190506');
INSERT INTO `item` VALUES ('2', '20190506', '小米', 'ss', '1999.00', '4', '20190506');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` varchar(255) NOT NULL,
  `userid` int(11) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `total` double(11,0) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phonenum` varchar(13) DEFAULT '0',
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '201903222', '1', '2019-04-18 00:00:00', '6599', '飒飒', '12344445555', '1');
INSERT INTO `orders` VALUES ('2', '201903211', '1', '2019-05-03 00:00:00', '3463', 'sas', '12344445555', '1');

-- ----------------------------
-- Table structure for `phone`
-- ----------------------------
DROP TABLE IF EXISTS `phone`;
CREATE TABLE `phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phoneid` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `rom` varchar(255) DEFAULT NULL,
  `nettype` varchar(255) DEFAULT NULL,
  `camera` varchar(255) DEFAULT NULL,
  `cpu` varchar(255) DEFAULT NULL,
  `operatingsystem` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of phone
-- ----------------------------
INSERT INTO `phone` VALUES ('1', '20190321', '小米1', '小米', '3000.00', '20', 'sa', '5.15', '红', '6g', '128g', '全网通', '4000w', '高通855', 'miui', '好');
INSERT INTO `phone` VALUES ('2', '20190322', '小米2', '小米', '3000.00', '20', 'sa', '5.15', '红', '6g', '128g', '全网通', '4000w', '高通855', 'miui', '好');
INSERT INTO `phone` VALUES ('3', '20190321', '小米3', '小米', '3000.00', '20', 'sa', '5.15', '红', '6g', '128g', '全网通', '4000w', '高通855', 'miui', '好');
INSERT INTO `phone` VALUES ('4', '20190321', '小米4', '小米', '3000.00', '20', 'sa', '5.15', '红', '6g', '128g', '全网通', '4000w', '高通855', 'miui', '好');
INSERT INTO `phone` VALUES ('5', '20190321', '小米5', '小米', '3000.00', '20', 'sa', '5.15', '红', '6g', '128g', '全网通', '4000w', '高通855', 'miui', '好');
INSERT INTO `phone` VALUES ('6', '20190321', '小米6', '小米', '3000.00', '20', 'sa', '5.15', '红', '6g', '128g', '全网通', '4000w', '高通855', 'miui', '好');
INSERT INTO `phone` VALUES ('7', '20190321', '小米8', '小米', '3000.00', '20', 'sa', '5.15', '红', '6g', '128g', '全网通', '4000w', '高通855', 'miui', '好');
INSERT INTO `phone` VALUES ('8', '20190321', '小米9', '小米', '3000.00', '20', 'sa', '5.15', '红', '6g', '128g', '全网通', '4000w', '高通855', 'miui', '好');
INSERT INTO `phone` VALUES ('9', '20190321', '小米9 se', '小米', '3000.00', '20', 'sa', '5.15', '红', '6g', '128g', '全网通', '4000w', '高通855', 'miui', '好');
INSERT INTO `phone` VALUES ('10', '20190321', '小米1s', '小米', '3000.00', '20', 'sa', '5.15', '红', '6g', '128g', '全网通', '4000w', '高通855', 'miui', '好');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `vip` int(11) DEFAULT '0',
  `guanliyuan` int(11) DEFAULT '0',
  `phonenum` varchar(13) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '小明', '123', '1', '1', '12333334444');
INSERT INTO `user` VALUES ('2', 'sa', '123', '1', '0', '0');
