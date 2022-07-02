CREATE TABLE `namesrv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addr` varchar(300) COLLATE utf8mb4_bin DEFAULT '' COMMENT 'namesrv地址,eg:"192.168.1.1:9876"',
  `code` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '代码,eg:dev',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '名称,eg:测试环境',
  `status` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT 'lock,unlock',
  `note` varchar(500) COLLATE utf8mb4_bin DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='namesrv表';

-- topic表
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) COLLATE utf8mb4_bin DEFAULT '' COMMENT '名称',
  `queue_num` int(5) DEFAULT '0' COMMENT '队列个数',
  `broker` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '逗号分割',
  `namesrv` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT 'namesrv地址',
  `app_id` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '应用id',
  `app_name` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '应用名称',
  `department` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '部门',
  `owner` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '负责人逗号分割',
  `note` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='topic表';

-- consumer表
CREATE TABLE `consumer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_bin DEFAULT '' COMMENT '名称',
  `app_id` varchar(300) COLLATE utf8mb4_bin DEFAULT '' COMMENT '应用id',
  `app_name` varchar(11) COLLATE utf8mb4_bin DEFAULT '' COMMENT '应用名称',
  `topic` varchar(11) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '订阅的topic',
  `broadcast_enable` int(11) DEFAULT '0' COMMENT '是否开启广播模式',
  `broker_id` int(11) DEFAULT '0' COMMENT 'broier_id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='消费组';