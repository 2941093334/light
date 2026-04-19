/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80039
 Source Host           : localhost:3306
 Source Schema         : light

 Target Server Type    : MySQL
 Target Server Version : 80039
 File Encoding         : 65001

 Date: 19/04/2026 14:43:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lamp
-- ----------------------------
DROP TABLE IF EXISTS `lamp`;
CREATE TABLE `lamp`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `brand` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌',
  `model` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '型号',
  `power` double NOT NULL COMMENT '功率（瓦特）',
  `color_temperature` int NOT NULL COMMENT '色温（开尔文）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lamp
-- ----------------------------
INSERT INTO `lamp` VALUES (1, '飞利浦', 'HPL-01', 16, 3001);
INSERT INTO `lamp` VALUES (2, '欧司朗', 'OSL-10', 25, 4000);
INSERT INTO `lamp` VALUES (3, '松下', 'PH-200', 40, 5000);
INSERT INTO `lamp` VALUES (4, '雷士', 'NS-52', 60, 6000);
INSERT INTO `lamp` VALUES (5, '亿光', 'EG-110', 30, 3500);
INSERT INTO `lamp` VALUES (6, '三雄极光', 'SXJ-36', 50, 4500);
INSERT INTO `lamp` VALUES (7, '长虹', 'CH-04', 10, 2700);
INSERT INTO `lamp` VALUES (8, '澳克士', 'OKS-77', 80, 5500);
INSERT INTO `lamp` VALUES (12, '测试1', 'HPL-01', 5, 3000);

SET FOREIGN_KEY_CHECKS = 1;
