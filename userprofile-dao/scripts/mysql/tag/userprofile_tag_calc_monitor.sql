create table userprofile_tag_calc_monitor(
  `data_date` varchar(45) NOT NULL COMMENT '数据日期,格式：yyyyMMdd',
  `tag_id` varchar(45) NOT NULL COMMENT '标签编号',
  `tag_theme` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '标签主题',
  `is_success` tinyint(4) DEFAULT '1' COMMENT '完成状态，1：完成，0：未完成',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`data_date`,`tag_id`),
  KEY `d_t_index` (`data_date`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='基础标签计算监控表';