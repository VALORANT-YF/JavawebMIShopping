/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : db1

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 11/11/2023 12:02:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_brand
-- ----------------------------
DROP TABLE IF EXISTS `tb_brand`;
CREATE TABLE `tb_brand`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `brandName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `companyName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ordered` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `imageUrl` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `flag` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `brandName`(`brandName` ASC, `companyName` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_brand
-- ----------------------------

-- ----------------------------
-- Table structure for tb_dept
-- ----------------------------
DROP TABLE IF EXISTS `tb_dept`;
CREATE TABLE `tb_dept`  (
  `numbering` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dept_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`numbering`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_dept
-- ----------------------------
INSERT INTO `tb_dept` VALUES ('101', '行政部');
INSERT INTO `tb_dept` VALUES ('102', '采购部');

-- ----------------------------
-- Table structure for tb_engineer
-- ----------------------------
DROP TABLE IF EXISTS `tb_engineer`;
CREATE TABLE `tb_engineer`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `workNumber` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `workNumber`(`workNumber` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_engineer
-- ----------------------------
INSERT INTO `tb_engineer` VALUES (1, '20210344117', '123');
INSERT INTO `tb_engineer` VALUES (2, '20210344116', '123');

-- ----------------------------
-- Table structure for tb_engineerinformation
-- ----------------------------
DROP TABLE IF EXISTS `tb_engineerinformation`;
CREATE TABLE `tb_engineerinformation`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `engineerName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `onboarding` date NULL DEFAULT NULL,
  `department` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `department`(`department` ASC) USING BTREE,
  CONSTRAINT `department` FOREIGN KEY (`department`) REFERENCES `tb_dept` (`numbering`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `tb_engineer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_engineerinformation
-- ----------------------------
INSERT INTO `tb_engineerinformation` VALUES (1, '张大飞', '河南省南阳市', '18836251380', '2023-04-30', '101');
INSERT INTO `tb_engineerinformation` VALUES (2, '张二飞', '河南省南阳市', '17884712216', '2023-05-01', '102');

-- ----------------------------
-- Table structure for tb_nav
-- ----------------------------
DROP TABLE IF EXISTS `tb_nav`;
CREATE TABLE `tb_nav`  (
  `nav_test` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `brand_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_nav
-- ----------------------------
INSERT INTO `tb_nav` VALUES ('测试1', '小米', '111', 'd', 1);
INSERT INTO `tb_nav` VALUES ('测试3', '华为', '2222', 'd', 3);
INSERT INTO `tb_nav` VALUES ('er', 'er', '12123', 'ear', 4);
INSERT INTO `tb_nav` VALUES ('1234', '342', '234', '123d', 5);
INSERT INTO `tb_nav` VALUES ('sdf', 'ds', 'dsf', 'd', 6);

-- ----------------------------
-- Table structure for tb_shopping
-- ----------------------------
DROP TABLE IF EXISTS `tb_shopping`;
CREATE TABLE `tb_shopping`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `addressee` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `brandName` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `brandImg` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `receiptAddress` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `shoppingAccount` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `brandNumber` int NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `shoppingAccount`(`shoppingAccount` ASC) USING BTREE,
  CONSTRAINT `tb_shopping_ibfk_1` FOREIGN KEY (`shoppingAccount`) REFERENCES `tb_userinformation` (`account`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_shopping
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (2, 'lisi', '12345');
INSERT INTO `tb_user` VALUES (6, 'Sugarcane', 'zz5211314');
INSERT INTO `tb_user` VALUES (9, 'erer', '111');
INSERT INTO `tb_user` VALUES (10, 'eee', '111');
INSERT INTO `tb_user` VALUES (11, 'rrr', '111');
INSERT INTO `tb_user` VALUES (12, 'zhangsan', 'zhangsan123');
INSERT INTO `tb_user` VALUES (15, 'lisi2222', '111');

-- ----------------------------
-- Table structure for tb_userinformation
-- ----------------------------
DROP TABLE IF EXISTS `tb_userinformation`;
CREATE TABLE `tb_userinformation`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `avatarPath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` int NULL DEFAULT NULL,
  `account` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `account`(`account` ASC) USING BTREE,
  CONSTRAINT `tb_userinformation_ibfk_1` FOREIGN KEY (`account`) REFERENCES `tb_user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_userinformation
-- ----------------------------
INSERT INTO `tb_userinformation` VALUES (2, '7d45d16e-2d60-4a27-9158-656c433efefe.jpg', '李四', 0, 'lisi', '中国');
INSERT INTO `tb_userinformation` VALUES (6, NULL, NULL, NULL, 'Sugarcane', NULL);
INSERT INTO `tb_userinformation` VALUES (9, 'upload/images/5136fc55-6f84-43c0-9122-28016f18fa86.jpg', '测试1', 0, 'erer', '中国');
INSERT INTO `tb_userinformation` VALUES (10, 'upload/images/f08b68c4-8da1-47c0-910f-5fea126919bb.png', 'sdaf ', 0, 'eee', '中国');
INSERT INTO `tb_userinformation` VALUES (11, 'upload/images/79dc1429-6ed8-4c86-b5ff-e329358a2ccd.jpg', 'dsaf ', 0, 'rrr', '外国');
INSERT INTO `tb_userinformation` VALUES (12, 'upload/images/3e488bf7-7fdd-4a98-bb20-8e4129128c63.jpg', '张三', 1, 'zhangsan', '中国');
INSERT INTO `tb_userinformation` VALUES (15, NULL, NULL, NULL, 'lisi2222', NULL);

SET FOREIGN_KEY_CHECKS = 1;
