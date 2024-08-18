SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for plant_user
-- ----------------------------
DROP TABLE IF EXISTS `plant_user`;
CREATE TABLE `plant_user` (
  `user_id` varchar(255) NOT NULL,
  `plant_id` varchar(255) NOT NULL,
  UNIQUE KEY `user_id&plant_id` (`user_id`,`plant_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

SET FOREIGN_KEY_CHECKS = 1;
