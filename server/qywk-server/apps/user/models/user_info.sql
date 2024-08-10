CREATE TABLE `user_info` (
     `user_id` varchar(32) NOT NULL COMMENT '用户id',
     `password` varchar(255) NOT NULL COMMENT 'md5加密密码',
     `tel` varchar(255) DEFAULT NULL COMMENT '手机号',
     `nickname` varchar(255) NOT NULL COMMENT '用户名',
     `avatar` varchar(255) DEFAULT NULL COMMENT '头像url地址',
     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     `enable` varchar(255) DEFAULT NULL COMMENT '用户状态码',
     PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

SET FOREIGN_KEY_CHECKS = 1;