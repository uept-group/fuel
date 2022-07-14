CREATE TABLE `config` (
  `id` int(11) NOT NULL,
  `key` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `value` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `commit` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


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

CREATE TABLE `namesrv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addr` varchar(300) COLLATE utf8mb4_bin DEFAULT '' COMMENT 'namesrv地址,eg:"192.168.1.1:9876"',
  `code` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '代码,eg:dev',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '名称,eg:测试环境',
  `status` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT 'lock,unlock',
  `note` varchar(500) COLLATE utf8mb4_bin DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='namesrv表';

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

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '名称，比如zhangsan',
  `nickname` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称，比如张三',
  `pwd` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '个人邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `broker_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '属性',
  `default_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '默认value',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '备注',
  `sort_no` int(10) NULL DEFAULT 0 COMMENT '排序编号',
  `hot_update` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '是否热更新: yes, no',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_key`(`key`) USING BTREE COMMENT 'key必须唯一约束'
) ENGINE = InnoDB AUTO_INCREMENT = 162 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of broker_config
-- ----------------------------
INSERT INTO `broker_config` VALUES (1, 'serverSelectorThreads', '3', '', 0, '');
INSERT INTO `broker_config` VALUES (2, 'brokerRole', 'ASYNC_MASTER', '', 0, '');
INSERT INTO `broker_config` VALUES (3, 'serverSocketRcvBufSize', '131072', '', 0, '');
INSERT INTO `broker_config` VALUES (4, 'osPageCacheBusyTimeOutMills', '1000', '', 0, '');
INSERT INTO `broker_config` VALUES (5, 'shortPollingTimeMills', '1000', '', 0, '');
INSERT INTO `broker_config` VALUES (6, 'clientSocketRcvBufSize', '131072', '', 0, '');
INSERT INTO `broker_config` VALUES (7, 'clusterTopicEnable', 'true', '', 0, '');
INSERT INTO `broker_config` VALUES (8, 'brokerTopicEnable', 'true', '', 0, '');
INSERT INTO `broker_config` VALUES (9, 'autoCreateTopicEnable', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (10, 'maxErrorRateOfBloomFilter', '20', '', 0, '');
INSERT INTO `broker_config` VALUES (11, 'maxMsgsNumBatch', '64', '', 0, '');
INSERT INTO `broker_config` VALUES (12, 'cleanResourceInterval', '10000', '', 0, '');
INSERT INTO `broker_config` VALUES (13, 'commercialBaseCount', '1', '', 0, '');
INSERT INTO `broker_config` VALUES (14, 'maxTransferCountOnMessageInMemory', '32', '', 0, '');
INSERT INTO `broker_config` VALUES (15, 'brokerFastFailureEnable', 'true', '', 0, '');
INSERT INTO `broker_config` VALUES (16, 'brokerClusterName', 'DefaultCluster', '', 0, '');
INSERT INTO `broker_config` VALUES (17, 'flushDiskType', 'ASYNC_FLUSH', '', 0, '');
INSERT INTO `broker_config` VALUES (18, 'commercialBigCount', '1', '', 0, '');
INSERT INTO `broker_config` VALUES (19, 'mappedFileSizeConsumeQueue', '6000000', '', 0, '');
INSERT INTO `broker_config` VALUES (20, 'consumerFallbehindThreshold', '17179869184', '', 0, '');
INSERT INTO `broker_config` VALUES (21, 'autoCreateSubscriptionGroup', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (22, 'transientStorePoolEnable', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (23, 'flushConsumerOffsetInterval', '5000', '', 0, '');
INSERT INTO `broker_config` VALUES (24, 'waitTimeMillsInHeartbeatQueue', '31000', '', 0, '');
INSERT INTO `broker_config` VALUES (25, 'diskMaxUsedSpaceRatio', '75', '', 0, '');
INSERT INTO `broker_config` VALUES (26, 'cleanFileForciblyEnable', 'true', '', 0, '');
INSERT INTO `broker_config` VALUES (27, 'slaveReadEnable', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (28, 'flushCommitLogLeastPages', '4', '', 0, '');
INSERT INTO `broker_config` VALUES (29, 'msgTraceTopicName', 'RMQ_SYS_TRACE_TOPIC', '', 0, '');
INSERT INTO `broker_config` VALUES (30, 'expectConsumerNumUseFilter', '32', '', 0, '');
INSERT INTO `broker_config` VALUES (31, 'traceTopicEnable', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (32, 'useEpollNativeSelector', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (33, 'enablePropertyFilter', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (34, 'messageDelayLevel', '1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h', '', 0, '');
INSERT INTO `broker_config` VALUES (35, 'deleteCommitLogFilesInterval', '100', '', 0, '');
INSERT INTO `broker_config` VALUES (36, 'brokerName', 'broker-b', '', 0, '');
INSERT INTO `broker_config` VALUES (37, 'maxTransferBytesOnMessageInDisk', '65536', '', 0, '');
INSERT INTO `broker_config` VALUES (38, 'listenPort', '12911', '', 0, '');
INSERT INTO `broker_config` VALUES (39, 'flushConsumeQueueLeastPages', '2', '', 0, '');
INSERT INTO `broker_config` VALUES (40, 'pullMessageThreadPoolNums', '20', '', 0, '');
INSERT INTO `broker_config` VALUES (41, 'useReentrantLockWhenPutMessage', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (42, 'flushIntervalConsumeQueue', '1000', '', 0, '');
INSERT INTO `broker_config` VALUES (43, 'sendThreadPoolQueueCapacity', '10000', '', 0, '');
INSERT INTO `broker_config` VALUES (44, 'debugLockEnable', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (45, 'haHousekeepingInterval', '20000', '', 0, '');
INSERT INTO `broker_config` VALUES (46, 'diskFallRecorded', 'true', '', 0, '');
INSERT INTO `broker_config` VALUES (47, 'messageIndexEnable', 'true', '', 0, '');
INSERT INTO `broker_config` VALUES (48, 'clientAsyncSemaphoreValue', '65535', '', 0, '');
INSERT INTO `broker_config` VALUES (49, 'clientCallbackExecutorThreads', '2', '', 0, '');
INSERT INTO `broker_config` VALUES (50, 'putMsgIndexHightWater', '600000', '', 0, '');
INSERT INTO `broker_config` VALUES (51, 'sendMessageThreadPoolNums', '1', '', 0, '');
INSERT INTO `broker_config` VALUES (52, 'clientManagerThreadPoolQueueCapacity', '1000000', '', 0, '');
INSERT INTO `broker_config` VALUES (53, 'serverSocketSndBufSize', '131072', '', 0, '');
INSERT INTO `broker_config` VALUES (54, 'maxDelayTime', '40', '', 0, '');
INSERT INTO `broker_config` VALUES (55, 'clientSocketSndBufSize', '131072', '', 0, '');
INSERT INTO `broker_config` VALUES (56, 'namesrvAddr', '127.0.0.1:9876', '', 0, '');
INSERT INTO `broker_config` VALUES (57, 'commercialEnable', 'true', '', 0, '');
INSERT INTO `broker_config` VALUES (58, 'maxHashSlotNum', '5000000', '', 0, '');
INSERT INTO `broker_config` VALUES (59, 'heartbeatThreadPoolNums', '2', '', 0, '');
INSERT INTO `broker_config` VALUES (60, 'transactionTimeOut', '6000', '', 0, '');
INSERT INTO `broker_config` VALUES (61, 'maxMessageSize', '4194304', '', 0, '');
INSERT INTO `broker_config` VALUES (62, 'adminBrokerThreadPoolNums', '16', '', 0, '');
INSERT INTO `broker_config` VALUES (63, 'defaultQueryMaxNum', '32', '', 0, '');
INSERT INTO `broker_config` VALUES (64, 'forceRegister', 'true', '', 0, '');
INSERT INTO `broker_config` VALUES (65, 'maxTransferBytesOnMessageInMemory', '262144', '', 0, '');
INSERT INTO `broker_config` VALUES (66, 'enableConsumeQueueExt', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (67, 'longPollingEnable', 'true', '', 0, '');
INSERT INTO `broker_config` VALUES (68, 'serverWorkerThreads', '8', '', 0, '');
INSERT INTO `broker_config` VALUES (69, 'messageIndexSafe', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (70, 'deleteConsumeQueueFilesInterval', '100', '', 0, '');
INSERT INTO `broker_config` VALUES (71, 'haSlaveFallbehindMax', '268435456', '', 0, '');
INSERT INTO `broker_config` VALUES (72, 'serverCallbackExecutorThreads', '0', '', 0, '');
INSERT INTO `broker_config` VALUES (73, 'flushCommitLogThoroughInterval', '10000', '', 0, '');
INSERT INTO `broker_config` VALUES (74, 'commercialTimerCount', '1', '', 0, '');
INSERT INTO `broker_config` VALUES (75, 'enableDLegerCommitLog', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (76, 'useTLS', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (77, 'redeleteHangedFileInterval', '120000', '', 0, '');
INSERT INTO `broker_config` VALUES (78, 'flushIntervalCommitLog', '500', '', 0, '');
INSERT INTO `broker_config` VALUES (79, 'rocketmqHome', '/data/rocketmq/b/rocketmq-all-4.7.1-bin-release', '', 0, '');
INSERT INTO `broker_config` VALUES (80, 'queryMessageThreadPoolNums', '10', '', 0, '');
INSERT INTO `broker_config` VALUES (81, 'messageStorePlugIn', '', '', 0, '');
INSERT INTO `broker_config` VALUES (82, 'serverChannelMaxIdleTimeSeconds', '120', '', 0, '');
INSERT INTO `broker_config` VALUES (83, 'maxIndexNum', '20000000', '', 0, '');
INSERT INTO `broker_config` VALUES (84, 'filterDataCleanTimeSpan', '86400000', '', 0, '');
INSERT INTO `broker_config` VALUES (85, 'filterServerNums', '0', '', 0, '');
INSERT INTO `broker_config` VALUES (86, 'commitCommitLogLeastPages', '4', '', 0, '');
INSERT INTO `broker_config` VALUES (87, 'waitTimeMillsInPullQueue', '5000', '', 0, '');
INSERT INTO `broker_config` VALUES (88, 'haSendHeartbeatInterval', '5000', '', 0, '');
INSERT INTO `broker_config` VALUES (89, 'processReplyMessageThreadPoolNums', '20', '', 0, '');
INSERT INTO `broker_config` VALUES (90, 'clientChannelMaxIdleTimeSeconds', '120', '', 0, '');
INSERT INTO `broker_config` VALUES (91, 'filterSupportRetry', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (92, 'flushDelayOffsetInterval', '10000', '', 0, '');
INSERT INTO `broker_config` VALUES (93, 'duplicationEnable', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (94, 'replyThreadPoolQueueCapacity', '10000', '', 0, '');
INSERT INTO `broker_config` VALUES (95, 'offsetCheckInSlave', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (96, 'clientCloseSocketIfTimeout', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (97, 'transientStorePoolSize', '5', '', 0, '');
INSERT INTO `broker_config` VALUES (98, 'waitTimeMillsInSendQueue', '200', '', 0, '');
INSERT INTO `broker_config` VALUES (99, 'warmMapedFileEnable', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (100, 'endTransactionThreadPoolNums', '12', '', 0, '');
INSERT INTO `broker_config` VALUES (101, 'flushCommitLogTimed', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (102, 'flushLeastPagesWhenWarmMapedFile', '4096', '', 0, '');
INSERT INTO `broker_config` VALUES (103, 'clientWorkerThreads', '4', '', 0, '');
INSERT INTO `broker_config` VALUES (104, 'endTransactionPoolQueueCapacity', '100000', '', 0, '');
INSERT INTO `broker_config` VALUES (105, 'registerNameServerPeriod', '30000', '', 0, '');
INSERT INTO `broker_config` VALUES (106, 'registerBrokerTimeoutMills', '6000', '', 0, '');
INSERT INTO `broker_config` VALUES (107, 'accessMessageInMemoryMaxRatio', '40', '', 0, '');
INSERT INTO `broker_config` VALUES (108, 'highSpeedMode', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (109, 'transactionCheckMax', '15', '', 0, '');
INSERT INTO `broker_config` VALUES (110, 'checkCRCOnRecover', 'true', '', 0, '');
INSERT INTO `broker_config` VALUES (111, 'destroyMapedFileIntervalForcibly', '120000', '', 0, '');
INSERT INTO `broker_config` VALUES (112, 'brokerIP2', '150.158.87.249', '', 0, '');
INSERT INTO `broker_config` VALUES (113, 'brokerIP1', '150.158.87.249', '', 0, '');
INSERT INTO `broker_config` VALUES (114, 'commitIntervalCommitLog', '200', '', 0, '');
INSERT INTO `broker_config` VALUES (115, 'clientOnewaySemaphoreValue', '65535', '', 0, '');
INSERT INTO `broker_config` VALUES (116, 'storeReplyMessageEnable', 'true', '', 0, '');
INSERT INTO `broker_config` VALUES (117, 'traceOn', 'true', '', 0, '');
INSERT INTO `broker_config` VALUES (118, 'clientManageThreadPoolNums', '32', '', 0, '');
INSERT INTO `broker_config` VALUES (119, 'channelNotActiveInterval', '60000', '', 0, '');
INSERT INTO `broker_config` VALUES (120, 'mappedFileSizeConsumeQueueExt', '50331648', '', 0, '');
INSERT INTO `broker_config` VALUES (121, 'consumerManagerThreadPoolQueueCapacity', '1000000', '', 0, '');
INSERT INTO `broker_config` VALUES (122, 'serverOnewaySemaphoreValue', '256', '', 0, '');
INSERT INTO `broker_config` VALUES (123, 'haListenPort', '12912', '', 0, '');
INSERT INTO `broker_config` VALUES (124, 'enableCalcFilterBitMap', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (125, 'clientPooledByteBufAllocatorEnable', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (126, 'aclEnable', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (127, 'storePathRootDir', './rocketmq/store', '', 0, '');
INSERT INTO `broker_config` VALUES (128, 'syncFlushTimeout', '5000', '', 0, '');
INSERT INTO `broker_config` VALUES (129, 'rejectTransactionMessage', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (130, 'commitCommitLogThoroughInterval', '200', '', 0, '');
INSERT INTO `broker_config` VALUES (131, 'connectTimeoutMillis', '3000', '', 0, '');
INSERT INTO `broker_config` VALUES (132, 'queryThreadPoolQueueCapacity', '20000', '', 0, '');
INSERT INTO `broker_config` VALUES (133, 'regionId', 'DefaultRegion', '', 0, '');
INSERT INTO `broker_config` VALUES (134, 'consumerManageThreadPoolNums', '32', '', 0, '');
INSERT INTO `broker_config` VALUES (135, 'disableConsumeIfConsumerReadSlowly', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (136, 'flushConsumerOffsetHistoryInterval', '60000', '', 0, '');
INSERT INTO `broker_config` VALUES (137, 'fetchNamesrvAddrByAddressServer', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (138, 'haTransferBatchSize', '32768', '', 0, '');
INSERT INTO `broker_config` VALUES (139, 'compressedRegister', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (140, 'storePathCommitLog', '/root/store/commitlog', '', 0, '');
INSERT INTO `broker_config` VALUES (141, 'commercialTransCount', '1', '', 0, '');
INSERT INTO `broker_config` VALUES (142, 'transactionCheckInterval', '60000', '', 0, '');
INSERT INTO `broker_config` VALUES (143, 'mappedFileSizeCommitLog', '1073741824', '', 0, '');
INSERT INTO `broker_config` VALUES (144, 'startAcceptSendRequestTimeStamp', '0', '', 0, '');
INSERT INTO `broker_config` VALUES (145, 'serverPooledByteBufAllocatorEnable', 'true', '', 0, '');
INSERT INTO `broker_config` VALUES (146, 'serverAsyncSemaphoreValue', '64', '', 0, '');
INSERT INTO `broker_config` VALUES (147, 'heartbeatThreadPoolQueueCapacity', '50000', '', 0, '');
INSERT INTO `broker_config` VALUES (148, 'waitTimeMillsInTransactionQueue', '3000', '', 0, '');
INSERT INTO `broker_config` VALUES (149, 'autoDeleteUnusedStats', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (150, 'deleteWhen', '04', '', 0, '');
INSERT INTO `broker_config` VALUES (151, 'bitMapLengthConsumeQueueExt', '112', '', 0, '');
INSERT INTO `broker_config` VALUES (152, 'fastFailIfNoBufferInStorePool', 'false', '', 0, '');
INSERT INTO `broker_config` VALUES (153, 'defaultTopicQueueNums', '8', '', 0, '');
INSERT INTO `broker_config` VALUES (154, 'notifyConsumerIdsChangedEnable', 'true', '', 0, '');
INSERT INTO `broker_config` VALUES (155, 'flushConsumeQueueThoroughInterval', '60000', '', 0, '');
INSERT INTO `broker_config` VALUES (156, 'fileReservedTime', '48', '', 0, '');
INSERT INTO `broker_config` VALUES (157, 'brokerPermission', '6', '', 0, '');
INSERT INTO `broker_config` VALUES (158, 'transferMsgByHeap', 'true', '', 0, '');
INSERT INTO `broker_config` VALUES (159, 'pullThreadPoolQueueCapacity', '100000', '', 0, '');
INSERT INTO `broker_config` VALUES (160, 'brokerId', '0', '', 0, '');
INSERT INTO `broker_config` VALUES (161, 'maxTransferCountOnMessageInDisk', '8', '', 0, '');