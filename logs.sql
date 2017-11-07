/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Host           : localhost:3306
 Source Schema         : logs

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : 65001

 Date: 27/09/2017 15:47:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` bigint(20) NOT NULL,
  `end_time` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `factor_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `factor_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `factor_unit` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `factor_value` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `start_time` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of record
-- ----------------------------
BEGIN;
INSERT INTO `record` VALUES (1, '2017-09-02', 'height', 'int', 'CM', '11', '2017-09-02', 0);
INSERT INTO `record` VALUES (2, '2017-09-03', 'height', 'int', 'CM', '11', '2017-09-03', 0);
INSERT INTO `record` VALUES (3, '2017-09-04', 'height', 'int', 'CM', '11', '2017-09-04', 0);
INSERT INTO `record` VALUES (4, '2017-09-01', 'height', 'int', 'CM', '11', '2017-09-01', 0);
INSERT INTO `record` VALUES (5, '2017-09-01', 'height', 'int', 'CM', '11', '2017-09-01', 0);
INSERT INTO `record` VALUES (6, '2017-09-01', 'height', 'int', 'CM', '11', '2017-09-01', 0);
INSERT INTO `record` VALUES (7, '2017-09-01', 'height', 'int', 'CM', '11', '2017-09-01', 0);
INSERT INTO `record` VALUES (8, '2017-09-01', 'height', 'int', 'CM', '11', '2017-09-01', 0);
INSERT INTO `record` VALUES (9, '2017-09-01', 'height', 'int', 'CM', '11', '2017-09-01', 0);
INSERT INTO `record` VALUES (10, '2017-09-01', 'height', 'int', 'CM', '11', '2017-09-01', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
