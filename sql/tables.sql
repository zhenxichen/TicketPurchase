-- -----------------
-- 建立用户信息补充表
-- -----------------
CREATE TABLE `user_info` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `name` varchar(32) DEFAULT '' COMMENT '用户真名',
  `id_card` varchar(32) DEFAULT '' COMMENT '用户身份证号',
  `open_id` varchar(64) DEFAULT '' COMMENT '用户绑定的微信openid',
  `balance` int(11) NOT NULL DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息补充表'
-- -----------------
-- 填充用户信息补充表
-- -----------------
INSERT INTO `user_info` (`user_id`, `name`, `id_card`, `open_id`) VALUES ('1', '', '', '')

INSERT INTO `user_info` (`user_id`, `name`, `id_card`, `open_id`) VALUES ('2', '', '', '')

INSERT INTO `user_info` (`user_id`, `name`, `id_card`, `open_id`) VALUES ('100', '', '', '')
-- -------------------
-- 建立车站表
-- -------------------
CREATE TABLE `station` (
  `station_id` int(11) NOT NULL COMMENT '车站ID',
  `station_name` varchar(64) NOT NULL DEFAULT '' COMMENT '车站名称',
  PRIMARY KEY (`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车站表'
-- ---------------------
-- 建立车次表
-- ---------------------
CREATE TABLE `bus` (
  `bus_id` varchar(20) NOT NULL DEFAULT '' COMMENT '车次',
  `start` int(11) NOT NULL COMMENT '始发站编号',
  `dest` int(11) NOT NULL COMMENT '终点站编号',
  `start_time` time NOT NULL COMMENT '发车时间',
  `end_time` time NOT NULL COMMENT '到达时间',
  `day` int(11) NOT NULL DEFAULT '0' COMMENT '间隔天数',
  `seat` int(11) DEFAULT NULL COMMENT '默认座位数',
  PRIMARY KEY (`bus_id`),
  KEY `start_station` (`start`),
  KEY `dest_station` (`dest`),
  CONSTRAINT `dest_station` FOREIGN KEY (`dest`) REFERENCES `station` (`station_id`),
  CONSTRAINT `start_station` FOREIGN KEY (`start`) REFERENCES `station` (`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车次表'
-- ------------------
-- 建立车次日历表
-- ------------------
CREATE TABLE `tickets` (
  `bus_id` varchar(20) NOT NULL DEFAULT '' COMMENT '车次',
  `bus_date` date NOT NULL COMMENT '发车日期',
  `seat` int(11) NOT NULL COMMENT '座位数',
  `employee_tickets` int(11) NOT NULL COMMENT '员工票数量',
  `normal_tickets` int(11) NOT NULL COMMENT '普通票数量',
  `employee_tickets_remain` int(11) NOT NULL COMMENT '剩余员工票数量',
  `normal_tickets_remain` int(11) NOT NULL COMMENT '剩余普通票数量',
  `employee_price` int(11) NOT NULL COMMENT '员工票价格',
  `normal_price` int(11) NOT NULL COMMENT '普通票价格',
  PRIMARY KEY (`bus_id`,`bus_date`),
  CONSTRAINT `bus_restrict` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`bus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车票表'
-- -----------------
-- 建立订单表
-- -----------------
CREATE TABLE `orders` (
  `order_id` varchar(64) NOT NULL COMMENT '订单号',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `status` char(1) NOT NULL DEFAULT '' COMMENT '订单状态（0待支付 1待核销 2已完成 3已关闭）',
  `bus` varchar(20) NOT NULL DEFAULT '' COMMENT '车次',
  `date` date NOT NULL COMMENT '日期',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `verify_time` datetime DEFAULT NULL COMMENT '核销时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表'
