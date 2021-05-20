/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.5
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : files

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 04/03/2021 20:44:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_files
-- ----------------------------
DROP TABLE IF EXISTS `t_files`;
CREATE TABLE `t_files`  (
  `id` int NOT NULL,
  `oldFileName` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `newFileName` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ext` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `size` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isImg` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `documents` int NULL DEFAULT NULL,
  `uploadTime` datetime(0) NULL DEFAULT NULL,
  `userid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_files
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
