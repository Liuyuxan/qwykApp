

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for meal_user
-- ----------------------------
DROP TABLE IF EXISTS `meal_user`;
CREATE TABLE `meal_user` (
  `meal_id` varchar(255) NOT NULL COMMENT '膳食id',
  `user_id` varchar(255) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`meal_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

SET FOREIGN_KEY_CHECKS = 1;
