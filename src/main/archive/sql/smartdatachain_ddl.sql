/*
 * 数据库建表语句
 * @author haolen
 * @version 1.0
 * @time 2018/10/23
 */


CREATE TABLE `work_group_local` (
  `id` int(11) AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=545 DEFAULT CHARSET=utf8;


CREATE TABLE `work_group_local_detail` (
  `app_id` int(11) AUTO_INCREMENT,
  `info` varchar(255) NOT NULL COMMENT '任务信息',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '任务名',
  `type` varchar(255) NOT NULL DEFAULT '' COMMENT '任务类型',
  `status` varchar(255) NOT NULL DEFAULT '' COMMENT '任务状态',
  `time_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `graph` text NOT NULL COMMENT '任务规则详细',
  `type_sub` varchar(255) NOT NULL DEFAULT '' COMMENT '任务提交类型',
  `count_local` int(11) NOT NULL  COMMENT '任务抓取本地条数',
  `collection_id` int(11) NOT NULL  COMMENT '任务组id',
  `rule` varchar(255) NOT NULL DEFAULT '' COMMENT '任务规则',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=545 DEFAULT CHARSET=utf8;

