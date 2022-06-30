CREATE TABLE `namesrv` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`addr` VARCHAR(300) NULL DEFAULT NULL COMMENT 'namesrv地址' COLLATE 'utf8mb4_bin',
	`code` VARCHAR(50) NULL DEFAULT NULL COMMENT '代码' COLLATE 'utf8mb4_bin',
	`name` VARCHAR(50) NULL DEFAULT NULL COMMENT '名称' COLLATE 'utf8mb4_bin',
	`note` VARCHAR(500) NULL DEFAULT NULL COMMENT '备注' COLLATE 'utf8mb4_bin',
	`status` VARCHAR(50) NULL DEFAULT NULL COMMENT 'lock,unlock' COLLATE 'utf8mb4_bin',
	PRIMARY KEY (`id`) USING BTREE
) COMMENT='namesrv表' COLLATE='utf8mb4_bin' ENGINE=InnoDB ;

-- topic表
CREATE TABLE `topic` (
	`id` INT(11) NOT NULL,
	`name` VARCHAR(80) NOT NULL DEFAULT '' COLLATE 'utf8mb4_bin',
	`queue_num` INT(5) NULL DEFAULT NULL COMMENT '队列个数',
	`broker` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_bin',
	`namesrv` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_bin',
	`app_id` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_bin',
	`app_name` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_bin',
	`group` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_bin',
	`owner` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_bin',
	`note` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_bin',
	PRIMARY KEY (`id`) USING BTREE
)
COMMENT='topic表'
COLLATE='utf8mb4_bin'
ENGINE=InnoDB
;
