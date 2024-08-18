

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for meal
-- ----------------------------
DROP TABLE IF EXISTS `meal`;
CREATE TABLE `meal` (
  `id` varchar(255) NOT NULL COMMENT '主键id',
  `subarea` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '分区',
  `subarea_url` varchar(255) DEFAULT NULL COMMENT '分区图片',
  `subtitle` varchar(255) NOT NULL COMMENT '副标题',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `photos` varchar(255) NOT NULL COMMENT '图片集',
  `desc` varchar(255) NOT NULL COMMENT '描述内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` varchar(255) NOT NULL COMMENT '激活状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

SET FOREIGN_KEY_CHECKS = 1;
