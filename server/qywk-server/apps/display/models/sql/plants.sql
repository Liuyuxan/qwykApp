/*
 Navicat Premium Dump SQL

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30-txsql)
 Source Host           : cd-cdb-ewbf764s.sql.tencentcdb.com:23527
 Source Schema         : qywk_display

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30-txsql)
 File Encoding         : 65001

 Date: 13/08/2024 18:02:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for plants
-- ----------------------------
DROP TABLE IF EXISTS `plants`;
CREATE TABLE `plants` (
  `id` varchar(255) NOT NULL COMMENT '主键id',
  `name` varchar(255) NOT NULL COMMENT '植物名称',
  `url` varchar(255) NOT NULL COMMENT '图片url',
  `tag` varchar(255) NOT NULL COMMENT '标签',
  `desc` varchar(255) NOT NULL COMMENT '描述信息',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` varchar(255) NOT NULL COMMENT '激活状态',
  PRIMARY KEY (` id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

SET FOREIGN_KEY_CHECKS = 1;
