/*
 Navicat Premium Data Transfer

 Source Server         : dbserver
 Source Server Type    : MySQL
 Source Server Version : 50568 (5.5.68-MariaDB)
 Source Host           : 192.168.40.129:3306
 Source Schema         : ordersystem

 Target Server Type    : MySQL
 Target Server Version : 50568 (5.5.68-MariaDB)
 File Encoding         : 65001

 Date: 03/09/2024 00:23:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cartdetail
-- ----------------------------
DROP TABLE IF EXISTS `cartdetail`;
CREATE TABLE `cartdetail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cart_id` int(11) NULL DEFAULT NULL,
  `dishes_id` int(11) NULL DEFAULT NULL,
  `dishes_count` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of cartdetail
-- ----------------------------

-- ----------------------------
-- Table structure for carts
-- ----------------------------
DROP TABLE IF EXISTS `carts`;
CREATE TABLE `carts`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of carts
-- ----------------------------
INSERT INTO `carts` VALUES (1, 3);
INSERT INTO `carts` VALUES (4, 4);

-- ----------------------------
-- Table structure for dishes
-- ----------------------------
DROP TABLE IF EXISTS `dishes`;
CREATE TABLE `dishes`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dishes_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `sales` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recommend` int(11) NULL DEFAULT NULL,
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `category_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dishes
-- ----------------------------
INSERT INTO `dishes` VALUES (1, '仰望星空', 100, 0, '哈哈', 1, NULL, 1, 1);
INSERT INTO `dishes` VALUES (2, '汉堡', 30.5, 0, '哈哈', 1, NULL, 1, 2);
INSERT INTO `dishes` VALUES (3, 'test', 0, 0, 'test', 1, NULL, 1, 3);
INSERT INTO `dishes` VALUES (4, '可乐', 3.5, 0, '哈哈', 2, NULL, 1, 1);
INSERT INTO `dishes` VALUES (5, '水', 2, 0, '哈哈', 3, NULL, 1, 1);

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NULL DEFAULT NULL,
  `dishes_id` int(11) NULL DEFAULT NULL,
  `dishes_count` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------
INSERT INTO `orderdetail` VALUES (1, 1, 1, 1);
INSERT INTO `orderdetail` VALUES (2, 1, 2, 1);
INSERT INTO `orderdetail` VALUES (3, 1, 4, 3);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `order_time` datetime NULL DEFAULT NULL,
  `total_price` double NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 4, 0, '2024-09-02 00:28:06', 141, NULL);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin` int(11) NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'hhh', '123456', 1, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (2, 'sss', '123456', 1, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (3, 'mhy', '123456', 1, NULL, 'null', '110', 1);
INSERT INTO `users` VALUES (4, 'aaa', '123456', 1, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (5, 'bbb', '123456', 1, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (6, 'ccc', '123456', 1, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (7, 'lwq', '123456', 1, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (8, 'tyj', '123456', 0, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (9, 'zzz', '123456', 0, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (10, 'why', '123456', 0, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
