/*
 Navicat Premium Data Transfer

 Source Server         : luna-local
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : csi-restaurant

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 20/06/2021 11:42:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_app
-- ----------------------------
DROP TABLE IF EXISTS `tb_app`;
CREATE TABLE `tb_app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(32) NOT NULL COMMENT '应用编号',
  `api_key` varchar(32) NOT NULL COMMENT '应用key',
  `secret_key` varchar(32) NOT NULL COMMENT '应用密钥',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) unsigned NOT NULL COMMENT '锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='百度ApiKey';

-- ----------------------------
-- Records of tb_app
-- ----------------------------
BEGIN;
INSERT INTO `tb_app` VALUES (1, '15436824', '6kkP0eWEu5rGss7IwW0DQr8s', 'jz0XbDKln7DO2Bep5c9rzDVtFgq3Xjq9', '2021-05-27 14:27:57', '2021-05-27 14:27:59', 0);
INSERT INTO `tb_app` VALUES (2, '15949958', 'fDCBonu1DWRf8FQUt6VrnqER', '561cLBZsXcmSGL7tvjqz4GZkGb8rCpjk', '2021-06-17 17:38:48', '2021-06-17 17:38:50', 0);
COMMIT;

-- ----------------------------
-- Table structure for tb_cart
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE `tb_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物车',
  `user_id` bigint(20) NOT NULL COMMENT '用户编号',
  `meal_id` bigint(20) NOT NULL COMMENT '商品编号',
  `count` int(11) NOT NULL COMMENT '数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) unsigned NOT NULL COMMENT '锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品购物车';

-- ----------------------------
-- Records of tb_cart
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_meal
-- ----------------------------
DROP TABLE IF EXISTS `tb_meal`;
CREATE TABLE `tb_meal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '食物编号',
  `series_id` bigint(20) NOT NULL COMMENT '菜系编号',
  `meal_name` varchar(20) NOT NULL DEFAULT '' COMMENT '食物名称',
  `summarize` varchar(255) DEFAULT NULL COMMENT '食物摘要',
  `description` varchar(1023) DEFAULT '' COMMENT '食物描述',
  `meal_price` decimal(8,2) DEFAULT NULL COMMENT '物品价格',
  `image` varchar(250) DEFAULT NULL COMMENT '物品图片',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) unsigned NOT NULL COMMENT '锁',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `mealSeriesId` (`series_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='餐品信息表';

-- ----------------------------
-- Records of tb_meal
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_meal_series
-- ----------------------------
DROP TABLE IF EXISTS `tb_meal_series`;
CREATE TABLE `tb_meal_series` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜系编号',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `series_name` varchar(10) DEFAULT NULL COMMENT '菜系名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) unsigned NOT NULL COMMENT '锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='菜系表';

-- ----------------------------
-- Records of tb_meal_series
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `order_time` datetime DEFAULT NULL COMMENT '订单时间',
  `order_state` int(1) DEFAULT NULL COMMENT '订单状态',
  `order_price` decimal(8,2) DEFAULT NULL COMMENT '订单价格',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) unsigned NOT NULL COMMENT '锁',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `UserId` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='订单主表';

-- ----------------------------
-- Records of tb_order
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_order_meal
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_meal`;
CREATE TABLE `tb_order_meal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单详细表',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单编号',
  `meal_id` bigint(20) DEFAULT NULL COMMENT '物品编号',
  `meal_price` decimal(8,2) DEFAULT NULL COMMENT '物品数量x单价',
  `meal_count` int(11) DEFAULT NULL COMMENT '物品数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) unsigned NOT NULL COMMENT '锁',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `OID` (`order_id`),
  KEY `MealId` (`meal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_meal
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(20) NOT NULL COMMENT '登陆名称',
  `password` varchar(32) NOT NULL COMMENT '登陆密码',
  `real_name` varchar(20) NOT NULL COMMENT '真实名称',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `phone` varchar(20) NOT NULL COMMENT '手机',
  `address` varchar(50) NOT NULL COMMENT '收货地址',
  `face_path` varchar(255) DEFAULT NULL COMMENT '人脸地址',
  `face_data` varchar(255) DEFAULT NULL COMMENT '人脸key',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  `version` int(11) unsigned NOT NULL COMMENT '锁',
  `admin` char(1) NOT NULL DEFAULT '1' COMMENT '管理员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
